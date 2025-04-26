//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 給你一個包含 n 個整數的陣列 nums，判斷 nums 中是否存在三個元素 a，b，c ，使得 a + b + c = 0 ？
     * 請你找出所有滿足條件且不重複的三元組。
     * 注意： 答案中不可以包含重複的三元組。
     *
     * topic: array, two pointers, sorting
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        // 1. 將 nums 從小排到大 (方便去重)
        Arrays.sort(nums);

        // 2. 雙指針思維 (記得要去重)
        // 固定第一個指針，剩餘分成 left 和 right 指針，兩邊互相靠近，算出結果集
        for (int i = 0; i < nums.length; i++) {
            // 排序後的陣列，如果第一個數就是正數，後面一定加不到 0
            if (nums[i] > 0) {
                break;
            }
            // 固定指針去重（避免重複固定同個數）
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 雙指針
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int threeSum = nums[i] + nums[left] + nums[right];

                if (threeSum == 0) { // 找到解
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // 左右指針去重
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    // 移動指針繼續找下一組
                    left++;
                    right--;
                } else if (threeSum < 0) {
                    // 總和太小，左指針右移
                    left++;
                } else {
                    // threeSum > 0 總和太大，右指針左移
                    right--;
                }
            }
        }

        // 3. 返回結果集
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

