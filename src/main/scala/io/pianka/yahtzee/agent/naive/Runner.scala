package io.pianka.yahtzee.agent.naive

import io.pianka.yahtzee.game.Game
import io.pianka.yahtzee.game.player.Player

object Runner extends App {

  val player1 = new Player(new NaiveAgent("Player1"))
  val player2 = new Player(new NaiveAgent("Player2"))

  val game = new Game(Seq(player1, player2))
  game.run()
}
