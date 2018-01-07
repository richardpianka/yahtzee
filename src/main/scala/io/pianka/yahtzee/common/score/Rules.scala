package io.pianka.yahtzee.common.score

/**
  * The standard scoring card model rules.  Global variables aren't ideal, but they're immutable, and, similar to pi,
  * aren't changing any time soon.
  */
object Rules {

  /* upper card section */
  val bonusThreshold = 63
  val bonusValue = 35

  /* lower card section */
  val fullHouseScore = 25
  val smallStraightScore = 30
  val largeStraightScore = 40
  val yahtzeeScore = 50
  val yahtzeeBonus = 100
}
