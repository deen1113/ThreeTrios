package strategy;


import model.IReadonlyThreeTriosModel;
import model.PlayerColor;

public interface IThreeTriosStrategy {
  Coord chooseMove(IReadonlyThreeTriosModel model, PlayerColor playerColor);
}
