//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 勒索信
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
//leetcode submit region end(Prohibit modification and deletion)