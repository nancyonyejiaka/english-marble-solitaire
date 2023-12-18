import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link TriangleSolitaireModel}.
 */
public class TriangleSolitaireModelTest {
  MarbleSolitaireModelState.SlotState marble = MarbleSolitaireModelState.SlotState.Marble;
  MarbleSolitaireModelState.SlotState empty = MarbleSolitaireModelState.SlotState.Empty;
  MarbleSolitaireModel fiveTri;
  MarbleSolitaireModel fiveTriOC;
  MarbleSolitaireModel sevenTriOC;
  MarbleSolitaireModel sevenTri;

  /**
   * Set up the initial conditions for testing.
   */
  @Before
  public void setUp() {
    this.fiveTri = new TriangleSolitaireModel();
    this.sevenTri = new TriangleSolitaireModel(7);
    this.fiveTriOC = new TriangleSolitaireModel(2, 0);
    this.sevenTriOC = new TriangleSolitaireModel(7, 1, 1);
  }

  /**
   * Test that the empty, default constructor properly initializes marble slots.
   */
  @Test
  public void emptyConstructorProperlyInitializes() {
    assertEquals(14, fiveTri.getScore());
    assertEquals(empty, fiveTri.getSlotAt(0, 0));
  }

  /**
   * Test that the one parameter constructor properly initializes marble slots.
   */
  @Test
  public void oneParamConstructorProperlyInitializes() {
    assertEquals(27, sevenTri.getScore());
    assertEquals(empty, sevenTri.getSlotAt(0, 0));
  }

  /**
   * Test that the one parameter constructor properly initializes marble slots.
   */
  @Test
  public void twoParamConstructorProperlyInitializes() {
    assertEquals(14, fiveTriOC.getScore());
    assertEquals(marble, fiveTriOC.getSlotAt(0, 0));
    assertEquals(empty, fiveTriOC.getSlotAt(2, 0));
  }

  /**
   * Test that move method disallows moving up right by two slots to an occupied position.
   */
  @Test(expected = IllegalArgumentException.class)
  public void moveDisallowsTwoSlotUnoccupied() {
    MarbleSolitaireModel testMod = new TriangleSolitaireModel();
    testMod.move(4, 0, 2, 0);
    assertEquals(13, testMod.getScore());
  }

  /**
   * Test that move method disallows moving up right by two slots to an unoccupied position.
   */
  @Test(expected = IllegalArgumentException.class)
  public void moveDisallowsTwoSlotOccupied() {
    MarbleSolitaireModel testMod = new TriangleSolitaireModel(3, 3);
    testMod.move(5, 1, 3, 3);
    assertEquals(13, testMod.getScore());
  }

  /**
   * Test that moves disallows moving from an empty position.
   */
  @Test(expected = IllegalArgumentException.class)
  public void moveDisallowsMovesFromEmptySlot() {
    fiveTri.move(2, 0, 0, 0);
    fiveTri.move(3, 0, 1, 0);
  }

  /**
   * Test that move disallows jumping over an empty position.
   */
  @Test(expected = IllegalArgumentException.class)
  public void moveDisallowsJumpingEmpty() {
    MarbleSolitaireModel testMod = new TriangleSolitaireModel(1, 1);
    testMod.move(2, 2, 0, 0);
    assertEquals(13, testMod.getScore());
  }

  /**
   * Test that move method disallows moving up right by two slots to an unoccupied position.
   */
  @Test(expected = IllegalArgumentException.class)
  public void moveDisallowsTwoSlotDiagonal() {
    MarbleSolitaireModel testMod = new TriangleSolitaireModel(7,3, 3);
    testMod.move(5, 1, 3, 3);
    assertEquals(13, testMod.getScore());
  }

  /**
   * Test that move methods allows moving up to the right by one slot.
   */
  @Test
  public void moveAllowsMovingUpRightOneSlot() {
    MarbleSolitaireModel testMod = new TriangleSolitaireModel(5, 2, 1);
    testMod.move(4, 3, 2, 1);
    assertEquals(13, testMod.getScore());
  }

  /**
   * Test that move allows moves in upper left direction.
   */
  @Test
  public void moveAllowsUpperLeftJump() {
    MarbleSolitaireModel testMod = new TriangleSolitaireModel(2, 2);
    testMod.move(4, 2, 2, 2);
    assertEquals(13, testMod.getScore());
  }

  /**
   * Test move method allows moving down diagonally one slot.
   */
  @Test
  public void moveAllowsMovingDownRightOneSlotDiagonally() {
    MarbleSolitaireModel testMod =  new TriangleSolitaireModel(5, 4, 4);
    testMod.move(2, 2, 4, 4);
    assertEquals(13, testMod.getScore());
  }

  /**
   * Tests that move allows moves from the same column to a row that is two slots higher.
   */
  @Test
  public void moveAllowsUpTwoRowsSameCol() {
    MarbleSolitaireModel testMod =  new TriangleSolitaireModel(2, 2);
    testMod.move(4, 2, 2, 2);
    assertEquals(13, testMod.getScore());
  }

  /**
   * Test that move allows from same column to row that is two slots lower.
   */
  @Test
  public void moveAllowsDownTwoRowsSameCol() {
    MarbleSolitaireModel testMod =  new TriangleSolitaireModel(4, 2);
    testMod.move(2, 2, 4, 2);
    assertEquals(13, testMod.getScore());
  }

  /**
   * Test that move allows from same row to column that is two slots to the left.
   */
  @Test
  public void moveAllowsSameRowTwoColsLeft() {
    MarbleSolitaireModel testMod = new TriangleSolitaireModel(3, 0);
    testMod.move(3, 2, 3, 0);
    assertEquals(13, testMod.getScore());
  }

  /**
   * Test that move disallows moves from a position that is not within the bounds of the board.
   */
  @Test(expected = IllegalArgumentException.class)
  public void moveDisallowsFromPositionNotWithinBoundsOfBoard() {
    sevenTriOC.move(-1, -1, 1, 1);
  }

  /**
   * Test that move disallows moves to a position that is not within the bounds of the board.
   */
  @Test(expected = IllegalArgumentException.class)
  public void moveDisallowsMovesToPositionNotWithinBoundsOfBoard() {
    fiveTri.move(1, 1, -1, -1);
  }

  /**
   * Test that move allows from same row to column that is two slots to the right.
   */
  @Test
  public void moveAllowsSameRowTwoColsRight() {
    fiveTriOC.move(2, 2, 2, 0);
    assertEquals(13, fiveTriOC.getScore());
  }

  /**
   * Test that move allows moves in lower right direction.
   */
  @Test
  public void moveAllowsLowerRightJump() {
    MarbleSolitaireModel testMod = new TriangleSolitaireModel(3, 2);
    testMod.move(1, 0, 3, 2);
    assertEquals(13, testMod.getScore());
  }

  /**
   * Test that getScore method works properly.
   */
  @Test
  public void getScoreWorks() {
    assertEquals(14, fiveTri.getScore());
    assertEquals(14, fiveTriOC.getScore());
    assertEquals(20, new TriangleSolitaireModel(6).getScore());
    assertEquals(27, sevenTri.getScore());
    assertEquals(35, new TriangleSolitaireModel(8).getScore());

    fiveTri.move(2, 2, 0,0);
    assertEquals(13, fiveTri.getScore());
  }

  /**
   * Test that gameOver method works.
   */
  @Test
  public void gameOverWorks() {
    assertFalse(fiveTri.isGameOver());
    fiveTri.move(2, 2, 0 ,0);
    fiveTri.move(4, 4, 2, 2);
    fiveTri.move(3, 1, 1, 1);
    assertFalse(fiveTri.isGameOver());
    fiveTri.move(1, 1, 3, 3);
    fiveTri.move(3, 3, 3, 1);
    fiveTri.move(4, 1, 2, 1);
    assertFalse(fiveTri.isGameOver());
    fiveTri.move(4, 3, 4, 1);
    fiveTri.move(2, 0, 2, 2);
    fiveTri.move(0, 0, 2, 0);
    assertFalse(fiveTri.isGameOver());
    fiveTri.move(3, 0, 1, 0);
    fiveTri.move(4,0, 4, 2);
    assertTrue(fiveTri.isGameOver());

  }
}