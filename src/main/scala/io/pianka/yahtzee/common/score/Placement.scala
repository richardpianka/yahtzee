package io.pianka.yahtzee.common.score

sealed trait Placement
sealed trait UpperSectionPlacement extends Placement
sealed trait LowerSectionPlacement extends Placement

/* upper section */
case object Aces          extends UpperSectionPlacement
case object Twos          extends UpperSectionPlacement
case object Threes        extends UpperSectionPlacement
case object Fours         extends UpperSectionPlacement
case object Fives         extends UpperSectionPlacement
case object Sixes         extends UpperSectionPlacement

/* lower secton */
case object ThreeOfAKind  extends LowerSectionPlacement
case object FourOfAKind   extends LowerSectionPlacement
case object FullHouse     extends LowerSectionPlacement
case object SmallStraight extends LowerSectionPlacement
case object LargeStraight extends LowerSectionPlacement
case object Yahtzee       extends LowerSectionPlacement
case object Chance        extends LowerSectionPlacement