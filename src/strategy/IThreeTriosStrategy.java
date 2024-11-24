package strategy;


import model.IReadonlyThreeTriosModel;
import model.PlayerColor;

/**
 * This is the interface for each strategy for ThreeTrios.
 * Each type of strategy chooses a move in a different way,
 * overriding the chooseMove method.
 */
public interface IThreeTriosStrategy {

  /**
   * Chooses a move based on the strategy. There are two strategies.
   * <p>FlipMaxCards: Finds the move on the board that flips the max amount of
   * cards to the player's color. If there are multiple moves that flip the same
   * amount of cards, the strategy defaults to playing to the top left most cell.
   * <p>Corners: Plays to one of the four corners on the board, with the hardest card to
   * flip in that position. The hardest card to flip is defined by the card with the
   * highest numbers on it's exposed sides. If there are no corners available, the
   * strategy defaults to FlipMaxCards.
   *
   * @param model       for any necessary observations
   * @param playerColor for RED or BLUE hands
   * @return a Coord that has the row, col, and card to be played
   */
  Coord chooseMove(IReadonlyThreeTriosModel model, PlayerColor playerColor);
}
