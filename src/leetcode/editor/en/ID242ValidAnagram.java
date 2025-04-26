//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 給兩個字串s, t, 回傳s跟t是否為相同字母異序詞(anagram)
     *
     * 時間複雜度：$O(n)$（遍歷 s 和 t 各一次）
     * 空間複雜度：$O(1)$（因為只開了固定大小 26 的陣列）
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        // 1. 建立長度為 26 的陣列，對應字母 a-z 的出現次數（ASCII 97-122)
        int[] alphabet = new int[26];

        // 2. 把 s 字串拆解，累加字母出現次數
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            alphabet[idx]++;
        }

        // 3. 把 t 字串拆解，扣除字母出現次數；若出現負值，表示 t 多出某字母
        for (int i = 0; i < t.length(); i++) {
            int idx = t.charAt(i) - 'a';
            if (--alphabet[idx] < 0) {
                return false;
            }
        }

        // 4. 檢查所有字母次數是否歸零，若有未歸零則表示兩字串使用字母次數不同，返回 false
        for (int a : alphabet) {
            if (a != 0) {
                return false;
            }
        }

        // 所有字母使用次數都相同，是 anagram
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

