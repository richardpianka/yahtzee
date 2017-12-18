package io.pianka.yahtzee.model.dice

/**
  * One of the three rolls by a player during their turn, containing five dice.
  *
  * @param dice The dice in the roll, including those kept from possible previous rolls.
  */
case class Roll(dice: Seq[RolledDie]) {
  require(dice.length == 5)
}