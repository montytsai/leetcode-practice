package io.github.monty.leetcode.backtracking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ID216CombinationSumIIITest {

    private ID216CombinationSumIII solution;

    @BeforeEach
    void setUp() {
        solution = new ID216CombinationSumIII();
    }

    @Test
    void testExample1() {
        int k = 3, n = 7;
        List<List<Integer>> expected = Collections.singletonList(Arrays.asList(1, 2, 4));
        List<List<Integer>> actual = solution.combinationSum3(k, n);
        assertEquals(expected, actual);
    }

    @Test
    void testExample2() {
        int k = 3, n = 9;
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(1, 2, 6),
                Arrays.asList(1, 3, 5),
                Arrays.asList(2, 3, 4));
        List<List<Integer>> actual = solution.combinationSum3(k, n);
        assertEquals(expected, actual);
    }

    @Test
    void testEdgeCaseNoSolution() {
        int k = 4, n = 1;
        List<List<Integer>> expected = Collections.emptyList();
        List<List<Integer>> actual = solution.combinationSum3(k, n);
        assertEquals(expected, actual);
    }

    @Test
    void testMaxCase() {
        int k = 9, n = 45;
        List<List<Integer>> expected = Collections.singletonList(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        List<List<Integer>> actual = solution.combinationSum3(k, n);
        assertEquals(expected, actual);
    }

}