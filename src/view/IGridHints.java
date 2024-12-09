package view;

import model.ICard;

public interface IGridHints {
  /**
   * Sets hints to either true or false.
   * If true, hints are visible, if false they are not.
   *
   * @param enabled either true or false
   */
  void setHints(boolean enabled);

  /**
   * Returns if hints are enabled or not.
   *
   * @return true if hints are enabled, false if not
   */
  boolean isHintsEnabled();

  /**
   * When card is clicked, updates hints to show the current selected cards
   * available moves.
   *
   * @param selectedCard current selected card
   */
  void updateHints(ICard selectedCard);
}
