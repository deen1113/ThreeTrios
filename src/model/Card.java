package model;

import java.util.List;


/**
 * The implementation of a card in the game.
 */
public class Card implements ICard {
  private final String name;
  private final List<Integer> attackValues;
  private PlayerColor color;

  /**
   * Constructs a card with a name, attack values, and color.
   *
   * @param name   name of card
   * @param values attack values of card
   * @param color  color of card
   */
  public Card(String name, List<Integer> values, PlayerColor color) {
    this.name = name;
    this.attackValues = values;
    this.color = color;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getAttack(Direction direction) {
    return attackValues.get(direction.ordinal());
  }

  @Override
  public PlayerColor getColor() {
    return color;
  }


  @Override
  public Card setColor(PlayerColor color) {
    this.color = color;
    return this;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    if (this.getAttack(Direction.NORTH) == 10) {
      builder.append("A ");
    } else {
      builder.append(this.getAttack(Direction.NORTH)).append(" ");
    }
    if (this.getAttack(Direction.SOUTH) == 10) {
      builder.append("A ");
    } else {
      builder.append(this.getAttack(Direction.SOUTH)).append(" ");
    }
    if (this.getAttack(Direction.EAST) == 10) {
      builder.append("A ");
    } else {
      builder.append(this.getAttack(Direction.EAST)).append(" ");
    }
    if (this.getAttack(Direction.WEST) == 10) {
      builder.append("A");
    } else {
      builder.append(this.getAttack(Direction.WEST));
    }

    return builder.toString();
  }
}
