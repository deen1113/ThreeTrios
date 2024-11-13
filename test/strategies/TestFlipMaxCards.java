package strategies;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import model.Card;
import model.Direction;
import model.MockThreeTriosModel;
import model.PlayerColor;
import strategy.Coord;
import strategy.FlipMaxCards;

import org.junit.Test;

/**
 * Test class for FlipMaxCards strategy.
 */
public class TestFlipMaxCards {

  /**
   * Tests the chooseMove method of FlipMaxCards strategy.
   */
  @Test
  public void testChooseMove() {
    MockThreeTriosModel mockModel = new MockThreeTriosModel(3, 3);

    List<Integer> card1Values = new ArrayList<>();
    card1Values.add(5);
    card1Values.add(5);
    card1Values.add(5);
    card1Values.add(5);
    List<Integer> card2Values = new ArrayList<>();
    card2Values.add(3);
    card2Values.add(3);
    card2Values.add(3);
    card2Values.add(3);
    Card card1 = new Card("card1", card1Values, PlayerColor.RED);
    Card card2 = new Card("card2", card2Values, PlayerColor.BLUE);

    List<Card> redHand = new ArrayList<>();
    redHand.add(card1);
    redHand.add(card2);

    mockModel.setRedHand(redHand);

    mockModel.setValidMove(0, 0, true);
    mockModel.setValidMove(1, 1, true);

    mockModel.setFlippedCardsCount(card1, 0, 0, 2);
    mockModel.setFlippedCardsCount(card1, 1, 1, 3);

    mockModel.setFlippedCardsCount(card2, 0, 0, 1);
    mockModel.setFlippedCardsCount(card2, 1, 1, 2);

    FlipMaxCards strategy = new FlipMaxCards();

    Coord move = strategy.chooseMove(mockModel, PlayerColor.RED);

    assertEquals(1, move.getRow());
    assertEquals(1, move.getCol());
    assertEquals(0, move.getCardIndex());

    mockModel.setFlippedCardsCount(card1, 0, 0, 3);
    mockModel.setFlippedCardsCount(card1, 1, 1, 3);

    move = strategy.chooseMove(mockModel, PlayerColor.RED);

    assertEquals(0, move.getRow());
    assertEquals(0, move.getCol());
    assertEquals(0, move.getCardIndex());
  }

  /**
   * Tests the chooseMove method when no valid moves are available.
   */
  @Test
  public void testNoValidMoves() {
    MockThreeTriosModel mockModel = new MockThreeTriosModel(3, 3);
    List<Integer> card1Values = new ArrayList<>();
    card1Values.add(5);
    card1Values.add(5);
    card1Values.add(5);
    card1Values.add(5);
    Card card1 = new Card("card1", card1Values, PlayerColor.RED);
    List<Card> redHand = new ArrayList<>();
    redHand.add(card1);
    mockModel.setRedHand(redHand);

    FlipMaxCards strategy = new FlipMaxCards();

    Coord move = strategy.chooseMove(mockModel, PlayerColor.RED);

    assertEquals(0, move.getRow());
    assertEquals(0, move.getCol());
    assertEquals(0, move.getCardIndex());
  }
}
