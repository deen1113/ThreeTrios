package model;

import java.io.FileNotFoundException;

public class ThreeTriosVariant1Model extends ThreeTriosModel implements IThreeTriosModel {
  private final boolean reverse;
  private boolean fallenAce;

  /**
   * Constructor for the ThreeTriosModel.
   *
   * @param deckFile file path for the deck config
   * @param gridFile file path for the grid config
   * @throws FileNotFoundException if the file is not found
   */
  public ThreeTriosVariant1Model(
          String deckFile, String gridFile, boolean reverse, boolean fallenAce)
          throws FileNotFoundException {
    super(deckFile, gridFile);
    this.reverse = reverse;
    this.fallenAce = fallenAce;
  }

  @Override
  public void battle(int row, int col) {
    ThreeTriosModel.battleChecks(row, col, gameState != GameState.BATTLE, grid);

      attackNorth(row, col, grid.getCard(row, col).getColor(), false, reverse);
      attackSouth(row, col, grid.getCard(row, col).getColor(), false, reverse);
      attackEast(row, col, grid.getCard(row, col).getColor(), false, reverse);
      attackWest(row, col, grid.getCard(row, col).getColor(), false, reverse);

      gameState = GameState.PLACING;
  }

  @Override
  protected void simulateBattle(ICard card, int row, int col) {
    // Override simulation to account for reverse logic
    attackNorth(row, col, card.getColor(), true, reverse);
    attackSouth(row, col, card.getColor(), true, reverse);
    attackEast(row, col, card.getColor(), true, reverse);
    attackWest(row, col, card.getColor(), true, reverse);
  }
}
