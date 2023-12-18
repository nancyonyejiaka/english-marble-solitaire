package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Represents the board of a Triangular Marble Solitaire model. Responsible for displaying
 * the game during gameplay
 */
public class TriangleSolitaireTextView extends AMarbleSolitaireView {

  /**
   * Construct a view for Triangular Solitaire with the given model  and view objects.
   * Queries the model and prints the board. Uses the specified appendable as the destination
   * of this text view.
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model, Appendable destination)
          throws IllegalArgumentException {
    super(model, destination);
  }

  /**
   * Construct a view for Triangular Marble Solitaire with the given model object.
   * Queries the model and prints the board. Uses the console as the default destination of
   * this text view.
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model)
          throws IllegalArgumentException {
    super(model, System.out);
  }

  /**
   * Draws a triangular marble solitaire model based on the following constraints:
   * (1) marble slots are represented as "O".
   * (2) empty slots are represented as " ".
   * (3) invalid slots are represented as " " if found in the margins within the plus-shaped board
   * and new lines if the slot is at the end of each row. There are no spaces after the last marble
   * in each row. Unlike rectangular models of marble solitaire, triangular models of marble
   * solitaire render spaces on the board based on the row number.
   *
   * @return the String representation of an English Solitaire Model
   */
  @Override
  public String toString() {
    StringBuilder board = new StringBuilder();

    for (int r = 0; r < this.model.getBoardSize(); r++) {
      StringBuilder rowBuilder = new StringBuilder();
      for (int c = 0; c < this.model.getBoardSize(); c++) {
        if (c == 0) {
          for (int spaces = this.model.getBoardSize() - 1 - r; spaces > 0; spaces--) {
            rowBuilder.append(" ");
          }
        }
        if ((r == this.model.getBoardSize() - 1) && (c == this.model.getBoardSize() - 1)) {
          rowBuilder.append(convertToSlotState(r, c));
        } else if (r == c) {
          rowBuilder.append(convertToSlotState(r, c)).append("\n");
        } else if (r > c) {
          rowBuilder.append(convertToSlotState(r, c)).append(" ");
        }
      }
      board.append(rowBuilder);
    }
    return board.toString();
  }
}
