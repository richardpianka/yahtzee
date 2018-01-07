package io.pianka.yahtzee.logic.check

import io.pianka.yahtzee.common.score.Scoring._
import io.pianka.yahtzee.model.dice.RolledDice

/**
  * Validates and/or checks whether the line items in the lower card section exist within a set of rolled dice.
  */
object LowerSectionChecker {

  /* Multiple Same Values */
  /**
    * Whether a particular roll contains a valid three of a kind: at least three dice of the same value.
    *
    * @param roll The particular roll.
    * @return If the roll is a valid three of a kind.
    */
  def threeOfAKind(roll: RolledDice): Boolean =
    numberOfAKind(3)(roll)

  /**
    * Whether a particular roll contains a valid four of a kind: at least four dice of the same value.
    *
    * @param roll The particular roll.
    * @return If the roll is a valid four of a kind.
    */
  def fourOfAKind(roll: RolledDice): Boolean =
    numberOfAKind(4)(roll)

  // "five of a kind"
  /**
    * Whether a particular roll contains a yahtzee: all five dice are of the same value.
    *
    * @param roll The particular roll.
    * @return If the roll is a valid yahtzee.
    */
  def yahtzee(roll: RolledDice): Boolean =
    numberOfAKind(5)(roll)

  /* Straights */
  /**
    * Whether a particular roll contains a small straight: four dice in consecutive numerical value.  A small straight
    * is a subset of a large straight, so large straights are also small straights.
    *
    * @param roll The particular roll.
    * @return If the roll is a valid small straight.
    */
  def smallStraight(roll: RolledDice): Boolean = {
    val maxOfDice = presenceByDie(roll)

    maxOfDice match {
      case RollStatistics(1, 1, 1, 1, _, _) => true
      case RollStatistics(_, 1, 1, 1, 1, _) => true
      case RollStatistics(_, _, 1, 1, 1, 1) => true
      case _ => false
    }
  }

  /**
    * Whether a particular roll contains a large straight: five dice in consecutive numerical value.
    *
    * @param roll The particular roll.
    * @return If the roll is a valid large straight.
    */
  def largeStraight(roll: RolledDice): Boolean = {
    val maxOfDice = presenceByDie(roll)

    maxOfDice match {
      case RollStatistics(1, 1, 1, 1, 1, _) => true
      case RollStatistics(_, 1, 1, 1, 1, 1) => true
      case _ => false
    }
  }

  /* Full House */
  /**
    * Whether a particular roll contains a full house: three of a first value, two of a second and different value.
    * In practice, a yahtzee can be used as a full house under joker rules, but that's a scoring matter, and not
    * actually technically a full house.
    *
    * NOTE: http://mix957gr.com/the-great-yahtzee-debate-can-you-use-a-yahtzee-as-a-full-house/
    * NOTE: http://billissowrong.blogspot.com/ -- the internet rarely disappoints
    * NOTE: http://grail.sourceforge.net/demo/yahtzee/rules.html
    *
    * @param roll The particular roll.
    * @return If the roll is a valid full house.
    */
  def fullHouse(roll: RolledDice): Boolean = {
    val sumOfDice = summaryStatisticsByRoll(roll)
    val stats = Set(
      sumOfDice.aces,
      sumOfDice.twos,
      sumOfDice.threes,
      sumOfDice.fours,
      sumOfDice.fives,
      sumOfDice.sixes
    )

    stats == Set(0, 2, 3)
  }

  /* Helpers */
  private def numberOfAKind(number: Int)(roll: RolledDice): Boolean = {
    val sumOfDice = summaryStatisticsByRoll(roll)

    sumOfDice match {
      case s if s.aces   >= number => true
      case s if s.twos   >= number => true
      case s if s.threes >= number => true
      case s if s.fours  >= number => true
      case s if s.fives  >= number => true
      case s if s.sixes  >= number => true
      case _ => false
    }
  }
}
