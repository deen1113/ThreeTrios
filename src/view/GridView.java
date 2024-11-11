package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

import javax.swing.JPanel;

import model.Card;
import model.IReadonlyThreeTriosModel;
import model.PlayerColor;

/**
 * This is the implementation of the grid for the game.
 * The grid creates a Yellow cell for empty card cells,
 * grey cells for holes, and adds a CardView cell for
 * placed cards.
 */
public class GridView extends JPanel {
  public final IReadonlyThreeTriosModel model;

  /**
   * This is the constructor for the GridView. It takes a model,
   * which paintComponent uses to get the grid and set each grid
   * cell to the correct color.
   *
   * @param model the current model
   */
  public GridView(IReadonlyThreeTriosModel model) {
    this.model = model;
    setPreferredSize(new Dimension(500, 500));
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    int totalRows = model.getGridRowAmount();
    int totalCols = model.getGridColAmount();

    int cellWidth = getWidth() / totalCols;
    int cellHeight = getHeight() / totalRows;


    for (int row = 0; row < totalRows; row++) {
      for (int col = 0; col < totalCols; col++) {
        int x = col * cellWidth;
        int y = row * cellHeight;

        if (model.getGrid().isHole(row, col)) {
          // Draw Hole
          g2d.setColor(Color.GRAY);
          g2d.fillRect(x, y, cellWidth, cellHeight);
        } else if (model.cellContents(row, col) != null) {
          // Draw Card
          Card card = model.cellContents(row, col);
          if (card.getColor() == PlayerColor.RED) {
            CardView cardView = new CardView(card, Color.PINK);
            add(cardView);
          } else if (card.getColor() == PlayerColor.BLUE) {
            CardView cardView = new CardView(card, Color.CYAN);
            add(cardView);
          }
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
