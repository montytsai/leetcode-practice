package io.github.monty.leetcode.array;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ID209MinimumSizeSubarraySumTest {

    private final ID209MinimumSizeSubarraySum solution = new ID209MinimumSizeSubarraySum();

    @Test
    public void testExample1() {
        int target = 7;
        int[] nums = {2, 3, 1, 2, 4, 3};
        int expected = 2; // [4,3]
        assertEquals(expected, solution.minSubArrayLen(target, nums));
    }

    @Test
    public void testSingleElementEqualToTarget() {
        int target = 4;
        int[] nums = {1, 4, 5};
        int expected = 1;
        assertEquals(expected, solution.minSubArrayLen(target, nums));
    }

    @Test
    public void testNoValidSubarray() {
        int target = 15;
        int[] nums = {1, 2, 3, 4, 5};
        int expected = 5; // 1+2+3+4+5 = 15
        assertEquals(expected, solution.minSubArrayLen(target, nums));
    }

    @Test
    public void testTargetGreaterThanTotalSum() {
        int target = 100;
        int[] nums = {1, 2, 3, 4, 5};
        int expected = 0;
        assertEquals(expected, solution.minSubArrayLen(target, nums));
    }

    @Test
    public void testMultipleMinimums() {
        int target = 11;
        int[] nums = {1, 2, 3, 4, 5};
        int expected = 3; // [3,4,5]
        assertEquals(expected, solution.minSubArrayLen(target, nums));
    }

    @Test
    public void testAllElementsAreLarge() {
        int target = 3;
        int[] nums = {4, 5, 6};
        int expected = 1; // [4]
        assertEquals(expected, solution.minSubArrayLen(target, nums));
    }

    @Test
    public void testEmptyArray() {
        int target = 5;
        int[] nums = {};
        int expected = 0;
        assertEquals(expected, solution.minSubArrayLen(target, nums));
    }

    @Test
    public void testOnlyOnePossibleWindow() {
        int target = 15;
        int[] nums = {1, 2, 3, 4, 5, 6};
        int expected = 3; // [5,6,4] or [4,5,6] depending on order
        assertEquals(expected, solution.minSubArrayLen(target, nums));
    }

}