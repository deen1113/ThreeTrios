package controller;

import model.ICard;

/**
 * This interface represents a listener for player actions in the view.
 * It provides methods that are called when the player performs certain actions,
 * such as selecting a card or selecting a grid cell.
 */
public interface IPlayerActions {

  /**
   * Called when the player clicks a card from their hand.
   * Takes the index of the clicked card and saves it.
   *
   * @param index index of the clicked card
   */
  void onCardSelected(int index);

  /**
   * Called when the player selects a cell on the grid.
   *
   * @param row the row index of the selected cell
   * @param col the column index of the selected cell
   */
  void onGridCellSelected(int row, int col);
}
