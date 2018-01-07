package io.pianka.yahtzee.logic.score

import io.pianka.yahtzee.common.exception.IllegalRuleViolationException
import io.pianka.yahtzee.common.score.Scoring
import io.pianka.yahtzee.model.dice.RolledDice

object UpperSectionScorer {

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
