package io.github.monty.leetcode.string;

/**
 * LeetCode #28:
 * <a href="https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string">Find the Index of the First Occurrence in a String</a>
 *
 * @author Monty.Tsai
 * @since 2025-05-01
 */
public class ID28FindTheIndexOfTheFirstOccurrenceInAString {

    /**
     * 解法: KMP 演算法
     * 核心思想：避免重複比對。若字元不匹配，根據前綴表移動 needle 位置。
     *
     * Step 1: 構建前綴表（Longest Prefix which is also Suffix）
     * Step 2: 使用兩指標 hIdx, nIdx 對 haystack 與 needle 進行比對
     *         若匹配則同步移動，若不匹配則根據 lps 回退 needle 位置
     *
     * Time Complexity: 最壞 O(n + m)
     * Space Complexity: O(m)
     */
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) return 0;

        // 取得 needle 的前綴表
        int[] lps = buildLPS(needle);

        int nIdx = 0;
        for (int hIdx = 0; hIdx < haystack.length(); hIdx++) {
            // 當 haystack 與 needle 當前的字元不同，根據 LPS 結果，needle 回退到 前一個 可能相同的位置
            while (nIdx > 0 && haystack.charAt(hIdx) != needle.charAt(nIdx)) {
                nIdx = lps[nIdx - 1];
            }

            // 當前字元相同，繼續比較下一個字元
            if (haystack.charAt(hIdx) == needle.charAt(nIdx)) {
                nIdx++;
            }

            // needle 字元比較完成，表示全相符，回傳開始的 index
            if (nIdx == needle.length()) {
                return hIdx - needle.length() + 1;
            }
        }

        // 全部字元比較完，沒有相同
        return -1;
    }

    /**
     * 建立 字串 的部分匹配表（Longest Prefix Suffix, LPS）
     *  lps[i] 代表：pattern[0..i] 的子字串中，前綴與後綴相等的最大長度。
     * 用於主流程中在發生 mismatch 時決定 needle 應往哪裡移動（避免重頭比較X）
     *
     * @param pattern 字串
     * @return 字串的部分匹配表
     */
    private int[] buildLPS(String pattern) {
        int[] lps = new int[pattern.length()];

        // 1. 初始化
        int len = 0; // 當前前綴的長度
        lps[0] = len;

        for (int i = 1; i < pattern.length(); i++) { // 注意 i 從 1 開始
            // 2. 前後綴不相同: 去看前一個值
            while (len > 0 && pattern.charAt(len) != pattern.charAt(i)) {
                len = lps[len - 1];
            }

            // 3. 找到相同的前後綴: i, len 一起前進
            if (pattern.charAt(len) == pattern.charAt(i)) {
                len++;
            }

            // 4. 賦值: 將 len (前綴的長度) 賦給 lps[i]
            lps[i] = len;
        }

        return lps;
    }

    /**
     * 解法: 暴力解法（雙重迴圈直接比對）
     *
     * Step 1: 以 i 為 haystack 起始位置，從 0 遍歷至 haystack.length - needle.length
     * Step 2: 針對每個起點，逐字與 needle 比對
     *         若有不符則中斷比對並換下一個起點
     *         若整段匹配成功則回傳起始 index
     *
     * Time Complexity: O(n * m)
     * Space Complexity: O(1)
     */
    public int strStrZ(String haystack, String needle) {
        if (haystack == null || needle == null) return -1;
        if (needle.isEmpty()) return 0;

        // 最後可能比對起點為 hLen - nLen，超過就沒必要比對
        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
            int hIdx = i;
            for (int nIdx = 0; nIdx < needle.length(); nIdx++) {
                // 若有任一字元不符，即跳出本次比對
                if (haystack.charAt(hIdx++) != needle.charAt(nIdx)) {
                    break;
                }

                // 若成功比對至 needle 最後一位，代表完全匹配
                if (nIdx == needle.length() - 1) {
                    return i;
                }
            }
        }

        // 找不到匹配，回傳 -1
        return -1;
    }

}