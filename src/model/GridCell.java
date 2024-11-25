package model;

/**
 * Represents a cell in the grid of the game.
 */
public class GridCell implements IGridCell {
  private boolean isHole;
  private ICard card;


  /**
   * Constructs a GridCell object.
   *
   * @param isHole whether the constructed cell is a hole or not
   */
  public GridCell(boolean isHole) {
    this.isHole = isHole;
    this.card = null;
  }

  @Override
  public boolean isHole() {
    return isHole;
  }

  @Override
  public ICard getCard() {
    return card;
  }

  @Override
  public void setCard(ICard card) {
    this.card = card;
    this.isHole = false;
  }
}
