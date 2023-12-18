package cs3500.marblesolitaire.controller;

import java.io.IOException;

/**
 * An appendable class that throws an IOException any time an append attempt
 * is made with this class.
 */
public class FailingAppendable implements Appendable {

  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("Append failed.");
  }


  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("Append failed.");
  }


  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("Append failed.");
  }
}
