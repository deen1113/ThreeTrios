package controller;

import model.ICard;
import player.IPlayer;

/**
 * This interface represents a listener for player actions in the view.
 * It provides methods that are called when the player performs certain actions,
 * such as selecting a card or selecting a grid cell.
 */
public interface IPlayerActions {

  /**
   * Called when the player clicks a card from their hand.
   * Takes the index of the clicked card and saves it.
   */
  void onCardSelected();

  /**
   * Called when the player selects a cell on the grid.
   *
   * @param row the row index of the selected cell
   * @param col the column index of the selected cell
   */
  void onGridCellSelected(int row, int col);

  IPlayer getPlayer();
}
