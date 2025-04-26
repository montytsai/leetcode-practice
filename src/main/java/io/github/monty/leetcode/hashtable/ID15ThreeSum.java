package io.github.monty.leetcode.hashtable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode #15:
 * <a href="https://leetcode.com/problems/3sum">3Sum</a>
 * 找出陣列中所有「和為 0」的不重複三元組。
 *
 * @author Monty.Tsai
 * @since 2025-04-26
 */
public class ID15ThreeSum {

    /**
     * # 排序 + 雙指針
     * ## 思路
     * 先將陣列排序，然後固定一個數，搭配雙指針 (left, right) 向內移動，
     * 找出所有和為 0 的三元組，並去除重複組合。
     * ## 重點
     * 1. 排序後，如果固定數已經 > 0，可以提早結束（因為後面也都是正數）。
     * 2. 固定數與雙指針都要「去重」避免重複解。
     * ## 複雜度
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)（不含輸出空間）
     */
    /*
    1. 對 nums 排序
    2. for i = 0 到 nums.length
       ├─ 若 nums[i] > 0：直接 break（後面都無解）
       ├─ 若 nums[i] == nums[i-1]：跳過（去重）
       ├─ 設定 left = i + 1, right = nums.length - 1
       │
       │ while left < right
       │   ├─ 計算 sum = nums[i] + nums[left] + nums[right]
       │   ├─ if sum == 0
       │   │    ├─ 加入答案
       │   │    ├─ 去重後，left++，right--
       │   ├─ else if sum < 0
       │   │    ├─ left++
       │   ├─ else
       │   │    ├─ right--
    */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        // 1. 將 nums 從小排到大 (方便去重)
        Arrays.sort(nums);

        // 2. 雙指針: 固定第一個指針，剩餘分成 left 和 right 指針，兩邊互相靠近，算出結果集
        for (int i = 0; i < nums.length; i++) {
            // 排序後的陣列，如果第一個數就是正數，後面一定加不到 0
            if (nums[i] > 0) {
                break;
            }
            // 固定指針去重（避免重複固定同個數）
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 指針
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int threeSum = nums[i] + nums[left] + nums[right];

                if (threeSum == 0) { // 找到解
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // 左右指針去重
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    // 移動指針繼續找下一組
                    left++;
                    right--;
                } else if (threeSum < 0) {
                    // 總和太小，左指針右移
                    left++;
                } else {
                    // threeSum > 0 總和太大，右指針左移
                    right--;
                }
            }
        }

        return result;
    }

}