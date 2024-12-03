package cs3500.threetrios.provider.model;

import model.ICard;

/**
 * One GridCell represents single cell on the board.
 * A cell can be a hole. If a card is a hole, it should not be able
 * to take in a card. If it is not a hole, it can take in one card.
 */
public interface IGridCell {
  /**
   * Checks if the cell is a hole.
   *
   * @return returns true if cell is a hole and false if not
   */
  boolean isHole();

  /**
   * Gets the card in the cell.
   *
   * @return returns the card object as specified
   */
  model.ICard getCard();

  /**
   * Sets the card in the cell.
   *
   * @param card the card to be placed in the cell
   */
  void setCard(ICard card);
}
