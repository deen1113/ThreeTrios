package view;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;

import controller.IPlayerActions;
import model.ICard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

/**
 * This class implements the hand's view.
 */
public class HandView extends JPanel implements IHandView {

  private IPlayerActions features;
  private CardView selectedCardView;
  private final Color color;
  private boolean isCurrentPlayer;

  /**
   * This is the constructor for the HandView. It takes a color for
   * which hand it is creating, red or blue. It also takes the hand
   * in order to access each card in the hand, which it then sends to CardView.
   *
   * @param color the player's color
   * @param hand  the player's hand
   */
  public HandView(Color color, List<ICard> hand) {
    this.color = color;
    setPreferredSize(new Dimension(100, 50));
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    // Initialize the hand without interaction until set
    updateHand(hand, false);
  }

  @Override
  public void updateHand(List<ICard> hand, boolean isCurrentPlayer) {
    this.isCurrentPlayer = isCurrentPlayer;

    // Remove all existing card views
    removeAll();
    selectedCardView = null; // Reset selected card when updating the hand

    for (int i = 0; i < hand.size(); i++) {
      ICard card = hand.get(i);
      CardView cardView = new CardView(card, color, i);
      cardView.setAlignmentX(Component.CENTER_ALIGNMENT);

      if (isCurrentPlayer) {
        cardView.setEnabled(true);
        cardView.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            handleCardClick(cardView);
          }
        });
      } else {
        cardView.setEnabled(false);
        // Remove mouse listeners to prevent interaction
        for (MouseListener ml : cardView.getMouseListeners()) {
          cardView.removeMouseListener(ml);
        }
      }

      add(cardView);
    }

    revalidate();
    repaint();
  }

  private void handleCardClick(CardView cardView) {
    if (!isCurrentPlayer) {
      // Ignore clicks if not current player's hand
      return;
    }

    if (selectedCardView != null) {
      selectedCardView.setBorder(null);
    }
    if (selectedCardView == cardView) {
      selectedCardView = null;
      System.out.println("Deselected card in " + (color == Color.PINK ? "Red" : "Blue") + " hand.");
      return;
    }

    selectedCardView = cardView;
    selectedCardView.setBorder(new LineBorder(Color.GREEN, 3));

    if (features != null) {
      features.onCardSelected();
    } else {
      System.err.println("Features is null in HandView.handleCardClick");
    }
    System.out.println("Selected card: " + selectedCardView.getName());
  }

  @Override
  public ICardView getSelectedCardView() {
    return selectedCardView;
  }

  @Override
  public void refresh() {
    repaint();
  }

  @Override
  public void setFeatures(IPlayerActions features) {
    this.features = features;
  }

  @Override
  public void deselectCard() {
    if (selectedCardView != null) {
      selectedCardView.setBorder(null);
      selectedCardView = null;
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
  }
}
