package view;

import java.awt.Color;
import java.awt.BorderLayout;

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
   * This is the constructor for the GridView. It takes a model,
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
  }

  static void CardCells(int row, int col, JPanel cell, IReadonlyThreeTriosModel model) {
    ICard card = model.cellContents(row, col);
    Color cardColor = card.getColor() == PlayerColor.RED ? Color.PINK : Color.CYAN;

    CardView cardView = new CardView(card, cardColor, 0);
    cell.setLayout(new BorderLayout());
    cell.add(cardView, BorderLayout.CENTER);
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

    for (int row = 0; row < hintValues.length; row++) {
      for (int col = 0; col < hintValues[row].length; col++) {
        hintValues[row][col] = model.totalFlippedWithMove(selectedCard, row, col);
      }
    }

    refresh();
  }

  @Override
  public void refresh() {
    if (baseGridView != null) {
      baseGridView.refresh();
    }
    if (hints) {
      updateGrid();
    }
  }

  private void updateGrid() {
    if (hintValues != null) {
      for (int row = 0; row < hintValues.length; row++) {
        for (int col = 0; col < hintValues[0].length; col++) {
          JPanel cell = getGridCell(row, col);
          cell.removeAll();

          if (model.isMoveValid(row, col) && model.cellContents(row, col) == null) {
            JLabel hintLabel = new JLabel(String.valueOf(hintValues[row][col]));
            hintLabel.setForeground(Color.BLACK);

            cell.setLayout(new BorderLayout());
            cell.add(hintLabel, BorderLayout.CENTER);
          }
        }
      }
    }
  }
}
