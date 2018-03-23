package chap8.recursionAndDP;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jyang on 2/19/18.
 */
public class RobotInGridNoBombFindAllPaths {

	void recf(int row, int col, int M, int N, List<Cordinate> curPath, List<List<Cordinate>> paths) {

		// pre-order traverse
		curPath.add(new Cordinate(row, col));

		if (row == M - 1 && col == N - 1) {
			// need deep copy here
			paths.add(deepCopy(curPath));
		}

		// try go right
		if (row <= M - 1 && col + 1 <= N - 1) {
			recf(row, col + 1, M, N, curPath, paths);
		}
		// keep trying go down
		if (row + 1 <= M -1 && col <= N - 1) {
			recf(row + 1, col, M, N, curPath, paths);
		}

//	  curPath.remove(new Cordinate(row, col));
	  curPath.remove(curPath.size()-1);
	}

	public void display(List<Cordinate> path) {
		for(Cordinate po : path) {
			System.out.print("(" + po.row + ", " + po.col + ")  --->   ");
		}
	}

	public void displayAll(List<List<Cordinate>> paths) {
		for(List<Cordinate> path : paths) {
			display(path);
			System.out.println("");
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

	List<List<Cordinate>> findAllPaths(int M, int N) {
		List<Cordinate> curPath = new ArrayList<>();
		List<List<Cordinate>> paths = new ArrayList<>();
		recf(0, 0, M, N, curPath, paths);
		return paths;
	}

	List<Cordinate> deepCopy(List<Cordinate> path) {
		List<Cordinate> copy = new ArrayList<>();
		for (Cordinate spot: path) {
			copy.add(spot);
		}
		return copy;
	}

	public static void main(String[] args) {
		RobotInGridNoBombFindAllPaths myclass = new RobotInGridNoBombFindAllPaths();

		List<List<Cordinate>> paths = myclass.findAllPaths(3, 4);
		myclass.displayAll(paths);
	}
}
