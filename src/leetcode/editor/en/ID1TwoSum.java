//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // 儲存「希望湊成 target 的差值」以及「差值的下標」
        Map<Integer, Integer> idxByComplement = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            // 檢查目前元素是否是某個先前數字的補數
            if (idxByComplement.containsKey(nums[i])) {
                return new int[]{idxByComplement.get(nums[i]), i};
            }
            int complement = target - nums[i];
            idxByComplement.put(complement, i);
        }

        return new int[0]; // 題目保證有解，實際上不會走到這裡
    }
}
//leetcode submit region end(Prohibit modification and deletion)

