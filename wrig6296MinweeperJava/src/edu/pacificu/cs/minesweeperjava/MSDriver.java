package edu.pacificu.cs.minesweeperjava;

import java.util.Scanner;

/**
 * Creates a Driver class to create the minesweeper class,
 * take input from the keyboard, and play the game
 *
 * @author David Wright
 * @see Minesweeper
 */

public class MSDriver
{
  static Scanner scanner = new Scanner (System.in);

  public static void main (String[] args)
  {
    final int MIN_DIFF = 0;
    final int MAX_DIFF = 2;

    int difficulty;
    int xCoord, yCoord;

    Minesweeper cGame;

    System.out.println ("***********");
    System.out.println ("Minesweeper");
    System.out.println ("***********");

    do
    {
      System.out.println ("Enter difficulty level");
      System.out.print ("(0 = EASY, 1 = MEDIUM, 2 = HARD): ");

      difficulty = scanner.nextInt ();
    } while (difficulty < MIN_DIFF || difficulty > MAX_DIFF);

    cGame = new Minesweeper (difficulty);

    do
    {
      cGame.drawGame ();

      System.out.print ("Enter X and Y Coordinate: ");
      xCoord = scanner.nextInt ();
      yCoord = scanner.nextInt ();

      System.out.println ();

    } while (cGame.updateBoard (yCoord, xCoord) && cGame.boardIncomplete ());

    if (!cGame.boardIncomplete ())
    {
      System.out.println ("Congratulations. You Win.");
    }
    else
    {
      System.out.println ("Boooom!!! You Lose.");
    }

    cGame.drawGame ();

    scanner.close ();
  }
}