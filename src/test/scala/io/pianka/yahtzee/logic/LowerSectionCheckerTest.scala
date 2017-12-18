package io.pianka.yahtzee.logic

import io.pianka.yahtzee.model.dice.{Die, Roll, RolledDie}

import org.scalatest._

class LowerSectionCheckerTest extends FlatSpec with Matchers {

  /* Helpers */
  private def createRoll(values: Int*) = {
    Roll(values.map(x => RolledDie(Die(x), kept = false)))
  }

  /* Three of a kind */
  // Positive
  "Three aces" should "be three of a kind" in {
    val roll = createRoll(1, 1, 1, 2, 3)
    LowerSectionChecker.threeOfAKind(roll) should be (true)
  }

  "Four twos" should "be three of a kind" in {
    val roll = createRoll(2, 1, 2, 2, 2)
    LowerSectionChecker.threeOfAKind(roll) should be (true)
  }

  "Five sixes" should "be three of a kind" in {
    val roll = createRoll(6, 6, 6, 6, 6)
    LowerSectionChecker.threeOfAKind(roll) should be (true)
  }

  // Negative
  "One through five" should "not be three of a kind" in {
    val roll = createRoll(1, 2, 3, 4, 5)
    LowerSectionChecker.threeOfAKind(roll) shouldNot be (true)
  }

  /* Four of a kind */
  // Positive
  "Four aces" should "be four of a kind" in {
    val roll = createRoll(1, 1, 1, 1, 3)
    LowerSectionChecker.fourOfAKind(roll) should be (true)
  }

  "Five sixes" should "be four of a kind" in {
    val roll = createRoll(6, 6, 6, 6, 6)
    LowerSectionChecker.fourOfAKind(roll) should be (true)
  }

  // Negative
  "One through five" should "not be four of a kind" in {
    val roll = createRoll(1, 2, 3, 4, 5)
    LowerSectionChecker.fourOfAKind(roll) shouldNot be (true)
  }

  /* Yahtzee */
  // Positive
  "Five aces" should "be a yahtzee" in {
    val roll = createRoll(1, 1, 1, 1, 1)
    LowerSectionChecker.yahtzee(roll) should be (true)
  }

  "Five sixes" should "a yahtzee" in {
    val roll = createRoll(6, 6, 6, 6, 6)
    LowerSectionChecker.yahtzee(roll) should be (true)
  }

  // Negative
  "One through five" should "not be a yahtzee" in {
    val roll = createRoll(1, 2, 3, 4, 5)
    LowerSectionChecker.yahtzee(roll) shouldNot be (true)
  }

  "Four aces" should "not be a yahtzee" in {
    val roll = createRoll(1, 1, 1, 1, 3)
    LowerSectionChecker.yahtzee(roll) shouldNot be (true)
  }

  /* Small Straights */
  // Positive
  "Ace consecutive four" should "be a small straight" in {
    val roll = createRoll(2, 1, 3, 4, 1)
    LowerSectionChecker.smallStraight(roll) should be (true)
  }

  "Two consecutive four" should "be a small straight" in {
    val roll = createRoll(5, 2, 3, 4, 4)
    LowerSectionChecker.smallStraight(roll) should be (true)
  }

  "Three consecutive four" should "be a small straight" in {
    val roll = createRoll(5, 6, 3, 4, 6)
    LowerSectionChecker.smallStraight(roll) should be (true)
  }

  //NOTE Large straights are also small straights
  "Ace consecutive five" should "be a small straight" in {
    val roll = createRoll(2, 5, 3, 4, 1)
    LowerSectionChecker.smallStraight(roll) should be (true)
  }

  "Two consecutive five" should "be a small straight" in {
    val roll = createRoll(5, 2, 3, 6, 4)
    LowerSectionChecker.smallStraight(roll) should be (true)
  }

  // Negative
  "Three aces" should "not be a small straight" in {
    val roll = createRoll(1, 1, 1, 2, 3)
    LowerSectionChecker.smallStraight(roll) should be (false)
  }

  "Four twos" should "not be a small straight" in {
    val roll = createRoll(2, 1, 2, 2, 2)
    LowerSectionChecker.smallStraight(roll) should be (false)
  }

  "Five sixes" should "not be a small straight" in {
    val roll = createRoll(6, 6, 6, 6, 6)
    LowerSectionChecker.smallStraight(roll) should be (false)
  }

  /* Large Straights */
  // Positive
  "Ace consecutive five" should "be a large straight" in {
    val roll = createRoll(2, 5, 3, 4, 1)
    LowerSectionChecker.largeStraight(roll) should be (true)
  }

  "Two consecutive five" should "be a large straight" in {
    val roll = createRoll(5, 2, 3, 6, 4)
    LowerSectionChecker.largeStraight(roll) should be (true)
  }

  // Negative
  "Three aces" should "not be a large straight" in {
    val roll = createRoll(1, 1, 1, 2, 3)
    LowerSectionChecker.largeStraight(roll) should be (false)
  }

  "Four twos" should "not be a large straight" in {
    val roll = createRoll(2, 1, 2, 2, 2)
    LowerSectionChecker.largeStraight(roll) should be (false)
  }

  "Five sixes" should "not be a large straight" in {
    val roll = createRoll(6, 6, 6, 6, 6)
    LowerSectionChecker.largeStraight(roll) should be (false)
  }

  /* Full Houses */
  // Positive
  "Two aces and three twos" should "be a full house" in {
    val roll = createRoll(2, 3, 2, 3, 2)
    LowerSectionChecker.fullHouse(roll) should be (true)
  }

  "Two threes and three twos" should "be a full house" in {
    val roll = createRoll(3, 3, 2, 3, 2)
    LowerSectionChecker.fullHouse(roll) should be (true)
  }

  "TTwo threes and three sixes" should "be a full house" in {
    val roll = createRoll(6, 3, 6, 6, 3)
    LowerSectionChecker.fullHouse(roll) should be (true)
  }

  // Negative
  "Three aces" should "not be a full house" in {
    val roll = createRoll(1, 1, 1, 2, 3)
    LowerSectionChecker.fullHouse(roll) should be (false)
  }

  "Four twos" should "not be a full house" in {
    val roll = createRoll(2, 1, 2, 2, 2)
    LowerSectionChecker.fullHouse(roll) should be (false)
  }

  "Five sixes" should "not be a full house" in {
    val roll = createRoll(6, 6, 6, 6, 6)
    LowerSectionChecker.fullHouse(roll) should be (false)
  }
}