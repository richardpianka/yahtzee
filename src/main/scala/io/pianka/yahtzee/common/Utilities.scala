package io.pianka.yahtzee.common

import io.pianka.yahtzee.model.dice.Roll

object Utilities {

  /**
    * Contains some statistic (e.g., count, min, max) of each die value in a roll.
    *
    * @param aces The statistic for aces.
    * @param twos The statistic for twos.
    * @param threes The statistic for threes.
    * @param fours The statistic fof fours.
    * @param fives The statistic for fives.
    * @param sixes The statistic for sixes.
    */
  //TODO this class is less than ideal; 1s and 0s should be used for booleans
  case class RollStatistics(
    aces:   Int = 0,
    twos:   Int = 0,
    threes: Int = 0,
    fours:  Int = 0,
    fives:  Int = 0,
    sixes:  Int = 0
  )

  /**
    * Given a roll, produces the sum of each die value as a statistic summary.
    *
    * @param roll The roll over which to be computed.
    * @return The sum of each die value in the roll.
    */
  def sumByDie(roll: Roll): RollStatistics = {
    //TODO add memoization here
    roll.dice.map(_.die.value).foldLeft(RollStatistics()) { (accum, value) =>
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

  /**
    * Given a roll, produces whether that die value exists or does not as a statistic summary; values are always
    * either 0 or 1.
    *
    * @param roll The roll over which to be computed.
    * @return The binary presence of each possible die in a given roll.
    */
  def presenceByDie(roll: Roll): RollStatistics = {
    //TODO add memoization here
    roll.dice.map(_.die.value).foldLeft(RollStatistics()) { (accum, value) =>
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
