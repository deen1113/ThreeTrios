import java.io.File;
import java.io.FileNotFoundException;

import model.ThreeTriosModel;
import view.ThreeTriosJSwingView;

public final class ThreeTrios {
  public static void main(String[] args) throws FileNotFoundException {
    ThreeTriosModel model = new ThreeTriosModel("resources" + File.separator + "testDeck9+1.txt", "resources" + File.separator + "grid5x5Even.txt");
    ThreeTriosJSwingView view = new ThreeTriosJSwingView(model);
    view.setVisible(true);
  }
}
