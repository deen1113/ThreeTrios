package strategy;

import java.util.ArrayList;
import java.util.List;

import model.Card;
import model.Direction;
import model.ICard;
import model.IReadonlyThreeTriosModel;
import model.PlayerColor;

/**
 * This strategy goes for each corner of the grid first,
 * since the corners hide two of the sides, making them harder to flip.
 * The strategy then picks the card with the two highest exposed numbers.
 */
public class Corners implements IThreeTriosStrategy {
  ICard hardestCardToFlip;
  List<ICard> hand = new ArrayList<>();
  int rowIndex = 0;
  int colIndex = 0;
  int cardIdx = -1;


  @Override
  public Coord chooseMove(IReadonlyThreeTriosModel model, PlayerColor playerColor) {
    rowIndex = model.getGridRowAmount() - 1;
    colIndex = model.getGridColAmount() - 1;

    if (playerColor == PlayerColor.RED) {
      hand = model.getRedHand();
      return getBestMove(model);
    } else if (playerColor == PlayerColor.BLUE) {
      hand = model.getBlueHand();
      return getBestMove(model);
    }
    return null;
  }

  private Coord getBestMove(IReadonlyThreeTriosModel model) {
    if (model.isMoveValid(0, 0)) {
      return new Coord(0, 0, checkTopLeft(model));
    } else if (model.isMoveValid(0, colIndex)) {
      return new Coord(0, colIndex, checkTopRight(model));
    } else if (model.isMoveValid(rowIndex, 0)) {
      return new Coord(rowIndex, 0, checkBottomLeft(model));
    } else if (model.isMoveValid(rowIndex, colIndex)) {
      return new Coord(rowIndex, colIndex, checkBottomRight(model));
    }
    return null;
  }

  private int checkBottomRight(IReadonlyThreeTriosModel model) {
    for (int i = 0; i < hand.size(); i++) {
      ICard card = hand.get(i);
      int cardNorthAttack = card.getAttack(Direction.NORTH);
      int cardWestAttack = card.getAttack(Direction.WEST);
      int hardestCardNorthAttack = hardestCardToFlip.getAttack(Direction.NORTH);
      int hardestCardWestAttack = hardestCardToFlip.getAttack(Direction.WEST);

      if (hardestCardToFlip == null) {
        hardestCardToFlip = card;
        cardIdx = i;
      }

      if (cardNorthAttack > hardestCardNorthAttack && cardWestAttack > hardestCardWestAttack) {
        hardestCardToFlip = card;
        cardIdx = i;
      }
    }
    return cardIdx;
  }

  private int checkBottomLeft(IReadonlyThreeTriosModel model) {
    for (int i = 0; i < hand.size(); i++) {
      Card card = (Card) hand.get(i);
      int cardNorthAttack = card.getAttack(Direction.NORTH);
      int cardEastAttack = card.getAttack(Direction.EAST);
      int hardestCardNorthAttack = hardestCardToFlip.getAttack(Direction.NORTH);
      int hardestCardEastAttack = hardestCardToFlip.getAttack(Direction.EAST);

      if (hardestCardToFlip == null) {
        hardestCardToFlip = card;
        cardIdx = i;
      }

      if (cardNorthAttack > hardestCardNorthAttack && cardEastAttack > hardestCardEastAttack) {
        hardestCardToFlip = card;
        cardIdx = i;
      }
    }
    return cardIdx;
  }

  private int checkTopRight(IReadonlyThreeTriosModel model) {
    for (int i = 0; i < hand.size(); i++) {
      ICard card = hand.get(i);
      int cardWestAttack = card.getAttack(Direction.WEST);
      int cardSouthAttack = card.getAttack(Direction.SOUTH);
      int hardestCardWestAttack = hardestCardToFlip.getAttack(Direction.WEST);
      int hardestCardSouthAttack = hardestCardToFlip.getAttack(Direction.SOUTH);

      if (hardestCardToFlip == null) {
        hardestCardToFlip = card;
        cardIdx = i;
      }

      if (cardWestAttack > hardestCardWestAttack && cardSouthAttack > hardestCardSouthAttack) {
        hardestCardToFlip = card;
        cardIdx = i;
      }
    }
    return cardIdx;
  }

  private int checkTopLeft(IReadonlyThreeTriosModel model) {
    for (int i = 0; i < hand.size(); i++) {
      Card card = (Card) hand.get(i);
      int cardEastAttack = card.getAttack(Direction.EAST);
      int cardSouthAttack = card.getAttack(Direction.SOUTH);
      int hardestCardEastAttack = hardestCardToFlip.getAttack(Direction.EAST);
      int hardestCardSouthAttack = hardestCardToFlip.getAttack(Direction.SOUTH);

      if (hardestCardToFlip == null) {
        hardestCardToFlip = card;
        cardIdx = i;
      }

      if (cardEastAttack > hardestCardEastAttack && cardSouthAttack > hardestCardSouthAttack) {
        hardestCardToFlip = card;
        cardIdx = i;
      }
    }
    return cardIdx;
  }
}
