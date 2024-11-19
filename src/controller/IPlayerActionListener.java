package controller;

import model.Card;

/**
 * This interface represents a listener for player actions in the view.
 * It provides methods that are called when the player performs certain actions,
 * such as selecting a card or selecting a grid cell.
 */
public interface IPlayerActionListener {

  /**
   * Called when the player selects a card from their hand.
   *
   * @param card the card that was selected
   */
  void onCardSelected(Card card);

  /**
   * Called when the player selects a cell on the grid.
   *
   * @param row the row index of the selected cell
   * @param col the column index of the selected cell
   */
  void onGridCellSelected(int row, int col);
}
