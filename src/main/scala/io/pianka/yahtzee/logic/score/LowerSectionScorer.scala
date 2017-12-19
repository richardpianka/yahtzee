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

  private val fullHouseScore = 25
  def scoreFullHouse(roll: Roll): Int = {
    require(LowerSectionChecker.fullHouse(roll))
    fullHouseScore
  }

  private val smallStraightScore = 30
  def scoreSmallStraight(roll: Roll): Int = {
    require(LowerSectionChecker.smallStraight(roll))
    smallStraightScore
  }

  private val largeStraightScore = 40
  def scoreLargeStraight(roll: Roll): Int = {
    require(LowerSectionChecker.largeStraight(roll))
    largeStraightScore
  }

  private val yahtzeeScore = 50
  private val yahtzeeBonus = 100
  def scoreYahtzee(roll: Roll, first: Boolean = true): Int = {
    require(LowerSectionChecker.yahtzee(roll))

    if (first) yahtzeeScore else yahtzeeBonus
  }

  def scoreChance(roll: Roll): Int = {
    CommonScorer.sum(roll)
  }
}
