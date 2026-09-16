package io.github.monty.leetcode.stackqueue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ID239SlidingWindowMaximumTest {

    private final ID239SlidingWindowMaximum solution = new ID239SlidingWindowMaximum();

    @Test
    void testExample1() {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] expected = {3, 3, 5, 5, 6, 7};
        assertArrayEquals(expected, solution.maxSlidingWindow(nums, k));
    }

    @Test
    void testExample2() {
        int[] nums = {1, 3, -1, 1, -2, 3, 6, 5};
        int k = 3;
        int[] expected = {3, 3, 1, 3, 6, 6};
        assertArrayEquals(expected, solution.maxSlidingWindow(nums, k));
    }

    @Test
    @DisplayName("Window Size 1: [1], k=1 => [1]")
    void testSingleElement() {
        int[] nums = {1};
        int k = 1;
        int[] expected = {1};
        assertArrayEquals(expected, solution.maxSlidingWindow(nums, k));
    }

    @Test
    @DisplayName("All Elements Same: [2,2,2,2], k=2 => [2,2,2]")
    void testAllSame() {
        int[] nums = {2, 2, 2, 2};
        int k = 2;
        int[] expected = {2, 2, 2};
        assertArrayEquals(expected, solution.maxSlidingWindow(nums, k));
    }

    @Test
    @DisplayName("Decreasing Sequence: [5,4,3,2,1], k=3 => [5,4,3]")
    void testDecreasing() {
        int[] nums = {5, 4, 3, 2, 1};
        int k = 3;
        int[] expected = {5, 4, 3};
        assertArrayEquals(expected, solution.maxSlidingWindow(nums, k));
    }

    @Test
    @DisplayName("Increasing Sequence: [1,2,3,4,5], k=2 => [2,3,4,5]")
    void testIncreasing() {
        int[] nums = {1, 2, 3, 4, 5};
        int k = 2;
        int[] expected = {2, 3, 4, 5};
        assertArrayEquals(expected, solution.maxSlidingWindow(nums, k));
    }

    @Test
    @DisplayName("Window Size Equals Array Length: [1,2,3], k=3 => [3]")
    void testWindowEqualsLength() {
        int[] nums = {1, 2, 3};
        int k = 3;
        int[] expected = {3};
        assertArrayEquals(expected, solution.maxSlidingWindow(nums, k));
    }

}
