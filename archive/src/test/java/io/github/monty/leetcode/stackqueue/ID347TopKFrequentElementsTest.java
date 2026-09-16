package io.github.monty.leetcode.stackqueue;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ID347TopKFrequentElementsTest {

    ID347TopKFrequentElements solution = new ID347TopKFrequentElements();

    @Test
    void example1() {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        int[] expected = {1, 2};

        int[] result = solution.topKFrequent(nums, k);
        System.out.println(Arrays.toString(result));
        assertTrue(isSameSet(expected, result));
    }

    @Test
    void example2() {
        int[] nums = {1};
        int k = 1;
        int[] expected = {1};

        int[] result = solution.topKFrequent(nums, k);
        assertArrayEquals(expected, result);
    }

    @Test
    void allSameFrequency() {
        int[] nums = {4, 5, 6, 7};
        int k = 2;
        int[] result = solution.topKFrequent(nums, k);

        assertEquals(k, result.length);
        for (int num : result) {
            assertTrue(Arrays.asList(4, 5, 6, 7).contains(num));
        }
    }

    @Test
    void emptyInput() {
        int[] nums = {};
        int k = 0;
        int[] result = solution.topKFrequent(nums, k);
        assertEquals(0, result.length);
    }

    private boolean isSameSet(int[] a, int[] b) {
        if (a.length != b.length) return false;
        Set<Integer> setA = Arrays.stream(a).boxed().collect(Collectors.toSet());
        Set<Integer> setB = Arrays.stream(b).boxed().collect(Collectors.toSet());
        return setA.equals(setB);
    }

}