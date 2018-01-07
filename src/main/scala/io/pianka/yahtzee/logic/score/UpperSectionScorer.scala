package io.pianka.yahtzee.logic.score

import io.pianka.yahtzee.common.exception.IllegalRuleViolationException
import io.pianka.yahtzee.common.score.Scoring
import io.pianka.yahtzee.model.dice.RolledDice

object UpperSectionScorer {

  /**
    * Scores a full roll of dice, only including those of a particular value.  No dice of a different value are
    * counted.  The score is a simple sum of the dice of that particular value.
    *
    * @param roll The roll of dice.
    * @param value The particular value.
    * @return
    */
  def scoreRollByValue(roll: RolledDice, value: Int): Int = {
    val statistics = Scoring.summaryStatisticsByRoll(roll)

    val count = value match {
      case 1 => statistics.aces
      case 2 => statistics.twos
      case 3 => statistics.threes
      case 4 => statistics.fours
      case 5 => statistics.fives
      case 6 => statistics.sixes
      case _ => throw IllegalRuleViolationException("Scoring by values outside a six-sided die.")
    }

    count * value
  }
}
