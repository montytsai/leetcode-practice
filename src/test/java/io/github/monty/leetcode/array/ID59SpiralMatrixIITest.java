package io.github.monty.leetcode.array;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class ID59SpiralMatrixIITest {

    private final ID59SpiralMatrixII solution = new ID59SpiralMatrixII();

    @Test
    public void testNEquals1() {
        int n = 1;
        int[][] expected = {
                {1}
        };
        assertArrayEquals(expected, solution.generateMatrix(n));
    }

    @Test
    public void testNEquals2() {
        int n = 2;
        int[][] expected = {
                {1, 2},
                {4, 3}
        };
        assertArrayEquals(expected, solution.generateMatrix(n));
    }

    @Test
    public void testNEquals3() {
        int n = 3;
        int[][] expected = {
                {1, 2, 3},
                {8, 9, 4},
                {7, 6, 5}
        };
        assertArrayEquals(expected, solution.generateMatrix(n));
    }

    @Test
    public void testNEquals4() {
        int n = 4;
        int[][] expected = {
                { 1,  2,  3,  4},
                {12, 13, 14,  5},
                {11, 16, 15,  6},
                {10,  9,  8,  7}
        };
        assertArrayEquals(expected, solution.generateMatrix(n));
    }

    @Test
    public void testNEquals5() {
        int n = 5;
        int[][] expected = {
                { 1,  2,  3,  4,  5},
                {16, 17, 18, 19,  6},
                {15, 24, 25, 20,  7},
                {14, 23, 22, 21,  8},
                {13, 12, 11, 10,  9}
        };
        assertArrayEquals(expected, solution.generateMatrix(n));
    }

    @Test
    public void testNEquals6() {
        int n = 6;
        int[][] expected = {
                {  1,  2,  3,  4,  5,  6 },
                { 20, 21, 22, 23, 24,  7 },
                { 19, 32, 33, 34, 25,  8 },
                { 18, 31, 36, 35, 26,  9 },
                { 17, 30, 29, 28, 27, 10 },
                { 16, 15, 14, 13, 12, 11 }
        };
        assertArrayEquals(expected, solution.generateMatrix(n));
    }

    @Test
    public void testNEquals7() {
        int n = 7;
        int[][] expected = {
                {  1,  2,  3,  4,  5,  6,  7 },
                { 24, 25, 26, 27, 28, 29,  8 },
                { 23, 40, 41, 42, 43, 30,  9 },
                { 22, 39, 48, 49, 44, 31, 10 },
                { 21, 38, 47, 46, 45, 32, 11 },
                { 20, 37, 36, 35, 34, 33, 12 },
                { 19, 18, 17, 16, 15, 14, 13 }
        };
        assertArrayEquals(expected, solution.generateMatrix(n));
    }

    @Test
    public void testNEquals8() {
        int n = 8;
        int[][] expected = {
                {  1,  2,  3,  4,  5,  6,  7,  8 },
                { 28, 29, 30, 31, 32, 33, 34,  9 },
                { 27, 48, 49, 50, 51, 52, 35, 10 },
                { 26, 47, 60, 61, 62, 53, 36, 11 },
                { 25, 46, 59, 64, 63, 54, 37, 12 },
                { 24, 45, 58, 57, 56, 55, 38, 13 },
                { 23, 44, 43, 42, 41, 40, 39, 14 },
                { 22, 21, 20, 19, 18, 17, 16, 15 }
        };
        assertArrayEquals(expected, solution.generateMatrix(n));
    }

}