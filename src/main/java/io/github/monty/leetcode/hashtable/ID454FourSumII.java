package io.github.monty.leetcode.hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode #454:
 * <a href="https://leetcode.com/problems/4sum-ii">4Sum II</a>
 *
 * 給定四個整數陣列 nums1、nums2、nums3、nums4，計算有多少組 (i, j, k, l) 使得：
 * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0。
 *
 * @author Monty.Tsai
 * @since 2025-04-26
 */
public class ID454FourSumII {

    /**
     * 解法：雜湊表法
     * - 先統計所有 nums1[i] + nums2[j] 的和及出現次數。
     * - 再遍歷 nums3[k] + nums4[l]，找 -(nums3[k] + nums4[l]) 出現次數累加。
     * Time Complexity: O(n^2)
     * Space Complexity: O(n^2)
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        // 統計 nums1 + nums2 所有和及次數
        Map<Integer, Integer> countByNum12Sum = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int num12Sum = nums1[i] + nums2[j];
                countByNum12Sum.put(num12Sum, countByNum12Sum.getOrDefault(num12Sum, 0) + 1);
            }
        }

        // 計算能與 nums3 + nums4 湊成 0 的總數
        int quadrupletsCount = 0;
        for (int i = 0; i < nums3.length; i++) {
            for (int j = 0; j < nums4.length; j++) {
                int num34Sum = nums3[i] + nums4[j];
                quadrupletsCount += countByNum12Sum.getOrDefault(-num34Sum, 0);
            }
        }
        return quadrupletsCount;
    }

}