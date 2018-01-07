package io.pianka.yahtzee.model.dice

import org.scalatest.{FlatSpec, Matchers}

class RollTest extends FlatSpec with Matchers {

  /* Positive */
  "Any five dice" should "be a valid roll" in {
    val roll = RolledDice(
      List(
        RolledDie(Die(1), kept = false),
        RolledDie(Die(2), kept = false),
        RolledDie(Die(3), kept = false),
        RolledDie(Die(4), kept = false),
        RolledDie(Die(5), kept = false)
      )
    )

    roll.dice.length should be (5)
  }

  /* Negative */
  "Fewer than five dice" should "throw an IllegalArgumentException" in {
    an [IllegalArgumentException] should be thrownBy {
      RolledDice(
        List(
          RolledDie(Die(1), kept = false),
          RolledDie(Die(2), kept = false),
          RolledDie(Die(3), kept = false),
          RolledDie(Die(4), kept = false)
        )
      )
    }
  }

  "More than five dice" should "throw an IllegalArgumentException" in {
    an [IllegalArgumentException] should be thrownBy {
      RolledDice(
        List(
          RolledDie(Die(1), kept = false),
          RolledDie(Die(2), kept = false),
          RolledDie(Die(3), kept = false),
          RolledDie(Die(4), kept = false),
          RolledDie(Die(5), kept = false),
          RolledDie(Die(6), kept = false),
        )
      )
    }
  }
}
