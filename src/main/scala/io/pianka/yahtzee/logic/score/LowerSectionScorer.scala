package io.pianka.yahtzee.logic.score

import io.pianka.yahtzee.common.score.{Rules, Scoring}
import io.pianka.yahtzee.logic.check.LowerSectionChecker
import io.pianka.yahtzee.model.dice.RolledDice

object LowerSectionScorer {

  /**
    * Validates and scores a full roll of dice as three of a kind. (cf. [[LowerSectionChecker]] for rule details)
    *
    * @param roll The roll of dice.
    * @return The resultant score.
    */
  def scoreThreeOfAKind(roll: RolledDice): Int = {
    require(LowerSectionChecker.threeOfAKind(roll))
    Scoring.sumRoll(roll)
  }

  /**
    * Validates and scores a full roll of dice as four of a kind. (cf. [[LowerSectionChecker]] for rule details)
    *
    * @param roll The roll of dice.
    * @return The resultant score.
    */
  def scoreFourOfAKind(roll: RolledDice): Int = {
    require(LowerSectionChecker.fourOfAKind(roll))
    Scoring.sumRoll(roll)
  }

  /**
    * Validates and scores a full roll of dice as a full house. (cf. [[LowerSectionChecker]] for rule details)
    *
    * @param roll The roll of dice.
    * @return The resultant score.
    */
  def scoreFullHouse(roll: RolledDice): Int = {
    require(LowerSectionChecker.fullHouse(roll))
    Rules.fullHouseScore
  }

  /**
    * Validates and scores a full roll of dice as a small straight. (cf. [[LowerSectionChecker]] for rule details)
    *
    * @param roll The roll of dice.
    * @return The resultant score.
    */
  def scoreSmallStraight(roll: RolledDice): Int = {
    require(LowerSectionChecker.smallStraight(roll))
    Rules.smallStraightScore
  }


  /**
    * Validates and scores a full roll of dice as a large straight. (cf. [[LowerSectionChecker]] for rule details)
    *
    * @param roll The roll of dice.
    * @return The resultant score.
    */
  def scoreLargeStraight(roll: RolledDice): Int = {
    require(LowerSectionChecker.largeStraight(roll))
    Rules.largeStraightScore
  }

  /**
    * Validates and scores a full roll of dice as a yahtzee.  Includes the ability to score as the first yahtzee or as
    * one of the bonus yahtzees. (cf. [[LowerSectionChecker]] for rule details)
    *
    * @param roll The roll of dice.
    * @param first Whether this is the first yahtzee or a subsequent yahtzee.
    * @return The resultant score.
    */
  def scoreYahtzee(roll: RolledDice, first: Boolean = true): Int = {
    require(LowerSectionChecker.yahtzee(roll))

    if (first) {
      Rules.yahtzeeScore
    } else {
      Rules.yahtzeeBonus
    }
  }

  /**
    * Scores a full roll of dice by simple sum.  There are no rule details to reference around this beyond it simply
    * being the sum of all dice.  Players typically use this for great point rolls they can't use elsewhere, an easy
    * line to zero out, especially when going for a long shot (e.g., second yahtzee) that they miss.
    *
    * There is no necessary qualification for placing a chance, except a player can only place it once.
    *
    * @param roll The roll of dice.
    * @return The resultant score.
    */
  def scoreChance(roll: RolledDice): Int = {
    Scoring.sumRoll(roll)
  }
}
