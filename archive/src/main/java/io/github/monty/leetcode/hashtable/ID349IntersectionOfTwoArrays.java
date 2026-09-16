package io.github.monty.leetcode.hashtable;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode #349:
 * <a href="https://leetcode.com/problems/intersection-of-two-arrays">Intersection of Two Arrays</a>
 * 給定兩個陣列 nums1 和 nums2，返回它們的交集。每個元素只能出現一次，且順序不限。
 *
 * @author Monty.Tsai
 * @since 2025-04-26 18:30:49
 */
public class ID349IntersectionOfTwoArrays {

    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) {
            return new int[]{};
        }

        // 1. 使用 set 儲存 nums1 的所有元素，去除重複並提供快速查找
        Set<Integer> set1 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }

        // 2. 遍歷 nums2，把出現在 set1 中的元素加入結果集合（去除重複）
        Set<Integer> intersection = new HashSet<>();
        for (int num : nums2) {
            if (set1.contains(num)) {
                intersection.add(num);
            }
        }

        // 3. 將結果集合轉成陣列並返回
        int[] result = new int[intersection.size()];
        int idx = 0;
        for (int num : intersection) {
            result[idx++] = num;
        }
        return result;
    }

}