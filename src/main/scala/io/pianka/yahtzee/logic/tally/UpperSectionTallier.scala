package io.pianka.yahtzee.logic.tally

import io.pianka.yahtzee.model.score.upper.{UpperSection, UpperSectionTotal}

object UpperSectionTallier extends Tallier {

  private val bonusThreshold = 63
  private val bonusValue = 35

  def tally(upperSection: UpperSection): UpperSectionTotal = {
    val subtotal =
      value(upperSection.aces) +
      value(upperSection.twos) +
      value(upperSection.threes) +
      value(upperSection.fours) +
      value(upperSection.fives) +
      value(upperSection.sixes)

    val bonus =
      if (subtotal >= bonusThreshold) {
        bonusValue
      } else {
        0 // no bonus :(
      }

    UpperSectionTotal(upperSection, subtotal, bonus, subtotal + bonus)
  }
}