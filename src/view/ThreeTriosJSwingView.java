package view;

import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.IReadonlyThreeTriosModel;

public class ThreeTriosJSwingView extends JFrame {
  IReadonlyThreeTriosModel model;


  public ThreeTriosJSwingView(IReadonlyThreeTriosModel model) {
    this.model = model;

    JPanel redSide = new HandView(Color.PINK, model.getRedHand());
    add(redSide, BorderLayout.WEST);

    JPanel blueSide = new HandView(Color.CYAN, model.getBlueHand());
    add(blueSide, BorderLayout.EAST);

    JPanel grid = new GridView(model);
    add(grid, BorderLayout.CENTER);
  }
}
