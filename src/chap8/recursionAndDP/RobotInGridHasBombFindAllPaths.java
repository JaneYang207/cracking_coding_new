package chap8.recursionAndDP;

import java.util.ArrayList;
import java.util.List;

public class RobotInGridHasBombFindAllPaths {

	/**
	 *
	 * @param row
	 * @param col
	 * @param grid false -- doesn't have bomb; true -- has bomb
	 * @param curPath
	 * @param allPaths
	 */
	public void recf (int row, int col, boolean[][] grid, List<Cordinate> curPath, List<List<Cordinate>> allPaths) {
		// base condition:
		if (row >= grid.length || col >= grid[0].length || grid[row][col]) {
			return; // just return, do nothing
		}

		// else if not base condition (index not out of bound, also doesn't have bomb), then do the following:
		// 1. Pre-order: add (row, col) to path
		// 2. Visit children: both left and right
		// 3. Post-order: remove (row, col) to path before returning control to parent node

		Cordinate spot = new Cordinate(row, col);
		curPath.add(spot);

		if ( row == grid.length -1 && col == grid[0].length -1) {
			allPaths.add(deepCopy(curPath));
		}

		// first check left child
		recf(row, col + 1, grid, curPath, allPaths);

		// then check right child
		recf(row + 1, col, grid, curPath, allPaths);

		// always back-tracking when want to find all paths
		curPath.remove(spot);
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

	List<Cordinate> deepCopy(List<Cordinate> path) {
		List<Cordinate> copy = new ArrayList<>();
		for (Cordinate spot: path) {
			copy.add(spot);
		}
		return copy;
	}

	private class Cordinate {
		int row;
		int col;

		public Cordinate(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	List<List<Cordinate>> findAllPaths(boolean[][] grid) {
		List<Cordinate> curPath = new ArrayList<>();
		List<List<Cordinate>> paths = new ArrayList<>();
		recf(0, 0, grid, curPath, paths);
		return paths;
	}

	public static void main(String[] args) {
		RobotInGridHasBombFindAllPaths myclass = new RobotInGridHasBombFindAllPaths();
		boolean[][] grid = new boolean[3][4];
		grid[0][3] = true;
		grid[1][2] = true;

		List<List<Cordinate>> paths = myclass.findAllPaths(grid);

		myclass.displayAll(paths);
	}
}
