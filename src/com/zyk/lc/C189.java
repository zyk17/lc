package com.zyk.lc;

public class C189 {
    
    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        int mid = n - k % n;        // 0 ~ mid-1, mid - n-1
        int l = 0, r = mid-1;
        while(l < r) {
            int temp = nums[l];
            nums[l++] = nums[r];
            nums[r--] = temp;
        }
        l = mid;
        r = n-1;
        while(l < r) {
            int temp = nums[l];
            nums[l++] = nums[r];
            nums[r--] = temp;
        }
        l = 0;
        r = n-1;
        while(l < r) {
            int temp = nums[l];
            nums[l++] = nums[r];
            nums[r--] = temp;
        }
    }
    
}
