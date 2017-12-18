package io.pianka.yahtzee.logic

import io.pianka.yahtzee.model.dice.Roll

object LowerSectionChecker {

  /* Multiple Same Values */
  def threeOfAKind(roll: Roll): Boolean =
    numberOfAKind(3)(roll)

  def fourOfAKind(roll: Roll): Boolean =
    numberOfAKind(4)(roll)

  // five of a kind
  def yahtzee(roll: Roll): Boolean =
    numberOfAKind(5)(roll)

  /* Straights */
  def smallStraight(roll: Roll): Boolean = {
    val maxOfDice = maxByDie(roll)

    maxOfDice match {
      case DiceStatistics(1, 1, 1, 1, _, _) => true
      case DiceStatistics(_, 1, 1, 1, 1, _) => true
      case DiceStatistics(_, _, 1, 1, 1, 1) => true
      case _ => false
    }
  }

  def largeStraight(roll: Roll): Boolean = {
    val maxOfDice = maxByDie(roll)

    maxOfDice match {
      case DiceStatistics(1, 1, 1, 1, 1, _) => true
      case DiceStatistics(_, 1, 1, 1, 1, 1) => true
      case _ => false
    }
  }

  /* Full House */
  def fullHouse(roll: Roll): Boolean = {
    val sumOfDice = sumByDie(roll)
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
  private def numberOfAKind(number: Int)(roll: Roll) = {
    val sumOfDice = sumByDie(roll)

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

  /* This whole helper will probably be useful elsewhere */
  private case class DiceStatistics(
    aces:   Int = 0,
    twos:   Int = 0,
    threes: Int = 0,
    fours:  Int = 0,
    fives:  Int = 0,
    sixes:  Int = 0
  )

  private def sumByDie(roll: Roll) = {
    //TODO add memoization here
    roll.dice.map(_.die.value).foldLeft(DiceStatistics()) { (accum, value) =>
      value match {
        case 1 => accum.copy(aces   = accum.aces   + 1)
        case 2 => accum.copy(twos   = accum.twos   + 1)
        case 3 => accum.copy(threes = accum.threes + 1)
        case 4 => accum.copy(fours  = accum.fours  + 1)
        case 5 => accum.copy(fives  = accum.fives  + 1)
        case 6 => accum.copy(sixes  = accum.sixes  + 1)
      }
    }
  }

  private def maxByDie(roll: Roll) = {
    //TODO add memoization here
    roll.dice.map(_.die.value).foldLeft(DiceStatistics()) { (accum, value) =>
      value match {
        case 1 => accum.copy(aces   = Math.max(accum.aces,   1))
        case 2 => accum.copy(twos   = Math.max(accum.twos,   1))
        case 3 => accum.copy(threes = Math.max(accum.threes, 1))
        case 4 => accum.copy(fours  = Math.max(accum.fours,  1))
        case 5 => accum.copy(fives  = Math.max(accum.fives,  1))
        case 6 => accum.copy(sixes  = Math.max(accum.sixes,  1))
      }
    }
  }
}
