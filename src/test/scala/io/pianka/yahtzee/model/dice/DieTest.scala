package io.pianka.yahtzee.model.dice

import org.scalatest.{FlatSpec, Matchers}

class DieTest extends FlatSpec with Matchers {

  /* Positive */
  "A die" should "retain its numerical value" in {
    val die = Die(1)
    die.value should be (1)
  }

  /* Negative */
  "A die" should "have a value of one or higher" in {
    an [IllegalArgumentException] should be thrownBy {
      Die(0)
    }
  }

  "A die" should "have a value of six or lower" in {
    an [IllegalArgumentException] should be thrownBy {
      Die(7)
    }
  }
}