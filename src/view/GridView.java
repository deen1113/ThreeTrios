package view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import controller.IPlayerActions;
import model.ICard;
import model.IReadonlyThreeTriosModel;
import model.PlayerColor;

/**
 * This is the implementation of the grid for the game.
 * The grid creates a Yellow cell for empty card cells,
 * grey cells for holes, and draws cards placed on the grid.
 */
public class GridView extends JPanel {
  private final IReadonlyThreeTriosModel model;
  private final JPanel[][] grid;
  private final IThreeTriosJSwingView view;
  private IPlayerActions features;


  /**
   * This is the constructor for the GridView. It takes a model,
   * which paintComponent uses to get the grid and set each grid
   * cell to the correct color.
   *
   * @param model the current model
   */
  public GridView(IReadonlyThreeTriosModel model, IThreeTriosJSwingView view) {
    this.model = model;
    this.view = view;
    int rows = model.getGridRowAmount();
    int cols = model.getGridColAmount();

    setLayout(new GridLayout(rows, cols));
    grid = new JPanel[rows][cols];

    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        JPanel cell = new JPanel();
        cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cell.setBackground(Color.YELLOW);

        int finalRow = row;
        int finalCol = col;
        cell.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            if (features != null) {
              features.onGridCellSelected(finalRow, finalCol);
              System.out.println("Cell clicked at row: " + finalRow + ", column: " + finalCol);
            }
          }
        });

        grid[row][col] = cell;
        add(cell);
      }
    }
    refresh();
    setPreferredSize(new Dimension(500, 500));
  }

  public void setFeatures(IPlayerActions features) {
    this.features = features;
  }

  public void refresh() {
    int rows = model.getGridRowAmount();
    int cols = model.getGridColAmount();

    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        JPanel cell = grid[row][col];
        cell.removeAll();

        if (model.getGrid().isHole(row, col)) {
          cell.setBackground(Color.GRAY);
        } else if (model.cellContents(row, col) != null) {
          ICard card = model.cellContents(row, col);
          Color cardColor = card.getColor() == PlayerColor.RED ? Color.PINK : Color.CYAN;

          CardView cardView = new CardView(card, cardColor, 0);
          cell.setLayout(new BorderLayout());
          cell.add(cardView, BorderLayout.CENTER);
        } else {
          cell.setBackground(Color.YELLOW);
        }
      }
    }
  }
}

