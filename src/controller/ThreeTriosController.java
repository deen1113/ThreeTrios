package controller;


import model.ICard;
import model.IThreeTriosModel;
import model.Player;
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
  public void onCardSelected(int cardIndex) {
    clickedCardIndex = cardIndex;
  }

  @Override
  public void onGridCellSelected(int row, int col) {
    model.placeCard(row, col, clickedCardIndex, player.getColor());
    model.battle(row, col);
  }
}
