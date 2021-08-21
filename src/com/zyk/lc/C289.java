package com.zyk.lc;

public class C289 {

    public static void gameOfLife(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int numOfNeighbor = numOfNeighbor(board, i, j);
                if( numOfNeighbor == 3 || (board[i][j] == 1 && numOfNeighbor == 2) ) {
                    board[i][j] |= 2;
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] >>= 1;
            }
        }
    }

    public static int numOfNeighbor(int[][] b, int i, int j) {
        return f(b, i - 1, j - 1) +
                f(b, i - 1, j) +
                f(b, i, j - 1) +
                f(b, i + 1, j + 1) +
                f(b, i + 1, j) +
                f(b, i, j + 1) +
                f(b, i + 1, j - 1) +
                f(b, i - 1, j + 1) ;
    }

    // 当前位置，当前轮是否有细胞
    public static int f(int[][] b, int i, int j) {
        if(i < 0 || j < 0 || i == b.length || j == b[0].length)
            return 0;
        return b[i][j] & 1;
    }

}
