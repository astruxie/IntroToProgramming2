
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
		int minbirth = 0;
		int maxbirth = 0;
		int minsurvive = 0;
		int maxsurvive = 0;
		int count = 0;

		// Input from user
		row = reader.nextInt();
		column = reader.nextInt();
		seed = reader.nextLong();
		minbirth = reader.nextInt();
		maxbirth = reader.nextInt();
		minsurvive = reader.nextInt();
		maxsurvive = reader.nextInt();

		// Create game board
		boolean[][] gameBoard = getMatrix(row, column, seed);

		// print the original board before the game starts
		printBoard(gameBoard, row, column);

		// Go through one round of the game at a time
		while (count < 5) {
			gameBoard = gameRound(gameBoard, row, column, minbirth, maxbirth, minsurvive, maxsurvive);
			count++;
		}

		// Close scanner because Eclipse is angry
		reader.close();
	}// end main

	/**
	 * Constructs & fills the initial game board with random values.
	 *
	 * @param r number of rows
	 * @param c number of columns
	 * @param s random seed
	 * @return the new game board
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
	 * 
	 * @param r         number of rows
	 * @param c         number of columns
	 * @param gameBoard array of the game board
	 **/
	public static void printBoard(boolean[][] gameBoard, int r, int c) {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (gameBoard[i][j] == false) {
					System.out.print("- ");
				} else {
					System.out.print("# ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * Makes a copy of the gameBoard array so as it updates, there is no data loss.
	 * 
	 * @param r             number of rows
	 * @param c             number of columns
	 * @param originalBoard original array of the game board that becomes updated
	 *                      during the round
	 * @return the copy of the board
	 **/
	public static boolean[][] copyBoard(boolean[][] originalBoard, int r, int c) {
		// Create the copy
		boolean[][] frozenBoard = new boolean[r][c];

		// Manually copy the arrays
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				frozenBoard[i][j] = originalBoard[i][j];
			}
		}
		return frozenBoard;
	}

	/**
	 * Completes one iteration of the game, printing the updated board when
	 * finished.
	 * 
	 * @param r         number of rows
	 * @param c         number of columns
	 * @param gameBoard array of the game board
	 * @param minB      minimum number of neighbors necessary for a birth
	 * @param maxB      maximum number of neighbors allowable for a birth
	 * @param minS      minimum number of neighbors for survival
	 * @param maxS      maximum number of neighbors for survival
	 * @return the current updated board
	 **/
	public static boolean[][] gameRound(boolean[][] gameBoard, int r, int c, int minB, int maxB, int minS, int maxS) {
		// Variables
		int neighborCount = 0;

		// Copy board at the start of the turn
		boolean[][] frozenBoard = copyBoard(gameBoard, r, c);

		// Find the number of neighbors in each cell and update it accordingly
		for (int i = 1; i < (r - 1); i++) {

			for (int j = 1; j < (c - 1); j++) {
				// refresh counter
				neighborCount = 0;
				
				// upper left corner
				if (frozenBoard[i - 1][j - 1] == true) {
					neighborCount++;
				}
				// above
				if (frozenBoard[i][j - 1] == true) {
					neighborCount++;
				}
				// upper right corner
				if (frozenBoard[i + 1][j - 1] == true) {
					neighborCount++;
				}
				// right
				if (frozenBoard[i + 1][j] == true) {
					neighborCount++;
				}
				// bottom right corner
				if (frozenBoard[i + 1][j + 1] == true) {
					neighborCount++;
				}
				// bottom
				if (frozenBoard[i][j + 1] == true) {
					neighborCount++;
				}
				// bottom left corner
				if (frozenBoard[i - 1][j + 1] == true) {
					neighborCount++;
				}
				// left
				if (frozenBoard[i - 1][j] == true) {
					neighborCount++;
				}
				//self
				if (frozenBoard[i][j] == true) {
					neighborCount++;
				}

				// Check if cell is occupied, if yes, check if conditions are not met, and kill
				// the organism if so.
				// else, check if empty and conditions are met for a birth, if yes, birth occurs
				
				//i've written this over lots of times so it's messy right now & not efficient
				if (frozenBoard[i][j] == true) {
					if (neighborCount > maxS || neighborCount < minS) {
						gameBoard[i][j] = false;
					}
					else {
						gameBoard[i][j] = true;
					}
				}
				if (frozenBoard[i][j] == false) {
					if (neighborCount >= minB && neighborCount <= maxB) {
						gameBoard[i][j] = true;
					} 
					else {
						gameBoard[i][j] = false;
					}
				}
			}
		}

		// Print board at the end of turn
		printBoard(gameBoard, r, c);

		return gameBoard;
	}
}