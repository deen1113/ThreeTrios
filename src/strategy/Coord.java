package strategy;

import model.Card;

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
