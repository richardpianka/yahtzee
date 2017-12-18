package io.pianka.yahtzee.logic.tally

import io.pianka.yahtzee.model.score.lower.LowerSection

object LowerSectionTallier extends Tallier {

  def tally(lowerSection: LowerSection) = {
    val subtotal =
      value(lowerSection.threeOfAKind) +
      value(lowerSection.fourOfAKind) +
      value(lowerSection.fullHouse) +
      value(lowerSection.smallStraight) +
      value(lowerSection.largeStraight) +
      value(lowerSection.yahtzee)
  }
}