package strategies;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import model.Card;
import model.PlayerColor;
import model.MockThreeTriosModel;
import strategy.Coord;
import strategy.Corners;

import org.junit.Test;

/**
 * Test class for Corners strategy.
 */
public class TestCorners {

  /**
   * Tests the chooseMove method of Corners strategy.
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

    mockModel.setValidMove(0, 0, true);
    mockModel.setValidMove(0, 2, true);
    mockModel.setValidMove(2, 0, true);
    mockModel.setValidMove(2, 2, true);

    Corners strategy = new Corners();

    Coord move = strategy.chooseMove(mockModel, PlayerColor.RED);

    assertEquals(0, move.getRow());
    assertEquals(0, move.getCol());
    assertEquals(0, move.getCardIndex());
  }

  /**
   * Tests the chooseMove method when no corners are available.
   */
  @Test
  public void testNoCornersAvailable() {
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

    // No valid corner moves
    mockModel.setValidMove(1, 1, true);

    Corners strategy = new Corners();

    Coord move = strategy.chooseMove(mockModel, PlayerColor.RED);

    // Should return null or handle accordingly
    assertEquals(null, move);
  }
}
