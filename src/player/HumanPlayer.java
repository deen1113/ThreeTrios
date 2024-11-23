package player;

import java.util.List;

import model.ICard;
import model.IThreeTriosModel;
import model.PlayerColor;

/**
 * This class represents a human player. A human player places cards normally
 * through the model. Every player, either human or AI, has a color and a matching hand.
 */
public class HumanPlayer implements IPlayer {
  private final PlayerColor color;
  private final List<ICard> hand;
  private final IThreeTriosModel model;

  /**
   * This is the constructor for a human player. A human player takes the model in order
   * to play a card, a hand in order to access their cards, and a color, either red or blue.
   *
   * @param model ThreeTrios model
   * @param color the player's color, red or blue
   * @param hand the player's hand
   */
  public HumanPlayer(IThreeTriosModel model, PlayerColor color, List<ICard> hand) {
    this.color = color;
    this.hand = hand;
    this.model = model;
  }

  @Override
  public PlayerColor getColor() {
    return color;
  }

  @Override
  public List<ICard> getHand() {
    return hand;
  }

  @Override
  public String toString() {
    return "Player " + color + "\n";
  }

  @Override
  public void playCard(int row, int col, int handIndex, PlayerColor color) {
    model.placeCard(row, col, handIndex, color);
  }
}
