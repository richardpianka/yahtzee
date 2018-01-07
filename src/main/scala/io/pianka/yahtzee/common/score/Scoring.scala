package io.pianka.yahtzee.common.score

import io.pianka.yahtzee.model.dice.{Die, RolledDice}

object Scoring {

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
    * @param roll The roll containing the dice over which to be summed.
    * @return The sum of each die value in the roll.
    */
  def summaryStatisticsByRoll(roll: RolledDice): RollStatistics = {
    summaryStatisticsByDice(roll.dice.map(_.die))
  }

  /**
    * Given some dice, produces the sum of each die value as a statistic summary.
    *
    * @param dice The dice over which to be summed.
    * @return The sum of each die value in the dice.
    */
  def summaryStatisticsByDice(dice: Seq[Die]): RollStatistics = {
    //TODO add memoization here
    dice.map(_.value).foldLeft(RollStatistics()) { (accumulator, value) =>
      value match {
        case 1 => accumulator.copy(aces   = accumulator.aces   + 1)
        case 2 => accumulator.copy(twos   = accumulator.twos   + 1)
        case 3 => accumulator.copy(threes = accumulator.threes + 1)
        case 4 => accumulator.copy(fours  = accumulator.fours  + 1)
        case 5 => accumulator.copy(fives  = accumulator.fives  + 1)
        case 6 => accumulator.copy(sixes  = accumulator.sixes  + 1)
        //TODO this is useful for another test, but also needs a negative test
        case _ => accumulator
      }
    }
  }

  /**
    * Simply sums the value of dice in a roll.
    *
    * @param roll The roll whose dice are to be summed.
    * @return The sum of all the dice in the roll.
    */
  def sumRoll(roll: RolledDice): Int = {
    roll.dice.map(_.die.value).sum
  }

  /**
    * Given a roll, produces whether that die value exists or does not as a statistic summary; values are always
    * either 0 or 1.
    *
    * @param roll The roll over which to be computed.
    * @return The binary presence of each possible die in a given roll.
    */
  def presenceByDie(roll: RolledDice): RollStatistics = {
    //TODO add memoization here
    roll.dice.map(_.die.value).foldLeft(RollStatistics()) { (accumulator, value) =>
      value match {
        case 1 => accumulator.copy(aces   = Math.max(accumulator.aces,   1))
        case 2 => accumulator.copy(twos   = Math.max(accumulator.twos,   1))
        case 3 => accumulator.copy(threes = Math.max(accumulator.threes, 1))
        case 4 => accumulator.copy(fours  = Math.max(accumulator.fours,  1))
        case 5 => accumulator.copy(fives  = Math.max(accumulator.fives,  1))
        case 6 => accumulator.copy(sixes  = Math.max(accumulator.sixes,  1))
      }
    }
  }
}
