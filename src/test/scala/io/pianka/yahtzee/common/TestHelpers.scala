package io.pianka.yahtzee.common

import io.pianka.yahtzee.model.dice.{Die, Roll, RolledDie}

object TestHelpers {

  def createRoll(values: Int*): Roll = {
    Roll(values.map(x => RolledDie(Die(x), kept = false)))
  }
}
