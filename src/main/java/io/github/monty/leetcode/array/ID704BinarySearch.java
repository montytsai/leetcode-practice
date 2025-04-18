package io.github.monty.leetcode.array;

/**
 * LeetCode #704:
 * <a href="https://leetcode.com/problems/binary-search">Binary Search</a>
 * <p>
 * 給定「有序」、「不重複」的陣列 nums，找尋 target 在陣列中的 index，若沒有則回傳 -1
 *
 * @author Monty.Tsai
 * @since 2025-04-18 13:45:00
 */
public class ID704BinarySearch {

    /**
     * 暴力解法，從 0 開始
     */
    public int searchZ(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 二分查找: 找中間值
     * 時間複雜度：O(log n)
     * 空間複雜度：O(1)
     * <br/>
     * 重點! 定義區間要想清楚，一般而言分為兩種:
     * 1. 左閉右閉 [left, right]: [0, len-1]
     * 2. 左閉右開 [left, right): [0, len)
     */
    public int search(int[] nums, int target) {
        // 定義 target 在左閉右閉的區間裡，[start, end]
        int startIndex = 0;
        int endIndex = nums.length - 1;

        while (startIndex <= endIndex) { // 這個"="，仍然在區間內
            int midIndex = (endIndex - startIndex) / 2 + startIndex;
            if (nums[midIndex] == target) {
                // 找到 target，回傳 index
                return midIndex;
            } else if (nums[midIndex] > target) {
                // target 在 [start, mid - 1] 區間
                endIndex = midIndex - 1;
            } else {
                // target 在 [mid + 1, end] 區間
                startIndex = midIndex + 1;
            }
        }

        return -1;
    }

    /**
     * 左閉右開 [left, right): [0, len)
     */
    public int search2(int[] nums, int target) {
        int left = 0;
        int right = nums.length;

        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }

}