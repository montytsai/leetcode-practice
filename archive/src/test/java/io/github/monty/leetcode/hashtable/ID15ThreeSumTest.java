package io.github.monty.leetcode.hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Test cases for {@link ID15ThreeSum}.
 *
 * 測試重點：
 * 1. 基本三元組邏輯正確
 * 2. 無解時能正確回傳空列表
 * 3. 特殊情況（如全 0、重複元素）
 * 4. 邊界條件（少於三個元素）
 *
 * @author Monty.Tsai
 * @since 2025-04-26 21:37:00
 */
class ID15ThreeSumTest {

    private final ID15ThreeSum solution = new ID15ThreeSum();

    @Test
    void testNormalCase() {
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(-1, -1, 2));
        expected.add(Arrays.asList(-1, 0, 1));
        List<List<Integer>> result = solution.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        assertEquals(expected.size(), result.size());
        assertTrue(result.containsAll(expected));
    }

    @Test
    void testNoSolution() {
        List<List<Integer>> expected = new ArrayList<>();
        List<List<Integer>> result = solution.threeSum(new int[]{1, 2, 3, 4});
        assertEquals(expected.size(), result.size());
    }

    @Test
    void testAllZeros() {
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(0, 0, 0));
        List<List<Integer>> result = solution.threeSum(new int[]{0, 0, 0, 0});
        assertEquals(expected.size(), result.size());
        assertTrue(result.containsAll(expected));
    }

    @Test
    void testDuplicateElements() {
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(-2, 0, 2));
        List<List<Integer>> result = solution.threeSum(new int[]{-2, 0, 0, 2, 2});
        assertEquals(expected.size(), result.size());
        assertTrue(result.containsAll(expected));
    }

    @Test
    void testLessThanThreeElements() {
        List<List<Integer>> expected = new ArrayList<>();
        List<List<Integer>> result = solution.threeSum(new int[]{0, 1});
        assertEquals(expected.size(), result.size());
    }

}