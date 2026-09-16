package io.github.monty.leetcode.backtracking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ID40CombinationSumIITest {

    private ID40CombinationSumII solution;

    @BeforeEach
    void setUp() {
        solution = new ID40CombinationSumII();
    }

    @Test
    void testExample1() {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(1, 1, 6),
                Arrays.asList(1, 2, 5),
                Arrays.asList(1, 7),
                Arrays.asList(2, 6)
        );
        assertEquals(expected.size(), solution.combinationSum2(candidates, target).size());
    }

    @Test
    void testExample2() {
        int[] candidates = {2, 5, 2, 1, 2};
        int target = 5;
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(1, 2, 2),
                Collections.singletonList(5)
        );
        assertEquals(expected.size(), solution.combinationSum2(candidates, target).size());
    }

    @Test
    void testEmpty() {
        assertEquals(0, solution.combinationSum2(new int[]{}, 3).size());
    }

}