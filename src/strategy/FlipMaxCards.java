package strategy;

import model.Card;
import model.IReadonlyThreeTriosModel;
import model.PlayerColor;

/**
 * This strategy finds the move that flips as many cards
 * as possible with a single move.
 */
public class FlipMaxCards implements IThreeTriosStrategy {
  int totalFlippedCards = 0;
  int bestRow = 0;
  int bestCol = 0;
  Card bestCard = null;
  Card tieCard = null;
  int tieCount = 0;

  @Override
  public Coord chooseMove(IReadonlyThreeTriosModel model, PlayerColor playerColor) {
    for (int row = 0; row < model.getGridRowAmount(); row++) {
      for (int col = 0; col < model.getGridColAmount(); col++) {
        if (model.isMoveValid(row, col)) {
          if (playerColor == PlayerColor.RED) {
            for (Card card : model.getRedHand()) {
              doBestMoveCheck(model, row, col, card);
            }
          } else if (playerColor == PlayerColor.BLUE) {
            for (Card card : model.getBlueHand()) {
              doBestMoveCheck(model, row, col, card);
            }
          }
        }
      }
    }
    if (tieCount > 0) {
      return new Coord(0, 0, tieCard);
    }
    return new Coord(bestRow, bestCol, bestCard);
  }

  private void doBestMoveCheck(IReadonlyThreeTriosModel model, int row, int col, Card card) {
    int tempFlippedCards = model.totalFlippedWithMove(card, row, col);
    if (totalFlippedCards < tempFlippedCards) {
      if (row == 0 && col == 0) {
        tieCard = card;
      }
      totalFlippedCards = tempFlippedCards;
      bestRow = row;
      bestCol = col;
      bestCard = card;
      tieCount = 0;
    } else if (totalFlippedCards == tempFlippedCards) {
      tieCount++;
    }
  }
}
