import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFrame;

import model.ThreeTriosModel;
import view.ThreeTriosJSwingView;


/**
 * Main class to run the game.
 */
public final class ThreeTrios {

  /**
   *  Main.
   *
   * @param args arguments
   * @throws FileNotFoundException if file not found
   */
  public static void main(String[] args) throws FileNotFoundException {
    ThreeTriosModel model = new ThreeTriosModel(
            "resources" + File.separator + "testDeck9+1.txt",
            "resources" + File.separator + "grid4x4OneHole.txt");
    model.startGame();
    ThreeTriosJSwingView view = new ThreeTriosJSwingView(model);
    view.setVisible(true);
    view.pack();
    view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
