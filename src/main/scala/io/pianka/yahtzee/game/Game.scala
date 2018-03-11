package io.pianka.yahtzee.game

import io.pianka.yahtzee.common.exception.IllegalRuleViolationException
import io.pianka.yahtzee.game.player.Player
import io.pianka.yahtzee.logic.decision.{Placer, Zeroer}
import io.pianka.yahtzee.logic.roll.{Eligibility, Roller}
import io.pianka.yahtzee.model.dice.RolledDice

import scala.util.Random

/**
  * All mutability lives here.
  *
  * @param players The lucky.
  */
class Game(private val players: Seq[Player]) {
  require(players.length >= 2) // otherwise it's not a game

  //TODO use random dice turns to establish order
  private val orderedPlayers: Seq[Player] = Random.shuffle(players)
  //https://www.cs.utexas.edu/users/EWD/transcriptions/EWD08xx/EWD831.html
  private var turnNumber = 0 // plus it's easier mathematically; add one for display if necessary

  def run(): Unit = {
    while (!gameFinished) {
      runTurn(currentPlayer)
      nextTurn()
    }
  }

  private def gameFinished = {
    eligiblePlayers.isEmpty
  }

  private def eligiblePlayers = {
    orderedPlayers.filter(x => Eligibility.eligibleForRolling(x.scoreCard))
  }

  private def currentPlayer = {
    val currentlyEligiblePlayers = eligiblePlayers
    val playerCount = currentlyEligiblePlayers.length
    val index = turnNumber % playerCount

    currentlyEligiblePlayers(index)
  }

  private def nextTurn(): Unit = {
    turnNumber += 1
  }

  private def runTurn(player: Player): Unit = {
    var rolls = 1
    var done = false
    var dice = Roller.rollAll

    while (rolls <= 3 && !done) {
      val decision = player.agent.roll(dice, player.scoreCard, rolls) // agent makes a decision
      val outcome = processDecision(player, dice, decision) // roll some dice (or not) given decision

      outcome match {
        case NextRoll(newDice) =>
          // ensure the last roll decision isn't another roll
          if (rolls == 3) {
            throw IllegalRuleViolationException("A player cannot exceed 3 rolls during a turn.")
          }
          dice = newDice
          rolls += 1
        case Zeroed(placement) => {
          player.scoreCard = Zeroer.zeroPlacement (player.scoreCard, placement)
          done = true
        }
        case Placed(placement) => {
          player.scoreCard = Placer.scorePlacement(player.scoreCard, placement, dice)
          done = true
        }
      }
    }
  }

  private def processDecision(player: Player, currentDice: RolledDice, decision: Decision): Outcome = {
    decision match {
      case RerollAll              => NextRoll(Roller.rollAll)
      case KeepAndReroll(keepers) => NextRoll(Roller.keepAndRoll(currentDice, keepers))
      case ZeroOut(placement)     => Zeroed(placement)
      case PlaceScore(placement)  => Placed(placement)
    }
  }
}