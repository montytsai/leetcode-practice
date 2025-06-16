package io.github.monty.leetcode.backtracking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ID78SubsetsTest {

    private ID78Subsets solution;

    @BeforeEach
    public void setUp() {
        solution = new ID78Subsets();
    }

    @Test
    public void testEmptyInput() {
        int[] nums = {};
        List<List<Integer>> expected = Collections.singletonList(
                Collections.emptyList()
        );
        List<List<Integer>> actual = solution.subsets(nums);
        assertSubsetMatch(expected, actual);
    }

    @Test
    public void testSingleElement() {
        int[] nums = {1};
        List<List<Integer>> expected = Arrays.asList(
                Collections.emptyList(),
                Collections.singletonList(1)
        );
        List<List<Integer>> actual = solution.subsets(nums);
        assertSubsetMatch(expected, actual);
    }

    @Test
    public void testTwoElements() {
        int[] nums = {1, 2};
        List<List<Integer>> expected = Arrays.asList(
                Collections.emptyList(),
                Collections.singletonList(1),
                Collections.singletonList(2),
                Arrays.asList(1, 2)
        );
        List<List<Integer>> actual = solution.subsets(nums);
        assertSubsetMatch(expected, actual);
    }

    @Test
    public void testThreeElements() {
        int[] nums = {1, 2, 3};
        List<List<Integer>> expected = Arrays.asList(
                Collections.emptyList(),
                Collections.singletonList(1),
                Collections.singletonList(2),
                Collections.singletonList(3),
                Arrays.asList(1, 2),
                Arrays.asList(1, 3),
                Arrays.asList(2, 3),
                Arrays.asList(1, 2, 3)
        );
        List<List<Integer>> actual = solution.subsets(nums);
        assertSubsetMatch(expected, actual);
    }

    /**
     * 判斷兩個子集結果是否包含相同內容（不在意順序）
     */
    private void assertSubsetMatch(List<List<Integer>> expected, List<List<Integer>> actual) {
        for (List<Integer> subset : expected) {
            assertTrue(actual.contains(subset), "缺少子集: " + subset);
        }
        for (List<Integer> subset : actual) {
            assertTrue(expected.contains(subset), "多出子集: " + subset);
        }
    }

}