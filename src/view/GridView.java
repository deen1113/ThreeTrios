package view;

import java.awt.*;

import javax.swing.*;

import model.IReadonlyThreeTriosModel;

public class GridView extends JPanel {
  public final IReadonlyThreeTriosModel model;
  int cellSize = 100;

  public GridView(IReadonlyThreeTriosModel model) {
    this.model = model;
    setPreferredSize(new Dimension(model.getGridColAmount() * cellSize,
            model.getGridRowAmount() * cellSize));
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    for (int row = 0; row < model.getGridRowAmount(); row++) {
      for (int col = 0; col < model.getGridColAmount(); col++) {
        int x = col * cellSize;
        int y = row * cellSize;

        if (model.getGrid().isHole(row, col)) {
          // Draw Hole
          g2d.setColor(Color.GRAY);
          g2d.fillRect(x, y, cellSize, cellSize);
        } else {
          // Draw Empty Cell
          g2d.setColor(Color.YELLOW);
          g2d.fillRect(x, y, cellSize, cellSize);
        }

        // Draw Border
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, cellSize, cellSize);
      }
    }
  }
}
