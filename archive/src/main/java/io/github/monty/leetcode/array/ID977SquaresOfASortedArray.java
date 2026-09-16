package io.github.monty.leetcode.array;

/**
 * LeetCode #977:
 * <a href="https://leetcode.com/problems/squares-of-a-sorted-array">Squares of a Sorted Array</a>
 *
 * 給定一個遞增的陣列，回傳平方後且遞增的陣列。 時間複雜度必須為 O(n)。
 *
 * @author Monty.Tsai
 * @since 2025-04-19 21:32:21
 */
public class ID977SquaresOfASortedArray {

    /**
     * 雙指針法
     *
     * 計時: 17:15
     * 時間複雜度: O(n)
     * 空間複雜度: O(n)
     */
    public int[] sortedSquares(int[] nums) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;

        int[] sortedSquares = new int[len];
        for (int idx = len - 1; idx >= 0; idx--) {
            int leftVal = nums[left] * nums[left];
            int rightVal = nums[right] * nums[right];
            if (leftVal > rightVal) {
                sortedSquares[idx] = leftVal;
                left++;
            } else {
                sortedSquares[idx] = rightVal;
                right--;
            }
        }
        return sortedSquares;
    }

}