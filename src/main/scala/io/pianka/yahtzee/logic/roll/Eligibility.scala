package io.pianka.yahtzee.logic.roll

import io.pianka.yahtzee.model.score.Empty
import io.pianka.yahtzee.model.score.card.ScoreCard
import io.pianka.yahtzee.model.score.lower.LowerSection
import io.pianka.yahtzee.model.score.upper.UpperSection

object Eligibility {

  def eligibleForRolling(scoreCard: ScoreCard): Boolean = {
    upperSectionEligibility(scoreCard.upperSection) ||
    lowerSectionEligibility(scoreCard.lowerSection)
  }

  private def upperSectionEligibility(upperSection: UpperSection): Boolean = {
    upperSection.aces   == Empty ||
    upperSection.twos   == Empty ||
    upperSection.threes == Empty ||
    upperSection.fours  == Empty ||
    upperSection.fives  == Empty ||
    upperSection.sixes  == Empty
  }

  private def lowerSectionEligibility(lowerSection: LowerSection): Boolean = {
    lowerSection.threeOfAKind   == Empty ||
    lowerSection.fourOfAKind    == Empty ||
    lowerSection.fullHouse      == Empty ||
    lowerSection.smallStraight  == Empty ||
    lowerSection.largeStraight  == Empty ||
    lowerSection.yahtzee        == Empty ||
    lowerSection.chance         == Empty
  }
}