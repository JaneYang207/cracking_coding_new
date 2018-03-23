package cracking;

/**
 * Created by jyang on 4/3/17.
 */
public class RotateMatrix
{

	public int[][] rotateMatrix(int[][] matrix) throws NotSquareException{
		int N = decideSquareSize(matrix);

		if(N == 1) {
			return matrix;
		}
		else {
			int lowerBound, upperBound, temp;
			for (int layer = 0; layer < N/2; layer++) {
				lowerBound = layer;
				upperBound = N-1-lowerBound;

				for (int index = 0; index < upperBound - lowerBound; index++) {
					temp = matrix[lowerBound][index]; // move top to temp
					matrix[lowerBound][index] = matrix[upperBound - index][lowerBound]; // move left to top
					matrix[upperBound - index][lowerBound] = matrix[upperBound][upperBound - index]; // move bottom to left
					matrix[upperBound][upperBound - index] = matrix[lowerBound + index][upperBound]; // move right to bottom
					matrix[lowerBound + index][upperBound] = temp; // move temp to right
				}
			}
			return matrix;
		}
	}


	public int decideSquareSize(int[][] matrix) throws NotSquareException{
		int matrixSize = matrix.length;
		for (int i = 0; i < matrixSize; i++) {
			if(matrix[i].length != matrixSize) {
				 throw new NotSquareException();
			}
		}
		return matrixSize;
	}


	public void displayMatrix(int[][] arr) {
		for(int i=0; i < arr.length; i++) {
			for(int j=0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println("");
		}
	}

	// inner class
	public class NotSquareException extends Exception {
		public static final String msg = "The matrix is not a square";

		public NotSquareException() {
			super(msg);
		}
	}

	public static void main(String[] args) {
		RotateMatrix classInstance = new RotateMatrix();
//		int[][] matrix = {{1}};
//		int[][] matrix = {{1, 2}, {3, 4}};
//		int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
//		int[][] matrix = {{1, 2, 3, 4, 5, 6}, {7, 8, 9, 10, 11, 12}, {13, 14, 15, 16, 17, 18}, {19, 20, 21, 22, 23, 24}, {25, 26, 27, 28, 29, 30}, {31, 32, 33, 34, 35, 36}};
		int[][] matrix = {{1, 2}, {3, 4, 5} };

		System.out.println("=============Before Rotate================");
		classInstance.displayMatrix(matrix);

		int[][] returnedMatrix;

		try {
			returnedMatrix = classInstance.rotateMatrix(matrix);
			System.out.println("=============After Rotate================");
			classInstance.displayMatrix(returnedMatrix);
		} catch (NotSquareException e) {
			System.out.println(e.msg);
		}
	}
}
