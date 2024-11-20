package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.geom.Path2D;

import javax.swing.JPanel;

import model.Direction;
import model.ICard;

/**
 * This class implements the view for each individual card.
 */
public class CardView extends JPanel {
  private final Color color;
  private final ICard card;
  private final int index;
  private int width = 100;
  private int height = 50;
  private String northText;
  private String southText;
  private String eastText;
  private String westText;

  /**
   * This constructor sets the number from each direction of a card to a variable.
   * If the number is 10, it is converted back into an A.
   *
   * @param card  the card
   * @param color the player's color the card belongs to
   */
  public CardView(ICard card, Color color, int index) {
    this.color = color;
    this.card = card;
    this.index = index;
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

  private Path2D createCard() {
    Path2D.Double path = new Path2D.Double();
    path.moveTo(0, 0);
    path.lineTo(width, 0);
    path.lineTo(width, height);
    path.lineTo(0, height);
    path.closePath();
    return path;
  }

  public int getIndex() {
    return index;
  }

  public ICard getCard() {
    return this.card;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    width = getWidth();
    height = getHeight();
    Path2D card = createCard();

    g2d.setColor(color);
    g2d.fill(card);

    g2d.setColor(Color.BLACK);
    g2d.draw(card);

    int fontSize = Math.min(width, height) / 3;
    g2d.setFont(new Font("Arial", Font.BOLD, fontSize));
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
