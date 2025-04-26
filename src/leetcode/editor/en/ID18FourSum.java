//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 給定一個包含 n 個整數的陣列 nums 和一個目標值 target，判斷 nums 中是否存在四個元素 a，b，c 和 d ，使得 a + b + c + d 的值與 target
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();

        // 排序 Array
        Arrays.sort(nums);

        // 1. 固定第一 & 第二下標，雙指針移動剩餘解
        for (int i = 0; i < nums.length - 3; i++) {
            // 第一固定數去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 2. 找出第二個固定的數
            for (int j = i + 1; j < nums.length - 2; j++) {
                // 第二固定數去重
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                // 3. 剩餘兩數雙指針求解
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    // 注意! 記得溢位問題
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // 左右指針去重
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }

                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)