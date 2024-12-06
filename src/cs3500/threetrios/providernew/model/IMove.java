package cs3500.threetrios.providernew.model;

/**
 * Represents a move the game of ThreeTrios.
 * A move has an x and y position on the grid, the index of the hand
 * to be played, and an arbitrary score.
 * X and Y are set to be the top left corner of a grid, where y
 * expands as it goes down and x as it goes right.
 */
public interface IMove {
  /**
   * Fetches hand index in a Move.
   *
   * @return the int position that the move wants to make
   */
  int getHandIndex();

  /**
   * Fetches the x position on grid.
   *
   * @return int that represents the x position.
   */
  int getPosX();

  /**
   * Fetches the y position on grid.
   *
   * @return int that represents the y pos.
   */
  int getPosY();

  /**
   * Fetches an arbitrary score of the given move.
   *
   * @return the int that represents the score.
   */
  int getScore();
}
