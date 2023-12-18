package cs3500.marblesolitaire.model.hw02;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link EnglishSolitaireModel}.
 */
public class EnglishSolitaireModelTest {
  static MarbleSolitaireModel threeESM = new EnglishSolitaireModel();
  static MarbleSolitaireModel threeESMDupe = new EnglishSolitaireModel();
  static MarbleSolitaireModel threeESMTopRight = new EnglishSolitaireModel(0, 2);
  static MarbleSolitaireModel fiveESM = new EnglishSolitaireModel(5);
  static MarbleSolitaireModel sevenESM = new EnglishSolitaireModel(7);
  static MarbleSolitaireModel fiveESMOffCenter
          = new EnglishSolitaireModel(5, 4, 4);
  static MarbleSolitaireModel threeESMMove = new EnglishSolitaireModel();


  /**
   * Tests for empty EnglishSolitaireModel constructor.
   */
  @Test
  public void esmEmptyConstructorWorks() {
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            threeESM.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            threeESM.getSlotAt(0, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            threeESM.getSlotAt(1, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            threeESM.getSlotAt(1, 1));

    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            threeESM.getSlotAt(5, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            threeESM.getSlotAt(5, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            threeESM.getSlotAt(6, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            threeESM.getSlotAt(6, 1));

    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            threeESM.getSlotAt(0, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            threeESM.getSlotAt(1, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            threeESM.getSlotAt(5, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            threeESM.getSlotAt(6, 5));

    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            threeESM.getSlotAt(0, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            threeESM.getSlotAt(1, 4));

    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            threeESM.getSlotAt(3, 3));
  }

  /**
   * Tests for EnglishSolitaireModel constructor that specifies center position.
   */
  @Test
  public void esmPositionConstructorWorks() {
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            threeESMTopRight.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            threeESMTopRight.getSlotAt(0, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            threeESMTopRight.getSlotAt(1, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            threeESMTopRight.getSlotAt(1, 1));

    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            threeESMTopRight.getSlotAt(5, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            threeESMTopRight.getSlotAt(5, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            threeESMTopRight.getSlotAt(6, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            threeESMTopRight.getSlotAt(6, 1));

    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            threeESMTopRight.getSlotAt(0, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            threeESMTopRight.getSlotAt(1, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            threeESMTopRight.getSlotAt(5, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            threeESMTopRight.getSlotAt(6, 5));

    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            threeESMTopRight.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            threeESMTopRight.getSlotAt(1, 4));

    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            threeESMTopRight.getSlotAt(0, 2));
  }

  /**
   * Tests for EnglishSolitaireModel constructor that specifies center position.
   * Test that the constructor does not allow negative row values.
   */
  @Test(expected = IllegalArgumentException.class)
  public void esmPositionConstructorDisallowsNegativeSRow() {
    new EnglishSolitaireModel(-1, 3);
  }

  /**
   * Tests for EnglishSolitaireModel constructor that specifies center position.
   * Test that the constructor does not allow negative column values.
   */
  @Test(expected = IllegalArgumentException.class)
  public void esmPositionConstructorDisallowsNegativeSColumn() {
    new EnglishSolitaireModel(1, -3);
  }

  /**
   * Tests for EnglishSolitaireModel constructor that specifies center position.
   * Test that the constructor does not allow negative row and column values.
   */
  @Test(expected = IllegalArgumentException.class)
  public void esmPositionConstructorDisallowsNegativeCenterPosition() {
    new EnglishSolitaireModel(-2, -5);
  }

  /**
   * Tests for EnglishSolitaireModel constructor that specifies center position.
   * Tests that the constructor does not allow row values that fall outside the model.
   */
  @Test(expected = IllegalArgumentException.class)
  public void esmPositionConstructorDisallowsSRowOutsideModel() {
    new EnglishSolitaireModel(7, 3);
  }

  /**
   * Tests for EnglishSolitaireModel constructor that specifies center position.
   * Tests that the constructor does not allow column values that fall outside the model.
   */
  @Test(expected = IllegalArgumentException.class)
  public void esmPositionConstructorDisallowsSColumnOutsideModel() {
    new EnglishSolitaireModel(1, 7);
  }

  /**
   * Tests for EnglishSolitaireModel constructor that specifies center position.
   * Tests that the constructor does not allow row and column values that fall outside the model.
   */
  @Test(expected = IllegalArgumentException.class)
  public void esmPositionConstructorDisallowsCenterPositionOutsideModel() {
    new EnglishSolitaireModel(8, 8);
  }

  /**
   * Tests for EnglishSolitaireModel constructor that specifies center position.
   * Tests that the constructor does not allow center position values in top left margin.
   */
  @Test(expected = IllegalArgumentException.class)
  public void esmPositionConstructorDisallowsRowPositionInTopLeftMargin() {
    new EnglishSolitaireModel(0, 1);
  }

  /**
   * Tests for EnglishSolitaireModel constructor that specifies center position.
   * Tests that the constructor does not allow center position values in top right margin.
   */
  @Test(expected = IllegalArgumentException.class)
  public void esmPositionConstructorDisallowsRowPositionInTopRightMargin() {
    new EnglishSolitaireModel(5, 6);
  }

  /**
   * Tests for EnglishSolitaireModel constructor that specifies center position.
   * Tests that the constructor does not allow center position values in bottom lect margin.
   */
  @Test(expected = IllegalArgumentException.class)
  public void esmPositionConstructorDisallowsRowPositionInBottomLeftMargin() {
    new EnglishSolitaireModel(5, 1);
  }

  /**
   * Tests for EnglishSolitaireModel constructor that specifies center position.
   * Tests that the constructor does not allow center position values in bottom right margin.
   */
  @Test(expected = IllegalArgumentException.class)
  public void esmPositionConstructorDisallowsRowPositionInBottomRightMargin() {
    new EnglishSolitaireModel(6, 6);
  }

  /**
   * Tests for EnglishSolitaireModel constructor that specifies arm thickness.
   */
  @Test(expected = IllegalArgumentException.class)
  public void esmArmThicknessConstructorDisallowsNegativeOddNumber() {
    new EnglishSolitaireModel(-3);
  }

  /**
   * Tests for empty EnglishSolitaireModel constructor.
   */
  @Test
  public void esmArmThicknessConstructorWorks() {
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            fiveESM.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            fiveESM.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            fiveESM.getSlotAt(3, 3));

    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            fiveESM.getSlotAt(0, 9));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            fiveESM.getSlotAt(3, 12));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            fiveESM.getSlotAt(1, 11));

    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            fiveESM.getSlotAt(9, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            fiveESM.getSlotAt(12, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            fiveESM.getSlotAt(9, 9));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            fiveESM.getSlotAt(12, 12));

    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            threeESM.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            fiveESM.getSlotAt(8, 11));

    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            fiveESM.getSlotAt(6, 6));
  }

  /**
   * Tests for EnglishSolitaireModel constructor that specifies arm thickness.
   * Tests that the constructor does not allow an arm thickness of 1.
   */
  @Test(expected = IllegalArgumentException.class)
  public void esmArmThicknessConstructorDisallowsThicknessOfOne() {
    new EnglishSolitaireModel(1);
  }

  /**
   * Tests for EnglishSolitaireModel constructor that specifies arm thickness.
   * Tests that the constructor does not allow an even arm thickness.
   */
  @Test(expected = IllegalArgumentException.class)
  public void esmArmThicknessConstructorDisallowsEvenNumber() {
    new EnglishSolitaireModel(6);
  }

  /**
   * Tests for EnglishSolitaireModel constructor that specifies arm thickness.
   * Tests that the constructor does not allow a negative, even arm thickness.
   */
  @Test(expected = IllegalArgumentException.class)
  public void esmArmThicknessConstructorDisallowsNegativeEvenNumber() {
    new EnglishSolitaireModel(-8);
  }

  /**
   * Tests for EnglishSolitaireModel constructor that specifies arm thickness.
   * Tests that the constructor does not allow a negative arm thickness.
   */
  @Test(expected = IllegalArgumentException.class)
  public void esmArmThicknessConstructorDisallowsNegativeNumber() {
    new EnglishSolitaireModel(-5);
  }

  /**
   * Tests for EnglishSolitaireModel constructor that specifies arm thickness and
   * empty slot position.
   */
  @Test
  public void esmArmThicknessPositionConstructorWorks() {
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            fiveESMOffCenter.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            fiveESMOffCenter.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            fiveESMOffCenter.getSlotAt(3, 3));

    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            fiveESMOffCenter.getSlotAt(0, 9));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            fiveESMOffCenter.getSlotAt(3, 12));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            fiveESMOffCenter.getSlotAt(1, 11));

    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            fiveESMOffCenter.getSlotAt(9, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            fiveESMOffCenter.getSlotAt(12, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            fiveESMOffCenter.getSlotAt(9, 9));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            fiveESMOffCenter.getSlotAt(12, 12));

    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            fiveESMOffCenter.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            fiveESMOffCenter.getSlotAt(8, 11));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            fiveESMOffCenter.getSlotAt(6, 6));

    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            fiveESMOffCenter.getSlotAt(4, 4));
  }

  /**
   * Tests for EnglishSolitaireModel constructor that specifies arm thickness and
   * empty slot position.
   * Tests that the constructor disallows a negative, odd value for the arm thickness.
   */
  @Test(expected = IllegalArgumentException.class)
  public void esmArmThicknessPositionConstructorDisallowsNegativeOddNumberArmThickness() {
    new EnglishSolitaireModel(-5, 5, 5);
  }

  /**
   * Tests for EnglishSolitaireModel constructor that specifies arm thickness and
   * empty slot position.
   * Tests that the constructor disallows an even value for the arm thickness.
   */
  @Test(expected = IllegalArgumentException.class)
  public void esmArmThicknessPositionConstructorDisallowsEvenNumberArmThickness() {
    new EnglishSolitaireModel(6, 1, 3);
  }

  /**
   * Tests for EnglishSolitaireModel constructor that specifies arm thickness and
   * empty slot position.
   * Tests that the constructor disallows a negative, even value for the arm thickness.
   */
  @Test(expected = IllegalArgumentException.class)
  public void esmArmThicknessPositionConstructorDisallowsNegativeEvenNumberArmThickness() {
    new EnglishSolitaireModel(-2, 4, 5);
  }

  /**
   * Tests for EnglishSolitaireModel constructor that specifies arm thickness and
   * empty slot position.
   * Tests that the constructor disallows a center position value in the top left margin.
   */
  @Test(expected = IllegalArgumentException.class)
  public void esmThreeParamConstructorDisallowsCenterPositionInTopLeftMargin() {
    new EnglishSolitaireModel(5, 0, 2);
  }

  /**
   * Tests for EnglishSolitaireModel constructor that specifies arm thickness and
   * empty slot position.
   * Tests that the constructor disallows a center position value in the top right margin.
   */
  @Test(expected = IllegalArgumentException.class)
  public void esmThreeParamConstructorDisallowsCenterPositionInTopRightMargin() {
    new EnglishSolitaireModel(5, 2, 10);
  }

  /**
   * Tests for EnglishSolitaireModel constructor that specifies arm thickness and
   * empty slot position.
   * Tests that the constructor disallows a center position value in the bottom left margin.
   */
  @Test(expected = IllegalArgumentException.class)
  public void esmThreeParamConstructorDisallowsCenterPositionInBottomLeftMargin() {
    new EnglishSolitaireModel(5, 10, 3);
  }

  /**
   * Tests for EnglishSolitaireModel constructor that specifies arm thickness and
   * empty slot position.
   * Tests that the constructor disallows a center position value in the bottom right margin.
   */
  @Test(expected = IllegalArgumentException.class)
  public void esmThreeParamConstructorDisallowsCenterPositionInBottomRightMargin() {
    new EnglishSolitaireModel(5, 10, 10);
  }

  /**
   * Tests for EnglishSolitaireModel constructor that specifies arm thickness and
   * empty slot position.
   * Tests that the constructor disallows invalid arm thickness and center position.
   */
  @Test(expected = IllegalArgumentException.class)
  public void esmThreeParamConstructorDisallowsInValidArmThicknessAndCenterPosition() {
    new EnglishSolitaireModel(-5, -10, -10);
  }

  /**
   * Tests for move method.
   */
  @Test
  public void moveWorks() {
    assertEquals(32, threeESMMove.getScore());
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            threeESMMove.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            threeESMMove.getSlotAt(3, 3));
    threeESMMove.move(5, 3, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            threeESMMove.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            threeESMMove.getSlotAt(3, 3));
    assertEquals(31, threeESMMove.getScore());

    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            threeESMMove.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            threeESMMove.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            threeESMMove.getSlotAt(4, 3));
    threeESMMove.move(2, 3, 4, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            threeESMMove.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            threeESMMove.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            threeESMMove.getSlotAt(4, 3));

    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            threeESMMove.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            threeESMMove.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            threeESMMove.getSlotAt(3, 3));
    threeESMMove.move(3, 1, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            threeESMMove.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            threeESMMove.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            threeESMMove.getSlotAt(3, 3));

    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            threeESMMove.getSlotAt(2, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            threeESMMove.getSlotAt(2, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            threeESMMove.getSlotAt(2, 3));
    threeESMMove.move(2, 5, 2, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            threeESMMove.getSlotAt(2, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            threeESMMove.getSlotAt(2, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            threeESMMove.getSlotAt(2, 3));

    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            threeESMTopRight.getSlotAt(0, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            threeESMTopRight.getSlotAt(0, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            threeESMTopRight.getSlotAt(0, 4));
    threeESMTopRight.move(0, 4, 0, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            threeESMTopRight.getSlotAt(0, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            threeESMTopRight.getSlotAt(0, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            threeESMTopRight.getSlotAt(0, 4));

  }

  @Test(expected = IllegalArgumentException.class)
  public void moveDisallowsMovingToOccupiedSlot() {
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            threeESMMove.getSlotAt(0, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            threeESMMove.getSlotAt(1, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            threeESMMove.getSlotAt(2, 2));
    threeESMMove.move(0, 2, 2, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveDisallowsMovingDiagonally() {
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            threeESMMove.getSlotAt(2, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            threeESMMove.getSlotAt(3, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            threeESMMove.getSlotAt(3, 1));
    threeESMMove.move(2, 0, 3, 1);
  }

  /**
   * Tests for isGameOver method.
   */
  @Test
  public void isGameOverWorks() {
    assertEquals(false, threeESMDupe.isGameOver());
    threeESMDupe.move(1, 3, 3, 3);
    threeESMDupe.move(2,1, 2 ,3);
    threeESMDupe.move(0, 2, 2, 2);
    threeESMDupe.move(0, 4, 0, 2);
    threeESMDupe.move(3,2, 1, 2);
    assertEquals(27, threeESMDupe.getScore());
    assertEquals(false, threeESMDupe.isGameOver());

    threeESMDupe.move(0, 2, 2, 2);
    threeESMDupe.move(5, 2, 3, 2);
    threeESMDupe.move(4, 0, 4, 2);
    threeESMDupe.move(2, 0, 4, 0);
    threeESMDupe.move(4, 3, 4, 1);
    assertEquals(22, threeESMDupe.getScore());
    assertEquals(false, threeESMDupe.isGameOver());

    threeESMDupe.move(4, 0, 4,2);
    threeESMDupe.move(4, 5, 4, 3);
    threeESMDupe.move(6, 4, 4, 4);
    threeESMDupe.move(6, 2, 6, 4);
    threeESMDupe.move(3, 4, 5, 4);
    assertEquals(17, threeESMDupe.getScore());
    assertEquals(false, threeESMDupe.isGameOver());


    threeESMDupe.move(6, 4, 4, 4);
    threeESMDupe.move(1, 4, 3, 4);
    threeESMDupe.move(2, 6, 2, 4);
    threeESMDupe.move(4, 6, 2, 6);
    threeESMDupe.move(2, 3, 2, 5);
    assertEquals(12, threeESMDupe.getScore());
    assertEquals(false, threeESMDupe.isGameOver());

    threeESMDupe.move(2, 6, 2, 4);
    threeESMDupe.move(4, 3, 4, 5);
    threeESMDupe.move(4, 5, 2, 5);
    threeESMDupe.move(2, 5, 2, 3);
    threeESMDupe.move(2, 3, 2, 1);
    assertEquals(7, threeESMDupe.getScore());
    assertEquals(false, threeESMDupe.isGameOver());

    threeESMDupe.move(2, 1, 4, 1);
    threeESMDupe.move(4, 1, 4, 3);
    threeESMDupe.move(3, 3, 3, 5);
    threeESMDupe.move(5, 3, 3, 3);
    threeESMDupe.move(3, 2, 3, 4);
    assertEquals(2, threeESMDupe.getScore());
    assertEquals(false, threeESMDupe.isGameOver());

    threeESMDupe.move(3, 5, 3, 3);
    assertEquals(1, threeESMDupe.getScore());
    assertEquals(true, threeESMDupe.isGameOver());
  }

  /**
   * Tests for getBoardSize method.
   */
  @Test
  public void getBoardSizeWorks() {
    assertEquals(7, threeESM.getBoardSize());
    assertEquals(13, fiveESM.getBoardSize());
    assertEquals(19, sevenESM.getBoardSize());
  }

  /**
   * Tests for getSlotAt method.
   */
  @Test
  public void getSlotAtWorks() {
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            threeESM.getSlotAt(0, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            threeESM.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            threeESM.getSlotAt(0, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            threeESM.getSlotAt(1, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            threeESM.getSlotAt(5, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            threeESM.getSlotAt(6, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            threeESM.getSlotAt(5, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            threeESM.getSlotAt(6, 6));

    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            threeESM.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            threeESM.getSlotAt(2, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            threeESM.getSlotAt(4, 6));

    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            fiveESMOffCenter.getSlotAt(4, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            fiveESMOffCenter.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            fiveESMOffCenter.getSlotAt(6, 6));
  }

  /**
   * Tests for getSlotAt method.
   * Tests that the method disallows row values that are outside the entire model.
   */
  @Test(expected = IllegalArgumentException.class)
  public void getSlotAtDisallowsRowOutsideTheModel() {
    threeESMMove.getSlotAt(8, 0);
  }

  /**
   * Tests for getSlotAt method.
   * Tests that the method disallows column values that are outside the entire model.
   */
  @Test(expected = IllegalArgumentException.class)
  public void getSlotAtDisallowsColOutsideTheModel() {
    threeESMMove.getSlotAt(0, 7);
  }

  /**
   * Tests for getSlotAt method.
   * Tests that the method disallows column values that are outside the entire model.
   */
  @Test(expected = IllegalArgumentException.class)
  public void getSlotAtDisallowsRowAndColOutsideTheModel() {
    threeESMMove.getSlotAt(13, 13);
  }

  /**
   * Tests for getScore method.
   */
  @Test
  public void getScoreWorks() {
    assertEquals(32, threeESM.getScore());
    assertEquals(104, fiveESM.getScore());
    assertEquals(216, sevenESM.getScore());
  }
}