package io.pianka.yahtzee.game

import io.pianka.yahtzee.common.score.Placement
import io.pianka.yahtzee.model.dice.RolledDice

sealed trait Outcome

case class NextRoll(roll: RolledDice)   extends Outcome
case class Zeroed(placement: Placement) extends Outcome
case class Placed(placement: Placement) extends Outcome