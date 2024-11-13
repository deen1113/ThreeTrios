package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Card;
import model.Direction;
import model.IReadonlyThreeTriosModel;
import model.PlayerColor;

/**
 * Mock implementation of IReadonlyThreeTriosModel for testing strategies.
 */
public class MockThreeTriosModel implements IReadonlyThreeTriosModel {
  private int gridRows;
  private int gridCols;
  private List<Card> redHand;
  private List<Card> blueHand;
  private Map<String, Boolean> validMoves;
  private Map<String, Integer> flippedCardsCount;

  /**
   * Constructs a MockThreeTriosModel with specified grid size.
   *
   * @param gridRows number of rows in the grid
   * @param gridCols number of columns in the grid
   */
  public MockThreeTriosModel(int gridRows, int gridCols) {
    this.gridRows = gridRows;
    this.gridCols = gridCols;
    this.redHand = new ArrayList<>();
    this.blueHand = new ArrayList<>();
    this.validMoves = new HashMap<>();
    this.flippedCardsCount = new HashMap<>();
  }

  /**
   * Sets the red player's hand.
   *
   * @param redHand list of cards in red player's hand
   */
  public void setRedHand(List<Card> redHand) {
    this.redHand = redHand;
  }

  /**
   * Sets the blue player's hand.
   *
   * @param blueHand list of cards in blue player's hand
   */
  public void setBlueHand(List<Card> blueHand) {
    this.blueHand = blueHand;
  }

  /**
   * Sets whether a move is valid at the specified coordinates.
   *
   * @param row     row index
   * @param col     column index
   * @param isValid true if the move is valid, false otherwise
   */
  public void setValidMove(int row, int col, boolean isValid) {
    validMoves.put(row + "," + col, isValid);
  }

  /**
   * Sets the number of cards that would be flipped by a move.
   *
   * @param card  the card to be placed
   * @param row   row index
   * @param col   column index
   * @param count number of cards that would be flipped
   */
  public void setFlippedCardsCount(Card card, int row, int col, int count) {
    flippedCardsCount.put(row + "," + col + "," + card.hashCode(), count);
  }

  @Override
  public int getGridRowAmount() {
    return gridRows;
  }

  @Override
  public int getGridColAmount() {
    return gridCols;
  }

  @Override
  public boolean isMoveValid(int row, int col) {
    return validMoves.getOrDefault(row + "," + col, false);
  }

  @Override
  public PlayerColor playedCardColor(int row, int col) {
    return null;
  }

  @Override
  public int totalFlippedWithMove(Card card, int row, int col) {
    return flippedCardsCount.getOrDefault(row + "," + col + "," + card.hashCode(), 0);
  }

  @Override
  public List<Card> getRedHand() {
    return redHand;
  }

  @Override
  public List<Card> getBlueHand() {
    return blueHand;
  }

  @Override
  public GameState getGameState() {
    return null;
  }

  @Override
  public Grid getGrid() {
    return null;
  }

  // Implement other methods from IReadonlyThreeTriosModel as needed
  // Return default or mock values where appropriate

  @Override
  public Card cellContents(int row, int col) {
    // Return null or a mock Card as needed
    return null;
  }

  @Override
  public int getRedScore() {
    return 0;
  }

  @Override
  public int getBlueScore() {
    return 0;
  }

  @Override
  public boolean isGameOver() {
    // Return false or true based on test requirements
    return false;
  }

  @Override
  public PlayerColor determineWinner() {
    return null;
  }

  @Override
  public PlayerColor getCurrentPlayer() {
    // Return a default player color
    return PlayerColor.RED;
  }
}