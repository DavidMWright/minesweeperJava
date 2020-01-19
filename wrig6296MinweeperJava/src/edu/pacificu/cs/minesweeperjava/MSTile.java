package edu.pacificu.cs.minesweeperjava;

/**
 * Enumerator for a tile's state
 */
enum State
{
  HIDDEN, VISIBLE, BOMB
}


/**
 * Creates a minesweeper tile class that contains what state the
 * tile is in and the number of adjacent bombs to it
 *
 * @author David Wright
 */

public class MSTile
{
  private State mState;
  private int mAdj;

  /**
   * Creates a minesweeper tile with a state and adjacency
   *
   * @param state an enum for what state the tile is in
   * @param adj an int for the tiles number of adjacent bombs
   */
  public MSTile (State state, int adj)
  {
    mState = state;
    mAdj = adj;
  }

  /**
   * Returns the tile's state
   *
   * @return tile state
   */
  public State getState ()
  {
    return mState;
  }

  /**
   * Returns the tile's adjacency
   *
   * @return tile adj
   */
  public int getAdj ()
  {
    return mAdj;
  }
}