package com.zyk.lc;

import java.util.ArrayList;
import java.util.List;

public class C163 {

    // 寻找 从lower ～ upper 寻找数组中确实的数组
    // 返回形式：2, 5~10, 76~99
    public static List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ans = new ArrayList<>();
        int i = 0, j = lower;
        int pre = -1;
        for (; j <= upper && i < nums.length; j++) {
            if(nums[i] == j) {
                if(pre != -1) {
                    if (pre == nums[i] - 1) {
                        ans.add(String.valueOf(pre));
                    } else {
                        ans.add(pre + "~" + (j - 1));
                    }
                    pre = -1;
                }
                i++;
            }else {
                pre = pre == -1 ? j : pre;
            }
        }
        if(j <= upper) {
            ans.add(nums[nums.length-1] + 1 + "~" + upper);
        }
        return ans;
    }

    // for test
    public static void main(String[] args) {
        int[] nums = {2, 3, 4, 5, 9, 12, 13, 14, 15, 22};
        int lower = 1, upper = 30;
        System.out.println(findMissingRanges(nums, lower, upper));
    }

}
