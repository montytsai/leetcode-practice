package io.github.monty.leetcode.backtracking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ID90SubsetsIITest {

    private ID90SubsetsII solution;

    @BeforeEach
    void setUp() {
        solution = new ID90SubsetsII();
    }

    @Test
    void testSubsetsWithDup_case1() {
        int[] nums = {1, 2, 2};
        List<List<Integer>> expected = Arrays.asList(
                Collections.emptyList(),
                Collections.singletonList(1),
                Arrays.asList(1, 2),
                Arrays.asList(1, 2, 2),
                Collections.singletonList(2),
                Arrays.asList(2, 2)
        );

        List<List<Integer>> actual = solution.subsetsWithDup(nums);
        assertEquals(expected.size(), actual.size());
        assertTrue(containsSameElements(expected, actual));
    }

    @Test
    void testSubsetsWithDup_case2_emptyInput() {
        int[] nums = {};
        List<List<Integer>> expected = Collections.singletonList(Collections.emptyList());
        List<List<Integer>> actual = solution.subsetsWithDup(nums);
        assertEquals(expected, actual);
    }

    @Test
    void testSubsetsWithDup_case3_singleElement() {
        int[] nums = {5};
        List<List<Integer>> expected = Arrays.asList(
                Collections.emptyList(),
                Collections.singletonList(5)
        );
        List<List<Integer>> actual = solution.subsetsWithDup(nums);
        assertEquals(expected.size(), actual.size());
        assertTrue(containsSameElements(expected, actual));
    }

    /**
     * 工具方法：忽略順序比對 List<List<Integer>>
     */
    private boolean containsSameElements(List<List<Integer>> a, List<List<Integer>> b) {
        Set<String> setA = new HashSet<>();
        for (List<Integer> list : a) {
            List<Integer> sorted = new ArrayList<>(list);
            Collections.sort(sorted);
            setA.add(sorted.toString());
        }

        Set<String> setB = new HashSet<>();
        for (List<Integer> list : b) {
            List<Integer> sorted = new ArrayList<>(list);
            Collections.sort(sorted);
            setB.add(sorted.toString());
        }

        return setA.equals(setB);
    }

}