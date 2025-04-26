package io.github.monty.leetcode.hashtable;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ID18FourSumTest {

    @Test
    void testFourSum_case1() {
        ID18FourSum solution = new ID18FourSum();
        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;

        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(-2, -1, 1, 2));
        expected.add(Arrays.asList(-2, 0, 0, 2));
        expected.add(Arrays.asList(-1, 0, 0, 1));

        List<List<Integer>> actual = solution.fourSum(nums, target);

        assertSameElements(expected, actual);
    }

    @Test
    void testFourSum_case2() {
        ID18FourSum solution = new ID18FourSum();
        int[] nums = {2, 2, 2, 2, 2};
        int target = 8;

        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(2, 2, 2, 2));

        List<List<Integer>> actual = solution.fourSum(nums, target);

        assertSameElements(expected, actual);
    }

    private void assertSameElements(List<List<Integer>> expected, List<List<Integer>> actual) {
        assertTrue(expected.size() == actual.size()
                        && expected.stream().allMatch(e -> actual.stream().anyMatch(a -> a.equals(e))),
                "實際結果與預期結果不符");
    }

}