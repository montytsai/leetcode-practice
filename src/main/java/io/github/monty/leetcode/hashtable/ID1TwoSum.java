package io.github.monty.leetcode.hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode #1:
 * <a href="https://leetcode.com/problems/two-sum">Two Sum</a>
 * 給定一個整數陣列 nums 和一個目標值 target，在該陣列中找出和為目標值的那 兩個 整數，並返回他們的陣列下標。
 * 可以假設每種輸入只會對應一個答案。但是，陣列中同一個元素不能使用兩遍。
 *
 * @author Monty.Tsai
 * @since 2025-04-26
 */
public class ID1TwoSum {

    /**
     * 雜湊法
     * ## 重點
     * 什麼時候使用雜湊法？
     * 當我們需要「快速尋找元素是否存在」或「快速查詢元素位置」時，第一時間應考慮雜湊法。
     * ## 思路
     * - 使用 HashMap，記錄遍歷過的元素的「目標補數 complement」以及其下標。
     * - 每遍歷一個元素時，檢查當前數字是否存在於 HashMap，如果存在，直接回傳答案。
     * ## 複雜度
     * - 時間複雜度：O(n)
     * - 空間複雜度：O(n)
     */
    public int[] twoSum(int[] nums, int target) {
        // 儲存「希望湊成 target 的差值」以及「差值的下標」
        Map<Integer, Integer> idxByComplement = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            // 檢查目前元素是否是某個先前數字的補數
            if (idxByComplement.containsKey(nums[i])) {
                return new int[]{idxByComplement.get(nums[i]), i};
            }
            int complement = target - nums[i];
            idxByComplement.put(complement, i);
        }

        return new int[0]; // 題目保證有解，實際上不會走到這裡
    }

    /**
     * 暴力解: 雙迴圈判斷
     * Time Complexity: O(n^2)
     * Space Complexity: O(n)
     */
    public int[] twoSumZ(int[] nums, int target) {
        // 儲存希望湊成 target 的差值
        int[] complement = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            // 檢查先前儲存的差值是否存在於目前元素
            for (int j = 0; j < i; j++) {
                if (complement[j] == nums[i]) {
                    return new int[]{j, i};
                }
            }
            complement[i] = target - nums[i];
        }
        return new int[0];
    }

}
