package cs3500.threetrios.providernew.model;

import java.util.List;

public class PlayCard implements Card {
  private final String name;
  private final List<Value> values;
  private PlayerColor color;

  public PlayCard(String name, List<Value> values, PlayerColor color) {
    if (color == null || name == null || values == null) {
      throw new NullPointerException("Color, name or values cannot be null");
    }
    this.name = name;
    this.color = color;
    this.values = values;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public PlayerColor getColor() {
    return color;
  }

  @Override
  public void setColor(PlayerColor color) {
    if (color == null) {
      throw new IllegalArgumentException("Color cannot be null");
    }
    if (color == this.color) {
      throw new IllegalArgumentException("Can't change color of card to same color");
    }
    this.color = color;
  }

  @Override
  public Value getValue(Compass dir) {
    switch (dir) {
      case NORTH:
        return values.get(0);
      case SOUTH:
        return values.get(1);
      case EAST:
        return values.get(2);
      case WEST:
        return values.get(3);
      default:
        return null;
    }
  }

  @Override
  public Card clone() {
    return null;
  }
}
