package io.pianka.yahtzee.model.score.upper

import io.pianka.yahtzee.model.score.{Empty, Score}

/**
  * The upper section of the score card, from pure dice counts.
  *
  * @param aces Scored dice value of ones.
  * @param twos Scored dice value of twos.
  * @param threes Scored dice value of threes.
  * @param fours Scored dice value of fours.
  * @param fives Scored dice value of fives.
  * @param sixes Scored dice values of sixes.
  */
case class UpperSection(
  aces:   Score,
  twos:   Score,
  threes: Score,
  fours:  Score,
  fives:  Score,
  sixes:  Score
)

object UpperSection {

  val blank =
    UpperSection(
      aces   = Empty,
      twos   = Empty,
      threes = Empty,
      fours  = Empty,
      fives  = Empty,
      sixes  = Empty
    )
}