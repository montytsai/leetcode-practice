package io.github.monty.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 題目：Two Sum
 * 給定一個整數陣列 nums 和一個目標值 target，
 * 回傳兩個索引，使得 nums[i] + nums[j] == target。
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }

            map.put(nums[i], i);
        }

        // 若無解，回傳空陣列（實務上可以拋出例外）
        return new int[]{};
    }
}
