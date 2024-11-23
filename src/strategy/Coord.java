package strategy;

/**
 * This class represents a location and card in order to play
 * the best move.
 */
public class Coord {
  private final int row;
  private final int col;
  private final int card;


  /**
   * Constructor for Coord.
   *
   * @param bestRow row
   * @param bestCol column
   * @param bestCard card index
   */
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
