package io.pianka.yahtzee.model.score

/**
  * A score state entered into a score card.
  */
sealed trait Score

/**
  * An as yet unzeroed or unplaced score on a score card.
  */
case object Empty extends Score

/**
  * A player having chosen to place a score on the score card.
  *
  * @param value The score value placed, based on the rules of the roll.
  */
case class Placed(value: Int) extends Score

/**
  * A player having chosen to zero out a score category.
  */
object Zeroed extends Placed(value = 0)