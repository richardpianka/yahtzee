package io.pianka.yahtzee.common.score

import io.pianka.yahtzee.model.dice.{Die, RolledDice, RolledDie}
import org.scalatest.{FlatSpec, Matchers}

class ScoringTest extends FlatSpec with Matchers {

  /* Positive */
  "A roll" should "sum to the values of its dice" in {
    val roll = RolledDice(
      List(
        RolledDie(Die(1), kept = false),
        RolledDie(Die(2), kept = false),
        RolledDie(Die(3), kept = false),
        RolledDie(Die(4), kept = false),
        RolledDie(Die(5), kept = false)
      )
    )

    Scoring.sumRoll(roll) should be (15)
  }
}
