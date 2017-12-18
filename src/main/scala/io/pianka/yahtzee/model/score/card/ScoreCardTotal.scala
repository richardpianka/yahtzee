package io.pianka.yahtzee.model.score.card

import io.pianka.yahtzee.model.score.lower.LowerSectionTotal
import io.pianka.yahtzee.model.score.upper.UpperSectionTotal

/**
  * A totalized score card including the totals of both the upper and lower sections.
  *
  * @param upperSectionTotal The calculated total of the upper score card section.
  * @param lowerSectionTotal The calculated total of the lower score card section.
  * @param grandTotal The summed total of both sections of a score card, and the final score for a player.
  */
case class ScoreCardTotal(
  upperSectionTotal: UpperSectionTotal,
  lowerSectionTotal: LowerSectionTotal,
  grandTotal:        Int
)