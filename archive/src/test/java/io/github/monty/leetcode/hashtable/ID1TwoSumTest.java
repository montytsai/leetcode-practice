package io.github.monty.leetcode.hashtable;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

/**
 * LeetCode #1: Two Sum 測試
 * 測試目標：驗證輸入不同情境下，能正確回傳兩數之和為 target 的索引
 * 時間複雜度：取決於實作，通常 O(n)
 * 空間複雜度：取決於實作，通常 O(n)
 */
class ID001TwoSumTest {

    private final ID1TwoSum solution = new ID1TwoSum();

    @Test
    void testExample1() {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] expected = {0, 1};

        int[] result = solution.twoSum(nums, target);
        Arrays.sort(expected);
        Arrays.sort(result);

        assertArrayEquals(expected, result);
    }

    @Test
    void testExample2() {
        int[] nums = {3, 2, 4};
        int target = 6;
        int[] expected = {1, 2};
        assertArrayEquals(expected, solution.twoSum(nums, target));
    }

    @Test
    void testExample3() {
        int[] nums = {3, 3};
        int target = 6;
        int[] expected = {0, 1};
        assertArrayEquals(expected, solution.twoSum(nums, target));
    }

    @Test
    void testNoSolution() {
        int[] nums = {1, 2, 3};
        int target = 7;

        // 基本上不會執行到，因為題目保證有解
        int[] expected = {};
        assertArrayEquals(expected, solution.twoSum(nums, target));
    }

}