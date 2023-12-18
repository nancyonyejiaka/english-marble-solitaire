package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * This class represents an implementation of a controller for a marble solitaire game.
 * The controller takes input from the user and decides what to do with it.
 *
 * <p>A few minor changes have been made to this class in the time between this submission and the
 * previous submission for assignment 2. These minor changes had to do with making the loops for
 * playGame less "unwieldy" and therefore easier to test. The condition for the while loop that
 * iterates through the method while the game is not over has been modified from an or statement
 * to an and statement. Since scanner will always have a next unless the game is quit, this and
 * loops ensures that the loops will end once the game is over, and thus the behavior of exiting
 * the loop can be isolated to the game over part of the condition.
 *
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private final MarbleSolitaireModel model;
  private final MarbleSolitaireView view;
  private final Readable input;
  private final Appendable output;

  /**
   * Construct a new instance of a controller for a marble solitaire game.
   *
   * @param model the model that the game of marble solitaire is played ond this controller
   *              transmits user input to
   * @param view  the view that displays the game interface to the user
   * @param input the user input source
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view,
                                       Readable input) {
    if (model == null || view == null || input == null) {
      throw new IllegalArgumentException("Model, view, and input parameters must be specified. "
              + "Neither can be null.");
    } else {
      this.model = model;
      this.view = view;
      this.input = input;
      this.output = System.out;
    }
  }

  /**
   * Play a new game of Marble Solitaire.
   *
   * @throws IllegalStateException only if the controller is unable to successfully
   *                               read input or transmit input.
   */
  @Override
  public void playGame() throws IllegalStateException {
    int[] validInputs = new int[4];
    int count = 0;
    int num = 0;
    boolean hasQuit = false;

    updateGameWindow();
    Scanner sc = new Scanner(this.input);

    while (!this.model.isGameOver() && sc.hasNext()) {
      String object = sc.next();

      if (object.equalsIgnoreCase("q")) {
        hasQuit = true;
        quit();
        return;
      } else {
        try {
          num = Integer.parseInt(object);
          if (num > 0 && num <= this.model.getBoardSize()) {
            if (count == 3) {
              validInputs[count] = num - 1;
              transmitMove(validInputs);
              count = 0;

            } else {
              validInputs[count] = num - 1;
              count++;
            }
          }
        } catch (NumberFormatException f) {
          // ignore, do nothing
        }
      }
    }
    if (this.model.isGameOver()) {
      renderGameOver();
    } else if (!hasQuit) {
      throw new IllegalStateException("Out of readable inputs!");
    }
  }

  /**
   * Quit the game when the user inputs the letter 'q' or the letter 'Q'.
   */
  private void quit() {
    try {
      this.view.renderMessage("Game quit!\nState of game when quit:\n");
      updateGameWindow();
    } catch (IOException e) {
      throwISE();
    }
  }

  /**
   * Throws an IllegalStateException in cases where an IOException would have been thrown.
   *
   * @throws IllegalStateException if the controller is unable to successfully
   *                               read input or transmit input
   */
  private void throwISE() throws IllegalStateException {
    throw new IllegalStateException("Oops! Transmission of board to provided data "
            + "destination failed!");
  }

  /**
   * Update the game window after each turn by rendering the current state of the game,
   * and the current score.
   */
  private void updateGameWindow() {
    try {
      this.view.renderBoard();
      this.view.renderMessage("\nScore: " + this.model.getScore() + "\n");
    } catch (IOException e) {
      throwISE();
    }
  }

  /**
   * Update the game window after the game is over by rendering the message "Game Over!" followed
   * by the state of the board at the end of the game, and the score at the end of the game.
   */
  private void renderGameOver() {
    try {
      this.view.renderMessage("Game over!\n");
      updateGameWindow();
    } catch (IOException e) {
      throwISE();
    }
  }


  /**
   * Transmits a move to the board based on the user input.
   *
   * @param validInputs an array of 4 valid inputs recived by the controller from the user
   */
  private void transmitMove(int[] validInputs) {
    try {
      this.model.move(validInputs[0], validInputs[1], validInputs[2], validInputs[3]);
      updateGameWindow();
    } catch (IllegalArgumentException e) {
      try {
        this.view.renderMessage("Invalid move. Play again. " + e.getMessage() + "\n");
      } catch (IOException i) {
        throwISE();
      }
    }
  }

}
