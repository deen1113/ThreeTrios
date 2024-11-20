package view;

import java.awt.Color;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.IPlayerActions;
import model.ICard;
import model.IReadonlyThreeTriosModel;
import model.PlayerColor;

/**
 * This is the implementation of the JSwing View for Three Trios.
 * This class adds all the individual panels together, setting
 * the correct colors for the hands and adding the grid.
 */
public class ThreeTriosJSwingView extends JFrame implements IThreeTriosJSwingView {
  private IReadonlyThreeTriosModel model;
  private IPlayerActions features;
  private HandView redHand;
  private HandView blueHand;

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

    setTitle("Current Player: " + model.getCurrentPlayer().getColor());

    redHand = new HandView(Color.PINK, model.getRedHand());
    add(redHand, BorderLayout.WEST);

    blueHand = new HandView(Color.CYAN, model.getBlueHand());
    add(blueHand, BorderLayout.EAST);

    JPanel grid = new GridView(model);
    add(grid, BorderLayout.CENTER);
  }

  @Override
  public void refresh() {

  }

  @Override
  public void setFeatures(IPlayerActions features) {
    this.features = features;
  }

  @Override
  public int getSelectedCard() {
   if (model.getCurrentPlayer().getColor().equals(PlayerColor.RED)) {
     return redHand.getSelectedCardView().getIndex();
   } else if (model.getCurrentPlayer().getColor().equals(PlayerColor.BLUE)) {
     return blueHand.getSelectedCardView().getIndex();
   }
   return -1;
  }
}
