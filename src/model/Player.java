package model;

import java.util.List;

import javax.swing.*;

public class Player implements IPlayer {
  private final PlayerColor color;
  private final List<ICard> hand;

  public Player(PlayerColor color, List<ICard> hand) {
    this.color = color;
    this.hand = hand;
  }

  public PlayerColor getColor() {
    return color;
  }

  public List<ICard> getHand() {
    return hand;
  }
}
