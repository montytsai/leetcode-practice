//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int minSubArrayLen = Integer.MAX_VALUE;
        int left = 0;
        int sum = 0;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            while (sum >= target) {
                minSubArrayLen = Math.min(minSubArrayLen, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }

        return minSubArrayLen == Integer.MAX_VALUE ? 0 : minSubArrayLen;
    }
}
//leetcode submit region end(Prohibit modification and deletion)