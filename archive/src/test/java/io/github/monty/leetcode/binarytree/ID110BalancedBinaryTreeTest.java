package io.github.monty.leetcode.binarytree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ID110BalancedBinaryTreeTest {

    private ID110BalancedBinaryTree solution;

    @BeforeEach
    public void setUp() {
        solution = new ID110BalancedBinaryTree();
    }

    @Test
    void testExample2() {
        TreeNode root = TreeNode.of(1, 2, 2, 3, null, null, 3, 4, null, null, 4);
        assertFalse(solution.isBalanced(root));
    }

    @Test
    void testEmptyTree() {
        TreeNode root = null;
        assertTrue(solution.isBalanced(root), "空樹應為平衡樹");
    }

    @Test
    void testSingleNode() {
        TreeNode root = TreeNode.of(1);
        assertTrue(solution.isBalanced(root), "單一節點應為平衡樹");
    }

    @Test
    void testPerfectBalancedTree() {
        //       1
        //     /   \
        //    2     3
        //   / \   / \
        //  4   5 6   7
        TreeNode root = TreeNode.of(1, 2, 3, 4, 5, 6, 7);
        assertTrue(solution.isBalanced(root), "完全二元樹應為平衡樹");
    }

    @Test
    void testUnbalancedLeftHeavyTree() {
        //     1
        //    /
        //   2
        //  /
        // 3
        TreeNode root = TreeNode.of(1, 2, null, 3);
        assertFalse(solution.isBalanced(root), "左子樹過深，應為不平衡樹");
    }

    @Test
    void testUnbalancedSubtreeDifferenceMoreThanOne() {
        //       1
        //      / \
        //     2   2
        //    /
        //   3
        //  /
        // 4
        TreeNode root = TreeNode.of(1, 2, 2, 3, null, null, null, 4);
        assertFalse(solution.isBalanced(root), "子樹深度差超過 1，應為不平衡樹");
    }

    @Test
    void testBalancedButNotPerfectTree() {
        //       1
        //      / \
        //     2   2
        //    /     \
        //   3       3
        TreeNode root = TreeNode.of(1, 2, 2, 3, null, null, 3);
        assertTrue(solution.isBalanced(root), "左右子樹深度差為 0，應為平衡樹");
    }

}