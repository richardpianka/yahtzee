package io.pianka.yahtzee.common

import io.pianka.yahtzee.model.dice.{Die, RolledDice, RolledDie}

import scala.util.Random

object TestHelpers {

  def createRoll(values: Int*): RolledDice = {
    RolledDice(values.map(x => RolledDie(Die(x), kept = false)))
  }

  def createFullRollByValue(value: Int): RolledDice = {
    createRoll(value, value, value, value, value)
  }

  def randomRoll: RolledDice = {
    val random = new Random
    val values = (1 to 5).map(_ => random.nextInt(5) + 1)
    val dice = values.map(Die.apply).map(RolledDie.apply)

    RolledDice(dice)
  }
}
