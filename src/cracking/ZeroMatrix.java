package cracking;

/**
 * Created by jyang on 4/3/17.
 */
public class ZeroMatrix
{
	public void setZerosForMatrix(int[][] matrix) throws NullPointerException{

		if(matrix == null) {
			throw new NullPointerException();
		}

		int M = matrix.length;
		int N = matrix[0].length;

		boolean[] rowsHasZero = new boolean[M];
		boolean[] columnsHasZero = new boolean[N];

		for (int i = 0; i < M; i++) {
			for (int j=0; j < N; j++) {
				if (matrix[i][j] == 0) {
					rowsHasZero[i] = true;
					columnsHasZero[j] = true;
				}
			}
		}

		for (int rowIndex = 0; rowIndex < rowsHasZero.length; rowIndex++) {
			if (rowsHasZero[rowIndex]) {
				setEntireRowToZero(matrix, rowIndex);
			}
		}

		for (int colIndex = 0; colIndex < columnsHasZero.length; colIndex++) {
			if (columnsHasZero[colIndex]) {
				setEntireColumnToZero(matrix, colIndex);
			}
		}
	}

	public void setEntireRowToZero(int[][] matrix, int row) {
		for (int j = 0; j < matrix[0].length; j++) {
			matrix[row][j] = 0;
		}
	}

	public void setEntireColumnToZero(int[][] matrix, int column) {
		for (int i = 0; i < matrix.length; i++) {
			matrix[i][column] = 0;
		}
	}

	public void displayMatrix(int[][] arr) throws NullPointerException {
		if(arr == null) {
			throw new NullPointerException();
		}
		for(int i=0; i < arr.length; i++) {
			for(int j=0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println("");
		}
	}

	public static void main (String[] args) {

//		int[][] matrix = {{1, 0, 3}, {4, 5, 6} };
//		int[][] matrix = {{1, 0, 0}, {4, 5, 6} };
//		int[][] matrix = null;
//		int[][] matrix = {{1, 0}, {4, 5, 6} };
		int[][] matrix = {{1, 0}, {4, 5, 0} };

		ZeroMatrix zeroMatrixInstance = new ZeroMatrix();
		try
		{
			System.out.println("=============Before set zero================");
			zeroMatrixInstance.displayMatrix(matrix);

			zeroMatrixInstance.setZerosForMatrix(matrix);
			System.out.println("=============After set zero================");
			zeroMatrixInstance.displayMatrix(matrix);
		} catch (NullPointerException e) {
			System.out.println("=======the matrix is null=======");
		}

	}
}
