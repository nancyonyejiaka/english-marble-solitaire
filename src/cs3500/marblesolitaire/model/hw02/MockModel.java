package cs3500.marblesolitaire.model.hw02;

import java.io.IOException;
import java.util.Objects;

/**
 * Represents a fake model for English marble solitaire. Used for testing in order to confirm
 * that the controller properly transmits information to its model.
 */
public class MockModel implements MarbleSolitaireModel {
  private final Appendable log;
  private final MarbleSolitaireModel model;

  /**
   * Constructs a new instance of this mock model.
   *
   * @param log the appendable that logs the moves transmitted to this model from the controller
   */
  public MockModel(Appendable log) {
    this.log = Objects.requireNonNull(log);
    this.model = new EnglishSolitaireModel();
  }

  /**
   * Construct a new instance of this mock model.
   *
   * @param log   the appendable that logs the moves transmitted to this model from the controller
   * @param model the model that the controller will transmit user input to
   */
  public MockModel(Appendable log, MarbleSolitaireModel model) {
    this.log = Objects.requireNonNull(log);
    this.model = Objects.requireNonNull(model);
  }

  /**
   * Keeps a log of the moves fed to this model by the controller. Each move is appended to
   * the appendable log of this mock model
   *
   * @param fromRow the row number of the position to be moved from
   * @param fromCol the column number of the position to be moved from
   * @param toRow   the row number of the position to be moved to
   * @param toCol   the column number of the position to be moved to
   * @throws IllegalStateException if transmission of board to provided data destination fails
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    this.model.move(fromRow, fromCol, toRow, toCol);
    try {
      log.append(String.valueOf(fromRow))
              .append(String.valueOf(fromCol))
              .append(String.valueOf(toRow))
              .append(String.valueOf(toCol));
    } catch (IOException e) {
      throw new IllegalStateException("Transmission of board to provided data destination failed");
    }
  }

  @Override
  public boolean isGameOver() {
    return this.model.isGameOver();
  }

  @Override
  public int getBoardSize() {
    return this.model.getBoardSize();
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    return this.model.getSlotAt(row, col);
  }

  @Override
  public int getScore() {
    return this.model.getScore();
  }
}
