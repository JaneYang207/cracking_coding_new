package leetCode;

public class UniquePath_62 {
    public int uniquePaths(int m, int n) {
        int[][] memo = new int[m][n];

        for (int row = 0; row < m; row++) {
            memo[row][0] = 1;
        }

        for (int col = 0; col < n; col++) {
            memo[0][col] = 1;
        }

        return recf(m-1, n-1, memo);
    }

    private int recf(int row, int col, int[][] memo) {
        if (row < 0 || col < 0) {
            return 0;
        }

        if (memo[row][col] == 0) {
            memo[row][col] = recf(row, col-1, memo) + recf(row-1, col, memo);
        }

        return memo[row][col];
    }

    public static void main(String[] args) {
        UniquePath_62 myclass = new UniquePath_62();
        int ans = myclass.uniquePaths(3,2);
        System.out.println(ans);
    }
}
