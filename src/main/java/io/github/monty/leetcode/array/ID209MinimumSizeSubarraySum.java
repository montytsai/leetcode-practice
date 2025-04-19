package io.github.monty.leetcode.array;

/**
 * LeetCode #209:
 * <a href="https://leetcode.com/problems/minimum-size-subarray-sum">Minimum Size Subarray Sum</a>
 * <p>
 * 給一個正整數陣列 nums 和一個目標正整數 target
 * 找出陣列中最小相加 大於等於 target 的(連續)子陣列
 * 如果相加無法大於等於 target，回傳 0
 * 時間複雜度要求: O(n log(n)) > O(n)
 *
 * @author Monty.Tsai
 * @since 2025-04-19 22:00:32
 */
public class ID209MinimumSizeSubarraySum {

    /**
     * 滑動窗口解法: by chatGPT
     * 時間複雜度: O(n)
     */
    public int minSubArrayLen(int target, int[] nums) {
        int minSubArrayLen = Integer.MAX_VALUE;
        int left = 0;
        int sum = 0;

        for (int right = 0; right < nums.length; right++) {
//            System.out.println("=======================================");
            sum += nums[right];
//            System.out.printf("left: %s, right: %s, sum: %s\n", left, right, sum);
//            System.out.println("===");

            while (sum >= target) {
                minSubArrayLen = Math.min(minSubArrayLen, right - left + 1);
                sum -= nums[left];
                left++;
//                System.out.printf("left: %s, right: %s, sum: %s\n", left, right, sum);
            }
//            System.out.printf(" => minSubArrayLen: %s\n", minSubArrayLen);
        }

        return minSubArrayLen == Integer.MAX_VALUE ? 0 : minSubArrayLen;
    }


    /**
     * 暴力解
     * !! sum 的 for迴圈是 O(n*n); => 下次改正: sum總數從上一次拿就好不用重新算
     * 時間複雜度要求: O(n * n)
     */
    public int minSubArrayLenZ(int target, int[] nums) {
        int left = 0;
        int right = 0;
        int minSubArrayLen = Integer.MAX_VALUE;

        while (minSubArrayLen != 1 && right < nums.length) {
            System.out.println("=======================================");
            int sum = nums[left];
            for (int k = left + 1; k <= right; k++) {
                sum += nums[k];
            }
            System.out.printf("left: %s, right: %s, sum: %s", left, right, sum);

            if (sum < target) {
                right++;
            } else if (sum > target) {
                minSubArrayLen = Math.min(minSubArrayLen, right - left + 1);
                left++;
            } else { // sum == target
                minSubArrayLen = Math.min(minSubArrayLen, right - left + 1);
                right++;
                left = right - minSubArrayLen + 1;
            }
            System.out.printf(" => minSubArrayLen: %s\n", minSubArrayLen);
        }

        return minSubArrayLen;
    }

}