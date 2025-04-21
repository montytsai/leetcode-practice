//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int minSubArrayLen = Integer.MAX_VALUE;

        int left = 0;
        int sum = 0; // 先把 sum 存起來
        // right 標示終點位置
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            // 滑動窗口意思是如何動態調整區間，重點在於如何調整"起始位置(left)"
            while (sum >= target) {
                minSubArrayLen = Math.min(minSubArrayLen, right - left + 1);
                sum -= nums[left++];
            }
        }

        return minSubArrayLen == Integer.MAX_VALUE ? 0 : minSubArrayLen;
    }
}
//leetcode submit region end(Prohibit modification and deletion)