package cs3500.marblesolitaire.model.hw04;

/**
 * Represents a variant of the marble solitaire board that arranges the marbles in an
 * octagonal formation.
 */
public class EuropeanSolitaireModel extends ASolitaireBoard {

  /**
   * Construct a model for European Marble Solitaire with a specified side length and an empty slot
   * at the specified position.
   *
   * @param side    the number of marbles on each side of the board
   * @param sRow    the row position of the empty slot
   * @param sColumn the column position of the empty slot
   * @throws IllegalArgumentException if the side length is not a positive odd number or
   *                                  if the empty cell position does not exist on the model
   */
  public EuropeanSolitaireModel(int side, int sRow, int sColumn) throws IllegalArgumentException {
    super(side, sRow, sColumn);
  }

  /**
   * Construct a model for European Marble Solitaire with a default side length of 3 and
   * an empty slot at the specified position.
   *
   * @param sRow    the row position of the empty slot
   * @param sColumn the column position of the empty slot
   * @throws IllegalArgumentException if the empty cell position does not exist on the model
   */
  public EuropeanSolitaireModel(int sRow, int sColumn) throws IllegalArgumentException {
    super(3, sRow, sColumn);
  }

  /**
   * Construct a model for European Marble Solitaire with a specified side length and an empty
   * at the center of the board.
   *
   * @param side    the number of marbles on each side of the board
   * @throws IllegalArgumentException if the side length is not a positive odd number
   */
  public EuropeanSolitaireModel(int side) throws IllegalArgumentException {
    super(side);
  }

  /**
   * Construct a model for European Marble Solitaire with a side length of 3 and an empty slot
   * at the center of the board.
   *
   */
  public EuropeanSolitaireModel() {
    super(3, 3, 3);
  }

  /**
   * Determine what type of marble solitaire board is represented by this class.
   *
   * @return the String "European", which defines the type of board represented by this class.
   */
  protected String getType() {
    return "European";
  }

}
