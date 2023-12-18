package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Represents the board of an EnglishSolitaireModel. Responsible for displaying the game during
 * gameplay
 */
public class MarbleSolitaireTextView implements MarbleSolitaireView {
  private final MarbleSolitaireModelState model;

  /**
   * Constructs a view for English Solitaire with the given model object. Queries the model and
   * prints the board.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot null. Please provide a valid model");
    }
    this.model = model;
  }

  /**
   * Draws an English Solitaire model based on the following constraints:
   * (1) marble slots are represented as "O".
   * (2) empty slots are represented as " ".
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
        builder.append(covertSlotState(row, column));
      }
      if (row != model.getBoardSize() - 1) {
        builder.append("\n");
      }
    }
    System.out.println(builder);
    return builder.toString();
  }

  /**
   * Determines which String to print based on the SlotState of the specified row and position in
   * an English Solitaire model.
   *
   * @param row    the row of the English Solitaire Model
   * @param column the column of the English Solitaire Model
   * @return a String either representing an empty slot, a new line
   */
  private String covertSlotState(int row, int column) {
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
