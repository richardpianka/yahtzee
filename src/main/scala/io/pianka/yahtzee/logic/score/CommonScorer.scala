package io.pianka.yahtzee.logic.score

import io.pianka.yahtzee.model.dice.Roll

object CommonScorer {

  /**
    * Simply sums the value of dice in a roll.
    *
    * @param roll The roll whose dice are to be summed.
    * @return The sum of all the dice in the roll.
    */
  def sum(roll: Roll): Int = {
    roll.dice.map(_.die.value).sum
  }
}
