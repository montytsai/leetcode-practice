package io.github.monty.leetcode.backtracking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ID77CombinationsTest {

    private ID77Combinations solution;

    @BeforeEach
    void setUp() {
        solution = new ID77Combinations();
    }

    @Test
    void testCombine_n4_k2() {
        int n = 4;
        int k = 2;
        List<List<Integer>> result = solution.combine(n, k);

        assertEquals(6, result.size());
        assertTrue(result.contains(Arrays.asList(1, 2)));
        assertTrue(result.contains(Arrays.asList(1, 3)));
        assertTrue(result.contains(Arrays.asList(1, 4)));
        assertTrue(result.contains(Arrays.asList(2, 3)));
        assertTrue(result.contains(Arrays.asList(2, 4)));
        assertTrue(result.contains(Arrays.asList(3, 4)));
    }

    @Test
    void testCombine_n1_k1() {
        int n = 1;
        int k = 1;
        List<List<Integer>> result = solution.combine(n, k);

        assertEquals(1, result.size());
        assertEquals(Collections.singletonList(Collections.singletonList(1)), result);
    }

    @Test
    void testCombine_n5_k3() {
        int n = 5;
        int k = 3;
        List<List<Integer>> result = solution.combine(n, k);

        assertEquals(10, result.size());
        assertTrue(result.contains(Arrays.asList(1, 2, 3)));
        assertTrue(result.contains(Arrays.asList(3, 4, 5)));
    }

    @Test
    void testCombine_kEqualsZero() {
        int n = 5;
        int k = 0;
        List<List<Integer>> result = solution.combine(n, k);

        assertEquals(1, result.size());
        assertEquals(Collections.singletonList(Collections.emptyList()), result);
    }

    @Test
    void testCombine_kGreaterThanN() {
        int n = 3;
        int k = 4;
        List<List<Integer>> result = solution.combine(n, k);

        assertEquals(0, result.size());
    }

}