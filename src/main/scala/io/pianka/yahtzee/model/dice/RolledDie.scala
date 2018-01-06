package io.pianka.yahtzee.model.dice

/**
  * A die that has been rolled, either appearing in the most recent, or having been kept in a previous roll.
  *
  * @param die The die itself.
  * @param kept Whether or not the die was kept from a previous roll.
  */
case class RolledDie(die: Die, kept: Boolean)

object RolledDie {

  def apply(die: Die): RolledDie = {
    RolledDie(die, kept = false)
  }
}