package strategy;

import model.Card;

/**
 * This class represents a location and card in order to play
 * the best move.
 */
public class Coord {
  int row;
  int col;
  Card card;

  public Coord(int bestRow, int bestCol, Card bestCard) {
    row = bestRow;
    col = bestCol;
    card = bestCard;
  }
}
