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
public class GridView extends JPanel implements IView {
  private final IReadonlyThreeTriosModel model;
  private final JPanel[][] grid;
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
    int rows = model.getGridRowAmount();
    int cols = model.getGridColAmount();

    setLayout(new GridLayout(rows, cols));
    grid = new JPanel[rows][cols];

    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        JPanel cell = makeCell(row, col);

        grid[row][col] = cell;
        add(cell);
      }
    }
    refresh();
    setPreferredSize(new Dimension(500, 500));
  }

  // Makes cells that represent each GridCell in the grid
  private JPanel makeCell(int row, int col) {
    JPanel cell = new JPanel();
    cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    cell.setBackground(Color.YELLOW);

    cell.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (features != null) {
          features.onGridCellSelected(row, col);
          System.out.println("Cell clicked at row: " + row + ", column: " + col);
        }
      }
    });
    return cell;
  }

  @Override
  public void setFeatures(IPlayerActions features) {
    this.features = features;
  }

  @Override
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
          CardCells(row, col, cell, model);
        } else {
          cell.setBackground(Color.YELLOW);
        }
      }
    }
  }

  static void CardCells(int row, int col, JPanel cell, IReadonlyThreeTriosModel model) {
    ICard card = model.cellContents(row, col);
    Color cardColor = card.getColor() == PlayerColor.RED ? Color.PINK : Color.CYAN;

    CardView cardView = new CardView(card, cardColor, 0);
    cell.setLayout(new BorderLayout());
    cell.add(cardView, BorderLayout.CENTER);
  }

  protected JPanel getGridCell(int row, int col) {
    return grid[row][col];
  }
}

