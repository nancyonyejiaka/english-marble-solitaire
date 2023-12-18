package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * This class represents variants of the marble solitaire board, including the english, european
 * and triangular invariants.
 */
public abstract class ASolitaireBoard implements MarbleSolitaireModel {
  protected final int length;
  protected int sRow;
  protected int sColumn;
  protected SlotState[][] marbleFormation;


  /**
   * Construct a new instance of a marble solitaire board with a specified board length, and
   * row and column values representing the position of the initial empty slot.
   *
   * @param length  the dimensions of the marble solitaire board
   * @param sRow    the row value for the position of the initial empty slot
   * @param sColumn the colum value for the initial empty slot
   * @throws IllegalArgumentException if the specified length, row, or column values are invalid
   *                                  based on the restrictions of the board type
   */
  public ASolitaireBoard(int length, int sRow, int sColumn) throws IllegalArgumentException {
    if (!validLength(length, getType())) {
      throw new IllegalArgumentException("Invalid board length.");
    }
    this.length = length;
    if (!validPosition(sRow, sColumn, length, getType())) {
      throw new IllegalArgumentException(
              String.format("Invalid empty cell position (%d,%d)", sRow, sColumn));
    }
    this.sRow = sRow;
    this.sColumn = sColumn;
    this.marbleFormation = initBoard();
  }

  /**
   * Construct a new instance of a marble solitaire board with a specified board length.
   *
   * @param length the dimensions of the marble solitaire board
   * @throws IllegalArgumentException if the specified length, row, or column values are invalid
   *                                  based on the restrictions of the board type
   */
  public ASolitaireBoard(int length) throws IllegalArgumentException {
    this(length, ((length * 3) - 2) / 2, ((length * 3) - 2) / 2);
  }

  /**
   * Move a single marble from a given position to another given position.
   * A move is valid only if the from and to positions are valid. Specific
   * implementations may place additional constraints on the validity of a move.
   *
   * @param fromRow the row number of the position to be moved from
   * @param fromCol the column number of the position to be moved from
   * @param toRow   the row number of the position to be moved to
   * @param toCol   the column number of the position to be moved to
   * @throws IllegalArgumentException if the move is not possible based on the constraints of the
   *                                  model type
   */
  public void move(int fromRow, int fromCol, int toRow, int toCol)
          throws IllegalArgumentException {
    if (hasValidMove(fromRow, fromCol, toRow, toCol)) {
      moveViolation(fromRow, fromCol, toRow, toCol);
      marbleFormation[fromRow][fromCol] = SlotState.Empty;
      marbleFormation[toRow][toCol] = SlotState.Marble;
      removeMarble(fromRow, fromCol, toRow, toCol);
    } else {
      moveViolation(fromRow, fromCol, toRow, toCol);
      throw new IllegalArgumentException("Cannot move from (" + (fromRow + 1) + "," + (fromCol + 1)
              + ") to (" + (toRow + 1) + "," + (toCol + 1) + ")! ");
    }
  }

  /**
   * Determine and return if the game is over or not. A game is over if no
   * more moves can be made.
   *
   * @return true if the game is over, false otherwise
   */
  public boolean isGameOver() {
    if (getScore() == 1) {
      return true;
    } else {
      for (int row = 0; row < getBoardSize(); row++) {
        for (int col = 0; col < getBoardSize(); col++) {
          if (validRowCol(row, col) && marbleSlot(row, col)) {
            if (hasValidMove(row, col, row, col + 2)
                    || hasValidMove(row, col, row + 2, col)
                    || hasValidMove(row, col, row, col - 2)
                    || hasValidMove(row, col, row - 2, col)) {
              return false;
            }
          }
        }
      }
    }
    return true;
  }

  /**
   * Return the size of this board. The size is roughly the longest dimension of a board
   *
   * @return the size as an integer
   */
  public int getBoardSize() {
    return (this.length * 3) - 2;
  }

  /**
   * Get the state of the slot at a given position on the board.
   *
   * @param row the row of the position sought, starting at 0
   * @param col the column of the position sought, starting at 0
   * @return the state of the slot at the given row and column
   * @throws IllegalArgumentException if the row or the column are beyond
   *                                  the dimensions of the board
   */
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    try {
      return this.marbleFormation[row][col];
    } catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException(String.format("Slot (%d,%d) is not a valid "
              + "position on the board", row, col));
    }
  }

  /**
   * Return the number of marbles currently on the board.
   *
   * @return the number of marbles currently on the board
   */
  public int getScore() {
    int score = 0;
    for (int row = 0; row < getBoardSize(); row++) {
      for (int column = 0; column < getBoardSize(); column++) {
        if (this.marbleFormation[row][column] == SlotState.Marble) {
          score++;
        }
      }
    }
    return score;
  }

  /**
   * Verify that the move specified by the from and to positions is valid based on the
   * constraints the appropriate marble solitaire board. This method primarily handles throwing
   * exceptions based on the violations made by the specified move.
   *
   * @param fromRow the row number of the position to be moved from
   * @param fromCol the column number of the position to be moved from
   * @param toRow   the row number of the position to be moved to
   * @param toCol   the column number of the position to be moved to
   * @throws IllegalArgumentException if the specified move is not within the bounds of the board
   *                                  or if the to position is not empty
   *                                  or if the from position does not have a marble
   *                                  or if there is not a marble between the from and to positions
   *                                  or if the from and to positions are an inappropriate distance
   */
  private void moveViolation(int fromRow, int fromCol, int toRow, int toCol)
          throws IllegalArgumentException {

    if (!withinBounds(fromRow, fromCol, this.length, getType())
            || !withinBounds(toRow, toCol, this.length, getType())) {
      throw new IllegalArgumentException("One of the specified positions "
              + "is not within the bounds of the board.");
    }
    if (!emptySlot(toRow, toCol)) {
      throw new IllegalArgumentException("Marble destination must be an empty slot.");
    }
    if (!marbleSlot(fromRow, fromCol)) {
      throw new IllegalArgumentException("There must be a marble in the move origin.");
    }

    // handles exceptions regarding the distance between the from and to positions
    checkDistance(fromRow, fromCol, toRow, toCol);
  }

  /**
   * Check that the from and to positions are an acceptable distance from each other based on
   * the constraints set by the board type.
   *
   * @param fromRow the row number of the position to be moved from
   * @param fromCol the column number of the position to be moved from
   * @param toRow   the row number of the position to be moved to
   * @param toCol   the column number of the position to be moved to
   * @throws IllegalArgumentException if the from and to positions are not exactly two slots away
   *                                  from each other in English and European board or
   *                                  if the from and to positions are not two or two-root-two
   *                                  slots away from each other in a triangular board
   */
  private void checkDistance(int fromRow, int fromCol, int toRow, int toCol)
          throws IllegalArgumentException {
    double distance = Math.sqrt(Math.pow(toCol - fromCol, 2) + Math.pow(toRow - fromRow, 2));
    switch (getType()) {
      case "English":
      case "European":
        if (distance != 2.0) {
          if (distance > 2.0) {
            throw new IllegalArgumentException("The specified move origin and move "
                    + "destination are too far apart.");
          } else {
            throw new IllegalArgumentException("The specified move origin and move "
                    + "destination are too close together.");
          }
        }
        break;
      case "Triangle":
        int rowDiff = fromRow - toRow;
        int colDiff = fromCol - toCol;
        if ((rowDiff == 2 && colDiff == -2) || (rowDiff == -2 && colDiff == 2)) {
          throw new IllegalArgumentException("Invalid move. Cannot move diagonally.");
        }
        if (!(distance == (2 * Math.sqrt(2)) || distance == 2.0)) {
          throw new IllegalArgumentException("The specified move origin and move destination "
                  + "are an invalid distance apart.");
        }
        break;
      default: // unhandled board variants
        throw new IllegalArgumentException("Oops! Something went wrong...");
    }
  }

  /**
   * Determine if moving from the first specified position of the second specified position would
   * be a valid move in an English Solitaire Model.
   *
   * @param fromRow the row number of the position to be moved from
   * @param fromCol the column number of the position to be moved from
   * @param toRow   the row number of the position to be moved to
   * @param toCol   the column number of the position to be moved to
   * @return true if the move from the from position to the second position is valid
   */
  protected boolean hasValidMove(int fromRow, int fromCol, int toRow, int toCol) {
    return validRowCol(fromRow, fromCol) && validRowCol(toRow, toCol)
            && marbleSlot(fromRow, fromCol)
            && emptySlot(toRow, toCol)
            && twoSlotsBetween(fromRow, fromCol, toRow, toCol)
            && marbleBetween(fromRow, fromCol, toRow, toCol);
  }

  /**
   * Determine whether the given row and column values are valid for a marble solitaire models.
   * Row and column values for marble solitaire models are valid if:
   * (1) the row and column values are each positive integers
   * (2) the row and column values are each less than the overall size of the board
   * (3) the row and column values are each within the bounds of the model
   * (4) in the case of triangular boards, the column must be less than or equal to the row
   *
   * @param row the row position of the empty slot
   * @param col the column position of the empty slot
   * @return true if the row and column values are valid for the empty slot of an
   *         English Solitaire Model
   */
  protected boolean validRowCol(int row, int col) {
    boolean valid = (row >= 0 && col >= 0
            && row < getBoardSize() && col < getBoardSize()
            && withinBounds(row, col, this.length, this.getType()));
    if ("Triangle".equals(getType())) {
      return valid && col <= row;
    }
    return valid;
  }

  /**
   * Determines if there is a marble in the slot between the two specified positions.
   *
   * @param fromRow the row of the first specified position
   * @param fromCol the column of the first specified position
   * @param toRow   the row of the second specified position
   * @param toCol   the column of the second specified position
   * @return true if there is a marble in the slot between the two specified positions
   */
  protected boolean marbleBetween(int fromRow, int fromCol, int toRow, int toCol) {
    if (fromCol == toCol) {
      if (fromRow > toRow) {
        return marbleSlot(toRow + 1, toCol);
      } else if (fromRow < toRow) {
        return marbleSlot(toRow - 1, toCol);
      }
    } else if (fromRow == toRow) {
      if (fromCol > toCol) {
        return marbleSlot(toRow, toCol + 1);
      } else if (fromCol < toCol) {
        return marbleSlot(toRow, toCol - 1);
      }
    }
    return false;
  }

  /**
   * Determines if there is a marble at the specified position.
   *
   * @param row the row of the specified position
   * @param col the column of the specified position
   * @return true if the specified position has a marble
   */
  protected boolean marbleSlot(int row, int col) {
    return getSlotAt(row, col) == SlotState.Marble;
  }

  /**
   * Determines if there is an empty at the specified position.
   *
   * @param row the row of the specified position
   * @param col the column of the specified position
   * @return true if the specified position has a marble
   */
  protected boolean emptySlot(int row, int col) {
    return getSlotAt(row, col) == SlotState.Empty;
  }

  /**
   * Determines if the two specified positions are exactly two positions away from each other.
   *
   * @param fromRow the row of the first specified position
   * @param fromCol the column of the first specified position
   * @param toRow   the row of the second specified position
   * @param toCol   the column of the second specified position
   * @return true if there are two positions between the two specified positions
   */
  private boolean twoSlotsBetween(int fromRow, int fromCol, int toRow, int toCol) {
    return (Math.abs(fromRow - toRow) == 2) || (Math.abs(fromCol - toCol) == 2);
  }

  /**
   * Removes the marble between the two specified positions by changing the slot state from
   * Marble to Empty.
   *
   * @param fromRow the row of the first specified position
   * @param fromCol the column of the first specified position
   * @param toRow   the row of the second specified position
   * @param toCol   the column of the second specifed position
   */
  private void removeMarble(int fromRow, int fromCol, int toRow, int toCol) {
    if (fromCol == toCol) {
      if (fromRow > toRow) {
        marbleFormation[toRow + 1][toCol] = SlotState.Empty;
      } else if (fromRow < toRow) {
        marbleFormation[toRow - 1][toCol] = SlotState.Empty;
      }
    } else if (fromRow == toRow) {
      if (fromCol > toCol) {
        marbleFormation[toRow][toCol + 1] = SlotState.Empty;
      } else if (fromCol < toCol) {
        marbleFormation[toRow][toCol - 1] = SlotState.Empty;
      }
    } else if (fromRow - 2 == toRow) {
      marbleFormation[fromRow - 1][fromCol - 1] = SlotState.Empty;
    } else if (fromRow + 2 == toRow) {
      marbleFormation[fromRow + 1][fromCol + 1] = SlotState.Empty;
    }
  }

  /**
   * Determine the type of marble solitaire board this class represents.
   *
   * @return the String "English", "European" or "Triangle" depending on the board type
   */
  protected abstract String getType();

  /**
   * Determine if the given length is valid for a marble solitaire board of the given type.
   *
   * @param l    the dimension of the board
   * @param type the type of marble solitaire board, either English, European, or Triangle
   * @return true if the length is valid for the board type, false otherwise
   */
  private boolean validLength(int l, String type) {
    boolean valid = false;
    switch (type) {
      case "English":
      case "European":
        valid = (l > 1) && (l % 2 > 0);
        break;
      case "Triangle":
        valid = l > 0;
        break;
      default:
        break;
    }
    return valid;
  }

  /**
   * Determine if the given row and column values represent a valid position for the initial
   * empty position in a marble solitaire board of the given type.
   *
   * @param row  the row number of the empty slot
   * @param col  the column number of the empty slot
   * @param l    the dimension of the board
   * @param type the type of marble solitaire board, either English, European, or Triangle
   * @return true if the position specified by the row and column is valid for the board type,
   *         false otherwise
   */
  protected boolean validPosition(int row, int col, int l, String type) {
    boolean valid = false;
    switch (type) {
      case "English":
      case "European":
        valid = (row >= 0 && col >= 0
                && row < getBoardSize() && col < getBoardSize()
                && withinBounds(row, col, l, type));
        break;
      case "Triangle":
        valid = withinBounds(row, col, l, type);
        break;
      default:
        break;
    }
    return valid;
  }


  /**
   * Determines whether the given row and column values are within the plus-shaped marble
   * formation of an English Solitaire model.
   *
   * @param row  the row position of the empty slot
   * @param col  the column position of the empty slot
   * @param l    the length of the board
   * @param type the type of board
   * @return true of the specified row and column represent a position not within the model
   */
  protected boolean withinBounds(int row, int col, int l, String type) {
    boolean within = false;
    switch (type) {
      case "English":
        within = !((row < 0 || row >= getBoardSize() || col < 0 || col >= getBoardSize())
                || (row < this.length - 1 && (col < this.length - 1
                || col > getBoardSize() - this.length)
                || (row > getBoardSize() - this.length && (col < this.length - 1
                || col > getBoardSize() - this.length))));
        break;
      case "Triangle":
        within = row >= 0 && col >= 0 && row < this.length && col <= row;
        break;
      case "European":
        int margin = getBoardSize() - (this.length - 1);
        int primaryMargin = row - margin;
        int secondaryMargin = col - margin;
        within = !((row + col < this.length - 1)
                || (row + col >= getBoardSize() + 2 * (this.length - 1))
                || (col <= primaryMargin)
                || (row <= secondaryMargin));
        break;
      default:
        break;
    }
    return within;
  }

  /**
   * Initializes the marble formation of a Marble Solitaire model as a
   * two-dimensional ArrayList.
   *
   * @return an ArrayList containing ArrayLists of Strings, with each String representing a marble
   */
  protected SlotState[][] initBoard() {
    SlotState[][] marbles =
            new SlotState[getBoardSize()][getBoardSize()];

    for (int row = 0; row < getBoardSize(); row++) {
      for (int col = 0; col < getBoardSize(); col++) {
        if (row == this.sRow && col == this.sColumn) {
          marbles[row][col] = SlotState.Empty;
        } else if (marbleCondition(row, col, getType())) {
          marbles[row][col] = SlotState.Marble;
        } else {
          marbles[row][col] = SlotState.Invalid;
        }
      }
    }
    return marbles;
  }

  /**
   * Determine if a marble belongs in the position specified by the given row and column values.
   * based on the given type of the marble solitaire board.
   * @param r the row number of the specified position
   * @param c the column number of the specified position
   * @param type the type of board
   * @return true if the specified position requires a marble, false otherwise
   */
  protected boolean marbleCondition(int r, int c, String type) {
    boolean needsMarble = false;
    switch (type) {
      case "English":
        needsMarble = withinBounds(r, c, this.length, "English");
        break;
      case "Triangle":
        needsMarble = c <= r;
        break;
      case "European":
        needsMarble = withinBounds(r, c, this.length, "European");
        break;
      default:
        break;
    }
    return needsMarble;
  }
}
