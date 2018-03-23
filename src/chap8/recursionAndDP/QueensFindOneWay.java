package chap8.recursionAndDP;

import java.util.ArrayList;
import java.util.List;

/**
 * 8.12 Eight QueensFindOneWay: just print out one way
 */
public class QueensFindOneWay {

	/**
	 * Check each spot in the matrix
	 * @param matrix 0, no queen; 1, has queen
	 * @param n_th the n_th queen to be placed. Could be 0, 1, 2, ...., 7
	 * @return true if it's solvable
	 */
	boolean solveNQueenRec(int[][] matrix, int n_th) {
		// Base condition: if all queens has been placed.
		if (n_th == matrix.length) {
			display(matrix);
			return true;
		}

		// Check each position in the grid
		for (int row = 0; row < matrix.length; row++) {

			for (int col = 0; col < matrix.length; col++){

				// check if it's already occupied, if yes, check next spot
				if (matrix[row][col] == 0) {

					// check if the position is valid
					if (isValid(matrix, row, col)) {

						// place a new queen at the spot
						matrix[row][col] = 1;

						// check if the new queen make the next queen placable
						// if yes, return true;
						// if no, remove the placed queen, try next spot
						if (solveNQueenRec(matrix, n_th + 1)) {
							return true;
						} else {
							// remove the placed queen, try next spot
							matrix[row][col] = 0;
						}
					}
				}
			}
		}

		return false;
	}

	/**
	 * Check row by row: each row should have one and only one queen. start from row 0 to place one queen,
	 *             then row 1 to place another queen, until row 7 to place the last queen
	 * @param matrix 0, no queen; 1, has queen
	 * @param row row number
	 * @return true if it's solvable
	 */
	boolean solveNQueenRecByRow(int[][] matrix, int row) {
		// Base condition: if all queens has been placed.
		if (row == matrix.length) {
			display(matrix);
			return true;
		}

		// Check each position in the row
		for (int col = 0; col < matrix.length; col++){

			// check if the position is valid
			if (isValid(matrix, row, col)) {

				// place a new queen at the spot
				matrix[row][col] = 1;

				// check if the new queen make the next queen placable
				// if yes, return true;
				// if no, remove the placed queen, try next spot
				if (solveNQueenRecByRow(matrix, row + 1)) {
					return true;
				}
//				else {

				// remove the placed queen, try next spot
				matrix[row][col] = 0;
//				}
			}
		}

		return false;
	}

	/**
	 * check if a spot is ok to place a queeen
	 * 1. row not occupied by other queens
	 * 2. col not occupied by other queens
	 * 3. not on the same diagonal with other queens
	 */
	boolean isValid(int[][] matrix, int row, int col) {
		// check if no queen on the same row
		for (int j = 0; j < matrix.length; j++) {
			if (matrix[row][j] == 1) {
				return false;
			}
		}

		// check if no queen on the same col
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i][col] == 1) {
				return false;
			}
		}

		// check if no queen on the same diagonal
		// rigth diagonal (uppper)
		for (int m = row - 1, n = col - 1; m >= 0 && n >=0; m--, n--) {
			if (matrix[m][n] == 1) {
				return false;
			}
		}

		// rigth diagonal (lower)
		for (int m = row + 1, n = col + 1; m < matrix.length && n < matrix.length; m++, n++) {
			if (matrix[m][n] == 1) {
				return false;
			}
		}

		// left diagonal (uppper)
		for (int m = row - 1, n = col + 1; m >= 0 && n < matrix.length; m--, n++) {
			if (matrix[m][n] == 1) {
				return false;
			}
		}

		// left diagonal (lower)
		for (int m = row + 1, n = col - 1; m < matrix.length && n >= 0; m++, n--) {
			if (matrix[m][n] == 1) {
				return false;
			}
		}

		return true;
	}

	/**
	 * @param n: number of queens
	 * @return true if the problem is solvable
	 */
	boolean solveNQueen (int n) {
		int[][] matrix = new int[n][n];
//		return solveNQueenRec(matrix, 0);
		return solveNQueenRecByRow(matrix, 0);
	}

	void display(int[][] matrix) {
     for (int i = 0; i<matrix.length; i++) {
     	for(int j=0; j< matrix.length; j++){
     		System.out.print( matrix[i][j] + "   ");
			}
			System.out.println("  ");
		 }
	}


	public static void main(String[] args) {
		QueensFindOneWay myclass = new QueensFindOneWay();
		myclass.solveNQueen(8);
	}
}
