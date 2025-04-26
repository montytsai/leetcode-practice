//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 編寫一個演算法來判斷一個數 n 是不是快樂數。
     *
     * 「快樂數」 定義為：
     *
     * 對於一個正整數，每一次將該數取代為它每個位置上的數字的平方和。
     * 然後重複這個過程直到這個數變為 1，也可能是 無限循環 但始終變不到 1。
     * 如果這個過程 結果為 1，那麼這個數就是快樂數。
     * 如果 n 是 快樂數 就返回 true ；不是，則返回 false 。
     */
    public boolean isHappy(int n) {
        // 建立結果集，儲存已出現過的數字，避免進入無限循環
        Set<Integer> resultSet = new HashSet<>();

        // 只要 n 不是 1 且沒出現過，就持續進行
        while (n != 1 && !resultSet.contains(n)) {
            resultSet.add(n);

            int sum = 0;
            // 計算 n 各位數字平方的總和
            while (n > 0) {
                int remainder = n % 10;
                sum += remainder * remainder;
                n = n / 10;
            }
            n = sum;
        }

        // n 變成 1，代表是快樂數；否則是陷入循環
        return n == 1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

