package io.github.monty.leetcode.hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ID454FourSumIITest {

    private final ID454FourSumII solution = new ID454FourSumII();

    @Test
    void testExampleCase1() {
        int[] nums1 = {1, 2};
        int[] nums2 = {-2, -1};
        int[] nums3 = {-1, 2};
        int[] nums4 = {0, 2};
        assertEquals(2, solution.fourSumCount(nums1, nums2, nums3, nums4));
    }

    @Test
    void testCountGreaterThanOne() {
        int[] nums1 = {0, 1};
        int[] nums2 = {0, 1};
        int[] nums3 = {0, -1};
        int[] nums4 = {0, -1};
        assertEquals(6, solution.fourSumCount(nums1, nums2, nums3, nums4));
    }

    @Test
    void testAllZero() {
        int[] nums1 = {0};
        int[] nums2 = {0};
        int[] nums3 = {0};
        int[] nums4 = {0};
        assertEquals(1, solution.fourSumCount(nums1, nums2, nums3, nums4));
    }

    @Test
    void testEmptyArray() {
        int[] nums1 = {};
        int[] nums2 = {};
        int[] nums3 = {};
        int[] nums4 = {};
        assertEquals(0, solution.fourSumCount(nums1, nums2, nums3, nums4));
    }

}
