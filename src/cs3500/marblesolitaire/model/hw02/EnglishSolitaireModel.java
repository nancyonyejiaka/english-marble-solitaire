package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw04.ASolitaireBoard;

/**
 * This class represents the model for English Solitaire. The model maintains the state
 * of the game and allows a client to specify moves.
 *
 * <p>A few changes have been made to this class in the time between this submission and the
 * previous submission for assignment 2. All the changes made to this class have had to do
 * with abstraction with the purpose of reducing code duplication between other classes in this
 * project.
 *
 * <p>All of the methods that were previously in this class have been move to a new abstract
 * class for marble solitaire boards, {@link ASolitaireBoard}. The functionality of the methods
 * that were once in this class remains the same, and the original tests for this class are
 * unchanged. As part of abstracting this class into {@link ASolitaireBoard}, the constructors
 * for this class were also modified to make class to the super constructor of the abstract class.
 *
 * <p>In addition to migrating these methods, a new method, getType() has been added to this
 * class.The purpose of this class is to aid in the functionality of the abstract class by
 * delegating the self-identification of this class.
 */
public class EnglishSolitaireModel extends ASolitaireBoard {

  /**
   * Construct a model for English Solitaire with a specified arm thickness and an empty slot
   * at the specified position.
   *
   * @param armThickness the number of marbles on the top row
   * @param sRow         the row position of the empty slot
   * @param sColumn      the column position of the empty slot
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number or
   *                                  if the empty cell position does not exist on the model
   */
  public EnglishSolitaireModel(int armThickness, int sRow, int sColumn)
          throws IllegalArgumentException {
    super(armThickness, sRow, sColumn);
  }

  /**
   * Construct a model for English Solitaire with a default arm thickness of 3 and an empty slot
   * at the specified position.
   *
   * @param sRow    the row position of the empty slot
   * @param sColumn the column position of the empty slot
   * @throws IllegalArgumentException if the specified position does not exist on the model
   */
  public EnglishSolitaireModel(int sRow, int sColumn) throws IllegalArgumentException {
    super(3, sRow, sColumn);
  }

  /**
   * Construct a model for English Solitaire with a specified arm thickness.
   *
   * @param armThickness the number of marbles on the top row
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number
   */
  public EnglishSolitaireModel(int armThickness) throws IllegalArgumentException {
    super(armThickness);
  }

  /**
   * Construct a model for English Solitaire with a default arm thickness of 3 and an empty slot
   * at the center of the model.
   */
  public EnglishSolitaireModel() {
    super(3, 3, 3);
  }

  /**
   * Determine what type of marble solitaire board is represented by this class.
   *
   * @return the String "English", which defines the type of board represented by this class.
   */
  protected String getType() {
    return "English";
  }
}
