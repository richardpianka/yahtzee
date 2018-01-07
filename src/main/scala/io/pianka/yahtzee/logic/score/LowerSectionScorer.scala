package io.pianka.yahtzee.logic.score

import io.pianka.yahtzee.common.score.Scoring
import io.pianka.yahtzee.logic.check.LowerSectionChecker
import io.pianka.yahtzee.model.dice.RolledDice

object LowerSectionScorer {

  def scoreThreeOfAKind(roll: RolledDice): Int = {
    require(LowerSectionChecker.threeOfAKind(roll))
    Scoring.sumRoll(roll)
  }

  def scoreFourOfAKind(roll: RolledDice): Int = {
    require(LowerSectionChecker.fourOfAKind(roll))
    Scoring.sumRoll(roll)
  }

  private val fullHouseScore = 25
  def scoreFullHouse(roll: RolledDice): Int = {
    require(LowerSectionChecker.fullHouse(roll))
    fullHouseScore
  }

  private val smallStraightScore = 30
  def scoreSmallStraight(roll: RolledDice): Int = {
    require(LowerSectionChecker.smallStraight(roll))
    smallStraightScore
  }

  private val largeStraightScore = 40
  def scoreLargeStraight(roll: RolledDice): Int = {
    require(LowerSectionChecker.largeStraight(roll))
    largeStraightScore
  }

  private val yahtzeeScore = 50
  private val yahtzeeBonus = 100
  def scoreYahtzee(roll: RolledDice, first: Boolean = true): Int = {
    require(LowerSectionChecker.yahtzee(roll))

    if (first) yahtzeeScore else yahtzeeBonus
  }

  def scoreChance(roll: RolledDice): Int = {
    Scoring.sumRoll(roll)
  }
}
