package cs3500.threetrios.providernew.model;


import java.awt.*;
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
    return (TrioMap<C>) this.model.getGrid();
  }

  @Override
  public boolean isGameOver() {
    return this.model.isGameOver();
  }

  @Override
  public IPlayer<C> getWinner() {
    return (IPlayer<C>) this.model.determineWinner();
  }

  @Override
  public IPlayer<C> getTurn() {
    return (IPlayer<C>) this.model.getCurrentPlayer();
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
    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null.");
    }
    if (player.getColor().equals(PlayerColor.RED)) {
      return this.model.getRedScore();
    } else {
      return this.model.getBlueScore();
    }
  }

  @Override
  public int getFlipTotal(IPlayer<C> player, C card, int x, int y) {
    return 0;
  }

  @Override
  public List<C> getPlayerHand(IPlayer<C> player) {
    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null.");
    }
    if (this.model.getCurrentPlayer() == Color.RED) {
      return (List<C>) this.model.getRedHand();
    } else {
      return (List<C>) this.model.getBlueHand();
    }

  }

  @Override
  public Cell<C> getTile(int x, int y) {
    return this.getGrid().getTile(x, y);
  }

  @Override
  public int getGridHeight() {
    return this.getGrid().getHeight();
  }

  @Override
  public int getGridWidth() {
    return this.getGrid().getWidth();
  }
}
