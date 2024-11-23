package strategy;

import java.util.List;

import model.ICard;
import model.IReadonlyThreeTriosModel;
import model.PlayerColor;

/**
 * This strategy finds the move that flips as many cards
 * as possible with a single move. If there are multiple moves that flip the same
 * amount of cards, the strategy defaults to playing to the top left most cell.
 */
public class FlipMaxCards implements IThreeTriosStrategy {
  private int totalFlippedCards = 0;
  private int bestRow = 0;
  private int bestCol = 0;
  private int bestCardIndex = -1;
  private int tieCard = -1;
  private int tieCount = 0;

  @Override
  public Coord chooseMove(IReadonlyThreeTriosModel model, PlayerColor playerColor) {
    for (int row = 0; row < model.getGridRowAmount(); row++) {
      for (int col = 0; col < model.getGridColAmount(); col++) {
        if (model.isMoveValid(row, col)) {
          if (playerColor == PlayerColor.RED) {
            List<ICard> redHand = model.getRedHand();
            for (int i = 0; i < redHand.size(); i++) {
              ICard card = redHand.get(i);
              doBestMoveCheck(model, row, col, card, i);
            }
          } else if (playerColor == PlayerColor.BLUE) {
            List<ICard> blueHand = model.getBlueHand();
            for (int i = 0; i < blueHand.size(); i++) {
              ICard card = blueHand.get(i);
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
          IReadonlyThreeTriosModel model, int row, int col, ICard card, int idx) {
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
