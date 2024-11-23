package view;

import java.util.List;

import controller.IPlayerActions;
import model.ICard;

public interface IHandView extends IView {
  /**
   * Updates the hand view with the given list of cards.
   * This method rebuilds the hand display and sets interactivity based on whether it's the current player's hand.
   *
   * @param hand             the player's hand
   * @param isCurrentPlayer  true if this hand belongs to the current player, false otherwise
   */
  void updateHand(List<ICard> hand, boolean isCurrentPlayer);

  /**
   * Gets the selected CardView.
   *
   * @return the selected CardView, or null if no card is selected
   */
  CardView getSelectedCardView();

  /**
   * Sets the features object to enable communication with the controller.
   *
   * @param features the controller implementing IPlayerActions
   */
  void setFeatures(IPlayerActions features);

  /**
   * Deselects the currently selected card, if any.
   */
  void deselectCard();
}
