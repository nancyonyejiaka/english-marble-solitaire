package cs3500.marblesolitaire.model.hw04;

/**
 * Represents a variant of the marble solitaire board that arranges the marbles in a
 * triangular formation.
 */
public class TriangleSolitaireModel extends ASolitaireBoard {

  /**
   * Construct an instance of a default triangle 5-row game of marble solitaire
   * with the empty slot at (0,0).
   */

  public TriangleSolitaireModel() {
    super(5, 0, 0);
  }

  public TriangleSolitaireModel(int dimension, int row, int col) throws IllegalArgumentException {
    super(dimension, row, col);
  }

  public TriangleSolitaireModel(int row, int col) throws IllegalArgumentException {
    super(5, row, col);
  }

  public TriangleSolitaireModel(int dimension) throws IllegalArgumentException {
    super(dimension, 0, 0);
  }

  @Override
  public int getBoardSize() {
    int size = this.length;
    return size;
  }

  /**
   * Determine if moving from the first specified position of the second specified position would
   * be a valid move in an English Solitaire Model. This override for triangular marble solitaire
   * boards also checks if there is a valid up left or down right move within the board.
   *
   * @param fromRow the row number of the position to be moved from
   * @param fromCol the column number of the position to be moved from
   * @param toRow   the row number of the position to be moved to
   * @param toCol   the column number of the position to be moved to
   * @return true if the move from the from position to the second position is valid, or if there
   *         is a valid up left or down right move at the specified from position, false otherwise
   */
  @Override
  protected boolean hasValidMove(int fromRow, int fromCol, int toRow, int toCol) {
    return (validRowCol(fromRow, fromCol) && (super.hasValidMove(fromRow, fromCol, toRow, toCol)
            || canMoveUpLeft(fromRow, fromCol)
            || canMoveDownRight(fromRow, fromCol)));
  }

  /**
   * Determine if there is a valid move from the specified position to a slot in the upper left
   * direction on the board.
   *
   * @param fromRow the row number for the position of interest
   * @param fromCol the column number for the position of interest
   * @return true if there is a valid move in the upper left direction from the specified position,
   *         false otherwise
   */
  private boolean canMoveUpLeft(int fromRow, int fromCol) {
    if (fromRow - 2 < 0 || fromCol - 2 < 0) {
      return false;
    } else {
      return marbleSlot(fromRow - 1, fromCol - 1)
              && emptySlot(fromRow - 2, fromCol - 2);
    }
  }

  /**
   * Determine if there is a valid move from the specified position to a slot in the lower right
   * direction on the board.
   *
   * @param fromRow the row number for the position of interest
   * @param fromCol the column number for the position of interest
   * @return true if there is a valid move in the lower right direction from the specified position,
   *         false otherwise
   */
  private boolean canMoveDownRight(int fromRow, int fromCol) {
    if (fromRow + 2 > this.length - 1 || fromCol + 2 > this.length - 1) {
      return false;
    } else {
      return marbleSlot(fromRow + 1, fromCol + 1)
              && emptySlot(fromRow + 2, fromCol + 2);
    }
  }

  /**
   * Determine what type of marble solitaire board is represented by this class.
   *
   * @return the String "Triangle", which defines the type of board represented by this class.
   */
  protected String getType() {
    return "Triangle";
  }

}
