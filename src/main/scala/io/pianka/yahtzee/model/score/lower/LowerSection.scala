package io.pianka.yahtzee.model.score.lower

import io.pianka.yahtzee.model.score.{Empty, Score}

/**
  * The lower section of the score card, from particular dice configurations.
  *
  * @param threeOfAKind At least three dice of one value, plus sum of the other dice.
  * @param fourOfAKind At least four dice of one value, plus value of the remaining die.
  * @param fullHouse Three dice of one value, and two dice of another value.
  * @param smallStraight Any four dice of consecutive values.
  * @param largeStraight Any five dice of consecutive values.
  * @param yahtzee Five of a kind, and the first occurrence of this roll.
  * @param chance Used for any arbitrary roll, and is the sum of all dice.
  * @param yahtzeeBonus Additional yahtzees after the first has been attained.
  */
case class LowerSection(
  threeOfAKind:  Score,
  fourOfAKind:   Score,
  fullHouse:     Score,
  smallStraight: Score,
  largeStraight: Score,
  yahtzee:       Score,
  chance:        Score,
  yahtzeeBonus:  Seq[Score]
)

object LowerSection {

  val blank =
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
}