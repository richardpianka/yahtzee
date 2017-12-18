package io.pianka.yahtzee.logic.score

import io.pianka.yahtzee.model.dice.{Die, Roll, RolledDie}
import org.scalatest.{FlatSpec, Matchers}

class CommonScorerTest extends FlatSpec with Matchers {

  /* Positive */
  "A roll" should "sum to the values of its dice" in {
    val roll = Roll(
      List(
        RolledDie(Die(1), kept = false),
        RolledDie(Die(2), kept = false),
        RolledDie(Die(3), kept = false),
        RolledDie(Die(4), kept = false),
        RolledDie(Die(5), kept = false)
      )
    )

    CommonScorer.sum(roll) should be (15)
  }
}
