//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
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
}
//leetcode submit region end(Prohibit modification and deletion)