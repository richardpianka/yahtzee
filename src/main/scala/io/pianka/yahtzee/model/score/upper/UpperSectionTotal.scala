package io.pianka.yahtzee.model.score.upper

/**
  * The calculated totals of the upper section of a score card.
  *
  * @param upperSection The upper section of the score card.
  * @param subtotal The subtotal of all points of the first six scores.
  * @param bonus The attainable bonus given a certain threshold of the subtotal.
  * @param total The subtotal summed with the bonus.
  */
case class UpperSectionTotal(
  upperSection: UpperSection,
  subtotal:     Int,
  bonus:        Int,
  total:        Int
)
