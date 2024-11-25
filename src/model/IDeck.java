package model;

import java.util.List;

/**
 * The deck in ThreeTrios is used as a way to access the cards through the files.
 * The deck does not have much a purpose outside of getting cards and shuffling
 * them before starting the game.
 */
public interface IDeck {
  /**
   * Shuffles the deck.
   */
  void shuffle();

  /**
   * Returns the deck of cards.
   *
   * @return a list of cards
   */
  List<Card> getDeck();

  /**
   * Draws a card from the deck.
   *
   * @return the drawn card
   */
  Card draw();
}
