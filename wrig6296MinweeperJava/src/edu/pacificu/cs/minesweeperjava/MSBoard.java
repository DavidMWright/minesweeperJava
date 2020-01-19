package edu.pacificu.cs.minesweeperjava;

import java.util.Random;

/**
 * Creates a minesweeper board of Minesweeper tiles. The board is given
 * a size and you can set an amount of bombs based on the difficulty
 *
 * @author David Wright
 * @see MSTile
 */

public class MSBoard extends GameBoard
{
  final char BOMB_CHAR = '@';
  final char HIDDEN_CHAR = '.';

  private MSTile[][] mBoardTiles;
  private Random mRand = new Random (0);

  /**
   * Accepts a height and width for the game board and initializes the
   * 2D array
   *
   * @param height an integer
   * @param width an integer
   */
  public MSBoard (int height, int width)
  {
    super (height, width);

    mBoardTiles = new MSTile[height][width];
  }

  /**
   * Accepts a difficulty integer and sets bombs randomly based off that
   *
   * @param difficulty an integer from 0 to 3
   */
  protected void setBombs (int difficulty)
  {
    int bombs = 0;

    for (int i = 0; i < mWidth; i++)
    {
      for (int j = 0; j < mHeight; j++)
      {
        mBoardTiles[i][j] = new MSTile (State.HIDDEN, -1);
      }
    }

    while (bombs < (difficulty * 3) + mHeight)
    {
      int col = mRand.nextInt (mWidth);
      int row = mRand.nextInt (mHeight);

      if (mBoardTiles[row][col].getState () != State.BOMB)
      {
        mBoardTiles[row][col] = new MSTile (State.BOMB, -1);
        bombs++;
      }
    }
  }

  /**
   * Walks through the board and sets the adjacency for each MSTile
   */
  protected void setAdj ()
  {
    int adj;

    for (int row = 0; row < mHeight; row++)
    {
      for (int col = 0; col < mWidth; col++)
      {
        adj = 0;

        if (!isBomb (row, col))
        {
          // N
          if (isBomb (row - 1, col))
          {
            adj++;
          }
          // NE
          if (isBomb (row - 1, col + 1))
          {
            adj++;
          }
          //E
          if (isBomb (row, col + 1))
          {
            adj++;
          }
          //SE
          if (isBomb (row + 1, col + 1))
          {
            adj++;
          }
          //S
          if (isBomb (row + 1, col))
          {
            adj++;
          }
          //SW
          if (isBomb (row + 1, col - 1))
          {
            adj++;
          }
          //W
          if (isBomb (row, col - 1))
          {
            adj++;
          }
          //NW
          if (isBomb (row - 1, col - 1))
          {
            adj++;
          }

          mBoardTiles[row][col] = new MSTile (State.HIDDEN, adj);
        }
      }
    }
  }

  /**
   * Draws the board in ascii form
   */
  protected void drawBoard ()
  {
    for (int i = 0; i < mWidth; i++)
    {
      System.out.print ("  " + i + " ");
    }

    System.out.println ();

    for (int row = 0; row < mHeight; row++)
    {
      System.out.print ("  ");

      for (int col = 0; col < mWidth; col++)
      {
        switch (mBoardTiles[row][col].getState ())
        {
          case BOMB:
            System.out.print (BOMB_CHAR);
            break;

          case HIDDEN:
            System.out.print (HIDDEN_CHAR);
            break;

          default:
            if (mBoardTiles[row][col].getAdj () == 0)
            {
              System.out.print (' ');
            }
            else
            {
              System.out.print (mBoardTiles[row][col].getAdj ());
            }
        }

        if (col != mWidth - 1)
        {
          System.out.print ("|");
        }

        System.out.print ("  ");
      }

      System.out.println (row);

      if (row != mHeight - 1)
      {
        for (int k = 0; k < mWidth * 4 - 1; k++)
        {
          System.out.print ('-');
        }
      }

      System.out.println ();
    }
  }

  /**
   * Accepts tile coordinates to reveal the tile. Is called
   * recursively on all adjacent tiles if the tile has an adjacency of 0.
   *
   * @param row an integer coordinate
   * @param col an integer coordinate
   * @return false if tile is a bomb; otherwise return true
   */
  protected boolean revealTiles (int row, int col)
  {
    if (!validCell (row, col))
    {
      return true;
    }
    else if (isBomb (row, col))
    {
      return false;
    }
    else if (mBoardTiles[row][col].getState () == State.VISIBLE)
    {
      return true;
    }
    else if (mBoardTiles[row][col].getAdj () == 0)
    {
      mBoardTiles[row][col] = new MSTile (State.VISIBLE,
                                          mBoardTiles[row][col].getAdj ());

      // N
      if (validCell (row, col))
      {
        revealTiles (row - 1, col);
      }
      // NE
      if (validCell (row - 1, col + 1))
      {
        revealTiles (row - 1, col + 1);
      }
      //E
      if (validCell (row, col + 1))
      {
        revealTiles (row, col + 1);
      }
      //SE
      if (validCell (row + 1, col + 1))
      {
        revealTiles (row + 1, col + 1);
      }
      //S
      if (validCell (row + 1, col))
      {
        revealTiles (row + 1, col);
      }
      //SW
      if (validCell (row + 1, col - 1))
      {
        revealTiles (row + 1, col - 1);
      }
      //W
      if (validCell (row, col - 1))
      {
        revealTiles (row, col - 1);
      }
      //NW
      if (validCell (row - 1, col - 1))
      {
        revealTiles (row - 1, col - 1);
      }

      return true;
    }
    else
    {
      mBoardTiles[row][col] = new MSTile (State.VISIBLE,
                                          mBoardTiles[row][col].getAdj ());

      return true;
    }
  }

  /**
   * Checks if the board has hidden tiles left
   *
   * @return true if at least one tile is till hidden; otherwise return false
   */
  protected boolean tilesHidden ()
  {
    boolean hidden = false;

    for (int row = 0; row < mHeight; row++)
    {
      for (int col = 0; col < mWidth; col++)
      {
        if (mBoardTiles[row][col].getState () == State.HIDDEN)
        {
          hidden = true;
          break;
        }
      }
    }

    return hidden;
  }

  /**
   * Accepts tile coordinates and checks if it is within the bounds of
   * the game
   *
   * @param x integer tile coords
   * @param y integer tile coords
   * @return true if tile is in bounds. Otherwise return false
   */
  private boolean validCell (int x, int y)
  {
    return (x >= 0 && x < mWidth) && (y >= 0 && y < mHeight);
  }

  /**
   * Checks if passed tile is valid and then if it is a bomb
   *
   * @param row integer tile coord
   * @param col integer tile coord
   * @return true if the Rational object is in the set; otherwise, false
   */
  private boolean isBomb (int row, int col)
  {
    if (validCell (row, col))
    {
      return mBoardTiles[row][col].getState () == State.BOMB;
    }
    else
    {
      return false;
    }
  }
}