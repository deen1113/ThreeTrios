package view;

import model.ICard;
import model.PlayerColor;

/**
 * This interface represents the view for the ThreeTrios game.
 * The view has registers three panels, one for each hand and
 * one for the grid.
 * It can handle mouse clicks for clicking cards and placing them on the grid.
 * It can also display any exceptions and show the winner when the game is finished.
 */
public interface IThreeTriosJSwingView extends IView {
  /**
   * Gets the clicked CardView for either player and returns the index
   * for the card in the player's hand.
   *
   * @return the clicked card's index
   */
  int getSelectedCardIndex();

  /**
   * Gets the clicked CardView for either player and returns
   * that selected card.
   *
   * @return the selected CardView
   */
  ICardView getSelectedCardView();

  /**
   * When game is over, winner and points are displayed.
   */
  void displayGameWinner();

  /**
   * When an exception is thrown, given message is displayed.
   *
   * @param e thrown exception
   */
  void displayException(Exception e);

  /**
   * Returns true if hints are enabled and false if not.
   * Checks from the GridViewWithHintsDecorator.
   *
   * @return true or false
   */
  boolean isHintsEnabled();

  /**
   * Updates hints of the selected card in the grid decorator.
   *
   * @param selectedCard selected card to get hints for
   */
  void updateHints(ICard selectedCard);

  /**
   * If given color is RED, toggles hints for red.
   * If given color is BLUE, toggles hints for blue.
   *
   * @param playerColor given player color
   * @param enable either true for hints on, or false for hints off
   */
  void toggleHints(PlayerColor playerColor, boolean enable);
}
