package io.github.monty.leetcode.string;

/**
 * LeetCode #459:
 * <a href="https://leetcode.com/problems/repeated-substring-pattern">Repeated Substring Pattern</a>
 *
 * @author Monty.Tsai
 * @since 2025-05-01
 */
public class ID459RepeatedSubstringPattern {

    /**
     * 解法: KMP 算法判斷是否由重複子字串構成
     * 利用 LPS（前綴表）找出循環節長度，若能整除原字串長度，則為重複組成。
     *
     * Time: O(n), Space: O(n)
     */
    public boolean repeatedSubstringPattern(String s) {
        if (s.length() <= 1) return false;

        // 1. 求 s 的前綴表
        int[] lps = buildLPS(s);

        // 2. 看前綴表的最後一位，移動到該 index；若是整串字串能被前面整除，表示是重複子字串組成
        int len = s.length();
        int lpsLast = lps[len - 1];
        return lpsLast > 0 && len % (len - lpsLast) == 0;
    }

    private int[] buildLPS(String s) {
        int[] lps = new int[s.length()];

        // 初始化
        lps[0] = 0;
        int len = 0;

        for (int i = 1; i < s.length(); i++) { // 注意從 1 開始!
            // 1. 前後綴不相同
            while(len > 1 && s.charAt(i) != s.charAt(len)) {
                len = lps[len - 1];
            }

            // 2. 前後綴相同
            if (s.charAt(i) == s.charAt(len)) {
                len++;
            }

            // 3. 賦值
            lps[i] = len;
        }

        return lps;
    }

    /**
     * 解法: 拼接字串 + 去頭尾 + 包含判斷
     * Time: O(n)
     * Space: O(n)
     */
    public boolean repeatedSubstringPatternZ(String s) {
        if (s.length() <= 1) return false;

        StringBuilder sb = new StringBuilder(s);
        sb.append(s);
        sb.deleteCharAt(0); // 刪除第一個字元
        sb.setLength(sb.length() - 1); // 刪除最後一個字元

        return sb.toString().contains(s);
    }


}