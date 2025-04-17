package io.github.monty.leetcode.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTest {

    @Test
    void testExample() {
        BinarySearch sol = new BinarySearch();
        assertArrayEquals(new int[]{0, 1}, sol.solution(new int[]{2, 7, 11, 15}, 9));
    }

}
