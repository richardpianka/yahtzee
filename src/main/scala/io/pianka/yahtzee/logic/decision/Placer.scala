package io.pianka.yahtzee.logic.decision

import io.pianka.yahtzee.common.score._
import io.pianka.yahtzee.logic.score.{LowerSectionScorer, UpperSectionScorer}
import io.pianka.yahtzee.model.dice.RolledDice
import io.pianka.yahtzee.model.score.card.ScoreCard

object Placer {

  def scorePlacement(scoreCard: ScoreCard, placement: Placement, rolledDice: RolledDice): ScoreCard = {
    placement match {
      case u: UpperSectionPlacement => scoreUpperPlacement(scoreCard, u, rolledDice)
      case l: LowerSectionPlacement => scoreLowerPlacement(scoreCard, l, rolledDice)
    }
  }

  //TODO probably some refactoring to do here
  private def scoreUpperPlacement(scoreCard: ScoreCard, placement: UpperSectionPlacement, rolledDice: RolledDice): ScoreCard = {
    import io.pianka.yahtzee.model.score._

    val upperSection = scoreCard.upperSection
    val lowerSection = scoreCard.lowerSection

    //TODO lots of copy paste here
    val newUpperSection =
      placement match {
        case Aces   => upperSection.copy(aces   = Placed(UpperSectionScorer.scoreRollByValue(rolledDice, 1)))
        case Twos   => upperSection.copy(twos   = Placed(UpperSectionScorer.scoreRollByValue(rolledDice, 2)))
        case Threes => upperSection.copy(threes = Placed(UpperSectionScorer.scoreRollByValue(rolledDice, 3)))
        case Fours  => upperSection.copy(fours  = Placed(UpperSectionScorer.scoreRollByValue(rolledDice, 4)))
        case Fives  => upperSection.copy(fives  = Placed(UpperSectionScorer.scoreRollByValue(rolledDice, 5)))
        case Sixes  => upperSection.copy(sixes  = Placed(UpperSectionScorer.scoreRollByValue(rolledDice, 6)))
      }

    ScoreCard(newUpperSection, lowerSection)
  }

  private def scoreLowerPlacement(scoreCard: ScoreCard, placement: LowerSectionPlacement, rolledDice: RolledDice): ScoreCard = {
    import io.pianka.yahtzee.model.score._

    val upperSection = scoreCard.upperSection
    val lowerSection = scoreCard.lowerSection

    //TODO lots of copy paste here
    val newLowerSection =
      placement match {
        case ThreeOfAKind   => lowerSection.copy(threeOfAKind   = Placed(LowerSectionScorer.scoreThreeOfAKind(rolledDice)))
        case FourOfAKind    => lowerSection.copy(fourOfAKind    = Placed(LowerSectionScorer.scoreFourOfAKind(rolledDice)))
        case FullHouse      => lowerSection.copy(fullHouse      = Placed(LowerSectionScorer.scoreFullHouse(rolledDice)))
        case SmallStraight  => lowerSection.copy(smallStraight  = Placed(LowerSectionScorer.scoreSmallStraight(rolledDice)))
        case LargeStraight  => lowerSection.copy(largeStraight  = Placed(LowerSectionScorer.scoreLargeStraight(rolledDice)))
        //TODO yahtzee bonus scoring
        case Yahtzee        => lowerSection.copy(yahtzee        = Placed(LowerSectionScorer.scoreYahtzee(rolledDice)))
        case Chance         => lowerSection.copy(chance         = Placed(LowerSectionScorer.scoreChance(rolledDice)))
      }

    ScoreCard(upperSection, newLowerSection)
  }
}
