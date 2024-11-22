package player;

import java.util.List;

import model.ICard;
import model.IThreeTriosModel;
import model.PlayerColor;

public class HumanPlayer implements IPlayer {
  private final PlayerColor color;
  private final List<ICard> hand;
  private final IThreeTriosModel model;

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
