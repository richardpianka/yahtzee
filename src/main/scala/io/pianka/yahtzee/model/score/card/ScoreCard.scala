package io.pianka.yahtzee.model.score.card

import io.pianka.yahtzee.model.score.lower.LowerSection
import io.pianka.yahtzee.model.score.upper.UpperSection

/**
  * A score card used during the playing of a game before it has finished.
  *
  * @param upperSection The upper section of the score card, from pure dice counts.
  * @param lowerSection The lower section of the score card, from particular dice configurations.
  */
case class ScoreCard(
  upperSection: UpperSection,
  lowerSection: LowerSection
)

object ScoreCard {

  val blank = ScoreCard(
    upperSection = UpperSection.blank,
    lowerSection = LowerSection.blank
  )
}