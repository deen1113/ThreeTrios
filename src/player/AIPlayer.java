package player;

import java.util.List;

import model.ICard;
import model.IThreeTriosModel;
import model.PlayerColor;
import strategy.Coord;
import strategy.IThreeTriosStrategy;

public class AIPlayer implements IPlayer {
  private final IThreeTriosStrategy strategy;
  private final IThreeTriosModel model;
  private final PlayerColor playerColor;
  private final List<ICard> hand;

  public AIPlayer(IThreeTriosModel model, IThreeTriosStrategy strategy,
                  PlayerColor color,  List<ICard> hand) {
    this.strategy = strategy;
    this.model = model;
    this.playerColor = color;
    this.hand = hand;
  }

  @Override
  public PlayerColor getColor() {
    return playerColor;
  }

  @Override
  public List<ICard> getHand() {
    return hand;
  }

  @Override
  public void playCard(int row, int col, int handIndex, PlayerColor color) {
    Coord coord = strategy.chooseMove(model, playerColor);
    int playRow = coord.getRow();
    int playCol = coord.getCol();
    int cardIndex = coord.getCardIndex();
    model.placeCard(playRow, playCol, cardIndex, color);
  }
}
