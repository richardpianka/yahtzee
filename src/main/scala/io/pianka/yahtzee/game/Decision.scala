package io.pianka.yahtzee.game

import io.pianka.yahtzee.common.score.Placement
import io.pianka.yahtzee.model.dice.Die

/* move to next roll */
sealed trait Decision

case object RerollAll                       extends Decision
case class KeepAndReroll(keepers: Seq[Die]) extends Decision

/* pencil something in */
sealed trait FinalDecision                  extends Decision

case class ZeroOut(placement: Placement)    extends FinalDecision
case class PlaceScore(placement: Placement) extends FinalDecision