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

  /* Full house */
  // Positive
  "A three full of fours" should "score to twenty five" in {
    val roll = createRoll(4, 3, 3, 4, 3)
    LowerSectionScorer.scoreFullHouse(roll) should be (25)
  }

  "A two full of aces" should "score to twenty five" in {
    val roll = createRoll(1, 2, 2, 2, 1)
    LowerSectionScorer.scoreFullHouse(roll) should be (25)
  }

  /* Small straight */
  // Positive
  "One through four" should "score to thirty" in {
    val roll = createRoll(1, 2, 3, 4, 4)
    LowerSectionScorer.scoreSmallStraight(roll) should be (30)
  }

  "One through five" should "score to thirty" in {
    val roll = createRoll(1, 2, 3, 4, 5)
    LowerSectionScorer.scoreSmallStraight(roll) should be (30)
  }

  "Two through five" should "score to thirty" in {
    val roll = createRoll(2, 3, 4, 5, 5)
    LowerSectionScorer.scoreSmallStraight(roll) should be (30)
  }

  "Two through six" should "score to thirty" in {
    val roll = createRoll(2, 3, 4, 5, 6)
    LowerSectionScorer.scoreSmallStraight(roll) should be (30)
  }

  "Three through six" should "score to thirty" in {
    val roll = createRoll(3, 4, 5, 6, 6)
    LowerSectionScorer.scoreSmallStraight(roll) should be (30)
  }

  /* Large straight */
  // Positive
  "One through five" should "score to forty" in {
    val roll = createRoll(1, 2, 3, 4, 5)
    LowerSectionScorer.scoreLargeStraight(roll) should be (40)
  }

  "Two through six" should "score to forty" in {
    val roll = createRoll(2, 3, 4, 5, 6)
    LowerSectionScorer.scoreLargeStraight(roll) should be (40)
  }

  /* Yahtzee */
  // Positive
  "First yahtzee" should "score to fifty" in {
    val roll = createRoll(1, 1, 1, 1, 1)
    LowerSectionScorer.scoreYahtzee(roll) should be (50)
  }

  "Subsequent yahtzees" should "score to one hundred" in {
    val roll = createRoll(1, 1, 1, 1, 1)
    LowerSectionScorer.scoreYahtzee(roll, first = false) should be (100)
  }
}
