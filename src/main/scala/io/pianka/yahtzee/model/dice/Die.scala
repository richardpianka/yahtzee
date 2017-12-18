package io.pianka.yahtzee.model.dice

/**
  * One six-sided die.
  *
  * @param value The integer value of the die.
  */
class Die(val value: Int) extends AnyVal

object Die {

  /* you cannot put require() into the constructor of an AnyVal; silly work around */
  //https://stackoverflow.com/questions/22816497/is-it-possible-to-create-value-classes-with-invariants
  //https://stackoverflow.com/questions/9169691/how-to-check-constructor-arguments-and-throw-an-exception-or-make-an-assertion-i
  def apply(value: Int): Die = {
    require(value >= 1)
    require(value <= 6)

    new Die(value)
  }
}