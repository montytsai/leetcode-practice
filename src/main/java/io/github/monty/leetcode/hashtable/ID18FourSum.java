package io.github.monty.leetcode.hashtable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode #18:
 * <a href="https://leetcode.com/problems/4sum">4Sum</a>
 *
 * @author Monty.Tsai
 * @since 2025-04-26 20:47:44
 */
public class ID18FourSum {

    /**
     * # 排序 + 雙指針
     * ## 思路
     * 先將陣列排序，然後固定兩個數，搭配雙指針 (left, right) 向內移動，
     * 找出所有和為 target 的四元組，並去除重複組合。
     * ## 重點
     * 1. 排序後，第一個數如果重複，要跳過；第二個數也要去重。
     * 2. 雙指針移動時，同樣需要去重，以避免重複解。
     * ## 複雜度
     * - 時間複雜度：O(n^3)
     * - 空間複雜度：O(1)（不含輸出空間）
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();

        // 排序 Array
        Arrays.sort(nums);

        // 1. 固定第一 & 第二下標，雙指針移動剩餘解
        for (int i = 0; i < nums.length - 3; i++) {
            // 第一固定數去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 2. 找出第二個固定的數
            for (int j = i + 1; j < nums.length - 2; j++) {
                // 第二固定數去重
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                // 3. 剩餘兩數雙指針求解
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    // 注意! 記得溢位問題
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // 左右指針去重
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }

                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return result;
    }

}