package view;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.IReadonlyThreeTriosModel;

public class ThreeTriosJSwingView extends JFrame {
  IReadonlyThreeTriosModel model;
  private JPanel redSide;
  private JPanel blueSide;
  private JPanel grid;



  public ThreeTriosJSwingView(IReadonlyThreeTriosModel model) {
    this.model = model;

    redSide = new HandView(Color.PINK, model.getRedHand());
    add(redSide, BorderLayout.WEST);

    blueSide = new HandView(Color.CYAN, model.getBlueHand());
    add(blueSide, BorderLayout.EAST);

    grid = new GridView(model);
    add(grid, BorderLayout.CENTER);
  }
}
