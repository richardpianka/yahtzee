package io.pianka.yahtzee.logic.score

import io.pianka.yahtzee.logic.check.LowerSectionChecker
import io.pianka.yahtzee.model.dice.Roll

object LowerSectionScorer {

  def scoreThreeOfAKind(roll: Roll): Int = {
    require(LowerSectionChecker.threeOfAKind(roll))
    CommonScorer.sum(roll)
  }

  def scoreFourOfAKind(roll: Roll): Int = {
    require(LowerSectionChecker.fourOfAKind(roll))
    CommonScorer.sum(roll)
  }
}
