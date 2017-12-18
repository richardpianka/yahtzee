package io.pianka.yahtzee.logic

import io.pianka.yahtzee.model.dice.Roll

object LowerSectionChecker {

  def threeOfAKind(roll: Roll): Boolean =
    numberOfAKind(3)(roll)

  def fourOfAKind(roll: Roll): Boolean =
    numberOfAKind(4)(roll)

  def yahtzee(roll: Roll): Boolean =
    numberOfAKind(5)(roll)

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
  private case class Sum(
    aces:   Int = 0,
    twos:   Int = 0,
    threes: Int = 0,
    fours:  Int = 0,
    fives:  Int = 0,
    sixes:  Int = 0
  )

  private def sumByDie(roll: Roll) = {
    roll.dice.map(_.die.value).foldLeft(Sum()) { (accum, value) =>
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
}
