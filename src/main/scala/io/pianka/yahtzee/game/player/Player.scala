package io.pianka.yahtzee.game.player

import io.pianka.yahtzee.model.score.card.ScoreCard

/**
  * Managed by the game.
  *
  * @param agent Decisions managed by agency (e.g., human, robot, etc.)
  */
class Player(val agent: Agent) {

  var scoreCard: ScoreCard = ScoreCard.blank
}
