package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * This abstract class represents the board of a Marble Solitaire model. It is responsible for
 * displaying the game during gameplay
 */
public abstract class AMarbleSolitaireView implements MarbleSolitaireView {
  protected final MarbleSolitaireModelState model;
  protected final Appendable output;

  /**
   * Construct a view for marble solitaire with the given model and view objects.
   * Queries the model and prints the board. Uses the specified appendable as the destination
   * of this text view.
   */
  public AMarbleSolitaireView(MarbleSolitaireModelState model, Appendable destination)
          throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot null. Please provide a valid model");
    }
    if (destination == null) {
      throw new IllegalArgumentException("Appendable object cannot null. "
              + "Please provide a valid appendable");
    }
    this.model = model;
    this.output = destination;
  }

  /**
   * Construct a view for marble solitaire with the given model object. Queries the model and
   * prints the board. Uses the console as the default destination of this text view.
   */
  public AMarbleSolitaireView(MarbleSolitaireModelState model) throws IllegalArgumentException {
    this(model, System.out);
  }

  /**
   * Draws a marble solitaire model based on the following constraints:
   * (1) marble slots are represented as "O".
   * (2) empty slots are represented as "_".
   * (3) invalid slots are represented as " " if found in the margins within the plus-shaped board
   * and new lines if the slot is at the end of each row. There are no spaces after the last marble
   * in each row.
   *
   * @return the String representation of an English Solitaire Model
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (int row = 0; row < model.getBoardSize(); row++) {
      boolean start = false;
      for (int column = 0; column < model.getBoardSize(); column++) {
        if (model.getSlotAt(row, column) != MarbleSolitaireModelState.SlotState.Invalid) {
          start = true;
        } else if (model.getSlotAt(row, column) == MarbleSolitaireModelState.SlotState.Invalid
                && start) {
          break;
        }
        if (column > 0) {
          builder.append(" ");
        }
        builder.append(convertToSlotState(row, column));
      }
      if (row != model.getBoardSize() - 1) {
        builder.append("\n");
      }
    }
    return builder.toString();
  }

  @Override
  public void renderBoard() throws IOException {
    output.append(this.toString());
  }

  @Override
  public void renderMessage(String message) throws IOException {
    output.append(message);
  }

  /**
   * Determines which String to print based on the SlotState of the specified row and position in
   * an English Solitaire model.
   *
   * @param row    the row of the English Solitaire Model
   * @param column the column of the English Solitaire Model
   * @return a String either representing an empty slot, a new line
   */
  protected String convertToSlotState(int row, int column) {
    String toReturn = "";
    switch (model.getSlotAt(row, column)) {
      case Empty:
        toReturn = "_";
        break;
      case Marble:
        toReturn = "O";
        break;
      case Invalid:
        toReturn = " ";
        break;
      default:
        throw new IllegalArgumentException("Invalid Slot State.");
    }
    return toReturn;
  }
}
