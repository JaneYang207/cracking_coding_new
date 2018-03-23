package chap8.recursionAndDP;

import java.util.ArrayList;
import java.util.List;

/**
 * 8.12 Eight Queens: find all ways to place the 8 queens in a 8*8 matrix
 */
public class QueensFindAllWays {
	boolean solveNQueenRecByRow(int[][] matrix, int row, List<int[][]> ans) {
		// Base condition: if all queens has been placed.
		if (row == matrix.length) {
			// note: "matrix" added to "ans" list here is a shallow copy of matrix. Later modification of int[][] matrix
			// will modify "matrix" in "ans" list too. So should make a deep-copy of matrix first.
			// ans.add(matrix);

			int[][] matrix_copy = deepCopy(matrix);

			ans.add(matrix_copy);

			// note: this return is needed. Otherwise: when row == matrix.length, it will execute the for loop after this if
			// statement, and in the for loop, we will get an "index out of bound" error at line 31 "matrix[row][col] == =1"
//			return;
			return true;
		}

		// Check each column in the row
		for(int col = 0; col < matrix.length; col++){

				// check if the position is valid
				if (isValid(matrix, row, col)) {

					// place row_th queen at the spot
					matrix[row][col] = 1;

					solveNQueenRecByRow(matrix, row+1, ans);

					// keep trying next spot for the row-th queen: before this, clear the spot for row_th queen
					matrix[row][col] = 0;
				}

		}

		// if the queen cannot be placed at any column in the specified row, return false;
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
	List<int[][]> solveNQueen (int n) {
		int[][] matrix = new int[n][n];
		List<int[][]> ans = new ArrayList<>();
		boolean solvable = solveNQueenRecByRow(matrix, 0, ans);
		if (!solvable) {
			System.out.println("This problem is not solvable!!!");
		} else {
			System.out.println("This problem is solvable!!!");
		}
		return ans;
	}


	void display(int[][] matrix) {
		for (int i = 0; i<matrix.length; i++) {
			for(int j=0; j< matrix.length; j++){
				System.out.print( matrix[i][j] + "   ");
			}
			System.out.println("  ");
		}
	}

	void displayAll(List<int[][]> matrixes) {
		for(int i=0; i<matrixes.size(); i++) {
			display(matrixes.get(i));
			System.out.println("================================");
		}
	}

	int[][] deepCopy(int[][] matrix) {
		int[][] copy = new int[matrix.length][matrix[0].length];
		for (int i=0; i<matrix.length; i++) {
			for(int j=0; j<matrix[0].length; j++) {
				copy[i][j] = matrix[i][j];
			}
		}
		return copy;
	}

	public static void main(String[] args) {
		QueensFindAllWays myclass = new QueensFindAllWays();
		List<int[][]> ans = myclass.solveNQueen(4);

		myclass.displayAll(ans);
	}
}
