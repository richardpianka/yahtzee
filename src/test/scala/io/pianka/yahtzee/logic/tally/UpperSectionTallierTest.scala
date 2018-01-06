package io.pianka.yahtzee.logic.tally

import io.pianka.yahtzee.common.exception.IllegalRuleViolationException
import io.pianka.yahtzee.model.score.lower.LowerSectionTotal
import io.pianka.yahtzee.model.score.{Empty, Placed, Zeroed}
import io.pianka.yahtzee.model.score.upper.{UpperSection, UpperSectionTotal}
import org.scalatest.{FlatSpec, Matchers}

class UpperSectionTallierTest extends FlatSpec with Matchers {

  // Positive
  "Fully zeroed out upper section" should "tally to zero" in {
    val section =
      UpperSection(
        aces    = Zeroed,
        twos    = Zeroed,
        threes  = Zeroed,
        fours   = Zeroed,
        fives   = Zeroed,
        sixes   = Zeroed
      )

    val total =
      UpperSectionTotal(section, 0, 0, 0)

    UpperSectionTallier.tally(section) should be (total)
  }

  "Partially placed, partially zeroed upper section" should "tally correctly" in {
    val section =
      UpperSection(
        aces    = Zeroed,
        twos    = Placed(4),
        threes  = Placed(3),
        fours   = Zeroed,
        fives   = Placed(15),
        sixes   = Zeroed
      )

    val total =
      UpperSectionTotal(section, 22, 0, 22)

    UpperSectionTallier.tally(section) should be (total)
  }

  "Simple single full upper section" should "tally to subtotal without bonus" in {
    val section =
      UpperSection(
        aces    = Placed(1),
        twos    = Placed(2),
        threes  = Placed(3),
        fours   = Placed(4),
        fives   = Placed(5),
        sixes   = Placed(6)
      )

    val total =
      UpperSectionTotal(section, 21, 0, 21)

    UpperSectionTallier.tally(section) should be (total)
  }

  "Full section with three of each die" should "tally to subtotal with bonus" in {
    val section =
      UpperSection(
        aces    = Placed(1 * 3),
        twos    = Placed(2 * 3),
        threes  = Placed(3 * 3),
        fours   = Placed(4 * 3),
        fives   = Placed(5 * 3),
        sixes   = Placed(6 * 3)
      )

    val total =
      UpperSectionTotal(section, 63, 35, 98)

    UpperSectionTallier.tally(section) should be (total)
  }

  // Negative
  "Fully empty upper section" should "throw an IllegalRuleViolationException" in {
    an [IllegalRuleViolationException] should be thrownBy {
      val section =
        UpperSection(
          aces    = Empty,
          twos    = Empty,
          threes  = Empty,
          fours   = Empty,
          fives   = Empty,
          sixes   = Empty
        )

      UpperSectionTallier.tally(section)
    }
  }

  "The default blank lower section" should "throw an IllegalRuleViolationException" in {
    an [IllegalRuleViolationException] should be thrownBy {
      val section = UpperSection.blank
      UpperSectionTallier.tally(section)
    }
  }

  "Even one empty score in an upper section" should "throw an IllegalRuleViolationException" in {
    an [IllegalRuleViolationException] should be thrownBy {
      val section =
        UpperSection(
          aces    = Placed(1),
          twos    = Placed(2),
          threes  = Placed(3),
          fours   = Placed(4),
          fives   = Placed(5),
          sixes   = Empty
        )

      UpperSectionTallier.tally(section)
    }
  }
}
