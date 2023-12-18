import org.junit.Test;

import java.io.IOException;

import cs3500.marblesolitaire.controller.FailingAppendable;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link MarbleSolitaireTextView}.
 */
public class MarbleSolitaireTextViewTest {
  String threeESMBoard =
          "    O O O\n"
                  + "    O O O\n"
                  + "O O O O O O O\n"
                  + "O O O _ O O O\n"
                  + "O O O O O O O\n"
                  + "    O O O\n"
                  + "    O O O";
  MarbleSolitaireModel threeESM = new EnglishSolitaireModel();
  MarbleSolitaireView threeESMView = new MarbleSolitaireTextView(this.threeESM);

  String fiveESMBoard =
          "        O O O O O\n"
                  + "        O O O O O\n"
                  + "        O O O O O\n"
                  + "        O O O O O\n"
                  + "O O O O O O O O O O O O O\n"
                  + "O O O O O O O O O O O O O\n"
                  + "O O O O O O _ O O O O O O\n"
                  + "O O O O O O O O O O O O O\n"
                  + "O O O O O O O O O O O O O\n"
                  + "        O O O O O\n"
                  + "        O O O O O\n"
                  + "        O O O O O\n"
                  + "        O O O O O";
  MarbleSolitaireModel fiveESM = new EnglishSolitaireModel(5);
  MarbleSolitaireView fiveESMView = new MarbleSolitaireTextView(this.fiveESM);

  String threeEuroBoard =
          "    O O O\n"
                  + "  O O O O O\n"
                  + "O O O O O O O\n"
                  + "O O O _ O O O\n"
                  + "O O O O O O O\n"
                  + "  O O O O O\n"
                  + "    O O O";
  MarbleSolitaireModel threeEuro = new EuropeanSolitaireModel();
  MarbleSolitaireView threeEuroView = new MarbleSolitaireTextView(this.threeEuro);

  String fiveEuroBoard =
          "        O O O O O\n"
                  + "      O O O O O O O\n"
                  + "    O O O O O O O O O\n"
                  + "  O O O O O O O O O O O\n"
                  + "O O O O O O O O O O O O O\n"
                  + "O O O O O O O O O O O O O\n"
                  + "O O O O O O _ O O O O O O\n"
                  + "O O O O O O O O O O O O O\n"
                  + "O O O O O O O O O O O O O\n"
                  + "  O O O O O O O O O O O\n"
                  + "    O O O O O O O O O\n"
                  + "      O O O O O O O\n"
                  + "        O O O O O";
  MarbleSolitaireModel fiveEuro = new EuropeanSolitaireModel(5);
  MarbleSolitaireView fiveEuroView = new MarbleSolitaireTextView(this.fiveEuro);

  String threeESMBoardOffCenter =
          "    O O O\n"
                  + "    O O O\n"
                  + "O O O O O O O\n"
                  + "O O O O O O O\n"
                  + "O O O O O O O\n"
                  + "    O O _\n"
                  + "    O O O";
  MarbleSolitaireModel threeESMOffCenter = new EnglishSolitaireModel(5, 4);
  MarbleSolitaireView threeESMViewOffCenter = new MarbleSolitaireTextView(this.threeESMOffCenter);

  String fiveESMBoardOffCenter =
          "        O O O O O\n"
                  + "        O O O O O\n"
                  + "        O O O O O\n"
                  + "        O O O O O\n"
                  + "O O O O _ O O O O O O O O\n"
                  + "O O O O O O O O O O O O O\n"
                  + "O O O O O O O O O O O O O\n"
                  + "O O O O O O O O O O O O O\n"
                  + "O O O O O O O O O O O O O\n"
                  + "        O O O O O\n"
                  + "        O O O O O\n"
                  + "        O O O O O\n"
                  + "        O O O O O";
  MarbleSolitaireModel fiveESMOffCenter
          = new EnglishSolitaireModel(5, 4, 4);
  MarbleSolitaireView fiveESMViewOffCenter
          = new MarbleSolitaireTextView(this.fiveESMOffCenter);

  String threeEuroBoardOC =
          "    O O O\n"
                  + "  _ O O O O\n"
                  + "O O O O O O O\n"
                  + "O O O O O O O\n"
                  + "O O O O O O O\n"
                  + "  O O O O O\n"
                  + "    O O O";
  MarbleSolitaireModel threeEuroOC = new EuropeanSolitaireModel(1, 1);
  MarbleSolitaireView threeEuroOCView = new MarbleSolitaireTextView(this.threeEuroOC);

  String fiveEuroBoardOC =
          "        O O O O O\n"
                  + "      O O O O O O O\n"
                  + "    _ O O O O O O O O\n"
                  + "  O O O O O O O O O O O\n"
                  + "O O O O O O O O O O O O O\n"
                  + "O O O O O O O O O O O O O\n"
                  + "O O O O O O O O O O O O O\n"
                  + "O O O O O O O O O O O O O\n"
                  + "O O O O O O O O O O O O O\n"
                  + "  O O O O O O O O O O O\n"
                  + "    O O O O O O O O O\n"
                  + "      O O O O O O O\n"
                  + "        O O O O O";
  MarbleSolitaireModel fiveEuroOC = new EuropeanSolitaireModel(5, 2, 2);
  MarbleSolitaireView fiveEuroOCView = new MarbleSolitaireTextView(this.fiveEuroOC);


  MarbleSolitaireModel modelForFake = new EnglishSolitaireModel();
  Appendable fakeOutput = new StringBuilder();
  MarbleSolitaireView fakeView
          = new MarbleSolitaireTextView(this.modelForFake, this.fakeOutput);
  Appendable fakeAppendable = new FailingAppendable();
  MarbleSolitaireView fakeViewThrows
          = new MarbleSolitaireTextView(this.modelForFake, this.fakeAppendable);

  /**
   * Tests that the MarbleSolitaireTextView constructor that takes a model throws an exception
   * when given a null model.
   */
  @Test(expected = IllegalArgumentException.class)
  public void marbleSolitaireTextViewConstructorsDisallowsNullModel() {
    new MarbleSolitaireTextView(null);
  }

  /**
   * Tests that the MarbleSolitaireTextView constructor that takes a model throws an exception
   * when given a model null view.
   */
  @Test(expected = IllegalArgumentException.class)
  public void marbleSolitaireTextViewConstructorsDisallowsNullView() {
    new MarbleSolitaireTextView(this.threeESM, null);
  }

  /**
   * Tests that the MarbleSolitaireTextView constructor that takes a model throws an exception
   * when given a null model.
   */
  @Test(expected = IllegalArgumentException.class)
  public void marbleSolitaireTextViewConstructorsDisallowsNullModelAndNullView() {
    new MarbleSolitaireTextView(null, null);
  }

  /**
   * Test for toString method for English marble solitaire model.
   */
  @Test
  public void toStringWorks() {
    assertEquals(this.threeESMBoard, this.threeESMView.toString());
    assertEquals(this.fiveESMBoard, this.fiveESMView.toString());
    assertEquals(this.threeESMBoardOffCenter, this.threeESMViewOffCenter.toString());
    assertEquals(this.fiveESMBoardOffCenter, this.fiveESMViewOffCenter.toString());
  }

  /**
   * Test for toString method for European marble solitaire model.
   */
  @Test
  public void toStringEuroWorks() {
    assertEquals(this.threeEuroBoard, this.threeEuroView.toString());
    assertEquals(this.fiveEuroBoard, this.fiveEuroView.toString());
    assertEquals(this.threeEuroBoardOC, this.threeEuroOCView.toString());
    assertEquals(this.fiveEuroBoardOC, this.fiveEuroOCView.toString());
  }

  /**
   * Test that toString properly draws the board after one move has been made on an
   * English marble solitaire model.
   */
  @Test
  public void toStringWorksAfterOneMove() {
    this.threeESM.move(5, 3, 3, 3);
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "    O _ O\n"
            + "    O O O", this.threeESMView.toString());
  }

  /**
   * Test that toString properly draws the board after one move has been made on a
   * European marble solitaire model.
   */
  @Test
  public void toStringEuroWorksAfterOneMove() {
    this.threeEuro.move(5, 3, 3, 3);
    assertEquals("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "  O O _ O O\n"
            + "    O O O", this.threeEuroView.toString());
  }

  /**
   * Tests that render board works for a marble solitaire view with the initial state of the model.
   *
   * @throws IOException if an I/O error occurs through append
   */
  @Test
  public void renderBoardWorks() throws IOException {
    assertEquals("", this.fakeOutput.toString());
    this.fakeView.renderBoard();
    assertEquals(this.threeESMBoard, this.fakeOutput.toString());
  }

  /**
   * Tests that render board works for a marble solitaire view that has undergone one move.
   *
   * @throws IOException if an I/O error occurs through append
   */
  @Test
  public void renderBoardWorksAfterOneMove() throws IOException {
    assertEquals("", this.fakeOutput.toString());
    this.modelForFake.move(5, 3, 3, 3);
    this.fakeView.renderBoard();
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "    O _ O\n"
            + "    O O O", this.fakeOutput.toString());
  }

  /**
   * Tests that the renderBoard methods throws an exception.
   *
   * @throws IOException if an I/O error occurs through append
   */
  @Test(expected = IOException.class)
  public void renderBoardThrowsIOException() throws IOException {
    fakeViewThrows.renderBoard();
  }

  /**
   * Tests that the renderMessage methods throws an exception.
   *
   * @throws IOException if an I/O error occurs through append
   */
  @Test(expected = IOException.class)
  public void renderMessageThrowsIOException() throws IOException {
    fakeViewThrows.renderMessage("Throw exception.");
  }

  /**
   * Test that renderMessage methods works.
   *
   * @throws IOException if an I/O error occurs through append
   */
  @Test
  public void renderMessageWorks() throws IOException {
    assertEquals("", this.fakeOutput.toString());
    fakeView.renderMessage("Enter desired move: ");
    assertEquals("Enter desired move: ", this.fakeOutput.toString());
  }

}