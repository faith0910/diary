package com.nannan.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @program:demo
 * @description:
 * @author:Juwenchao
 * @date:2020-06-11 17:37:42
 */
public class LeetCode {


    public static void main(String[] args) {
        int nums[] = {2, 7, 11, 15};int target =18;
        int [] s = twoSum(nums,target);
        for (int i = 0; i < s.length; i++) {
            System.out.println(i);
        }
    }
    /**
     *   两数之和
     */
    public static int[] twoSum(int nums[], int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int tem =target - nums[i];
            if (map.containsKey(tem)) {
                return new int[] { map.get(tem), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
