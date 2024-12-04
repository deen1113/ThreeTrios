package cs3500.threetrios.providernew.model;

public class Tile implements Cell {
  private Card card;
  private final boolean hole;

  public Tile(boolean isHole) {
    this.card = null;
    this.hole = isHole;
  }

  @Override
  public void playToTile(Card card) {
    if (card == null) {
      throw new IllegalArgumentException("Card cannot be null.");
    }
    if (isHole()) {
      throw new IllegalArgumentException("Cannot play card to hole.");
    }
    if (this.card != null) {
      throw new IllegalArgumentException("A card has already been played here.");
    }

    this.card = card;
  }

  @Override
  public Card getSpace() {
    if (this.card != null) {
      return this.card;
    }
    return null;
  }

  @Override
  public boolean isHole() {
    return hole;
  }

  @Override
  public boolean hasCard() {
    return this.card != null;
  }
}
