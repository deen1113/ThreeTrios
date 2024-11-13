package model;

import java.util.List;

/**
 * This interface contains all the observation methods necessary for the
 * ThreeTriosModel implementation.
 */
public interface IReadonlyThreeTriosModel {
  /**
   * Checks if the game is over.
   * The game ends when all empty card cells are filled.
   *
   * @return true if game is over, false if not
   */
  boolean isGameOver();

  /**
   * Checks which player won.
   * A player wins if they have the most "owned" cards.
   * This includes all the cards they own on the grid and the cards
   * they have remaining in their hand.
   */
  PlayerColor determineWinner();

  /**
   * Returns a copy of the Red Player's hand.
   * Altering this list does not affect the list in game.
   *
   * @return a copy of the list redHand
   */
  List<Card> getRedHand();

  /**
   * Returns a copy of the Blue Player's hand.
   * Altering this list does not affect the list in game.
   *
   * @return a copy of the list blueHand
   */
  List<Card> getBlueHand();


  /**
   * Gets the current game state.
   * This could be NOT_STARTED, PLACING, BATTLE, or FINISHED
   *
   * @return the game state
   */
  GameState getGameState();

  /**
   * Returns a copy of the current grid.
   * Changing this grid will have no effect on the game.
   *
   * @return the grid
   */
  Grid getGrid();

  /**
   * Returns whether a move is valid at a cell given the cell's row
   * and column.
   *
   * @param row the cell's row
   * @param col the cell's column
   * @return true if move is valid, and false if not
   */
  boolean isMoveValid(int row, int col);

  /**
   * Returns the color of the placed card at a cell, given the
   * cell's row and column.
   *
   * @param row the cell's row
   * @param col the cell's column
   * @return Either RED or BLUE
   */
  PlayerColor playedCardColor(int row, int col);

  /**
   * Returns the card that occupies the given cell. If there is no
   * card, returns null.
   *
   * @param row the cell's row
   * @param col the cell's column
   * @return Card or null
   */
  Card cellContents(int row, int col);

  /**
   * Returns the Red Player's total score. This includes the amount
   * of red cards on the grid, as well as the amount of cards the
   * player has in hand.
   *
   * @return red's total score
   */
  int getRedScore();

  /**
   * Returns the Blue Player's total score. This includes the amount
   * of blue cards on the grid, as well as the amount of cards the
   * player has in hand.
   *
   * @return blue's total score
   */
  int getBlueScore();

  /**
   * Returns the amount of rows in the grid.
   *
   * @return num of rows in current grid
   */
  int getGridRowAmount();

  /**
   * Returns the amount of columns in the grid.
   *
   * @return num of columns in current grid
   */
  int getGridColAmount();


  /**
   * Returns the total amount of cards flipped with a move.
   *
   * @param card the card to be placed
   * @param row the row to place the card
   * @param col the column to place the card
   * @return the total amount of cards flipped
   */
  int totalFlippedWithMove(Card card, int row, int col);

  /**
   * Returns the current player color.
   *
   * @return either PlayerColor RED or BLUE
   */
  PlayerColor getCurrentPlayer();
}
