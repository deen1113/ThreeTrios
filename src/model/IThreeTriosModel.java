package model;

import controller.ModelListener;

/**
 * Interface for the ThreeTriosModel class.
 * This interface only has the mutable methods.
 */
public interface IThreeTriosModel extends IReadonlyThreeTriosModel {
  /**
   * Starts the game by checking exceptions & initializing the hands, shuffling deck.
   * And making the grid.
   *
   * @throws IllegalArgumentException if the deck size is not even or the grid size is not odd
   */
  void startGame();

  /**
   * Places a card of the player's color onto a valid cell on the grid.
   * Cards cannot be placed on holes, which vary depending on the grid.
   * Cards can only be played to grid during placing state.
   *
   * @param row       row index of grid cell to be placed
   * @param col       column index of grid cell to be placed
   * @param handIndex index of card in hand
   * @param color     color of player whose turn it is
   */
  void placeCard(int row, int col, int handIndex, PlayerColor color);

  /**
   * Battle happens after a player places a card on the grid.
   * The card does battle with all adjacent cards that belong to the other player.
   * Cards do battle by comparing values of the directions that face each other,
   * If the placed card wins the battle, the player wins the card they beat,
   * and this new card now does battle with all of its adjacent cards.
   * If the other player's card wins the battle, both cards stay the same.
   *
   * @param row   row index of card placed
   * @param col   column index of card placed
   */
  void battle(int row, int col);

  /**
   * Updates current player. If the current player is RED, changes to BLUE.
   * If current player is BLUE, changes to RED.
   */
  void updateCurrentPlayer();

  /**
   * Registers a listener to the model in order to access notifications.
   *
   * @param listener the controller
   */
  void setListener(ModelListener listener);
}
