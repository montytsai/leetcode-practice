package io.github.monty.leetcode.hashtable;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode #202:
 * <a href="https://leetcode.com/problems/happy-number">Happy Number</a>
 * 判斷一個數字是否為「快樂數」。
 * <p>
 * 題目說明：
 * - 對於一個正整數，每次將數字各位的平方和取代原數字
 * - 最後結果若為 1，則為快樂數；若陷入無限循環，則非快樂數
 * <p>
 * 解法說明：
 * - 使用 Set 紀錄過往出現過的數字，避免進入無窮迴圈
 * - 每次計算數字各位數字的平方和，更新 n
 * - 若結果為 1，返回 true；若出現重複數字，返回 false
 * <p>
 * 時間複雜度：O(log n)
 * - 每次操作將數字縮小到各位數平方和，過程中數字會下降，最多 O(log n) 次
 * <p>
 * 空間複雜度：O(log n)
 * - 最壞情況下需要儲存過去出現過的數字集合，大小與操作次數成正比
 *
 * @author Monty.Tsai
 * @since 2025-04-26
 */
public class ID202HappyNumber {

    /**
     * 判斷給定數字是否為快樂數
     *
     * @param n 待檢查的正整數
     * @return 如果是快樂數則返回 true，否則返回 false
     */
    public boolean isHappy(int n) {
        // 建立結果集，儲存已出現過的數字，避免進入無限循環
        Set<Integer> resultSet = new HashSet<>();

        // 只要 n 不是 1 且沒出現過，就持續進行
        while (n != 1 && !resultSet.contains(n)) {
            resultSet.add(n);

            int sum = 0;
            // 計算 n 各位數字平方的總和
            while (n > 0) {
                int remainder = n % 10;
                sum += remainder * remainder;
                n = n / 10;
            }
            n = sum;
        }

        // n 變成 1，代表是快樂數；否則是陷入循環
        return n == 1;
    }
}
