package view;

import java.awt.*;

import javax.swing.JPanel;
import javax.swing.JLabel;

import model.ICard;
import model.IReadonlyThreeTriosModel;
import model.PlayerColor;

public class GridWithHintDecorator extends GridView implements IGridHints {
  private final IReadonlyThreeTriosModel model;
  private final IView baseGridView;
  private boolean hints = false;
  private int[][] hintValues;

  /**
   * This is the constructor for the Grid with Hints Decorator. It takes a model,
   * which paintComponent uses to get the grid and set each grid
   * cell to the correct color.
   *
   * @param model the current model
   */
  public GridWithHintDecorator(IReadonlyThreeTriosModel model, IView baseGridView) {
    super(model);
    this.model = model;
    this.baseGridView = baseGridView;
    hintValues = new int[model.getGridRowAmount()][model.getGridColAmount()];

    refresh();
    setPreferredSize(new Dimension(500, 500));
  }

  @Override
  public void setHints(boolean enabled) {
    hints = enabled;
    refresh();
  }

  @Override
  public boolean isHintsEnabled() {
    return hints;
  }

  @Override
  public void updateHints(ICard selectedCard) {
    hintValues = new int[model.getGridRowAmount()][model.getGridColAmount()];

    if (hints) {
      for (int row = 0; row < hintValues.length; row++) {
        for (int col = 0; col < hintValues[row].length; col++) {
          if (model.isMoveValid(row, col)) {
            hintValues[row][col] = model.totalFlippedWithMove(selectedCard, row, col);
          }
        }
      }
    }
    refresh();
  }

  @Override
  public void refresh() {
    super.refresh();
    if (hints) {
      updateGrid();
    }
  }

  private void updateGrid() {
    int rows = model.getGridRowAmount();
    int cols = model.getGridColAmount();

    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        JPanel cell = getGridCell(row, col);
        removeLabelsFromCell(cell);

        if (model.getGrid().isHole(row, col)) {
          cell.setBackground(Color.GRAY);
        } else if (model.cellContents(row, col) != null) {
          CardCells(row, col, cell, model);
        } else {
          cell.setBackground(Color.YELLOW);
          if (hintValues != null) {
            cell.add(new JLabel(String.valueOf(hintValues[row][col])));
          }
        }
      }
    }
  }

  private void removeLabelsFromCell(JPanel cell) {
    for (Component component : cell.getComponents()) {
      if (component instanceof JLabel) {
        cell.remove(component);
      }
    }
  }
}
