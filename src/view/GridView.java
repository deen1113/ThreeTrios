package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.*;
import model.IReadonlyThreeTriosModel;

public class GridView extends JPanel {
  public final IReadonlyThreeTriosModel model;

  public GridView(IReadonlyThreeTriosModel model) {
    this.model = model;
    setPreferredSize(new Dimension(500, 500));
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    int rows = model.getGridRowAmount();
    int cols = model.getGridColAmount();

    int cellWidth = getWidth() / cols;
    int cellHeight = getHeight() / rows;


    for (int row = 0; row < model.getGridRowAmount(); row++) {
      for (int col = 0; col < model.getGridColAmount(); col++) {
        int x = col * cellWidth;
        int y = row * cellHeight;

        if (model.getGrid().isHole(row, col)) {
          // Draw Hole
          g2d.setColor(Color.GRAY);
          g2d.fillRect(x, y, cellWidth, cellHeight);
        } else {
          // Draw Empty Cell
          g2d.setColor(Color.YELLOW);
          g2d.fillRect(x, y, cellWidth, cellHeight);
        }

        // Draw Border
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, cellWidth, cellHeight);
      }
    }
  }
}
