//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean rotateString(String s, String goal) {
        // 如果長度不同，不可能相等
        if (s == null || goal == null || s.length() != goal.length()) {
            return false;
        }

        // 將 s 與自身連接，模擬所有旋轉情況，再檢查是否包含 goal
        return (s + s).contains(goal);
    }
}
//leetcode submit region end(Prohibit modification and deletion)