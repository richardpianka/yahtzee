package io.pianka.yahtzee.logic.decision

import io.pianka.yahtzee.common.score._
import io.pianka.yahtzee.model.score.card.ScoreCard

object Zeroer {

  def zeroPlacement(scoreCard: ScoreCard, placement: Placement): ScoreCard = {
    placement match {
      case u: UpperSectionPlacement => zeroUpperPlacement(scoreCard, u)
      case l: LowerSectionPlacement => zeroLowerPlacement(scoreCard, l)
    }
  }

  //TODO probably some refactoring to do here
  private def zeroUpperPlacement(scoreCard: ScoreCard, placement: UpperSectionPlacement): ScoreCard = {
    import io.pianka.yahtzee.model.score._

    val upperSection = scoreCard.upperSection
    val lowerSection = scoreCard.lowerSection

    val newUpperSection =
      placement match {
        case Aces   => upperSection.copy(aces   = Zeroed)
        case Twos   => upperSection.copy(twos   = Zeroed)
        case Threes => upperSection.copy(threes = Zeroed)
        case Fours  => upperSection.copy(fours  = Zeroed)
        case Fives  => upperSection.copy(fives  = Zeroed)
        case Sixes  => upperSection.copy(sixes  = Zeroed)
      }

    ScoreCard(newUpperSection, lowerSection)
  }

  private def zeroLowerPlacement(scoreCard: ScoreCard, placement: LowerSectionPlacement): ScoreCard = {
    import io.pianka.yahtzee.model.score._

    val upperSection = scoreCard.upperSection
    val lowerSection = scoreCard.lowerSection

    val newLowerSection =
      placement match {
        case ThreeOfAKind   => lowerSection.copy(threeOfAKind   = Zeroed)
        case FourOfAKind    => lowerSection.copy(fourOfAKind    = Zeroed)
        case FullHouse      => lowerSection.copy(fullHouse      = Zeroed)
        case SmallStraight  => lowerSection.copy(smallStraight  = Zeroed)
        case LargeStraight  => lowerSection.copy(largeStraight  = Zeroed)
        case Yahtzee        => lowerSection.copy(yahtzee        = Zeroed)
        case Chance         => lowerSection.copy(chance         = Zeroed)
      }

    ScoreCard(upperSection, newLowerSection)
  }
}
