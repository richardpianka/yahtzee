package io.pianka.yahtzee.game.player

import io.pianka.yahtzee.game.Decision
import io.pianka.yahtzee.model.dice.{Die, RolledDice}
import io.pianka.yahtzee.model.score.card.ScoreCard

// human
// AI
// network
trait Agent {

  def rolledForOrder(die: Die, otherPlayers: Seq[Die])
  def establishedOrder(order: Int, playerCount: Int)

  def roll(dice: RolledDice, scoreCard: ScoreCard, rollCount: Int): Decision

  //TODO an integer is useful, need win/loss, also need other player order
  def gameOver(order: Int)
}
