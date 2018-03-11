package io.pianka.yahtzee.common

import io.pianka.yahtzee.model.dice.{Die, RolledDice, RolledDie}
import io.pianka.yahtzee.model.score.Zeroed
import io.pianka.yahtzee.model.score.card.ScoreCard
import io.pianka.yahtzee.model.score.lower.LowerSection
import io.pianka.yahtzee.model.score.upper.UpperSection

import scala.util.Random

object TestHelpers {

  def createRoll(values: Int*): RolledDice = {
    RolledDice(values.map(x => RolledDie(Die(x), kept = false)))
  }

  def createFullRollByValue(value: Int): RolledDice = {
    createRoll(value, value, value, value, value)
  }

  def createFullyZeroedScoreCard: ScoreCard = {
    val upperSection = UpperSection(
      aces = Zeroed,
      twos = Zeroed,
      threes = Zeroed,
      fours = Zeroed,
      fives = Zeroed,
      sixes = Zeroed
    )

    val lowerSection = LowerSection(
      threeOfAKind = Zeroed,
      fourOfAKind = Zeroed,
      fullHouse = Zeroed,
      smallStraight = Zeroed,
      largeStraight = Zeroed,
      yahtzee = Zeroed,
      chance = Zeroed,
      yahtzeeBonus = Nil
    )

    ScoreCard(upperSection, lowerSection)
  }

  def randomRoll: RolledDice = {
    val random = new Random
    val values = (1 to 5).map(_ => random.nextInt(5) + 1)
    val dice = values.map(Die.apply).map(RolledDie.apply)

    RolledDice(dice)
  }
}
