package view;

import java.awt.*;

import javax.swing.*;

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
  private final IReadonlyThreeTriosModel model;
  private final GridWithHintDecorator gridDecorator;
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
    gridDecorator = new GridWithHintDecorator(model, gridView);
    add(gridDecorator, BorderLayout.CENTER);

    // for toggling hints
    add(createHintButtonPanel(), BorderLayout.SOUTH);
  }

  private JPanel createHintButtonPanel() {
    JPanel hintButtons = new JPanel();
    hintButtons.setLayout(new FlowLayout());

    JButton hintButton = new JButton("Toggle Hints");
    hintButton.addActionListener(e -> features.onHintButtonClicked());
    hintButtons.add(hintButton);

    return hintButtons;
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

    gridDecorator.refresh();

    revalidate();
    repaint();
  }

  @Override
  public boolean isHintsEnabled() {
    return gridDecorator.isHintsEnabled();
  }

  @Override
  public void updateHints(ICard selectedCard) {
    gridDecorator.updateHints(selectedCard);
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
    CardView selectedCardView = (CardView) getSelectedCardView();

    if (selectedCardView != null) {
      return selectedCardView.getIndex();
    } else {
      throw new IllegalStateException("No card selected.");
    }
  }

  @Override
  public ICardView getSelectedCardView() {
    PlayerColor currentPlayerColor = model.getCurrentPlayer().getColor();
    ICardView selectedCardView = null;

    if (currentPlayerColor.equals(PlayerColor.RED)) {
      selectedCardView = redHand.getSelectedCardView();
    } else if (currentPlayerColor.equals(PlayerColor.BLUE)) {
      selectedCardView = blueHand.getSelectedCardView();
    }
    return selectedCardView;
  }

  @Override
  public void displayGameWinner() {
    if (model.determineWinner() != null) {
      JOptionPane.showMessageDialog(
              this,
              "Winner: " + model.determineWinner().toString() +
                      "Score: \n" +
                      "Red: " + model.getRedScore() + "\n" +
                      "Blue: " + model.getBlueScore(),
              "Game over!",
              JOptionPane.INFORMATION_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(
              this,
              "Tie!" +
                      "Score: \n" +
                      "Red: " + model.getRedScore() + "\n" +
                      "Blue: " + model.getBlueScore(),
              "Game over!",
              JOptionPane.INFORMATION_MESSAGE);
    }
  }

  @Override
  public void toggleHints(PlayerColor playerColor, boolean enable) {
    if (playerColor == PlayerColor.RED) {
      gridDecorator.setHints(enable);
    } else if (playerColor == PlayerColor.BLUE) {
      gridDecorator.setHints(enable);
    }
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
