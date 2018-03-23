package chap8.recursionAndDP;

public class Sudoku {

//	/**
//	 * recursion function: iterate through each spot in the grid, put a number at the spot
//	 */
//	boolean isSolvable(int[][] A) {
//		// base case: all spots filled?
//
//		for (int i=0; i < 9; i++) {
//
//			for (int j=0; j < 9; j++) {
//
//				// find not filled spot, try number 1 ~ 9
//				if (A[i][j] == 0) {
//
//					int num = 1;
//					while(num <= 9) {
//						// step 1. check if the num is valid at the spot
//						if (isValid(A, num, i, j)) {
//							// step 2. if valid, place the num at the spot
//							A[i][j] = num;
//
//							// stpe 3. decide if the placed num make the rest of the grid solvable:
//							// if yes, return true; if no, (back tracking) remove the placed number, continue try next number
//							if (isSolvable(A)) {
//								return true;
//							} else {
//								A[i][j] = 0;
//							}
//						}
//						num++;
//					}
//
//					// if tried all numbers between 1 ~ 9, didn't find one to fill in
//					if(num > 9) {
//						return false;
//					}
//				}
//			}
//		}
//
//		// all spots are already filled
////		if (i >= 9 && j >= 9) {
////			return true;
////		}
//		return true;
//	}

	/**
	 * 	recursion function: For each spot in the grid, if it's empty put a number at spot (row, col).
 	 */
	boolean fitSpot(int[][] A, int row, int col) {
		System.out.println("row = " + row + "   col = " + col);
		// base case: all spots filled
		if (row >= 9 || col >= 9) {
			return true;
		}

		int nextSpot_row = (col == 8) ? row + 1 : row;
		int nextSpot_col = (col == 8) ? 0 : col + 1;

		if(A[row][col] == 0) {   // note: only try fill if it's not already filled

			// try num: 1 ~ 9
			int num = 1;
			while (num <= 9) {
				// step 1. check if the num is valid at the spot
				if (isValid(A, num, row, col)) {
					// step 2. if valid, place the num at the spot
					A[row][col] = num;

					// stpe 3. decide if the placed num make the rest of the grid solvable:
					// if yes, return true; if no, (back tracking) remove the placed number, continue try next number
					if (fitSpot(A, nextSpot_row, nextSpot_col)) {
						return true;
					} else {
						A[row][col] = 0;
					}
				}
				num++;
			}
			// if tried all numbers between 1 ~ 9, didn't find one to fill in
			 return false;
		}
		else {
			System.out.println("The above is already filled");
			// if already filled, check next spot.
			return fitSpot(A, nextSpot_row, nextSpot_col);
		}
	}

	boolean solve(int[][] A) {
//		for (int i = 0; i < 9; i++) {
//			for (int j=0; j< 9; j++) {
//				if (A[i][j] == 0) {
					// any of the spot unsolvable makes the whole grid unsolvable
//					if(!fitSpot(A, 0, 0)) {
//						return false;
//					}
////				}
//			}

//		}

		// if all can be filled, return true;
//		return true;
	return fitSpot(A, 0, 0);
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
	}

	public static void main(String[] args) {
		Sudoku myclass = new Sudoku();

		int[][] A = new int[9][9];
		A[0][4] = 7;
		boolean isSolvable = myclass.solve(A);
		System.out.println("Is solvable = " + isSolvable);
		myclass.displayMatrix(A);
	}
}
