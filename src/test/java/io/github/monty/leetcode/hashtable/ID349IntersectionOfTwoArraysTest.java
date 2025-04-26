package io.github.monty.leetcode.hashtable;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class ID349IntersectionOfTwoArraysTest {

    private final ID349IntersectionOfTwoArrays solution = new ID349IntersectionOfTwoArrays();

    @Test
    void testIntersection_basic() {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        int[] expected = {2};
        assertArrayEqualsUnordered(expected, solution.intersection(nums1, nums2));
    }

    @Test
    void testIntersection_noIntersection() {
        int[] nums1 = {1, 2, 3};
        int[] nums2 = {4, 5, 6};
        int[] expected = {};
        assertArrayEqualsUnordered(expected, solution.intersection(nums1, nums2));
    }

    @Test
    void testIntersection_emptyInput() {
        int[] nums1 = {};
        int[] nums2 = {1, 2, 3};
        int[] expected = {};
        assertArrayEqualsUnordered(expected, solution.intersection(nums1, nums2));
    }

    @Test
    void testIntersection_singleElement() {
        int[] nums1 = {1};
        int[] nums2 = {1};
        int[] expected = {1};
        assertArrayEqualsUnordered(expected, solution.intersection(nums1, nums2));
    }

    @Test
    void testIntersection_duplicatesInInput() {
        int[] nums1 = {4, 9, 5};
        int[] nums2 = {9, 4, 9, 8, 4};
        int[] expected = {4, 9};
        assertArrayEqualsUnordered(expected, solution.intersection(nums1, nums2));
    }

    private void assertArrayEqualsUnordered(int[] expected, int[] actual) {
        List<Integer> expectedList = java.util.Arrays.stream(expected)
                .boxed()
                .sorted()
                .collect(Collectors.toList());

        List<Integer> actualList = java.util.Arrays.stream(actual)
                .boxed()
                .sorted()
                .collect(Collectors.toList());

        assertEquals(expectedList, actualList, "陣列內容不同");
    }

}