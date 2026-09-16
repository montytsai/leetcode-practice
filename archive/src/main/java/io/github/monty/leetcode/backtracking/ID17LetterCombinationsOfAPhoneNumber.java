package io.github.monty.leetcode.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode #17:
 * <a href="https://leetcode.com/problems/letter-combinations-of-a-phone-number">
 * Letter Combinations of a Phone Number
 * </a>
 *
 * @author Monty.Tsai
 * @since 2025-05-27
 */
public class ID17LetterCombinationsOfAPhoneNumber {

    /**
     * 電話號碼對應字母，例如: '2' -> 查表得到 'a', 'b', 'c'
     * 使用靜態初始化減少每次呼叫都建構 Map 的成本
     */
    private static final Map<Character, char[]> LETTERS_BY_DIGIT = new HashMap<>();

    static {
        LETTERS_BY_DIGIT.put('2', new char[]{'a', 'b', 'c'});
        LETTERS_BY_DIGIT.put('3', new char[]{'d', 'e', 'f'});
        LETTERS_BY_DIGIT.put('4', new char[]{'g', 'h', 'i'});
        LETTERS_BY_DIGIT.put('5', new char[]{'j', 'k', 'l'});
        LETTERS_BY_DIGIT.put('6', new char[]{'m', 'n', 'o'});
        LETTERS_BY_DIGIT.put('7', new char[]{'p', 'q', 'r', 's'});
        LETTERS_BY_DIGIT.put('8', new char[]{'t', 'u', 'v'});
        LETTERS_BY_DIGIT.put('9', new char[]{'w', 'x', 'y', 'z'});
    }

    /**
     * 解法一：回溯法（Backtracking）
     * 思路：
     * - 將每個數字視為一層，對應一組英文字母。
     * - 遞迴每層對應的字母，依序填入 path（StringBuilder）。
     * - 當 path 長度等於 digits 長度時，即為一組合法結果。
     * - 遞迴結束後回退（回溯），繼續嘗試其他分支。
     * 複雜度分析：
     * - 時間：O(3^n * 4^m)，n 為出現 3 個字母的數字個數，m 為出現 4 個字母的數字個數。
     * - 空間：O(n)，為遞迴深度與 path 儲存長度。
     */
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.isEmpty()) return result;
        backtracking(digits, 0, new StringBuilder(), result);
        return result;
    }

    /**
     * 回溯函數
     *
     * @param digits 使用者輸入字串，例如: "23"
     * @param index  字串當前處理的位數，例如: 0 -> "23".charAt(0) = '2'
     * @param path   當前遞迴的組合內容
     * @param result 儲存所有合法組合
     */
    private void backtracking(String digits, int index,
                              StringBuilder path, List<String> result) {
        // 終止條件：已處理到輸入字串末尾
        if (index >= digits.length()) {
            result.add(path.toString());
            return;
        }

        // 取得當前位數對應的所有字母，例如: index 0，為字串中的 '2'，可查表得到 ['a', 'b', 'c']
        char[] letters = LETTERS_BY_DIGIT.get(digits.charAt(index));

        // 遍歷當層所有可能的字母選項
        for (char letter : letters) {
            path.append(letter);
            backtracking(digits, index + 1, path, result);
            path.deleteCharAt(path.length() - 1);
        }
    }

}