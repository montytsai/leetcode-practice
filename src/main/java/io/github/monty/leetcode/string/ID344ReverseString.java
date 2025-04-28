package io.github.monty.leetcode.string;

/**
 * LeetCode #344:
 * <a href="https://leetcode.com/problems/reverse-string">Reverse String</a>
 * 反轉字串
 * 空間複雜度要求：O(1)
 *
 * @author Monty.Tsai
 * @since 2025-04-28 16:33:57
 */
public class ID344ReverseString {

    /**
     * 雙指針
     * 時間複雜度：O(n)
     * 空間複雜度：O(1)
     * 解題時間: 4:20
     */
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            // reverse
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            // next character
            left++;
            right--;
        }
    }

}