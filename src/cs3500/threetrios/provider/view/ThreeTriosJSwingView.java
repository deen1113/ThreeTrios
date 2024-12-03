package cs3500.threetrios.provider.view;

import java.awt.Color;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.IPlayerActions;
import model.IReadonlyThreeTriosModel;
import model.PlayerColor;
import view.CardView;
import view.GridView;
import view.HandView;
import view.IThreeTriosJSwingView;

/**
 * This is the implementation of the JSwing View for Three Trios.
 * This class adds all the individual panels together, setting
 * the correct colors for the hands and adding the grid.
 */
public class ThreeTriosJSwingView extends JFrame implements IThreeTriosJSwingView {
  private final IReadonlyThreeTriosModel model;
  private final HandView redHand;
  private final HandView blueHand;
  private final GridView gridView;
  private IPlayerActions features;

  /**
   * Constructor for the ThreeTriosJSwingView.
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

    gridView = new GridView(model);
    add(gridView, BorderLayout.CENTER);
  }

  @Override
  public void refresh() {
    PlayerColor currentPlayerColor = model.getCurrentPlayer().getColor();
    setTitle(features.getPlayer().getColor() +
            " Side: " +
            "Current Player: " +
            currentPlayerColor);

    boolean isRedCurrentPlayer = currentPlayerColor == PlayerColor.RED;
    System.out.println("Refreshing view. Current player: " + currentPlayerColor);

    redHand.updateHand(model.getRedHand(), isRedCurrentPlayer);
    blueHand.updateHand(model.getBlueHand(), !isRedCurrentPlayer);

    // Update grid
    gridView.refresh();

    revalidate();
    repaint();
  }


  @Override
  public void setFeatures(IPlayerActions features) {
    this.features = features;
    redHand.setFeatures(features);
    blueHand.setFeatures(features);
    gridView.setFeatures(features);

    // Initial hand update
    boolean isRedCurrentPlayer = model.getCurrentPlayer().getColor() == PlayerColor.RED;

    redHand.updateHand(model.getRedHand(), isRedCurrentPlayer);
    blueHand.updateHand(model.getBlueHand(), !isRedCurrentPlayer);
  }

  @Override
  public int getSelectedCardIndex() {
    CardView selectedCardView = getSelectedCardView();

    if (selectedCardView != null) {
      return selectedCardView.getIndex();
    } else {
      throw new IllegalStateException("No card selected.");
    }
  }

  @Override
  public CardView getSelectedCardView() {
    PlayerColor currentPlayerColor = model.getCurrentPlayer().getColor();
    CardView selectedCardView = null;

    if (currentPlayerColor.equals(PlayerColor.RED)) {
      selectedCardView = (CardView) redHand.getSelectedCardView();
    } else if (currentPlayerColor.equals(PlayerColor.BLUE)) {
      selectedCardView = (CardView) blueHand.getSelectedCardView();
    }
    return selectedCardView;
  }

  @Override
  public void displayGameWinner() {
    JOptionPane.showMessageDialog(
            this,
         "Winner: " +  model.determineWinner().toString() +
            "Score: \n" +
            "Red: " + model.getRedScore() + "\n" +
            "Blue: " + model.getBlueScore(),
            "Game over!",
            JOptionPane.INFORMATION_MESSAGE);
  }

  @Override
  public void displayException(Exception e) {
    JOptionPane.showMessageDialog(
            this,
            e.getMessage(),
            "Error",
            JOptionPane.ERROR_MESSAGE);
  }

  public void deselectCard() {
    redHand.deselectCard();
    blueHand.deselectCard();
  }
}
