package com.zyk.lc;

public class C340 {

    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        char[] str = s.toCharArray();
        int N = str.length, l = 0, r = 0, diff = 0, ans = 0;
        int[] cnt = new int[256];
        while(l < N) {
            while(r < N && (diff < k || (diff == k && cnt[str[r]] > 0))) {
                diff += cnt[str[r]] == 0 ? 1 : 0;
                cnt[str[r++]]++;
            }
            ans = Math.max(ans, r-l);   // r 首次来到越界的位置了，所以不用+1
            // 窗口缩小
            diff -= cnt[str[l]] == 1 ? 1 : 0;
            cnt[str[l++]]--;
        }
        return ans;
    }

}
