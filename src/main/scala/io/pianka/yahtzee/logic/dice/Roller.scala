package io.pianka.yahtzee.logic.dice

import io.pianka.yahtzee.common.exception.IllegalRuleViolationException
import io.pianka.yahtzee.common.score.Scoring
import io.pianka.yahtzee.common.score.Scoring.RollStatistics
import io.pianka.yahtzee.model.dice.{Die, RolledDice, RolledDie}

import scala.util.Random

object Roller {

  // for all stochastic needs of the dice rolling aspects of the game
  private val random = new Random

  /**
    * Produces a single valid random die.
    *
    * @return A single random die.
    */
  def rollDie: Die = {
    Die(random.nextInt(5) + 1)
  }

  /**
    * Produces a full roll of five newly random dice.
    *
    * @return A fresh roll of all newly random dice.
    */
  def rollAll: RolledDice = {
    RolledDice(rollDice(5))
  }

  /**
    * As a helper function, rolls a number of dice which are necessarily not kept from a previous roll (if there even
    * was one).
    *
    * @param number The number of dice to roll.
    * @return A fresh roll of the number of newly rolled die or dice.
    */
  private def rollDice(number: Int): Seq[RolledDie] = {
    require(number >= 1)
    require(number <= 5)

    (1 to number).map { _ =>
      RolledDie(rollDie, kept = false)
    }
  }

  /**
    * For use during a player's turn to keep some dice while neither rerolling all of the previously rolled dice nor
    * rerolling zero dice.  This method also validates that kept dice are within the previous roll.  This should be
    * used for the second or third roll of a player's turn.
    *
    * @param rolledDice The previous rolled dice.
    * @param keepers The die or dice chosen to be kept for a subsequent roll.
    * @return A subsequent roll of dice where some were kept from a previous roll.
    */
  def keepAndRoll(rolledDice: RolledDice, keepers: Seq[Die]): RolledDice = {
    // ensure kept dice exist in previous roll
    val canKeep = validateKeepers(rolledDice, keepers)
    if (!canKeep) {
      throw IllegalRuleViolationException("You're unable to keep dice you didn't roll.")
    }

    // there should be a positive amount of remaining dice to roll
    val leftToRoll = 5 - keepers.length
    if (leftToRoll <= 0) {
      throw IllegalRuleViolationException("Cannot reroll zero or negative amount of dice.")
    }

    // roll remaining dice
    val newDice =
      (1 to leftToRoll).map(_ => RolledDie(rollDie, kept = false))

    // make sure keepers are keepers
    val keptDice = keepers.map { die =>
      RolledDie(die, kept = true)
    }

    RolledDice(keptDice ++ newDice)
  }

  /**
    * Ensures every kept die exists in   the current roll.
    *
    * @param rolledDice The current roll.
    * @param keepers The kept dice.
    * @return
    */
  private def validateKeepers(rolledDice: RolledDice, keepers: Seq[Die]) = {
    require(keepers.length <= 5)

    // validate all keepers are in the roll
    val kept = Scoring.summaryStatisticsByDice(keepers)
    val rolled = Scoring.summaryStatisticsByRoll(rolledDice)

    def canKeep(dieCount: RollStatistics => Int): Boolean = {
      val keptCount = dieCount(kept)
      val rolledCount = dieCount(rolled)

      keptCount <= rolledCount
    }

    canKeep(_.aces)   &&
    canKeep(_.twos)   &&
    canKeep(_.threes) &&
    canKeep(_.fours)  &&
    canKeep(_.fives)  &&
    canKeep(_.sixes)
  }
}
