package model;

/**
 * This interface represents a Card.
 * A Card has a name, color and 4 directions represented by numbers.
 * The numbers are attacks, used to battle other cards.
 * A bigger number beats a smaller number in battle.
 * The color depends on the player who played the card.
 * If a card that was attacked loses in battle, it gets flipped
 * to the opposite color.
 * If a card that attacked loses in battle, its color stays the same.
 */
public interface ICard {
  /**
   * Gets the name of the card.
   *
   * @return name
   */
  String getName();

  /**
   * Gets the attack value of the card in the given direction.
   *
   * @param direction an Enum direction of either NORTH, SOUTH, EAST, or WEST
   * @return the index of the direction
   */
  int getAttack(Direction direction);

  /**
   * Gets the color of the card.
   *
   * @return Either RED or BLUE, depending on who owns the card
   */
  PlayerColor getColor();

  /**
   * Sets color of card to provided player's color.
   *
   * @param color given player's color
   * @return the card
   */
  Card setColor(PlayerColor color);

  /**
   * Returns a string representation of the card.
   *
   * @return the name and the ints at each direction index
   */
  @Override
  String toString();
}
