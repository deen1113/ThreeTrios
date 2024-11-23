package view;

import controller.IPlayerActions;
import model.ICard;
import model.PlayerColor;

/**
 * This interface represents the view for the ThreeTrios game.
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
  CardView getSelectedCardView();

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
}
