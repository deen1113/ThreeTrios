package controller;

import player.AIPlayer;
import player.IPlayer;
import model.IThreeTriosModel;
import model.PlayerColor;
import view.IThreeTriosJSwingView;

/**
 * This is the controller for the ThreeTrios game. The controller listens to the model and
 * view and responds when the player clicks on the view and or the model sends an update.
 * The player can click on a card in the hands or on the grid.
 */
public class ThreeTriosController implements IPlayerActions, ModelListener {
  private final IThreeTriosModel model;
  private final IThreeTriosJSwingView view;
  private final IPlayer player;

  /**
   * The constructor for the controller takes in a model and view in order to
   * subscribe as a listener. The controller also uses the model to get necessary
   * information about the game state. The controller is also assigned a player,
   * and the player can only use their assigned controller.
   *
   * @param model  ThreeTrios model
   * @param view   ThreeTrios view
   * @param player assigned player for the controller
   */
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

      if (view.isHintsEnabled()) {
        view.updateHints(view.getSelectedCardView().getCard());
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

      PlayerColor currentPlayerColor = player.getColor();
      player.playCard(row, col, cardIdx, currentPlayerColor);

      model.battle(row, col);

      model.updateCurrentPlayer();

      if (model.isGameOver()) {
        view.displayGameWinner();
      }

      view.refresh();

    } catch (Exception e) {
      view.displayException(e);
    }
  }

  @Override
  public void onHintButtonClicked() {
    view.toggleHints(this.player.getColor(), !view.isHintsEnabled());
    view.refresh();
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
