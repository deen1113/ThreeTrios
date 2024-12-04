package cs3500.threetrios.providernew.model;

import cs3500.threetrios.providernew.controller.ModelWatcher;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the TrioModel interface, parameterized with C extends Card<C>.
 */
public class ThreeTriosProviderModel<C extends Card<C>> implements TrioModel<C> {
  private TrioMap<C> grid;
  private IPlayer<C> redPlayer;
  private IPlayer<C> bluePlayer;
  private IPlayer<C> currentPlayer;
  private boolean gameOver;
  private IPlayer<C> winner;
  private final List<ModelWatcher<C>> watchers;
  private List<C> deck;
  private boolean shuffleDeck;
  private String mapName;
  private String deckName;

  public ThreeTriosProviderModel() {
    this.watchers = new ArrayList<>();
  }

  @Override
  public void initializeGame(String mapName, String deckName, boolean shuffle,
                             IPlayer<C> redPlayer, IPlayer<C> bluePlayer) {
    this.mapName = mapName;
    this.deckName = deckName;
    this.shuffleDeck = shuffle;
    this.redPlayer = redPlayer;
    this.bluePlayer = bluePlayer;
    this.currentPlayer = redPlayer;
    this.gameOver = false;
    this.winner = null;

    // Initialize grid based on mapName
    initializeGrid(mapName);

    // Initialize and deal the deck
    initializeDeckAndDeal(deckName, shuffleDeck);

    // Notify observers to update the view
    for (ModelWatcher<C> watcher : watchers) {
      watcher.signalTurn();
    }
  }

  private void initializeGrid(String mapName) {
    // For simplicity, create a grid with no holes
    // In a real implementation, you'd read from a file based on mapName
    int width = 5; // Example width
    int height = 5; // Example height
    boolean[][] holes = new boolean[height][width]; // All false (no holes)
    grid = new TrioMap<>(width, height, holes);
  }

  private void initializeDeckAndDeal(String deckName, boolean shuffle) {
    // Read the deck from the file
    DeckReader<C> reader = new CardReader();
    File deckFile = new File(deckName);
    deck = reader.read(deckFile);

    if (shuffle) {
      java.util.Collections.shuffle(deck);
    }

    // Deal cards to players
    dealCardsToPlayers();
  }

  private void dealCardsToPlayers() {
    for (int i = 0; i < deck.size(); i++) {
      C card = deck.get(i);
      if (i % 2 == 0) {
        card.setColor(PlayerColor.RED);
        redPlayer.getHand().add(card);
      } else {
        card.setColor(PlayerColor.BLUE);
        bluePlayer.getHand().add(card);
      }
    }
    redPlayer.colorHand(redPlayer.getHand());
    bluePlayer.colorHand(bluePlayer.getHand());
  }

  @Override
  public void placeCard(int handIndex, int posY, int posX) {
    if (gameOver) {
      throw new IllegalStateException("Cannot place a card, the game is over.");
    }
    if (posY < 0 || posY >= grid.getHeight() || posX < 0 || posX >= grid.getWidth()) {
      throw new IllegalArgumentException("Position out of bounds.");
    }
    Cell<C> cell = grid.getTile(posY, posX);
    if (cell.isHole()) {
      throw new IllegalArgumentException("Cannot place a card on a hole.");
    }

    if (cell.hasCard()) {
      throw new IllegalArgumentException("This cell is already occupied.");
    }
    List<C> hand = currentPlayer.getHand();

    if (handIndex < 0 || handIndex >= hand.size()) {
      throw new IllegalArgumentException("Invalid hand index.");
    }

    C cardToPlace = hand.remove(handIndex);
    cell.playToTile(cardToPlace);
    handleBattles(posY, posX, cardToPlace);

    // Check if the game is over after this move
    checkGameOver();

    if (gameOver) {
      // Notify observers about the game over
      for (ModelWatcher<C> watcher : watchers) {
        watcher.callWinner();
      }
    } else {
      // Switch to the next player's turn
      switchTurn();
    }
  }

  private void handleBattles(int row, int col, C card) {
    attackNorth(row, col, card);
    attackSouth(row, col, card);
    attackEast(row, col, card);
    attackWest(row, col, card);
  }

  private void attackNorth(int row, int col, C card) {
    int adjRow = row - 1;
    int adjCol = col;

    if (adjRow >= 0) {
      Cell<C> adjCell = grid.getTile(adjRow, adjCol);
      if (adjCell.hasCard()) {
        C adjCard = adjCell.getSpace();
        if (adjCard.getColor() != card.getColor()) {
          int cardValue = card.getValue(Compass.NORTH).toInteger();
          int adjValue = adjCard.getValue(Compass.SOUTH).toInteger();
          if (cardValue > adjValue) {
            adjCard.setColor(card.getColor());
          }
        }
      }
    }
  }

  private void attackSouth(int row, int col, C card) {
    int adjRow = row + 1;
    int adjCol = col;

    if (adjRow < grid.getHeight()) {
      Cell<C> adjCell = grid.getTile(adjRow, adjCol);
      if (adjCell.hasCard()) {
        C adjCard = adjCell.getSpace();
        if (adjCard.getColor() != card.getColor()) {
          int cardValue = card.getValue(Compass.SOUTH).toInteger();
          int adjValue = adjCard.getValue(Compass.NORTH).toInteger();
          if (cardValue > adjValue) {
            adjCard.setColor(card.getColor());
          }
        }
      }
    }
  }

  private void attackEast(int row, int col, C card) {
    int adjRow = row;
    int adjCol = col + 1;

    if (adjCol < grid.getWidth()) {
      Cell<C> adjCell = grid.getTile(adjRow, adjCol);
      if (adjCell.hasCard()) {
        C adjCard = adjCell.getSpace();
        if (adjCard.getColor() != card.getColor()) {
          int cardValue = card.getValue(Compass.EAST).toInteger();
          int adjValue = adjCard.getValue(Compass.WEST).toInteger();
          if (cardValue > adjValue) {
            adjCard.setColor(card.getColor());
          }
        }
      }
    }
  }

  private void attackWest(int row, int col, C card) {
    int adjRow = row;
    int adjCol = col - 1;

    if (adjCol >= 0) {
      Cell<C> adjCell = grid.getTile(adjRow, adjCol);
      if (adjCell.hasCard()) {
        C adjCard = adjCell.getSpace();
        if (adjCard.getColor() != card.getColor()) {
          int cardValue = card.getValue(Compass.WEST).toInteger();
          int adjValue = adjCard.getValue(Compass.EAST).toInteger();
          if (cardValue > adjValue) {
            adjCard.setColor(card.getColor());
          }
        }
      }
    }
  }

  private void checkGameOver() {
    boolean gridFull = true;

    // Check if all cells (excluding holes) are occupied
    for (int row = 0; row < grid.getHeight(); row++) {
      for (int col = 0; col < grid.getWidth(); col++) {
        Cell<C> cell = grid.getTile(row, col);
        if (!cell.isHole() && !cell.hasCard()) {
          gridFull = false;
          break;
        }
      }
      if (!gridFull) {
        break;
      }
    }

    // Check if both players have no cards left
    boolean handsEmpty = redPlayer.getHand().isEmpty() && bluePlayer.getHand().isEmpty();

    if (gridFull || handsEmpty) {
      gameOver = true;
      determineWinner();
      // Notify observers about the game over
      for (ModelWatcher<C> watcher : watchers) {
        watcher.callWinner();
      }
    }
  }

  private void determineWinner() {
    int redScore = getScore(redPlayer);
    int blueScore = getScore(bluePlayer);

    if (redScore > blueScore) {
      winner = redPlayer;
    } else if (blueScore > redScore) {
      winner = bluePlayer;
    } else {
      winner = null; // It's a draw
    }
  }

  private void switchTurn() {
    if (currentPlayer == redPlayer) {
      currentPlayer = bluePlayer;
    } else {
      currentPlayer = redPlayer;
    }
    // Notify observers about the turn change
    for (ModelWatcher<C> watcher : watchers) {
      watcher.signalTurn();
    }
  }

  @Override
  public void addListener(ModelWatcher<C> observer) {
    watchers.add(observer);
  }

  @Override
  public TrioMap<C> getGrid() {
    return grid;
  }

  @Override
  public boolean isGameOver() {
    return gameOver;
  }

  @Override
  public IPlayer<C> getWinner() {
    return winner;
  }

  @Override
  public IPlayer<C> getTurn() {
    return currentPlayer;
  }

  @Override
  public IPlayer<C> getRedPlayer() {
    return redPlayer;
  }

  @Override
  public IPlayer<C> getBluePlayer() {
    return bluePlayer;
  }

  @Override
  public int getScore(IPlayer<C> player) {
    int score = 0;

    // Count the number of cards on the grid belonging to the player
    for (int row = 0; row < grid.getHeight(); row++) {
      for (int col = 0; col < grid.getWidth(); col++) {
        Cell<C> cell = grid.getTile(row, col);
        if (cell.hasCard() && cell.getSpace().getColor() == player.getColor()) {
          score++;
        }
      }
    }

    // Add the number of cards remaining in the player's hand
    score += player.getHand().size();

    return score;
  }

  @Override
  public int getFlipTotal(IPlayer<C> player, C card, int x, int y) {
    // Implement this method if required
    return 0;
  }

  @Override
  public List<C> getPlayerHand(IPlayer<C> player) {
    return player.getHand();
  }

  @Override
  public Cell<C> getTile(int x, int y) {
    if (y < 0 || y >= grid.getHeight() || x < 0 || x >= grid.getWidth()) {
      throw new IllegalArgumentException("Position out of bounds");
    }
    return grid.getTile(y, x);
  }

  @Override
  public int getGridHeight() {
    return grid.getHeight();
  }

  @Override
  public int getGridWidth() {
    return grid.getWidth();
  }
}