package view;

import javax.swing.JPanel;
import javax.swing.BoxLayout;

import model.Card;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Graphics;
import java.util.List;

/**
 * This class implements the hand's view.
 */
public class HandView extends JPanel {

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
      add(cardView);
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
  }
}
