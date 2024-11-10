package view;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import model.Card;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Graphics;
import java.util.List;

public class HandView extends JPanel {
  int boxHeight = 50;

  public HandView(Color color, List<Card> hand) {
    setPreferredSize(new Dimension(100, hand.size() * boxHeight));
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    setBackground(color);
    for (Card card : hand) {
      CardView cardView = new CardView(card);
      cardView.setAlignmentX(Component.CENTER_ALIGNMENT);
      add(cardView);
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
  }
}
