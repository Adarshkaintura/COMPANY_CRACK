class Solution {
    private int n;
    private int[][] dp;
    private int[][] matrix;

    public int minFallingPathSum(int[][] matrix) {
        this.n = matrix.length;
        this.dp = new int[n][n];
        this.matrix = matrix;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        int minPath = Integer.MAX_VALUE;
        for (int col = 0; col < n; col++) {
            minPath = Math.min(minPath, dfs(0, col));
        }
        return minPath;
    }

    private int dfs(int row, int col) {
        if (col < 0 || col >= n) {
            return Integer.MAX_VALUE;
        }

        if (row == n - 1) {
            return matrix[row][col];
        }

        if (dp[row][col] != Integer.MAX_VALUE) {
            return dp[row][col];
        }

        int down = dfs(row + 1, col);
        int left = dfs(row + 1, col - 1);
        int right = dfs(row + 1, col + 1);

        dp[row][col] = matrix[row][col] + Math.min(down, Math.min(left, right));
        return dp[row][col];
    }
}
