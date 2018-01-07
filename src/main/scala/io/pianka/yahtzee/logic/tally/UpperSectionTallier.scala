package io.pianka.yahtzee.logic.tally

import io.pianka.yahtzee.common.score.Rules
import io.pianka.yahtzee.model.score.upper.{UpperSection, UpperSectionTotal}

object UpperSectionTallier extends Tallier {

  /**
    * Tallies the score of the upper section of the card.
    *
    * @param upperSection The upper section of the card.
    * @return The total tally of the upper section, including bonus.
    */
  def tally(upperSection: UpperSection): UpperSectionTotal = {
    val subtotal =
      value(upperSection.aces) +
      value(upperSection.twos) +
      value(upperSection.threes) +
      value(upperSection.fours) +
      value(upperSection.fives) +
      value(upperSection.sixes)

    val bonus =
      if (subtotal >= Rules.bonusThreshold) {
        Rules.bonusValue
      } else {
        0 // no bonus :(
      }

    UpperSectionTotal(upperSection, subtotal, bonus, subtotal + bonus)
  }
}