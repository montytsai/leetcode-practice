package io.github.monty.leetcode.binarytree;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ID111MinimumDepthOfBinaryTreeTest {

    private final ID111MinimumDepthOfBinaryTree solution = new ID111MinimumDepthOfBinaryTree();

    @Test
    @DisplayName("空樹：最小深度應為 0")
    void testEmptyTree() {
        TreeNode root = null;
        assertEquals(0, solution.minDepth(root));
    }

    @Test
    @DisplayName("單一節點：最小深度應為 1")
    void testSingleNode() {
        TreeNode root = new TreeNode(1);
        assertEquals(1, solution.minDepth(root));
    }

    @Test
    @DisplayName("左子樹只有一層，右子樹為 null：最小深度應為 2")
    void testOnlyLeftSubtree() {
        TreeNode root = new TreeNode(1, new TreeNode(2), null);
        assertEquals(2, solution.minDepth(root));
    }

    @Test
    @DisplayName("右子樹只有一層，左子樹為 null：最小深度應為 2")
    void testOnlyRightSubtree() {
        TreeNode root = new TreeNode(1, null, new TreeNode(2));
        assertEquals(2, solution.minDepth(root));
    }

    @Test
    @DisplayName("左右皆有子樹：取最短的葉節點深度")
    void testBalancedTreeWithDifferentDepths() {
        TreeNode left = new TreeNode(2, new TreeNode(4), new TreeNode(5));
        TreeNode right = new TreeNode(3);
        TreeNode root = new TreeNode(1, left, right);
        assertEquals(2, solution.minDepth(root));
    }

    @Test
    @DisplayName("深度不均衡的樹：只有一個右節點鏈")
    void testSkewedRightTree() {
        TreeNode root = new TreeNode(1,
                null,
                new TreeNode(2,
                        null,
                        new TreeNode(3,
                                null,
                                new TreeNode(4)
                        )
                )
        );
        assertEquals(4, solution.minDepth(root));
    }

}