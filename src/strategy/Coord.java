package strategy;

/**
 * This class represents a location and card in order to play
 * the best move.
 */
public class Coord {
  int row;
  int col;
  int card;

  public Coord(int bestRow, int bestCol, int bestCard) {
    row = bestRow;
    col = bestCol;
    card = bestCard;
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  public int getCardIndex() {
    return card;
  }
}
