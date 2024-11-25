package model;

/**
 * The grid takes in a file formatted as specified in the README.
 * It converts it into an array of GridCells, the objects we used to represent each cell.
 * Each GridCell can be a hole. If they are not, they are an empty card cell. A card cell
 * can take one card.
 */
public interface IGrid {
  /**
   * Returns the grid.
   *
   * @return an array of grid cells
   */
  GridCell[][] getGrid();

  /**
   * Sets the card in the grid cell.
   *
   * @param row  the row index of the cell
   * @param col  the column index of the cell
   * @param card the card being played
   */
  void setCard(int row, int col, ICard card);

  /**
   * Returns the card in the grid cell.
   *
   * @param row the row index of the cell
   * @param col the column index of the cell
   * @return the card in the cell
   */
  ICard getCard(int row, int col);

  /**
   * Checks if the grid cell is a hole.
   *
   * @param row the row index of the cell
   * @param col the column index of the cell
   * @return true if the cell is a hole and false if not
   */
  boolean isHole(int row, int col);

  /**
   * Returns the number of card cells in the grid.
   *
   * @return the number of cells that are NOT holes
   */
  int getNumCardCells();
}
