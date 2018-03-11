package io.pianka.yahtzee.logic.roll

import io.pianka.yahtzee.common.TestHelpers
import io.pianka.yahtzee.model.score.card.ScoreCard
import org.scalatest.{FlatSpec, Matchers}

class EligibilityTest extends FlatSpec with Matchers {

  // Positive
  "Blank score card" should "be eligible to roll" in {
    val blankScoreCard = ScoreCard.blank
    Eligibility.eligibleForRolling(blankScoreCard) shouldBe true
  }

  // Negative
  "Fully zeroed score card" should "not be eligible to roll" in {
    val zeroedScoreCard = TestHelpers.createFullyZeroedScoreCard
    Eligibility.eligibleForRolling(zeroedScoreCard) shouldBe false
  }
}
