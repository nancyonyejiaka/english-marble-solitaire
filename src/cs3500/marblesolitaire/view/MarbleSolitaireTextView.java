package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * This class represents the board of a Marble Solitaire model. Responsible for
 * displaying the game during gameplay
 *
 * <p>A few changes have been made to this class in the time between this submission and the
 * previous submission for assignment 2. All the changes made to this class have had to do
 * with abstraction with the purpose of reducing code duplication between other classes in this
 * project.
 *
 * <p>All of the methods that were previously in this class have been move to a new abstract
 * class for marble solitaire boards, {@link AMarbleSolitaireView}. The functionality of the
 * methods that were once in this class remains the same, and the original tests for this class
 * are unchanged. As part of abstracting this class into {@link AMarbleSolitaireView},
 * the constructors for this class were also modified to make class to the super constructor of
 * the abstract class.
 */
public class MarbleSolitaireTextView extends AMarbleSolitaireView {

  /**
   * Construct a view for marble solitaire with the given model object. Queries the model and
   * prints the board. Uses the console as the default destination of this text view.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model)
          throws IllegalArgumentException {
    super(model);
  }

  /**
   * Construct a view for marble solitaire with the given model and view objects.
   * Queries the model and prints the board. Uses the specified appendable as the destination
   * of this text view.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model, Appendable destination)
          throws IllegalArgumentException {
    super(model, destination);
  }
}