package controller;

import player.IPlayer;
import model.IThreeTriosModel;
import model.PlayerColor;
import view.IThreeTriosJSwingView;

public class ThreeTriosController implements IPlayerActions {
  private final IThreeTriosModel model;
  private final IThreeTriosJSwingView view;
  private final IPlayer player;

  public ThreeTriosController(IThreeTriosModel model, IThreeTriosJSwingView view, IPlayer player) {
    this.model = model;
    this.view = view;
    this.player = player;
    this.view.setFeatures(this);
  }

  @Override
  public void onCardSelected() {
    if (view.getSelectedCardIndex() > -1) {
      view.getSelectedCardIndex();
    } else {
      throw new IllegalArgumentException("Cannot select card when it's not your turn");
    }
  }

  @Override
  public void onGridCellSelected(int row, int col) {
    try {
      int cardIdx = this.view.getSelectedCardIndex();
      PlayerColor currentPlayerColor = this.model.getCurrentPlayer().getColor();
      this.player.playCard(row, col, cardIdx, currentPlayerColor);
      this.model.battle(row, col);
      this.model.updateCurrentPlayer();

      if (model.isGameOver()) {
        this.view.displayGameWinner();
      }

      this.view.refresh();
    } catch (Exception e) {
      this.view.displayException(e);
    }
  }
}
