package io.github.monty.leetcode.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TwoSumTest {

    @Test
    void testTwoSum() {
        TwoSum solver = new TwoSum();

        int[] result = solver.twoSum(new int[]{2, 7, 11, 15}, 9);
        assertArrayEquals(new int[]{0, 1}, result);

        int[] result2 = solver.twoSum(new int[]{3, 2, 4}, 6);
        assertArrayEquals(new int[]{1, 2}, result2);

        int[] result3 = solver.twoSum(new int[]{3, 3}, 6);
        assertArrayEquals(new int[]{0, 1}, result3);
    }

}