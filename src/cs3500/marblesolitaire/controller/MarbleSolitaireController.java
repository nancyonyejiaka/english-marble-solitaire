package cs3500.marblesolitaire.controller;

/**
 * This interface represents operations that should be offered by
 * a controller for the Marble solitaire game in order to run the game.
 */
public interface MarbleSolitaireController {

  /**
   * Play a new game of Marble Soliatire.
   *
   * @throws IllegalStateException only if the controller is unable to successfully
   *                               read input or transmit input
   */
  void playGame() throws IllegalStateException;
}
