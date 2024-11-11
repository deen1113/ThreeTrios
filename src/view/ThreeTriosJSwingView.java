package view;

import java.awt.Color;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.IReadonlyThreeTriosModel;

/**
 * This is the implementation of the JSwing View for Three Trios.
 * This class adds all the individual panels together, setting
 * the correct colors for the hands and adding the grid.
 */
public class ThreeTriosJSwingView extends JFrame implements IThreeTriosJSwingView {
  IReadonlyThreeTriosModel model;

  /**
   * This is the constructor for the view. All the other views
   * are added to the frame here, including their locations.
   * The title is also set, which shows whose turn it is.
   * The constructor takes in the model, which allows it to access
   * each player's hand, the grid, as well as the current player.
   *
   * @param model the current model
   */
  public ThreeTriosJSwingView(IReadonlyThreeTriosModel model) {
    this.model = model;

    setTitle("Current Player: " + model.getCurrentPlayer());

    JPanel redHand = new HandView(Color.PINK, model.getRedHand());
    add(redHand, BorderLayout.WEST);

    JPanel blueHand = new HandView(Color.CYAN, model.getBlueHand());
    add(blueHand, BorderLayout.EAST);

    JPanel grid = new GridView(model);
    add(grid, BorderLayout.CENTER);
  }
}
