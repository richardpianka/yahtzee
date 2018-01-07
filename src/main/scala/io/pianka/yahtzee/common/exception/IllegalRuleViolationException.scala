package io.pianka.yahtzee.common.exception

/**
  * The state of the game has been evaluated against official or chosen rules and has been found invalid.
  *
  * @param message The explanation of the rule violation
  * @param cause The parent exception causing the rule violation
  */
case class IllegalRuleViolationException(
  private val message: String = "",
  private val cause: Throwable = None.orNull
) extends Exception(message, cause)