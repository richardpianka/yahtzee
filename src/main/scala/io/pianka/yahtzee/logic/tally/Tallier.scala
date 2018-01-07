package io.pianka.yahtzee.logic.tally

import io.pianka.yahtzee.common.exception.IllegalRuleViolationException
import io.pianka.yahtzee.model.score.{Empty, Placed, Score, Zeroed}

/**
  * Tallies the overall score for a section of a card.
  */
trait Tallier {

  /**
    * Produces a numerical value from a score.
    *
    * @param score The score.
    * @return The numerical value.
    */
  def value(score: Score): Int = {
    score match {
      case Zeroed => 0
      case p: Placed => p.value
      case Empty => throw IllegalRuleViolationException("Tallying an unfinished card is against the rules.")
    }
  }
}
