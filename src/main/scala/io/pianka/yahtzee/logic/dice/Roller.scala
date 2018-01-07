package io.pianka.yahtzee.logic.dice

import io.pianka.yahtzee.common.exception.IllegalRuleViolationException
import io.pianka.yahtzee.common.score.Scoring
import io.pianka.yahtzee.common.score.Scoring.RollStatistics
import io.pianka.yahtzee.model.dice.{Die, RolledDice, RolledDie}

import scala.util.Random

object Roller {

  private val random = new Random

  def rollDie: Die = {
    Die(random.nextInt(5) + 1)
  }

  def rollAll: RolledDice = {
    RolledDice(rollN(5))
  }

  private def rollN(n: Int): Seq[RolledDie] = {
    require(n <= 5)

    (1 to n).map { _ =>
      RolledDie(rollDie, kept = false)
    }
  }

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
