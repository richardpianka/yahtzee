package io.pianka.yahtzee.logic.score

import org.scalatest.{FlatSpec, Matchers}
import io.pianka.yahtzee.common.TestHelpers._
import io.pianka.yahtzee.common.exception.IllegalRuleViolationException
import io.pianka.yahtzee.model.dice.{Die, Roll, RolledDie}

class UpperSectionScorerTest extends FlatSpec with Matchers {

  // Positive
  "Five aces" should "sum to 5" in {
    val roll = createRoll(1, 1, 1, 1, 1)
    UpperSectionScorer.scoreRollByValue(roll, 1) should be (5)
  }

  "Five twos" should "sum to 10" in {
    val roll = createRoll(2, 2, 2, 2, 2)
    UpperSectionScorer.scoreRollByValue(roll, 2) should be (10)
  }

  "Five threes" should "sum to 15" in {
    val roll = createRoll(3, 3, 3, 3, 3)
    UpperSectionScorer.scoreRollByValue(roll, 3) should be (15)
  }

  "Five fours" should "sum to 20" in {
    val roll = createRoll(4, 4, 4, 4, 4)
    UpperSectionScorer.scoreRollByValue(roll, 4) should be (20)
  }

  "Five fives" should "sum to 25" in {
    val roll = createRoll(5, 5, 5, 5, 5)
    UpperSectionScorer.scoreRollByValue(roll, 5) should be (25)
  }

  "Five sixes" should "sum to 30" in {
    val roll = createRoll(6, 6, 6, 6, 6)
    UpperSectionScorer.scoreRollByValue(roll, 6) should be (30)
  }

  // Negative
  "Five zeroes" should "throw an IllegalRuleViolationException" in {
    an [IllegalRuleViolationException] should be thrownBy {
      // Die won't allow values outside of [1..6]
      val die = RolledDie(new Die(0))
      val roll = Roll(Seq(die, die, die, die, die))
      UpperSectionScorer.scoreRollByValue(roll, 0) should be (0)
    }
  }
}
