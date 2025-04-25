//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int search(int[] nums, int target) {
        // 定義 target 在左閉右閉的區間裡，[start, end]
        int startIndex = 0;
        int endIndex = nums.length - 1;

        while (startIndex <= endIndex) { // 這個"="，仍然在區間內
            int midIndex = (endIndex - startIndex) / 2 + startIndex;
            if (nums[midIndex] == target) {
                // 找到 target，回傳 index
                return midIndex;
            } else if (nums[midIndex] > target) {
                // target 在 [start, mid - 1] 區間
                endIndex = midIndex - 1;
            } else {
                // target 在 [mid + 1, end] 區間
                startIndex = midIndex + 1;
            }
        }

        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

