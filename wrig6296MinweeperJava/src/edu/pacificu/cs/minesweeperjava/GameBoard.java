package edu.pacificu.cs.minesweeperjava;

/**
 * Abstract class for creating a generic game board
 *
 * @author David Wright
 */

public class GameBoard
{
  protected int mHeight;
  protected int mWidth;

  /**
   * Creates a game board with a height and width
   *
   * @param height integer for board height
   * @param width integer for board width
   */
  public GameBoard (int height, int width)
  {
    mHeight = height;
    mWidth = width;
  }
}
