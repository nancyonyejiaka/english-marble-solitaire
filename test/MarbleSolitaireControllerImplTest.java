import org.junit.After;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

import cs3500.marblesolitaire.controller.FailingAppendable;
import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MockModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState.Empty;
import static cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState.Marble;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link MarbleSolitaireControllerImpl}.
 */
public class MarbleSolitaireControllerImplTest {
  MarbleSolitaireModel esmThree = new EnglishSolitaireModel();
  Appendable out;
  Reader in;
  Appendable mockLog;
  MarbleSolitaireModel esmThreeMock;
  MarbleSolitaireView esmThreeView;
  MarbleSolitaireController mockController;
  MarbleSolitaireController fakeController;
  Appendable initialGameWindow;
  Appendable winGameWindow;
  Appendable renderThreeMoves;
  Appendable renderSixMoves;
  Appendable renderImmediateQuit;
  Appendable fakeAppendable;
  MarbleSolitaireView fakeViewThrows;


  /**
   * Sets the initial conditions for tests using the mock model.
   *
   * @param input the Reader to be used as input for the mock model tests
   */
  protected void initMockModelTests(StringReader input) {
    this.initialGameWindow = new StringBuilder("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O"
            + "\nScore: 32\n");
    this.out = new StringBuilder();
    this.mockLog = new StringBuilder();
    this.esmThreeMock = new MockModel(this.mockLog, this.esmThree);
    this.esmThreeView = new MarbleSolitaireTextView(this.esmThreeMock, this.out);
    this.mockController = new MarbleSolitaireControllerImpl(this.esmThreeMock,
            this.esmThreeView, input);
    this.fakeAppendable = new FailingAppendable();
    this.fakeViewThrows = new MarbleSolitaireTextView(this.esmThreeMock, this.fakeAppendable);
    this.fakeController = new MarbleSolitaireControllerImpl(this.esmThreeMock,
            this.fakeViewThrows, input);

  }

  /**
   * Sets the initial conditions for tests using the mock model.
   */
  protected void initControllerRenderTests() {
    this.initialGameWindow = new StringBuilder("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O"
            + "\nScore: 32\n");
    this.renderImmediateQuit = new StringBuilder(this.initialGameWindow
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O"
            + "\nScore: 32\n");
    this.renderThreeMoves = new StringBuilder(this.initialGameWindow
            + "    O O O\n"
            + "    O _ O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O"
            + "\nScore: 31\n"
            + "    O O O\n"
            + "    O _ O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O"
            + "\nScore: 30\n"
            + "    _ O O\n"
            + "    _ _ O\n"
            + "O _ O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O"
            + "\nScore: 29\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    _ O O\n"
            + "    _ _ O\n"
            + "O _ O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O"
            + "\nScore: 29\n");
    this.renderSixMoves = new StringBuilder(this.initialGameWindow
            + "    O O O\n"
            + "    O _ O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O"
            + "\nScore: 31\n"
            + "    O O O\n"
            + "    O _ O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O"
            + "\nScore: 30\n"
            + "    _ O O\n"
            + "    _ _ O\n"
            + "O _ O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O"
            + "\nScore: 29\n"
            + "    O _ _\n"
            + "    _ _ O\n"
            + "O _ O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O"
            + "\nScore: 28\n"
            + "    O _ _\n"
            + "    O _ O\n"
            + "O _ _ O O O O\n"
            + "O O _ O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O"
            + "\nScore: 27\n"
            + "    _ _ _\n"
            + "    _ _ O\n"
            + "O _ O O O O O\n"
            + "O O _ O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O"
            + "\nScore: 26\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    _ _ _\n"
            + "    _ _ O\n"
            + "O _ O O O O O\n"
            + "O O _ O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O"
            + "\nScore: 26\n");
    this.winGameWindow = new StringBuilder(this.initialGameWindow
            + "    O O O\n"
            + "    O _ O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O"
            + "\nScore: 31\n"
            + "    O O O\n"
            + "    O _ O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O"
            + "\nScore: 30\n"
            + "    _ O O\n"
            + "    _ _ O\n"
            + "O _ O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O"
            + "\nScore: 29\n"
            + "    O _ _\n"
            + "    _ _ O\n"
            + "O _ O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O"
            + "\nScore: 28\n"
            + "    O _ _\n"
            + "    O _ O\n"
            + "O _ _ O O O O\n"
            + "O O _ O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O"
            + "\nScore: 27\n"
            + "    _ _ _\n"
            + "    _ _ O\n"
            + "O _ O O O O O\n"
            + "O O _ O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O"
            + "\nScore: 26\n"
            + "    _ _ _\n"
            + "    _ _ O\n"
            + "O _ O O O O O\n"
            + "O O O O O O O\n"
            + "O O _ O O O O\n"
            + "    _ O O\n"
            + "    O O O"
            + "\nScore: 25\n"
            + "    _ _ _\n"
            + "    _ _ O\n"
            + "O _ O O O O O\n"
            + "O O O O O O O\n"
            + "_ _ O O O O O\n"
            + "    _ O O\n"
            + "    O O O"
            + "\nScore: 24\n"
            + "    _ _ _\n"
            + "    _ _ O\n"
            + "_ _ O O O O O\n"
            + "_ O O O O O O\n"
            + "O _ O O O O O\n"
            + "    _ O O\n"
            + "    O O O"
            + "\nScore: 23\n"
            + "    _ _ _\n"
            + "    _ _ O\n"
            + "_ _ O O O O O\n"
            + "_ O O O O O O\n"
            + "O O _ _ O O O\n"
            + "    _ O O\n"
            + "    O O O"
            + "\nScore: 22\n"
            + "    _ _ _\n"
            + "    _ _ O\n"
            + "_ _ O O O O O\n"
            + "_ O O O O O O\n"
            + "_ _ O _ O O O\n"
            + "    _ O O\n"
            + "    O O O"
            + "\nScore: 21\n"
            + "    _ _ _\n"
            + "    _ _ O\n"
            + "_ _ O O O O O\n"
            + "_ O O O O O O\n"
            + "_ _ O O _ _ O\n"
            + "    _ O O\n"
            + "    O O O"
            + "\nScore: 20\n"
            + "    _ _ _\n"
            + "    _ _ O\n"
            + "_ _ O O O O O\n"
            + "_ O O O O O O\n"
            + "_ _ O O O _ O\n"
            + "    _ O _\n"
            + "    O O _"
            + "\nScore: 19\n"
            + "    _ _ _\n"
            + "    _ _ O\n"
            + "_ _ O O O O O\n"
            + "_ O O O O O O\n"
            + "_ _ O O O _ O\n"
            + "    _ O _\n"
            + "    _ _ O"
            + "\nScore: 18\n"
            + "    _ _ _\n"
            + "    _ _ O\n"
            + "_ _ O O O O O\n"
            + "_ O O O _ O O\n"
            + "_ _ O O _ _ O\n"
            + "    _ O O\n"
            + "    _ _ O"
            + "\nScore: 17\n"
            + "    _ _ _\n"
            + "    _ _ O\n"
            + "_ _ O O O O O\n"
            + "_ O O O _ O O\n"
            + "_ _ O O O _ O\n"
            + "    _ O _\n"
            + "    _ _ _"
            + "\nScore: 16\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "_ _ O O _ O O\n"
            + "_ O O O O O O\n"
            + "_ _ O O O _ O\n"
            + "    _ O _\n"
            + "    _ _ _"
            + "\nScore: 15\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "_ _ O O O _ _\n"
            + "_ O O O O O O\n"
            + "_ _ O O O _ O\n"
            + "    _ O _\n"
            + "    _ _ _"
            + "\nScore: 14\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "_ _ O O O _ O\n"
            + "_ O O O O O _\n"
            + "_ _ O O O _ _\n"
            + "    _ O _\n"
            + "    _ _ _"
            + "\nScore: 13\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "_ _ O _ _ O O\n"
            + "_ O O O O O _\n"
            + "_ _ O O O _ _\n"
            + "    _ O _\n"
            + "    _ _ _"
            + "\nScore: 12\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "_ _ O _ O _ _\n"
            + "_ O O O O O _\n"
            + "_ _ O O O _ _\n"
            + "    _ O _\n"
            + "    _ _ _"
            + "\nScore: 11\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "_ _ O _ O _ _\n"
            + "_ O O O O O _\n"
            + "_ _ O _ _ O _\n"
            + "    _ O _\n"
            + "    _ _ _"
            + "\nScore: 10\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "_ _ O _ O O _\n"
            + "_ O O O O _ _\n"
            + "_ _ O _ _ _ _\n"
            + "    _ O _\n"
            + "    _ _ _"
            + "\nScore: 9\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "_ _ O O _ _ _\n"
            + "_ O O O O _ _\n"
            + "_ _ O _ _ _ _\n"
            + "    _ O _\n"
            + "    _ _ _"
            + "\nScore: 8\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "_ O _ _ _ _ _\n"
            + "_ O O O O _ _\n"
            + "_ _ O _ _ _ _\n"
            + "    _ O _\n"
            + "    _ _ _"
            + "\nScore: 7\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ O O O _ _\n"
            + "_ O O _ _ _ _\n"
            + "    _ O _\n"
            + "    _ _ _"
            + "\nScore: 6\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ O O O _ _\n"
            + "_ _ _ O _ _ _\n"
            + "    _ O _\n"
            + "    _ _ _"
            + "\nScore: 5\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ O _ _ O _\n"
            + "_ _ _ O _ _ _\n"
            + "    _ O _\n"
            + "    _ _ _"
            + "\nScore: 4\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ O O _ O _\n"
            + "_ _ _ _ _ _ _\n"
            + "    _ _ _\n"
            + "    _ _ _"
            + "\nScore: 3\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ O O _\n"
            + "_ _ _ _ _ _ _\n"
            + "    _ _ _\n"
            + "    _ _ _"
            + "\nScore: 2\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ O _ _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "    _ _ _\n"
            + "    _ _ _"
            + "\nScore: 1\n"
            + "Game over!\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ O _ _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "    _ _ _\n"
            + "    _ _ _"
            + "\nScore: 1\n");
  }

  /**
   * Returns the fields in this class back to their default state after each test method.
   */
  @After
  public void teardown() {
    this.out = null;
    this.mockLog = null;
    this.esmThreeMock = null;
    this.esmThreeView = null;
    this.mockController = null;
  }

  /**
   * Test that the constructor for the controller works properly.
   */
  @Test
  public void testControllerConstructorWorks() {
    initMockModelTests(new StringReader("q"));
    assertEquals("", this.mockLog.toString());
    assertFalse(esmThree.isGameOver());
    assertEquals(new StringBuilder().toString(), this.out.toString());
  }

  /**
   * Test that the controller constructor throws exception when given a null model.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testControllerConstructorThrowsExceptionForNullModel() {
    initMockModelTests(new StringReader("q"));
    new MarbleSolitaireControllerImpl(null, this.esmThreeView, this.in);
  }

  /**
   * Test that the controller constructor throws exception when given a null view.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testControllerConstructorThrowsExceptionForNullView() {
    initMockModelTests(new StringReader("q"));
    new MarbleSolitaireControllerImpl(this.esmThreeMock, null, this.in);
  }


  /**
   * Test that the controller constructor throws exception when given a null input.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testControllerConstructorThrowsExceptionForNullInput() {
    initMockModelTests(new StringReader("q"));
    new MarbleSolitaireControllerImpl(this.esmThreeMock, this.esmThreeView, null);
  }

  /**
   * Test that the controller properly transmits user input to the model when given
   * only a q.
   */
  @Test
  public void testControllerTransmitsQToMoveInputToModel() {
    initMockModelTests(new StringReader("q"));
    this.mockController.playGame();
    assertEquals("", this.mockLog.toString());
  }

  /**
   * Test that the controller ignore q when it is at the beginning of an invalid input.
   */
  @Test(expected = IllegalStateException.class)
  public void testControllerIgnoresInvalidEndingInQ() {
    initMockModelTests(new StringReader("qerfurfu"));
    this.mockController.playGame();
  }

  /**
   * Test that the controller ignore q when it is at the end of an invalid input.
   */
  @Test(expected = IllegalStateException.class)
  public void testControllerIgnoresInvalidBeginningWithQ() {
    initMockModelTests(new StringReader("erfurfuq"));
    this.mockController.playGame();
  }

  /**
   * Test that the controller ignore q when it is in the middle of an invalid input.
   */
  @Test(expected = IllegalStateException.class)
  public void testControllerIgnoresInvalidWithQInMiddle() {
    initMockModelTests(new StringReader("erfuqrfu"));
    this.mockController.playGame();
  }

  /**
   * Test that the controller properly transmits user input to the model when given
   * an invalid input then a q.
   */
  @Test
  public void testControllerTransmitsInvalidThenQToMoveInputToModel() {
    initMockModelTests(new StringReader("wshdyw q"));
    this.mockController.playGame();
    assertEquals("", this.mockLog.toString());
  }

  /**
   * Test that the controller properly transmits user input to the model when given
   * two invalid inputs then a q.
   */
  @Test
  public void testControllerTransmitsTwoInvalidThenQToMoveInputToModel() {
    initMockModelTests(new StringReader("wshdyw -1 q"));
    this.mockController.playGame();
    assertEquals("", this.mockLog.toString());
  }

  /**
   * Test that the controller properly transmits user input to the model when given
   * three invalid inputs then a q.
   */
  @Test
  public void testControllerTransmitsThreeInvalidThenQToMoveInputToModel() {
    initMockModelTests(new StringReader("wshdyw -1 -288 q"));
    this.mockController.playGame();
    assertEquals("", this.mockLog.toString());
  }

  /**
   * Test that the controller properly transmits user input to the model when given
   * four invalid inputs then a q.
   */
  @Test
  public void testControllerTransmitsFourInvalidThenQToMoveInputToModel() {
    initMockModelTests(new StringReader("wshdyw -1 -288 p q"));
    this.mockController.playGame();
    assertEquals("", this.mockLog.toString());
  }

  /**
   * Test that the controller properly transmits user input to the model when given
   * six invalid inputs then a q.
   */
  @Test
  public void testControllerTransmitsSixInvalidThenQToMoveInputToModel() {
    initMockModelTests(new StringReader("wshdyw -1 -288 -2322 jhbfjhbfwhjf p q"));
    this.mockController.playGame();
    assertEquals("", this.mockLog.toString());
  }

  /**
   * Test that the controller ignores invalid Strings.
   */
  @Test
  public void testControllerIgnoresInvalidStrings() {
    initMockModelTests(new StringReader("h d sfdhj e rdjkk y s q"));
    this.mockController.playGame();
    assertEquals("", this.mockLog.toString());
  }

  /**
   * Test that the controller ignores negative numbers.
   */
  @Test
  public void testControllerIgnoresNegativeNumbers() {
    initMockModelTests(new StringReader("-1 -2 -3 -4 q"));
    this.mockController.playGame();
    assertEquals("", this.mockLog.toString());
  }

  /**
   * Test that the controller properly transmits user input to the model when given
   * one valid number with a q at the end.
   */
  @Test
  public void testControllerTransmitsOneValidThenQInputToModel() {
    initMockModelTests(new StringReader("2 q"));
    this.mockController.playGame();
    assertEquals("", this.mockLog.toString());
  }


  /**
   * Test that the controller properly transmits user input to the model when given
   * half of a complete move with a q at the end.
   */
  @Test
  public void testControllerTransmitsHalfAMoveThenQInputToModel() {
    initMockModelTests(new StringReader("2 4 q"));
    this.mockController.playGame();
    assertEquals("", this.mockLog.toString());
  }

  /**
   * Test that the controller properly transmits user input to the model when given
   * q followed by a valid move.
   */
  @Test
  public void testControllerTransmitsQThenValidMoveInputToModel() {
    initMockModelTests(new StringReader("q 2 4 4 4"));
    this.mockController.playGame();
    assertEquals("", this.mockLog.toString());
  }

  /**
   * Test that the controller properly transmits user input to the model when given
   * three quarters of a complete move with a q at the end.
   */
  @Test
  public void testControllerTransmitsThreeQuartersAMoveThenQInputToModel() {
    initMockModelTests(new StringReader("2 4 4 q"));
    this.mockController.playGame();
    assertEquals("", this.mockLog.toString());
  }

  /**
   * Test that the controller properly transmits user input to the model when given
   * a complete move with a q at the end.
   */
  @Test
  public void testControllerTransmitsCompleteMoveThenQInputToModel() {
    initMockModelTests(new StringReader("2 4 4 4 q"));
    this.mockController.playGame();
    assertEquals("1333", this.mockLog.toString());
  }

  /**
   * Test that controller properly transmits input when user enters
   * q followed by three valid numbers.
   */
  @Test
  public void testControllerTransmitsQThenThreeValid() {
    initMockModelTests(new StringReader("q 4 4 4 q"));
    this.mockController.playGame();
    assertEquals("", this.mockLog.toString());
  }

  /**
   * Test that controller properly transmits input when user enters
   * one valid number then q followed by two valid numbers.
   */
  @Test
  public void testControllerTransmitsValidThenQThenTwoValid() {
    initMockModelTests(new StringReader("2 q 4 4 q"));
    this.mockController.playGame();
    assertEquals("", this.mockLog.toString());
  }

  /**
   * Test that controller properly transmits input when user enters
   * two valid numbers then q followed by one valid number.
   */
  @Test
  public void testControllerTransmitsTwoValidThenQThenOneValid() {
    initMockModelTests(new StringReader("2 4 q 4 q"));
    this.mockController.playGame();
    assertEquals("", this.mockLog.toString());
  }

  /**
   * Test that controller properly transmits input when user enters
   * three valid numbers then q.
   */
  @Test
  public void testControllerTransmitsThreeValidQThenOneValid() {
    initMockModelTests(new StringReader("2 4 4 q q"));
    this.mockController.playGame();
    assertEquals("", this.mockLog.toString());
  }

  /**
   * Tests that the NoSuchElementException is caught by the controller and that
   * an IllegalStateException is thrown instead.
   */
  @Test(expected = IllegalStateException.class)
  public void throwsExceptionWhenReadableRunsOutOfInputs() {
    initMockModelTests(new StringReader("2 4 4 4 3"));
    this.mockController.playGame();
  }

  /**
   * Test that the playGame method throws and exception when appending to view fails.
   */
  @Test(expected = IllegalStateException.class)
  public void throwsExceptionWhenTransmissionFails() {
    initMockModelTests(new StringReader("2 4 4 4 q"));
    this.fakeController.playGame();
  }

  /**
   * Test that the controller properly transmits user input when given a series of invalid inputs
   * followed by valid inputs.
   */
  @Test
  public void testControllerTransmitsValidMovePrefixedByInvalid() {
    initMockModelTests(new StringReader("uwff weifuiufh -1 -23 iuwhfhrf rfhf 2 4 4 4 q"));
    this.mockController.playGame();
    assertEquals("1333", this.mockLog.toString());
  }

  /**
   * Test that the controller properly transmits user input when it is one valid number,
   * followed by a negative number, then three valid numbers.
   */
  @Test
  public void testControllerTransmitsOneValidWithNegativeThenThreeValid() {
    initMockModelTests(new StringReader("2 -1 4 4 4 q"));
    this.mockController.playGame();
    assertEquals("1333", this.mockLog.toString());
  }

  /**
   * Test that the controller properly transmits user input when it is two halves of a valid move
   * separated by a negative number.
   */
  @Test
  public void testControllerTransmitsTwoValidWithNegativeBetween() {
    initMockModelTests(new StringReader("2 4 -1 4 4 q"));
    this.mockController.playGame();
    assertEquals("1333", this.mockLog.toString());
  }

  /**
   * Test that the controller properly transmits user input to the model when given
   * a complete move and an additional valid input with a q at the end.
   */
  @Test
  public void testControllerTransmitsCompleteMoveWithOneExtraThenQInputToModel() {
    initMockModelTests(new StringReader("2 4 4 4 3 q"));
    this.mockController.playGame();
    assertEquals("1333", this.mockLog.toString());
  }

  /**
   * Test that the controller properly transmits user input to the model when given
   * two complete moves with a q at the end.
   */
  @Test
  public void testControllerTransmitsTwoCompleteMovesThenQInputToModel() {
    initMockModelTests(new StringReader("2 4 4 4 3 2 3 4 q"));
    this.mockController.playGame();
    assertEquals("13332123", this.mockLog.toString());
  }

  /**
   * Test that the control properly makes both moves based on user input to the model when given
   * one complete move with a q at the end.
   */
  @Test
  public void testControllerMakesAMovesOnModel() {
    assertEquals(Marble, this.esmThree.getSlotAt(1, 3));
    assertEquals(Marble, this.esmThree.getSlotAt(2, 3));
    assertEquals(Empty, this.esmThree.getSlotAt(3, 3));

    initMockModelTests(new StringReader("2 4 4 4 q"));
    this.mockController.playGame();

    assertEquals(Empty, this.esmThree.getSlotAt(1, 3));
    assertEquals(Empty, this.esmThree.getSlotAt(2, 3));
    assertEquals(Marble, this.esmThree.getSlotAt(3, 3));
  }

  /**
   * Test that the control properly makes both moves based on user input to the model when given
   * two complete moves with a q at the end.
   */
  @Test
  public void testControllerMakesTwoMovesOnModel() {
    assertEquals(Marble, this.esmThree.getSlotAt(1, 3));
    assertEquals(Marble, this.esmThree.getSlotAt(2, 3));
    assertEquals(Empty, this.esmThree.getSlotAt(3, 3));

    assertEquals(Marble, this.esmThree.getSlotAt(2, 1));
    assertEquals(Marble, this.esmThree.getSlotAt(2, 2));
    assertEquals(Marble, this.esmThree.getSlotAt(2, 3));

    initMockModelTests(new StringReader("2 4 4 4 3 2 3 4 q"));
    this.mockController.playGame();

    assertEquals(Empty, this.esmThree.getSlotAt(1, 3));
    assertEquals(Marble, this.esmThree.getSlotAt(2, 3));
    assertEquals(Marble, this.esmThree.getSlotAt(3, 3));

    assertEquals(Empty, this.esmThree.getSlotAt(2, 1));
    assertEquals(Empty, this.esmThree.getSlotAt(2, 2));
    assertEquals(Marble, this.esmThree.getSlotAt(2, 3));
  }

  /**
   * Test that the controller properly renders the game when quitting immediately at the start
   * of the game without making any moves when user inputs lowercase q.
   */
  @Test
  public void testControllerProperlyRendersImmediateQuitLowercase() {
    initMockModelTests(new StringReader("q"));
    initControllerRenderTests();
    this.mockController.playGame();
    assertEquals(this.renderImmediateQuit.toString(), this.out.toString());
  }

  /**
   * Test that the controller properly renders the game when quitting immediately at the start
   * of the game without making any moves when user inputs uppercase q.
   */
  @Test
  public void testControllerProperlyRendersImmediateQuitUppercase() {
    initMockModelTests(new StringReader("Q"));
    initControllerRenderTests();
    this.mockController.playGame();
    assertEquals(this.renderImmediateQuit.toString(), this.out.toString());
  }

  /**
   * Test that the controller properly renders the game when quitting after three valid moves.
   */
  @Test
  public void testControllerProperlyRendersAfterThreeMovesThenQuit() {
    initMockModelTests(new StringReader("2 4 4 4 | 3 2 3 4 | 1 3 3 3 q"));
    initControllerRenderTests();
    this.mockController.playGame();
    assertEquals(this.renderThreeMoves.toString(), this.out.toString());
  }

  /**
   * Test that the controller properly renders the game when quitting after three valid moves.
   */
  @Test
  public void testControllerProperlyRendersAfterSixMovesThenQuit() {
    initMockModelTests(new StringReader("2 4 4 4 | 3 2 3 4 | 1 3 3 3 | "
            + "1 5 1 3 | 4 3 2 3 | 1 3 3 3 q"));
    initControllerRenderTests();
    this.mockController.playGame();
    assertEquals(this.renderSixMoves.toString(), this.out.toString());
  }

  /**
   * Test that playGame continues attempting to read a value for fromRow before moving on
   * to read the value for fromCol when given an invalid fromRow and valid
   * fromCol, toRow and toCol.
   */
  @Test
  public void testPlayGameContinuesCheckingForValidFromRow() {
    initMockModelTests(new StringReader("-5 -6 hdjdj 2 4 4 4 q"));
    this.mockController.playGame();
    assertEquals("1333", this.mockLog.toString());
  }

  /**
   * Test that playGame continues attempting to read a value for fromCol before moving on
   * to read the value for toRow when given a valid fromRow and toRow and toCol.
   */
  @Test
  public void testPlayGameContinuesCheckingForValidFromCol() {
    initMockModelTests(new StringReader("-5 -6 hdjdj 2 4 4 4 q"));
    this.mockController.playGame();
    assertEquals("1333", this.mockLog.toString());
  }

  /**
   * Test that playGame continues attempting to read a value for toCol before moving on
   * to read the value for toColumn when given a valid fromRow and fromCol and from.
   */
  @Test
  public void testPlayGameContinuesCheckingForValidToRow() {
    initMockModelTests(new StringReader("-5 -6 hdjdj 2 4 4 4 q"));
    this.mockController.playGame();
    assertEquals("1333", this.mockLog.toString());
  }

  /**
   * Test that playGame runs through the game from start to finish when given all the moves
   * to win the game.
   */
  @Test
  public void testPlayGameProperlyTransmitsWithAllWinningMoves() {
    initMockModelTests(new StringReader("2 4 4 4 3 2 3 4 1 3 3 3 1 5 1 3 4 3 2 3 1 3 3 3 "
            + "6 3 4 3 5 1 5 3 3 1 5 1 5 4 5 2 5 1 5 3 5 6 5 4 7 5 5 5 7 3 7 5 4 5 6 5 7 5 5 5 2 "
            + "5 4 5 3 7 3 5 5 7 3 7 3 4 3 6 3 7 3 5 5 4 5 6 5 6 3 6 3 6 3 4 3 4 3 2 3 2 5 2 5 "
            + "2 5 4 4 4 4 6 6 4 4 4 4 3 4 5 4 6 4 4"));
    assertEquals(32, this.esmThreeMock.getScore());
    assertFalse(this.esmThreeMock.isGameOver());
    this.mockController.playGame();
    assertEquals(1, this.esmThreeMock.getScore());
    assertTrue(this.esmThreeMock.isGameOver());
  }

  /**
   * Test that playGame runs through the game from start to finish when given all the moves
   * to win the game.
   */
  @Test
  public void testPlayGameProperlyRendersGameWindowWithAllWinningMoves() {
    initMockModelTests(new StringReader("2 4 4 4 3 2 3 4 1 3 3 3 1 5 1 3 4 3 2 3 1 3 3 3 "
            + "6 3 4 3 5 1 5 3 3 1 5 1 5 4 5 2 5 1 5 3 5 6 5 4 7 5 5 5 7 3 7 5 4 5 6 5 7 5 5 5 2 "
            + "5 4 5 3 7 3 5 5 7 3 7 3 4 3 6 3 7 3 5 5 4 5 6 5 6 3 6 3 6 3 4 3 4 3 2 3 2 5 2 5 "
            + "2 5 4 4 4 4 6 6 4 4 4 4 3 4 5 4 6 4 4"));
    initControllerRenderTests();
    this.mockController.playGame();
    assertEquals(this.winGameWindow.toString(), this.out.toString());

  }

  /**
   * Test that playGame runs through the game from start to finish when given all the moves
   * to win the game, with intermediate invalid inputs.
   */
  @Test
  public void testPlayGameProperlyRendersGameWindowWithAllWinningMovesWithInValid() {
    initMockModelTests(new StringReader("2 4 4 4 | 3 2 3 4 | 1 3 3 3 | 1 5 1 3 | "
            + "4 3 2 3 | 1 3 3 3 | 6 3 4 3 | 5 1 5 3 | 3 1 5 1 | 5 4 5 2 | 5 1 5 3 | "
            + "5 6 5 4 | 7 5 5 5 | 7 3 7 5 | 4 5 6 5 | 7 5 5 5 | 2 5 4 5 | 3 7 3 5 | "
            + "5 7 3 7 | 3 4 3 6 | 3 7 3 5 | 5 4 5 6 | 5 6 3 6 | 3 6 3 4 | 3 4 3 2 | "
            + "3 2 5 2 | 5 2 5 4 | 4 4 4 6 | 6 4 4 4 | 4 3 4 5 | 4 6 4 4"));
    initControllerRenderTests();
    assertEquals(32, this.esmThreeMock.getScore());
    assertFalse(this.esmThreeMock.isGameOver());
    this.mockController.playGame();
    assertEquals(1, this.esmThreeMock.getScore());
    assertTrue(this.esmThreeMock.isGameOver());
  }

  /**
   * Test that controller properly renders the game window when user enters
   * q followed by three valid numbers.
   */
  @Test
  public void testControllerRendersQThenThreeValid() {
    initMockModelTests(new StringReader("q 4 4 4 q"));
    initControllerRenderTests();
    this.mockController.playGame();
    assertEquals(this.renderImmediateQuit.toString(), this.out.toString());
  }

  /**
   * Test that controller properly renders the game window when user enters
   * one valid number then q followed by two valid numbers.
   */
  @Test
  public void testControllerRendersValidThenQThenTwoValid() {
    initMockModelTests(new StringReader("2 q 4 4 q"));
    initControllerRenderTests();
    this.mockController.playGame();
    assertEquals(this.renderImmediateQuit.toString(), this.out.toString());
  }

  /**
   * Test that controller properly renders the game window when user enters
   * two valid numbers then q followed by one valid number.
   */
  @Test
  public void testControllerRendersTwoValidThenQThenOneValid() {
    initMockModelTests(new StringReader("2 4 q 4 q"));
    initControllerRenderTests();
    this.mockController.playGame();
    assertEquals(this.renderImmediateQuit.toString(), this.out.toString());
  }

  /**
   * Test that controller properly renders the game window when user enters
   * three valid numbers then q.
   */
  @Test
  public void testControllerRendersThreeValidQThenOneValid() {
    initMockModelTests(new StringReader("2 4 4 q q"));
    initControllerRenderTests();
    this.mockController.playGame();
    assertEquals(this.renderImmediateQuit.toString(), this.out.toString());
  }
}
