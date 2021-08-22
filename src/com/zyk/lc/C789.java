package com.zyk.lc;

public class C789 {

    public static boolean escapeGhosts(int[][] ghosts, int[] target) {
        int myDist = dist(0, 0, target);
        for (int[] ghost : ghosts) {
            if (dist(ghost[0], ghost[1], target) <= myDist) {
                return false;
            }
        }
        return true;
    }

    public static int dist(int i, int j, int[] target) {
        return Math.abs(i - target[0]) + Math.abs(j - target[1]);
    }

}
