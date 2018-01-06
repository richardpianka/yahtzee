package io.pianka.yahtzee.logic.tally

import io.pianka.yahtzee.common.exception.IllegalRuleViolationException
import org.scalatest.{FlatSpec, Matchers}
import io.pianka.yahtzee.model.score.{Empty, Placed, Zeroed}
import io.pianka.yahtzee.model.score.lower.{LowerSection, LowerSectionTotal}

class LowerSectionTallierTest extends FlatSpec with Matchers {

  // Positive
  "Fully zeroed out lower section" should "tally to zero" in {
    val section =
      LowerSection(
        threeOfAKind  = Zeroed,
        fourOfAKind   = Zeroed,
        fullHouse     = Zeroed,
        smallStraight = Zeroed,
        largeStraight = Zeroed,
        yahtzee       = Zeroed,
        chance        = Zeroed,
        yahtzeeBonus  = Nil
      )

    val total =
      LowerSectionTotal(section, 0, 0, 0)

    LowerSectionTallier.tally(section) should be (total)
  }

  "Partially placed, partially zeroed lower section" should "tally correctly" in {
    val section =
      LowerSection(
        threeOfAKind  = Placed(5),  // all aces
        fourOfAKind   = Placed(10), // all twos
        fullHouse     = Zeroed,     // missed the full house
        smallStraight = Placed(30), // ace through four
        largeStraight = Placed(40), // two through six
        yahtzee       = Zeroed,     // missed the yahtzee
        chance        = Placed(5),  // all aces
        yahtzeeBonus  = Nil         // no bonus yahtzees
      )

    val total =
      LowerSectionTotal(section, 90, 0, 90)

    LowerSectionTallier.tally(section) should be (total)
  }

  "Simple full lower section" should "tally to subtotal without bonus" in {
    val section =
      LowerSection(
        threeOfAKind  = Placed(5),  // all aces
        fourOfAKind   = Placed(10), // all twos
        fullHouse     = Placed(25), // threes full of twos
        smallStraight = Placed(30), // ace through four
        largeStraight = Placed(40), // two through six
        yahtzee       = Placed(50), // any first yahtzee
        chance        = Placed(5),  // all aces
        yahtzeeBonus  = Nil         // no bonus yahtzees
      )

    val total =
      LowerSectionTotal(section, 165, 0, 165)

    LowerSectionTallier.tally(section) should be (total)
  }

  "Full lower section with two bonus yahtzees" should "tally to subtotal including bonus" in {
    val section =
      LowerSection(
        threeOfAKind  = Placed(5),  // all aces
        fourOfAKind   = Placed(10), // all twos
        fullHouse     = Placed(25), // threes full of twos
        smallStraight = Placed(30), // ace through four
        largeStraight = Placed(40), // two through six
        yahtzee       = Placed(50), // any first yahtzee
        chance        = Placed(5),  // all aces
        yahtzeeBonus  =             // two additional yahtzees
          Seq(
            Placed(100),
            Placed(100)
          )
      )

    val total =
      LowerSectionTotal(section, 165, 200, 365)

    LowerSectionTallier.tally(section) should be (total)
  }

  // Negative
  "Fully empty lower section" should "throw an IllegalRuleViolationException" in {
    an [IllegalRuleViolationException] should be thrownBy {
      val section =
        LowerSection(
          threeOfAKind  = Empty,
          fourOfAKind   = Empty,
          fullHouse     = Empty,
          smallStraight = Empty,
          largeStraight = Empty,
          yahtzee       = Empty,
          chance        = Empty,
          yahtzeeBonus  = Nil
        )

      LowerSectionTallier.tally(section)
    }
  }

  "The default blank lower section" should "throw an IllegalRuleViolationException" in {
    an [IllegalRuleViolationException] should be thrownBy {
      val section = LowerSection.blank
      LowerSectionTallier.tally(section)
    }
  }

  "Even one empty score in a lower section" should "throw an IllegalRuleViolationException" in {
    an [IllegalRuleViolationException] should be thrownBy {
      val section =
        LowerSection(
          threeOfAKind  = Placed(5),  // all aces
          fourOfAKind   = Placed(10), // all twos
          fullHouse     = Placed(25), // threes full of twos
          smallStraight = Placed(30), // ace through four
          largeStraight = Placed(40), // two through six
          yahtzee       = Placed(50), // any first yahtzee
          chance        = Empty,      // unfinished roll & placement
          yahtzeeBonus  =             // two additional yahtzees
            Seq(
              Placed(100),
              Placed(100)
            )
        )

      LowerSectionTallier.tally(section)
    }
  }
}
