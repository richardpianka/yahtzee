package io.pianka.yahtzee.logic.tally

import io.pianka.yahtzee.model.score.lower.{LowerSection, LowerSectionTotal}

object LowerSectionTallier extends Tallier {

  def tally(lowerSection: LowerSection): LowerSectionTotal = {
    val subtotal =
      value(lowerSection.threeOfAKind) +
      value(lowerSection.fourOfAKind) +
      value(lowerSection.fullHouse) +
      value(lowerSection.smallStraight) +
      value(lowerSection.largeStraight) +
      value(lowerSection.chance) +
      value(lowerSection.yahtzee)

    val bonus =
      lowerSection.yahtzeeBonus.map(value).sum

    LowerSectionTotal(lowerSection, subtotal, bonus, subtotal + bonus)
  }
}