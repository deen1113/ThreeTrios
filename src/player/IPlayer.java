package player;

import java.util.List;

import model.ICard;
import model.PlayerColor;

/**
 * The interface for the player classes. A player should be able to get their color
 * and hand. They should also be able to play a card to any available space on the grid.
 */
public interface IPlayer {
  /**
   * Gives this player's color, either red or blue.
   *
   * @return the player's color
   */
  PlayerColor getColor();

  /**
   * Gets the player's hand.
   *
   * @return the player's hand
   */
  List<ICard> getHand();

  /**
   * This method is used to play a move from either a human player or
   * AI player. If the player is human, the method uses the model and the
   * given parameters to play a move as usual. If the player is AI, the
   * parameters are ignored and the method uses the player's strategy to
   * make a move.
   *
   * @param row       played row
   * @param col       played column
   * @param handIndex played card index in hand
   * @param color     player's color
   */
  void playCard(int row, int col, int handIndex, PlayerColor color);
}
