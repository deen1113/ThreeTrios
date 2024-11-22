package player;

import java.util.List;

import model.ICard;
import model.PlayerColor;

public interface IPlayer {
  PlayerColor getColor();

  List<ICard> getHand();

  void playCard(int row, int col, int handIndex, PlayerColor color);
}
