package cs3500.threetrios.providernew.model;


import java.util.List;

import model.ICard;
import model.IThreeTriosModel;


public class ModelAdapter<C extends ICard> implements ReadOnlyTrioModel<C> {
  private final IThreeTriosModel model;

  public ModelAdapter(IThreeTriosModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }
    this.model = model;
  }

  @Override
  public TrioMap<C> getGrid() {
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public IPlayer<C> getWinner() {
    return null;
  }

  @Override
  public IPlayer<C> getTurn() {
    return null;
  }

  @Override
  public IPlayer<C> getRedPlayer() {
    return null;
  }

  @Override
  public IPlayer<C> getBluePlayer() {
    return null;
  }

  @Override
  public int getScore(IPlayer<C> player) {
    return 0;
  }

  @Override
  public int getFlipTotal(IPlayer<C> player, C card, int x, int y) {
    return 0;
  }

  @Override
  public List<C> getPlayerHand(IPlayer<C> player) {
    return List.of();
  }

  @Override
  public Cell<C> getTile(int x, int y) {
    return null;
  }

  @Override
  public int getGridHeight() {
    return 0;
  }

  @Override
  public int getGridWidth() {
    return 0;
  }
}
