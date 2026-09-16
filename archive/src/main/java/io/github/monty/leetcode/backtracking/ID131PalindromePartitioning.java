package io.github.monty.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode #131:
 * <a href="https://leetcode.com/problems/palindrome-partitioning">
 * Palindrome Partitioning
 * </a>
 *
 * @author Monty.Tsai
 * @since 2025-06-06
 */
public class ID131PalindromePartitioning {

    // ASCII 樹圖說明（例："abba"）
    //
    // partition("abba")
    // ├── 已切割 [0,0] -> ["a | bba"]
    // │                        待切割 [1, 3] = "bba"
    // │                        ├── 已切割 [1, 1] ->  ["b | ba"]
    // │                        │                          待切割 [2, 3] = "ba"
    // │                        │                          ├── 已切割 [2, 2] -> ["b | a"]
    // │                        │                          │                         待切割 [3, 3]
    // │                        │                          │                         └── ["a |"] (回文 v, 已切割會存在 path: "a", "b", "b", "a")
    // │                        │                          └── 已切割 [2, 3] -> ["ba |"] (ba 非回文)
    // │                        ├── 已切割 [1, 2] ->  ["bb | a"]
    // │                        │                          待切割 [3, 3]
    // │                        │                          └── ["a |"] (回文 v, 已切割會存在 path: "a", "bb", "a")
    // │                        └── 已切割 [1, 3] ->  ["bba |"] (bba 非回文)
    // ├── 已切割 [0,1] -> ["ab | ba"] (ab 非回文)
    // ├── 已切割 [0,2] -> ["abb | a"] (abb 非回文)
    // └── 已切割 [0,3] -> ["abba |"] (回文v, path: "abba")

    /**
     * 解法：回溯法（Backtracking）
     * 思路：
     * - 將分解問題想像成組合問題，每次切割索引 [start, end] 的字符，再判斷剩下的字符  [end + 1, 字串尾] 是否為回文字串
     * - 一個字符分成 2 部分： (a|ab)
     *      1. 已切割：已處理的字串，加入 path 紀錄 (a)
     *      2. 尚未切割，待處理：加入下一層樹進行處理 (ab)
     * 實作：
     * - 對字串進行切割，從索引 start 開始，嘗試所有可能的 end 切割點。
     * - 若 s[start..end] 為回文子字串，則將該子字串加入當前 path，遞迴處理剩下字串。
     * - 終止條件為 start == s.length()，代表已切到底部，將 path 複製加入結果。
     * - 遞迴結束後回溯，移除最後一個子字串。
     * 時間複雜度：O(n * 2^n)
     * - 每個字元位置可切與不切，共 2^(n-1) 種切法，
     * - 每次切割需要 O(n) 時間判斷是否為回文（最壞情況）。
     * 空間複雜度：O(n)，遞迴堆疊最深為 n 層，path 最多長度為 n。
     */
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        if (s == null || s.isEmpty()) return result;

        backtracking(s, 0, new ArrayList<>(), result);

        return result;
    }

    /**
     * 回溯函數: 切割字串並探索所有回文切割組合
     *
     * @param s      字串
     * @param start  切割的起始位置
     * @param path   當前樹枝 = 從 root 到當前節點（已切割字串）
     * @param result 符合結果的所有樹枝
     */
    // 遞歸三部曲 - 1. 遞歸的回傳值 & 參數
    private void backtracking(String s, int start,
                              List<String> path, List<List<String>> result) {
        // 遞歸三部曲 - 2. 終止條件
        if (start >= s.length()) { // 到達葉子節點
            result.add(new ArrayList<>(path)); // 在上層已把非回文的字串給篩選掉，此處只需要無腦加入結果集
            return;
        }

        // 遞歸三部曲 - 3. 繼續遞歸（進行橫向遍歷及縱向遞歸）
        for (int end = start; end < s.length(); end++) {
            // 回溯三部曲 - 1. 處理當前節點: 切割字串 & 剪枝(判斷是否為回文字串)
            // 若 s[start..end] 為回文，則切割並遞迴
            if (isPalindrome(s, start, end)) {
                path.add(s.substring(start, end + 1)); // substring 是左閉右開，我的 end 是右閉
            } else {
                continue;
            }

            // 回溯三部曲 - 2. 遞歸回溯函數（處理樹的下一層）
            backtracking(s, end + 1, path, result);

            // 回溯三部曲 - 3. 回溯
            path.remove(path.size() - 1);
        }
    }

    /**
     * 判斷字串中 [start, end] 是否為回文字串 (雙指針法)
     *
     * @param s     字串
     * @param start 起始索引
     * @param end   結束索引
     * @return 是否為回文
     */
    private boolean isPalindrome(String s, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) return false;
        }
        return true;
    }

}
