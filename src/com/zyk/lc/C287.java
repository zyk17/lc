package com.zyk.lc;

public class C287 {

    // 快慢指针， 与链表找环题一样
    public int findDuplicate(int[] nums) {
        int N = nums.length;    // 值都在 1 ~ N 中，有一个数字重复
        int slow = nums[0], fast = nums[ nums[0] ];
        while( slow != fast ) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0;
        while(slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return fast;
    }

}
