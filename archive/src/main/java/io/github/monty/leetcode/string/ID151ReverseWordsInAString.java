package io.github.monty.leetcode.string;

/**
 * LeetCode #151:
 * <a href="https://leetcode.com/problems/reverse-words-in-a-string">Reverse Words in a String</a>
 * 給你一個字串 s ，請你反轉字串中 單詞 的順序。
 * 單詞 是由非空格字元組成的字串。s 中使用至少一個空格將字串中的 單詞 分隔開。
 * 返回 單詞 順序顛倒且 單詞 之間用單個空格連接的結果字串。
 * 注意：輸入字串 s中可能會存在前導空格、尾隨空格或者單詞間的多個空格。返回的結果字串中，單詞間應當僅用單個空格分隔，且不包含任何額外的空格。
 * 題目要求空間複雜度O(1)
 *
 * @author Monty.Tsai
 * @since 2025-04-28 18:24:11
 */
public class ID151ReverseWordsInAString {

    /**
     * 解法: 使用 char[] 原地反轉陣列
     * - 題目要求空間複雜度 O(1) => 在 char[] 原地修改可達成O (1)
     * - 時間複雜度: O(n)，需遍歷三次：壓縮、整體反轉、單字反轉。
     * - 空間複雜度: O(1)，在原陣列內操作。
     */
    public String reverseWords(String s) {
        char[] arr = s.toCharArray();
        int sLen = arr.length;

        // 1. 去掉所有空格
        int readIdx = 0; // 讀取 index
        int writeIdx = 0; // 寫入(要移動到)的 index

        while (readIdx < sLen) {
            // 略過前置空格
            while (readIdx < sLen && arr[readIdx] == ' ') {
                readIdx++;
            }
            
            // 將單字逐字拷貝到新位置
            while (readIdx < sLen && arr[readIdx] != ' ') {
                arr[writeIdx++] = arr[readIdx++];
            }
            
            // 略過單字後空格，並補一個空格（若後面還有單字）
            while (readIdx < sLen && arr[readIdx] == ' ') {
                readIdx++;
            }
            if (readIdx < sLen) {
                arr[writeIdx++] = ' ';
            }
        }

        // 2. 反轉整個有效字串
        this.reverse(arr, 0, writeIdx - 1);

        // 3. 反轉每個單字
        int start = 0;
        while (start < writeIdx) {
            int end = start + 1;
            while (end < writeIdx && arr[end] != ' ') {
                end++;
            }
            this.reverse(arr, start, end - 1);
            start = end + 1;
        }

        return new String(arr, 0, writeIdx);
    }

    private void reverse(char[] arr, int start, int end) {
        while (start < end) {
            char temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * 解法: 使用函式庫 split()
     * - split(" ") 的效率問題：
     *      使用 split(" ") 會將字串按照每個空格來切割，但如果字串中有多個連續的空格，這樣會產生多個空字串（""）。
     *      雖然後續的邏輯處理了這些空字串，但 split(" ") 會增加不必要的計算開銷，特別是在處理長字串時。
     *      => 改成 s.trim().split("\\s+")
     * - 空間複雜度: O(n)
     * - 時間複雜度: O(n)
     */
    public String reverseWordsZ(String s) {
//        String[] arr = s.split(" ");
        // trim() 前後空格；使用正則表達式來分割，避免空格重複問題
        String[] arr = s.trim().split("\\s+");

        StringBuilder sb = new StringBuilder();
        for (int i = arr.length - 1; i >= 0; i--) {
            if (!"".equals(arr[i])) {
                if (sb.length() > 0) {
                    sb.append(" ");
                }
                sb.append(arr[i]);
            }
        }

        return sb.toString();
    }

}
