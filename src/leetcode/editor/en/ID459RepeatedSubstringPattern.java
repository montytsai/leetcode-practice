//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 給定一個非空的字串，判斷它是否可以由它的一個子串重複多次構成。給定的字串只含有小寫英文字母，並且長度不超過10000。
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

}
//leetcode submit region end(Prohibit modification and deletion)