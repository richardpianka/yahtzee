package io.pianka.yahtzee.logic.dice

import io.pianka.yahtzee.common.exception.IllegalRuleViolationException
import io.pianka.yahtzee.logic.score.LowerSectionScorer
import io.pianka.yahtzee.common.TestHelpers._
import io.pianka.yahtzee.model.dice.Die
import org.scalatest.{FlatSpec, Matchers}

class RollerTest extends FlatSpec with Matchers {

  // Positive
  "Single rolled die" should "be a die within range" in {
    val die = Roller.rollDie
    val value = die.value

    value should be >= 1
    value should be <= 6
  }

  "Throwing a fresh roll" should "create a valid RolledDice" in {
    // create a random roll and score it by chance, nothing should go wrong
    val roll = Roller.rollAll
    LowerSectionScorer.scoreChance(roll)
  }

  "Fully rerolling an existing roll" should "have no problem" in {
    val roll = Roller.rollAll // first roll
    val fullReroll = Roller.keepAndRoll(roll, Nil) // second roll
    fullReroll.dice.length shouldBe 5
  }

  "Rerolling and keeping one die" should "include that die" in {
    val roll = Roller.rollAll
    val keeper = roll.dice.head.die

    val secondRoll = Roller.keepAndRoll(roll, Seq(keeper))
    secondRoll.dice.map(_.die).contains(keeper)
  }

  "Rerolling and keeping four dice" should "include those four dice" in {
    // roll, grab the first four, keep them, reroll once
    val roll = Roller.rollAll
    val keepers = roll.dice.take(4).map(_.die)
    val secondRoll = Roller.keepAndRoll(roll, keepers)

    val kept = keepers.forall { die =>
      secondRoll.dice.map(_.die).contains(die)
    }

    kept shouldBe true
  }

  // Negative
  "Keeping dice a player didn't roll" should "throw an Exception" in {
    an [IllegalRuleViolationException] should be thrownBy {
      val roll = createRoll(1, 1, 1, 1, 1)
      val invalidDie = Die(2)

      Roller.keepAndRoll(roll, Seq(invalidDie))
    }
  }

  "Rerolling zero dice" should "not be allowed" in {
    an [IllegalRuleViolationException] should be thrownBy {
      val roll = createRoll(1, 1, 1, 1, 1)
      val sameDice = roll.dice.map(_.die)
      Roller.keepAndRoll(roll, sameDice)
    }
  }
}
