package view;

import java.awt.*;

import javax.swing.JPanel;

import model.Card;
import model.Direction;

public class CardView extends JPanel {
  private final Color color;
  private final int width = 100;
  private final int height = 50;
  private String northText;
  private String southText;
  private String eastText;
  private String westText;

  public CardView(Card card, Color color) {
    this.color = color;
    northText = String.valueOf(card.getAttack(Direction.NORTH));
    if (northText.equals("10")) {
      northText = "A";
    }

    southText = String.valueOf(card.getAttack(Direction.SOUTH));
    if (southText.equals("10")) {
      southText = "A";
    }

    eastText = String.valueOf(card.getAttack(Direction.EAST));
    if (eastText.equals("10")) {
      eastText = "A";
    }

    westText = String.valueOf(card.getAttack(Direction.WEST));
    if (westText.equals("10")) {
      westText = "A";
    }
    setPreferredSize(new Dimension(width, height));
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    g2d.setColor(color);
    g2d.fillRect(0, 0, width, height);
    g2d.setColor(Color.BLACK);
    g2d.drawRect(0, 0, width - 1, height - 1);
    g2d.setFont(new Font("Arial", Font.BOLD, 15));
    FontMetrics metrics = g2d.getFontMetrics(g2d.getFont());

    // Draw North
    int northX = (width - metrics.stringWidth(northText)) / 2;
    int northY = metrics.getAscent() + 5;
    g2d.drawString(northText, northX, northY);

    // Draw South
    int southX = (width - metrics.stringWidth(southText)) / 2;
    int southY = height - 5;
    g2d.drawString(southText, southX, southY);

    // Draw East
    int eastX = (width - 15);
    int eastY = (height + metrics.getAscent()) / 2;
    g2d.drawString(eastText, eastX, eastY);

    // Draw West
    int westX = 5;
    int westY = (height + metrics.getAscent()) / 2;
    g2d.drawString(westText, westX, westY);
  }
}
