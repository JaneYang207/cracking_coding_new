package chap8.recursionAndDP;


import java.util.ArrayList;
import java.util.List;

public class RobotInGridHasBombFindOnePath {
	// go back
	// grid: false: no bomb; true: has bomb
	public boolean recf_back(int row, int col, boolean[][] grid, List<Cordinate> path) {
		if (row < 0 || col < 0 || grid[row][col]) {
			return false;
		}

		if ( (row == 0 && col == 0) ||
			recf_back(row-1, col, grid, path) ||
			recf_back(row, col-1, grid, path) )
		{
			path.add(new Cordinate(row, col));
			return true;
		}
		return false;
	}

	// go forward
	// grid: false: no bomb; true: has bomb
	public boolean recf_forward(int row, int col, boolean[][] grid, List<Cordinate> path) {
		if (row >= grid.length || col >= grid[0].length || grid[row][col]) {
			return false;
		}

		Cordinate spot = new Cordinate(row, col);
		path.add(spot);

		if ( (row == grid.length-1 && col == grid[0].length-1) ||
			recf_forward(row, col+1, grid, path) ||
			recf_forward(row+1, col, grid, path) )
		{
			return true;
		} else {
			path.remove(spot);
			return false;
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

	public List<Cordinate> findOnePath(boolean[][] grid) {
		List<Cordinate> path = new ArrayList<>();
//		boolean solvable = recf_back(grid.length-1, grid[0].length - 1, grid, path);
		boolean solvable = recf_forward(0, 0, grid, path);
		if (!solvable) {
			System.out.println("No path exist.");
		}
		return path;
	}

	public void display(List<Cordinate> path) {
		for(Cordinate po : path) {
			System.out.print("(" + po.row + ", " + po.col + ")  --->   ");
		}
	}

	public static void main(String[] args) {
		RobotInGridHasBombFindOnePath myclass = new RobotInGridHasBombFindOnePath();
		boolean[][] grid = new boolean[3][4];
		grid[0][3] = true;
		grid[1][2] = true;

		List<Cordinate> paths = myclass.findOnePath(grid);

		myclass.display(paths);
	}
}
