package io.github.monty.leetcode.backtracking;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ID39CombinationSumTest {

    private final ID39CombinationSum solution = new ID39CombinationSum();

    @Test
    void testExample1() {
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(2, 2, 3),
                Collections.singletonList(7)
        );
        assertEqualsIgnoreOrder(expected, solution.combinationSum(new int[]{2, 3, 6, 7}, 7));
    }

    @Test
    void testExample2() {
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(2, 2, 2, 2),
                Arrays.asList(2, 3, 3),
                Arrays.asList(3, 5)
        );
        assertEqualsIgnoreOrder(expected, solution.combinationSum(new int[]{2, 3, 5}, 8));
    }

    @Test
    void testEmpty() {
        List<List<Integer>> expected = Collections.emptyList();
        assertEquals(expected, solution.combinationSum(new int[]{}, 7));
    }

    private void assertEqualsIgnoreOrder(List<List<Integer>> expected, List<List<Integer>> actual) {
        assertEquals(expected.size(), actual.size(), "Size mismatch");
        for (List<Integer> exp : expected) {
            assertTrue(actual.stream().anyMatch(act -> act.containsAll(exp) && exp.containsAll(act)),
                    "Missing expected combination: " + exp);
        }
    }

}