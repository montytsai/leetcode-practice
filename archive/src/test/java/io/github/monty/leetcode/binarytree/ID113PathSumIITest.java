package io.github.monty.leetcode.binarytree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ID113PathSumIITest {

    private ID113PathSumII solution;

    @BeforeEach
    public void setUp() {
        solution = new ID113PathSumII();
    }

    @Test
    public void testExample1() {
        /*
         * 範例輸入：
         *        5
         *       / \
         *      4   8
         *     /   / \
         *    11  13  4
         *   /  \    / \
         *  7    2  5   1
         *
         * targetSum = 22
         *
         * 預期輸出：
         * [
         *   [5,4,11,2],
         *   [5,8,4,5]
         * ]
         */
        TreeNode root = TreeNode.of(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1);
        int targetSum = 22;
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(5, 4, 11, 2),
                Arrays.asList(5, 8, 4, 5)
        );

        List<List<Integer>> result = solution.pathSum(root, targetSum);
        assertEquals(expected.size(), result.size());
        assertTrue(result.containsAll(expected));
    }

    @Test
    public void testEmptyTree() {
        // 空樹測試：應回傳空清單
        TreeNode root = null;
        int targetSum = 0;
        List<List<Integer>> expected = Collections.emptyList();

        List<List<Integer>> result = solution.pathSum(root, targetSum);
        assertEquals(expected, result);
    }

    @Test
    public void testSingleNodeMatch() {
        // 單節點符合 targetSum，應回傳單一路徑
        TreeNode root = TreeNode.of(7);
        int targetSum = 7;
        List<List<Integer>> expected = Collections.singletonList(
                Collections.singletonList(7)
        );

        List<List<Integer>> result = solution.pathSum(root, targetSum);
        assertEquals(expected, result);
    }

    @Test
    public void testSingleNodeNotMatch() {
        // 單節點不符 targetSum，應回傳空清單
        TreeNode root = TreeNode.of(1);
        int targetSum = 2;
        List<List<Integer>> expected = Collections.emptyList();

        List<List<Integer>> result = solution.pathSum(root, targetSum);
        assertEquals(expected, result);
    }

    @Test
    public void testMultiplePaths() {
        /*
         * 測試路徑多條時，是否能正確收集所有合法路徑
         *
         *       1
         *      / \
         *     2   3
         *    /   / \
         *   4   5   6
         *
         * targetSum = 7
         * 合法路徑：[1,2,4], [1,3,3]（無），[1,3,3] 錯誤，應該只有 [1,2,4]
         */
        TreeNode root = TreeNode.of(1, 2, 3, 4, null, 5, 6);
        int targetSum = 7;
        List<List<Integer>> expected = Collections.singletonList(
                Arrays.asList(1, 2, 4)
        );

        List<List<Integer>> result = solution.pathSum(root, targetSum);
        assertEquals(expected.size(), result.size());
        assertTrue(result.containsAll(expected));
    }

}