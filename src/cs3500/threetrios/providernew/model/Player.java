package cs3500.threetrios.providernew.model;

import java.util.List;

import cs3500.threetrios.providernew.controller.PlayerAction;

public class Player implements IPlayer {
  private PlayerColor color;
  private List<Card> hand;
  private PlayerAction action;

  public Player(PlayerColor color, List<Card> hand) {
    this.color = color;
    colorHand(hand);
    this.hand = hand;
  }

  @Override
  public void colorHand(List hand) {
    for (Card card : hand) {
      card.setColor(color);
    }
  }

  @Override
  public Card takeCard(int index) {
    return hand.get(index);
  }

  @Override
  public List getHand() {
    return this.hand;
  }

  @Override
  public PlayerColor getColor() {
    return this.color;
  }

  @Override
  public void setColor(PlayerColor color) {
    this.color = color;
  }

  @Override
  public void addListener(PlayerAction action) {
    this.action = action;
  }

  @Override
  public void callMove() {
    // to be implemented
  }
}
