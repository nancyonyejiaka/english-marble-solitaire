package cs3500.marblesolitaire.view;

import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link MarbleSolitaireTextView}.
 */
public class MarbleSolitaireTextViewTest {

  /**
   * Tests for empty MarbleSolitaireTextView constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void marbleSolitaireTextViewConstructorsDisallowsNull() {
    new MarbleSolitaireTextView(null);
  }

  /**
   * Test for toString method.
   */
  @Test
  public void toStringWorks() {
    String threeESMBoard =
            "    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O O O _ O O O\n"
                    + "O O O O O O O\n"
                    + "    O O O\n"
                    + "    O O O";
    MarbleSolitaireModelState threeESM = new EnglishSolitaireModel();
    MarbleSolitaireView threeESMView = new MarbleSolitaireTextView(threeESM);

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
    MarbleSolitaireModelState fiveESM = new EnglishSolitaireModel(5);
    MarbleSolitaireView fiveESMView = new MarbleSolitaireTextView(fiveESM);

    String threeESMBoardOffCenter =
            "    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O O O O O O O\n"
                    + "O O O O O O O\n"
                    + "    O O _\n"
                    + "    O O O";
    MarbleSolitaireModelState threeESMOffCenter = new EnglishSolitaireModel(5, 4);
    MarbleSolitaireView threeESMViewOffCenter = new MarbleSolitaireTextView(threeESMOffCenter);

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
    MarbleSolitaireModelState fiveESMOffCenter
            = new EnglishSolitaireModel(5, 4, 4);
    MarbleSolitaireView fiveESMViewOffCenter = new MarbleSolitaireTextView(fiveESMOffCenter);

    assertEquals(threeESMBoard, threeESMView.toString());
    assertEquals(fiveESMBoard, fiveESMView.toString());
    assertEquals(threeESMBoardOffCenter, threeESMViewOffCenter.toString());
    assertEquals(fiveESMBoardOffCenter, fiveESMViewOffCenter.toString());
  }
}