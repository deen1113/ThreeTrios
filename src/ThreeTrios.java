import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFrame;

import controller.ThreeTriosController;
import player.AIPlayer;
import player.HumanPlayer;
import player.IPlayer;
import model.PlayerColor;
import model.ThreeTriosModel;
import strategy.Corners;
import strategy.FlipMaxCards;
import view.ThreeTriosJSwingView;


/**
 * Main class to run the game.
 */
public final class ThreeTrios {

  /**
   * Main.
   *
   * @param args arguments
   */
  public static void main(String[] args) {
    ThreeTriosModel model;
    IPlayer player1;
    IPlayer player2;


    if (args.length != 2) {
      throw new IllegalArgumentException("Wrong number of arguments");
    }

    try {
      model = new ThreeTriosModel(
              "resources" + File.separator + "testDeck9+1.txt",
              "resources" + File.separator + "grid4x4OneHole.txt");
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }

    switch (args[0].toLowerCase()) {
      case "human":
        player1 = new HumanPlayer(model, PlayerColor.RED, model.getRedHand());
        break;
      case "strategy1":
        player1 = new AIPlayer(model, new FlipMaxCards(), PlayerColor.RED, model.getRedHand());
        break;
      case "strategy2":
        player1 = new AIPlayer(model, new Corners(), PlayerColor.RED, model.getRedHand());
        break;
      default:
        player1 = null;
        break;
    }

    switch (args[1].toLowerCase()) {
      case "human":
        player2 = new HumanPlayer(model, PlayerColor.BLUE, model.getBlueHand());
        break;
      case "strategy1":
        player2 = new AIPlayer(model, new FlipMaxCards(), PlayerColor.BLUE, model.getBlueHand());
        break;
      case "strategy2":
        player2 = new AIPlayer(model, new Corners(), PlayerColor.BLUE, model.getBlueHand());
        break;
      default:
        player2 = null;
        break;
    }

    ThreeTriosJSwingView player1View = new ThreeTriosJSwingView(model);
    ThreeTriosJSwingView player2View = new ThreeTriosJSwingView(model);
    ThreeTriosController controller1 = new ThreeTriosController(model, player1View, player1);
    ThreeTriosController controller2 = new ThreeTriosController(model, player2View, player2);

    model.setListener(controller1);
    model.setListener(controller2);

    player1View.setFeatures(controller1);
    if (player1 instanceof HumanPlayer) {
      player1View.setVisible(true);
    }
    player1View.pack();
    player1View.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    player2View.setFeatures(controller2);
    if (player2 instanceof HumanPlayer) {
      player2View.setVisible(true);
    }
    player2View.pack();
    player2View.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    model.startGame();
  }
}
