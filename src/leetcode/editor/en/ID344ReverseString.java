//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 反轉字串
     * 時間複雜度：O(n)
     * 空間複雜度：O(1)
     */
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            // reverse
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            // next character
            left++;
            right--;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)