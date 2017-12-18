package io.pianka.yahtzee.model.score.lower

/**
  * The calculated totals of the lower section of a score card.
  *
  * @param lowerSection The lower section of the score card.
  * @param subtotal The subtotal of all points from non-yahtzee bonuses.
  * @param bonus The total of bonuses from additional yahtzees.
  * @param total The subtotal summed with the bonus.
  */
case class LowerSectionTotal(
  lowerSection: LowerSection,
  subtotal:     Int,
  bonus:        Int,
  total:        Int
)