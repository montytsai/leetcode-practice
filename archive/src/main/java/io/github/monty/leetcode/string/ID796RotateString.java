package io.github.monty.leetcode.string;

/**
 * LeetCode #796:
 * <a href="https://leetcode.com/problems/rotate-string">Rotate String</a>
 * 題目說明：
 * 　　給定兩個字串 s 和 goal，若 s 經過若干次旋轉操作（將最左邊的字元移到最右邊）後能變為 goal，則回傳 true。
 * 範例：
 * 　　s = "abcde"，一次旋轉後變 "bcdea"，兩次變 "cdeab"，以此類推。
 *
 * @author Monty.Tsai
 * @since 2025-04-30 16:56:28
 */
public class ID796RotateString {

    /**
     * 解法: 使用函數 contains()
     * 重點: (s + s) 與自身連接
     * 時間複雜度：O(n)
     * 空間複雜度：O(n)，取決於字串連接實作
     */
    public boolean rotateString(String s, String goal) {
        // 如果長度不同，不可能相等
        if (s == null || goal == null || s.length() != goal.length()) {
            return false;
        }

        // 將 s 與自身連接，模擬所有旋轉情況，再檢查是否包含 goal
        return (s + s).contains(goal);
    }

    /**
     * 方法 1: 暴力解(雙迴圈比較字串)
     * 重點: 使用 (rotationStart + gIdx) % len; 模擬旋轉
     * 時間複雜度：O(n^2)，最壞情況需對每個起點模擬比較
     * 空間複雜度：O(1)，僅使用索引變數
     */
    public boolean rotateStringZ(String s, String goal) {
        if (s == null || goal == null || s.length() != goal.length()) {
            return false;
        }
        int len = s.length();
        if (len == 0) { // // 空字串視為旋轉成功
            return true;
        }

        // 對每個可能的旋轉起始位置做比對
        for (int rotationStart = 0; rotationStart < len; rotationStart++) {

            // XXX 雖然這是嘗試跳過不必要的匹配位置，但因為已在 for 迴圈裡循環 rotationStart，再額外進行 while 跳過，會導致：
            //     rotationStart++ 在兩個地方都發生（for + while），容易出錯或漏判。
            //     不如直接保留單層 for，對所有旋轉位置直接比對是否匹配。
            /*
            // 找到與 goal 首字符合的 index
            while (rotationStart < len && s.charAt(rotationStart) != goal.charAt(0)) {
                rotationStart++;
            }
            if (rotationStart >= len) {
                break;
            }
            */

            boolean match = true;

            // 用模擬索引方式比較旋轉後的 s 和 goal 是否完全一致
            for (int gIdx = 0; gIdx < len; gIdx++) {
                int sIdx = (rotationStart + gIdx) % len; // 模擬旋轉
                if (s.charAt(sIdx) != goal.charAt(gIdx)) {
                    match = false;
                    break; // 有字不符合，s找下一個與goal相符的首字
                }
            }

            // 旋轉後的 s 與 goal 完全相符
            if (match) {
                return true;
            }
        }

        // 所有旋轉皆不符合
        return false;
    }

}