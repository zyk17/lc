package com.zyk.lc;

import java.util.Arrays;

public class C576 {

    public static int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        return dfs(startRow, startColumn, m, n, maxMove);
    }

    public static int dfs(int r, int c, int R, int C, int rest) {
        if (r < 0 || c < 0 || r == R || c == C) {
            return 1;
        }
        if (rest == 0) {
            return 0;
        }
        int ans = dfs(r + 1, c, R, C, rest - 1) +
                dfs(r - 1, c, R, C, rest - 1) +
                dfs(r, c + 1, R, C, rest - 1) +
                dfs(r, c - 1, R, C, rest - 1);
        return ans;
    }

    // 记忆化搜索
    public static int findPaths2(int m, int n, int maxMove, int startRow, int startColumn) {
        int[][][] dp = new int[m][n][maxMove + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k <= maxMove; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        return dfs2(startRow, startColumn, m, n, maxMove, dp);
    }

    public static int dfs2(int r, int c, int R, int C, int rest, int[][][] dp) {
        if (r < 0 || c < 0 || r == R || c == C) {
            return 1;
        }
        if (rest == 0) {
            return 0;
        }
        if (dp[r][c][rest] != -1) {
            return dp[r][c][rest];
        }
        int ans = dfs2(r + 1, c, R, C, rest - 1, dp) +
                dfs2(r - 1, c, R, C, rest - 1, dp) +
                dfs2(r, c + 1, R, C, rest - 1, dp) +
                dfs2(r, c - 1, R, C, rest - 1, dp);
        return dp[r][c][rest] = ans;
    }

    public static int findPaths3(int m, int n, int maxMove, int startRow, int startColumn) {
        final int MOD = 1000000007;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int outCounts = 0;
        int[][] dp = new int[m][n];
        dp[startRow][startColumn] = 1;
        for (int i = 0; i < maxMove; i++) {
            int[][] dpNew = new int[m][n];
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    int count = dp[j][k];
                    if (count > 0) {
                        for (int[] direction : directions) {
                            int j1 = j + direction[0], k1 = k + direction[1];
                            if (j1 >= 0 && j1 < m && k1 >= 0 && k1 < n) {
                                dpNew[j1][k1] = (dpNew[j1][k1] + count) % MOD;
                            } else {
                                outCounts = (outCounts + count) % MOD;
                            }
                        }
                    }
                }
            }
            dp = dpNew;
        }
        return outCounts;
    }

    public static void main(String[] args) {
        /*int m = 8,
                n = 50,
                maxMove = 23,
                startRow = 5,
                startColumn = 26;
        int ans1 = findPaths3(m, n, maxMove, startRow, startColumn);
        int ans2 = findPaths2(m, n, maxMove, startRow, startColumn);

        System.out.println(ans1);
        System.out.println(ans2);*/
        int maxN = 5;
        int maxMove = 50;
        int times = 100000;
        System.out.println("测试开始===");
        for (int i = 0; i < times; i++) {
            int[] testData = generateTestData(maxN, maxMove);
            int ans1 = findPaths3(testData[0], testData[1], testData[2], testData[3], testData[4]);
            int ans2 = findPaths2(testData[0], testData[1], testData[2], testData[3], testData[4]);
            if(ans1 != ans2) {
                System.out.println("OOPS");
                System.out.println(Arrays.toString(testData));
                System.out.println(ans1);
                System.out.println(ans2);
            }
        }
        System.out.println("测试结束！");
    }

    public static int[] generateTestData(int maxN, int maxMove) {
        int R = (int) (maxN * Math.random()) + 1;
        int C = (int) (maxN * Math.random()) + 1;
        maxMove = (int) (maxMove * Math.random()) + 1;
        int startRow = (int) (Math.random() * R);
        int startColumn = (int) (Math.random() * C);
        return new int[] {R, C, maxMove, startRow, startColumn};
    }

}
