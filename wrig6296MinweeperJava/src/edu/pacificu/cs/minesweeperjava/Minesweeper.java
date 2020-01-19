package edu.pacificu.cs.minesweeperjava;

/**
 * Creates a minesweeper game class where all methods for running
 * the game are defined
 *
 * @author David Wright
 * @see MSBoard
 */

public class Minesweeper
{
  final int GRID_SIZE = 9;

  private MSBoard mBoard;

  /**
   * Accepts a difficulty and creates a board based off that difficulty
   *
   * @param difficulty an integer to determine the number of bombs
   */
  public Minesweeper (int difficulty)
  {
    mBoard = new MSBoard (GRID_SIZE, GRID_SIZE);

    mBoard.setBombs (difficulty);
    mBoard.setAdj ();
  }

  /**
   * Accepts tile coordinates to update the board
   *
   * @param row row coordinate
   * @param column column coordinate
   * @return false if hit bomb; otherwise return true
   */
  public boolean updateBoard (int row, int column)
  {
    return mBoard.revealTiles (row, column);
  }

  /**
   * Draws the game board
   */
  public void drawGame ()
  {
    mBoard.drawBoard ();
  }

  /**
   * Checks if the game board is completely revealed
   *
   * @return true if board still has hidden tiles; otherwise return false
   */
  public boolean boardIncomplete ()
  {
    return mBoard.tilesHidden ();
  }
}