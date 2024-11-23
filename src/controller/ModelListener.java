package controller;


import player.IPlayer;


/**
 * This interface represents a listener for model status changes.
 * It provides methods that are called when certain events occur in the model,
 * such as turn changes or the game ending.
 */
public interface ModelListener {

  /**
   * Called when the active player changes.
   * This method refreshes both views when the turn changes,
   * keeping both views updated to the current game state.
   *
   * @param currentPlayer the player whose turn it now is
   */
  void onTurnChanged(IPlayer currentPlayer);
}
