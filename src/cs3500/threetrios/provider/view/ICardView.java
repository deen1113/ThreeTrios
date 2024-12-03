package cs3500.threetrios.provider.view;

import model.ICard;

/**
 * A CardView represents a single card on the board.
 * It takes the player's color it is assigned and has all of its
 * numbers visible for each direction.
 */
public interface ICardView {
  /**
   * Gets the index of this CardView's place in hand.
   *
   * @return card's index in hand
   */
  int getIndex();

  /**
   * Gets the card this CardView represents.
   *
   * @return this card
   */
  ICard getCard();
}
