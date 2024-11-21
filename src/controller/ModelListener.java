package controller;


import model.Player;

/**
 * This interface represents a listener for model status changes.
 * It provides methods that are called when certain events occur in the model,
 * such as turn changes or the game ending.
 */
public interface ModelListener {

  /**
   * Called when the active player changes.
   *
   * @param currentPlayer the player whose turn it now is
   */
  void onTurnChanged(Player currentPlayer);

  /**
   * Called when the game is over.
   *
   * @param winner the player who won the game (null if it's a draw)
   */
  void onGameOver(Player winner);

  /**
   * Called when an invalid move is attempted.
   *
   * @param errorMessage a message describing why the move was invalid
   */
  void onInvalidMove(String errorMessage);
}
