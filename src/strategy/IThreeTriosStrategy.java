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
   * This method returns a Coord, which provides the row, column and the Card to be played.
   * It takes in a model to access necessary information for each strategy, as well as the
   * playerColor in order to access the correct hands.
   *
   * @param model for any necessary observations
   * @param playerColor for RED or BLUE hands
   * @return a Coord that has the row, col, and card to be played
   */
  Coord chooseMove(IReadonlyThreeTriosModel model, PlayerColor playerColor);
}
