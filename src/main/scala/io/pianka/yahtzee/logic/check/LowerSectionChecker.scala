package io.pianka.yahtzee.logic.check

import io.pianka.yahtzee.common.score.Scoring._
import io.pianka.yahtzee.model.dice.RolledDice

object LowerSectionChecker {

  /* Multiple Same Values */
  def threeOfAKind(roll: RolledDice): Boolean =
    numberOfAKind(3)(roll)

  def fourOfAKind(roll: RolledDice): Boolean =
    numberOfAKind(4)(roll)

  // five of a kind
  def yahtzee(roll: RolledDice): Boolean =
    numberOfAKind(5)(roll)

  /* Straights */
  def smallStraight(roll: RolledDice): Boolean = {
    val maxOfDice = presenceByDie(roll)

    maxOfDice match {
      case RollStatistics(1, 1, 1, 1, _, _) => true
      case RollStatistics(_, 1, 1, 1, 1, _) => true
      case RollStatistics(_, _, 1, 1, 1, 1) => true
      case _ => false
    }
  }

  def largeStraight(roll: RolledDice): Boolean = {
    val maxOfDice = presenceByDie(roll)

    maxOfDice match {
      case RollStatistics(1, 1, 1, 1, 1, _) => true
      case RollStatistics(_, 1, 1, 1, 1, 1) => true
      case _ => false
    }
  }

  /* Full House */
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
