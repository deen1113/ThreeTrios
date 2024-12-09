package view;

import model.ICard;
import model.IReadonlyThreeTriosModel;

public class GridWithHintDecorator extends GridView implements IGridHints{
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


  @Override
  public void setHints(boolean enabled) {
    hints = enabled;
    baseGridView.refresh();
  }

  @Override
  public boolean isHintsEnabled() {
    return hints;
  }

  @Override
  public void updateHints(ICard selectedCard) {
    if (selectedCard == null) {
      hintValues = new int[model.getGridRowAmount()][model.getGridColAmount()];
      return;
    }

    for (int row = 0; row < hintValues.length; row++) {
      for (int col = 0; col < hintValues[row].length; col++) {
        hintValues[row][col] = model.totalFlippedWithMove(selectedCard, row, col);
      }
    }

    baseGridView.refresh();
  }
}
