package io.github.monty.leetcode.array;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ID977SquaresOfASortedArrayTest {

    private final ID977SquaresOfASortedArray solution = new ID977SquaresOfASortedArray();

    @Test
    public void testAllPositive() {
        int[] input = {1, 2, 3, 4, 5};
        int[] expected = {1, 4, 9, 16, 25};
        assertArrayEquals(expected, solution.sortedSquares(input));
    }

    @Test
    public void testAllNegative() {
        int[] input = {-5, -4, -3, -2, -1};
        int[] expected = {1, 4, 9, 16, 25};
        assertArrayEquals(expected, solution.sortedSquares(input));
    }

    @Test
    public void testMixedSigns() {
        int[] input = {-7, -3, 2, 3, 11};
        int[] expected = {4, 9, 9, 49, 121};
        assertArrayEquals(expected, solution.sortedSquares(input));
    }

    @Test
    public void testContainsZero() {
        int[] input = {-2, -1, 0, 3, 4};
        int[] expected = {0, 1, 4, 9, 16};
        assertArrayEquals(expected, solution.sortedSquares(input));
    }

    @Test
    public void testSingleElement() {
        int[] input = {3};
        int[] expected = {9};
        assertArrayEquals(expected, solution.sortedSquares(input));
    }

    @Test
    public void testEmptyArray() {
        int[] input = {};
        int[] expected = {};
        assertArrayEquals(expected, solution.sortedSquares(input));
    }

    @Test
    public void testAllZero() {
        int[] input = {0, 0, 0};
        int[] expected = {0, 0, 0};
        assertArrayEquals(expected, solution.sortedSquares(input));
    }

}