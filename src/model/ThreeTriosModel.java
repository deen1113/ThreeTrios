package model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import controller.ModelListener;
import player.HumanPlayer;
import player.IPlayer;

/**
 * This is the implementation of the model for the game Three Trios.
 */
public class ThreeTriosModel implements IThreeTriosModel {

  private IPlayer currentPlayer;
  private final IPlayer redPlayer;
  private final IPlayer bluePlayer;
  private final List<ICard> redHand;
  private final List<ICard> blueHand;
  private final Deck deck;
  private final Grid grid;
  private GameState gameState;
  private int numFlipped = 0;
  private ICard simCard;
  private final List<ModelListener> listeners;

  /**
   * Constructor for the ThreeTriosModel.
   *
   * @param deckFile file path for the deck config
   * @param gridFile file path for the grid config
   * @throws FileNotFoundException if the file is not found
   */
  public ThreeTriosModel(String deckFile, String gridFile) throws FileNotFoundException {
    this.deck = new Deck(deckFile);
    this.grid = new Grid(gridFile);
    redHand = new ArrayList<>();
    blueHand = new ArrayList<>();
    redPlayer = new HumanPlayer(this, PlayerColor.RED, redHand);
    bluePlayer = new HumanPlayer(this, PlayerColor.BLUE, blueHand);
    this.currentPlayer = redPlayer;
    gameState = GameState.NOT_STARTED;
    this.listeners = new ArrayList<>();
  }

  @Override
  public void startGame() {
    if (gameState != GameState.NOT_STARTED) {
      // if the game is in any other state besides not started, throw error
      throw new IllegalStateException("Game is not ready to start.");
    }
    if (deck.getDeck().size() % 2 != 0) {
      // deck size must be even
      throw new IllegalArgumentException("Deck size must be even");
    }
    if (grid.getNumCardCells() % 2 == 0) {
      // grid size must be odd
      throw new IllegalArgumentException("Number of card cells must be odd");
    }
    if (grid.getNumCardCells() > deck.getDeck().size()) {
      // grid size must be less than or equal to deck size
      throw new IllegalArgumentException(
              "Number of card cells must be less than or equal to deck size"
      );
    }
    fillHands();

    notifyTurn(currentPlayer);
    gameState = GameState.PLACING;
  }

  public void setListener(ModelListener listener) {
    listeners.add(listener);
  }

  private void notifyTurn(IPlayer player) {
      for (ModelListener listener : listeners) {
        listener.onTurnChanged(player);
      }
  }

  @Override
  public void placeCard(int row, int col, int handIndex, PlayerColor color) {
    if (gameState != GameState.PLACING) {
      throw new IllegalStateException("Game is not in placing stage.");
    }
    if (grid.isHole(row, col)) {
      throw new IllegalArgumentException("Cannot play card to a hole.");
    }
    if (row < 0 || row >= grid.getGrid().length || col < 0 || col >= grid.getGrid()[0].length) {
      throw new IllegalArgumentException("Row or Col is out of bounds.");
    }


    if (color == PlayerColor.RED) {
      placeCardHelper(row, col, redPlayer.getHand(), handIndex);
    } else if (color == PlayerColor.BLUE) {
      placeCardHelper(row, col, bluePlayer.getHand(), handIndex);
    } else {
      throw new IllegalArgumentException("Color cannot be null.");
    }
    gameState = GameState.BATTLE;
  }

  private void placeCardHelper(int row, int col, List<ICard> hand, int handIndex) {
    if (hand.isEmpty()) {
      throw new IllegalArgumentException("Hand is empty.");
    }
    if (handIndex < 0 || handIndex >= hand.size()) {
      throw new IllegalArgumentException("Hand index is out of bounds.");
    }
    if (grid.getCard(row, col) != null) {
      throw new IllegalArgumentException("Cell is already occupied.");
    }

    ICard card = hand.get(handIndex);
    grid.setCard(row, col, card);
    hand.remove(handIndex);
  }

  @Override
  public void battle(int row, int col) {
    if (gameState != GameState.BATTLE) {
      throw new IllegalStateException("Game is not in battle stage.");
    }
    if (row < 0 || row >= grid.getGrid().length || col < 0 || col >= grid.getGrid()[0].length) {
      throw new IllegalArgumentException("Row or Col is out of bounds.");
    }
    if (grid.getCard(row, col) == null) {
      throw new IllegalArgumentException("Cell does not have a card.");
    }

    // check north
    attackNorth(row, col, grid.getCard(row, col).getColor(), false);

    // check south
    attackSouth(row, col, grid.getCard(row, col).getColor(), false);

    // check east
    attackEast(row, col, grid.getCard(row, col).getColor(), false);

    // check west
    attackWest(row, col, grid.getCard(row, col).getColor(), false);

    gameState = GameState.PLACING;
  }

  @Override
  public void updateCurrentPlayer() {
    if (currentPlayer == redPlayer) {
      currentPlayer = bluePlayer;
    } else {
      currentPlayer = redPlayer;
    }

    notifyTurn(currentPlayer);
  }

  private void attackNorth(int row, int col, PlayerColor color, boolean isSim) {
    if (row == 0) { // check if row - 1 is out of bounds
      return;
    }
    if (grid.getCard(row - 1, col) == null) {
      return;
    }
    boolean cardIsOpponents = grid.getCard(row - 1, col).getColor() != color;
    boolean cardExists = grid.getCard(row - 1, col) != null;
    if (cardIsOpponents && cardExists) {
      int attack = grid.getCard(row, col).getAttack(Direction.NORTH);
      int defense = grid.getCard(row - 1, col).getAttack(Direction.SOUTH);
      if (attack > defense) {
        if (isSim) {
          numFlipped++;
          simulateBattle(simCard, row - 1, col);
        }
        grid.getCard(row - 1, col).setColor(color);
        battle(row - 1, col);
      }
    }
  }

  private void attackSouth(int row, int col, PlayerColor color, boolean isSim) {
    if (row == grid.getGrid().length - 1) { // check if row + 1 is out of bounds
      return;
    }
    if (grid.getCard(row + 1, col) == null) {
      return;
    }
    boolean cardIsOpponents = grid.getCard(row + 1, col).getColor() != color;
    if (cardIsOpponents) {
      int attack = grid.getCard(row, col).getAttack(Direction.SOUTH);
      int defense = grid.getCard(row + 1, col).getAttack(Direction.NORTH);
      if (attack > defense) {
        if (isSim) {
          numFlipped++;
          simulateBattle(simCard, row + 1, col);
        }
        grid.getCard(row + 1, col).setColor(color);
        battle(row + 1, col);
      }
    }
  }

  private void attackEast(int row, int col, PlayerColor color, boolean isSim) {
    if (col == grid.getGrid()[0].length - 1) { // check if col + 1 is out of bounds
      return;
    }
    if (grid.getCard(row, col + 1) == null) {
      return;
    }
    boolean cardIsOpponents = grid.getCard(row, col + 1).getColor() != color;
    boolean cardExists = grid.getCard(row, col + 1) != null;
    if (cardIsOpponents && cardExists) {
      int attack = grid.getCard(row, col).getAttack(Direction.EAST);
      int defense = grid.getCard(row, col + 1).getAttack(Direction.WEST);
      if (attack > defense) {
        if (isSim) {
          numFlipped++;
          simulateBattle(simCard, row, col + 1);
        }
        grid.getCard(row, col + 1).setColor(color);
        battle(row, col + 1);
      }
    }
  }

  private void attackWest(int row, int col, PlayerColor color, boolean isSim) {
    if (col == 0) { // check if col - 1 is out of bounds
      return;
    }
    if (grid.getCard(row, col - 1) == null) {
      return;
    }
    boolean cardIsOpponents = grid.getCard(row, col - 1).getColor() != color;
    boolean cardExists = grid.getCard(row, col - 1) != null;
    if (cardIsOpponents && cardExists) {
      int attack = grid.getCard(row, col).getAttack(Direction.WEST);
      int defense = grid.getCard(row, col - 1).getAttack(Direction.EAST);
      if (attack > defense) {
        if (isSim) {
          numFlipped++;
          simulateBattle(simCard, row, col - 1);
        }
        grid.getCard(row, col - 1).setColor(color);
        battle(row, col - 1);
      }
    }
  }

  @Override
  public boolean isGameOver() {
    for (int row = 0; row < grid.getGrid().length; row++) {
      for (int col = 0; col < grid.getGrid()[0].length; col++) {
        if (grid.getCard(row, col) == null && !grid.isHole(row, col)) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public IPlayer determineWinner() {
    int redCount = 0;
    int blueCount = 0;
    IPlayer winner = null;

    for (int row = 0; row < grid.getGrid().length; row++) {
      for (int col = 0; col < grid.getGrid()[0].length; col++) {
        Card card = grid.getCard(row, col);
        if (card != null) {
          if (card.getColor() == PlayerColor.RED) {
            redCount++;
          } else if (card.getColor() == PlayerColor.BLUE) {
            blueCount++;
          }
        }
      }
    }

    redCount += redHand.size();
    blueCount += blueHand.size();

    if (redCount > blueCount) {
      winner = redPlayer;
    } else if (blueCount > redCount) {
      winner = bluePlayer;
    } else {
      return null;
    }

    return winner;
  }

  @Override
  public List<ICard> getRedHand() {
    return redPlayer.getHand();
  }

  @Override
  public List<ICard> getBlueHand() {
    return bluePlayer.getHand();
  }

  @Override
  public GameState getGameState() {
    return gameState;
  }

  @Override
  public Grid getGrid() {
    return this.grid;
  }

  @Override
  public IPlayer getCurrentPlayer() {
    return currentPlayer;
  }

  private void fillHands() {
    while (!deck.getDeck().isEmpty()) {
      redHand.add(deck.draw().setColor(PlayerColor.RED));
      blueHand.add(deck.draw().setColor(PlayerColor.BLUE));
    }
  }

  @Override
  public boolean isMoveValid(int row, int col) {
    if (gameState == GameState.PLACING) {
      return grid.getCard(row, col) == null && !grid.isHole(row, col);
    }
    return false;
  }

  @Override
  public PlayerColor playedCardColor(int row, int col) {
    if (grid.getCard(row, col) ==  null) {
      return null;
    }
    return grid.getCard(row, col).getColor();
  }

  @Override
  public int getRedScore() {
    int redCount = 0;
    for (int row = 0; row < grid.getGrid().length; row++) {
      for (int col = 0; col < grid.getGrid()[0].length; col++) {
        Card card = grid.getCard(row, col);
        if (card != null) {
          if (card.getColor() == PlayerColor.RED) {
            redCount++;
          }
        }
      }
    }
    redCount += redHand.size();
    return redCount;
  }

  @Override
  public int getBlueScore() {
    int blueCount = 0;
    for (int row = 0; row < grid.getGrid().length; row++) {
      for (int col = 0; col < grid.getGrid()[0].length; col++) {
        Card card = grid.getCard(row, col);
        if (card != null) {
          if (card.getColor() == PlayerColor.BLUE) {
            blueCount++;
          }
        }
      }
    }
    blueCount += blueHand.size();
    return blueCount;
  }

  @Override
  public Card cellContents(int row, int col) {
    return grid.getCard(row, col);
  }

  @Override
  public int getGridRowAmount() {
    return grid.getGrid().length;
  }

  @Override
  public int getGridColAmount() {
    return grid.getGrid()[0].length;
  }

  @Override
  public int totalFlippedWithMove(ICard card, int row, int col) {
    simCard = card;
    simulateBattle(card, row, col);

    int tempNumFlipped = numFlipped;
    numFlipped = 0;
    return tempNumFlipped;
  }

  private void simulateBattle(ICard card, int row, int col) {

    // check north
    attackNorth(row, col, card.getColor(), true);

    // check south
    attackSouth(row, col, card.getColor(), true);

    // check east
    attackEast(row, col, card.getColor(), true);

    // check west
    attackWest(row, col, card.getColor(), true);
  }
}
