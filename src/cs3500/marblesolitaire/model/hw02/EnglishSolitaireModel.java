package cs3500.marblesolitaire.model.hw02;

/**
 * This class represents the model for English Solitaire. The model maintains the state
 * of the game and allows a client to specify moves.
 */
public class EnglishSolitaireModel implements MarbleSolitaireModel {
  private final int armThickness;
  private final int sRow;
  private final int sColumn;
  private final SlotState[][] marbleFormation;

  /**
   * Constructs a model for English Solitaire with a default arm thickness of 3 and an empty slot
   * at the center of the model.
   */
  public EnglishSolitaireModel() {
    this.armThickness = 3;
    this.sRow = 3;
    this.sColumn = 3;
    this.marbleFormation = initializeMarbleFormation();
  }

  /**
   * Constructs a model for English Solitaire with a default arm thickness of 3 and an empty slot
   * at the specified position.
   *
   * @param sRow    the row position of the empty slot
   * @param sColumn the column position of the empty slot
   * @throws IllegalArgumentException if the specified position does not exist on the model
   */
  public EnglishSolitaireModel(int sRow, int sColumn) {
    this.armThickness = 3;
    if (!validRowCol(sRow, sColumn)) {
      throw new IllegalArgumentException(
              String.format("Invalid empty cell position (%d,%d)", sRow, sColumn));
    }

    this.sRow = sRow;
    this.sColumn = sColumn;
    this.marbleFormation = initializeMarbleFormation();
  }

  /**
   * Constructs a model for English Solitaire with a specified arm thickness.
   *
   * @param armThickness the number of marbles on the top row
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number
   */
  public EnglishSolitaireModel(int armThickness) {
    if (!validArmThickness(armThickness)) {
      throw new IllegalArgumentException(String.format("Invalid arm thickness: %d. "
              + "Arm thickness must be a positive, odd number.", armThickness));
    }

    this.armThickness = armThickness;
    this.sRow = getBoardSize() / 2;
    this.sColumn = this.sRow;
    this.marbleFormation = initializeMarbleFormation();
  }

  /**
   * Constructs a model for English Solitaire with a specified arm thickness and an empty slot
   * at the specified position.
   *
   * @param armThickness the number of marbles on the top row
   * @param sRow         the row position of the empty slot
   * @param sColumn      the column position of the empty slot
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number or
   *                                  if the empty cell position does not exist on the model
   */
  public EnglishSolitaireModel(int armThickness, int sRow, int sColumn) {
    if (!validArmThickness(armThickness)) {
      throw new IllegalArgumentException("Invalid arm thickness: %d. "
              + "Arm thickness must be a positive, odd number.");
    }
    this.armThickness = armThickness;
    if (!validRowCol(sRow, sColumn)) {
      throw new IllegalArgumentException(
              String.format("Invalid empty cell position (%d,%d)", sRow, sColumn));
    }
    this.sRow = sRow;
    this.sColumn = sColumn;
    this.marbleFormation = initializeMarbleFormation();
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
   * @throws IllegalArgumentException if the move is not possible
   */
  public void move(int fromRow, int fromCol, int toRow, int toCol) {
    if (validMove(fromRow, fromCol, toRow, toCol)) {
      marbleFormation[fromRow][fromCol] = SlotState.Empty;
      marbleFormation[toRow][toCol] = SlotState.Marble;
      removeMarble(fromRow, fromCol, toRow, toCol);
    } else {
      throw new IllegalArgumentException(
              String.format("Invalid move attempt. Cannot move from (%d,%d) to (%d,%d)",
                      fromRow, fromCol, toRow, toCol));
    }
  }

  /**
   * Determine and return if the game is over or not. A game is over if no
   * more moves can be made.
   *
   * @return true if the game is over, false otherwise
   */
  public boolean isGameOver() {
    boolean noMovesLeft = true;
    for (int row = 0; row < getBoardSize(); row++) {
      for (int col = 0; col < getBoardSize(); col++) {
        if (validMove(row, col, row, col + 2) || validMove(row, col, row + 2, col)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Return the size of this board. The size is roughly the longest dimension of a board.
   *
   * @return the size as an integer
   */
  public int getBoardSize() {
    return (armThickness * 3) - 2;
  }

  /**
   * Get the state of the slot at a given position on the board.
   *
   * @param row the row of the position sought, starting at 0
   * @param col the column of the position sought, starting at 0
   * @return the state of the slot at the given row and column
   * @throws IllegalArgumentException if the row or the column are beyond
   *                                        the dimensions of the board
   */
  public SlotState getSlotAt(int row, int col) {
    if (row > getBoardSize() - 1 || col > getBoardSize() - 1 || row < 0 || col < 0) {
      throw new IllegalArgumentException();
    }
    return this.marbleFormation[row][col];
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
   * Determines whether the given arm thickness is valid for an English Solitaire model. An
   * arm thickness is valid if it is a positive, odd number
   *
   * @param thickness the specified arm thickness for an English Marble Solitaire Model
   * @return true if the given arm thickness meets the above requirements
   */
  private boolean validArmThickness(int thickness) {
    return ((thickness > 1) && (thickness % 2 > 0));
  }

  /**
   * Determines whether the given row and column values are valid for an English Solitaire model.
   * Row and column values for an English Solitaire model are valid if:
   * (1) the row and column values are positive integers
   * (2) the row and column values are each less than or equal to twice the arm thickness
   * (3) the column value is 0 or 1 while the row number is 0 or 1
   * (4) the column value is arm thickness or arm thickness - 1 while the row number is arm
   * thickness or arm thickness - 1
   *
   * @param row the row position of the empty slot
   * @param col the column position of the empty slot
   * @return true if the row and column values are valid for the empty slot of an
   *         English Solitaire Model
   */
  private boolean validRowCol(int row, int col) {
    int overhang = this.armThickness - 1;
    int modelLength = getBoardSize();
    return (row >= 0 && col >= 0
            && row < modelLength && col < modelLength
            && !outOfBoundsRowCol(row, col));
  }

  /**
   * Determines whether the given row and column values are within the plus-shaped marble
   * formation of an English Solitaire model.
   *
   * @param row the row position of the empty slot
   * @param col the column position of the empty slot
   * @return true of the specified row and column represent a position not within the model
   */
  private boolean outOfBoundsRowCol(int row, int col) {
    int overhang = this.armThickness - 1;
    int modelLength = getBoardSize();

    int upperMarginIndex = 0;
    int lowerMarginIndex = modelLength - overhang;
    boolean upperMarginComparison = row >= upperMarginIndex && row < overhang;
    boolean lowerMarginComparison = row >= lowerMarginIndex && row < modelLength;
    boolean leftMostMarginComparison = col >= upperMarginIndex && col < overhang;
    boolean rightMostMarginComparison = col >= lowerMarginIndex && col < modelLength;

    return (upperMarginComparison && leftMostMarginComparison)
            || (upperMarginComparison && rightMostMarginComparison)
            || (lowerMarginComparison && leftMostMarginComparison)
            || (lowerMarginComparison && rightMostMarginComparison);
  }

  /**
   * Initializes the plus-shaped marble formation of an English Solitaire model as a
   * two-dimensional ArrayList.
   *
   * @return an ArrayList containing ArrayLists of Strings, with each String representing a marble
   */
  private SlotState[][] initializeMarbleFormation() {
    int esmLength = getBoardSize();
    int esmOverhang = this.armThickness - 1;
    SlotState[][] marbleFormation =
            new SlotState[esmLength][esmLength];

    for (int row = 0; row < esmLength; row++) {
      for (int column = 0; column < esmLength; column++) {
        boolean centerPosition = (row == this.sRow && column == this.sColumn);
        if (centerPosition) {
          marbleFormation[row][column] = SlotState.Empty;
        } else if (withinPlusFormation(row, column)) {
          marbleFormation[row][column] = SlotState.Marble;
        } else {
          marbleFormation[row][column] = SlotState.Invalid;
        }
      }
    }
    return marbleFormation;
  }

  /**
   * Determines whether the given row and column values represents a position within the bounds
   * of the plus-shaped marble formation in an English Solitaire model. Checks if the given row
   * and column meet the value requirements necessary for the specified position to be within the
   * bounds of the English Solitaire Model
   *
   * @param row    the current row index in the two-dimensional ArrayList representing the
   *               English Solitaire model
   * @param column the current column index in the two-dimensional ArrayList representing the
   *               English Solitaire model
   */
  private boolean withinPlusFormation(int row, int column) {
    int modelLength = getBoardSize();
    int overhang = armThickness - 1;
    boolean verticalStripRows = row < modelLength;
    boolean verticalStripColumns = column >= overhang && column < modelLength - overhang;

    boolean horizontalOverhangRows = row >= overhang && row < modelLength - overhang;
    boolean horizontalLeftOverhangColumns = column < overhang;
    boolean horizontalRightOverhangColumns = column >= modelLength - overhang
            && column < modelLength;

    return ((verticalStripRows && verticalStripColumns)
            || (horizontalOverhangRows && horizontalLeftOverhangColumns)
            || (horizontalOverhangRows && horizontalRightOverhangColumns));
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
  private boolean validMove(int fromRow, int fromCol, int toRow, int toCol) {
    return validMovePosition(fromRow, fromCol) && validMovePosition(toRow, toCol)
            && marbleSlot(fromRow, fromCol) && emptySlot(toRow, toCol)
            && twoSlotsBetween(fromRow, fromCol, toRow, toCol)
            && marbleBetween(fromRow, fromCol, toRow, toCol);
  }

  /**
   * Determines if the specified position is valid in a English Solitaire Model. Used to determine
   * whether the from and to position of a move are valid.
   *
   * @param row the row of the specified position
   * @param col the coloumn of the specified position
   * @return true if the specified positon is valid
   */
  private boolean validMovePosition(int row, int col) {
    return validRowCol(row, col) && withinPlusFormation(row, col);
  }

  /**
   * Determines if there is a marble at the specified position.
   *
   * @param row the row of the specified position
   * @param col the column of the specified position
   * @return true if the specified position has a marble
   */
  private boolean marbleSlot(int row, int col) {
    return getSlotAt(row, col) == SlotState.Marble;
  }

  /**
   * Determines if there is an empty slot at the specified position.
   *
   * @param row the row of the specified position
   * @param col the column of the specified position
   * @return true if the specified position is an empty slot
   */
  private boolean emptySlot(int row, int col) {
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
   * Determines if there is a marble in the slot between the two specified positions.
   *
   * @param fromRow the row of the first specified position
   * @param fromCol the column of the first specified position
   * @param toRow   the row of the second specified position
   * @param toCol   the column of the second specified position
   * @return true if there is a marble in the slot between the two specified positions
   */
  private boolean marbleBetween(int fromRow, int fromCol, int toRow, int toCol) {
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
   * Removes the marble between the two specified positions by changing the slot state from
   * Marble to Empty.
   *
   * @param fromRow the row of the first specified position
   * @param fromCol the column of the first specified position
   * @param toRow   the row of the second specified position
   * @param toCol   the column of the second specifed position
   */
  public void removeMarble(int fromRow, int fromCol, int toRow, int toCol) {
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
    }
  }
}
