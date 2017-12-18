package io.pianka.yahtzee.model.dice

import org.scalatest.{FlatSpec, Matchers}

class RolledDieTest extends FlatSpec with Matchers {

  /* Positive */
  "A rolled die" should "retain its numerical value" in {
    val rolledDie = RolledDie(Die(1), kept = false)
    rolledDie.die.value should be (1)
  }
}
