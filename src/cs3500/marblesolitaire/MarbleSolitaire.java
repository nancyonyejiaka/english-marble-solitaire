package cs3500.marblesolitaire;

import java.io.InputStreamReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

/**
 * Represents the class that houses the main method in order to play the game.
 */
public class MarbleSolitaire {

  /**
   * Initiates a game of marble solitaire based on the given arguments. If no arguments are given,
   * this method resorts to constructing an English Solitaire model with a default size of three
   * and the initial empty slot at the middle of the board.
   *
   * @param args the arguments fore constructs a game of marble solitaire
   */
  public static void main(String[] args) {
    MarbleSolitaireModel model;
    MarbleSolitaireView view;

    String boardType = "";
    int boardSize = -1;
    int row = -1;
    int col = -1;

    // parse through args and extract commands and corresponding values
    for (int i = 0; i < args.length; i++) {
      if (i == 0) {
        boardType = args[0];
        continue;
      }
      if (args[i].equalsIgnoreCase("-size")) {
        i++;
        boardSize = Integer.parseInt(args[i]);
        continue;
      }
      if (args[i].equalsIgnoreCase("-hole")) {
        i++;
        row = Integer.parseInt(args[i]);
        i++;
        col = Integer.parseInt(args[i]);
      }
    }

    // assign default empty slot values
    if (row == -1 || col == -1) {
      switch (boardType) {
        case "english":
        case "european":
          row = ((boardSize * 3) - 2) / 2;
          col = row;
          break;
        case "triangular":
          row = 0;
          col = 0;
          break;
        default:
          break;
      }
    }

    // create boards and models
    switch (boardType) {
      case "english":
        model = new EnglishSolitaireModel(boardSize, row, col);
        view = new MarbleSolitaireTextView(model);
        break;
      case "european":
        model = new EuropeanSolitaireModel(boardSize, row, col);
        view = new MarbleSolitaireTextView(model);
        break;
      case "triangular":
        model = new TriangleSolitaireModel(boardSize, row, col);
        view = new TriangleSolitaireTextView(model);
        break;
      default:
        model = new EnglishSolitaireModel();
        view = new MarbleSolitaireTextView(model);
        break;
    }

    Readable input = new InputStreamReader(System.in);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, input);
    controller.playGame();
  }
}
