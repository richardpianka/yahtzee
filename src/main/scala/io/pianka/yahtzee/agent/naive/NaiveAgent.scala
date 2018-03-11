package io.pianka.yahtzee.agent.naive

import io.pianka.yahtzee.common.score._
import io.pianka.yahtzee.game.{Decision, ZeroOut}
import io.pianka.yahtzee.game.player.Agent
import io.pianka.yahtzee.model.dice.{Die, RolledDice}
import io.pianka.yahtzee.model.score.card.ScoreCard

class NaiveAgent(val name: String) extends Agent {

  private var roll = 0

  def roll(dice: RolledDice, scoreCard: ScoreCard, rollCount: Int): Decision = {
    roll += 1

    println(name + ": " + roll)

    roll match {
      //upper section
      case 1  => ZeroOut(Aces)
      case 2  => ZeroOut(Twos)
      case 3  => ZeroOut(Threes)
      case 4  => ZeroOut(Fours)
      case 5  => ZeroOut(Fives)
      case 6  => ZeroOut(Sixes)

      //lower section
      case 7  => ZeroOut(ThreeOfAKind)
      case 8  => ZeroOut(FourOfAKind)
      case 9  => ZeroOut(FullHouse)
      case 10 => ZeroOut(SmallStraight)
      case 11 => ZeroOut(LargeStraight)
      case 12 => ZeroOut(Yahtzee)
      case 13 => ZeroOut(Chance)
    }
  }

  def rolledForOrder(die: Die, otherPlayers: Seq[Die]): Unit = {}
  def establishedOrder(order: Int, playerCount: Int): Unit = {}
  def gameOver(order: Int): Unit = {}
}
