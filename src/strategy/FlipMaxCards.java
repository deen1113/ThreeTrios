package strategy;

import java.util.List;

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
  int bestCardIndex = -1;
  int tieCard = -1;
  int tieCount = 0;

  @Override
  public Coord chooseMove(IReadonlyThreeTriosModel model, PlayerColor playerColor) {
    for (int row = 0; row < model.getGridRowAmount(); row++) {
      for (int col = 0; col < model.getGridColAmount(); col++) {
        if (model.isMoveValid(row, col)) {
          if (playerColor == PlayerColor.RED) {
            List<Card> redHand = model.getRedHand();
            for (int i = 0; i < redHand.size(); i++) {
              Card card = redHand.get(i);
              doBestMoveCheck(model, row, col, card, i);
            }
          } else if (playerColor == PlayerColor.BLUE) {
            List<Card> blueHand = model.getBlueHand();
            for (int i = 0; i < blueHand.size(); i++) {
              Card card = blueHand.get(i);
              doBestMoveCheck(model, row, col, card, i);
            }
          }
        }
      }
    }
    if (tieCount > 0) {
      return new Coord(0, 0, tieCard);
    }
    return new Coord(bestRow, bestCol, bestCardIndex);
  }

  private void doBestMoveCheck(
          IReadonlyThreeTriosModel model, int row, int col, Card card, int idx) {
    int tempFlippedCards = model.totalFlippedWithMove(card, row, col);
    if (totalFlippedCards < tempFlippedCards) {
      if (row == 0 && col == 0) {
        tieCard = idx;
      }
      totalFlippedCards = tempFlippedCards;
      bestRow = row;
      bestCol = col;
      bestCardIndex = idx;
      tieCount = 0;
    } else if (totalFlippedCards == tempFlippedCards) {
      tieCount++;
    }
  }
}
