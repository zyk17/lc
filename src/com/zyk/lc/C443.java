package com.zyk.lc;

public class C443 {

    public static int compress(char[] str) {
        int slow = 0, fast = 0, cnt = 1, len = 0, N = str.length;
        for (; fast < N; cnt = 1, len++) {
            char c = str[fast];
            while (++fast < N && str[fast] == c) cnt++;
            str[slow++] = c;
            if (cnt != 1) {
                char[] v = String.valueOf(cnt).toCharArray();
                System.arraycopy(v, 0, str, slow, v.length);
                slow += v.length;
                len += v.length;
            }
        }
        return len;
    }


    // for test
    public static void main(String[] args) {
//        char[] str = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        char[] str = {'a', 'b', 'c'};
        System.out.println(compress(str));
        System.out.println(new String(str));
    }

}
