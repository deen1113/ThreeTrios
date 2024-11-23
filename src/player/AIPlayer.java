package player;

import java.util.List;

import model.ICard;
import model.IThreeTriosModel;
import model.PlayerColor;
import strategy.Coord;
import strategy.IThreeTriosStrategy;

/**
 * This class represents an AI player. An AI player plays a card using a given strategy.
 * There are two strategies, either flipping the maximum amount of cards or playing to
 * corners. Every player, either human or AI, has a color and a matching hand.
 */
public class AIPlayer implements IPlayer {
  private final IThreeTriosStrategy strategy;
  private final IThreeTriosModel model;
  private final PlayerColor playerColor;
  private final List<ICard> hand;

  /**
   * This is the constructor for an AI player. An AI player takes a model to play a move,
   * a strategy to pick the best move to make according to the given strategy,
   * a hand in order to access their cards, and a color, either red or blue.
   *
   * @param model ThreeTrios model
   * @param strategy given strategy, either FlipMaxCards or Corners
   * @param color the player's color, either red or blue
   * @param hand the player's hand
   */
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
