
/**
 * Program that simulates organisms that inhabit an area
 * @author - Samantha J. Noggle
 **/
import java.util.*;

public class Life {
	public static void main(String[] args) {
		// Scanner
		Scanner reader = new Scanner(System.in);

		// Variables
		int row = 0;
		int column = 0;
		long seed = 0;

		// Input from user
		row = reader.nextInt();
		column = reader.nextInt();
		seed = reader.nextLong();

		// Create game board
		boolean[][] gameBoard = getMatrix(row, column, seed);

		// Print board
		printBoard(gameBoard, row, column);

	}// end main

	/**
	 * Constructs & fills the initial game board with random values.
	 * 
	 * @return gameBoard
	 * @param r number of rows
	 * @param c number of columns
	 * @param s random seed
	 * 
	 **/
	public static boolean[][] getMatrix(int r, int c, long s) {
		boolean[][] gameBoard = new boolean[r][c];

		// Fill array with false
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				gameBoard[i][j] = false;
			}
		}

		// Create random object w/ seed
		Random rand = new Random();
		rand.setSeed(s);

		// Randomize the inner board
		for (int i = 1; i < (r - 1); i++) {
			for (int j = 1; j < (c - 1); j++) {
				gameBoard[i][j] = rand.nextBoolean();
			}
		}

		return gameBoard;
	}

	/**
	 * Prints out the game board.
	 * @param r number of rows
	 * @param c number of columns
	 * @param gameBoard array of the game board
	 **/
	public static void printBoard(boolean[][] gameBoard, int r, int c) {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (gameBoard[i][j] == false) {
					System.out.print("- ");
					;
				} else {
					System.out.print("# ");
				}
			}
			System.out.println();
		}
	}
}