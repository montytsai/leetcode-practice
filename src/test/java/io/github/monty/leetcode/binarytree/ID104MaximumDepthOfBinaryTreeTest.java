package io.github.monty.leetcode.binarytree;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * LeetCode #104: Maximum Depth of Binary Tree
 * JUnit 5 測試類別
 *
 * @author Monty
 * @since 2025-05-09
 */
class ID104MaximumDepthOfBinaryTreeTest {

    private final ID104MaximumDepthOfBinaryTree solution = new ID104MaximumDepthOfBinaryTree();

    @Test
    @DisplayName("空樹應回傳深度 0")
    void testEmptyTree() {
        TreeNode root = null;
        assertEquals(0, solution.maxDepth(root));
    }

    @Test
    @DisplayName("只有根節點，深度應為 1")
    void testSingleNode() {
        TreeNode root = new TreeNode(1);
        assertEquals(1, solution.maxDepth(root));
    }

    @Test
    @DisplayName("左側為直線鏈狀樹，深度應正確")
    void testLeftSkewedTree() {
        TreeNode root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(3,
                                new TreeNode(4), null),
                        null),
                null);
        assertEquals(4, solution.maxDepth(root));
    }

    @Test
    @DisplayName("右側為直線鏈狀樹，深度應正確")
    void testRightSkewedTree() {
        TreeNode root = new TreeNode(1,
                null,
                new TreeNode(2,
                        null,
                        new TreeNode(3,
                                null,
                                new TreeNode(4))));
        assertEquals(4, solution.maxDepth(root));
    }

    @Test
    @DisplayName("完整二元樹應計算最大深度")
    void testBalancedTree() {
        TreeNode root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4),
                        new TreeNode(5)),
                new TreeNode(3,
                        new TreeNode(6),
                        new TreeNode(7)));
        assertEquals(3, solution.maxDepth(root));
    }

    @Test
    @DisplayName("不平衡樹深度測試")
    void testUnbalancedTree() {
        TreeNode root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4,
                                new TreeNode(8),
                                null),
                        null),
                new TreeNode(3));
        assertEquals(4, solution.maxDepth(root));
    }

}