package cs3500.threetrios.provider.view;

import java.util.List;

import controller.IPlayerActions;
import model.ICard;
import view.CardView;
import view.IView;

/**
 * This is the interface for HandView.
 * All the necessary methods for the hand to be properly represented
 * are defined here.
 * A hand is represented by a list of CardViews, positioned on the sides of the main view.
 * Each hand listens for a click, and can return the selected card.
 */
public interface IHandView extends IView {
  /**
   * Updates the hand view with the given list of cards.
   * This method rebuilds the hand display and sets interactivity based on whether
   * it's the current player's hand.
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
