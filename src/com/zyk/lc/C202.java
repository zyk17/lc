package com.zyk.lc;

import java.util.HashSet;

public class C202 {

    public static boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        while(n != 1 && !set.contains(n)) {
            set.add(n);
            n = calc(n);
        }
        return n==1;
    }

    private static int calc(int n) {
        int res = 0;
        while (n != 0) {
            res += (n % 10) * (n % 10);
            n /= 10;
        }
        return res;
    }

}
