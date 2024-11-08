package view;

import java.awt.*;
import java.util.List;
import model.Card;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.IReadonlyThreeTriosModel;
import model.PlayerColor;

public class ThreeTriosJSwingView extends JFrame {
  IReadonlyThreeTriosModel model;
  private JFrame frame;
  private JPanel redSide;
  private JPanel blueSide;
  private JPanel grid;



  public ThreeTriosJSwingView(IReadonlyThreeTriosModel model) {
    this.model = model;
    frame = new JFrame();
    frame.setSize(1000, 1000);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    redSide = new handView(Color.RED, model.getRedHand());
    add(redSide, BorderLayout.WEST);

    blueSide = new handView(Color.BLUE, model.getBlueHand());
    add(blueSide, BorderLayout.EAST);
  }
}
