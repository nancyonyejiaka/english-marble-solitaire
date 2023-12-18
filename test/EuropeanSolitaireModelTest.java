import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Tests for {@link EuropeanSolitaireModel}.
 */
public class EuropeanSolitaireModelTest {
  MarbleSolitaireModelState.SlotState marble = MarbleSolitaireModelState.SlotState.Marble;
  MarbleSolitaireModelState.SlotState empty = MarbleSolitaireModelState.SlotState.Empty;
  MarbleSolitaireModelState.SlotState invalid = MarbleSolitaireModelState.SlotState.Invalid;
  MarbleSolitaireModel threeEuro;
  MarbleSolitaireModel fiveEuro;
  MarbleSolitaireModel threeEuroOC;
  MarbleSolitaireModel fiveEuroOC;

  /**
   * Initializes instances of european marble solitaire for testing.
   */
  @Before
  public void setUp() {
    this.threeEuro = new EuropeanSolitaireModel();
    this.fiveEuro = new EuropeanSolitaireModel(5);
    this.threeEuroOC = new EuropeanSolitaireModel(1, 1);
    this.fiveEuroOC = new EuropeanSolitaireModel(5, 3, 2);
  }

  /**
   * Test that the empty constructor for a European Marble Solitaire Model properly
   * constructs the board.
   */
  @Test
  public void emptyEuroConstructorWorks() {
    assertEquals(36, threeEuro.getScore());

    assertEquals(invalid, threeEuro.getSlotAt(0, 0));
    assertEquals(invalid, threeEuro.getSlotAt(0, 1));
    assertEquals(invalid, threeEuro.getSlotAt(1, 0));

    assertEquals(invalid, threeEuro.getSlotAt(0, 5));
    assertEquals(invalid, threeEuro.getSlotAt(0, 6));
    assertEquals(invalid, threeEuro.getSlotAt(1, 6));

    assertEquals(invalid, threeEuro.getSlotAt(5, 0));
    assertEquals(invalid, threeEuro.getSlotAt(6, 0));
    assertEquals(invalid, threeEuro.getSlotAt(6, 1));

    assertEquals(invalid, threeEuro.getSlotAt(5, 6));
    assertEquals(invalid, threeEuro.getSlotAt(6, 5));
    assertEquals(invalid, threeEuro.getSlotAt(6, 6));

    assertEquals(empty, threeEuro.getSlotAt(3, 3));

    assertEquals(marble, threeEuro.getSlotAt(0, 2));
    assertEquals(marble, threeEuro.getSlotAt(3, 2));
  }

  /**
   * Test that the constructor for a European marble solitaire board that takes the side
   * length properly constructs the board.
   */
  @Test
  public void oneParamEuroConstructorWorks() {
    assertEquals(128, fiveEuro.getScore());

    assertEquals(invalid, fiveEuro.getSlotAt(0, 0));
    assertEquals(invalid, fiveEuro.getSlotAt(0, 1));
    assertEquals(invalid, fiveEuro.getSlotAt(0, 2));
    assertEquals(invalid, fiveEuro.getSlotAt(0, 3));

    assertEquals(invalid, fiveEuro.getSlotAt(1, 0));
    assertEquals(invalid, fiveEuro.getSlotAt(1, 1));
    assertEquals(invalid, fiveEuro.getSlotAt(1, 2));

    assertEquals(invalid, fiveEuro.getSlotAt(2, 0));
    assertEquals(invalid, fiveEuro.getSlotAt(2, 1));

    assertEquals(invalid, fiveEuro.getSlotAt(3, 0));

    assertEquals(invalid, fiveEuro.getSlotAt(0, 9));
    assertEquals(invalid, fiveEuro.getSlotAt(0, 10));
    assertEquals(invalid, fiveEuro.getSlotAt(0, 11));
    assertEquals(invalid, fiveEuro.getSlotAt(0, 12));

    assertEquals(invalid, fiveEuro.getSlotAt(1, 10));
    assertEquals(invalid, fiveEuro.getSlotAt(1, 11));
    assertEquals(invalid, fiveEuro.getSlotAt(1, 12));

    assertEquals(invalid, fiveEuro.getSlotAt(2, 11));
    assertEquals(invalid, fiveEuro.getSlotAt(2, 12));

    assertEquals(invalid, fiveEuro.getSlotAt(3, 12));

    assertEquals(invalid, fiveEuro.getSlotAt(12, 0));
    assertEquals(invalid, fiveEuro.getSlotAt(12, 1));
    assertEquals(invalid, fiveEuro.getSlotAt(12, 2));
    assertEquals(invalid, fiveEuro.getSlotAt(12, 3));

    assertEquals(invalid, fiveEuro.getSlotAt(11, 0));
    assertEquals(invalid, fiveEuro.getSlotAt(11, 1));
    assertEquals(invalid, fiveEuro.getSlotAt(11, 2));

    assertEquals(invalid, fiveEuro.getSlotAt(10, 0));
    assertEquals(invalid, fiveEuro.getSlotAt(10, 1));

    assertEquals(invalid, fiveEuro.getSlotAt(9, 0));

    assertEquals(invalid, fiveEuro.getSlotAt(12, 9));
    assertEquals(invalid, fiveEuro.getSlotAt(12, 10));
    assertEquals(invalid, fiveEuro.getSlotAt(12, 11));
    assertEquals(invalid, fiveEuro.getSlotAt(12, 12));

    assertEquals(invalid, fiveEuro.getSlotAt(11, 10));
    assertEquals(invalid, fiveEuro.getSlotAt(11, 11));
    assertEquals(invalid, fiveEuro.getSlotAt(11, 12));

    assertEquals(invalid, fiveEuro.getSlotAt(10, 11));
    assertEquals(invalid, fiveEuro.getSlotAt(10, 12));

    assertEquals(invalid, fiveEuro.getSlotAt(9, 12));

    assertEquals(empty, fiveEuro.getSlotAt(6, 6));
  }

  /**
   * Test that the constructor for a European marble solitaire board that takes the
   * row and column values properly constructs the board.
   */
  @Test
  public void twoParamEuroConstructorWorks() {
    assertEquals(36, threeEuroOC.getScore());

    assertEquals(invalid, threeEuroOC.getSlotAt(0, 0));
    assertEquals(invalid, threeEuroOC.getSlotAt(0, 1));
    assertEquals(invalid, threeEuroOC.getSlotAt(1, 0));

    assertEquals(invalid, threeEuroOC.getSlotAt(0, 5));
    assertEquals(invalid, threeEuroOC.getSlotAt(0, 6));
    assertEquals(invalid, threeEuroOC.getSlotAt(1, 6));

    assertEquals(invalid, threeEuroOC.getSlotAt(5, 0));
    assertEquals(invalid, threeEuroOC.getSlotAt(6, 0));
    assertEquals(invalid, threeEuroOC.getSlotAt(6, 1));

    assertEquals(invalid, threeEuroOC.getSlotAt(5, 6));
    assertEquals(invalid, threeEuroOC.getSlotAt(6, 5));
    assertEquals(invalid, threeEuroOC.getSlotAt(6, 6));

    assertEquals(marble, threeEuroOC.getSlotAt(3, 3));
    assertEquals(empty, threeEuroOC.getSlotAt(1, 1));
  }

  /**
   * Test that the constructor for a European marble solitaire board that takes the
   * row and column values properly constructs the board.
   */
  @Test
  public void threeParamEuroConstructorWorks() {
    assertEquals(128, fiveEuroOC.getScore());

    assertEquals(invalid, fiveEuroOC.getSlotAt(0, 0));
    assertEquals(invalid, fiveEuroOC.getSlotAt(0, 1));
    assertEquals(invalid, fiveEuroOC.getSlotAt(0, 2));
    assertEquals(invalid, fiveEuroOC.getSlotAt(0, 3));

    assertEquals(invalid, fiveEuroOC.getSlotAt(1, 0));
    assertEquals(invalid, fiveEuroOC.getSlotAt(1, 1));
    assertEquals(invalid, fiveEuroOC.getSlotAt(1, 2));

    assertEquals(invalid, fiveEuroOC.getSlotAt(2, 0));
    assertEquals(invalid, fiveEuroOC.getSlotAt(2, 1));

    assertEquals(invalid, fiveEuroOC.getSlotAt(3, 0));

    assertEquals(invalid, fiveEuroOC.getSlotAt(0, 9));
    assertEquals(invalid, fiveEuroOC.getSlotAt(0, 10));
    assertEquals(invalid, fiveEuroOC.getSlotAt(0, 11));
    assertEquals(invalid, fiveEuroOC.getSlotAt(0, 12));

    assertEquals(invalid, fiveEuroOC.getSlotAt(1, 10));
    assertEquals(invalid, fiveEuroOC.getSlotAt(1, 11));
    assertEquals(invalid, fiveEuroOC.getSlotAt(1, 12));

    assertEquals(invalid, fiveEuroOC.getSlotAt(2, 11));
    assertEquals(invalid, fiveEuroOC.getSlotAt(2, 12));

    assertEquals(invalid, fiveEuroOC.getSlotAt(3, 12));

    assertEquals(invalid, fiveEuroOC.getSlotAt(12, 0));
    assertEquals(invalid, fiveEuroOC.getSlotAt(12, 1));
    assertEquals(invalid, fiveEuroOC.getSlotAt(12, 2));
    assertEquals(invalid, fiveEuroOC.getSlotAt(12, 3));

    assertEquals(invalid, fiveEuroOC.getSlotAt(11, 0));
    assertEquals(invalid, fiveEuroOC.getSlotAt(11, 1));
    assertEquals(invalid, fiveEuroOC.getSlotAt(11, 2));

    assertEquals(invalid, fiveEuroOC.getSlotAt(10, 0));
    assertEquals(invalid, fiveEuroOC.getSlotAt(10, 1));

    assertEquals(invalid, fiveEuroOC.getSlotAt(9, 0));

    assertEquals(invalid, fiveEuroOC.getSlotAt(12, 9));
    assertEquals(invalid, fiveEuroOC.getSlotAt(12, 10));
    assertEquals(invalid, fiveEuroOC.getSlotAt(12, 11));
    assertEquals(invalid, fiveEuroOC.getSlotAt(12, 12));

    assertEquals(invalid, fiveEuroOC.getSlotAt(11, 10));
    assertEquals(invalid, fiveEuroOC.getSlotAt(11, 11));
    assertEquals(invalid, fiveEuroOC.getSlotAt(11, 12));

    assertEquals(invalid, fiveEuroOC.getSlotAt(10, 11));
    assertEquals(invalid, fiveEuroOC.getSlotAt(10, 12));

    assertEquals(invalid, fiveEuroOC.getSlotAt(9, 12));

    assertEquals(marble, fiveEuroOC.getSlotAt(6, 6));
    assertEquals(empty, fiveEuroOC.getSlotAt(3, 2));
  }

  /**
   * Test that the constructor for a European marble solitaire board that takes the side
   * length throws exception for negative length.
   */
  @Test(expected = IllegalArgumentException.class)
  public void oneParamEuroConstructorDisallowsNegative() {
    new EuropeanSolitaireModel(-3);
  }

  /**
   * Test that the constructor for a European marble solitaire board that takes the side
   * length throws exception for even length.
   */
  @Test(expected = IllegalArgumentException.class)
  public void oneParamEuroConstructorDisallowsEven() {
    new EuropeanSolitaireModel(4);
  }

  /**
   * Test that the constructor for a European marble solitaire board that takes the side
   * length throws exception for negative even length.
   */
  @Test(expected = IllegalArgumentException.class)
  public void oneParamEuroConstructorDisallowsNegativeEven() {
    new EuropeanSolitaireModel(-6);
  }

  /**
   * Test that the constructor for a European marble solitaire board that takes the
   * row, and column values throws exception for negative sRow.
   */
  @Test(expected = IllegalArgumentException.class)
  public void twoParamEuroConstructorDisallowsNegativeRow() {
    new EuropeanSolitaireModel(-5, 3);
  }

  /**
   * Test that the constructor for a European marble solitaire board that takes the
   * row, and column values throws exception for negative sColumn.
   */
  @Test(expected = IllegalArgumentException.class)
  public void twoParamEuroConstructorDisallowsNegativeColumn() {
    new EuropeanSolitaireModel(6, -5);
  }

  /**
   * Test that the constructor for a European marble solitaire board that takes the
   * row, and column values throws exception for negative sRow and sColumn.
   */
  @Test(expected = IllegalArgumentException.class)
  public void twoParamEuroConstructorDisallowsNegativeRowColumn() {
    new EuropeanSolitaireModel(-2, -4);
  }

  /**
   * Test that the constructor for a European marble solitaire board that takes the
   * row, and column values throws exception for out of bounds sRow.
   */
  @Test(expected = IllegalArgumentException.class)
  public void twoParamEuroConstructorDisallowsOutOfBoundsRow() {
    new EuropeanSolitaireModel(8, 3);
  }

  /**
   * Test that the constructor for a European marble solitaire board that takes the
   * row, and column values throws exception for out of bounds sColumn.
   */
  @Test(expected = IllegalArgumentException.class)
  public void twoParamEuroConstructorDisallowsOutOfBoundsColumn() {
    new EuropeanSolitaireModel(6, 7);
  }

  /**
   * Test that the constructor for a European marble solitaire board that takes the
   * row, and column values throws exception for out of bounds sRow and sColumn.
   */
  @Test(expected = IllegalArgumentException.class)
  public void twoParamEuroConstructorDisallowsOutOfBoundsRowColumn() {
    new EuropeanSolitaireModel(0, 1);
  }

  /**
   * Test that the constructor for a European marble solitaire board that takes the side length,
   * row, and column values throws exception for negative sRow.
   */
  @Test(expected = IllegalArgumentException.class)
  public void threeParamEuroConstructorDisallowsNegativeRow() {
    new EuropeanSolitaireModel(3, -3, 3);
  }

  /**
   * Test that the constructor for a European marble solitaire board that takes the side length,
   * row, and column values throws exception for negative sColumn.
   */
  @Test(expected = IllegalArgumentException.class)
  public void threeParamEuroConstructorDisallowsNegativeColumn() {
    new EuropeanSolitaireModel(3, 3, -4);
  }

  /**
   * Test that the move method allows moves to the corner slots of the European model that
   * are not present in the english model.
   */
  @Test
  public void canMovesToNewCornerSlots() {
    MarbleSolitaireModel threeTopLeft = new EuropeanSolitaireModel(3, 1);
    threeTopLeft.move(1, 1,3, 1);
    assertEquals(empty, threeTopLeft.getSlotAt(1, 1));
    assertEquals(empty, threeTopLeft.getSlotAt(2, 1));
    assertEquals(marble, threeTopLeft.getSlotAt(3, 1));

    MarbleSolitaireModel threeTopRight = new EuropeanSolitaireModel(3, 5);
    threeTopRight.move(1, 5, 3, 5);
    assertEquals(empty, threeTopRight.getSlotAt(1, 5));
    assertEquals(empty, threeTopRight.getSlotAt(2, 5));
    assertEquals(marble, threeTopRight.getSlotAt(3, 5));

    MarbleSolitaireModel threeBottomLeft = new EuropeanSolitaireModel(3, 1);
    threeBottomLeft.move(5, 1, 3, 1);
    assertEquals(empty, threeBottomLeft.getSlotAt(5, 1));
    assertEquals(empty, threeBottomLeft.getSlotAt(4, 1));
    assertEquals(marble, threeBottomLeft.getSlotAt(3, 1));

    MarbleSolitaireModel threeBottomRight = new EuropeanSolitaireModel(3, 5);
    threeBottomRight.move(5, 5, 3, 5);
    assertEquals(empty, threeBottomRight.getSlotAt(5, 5));
    assertEquals(empty, threeBottomRight.getSlotAt(4, 5));
    assertEquals(marble, threeBottomRight.getSlotAt(3, 5));
  }

  /**
   * Test that the move method allows moves from the corner slots of the European model that
   * are not present in the english model.
   */
  @Test
  public void canMovesFromNewCornerSlots() {
    MarbleSolitaireModel threeTopLeft = new EuropeanSolitaireModel(1, 1);
    threeTopLeft.move(3, 1,1, 1);
    assertEquals(empty, threeTopLeft.getSlotAt(3, 1));
    assertEquals(empty, threeTopLeft.getSlotAt(2, 1));
    assertEquals(marble, threeTopLeft.getSlotAt(1, 1));

    MarbleSolitaireModel threeTopRight = new EuropeanSolitaireModel(1, 5);
    threeTopRight.move(3, 5, 1, 5);
    assertEquals(empty, threeTopRight.getSlotAt(3, 5));
    assertEquals(empty, threeTopRight.getSlotAt(2, 5));
    assertEquals(marble, threeTopRight.getSlotAt(1, 5));

    MarbleSolitaireModel threeBottomLeft = new EuropeanSolitaireModel(5, 1);
    threeBottomLeft.move(3, 1, 5, 1);
    assertEquals(empty, threeBottomLeft.getSlotAt(3, 1));
    assertEquals(empty, threeBottomLeft.getSlotAt(4, 1));
    assertEquals(marble, threeBottomLeft.getSlotAt(5, 1));

    MarbleSolitaireModel threeBottomRight = new EuropeanSolitaireModel(5, 5);
    threeBottomRight.move(3, 5, 5, 5);
    assertEquals(empty, threeBottomRight.getSlotAt(3, 5));
    assertEquals(empty, threeBottomRight.getSlotAt(4, 5));
    assertEquals(marble, threeBottomRight.getSlotAt(5, 5));
  }

  /**
   * Test that the move method allows moves upward over one slot.
   */
  @Test
  public void movesAllowsUpwardOneSlot() {
    threeEuro.move(5, 3, 3, 3);
    assertEquals(empty, threeEuro.getSlotAt(5, 3));
    assertEquals(empty, threeEuro.getSlotAt(4, 3));
    assertEquals(marble, threeEuro.getSlotAt(3, 3));
  }

  /**
   * Test that the move method allows moves downward over one slot.
   */
  @Test
  public void movesAllowsDownwardOneSlot() {
    fiveEuro.move(4, 6, 6, 6);
    assertEquals(empty, fiveEuro.getSlotAt(4, 6));
    assertEquals(empty, fiveEuro.getSlotAt(5, 6));
    assertEquals(marble, fiveEuro.getSlotAt(6, 6));
  }

  /**
   * Test that the move method allows moves rightward over one slot.
   */
  @Test
  public void movesAllowsRightwardOneSlot() {
    fiveEuro.move(6, 4, 6, 6);
    assertEquals(empty, fiveEuro.getSlotAt(6, 4));
    assertEquals(empty, fiveEuro.getSlotAt(6, 5));
    assertEquals(marble, fiveEuro.getSlotAt(6, 6));
  }

  /**
   * Test that the move method allows moves leftward over two slots.
   */
  @Test
  public void movesAllowsLeftwardOneSlot() {
    fiveEuro.move(6, 8, 6, 6);
    assertEquals(empty, fiveEuro.getSlotAt(6, 8));
    assertEquals(empty, fiveEuro.getSlotAt(6, 7));
    assertEquals(marble, fiveEuro.getSlotAt(6, 6));
  }

  /**
   * Test that the move method allows moves upward over two slots.
   */
  @Test(expected = IllegalArgumentException.class)
  public void movesAllowsUpwardTwoSlots() {
    threeEuro.move(5, 3, 3, 3);
    threeEuro.move(2, 3, 2, 5);
  }

  /**
   * Test that the move method allows moves downward over two slots.
   */
  @Test(expected = IllegalArgumentException.class)
  public void movesAllowsDownwardTwoSlots() {
    fiveEuro.move(4, 6, 6, 6);
    fiveEuro.move(7, 6, 4, 6);
  }

  /**
   * Test that the game over method works properly throughout gameplay.
   */
  @Test
  public void gameOverWork() {
    assertFalse(threeEuro.isGameOver());
    threeEuro.move(3, 1, 3, 3);
    threeEuro.move(1, 1, 3, 1);
    threeEuro.move(3, 0, 3, 2);
    threeEuro.move(1, 3, 1, 1);
    assertFalse(threeEuro.isGameOver());
    threeEuro.move(1, 5, 1, 3);
    threeEuro.move(2, 3, 2,1);
    threeEuro.move(2, 0, 2, 2);
    threeEuro.move(0, 3, 2, 3);
    assertFalse(threeEuro.isGameOver());
    threeEuro.move(3, 2, 1, 2);
    threeEuro.move(1, 1, 1,3);
    threeEuro.move(2, 3, 0,3);
    threeEuro.move(3, 4, 1, 4);
    assertFalse(threeEuro.isGameOver());
    threeEuro.move(0, 4, 2, 4);
    threeEuro.move(0, 2, 0, 4);
    threeEuro.move(5, 4, 3, 4);
    threeEuro.move(3, 4, 1, 4);
    assertFalse(threeEuro.isGameOver());
    threeEuro.move(0, 4, 2, 4);
    threeEuro.move(2, 5, 2,3);
    threeEuro.move(4, 5, 2, 5);
    threeEuro.move(2, 6,2, 4);
    assertFalse(threeEuro.isGameOver());
    threeEuro.move(2, 4, 2, 2);
    threeEuro.move(5, 2, 3, 2);
    threeEuro.move(2, 2, 4, 2);
    threeEuro.move(4, 2, 4, 4);
    assertFalse(threeEuro.isGameOver());
    threeEuro.move(6, 3, 4, 3);
    threeEuro.move(4, 3, 4, 5);
    threeEuro.move(4, 6, 4, 4);
    threeEuro.move(4, 0, 4, 2);
    assertTrue(threeEuro.isGameOver());
  }

  /**
   * Test that move disallows moving to an occupied position.
   */
  @Test(expected = IllegalArgumentException.class)
  public void moveDisallowsMovingToOccupiedSlot() {
    threeEuro.move(6, 3, 4,3);
  }
}