import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.*;

import model.ThreeTriosModel;
import view.ThreeTriosJSwingView;

public final class ThreeTrios {
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
