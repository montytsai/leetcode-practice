package io.github.monty.leetcode.string;

import java.util.Arrays;

/**
 * LeetCode #541:
 * <a href="https://leetcode.com/problems/reverse-string-ii">Reverse String II</a>
 * 給定一個字串 s 和一個整數 k，從字串開頭算起，每計數至 2k 個字元，就反轉這 2k 字元中的前 k 個字元。
 * 如果剩餘字元少於 k 個，則將剩餘字元全部反轉。
 * 如果剩餘字元小於 2k 但大於或等於 k 個，則反轉前 k 個字元，其餘字元保持原樣。
 *
 * @author Monty.Tsai
 * @since 2025-04-28 16:53:57
 */
public class ID541ReverseStringII {

    /**
     * for-loop，每次跳 2k
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 解題時間: 30 mins
     */
    public String reverseStr(String s, int k) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        // 每 2k 個字串處理一次邏輯
        char[] arr = s.toCharArray();
        for (int left = 0; left < arr.length; left += 2 * k) { // 處理字串的左界線(左閉)
            int right = Math.min(left + k - 1, arr.length - 1); // 處理字串的右界線(右閉)
            reverse(arr, left, right);
        }
        return new String(arr);
    }

    private void reverse(char[] arr, int start, int end) {
        if (arr.length <= 1 || start >= end) {
            return;
        }

        while (start < end) {
            char temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;

            start++;
            end--;
        }
    }

}
