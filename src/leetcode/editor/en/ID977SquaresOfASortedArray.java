//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] sortedSquares(int[] nums) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;

        int[] sortedSquares = new int[len];
        for (int idx = len - 1; idx >= 0; idx--) {
            int leftVal = nums[left] * nums[left];
            int rightVal = nums[right] * nums[right];
            if (leftVal > rightVal) {
                sortedSquares[idx] = leftVal;
                left++;
            } else {
                sortedSquares[idx] = rightVal;
                right--;
            }
        }
        return sortedSquares;
    }
}
//leetcode submit region end(Prohibit modification and deletion)