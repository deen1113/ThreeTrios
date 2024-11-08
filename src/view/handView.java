package view;

import javax.swing.JPanel;
import model.Card;

import java.awt.*;
import java.util.List;
import model.PlayerColor;

public class handView extends JPanel {
  private final Color color;
  private final List<Card> hand;

  public handView(Color color, List<Card> hand) {
    this.color = color;
    this.hand = hand;
    setPreferredSize(new Dimension(100, 400));
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(color);
    g2d.fillRect(0, 0, getWidth(), getHeight());

    g2d.setColor(Color.BLACK);
    for (int i = 0; i < hand.size(); i++) {
      g2d.drawString(hand.get(i).toString(), 10, (i + 1) * 30);
    }
  }
}
