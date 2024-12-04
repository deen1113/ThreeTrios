package cs3500.threetrios.providernew.model;

import java.util.List;

import cs3500.threetrios.providernew.controller.ModelWatcher;

public class ThreeTriosProviderModel implements TrioModel {
  @Override
  public void placeCard(int handIndex, int posY, int posX) {

  }

  @Override
  public void initializeGame(String mapName, String deckName, boolean shuffle, IPlayer redPlayer, IPlayer bluePlayer) {

  }

  @Override
  public void addListener(ModelWatcher observer) {

  }

  @Override
  public TrioMap getGrid() {
    return null;
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public IPlayer getWinner() {
    return null;
  }

  @Override
  public IPlayer getTurn() {
    return null;
  }

  @Override
  public IPlayer getRedPlayer() {
    return null;
  }

  @Override
  public IPlayer getBluePlayer() {
    return null;
  }

  @Override
  public int getScore(IPlayer player) {
    return 0;
  }

  @Override
  public int getFlipTotal(IPlayer player, Card card, int x, int y) {
    return 0;
  }

  @Override
  public List getPlayerHand(IPlayer player) {
    return List.of();
  }

  @Override
  public Cell getTile(int x, int y) {
    return null;
  }

  @Override
  public int getGridHeight() {
    return 0;
  }

  @Override
  public int getGridWidth() {
    return 0;
  }
}
