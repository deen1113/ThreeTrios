package controller;

import player.AIPlayer;
import player.IPlayer;
import model.IThreeTriosModel;
import model.PlayerColor;
import view.IThreeTriosJSwingView;

public class ThreeTriosController implements IPlayerActions, ModelListener {
  private final IThreeTriosModel model;
  private final IThreeTriosJSwingView view;
  private final IPlayer player;

  public ThreeTriosController(IThreeTriosModel model, IThreeTriosJSwingView view, IPlayer player) {
    this.model = model;
    this.view = view;
    this.player = player;
    this.view.setFeatures(this);
    this.model.setListener(this);
  }

  @Override
  public void onCardSelected() {
    try {
      if (!model.getCurrentPlayer().getColor().equals(player.getColor())) {
        throw new IllegalArgumentException("It's not your turn!");
      }

      int selectedCardIndex = view.getSelectedCardIndex();
      if (selectedCardIndex == -1) {
        throw new IllegalArgumentException("No card selected!");
      }

      System.out.println("Card selected: " + selectedCardIndex);

    } catch (Exception e) {
      view.displayException(e);
    }
  }


  @Override
  public void onGridCellSelected(int row, int col) {
    try {
      if (!model.getCurrentPlayer().getColor().equals(player.getColor())) {
        throw new IllegalStateException("It's not your turn!");
      }

      int cardIdx = view.getSelectedCardIndex();
      if (cardIdx == -1) {
        throw new IllegalStateException("No card selected!");
      }

      // Play the card
      PlayerColor currentPlayerColor = player.getColor();
      player.playCard(row, col, cardIdx, currentPlayerColor);

      // Battle if possible
      model.battle(row, col);

      // Update the current player
      model.updateCurrentPlayer();

      // Check if game is over
      if (model.isGameOver()) {
        view.displayGameWinner();
      }

      // Refresh view
      view.refresh();

    } catch (Exception e) {
      // Display any exceptions in the view
      view.displayException(e);
    }
  }

  @Override
  public IPlayer getPlayer() {
    return this.player;
  }

  @Override
  public void onTurnChanged(IPlayer currentPlayer) {
    view.refresh();

    if (currentPlayer instanceof AIPlayer) {
      player.playCard(0, 0, 0, player.getColor());
    }
  }
}
