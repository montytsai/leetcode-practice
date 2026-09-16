package io.github.monty.leetcode.array;

/**
 * LeetCode #59:
 * <a href="https://leetcode.com/problems/spiral-matrix-ii">Spiral Matrix II</a>
 *
 * @author Monty.Tsai
 * @since 2025-04-21 12:42:56
 */
public class ID59SpiralMatrixII {

    /**
     * 矩陣
     *
     * 筆記:
     * 1. 寫錯是因為使用太少變數，把上下左右限制的變數都拉出來簡單多了，不要害怕創造變數
     * 2. array 大重點，在設定條件時，要想好整體要選左閉右閉或左閉右開，維持一致!
     * 3. 踩雷笨點(可以略過):
     *  寫的時候在 while 迴圈最下才調整變數，所以邏輯是 A；
     *  提交時自作聰明把變數調整(ex. top++)移到每個 for 下面，導致 java 檔與 Leetcode 檔不同，一直測試失敗。
     */
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int val = 1; // 矩陣中的值
        // 設定起始點到終點
        int left = 0, right = n - 1;
        int top = 0, bottom = n - 1;

        while (left <= right && top <= bottom) {
            // 從左至右
            for (int i = left; i < right; i++) {
                matrix[top][i] = val++;
            }
            // 從上至下
            for (int i = top; i < bottom; i++) {
                matrix[i][right] = val++;
            }
            // 從右至左
            for (int i = right; i > left; i--) {
                matrix[bottom][i] = val++;
            }
            // 從下至上
            for (int i = bottom; i > top; i--) {
                matrix[i][left] = val++;
            }

            top++;
            right--;
            bottom--;
            left++;
        }

        // 奇數補上正中心
        if (n % 2 == 1) {
            matrix[n/2][n/2] = val;
        }

        return matrix;
    }

}