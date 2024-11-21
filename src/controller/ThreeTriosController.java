package controller;

import model.ICard;
import model.IThreeTriosModel;
import model.Player;
import model.PlayerColor;
import view.IThreeTriosJSwingView;

public class ThreeTriosController implements IPlayerActions {
  private final IThreeTriosModel model;
  private final IThreeTriosJSwingView view;
  private final Player player;
  private int clickedCardIndex;

  public ThreeTriosController(IThreeTriosModel model, IThreeTriosJSwingView view, Player player) {
    this.model = model;
    this.view = view;
    this.player = player;
    this.view.setFeatures(this);
  }

  @Override
  public void onCardSelected() {
    if (view.getSelectedCardIndex() > -1) {
      clickedCardIndex = view.getSelectedCardIndex();
    } else {
      throw new IllegalArgumentException("Cannot select card when it's not your turn");
    }
  }

  @Override
  public void onGridCellSelected(int row, int col) {
    try {
      int cardIdx = view.getSelectedCardIndex();
      PlayerColor currentPlayerColor = model.getCurrentPlayer().getColor();
      model.placeCard(row, col, cardIdx, currentPlayerColor);
      model.battle(row, col);
      view.refresh();
    }
    catch (IllegalStateException e) {
      throw new IllegalArgumentException("Cannot place card when it's not your turn");
    }
  }
}
