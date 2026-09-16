package io.github.monty.leetcode.array;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ID704BinarySearchTest {

    ID704BinarySearch bs = new ID704BinarySearch();

    @Test
    public void testTargetInMiddle() {
        int[] nums = {1, 3, 5, 7, 9};
        int target = 5;
        assertEquals(2, bs.search(nums, target));
    }

    @Test
    public void testTargetAtStart() {
        int[] nums = {1, 3, 5, 7, 9};
        int target = 1;
        assertEquals(0, bs.search(nums, target));
    }

    @Test
    public void testTargetAtEnd() {
        int[] nums = {1, 3, 5, 7, 9};
        int target = 9;
        assertEquals(4, bs.search(nums, target));
    }

    @Test
    public void testTargetNotFound() {
        int[] nums = {1, 3, 5, 7, 9};
        int target = 4;
        assertEquals(-1, bs.search(nums, target));
    }

    @Test
    public void testEmptyArray() {
        int[] nums = {};
        int target = 1;
        assertEquals(-1, bs.search(nums, target));
    }

    @Test
    public void testSingleElementFound() {
        int[] nums = {7};
        int target = 7;
        assertEquals(0, bs.search(nums, target));
    }

    @Test
    public void testSingleElementNotFound() {
        int[] nums = {7};
        int target = 3;
        assertEquals(-1, bs.search(nums, target));
    }

}