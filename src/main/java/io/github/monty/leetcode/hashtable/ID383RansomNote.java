package io.github.monty.leetcode.hashtable;

/**
 * LeetCode #383:
 * <a href="https://leetcode.com/problems/ransom-note">Ransom Note</a>
 * 給定兩個字串 ransomNote 和 magazine，判斷 ransomNote 能否由 magazine 裡的字母構成。
 * magazine 中的每個字母只能使用一次。
 *
 * @author Monty.Tsai
 * @since 2025-04-26
 */
public class ID383RansomNote {

    /**
     * 解法：雜湊表 (陣列版)
     *
     * - 使用長度 26 的陣列統計 ransomNote 需要的字母數量。
     * - 遍歷 magazine，遇到需要的字母就扣除。
     * - 最後檢查是否所有字母都已滿足。
     *
     * Time Complexity: O(m + n)
     * Space Complexity: O(1)
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }

        // 1. 統計 ransomNote 中各字母出現次數
        int[] letterCount = new int[26]; // 按字母儲存字母使用量
        for(char c: ransomNote.toCharArray()) {
            int idx = c - 'a';
            letterCount[idx]++;
        }

        // 2. 在 magazine 中尋找並扣除所需字母
        int remainingLetters = ransomNote.length(); // 紀錄勒索信還有幾個字母要找
        int i = 0; // 遍歷 magazine 的 index
        // 結束條件: 勒索信的字母已找完 or 雜誌已找完所有字母
        while (remainingLetters > 0 && i < magazine.length()) {
            int idx = magazine.charAt(i++) - 'a';
            if (letterCount[idx] > 0) {
                remainingLetters--; // 勒索信用掉字母了
            }
            letterCount[idx]--;
        }

        // 若所有字母皆找到，則可以構成
        return remainingLetters == 0;
    }

}
