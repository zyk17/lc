package com.zyk.lc;

public class C152 {

    public static int maxProduct(int[] nums) {
        int max = nums[0], imax = nums[0], imin = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] < 0) {       // 如果这个数是负数，会使大的变小， 小的变大
                int temp = imax;
                imax = imin;
                imin = temp;
            }
            imax = Math.max( imax * nums[i], nums[i] );
            imin = Math.min( imin * nums[i], nums[i] );
            max = Math.max(max, imax);
        }
        return max;
    }

}
