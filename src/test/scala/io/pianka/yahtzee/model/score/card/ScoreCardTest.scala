package io.pianka.yahtzee.model.score.card

import io.pianka.yahtzee.model.score.lower.LowerSection
import io.pianka.yahtzee.model.score.upper.UpperSection
import org.scalatest.{FlatSpec, Matchers}

class ScoreCardTest extends FlatSpec with Matchers {

  "An empty score card" should "instantiate" in {
    ScoreCard(UpperSection.blank, LowerSection.blank)
  }

  "The blank score card" should "be instantiated" in {
    ScoreCard.blank
  }
}
