package strategy;

import java.util.ArrayList;
import java.util.List;

import model.Card;
import model.Direction;
import model.IReadonlyThreeTriosModel;
import model.PlayerColor;

public class Corners implements IThreeTriosStrategy {
  Card hardestCardToFlip;
  List<Card> hand = new ArrayList<>();
  int rowIndex = 0;
  int colIndex = 0;


  @Override
  public Coord chooseMove(IReadonlyThreeTriosModel model, PlayerColor playerColor) {
    rowIndex = model.getGridRowAmount() - 1;
    colIndex = model.getGridColAmount() - 1;

    if (playerColor == PlayerColor.RED) {
      hand = model.getRedHand();
      if (model.isMoveValid(0, 0)) {
        return new Coord(0, 0, checkTopLeft(model));
      } else if (model.isMoveValid(0, colIndex)) {
        return new Coord(0, colIndex, checkTopRight(model));
      } else if (model.isMoveValid(rowIndex, 0)) {
        return new Coord(rowIndex, 0, checkBottomLeft(model));
      } else if (model.isMoveValid(rowIndex, colIndex)) {
        return new Coord(rowIndex, colIndex, checkBottomRight(model));
      }
    }
    return null;
  }

  private Card checkBottomRight(IReadonlyThreeTriosModel model) {
    for (Card card : hand) {
      int cardNorthAttack = card.getAttack(Direction.NORTH);
      int cardWestAttack = card.getAttack(Direction.WEST);
      int hardestCardNorthAttack = hardestCardToFlip.getAttack(Direction.NORTH);
      int hardestCardWestAttack = hardestCardToFlip.getAttack(Direction.WEST);

      if (hardestCardToFlip == null) {
        hardestCardToFlip = card;
      }

      if (cardNorthAttack > hardestCardNorthAttack && cardWestAttack > hardestCardWestAttack) {
        hardestCardToFlip = card;
      }
    }
    return hardestCardToFlip;
  }

  private Card checkBottomLeft(IReadonlyThreeTriosModel model) {
    for (Card card : hand) {
      int cardNorthAttack = card.getAttack(Direction.NORTH);
      int cardEastAttack = card.getAttack(Direction.EAST);
      int hardestCardNorthAttack = hardestCardToFlip.getAttack(Direction.NORTH);
      int hardestCardEastAttack = hardestCardToFlip.getAttack(Direction.EAST);

      if (hardestCardToFlip == null) {
        hardestCardToFlip = card;
      }

      if (cardNorthAttack > hardestCardNorthAttack && cardEastAttack > hardestCardEastAttack) {
        hardestCardToFlip = card;
      }
    }
    return hardestCardToFlip;
  }

  private Card checkTopRight(IReadonlyThreeTriosModel model) {
    for (Card card : hand) {
      int cardWestAttack = card.getAttack(Direction.WEST);
      int cardSouthAttack = card.getAttack(Direction.SOUTH);
      int hardestCardWestAttack = hardestCardToFlip.getAttack(Direction.WEST);
      int hardestCardSouthAttack = hardestCardToFlip.getAttack(Direction.SOUTH);

      if (hardestCardToFlip == null) {
        hardestCardToFlip = card;
      }

      if (cardWestAttack > hardestCardWestAttack && cardSouthAttack > hardestCardSouthAttack) {
        hardestCardToFlip = card;
      }
    }
    return hardestCardToFlip;
  }

  private Card checkTopLeft(IReadonlyThreeTriosModel model) {
    for (Card card : hand) {
      int cardEastAttack = card.getAttack(Direction.EAST);
      int cardSouthAttack = card.getAttack(Direction.SOUTH);
      int hardestCardEastAttack = hardestCardToFlip.getAttack(Direction.EAST);
      int hardestCardSouthAttack = hardestCardToFlip.getAttack(Direction.SOUTH);

      if (hardestCardToFlip == null) {
        hardestCardToFlip = card;
      }
      if (cardEastAttack > hardestCardEastAttack && cardSouthAttack > hardestCardSouthAttack) {
        hardestCardToFlip = card;
      }
    }
    return hardestCardToFlip;
  }
}
