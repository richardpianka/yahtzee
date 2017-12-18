package io.pianka.yahtzee.logic.score

import org.scalatest.{FlatSpec, Matchers}
import io.pianka.yahtzee.common.TestHelpers._

class LowerSectionScorerTest extends FlatSpec with Matchers {

  /* Three of a kind */
  // Positive
  "A three of a kind roll" should "sum to the values of its dice" in {
    val roll = createRoll(1, 2, 1, 6, 1)
    LowerSectionScorer.scoreThreeOfAKind(roll) should be (11)
  }

  "A three of a kind roll that meets four of a kind" should "sum to the values of its dice" in {
    val roll = createRoll(1, 2, 1, 1,1 )
    LowerSectionScorer.scoreThreeOfAKind(roll) should be (6)
  }

  // Negative
  "A non-three of a kind roll" should "throw an InvalidArgumentException" in {
    a [IllegalArgumentException] should be thrownBy {
      val roll = createRoll(1, 2, 3, 4, 5)
      LowerSectionScorer.scoreThreeOfAKind(roll) should be (15)
    }
  }

  /* Four of a kind */
  // Positive
  "A four of a kind roll" should "sum to the values of its dice" in {
    val roll = createRoll(2, 2, 3, 2, 2)
    LowerSectionScorer.scoreThreeOfAKind(roll) should be (11)
  }

  "A three of a kind roll that meets five of a kind (yahtzee)" should "sum to the values of its dice" in {
    val roll = createRoll(6, 6, 6, 6, 6)
    LowerSectionScorer.scoreThreeOfAKind(roll) should be (30)
  }

  // Negative
  "A non-four of a kind roll" should "throw an InvalidArgumentException" in {
    a [IllegalArgumentException] should be thrownBy {
      val roll = createRoll(1, 2, 3, 4, 5)
      LowerSectionScorer.scoreThreeOfAKind(roll) should be (15)
    }
  }
}
