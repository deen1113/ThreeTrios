package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import controller.IPlayerActions;
import model.Card;
import model.ICard;
import model.IReadonlyThreeTriosModel;
import model.PlayerColor;

/**
 * This is the implementation of the grid for the game.
 * The grid creates a Yellow cell for empty card cells,
 * grey cells for holes, and draws cards placed on the grid.
 */
public class GridView extends JPanel {
  public final IReadonlyThreeTriosModel model;
  private IPlayerActions features;

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

    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        handleGridClick(e);
      }
    });
  }

  public void setFeatures(IPlayerActions features) {
    this.features = features;
  }

  private void handleGridClick(MouseEvent e) {
    int totalRows = model.getGridRowAmount();
    int totalCols = model.getGridColAmount();

    int cellWidth = getWidth() / totalCols;
    int cellHeight = getHeight() / totalRows;

    int col = e.getX() / cellWidth;
    int row = e.getY() / cellHeight;

    features.onGridCellSelected(row, col);


    System.out.println("Cell clicked at row: " + row + ", column: " + col);
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
          g2d.setColor(Color.GRAY);
          g2d.fillRect(x, y, cellWidth, cellHeight);
        } else if (model.cellContents(row, col) != null) {
          ICard card = model.cellContents(row, col);
          Color cardColor = card.getColor() == PlayerColor.RED ? Color.PINK : Color.CYAN;
          g2d.setColor(cardColor);
          g2d.fillRect(x, y, cellWidth, cellHeight);
        } else {
          g2d.setColor(Color.YELLOW);
          g2d.fillRect(x, y, cellWidth, cellHeight);
        }

        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, cellWidth, cellHeight);
      }
    }
  }
}
