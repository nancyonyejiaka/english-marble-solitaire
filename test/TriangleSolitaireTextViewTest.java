import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link TriangleSolitaireTextView}.
 */
public class TriangleSolitaireTextViewTest {

  String fiveTriBoard =
                    "    _\n"
                  + "   O O\n"
                  + "  O O O\n"
                  + " O O O O\n"
                  + "O O O O O";
  MarbleSolitaireModel fiveTri = new TriangleSolitaireModel();
  MarbleSolitaireView fiveTriView = new TriangleSolitaireTextView(this.fiveTri);

  String sevenTriBoard =
          "      _\n"
                  + "     O O\n"
                  + "    O O O\n"
                  + "   O O O O\n"
                  + "  O O O O O\n"
                  + " O O O O O O\n"
                  + "O O O O O O O";
  MarbleSolitaireModel sevenTri = new TriangleSolitaireModel(7);
  MarbleSolitaireView sevenTriView = new TriangleSolitaireTextView(this.sevenTri);

  String fiveTriBoardOC =
          "    O\n"
                  + "   O O\n"
                  + "  O O _\n"
                  + " O O O O\n"
                  + "O O O O O";

  MarbleSolitaireModel fiveTriOC = new TriangleSolitaireModel(2, 2);
  MarbleSolitaireView fiveTriOCView = new TriangleSolitaireTextView(this.fiveTriOC);

  String sevenTriBoardOC =
          "      O\n"
                  + "     O O\n"
                  + "    O O O\n"
                  + "   O O O _\n"
                  + "  O O O O O\n"
                  + " O O O O O O\n"
                  + "O O O O O O O";
  MarbleSolitaireModel sevenTriOC = new TriangleSolitaireModel(7,3, 3);
  MarbleSolitaireView sevenTriOCView = new TriangleSolitaireTextView(this.sevenTriOC);

  /**
   * Tests that the TriangleSolitaireTextView constructor that takes a model throws an exception
   * when given a null model.
   */
  @Test(expected = IllegalArgumentException.class)
  public void TriangleMarbleSolitaireTextViewConstructorsDisallowsNullModel() {
    new TriangleSolitaireTextView(null);
  }

  /**
   * Tests that the TriangleSolitaireTextView constructor that takes a model throws an exception
   * when given a model null view.
   */
  @Test(expected = IllegalArgumentException.class)
  public void TriangleMarbleSolitaireTextViewConstructorsDisallowsNullView() {
    new TriangleSolitaireTextView(this.fiveTri, null);
  }

  /**
   * Tests that the TriangleSolitaireTextView constructor that takes a model throws an exception
   * when given a null model.
   */
  @Test(expected = IllegalArgumentException.class)
  public void TriangleMarbleSolitaireTextViewConstructorsDisallowsNullModelAndNullView() {
    new TriangleSolitaireTextView(null, null);
  }

  /**
   * Test for toString method for a European marble solitaire model.
   */
  @Test
  public void toStringTriWorks() {
    assertEquals(this.fiveTriBoard, this.fiveTriView.toString());
    assertEquals(this.sevenTriBoard, this.sevenTriView.toString());
    assertEquals(this.fiveTriBoardOC, this.fiveTriOCView.toString());
    assertEquals(this.sevenTriBoardOC, this.sevenTriOCView.toString());
  }

  /**
   * Test for toString method properly renders a European marble solitaire model after
   * a move has been made.
   */
  @Test
  public void toStringTriWorksAfterOneMove() {
    fiveTriOC.move(4, 4, 2, 2);
    assertEquals("    O\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O _\n"
            + "O O O O _", this.fiveTriOCView.toString());
  }
}