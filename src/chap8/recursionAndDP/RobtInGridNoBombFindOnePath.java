package chap8.recursionAndDP;

import java.util.ArrayList;
import java.util.List;

/**
 * find one path
 * Every spot can be stepped on, no bomb
 */
public class RobtInGridNoBombFindOnePath {


	///////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////// Start from destination (M, N), step back until reach (0, 0) //////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////        from (row, col)         /////////////////////////////////////
	/////////////////////////////////           /        \            /////////////////////////////////////
	////////////////////////////////           /          \           /////////////////////////////////////
	///////////////////////////////    (row-1, col)   (row, col-1)    /////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Post-order traverse: visit (row, col) (by adding it to "path" list) after visited it's children
	 * path: (0, 0)  --->   (0, 1)  --->   (0, 2)  --->   (0, 3)  --->   (1, 3)  --->   (2, 3)  --->   (3, 3)
	 */
	void recf_postOrder(int row, int col, List<Cordinate> path) {
		System.out.println("row = " + row + "    col = " + col);
		// base condition
//		if (row < 0 || col < 0) {
//			return;
//		}

//		if (row == 0 && col == 0) {
//			path.add(new Cordinate(0, 0));
//			System.out.println( "=================== Added ( " + row + ", " + col + " )");
//			return;
//		}

		// the following is wrong. Only one child is visited.
		// We first visit the left child, right child is visited only when left child index are out of bound
		// so should add conditions before call the recursion function
//		recf(row-1, col, path);
//		recf(row, col - 1, path);

		// With the condition "row - 1 >= 0 && col >= 0", the original recursion base condition "row < 0 || col < 0" is not needed any more;
		// new base condition should be "row == 0 && col == 0"
		if(row - 1 >= 0 && col >= 0) {
			recf_postOrder(row-1, col, path);
		} else if (row >= 0 && col - 1 >= 0) {
			recf_postOrder(row, col - 1, path);
		}

		System.out.println( "=================== Added ( " + row + ", " + col + " )");

		// path modified after recursion calls. Thus (0, 0) will be the first one added to path.
		// If we move this statement before recursion calls, (M, N) will be the first one added to path
		path.add(new Cordinate(row, col)); // After all children nodes are visited, process the parent node (add parent node to path)
	}

	/**
	 * Pre-order traverse: visit (row, col) (by adding it to "path" list) before visiting it's children
	 * path: (3, 3)  --->   (2, 3)  --->   (1, 3)  --->   (0, 3)  --->   (0, 2)  --->   (0, 1)  --->   (0, 0)
	 */
	void recf_preOrder(int row, int col, List<Cordinate> path) {
		// pre-order traverse
		path.add(new Cordinate(row, col));

		if (row - 1 >= 0 && col >= 0) {
			recf_preOrder(row - 1, col, path);
		}
		else if (row >= 0 && col - 1 >= 0) {
			recf_preOrder(row, col - 1, path);
		}
	}

	void iterationFunc(int M, int N, List<Cordinate> path) {
		int i = 0;
		int j = 0;

		// go first row
		while(j < M) {
			path.add(new Cordinate(i, j++));
		}

		// go the last column
		j--;
		while(i < N) {
			path.add(new Cordinate(i++, j));
		}
	}

	/**
	 * Iteration:
	 * This is not efficient, see "iterationFunc" for more optimized iteration
	 * path: (3, 3)  --->   (2, 3)  --->   (1, 3)  --->   (0, 3)  --->   (0, 2)  --->   (0, 1)  --->   (0, 0)
	 * @param M size
	 * @param N size
	 */
	void itf_stepBackward(int M, int N, List<Cordinate> path) {
		int row = M - 1;
		int col = N - 1;
		path.add(new Cordinate(row, col));

		while ((row-1 >= 0 && col >= 0) || (row >= 0 && col-1 >= 0))
		{
			if(row-1 >= 0 && col >= 0) {
				path.add(new Cordinate(row-1, col));
				row--;
			}
			else {
				path.add(new Cordinate(row, col-1));
				col--;
			}
		}
	}


	List<Cordinate> findOnePath(int M, int N) {
		List<Cordinate> path = new ArrayList<>();
		recf_postOrder(M-1, N-1, path);
//		recf_preOrder(M-1, N-1, path);
//		itf_stepBackward(M, N, path);
		iterationFunc(M, N, path);
		return path;
	}


	public void display(List<Cordinate> path) {
		for(Cordinate po : path) {
			System.out.print("(" + po.row + ", " + po.col + ")  --->   ");
		}
	}

	private class Cordinate {
		int row;
		int col;

		public Cordinate(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}


	///////////////////////////////////////////////////////////////////////////////////////////////////////
  /////////////// Start from (0, 0), step forward until reach destination (M-1, N-1) ////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////        from (row, col)         /////////////////////////////////////
	/////////////////////////////////           /        \            /////////////////////////////////////
	////////////////////////////////           /          \           /////////////////////////////////////
	///////////////////////////////    (row, col+1)   (row+1, col)    /////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Pre-order traverse: visit (row, col) (by adding it to "path" list) before visiting it's children
	 * path: (0, 0)  --->   (0, 1)  --->   (0, 2)  --->   (0, 3)  --->   (1, 3)  --->   (2, 3)  --->   (3, 3)
	 * @param M row size
	 * @param N col size
	 */
	void recf_preOrder_stepForward(int row, int col, List<Cordinate> path, int M, int N) {
		// pre-order traverse
		path.add(new Cordinate(row, col));

		if (row <= M - 1 && col + 1 <= N - 1) {
			recf_preOrder_stepForward(row, col + 1, path, M, N);
		}
		else if (row + 1 <= M -1 && col <= N - 1) {
			recf_preOrder_stepForward(row + 1, col, path, M, N);
		}
	}

	/**
	 * Post-order traverse: visit (row, col) (by adding it to "path" list) after visited it's children
	 * path: (3, 3)  --->   (2, 3)  --->   (1, 3)  --->   (0, 3)  --->   (0, 2)  --->   (0, 1)  --->   (0, 0)
	 * @param M row size
	 * @param N col size
	 */
	void recf_postOrder_stepForward(int row, int col, List<Cordinate> path, int M, int N) {
		if (row <= M - 1 && col + 1 <= N - 1) {
			recf_postOrder_stepForward(row, col + 1, path, M, N);
		}
		else if (row + 1 <= M -1 && col <= N - 1) {
			recf_postOrder_stepForward(row + 1, col, path, M, N);
		}

		// post-order traverse
		path.add(new Cordinate(row, col));
	}

	/**
	 * Iteration:
	 * This is not efficient, see "iterationFunc" for more optimized iteration
	 * path: (0, 0)  --->   (0, 1)  --->   (0, 2)  --->   (0, 3)  --->   (1, 3)  --->   (2, 3)  --->   (3, 3)
	 * @param M size
	 * @param N size
	 */
	void itf_stepForward(int M, int N, List<Cordinate> path) {
		int row = 0;
		int col = 0;
		path.add(new Cordinate(row, col));

		while ((row < M && col+1 < N) || (row+1 < M && col < N))
		{
			if(row < M && col+1 < N) {
				path.add(new Cordinate(row, col+1));
				col++;
			}
			else {
				path.add(new Cordinate(row+1, col));
				row++;
			}
		}
	}

	List<Cordinate> findOnePath_stepForward(int M, int N) {
		List<Cordinate> path = new ArrayList<>();
//		recf_preOrder_stepForward(0, 0, path, M, N);
//		recf_postOrder_stepForward(0, 0, path, M, N);
//		itf_stepForward(M, N, path);
		iterationFunc(M, N, path);
		return path;
	}



	public static void main(String[] args) {
		RobtInGridNoBombFindOnePath myclass = new RobtInGridNoBombFindOnePath();
//		List<Cordinate> path = myclass.findOnePath(4, 4);
		List<Cordinate> path = myclass.findOnePath_stepForward(4, 4);
		myclass.display(path);
	}
}
