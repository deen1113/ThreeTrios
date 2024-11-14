package view;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;

import model.Card;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * This class implements the hand's view.
 */
public class HandView extends JPanel {

  private CardView selectedCardView;

  /**
   * This is the constructor for the HandView. It takes a color for
   * which hand it is creating, red or blue. It also takes the hand
   * in order to access each card in the hand, which it then sends to CardView.
   *
   * @param color the player's color
   * @param hand  the player's hand
   */
  public HandView(Color color, List<Card> hand) {
    setPreferredSize(new Dimension(100, 50));
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    for (Card card : hand) {
      CardView cardView = new CardView(card, color);
      cardView.setAlignmentX(Component.CENTER_ALIGNMENT);

      cardView.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          handleCardClick(cardView);
        }
      });

      add(cardView);
    }
  }

  private void handleCardClick(CardView cardView) {
    if (selectedCardView != null) {
      selectedCardView.setBorder(null);
    }
    if (selectedCardView == cardView) {
      selectedCardView = null;
      return;
    }

    selectedCardView = cardView;
    selectedCardView.setBorder(new LineBorder(Color.GREEN, 3));

    System.out.println("Selected card: " + selectedCardView.getName());
  }

  /**
   * This method gets and returns the selectCardView.
   *
   * @return
   */
  public CardView getSelectedCardView() {
    if (selectedCardView != null) {
      return selectedCardView;
    }
    return null;
  }


  /**
   * This method removes the selected card view.
   */
  public void removeSelectedCardView() {
    if (selectedCardView != null) {
      remove(selectedCardView);
      selectedCardView = null;
      revalidate();
      repaint();
    }
  }



  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
  }
}
