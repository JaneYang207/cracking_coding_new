package chap8.recursionAndDP;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jyang on 2/7/18.
 */
public class SudokuAllWays {
	/**
	 * 	recursion function: For each spot in the grid, if it's empty put a number at spot (row, col).
	 */
	boolean fitSpot(int[][] A, int row, int col, List<int[][]> ans) {
//		System.out.println("row = " + row + "   col = " + col);
		// base case: all spots filled
		if (row >= 9 || col >= 9) {
			int[][] matrix_copy = deepCopy(A);
			ans.add(matrix_copy);
			displayMatrix(A);
			return true;
		}

		int nextSpot_row = (col == 8) ? row + 1 : row;
		int nextSpot_col = (col == 8) ? 0 : col + 1;

		if(A[row][col] == 0) {   // note: only try fill if it's not already filled

			// try num: 1 ~ 9
			for (int num = 1; num <= 9; num++) {
//				System.out.println("num = " + num);
				// step 1. check if the num is valid at the spot
				if (isValid(A, num, row, col)) {
					// step 2. if valid, place the num at the spot
					A[row][col] = num;

					// stpe 3. decide if the placed num make the rest of the grid solvable:
					fitSpot(A, nextSpot_row, nextSpot_col, ans);
					A[row][col] = 0;
				}
			}
			// if tried all numbers between 1 ~ 9, didn't find one to fill in
			return false;
		}
		else {
			// if already filled, check next spot.
			return fitSpot(A, nextSpot_row, nextSpot_col, ans);
		}
	}

	List<int[][]> solve(int[][] A) {
		List<int[][]> ans = new ArrayList<>();
		fitSpot(A, 0, 0, ans);
		return ans;
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

	/**
	 * check the filled_in value is valid:
	 * 1. no duplicate at the row
	 * 2. no duplicate at the col
	 * 3. no duplicate in the subgrid
	 */
	boolean isValid(int[][] A, int val, int row, int col) {
		return isValidAtRow(A, val, row, col) && isValidAtCol(A, val, row, col) && isValidInSubGrid(A, val, row, col);
	}

	boolean isValidAtRow(int[][] A, int val, int row, int col) {
		for (int j = 0; j < 9; j++) {
			// compare val with all elements in the row (except the spot itself)
			if (A[row][j] == val) {
				return false;
			}
		}

		// if checked all elements in the row, no duplicates
		return true;
	}

	boolean isValidAtCol(int[][] A, int val, int row, int col) {
		for (int i = 0; i < 9; i++) {
			// compare A[i][col] with val
			if (A[i][col] == val) {
				return false;
			}
		}

		// if checked all elements in the row, no duplicates
		return true;
	}

	boolean isValidInSubGrid(int[][] A, int val, int row, int col) {
		// STEP 1. decide subgrid index scope:

		// step 1_1. decide row index scope: find the nearest number which is smaller than "row", and is a multipe of 3
		int rowBegin = row;
		for (; rowBegin >= 0; rowBegin--) {
			if (rowBegin % 3 == 0) {
				break;
			}
		}

		// step 1_2. decide column index scope:
		int colBegin = col;
		for(; colBegin >= 0; colBegin--) {
			if (colBegin % 3 == 0) {
				break;
			}
		}

		// STEP 2. iterate all elements in the subgrid:
		// row indexes: rowBegin ~ rowBegin + 2
		// col indexes: colBegin ~ colBegin + 2
		for (int i = rowBegin; i <= rowBegin + 2; i++) {
			for (int j = colBegin; j <= colBegin + 2; j++) {
				if (A[i][j] == val) {
					return false;
				}
			}
		}

		return true;
	}

	void displayMatrix(int[][] A) {
		for (int i = 0; i < A.length; i++) {
			System.out.println(" ");

			for(int j = 0; j < A[0].length; j++) {
				System.out.print(A[i][j] + " ");
			}
		}
		System.out.println( " ");
	}

	void displayAll(List<int[][]> matrixes) {
		for(int i=0; i<matrixes.size(); i++) {
			displayMatrix(matrixes.get(i));
			System.out.println("================================");
		}
	}

	public static void main(String[] args) {
		SudokuAllWays myclass = new SudokuAllWays();

		int[][] A = new int[9][9];
		List<int[][]> ans = myclass.solve(A);
		myclass.displayAll(ans);
	}
}
