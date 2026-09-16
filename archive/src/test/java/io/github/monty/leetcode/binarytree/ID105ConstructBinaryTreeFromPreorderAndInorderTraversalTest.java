package io.github.monty.leetcode.binarytree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ID105ConstructBinaryTreeFromPreorderAndInorderTraversalTest {

    private ID105ConstructBinaryTreeFromPreorderAndInorderTraversal solution;

    @BeforeEach
    void setUp() {
        solution = new ID105ConstructBinaryTreeFromPreorderAndInorderTraversal();
    }

    @Test
    @DisplayName("Example 1: buildTree([3,9,20,15,7], [9,3,15,20,7])")
    void testBuildTree_example1() {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};

        // 預期建構出的樹為：
        //     3
        //    / \
        //   9  20
        //      / \
        //     15  7
        TreeNode expected = TreeNode.of(3, 9, 20, null, null, 15, 7);

        TreeNode actual = solution.buildTree(preorder, inorder);
        assertTrue(isSameTree(expected, actual));
    }

    @Test
    @DisplayName("Example 2: buildTree([-1], [-1])")
    void testBuildTree_example2_singleNode() {
        int[] preorder = {-1};
        int[] inorder = {-1};

        TreeNode expected = TreeNode.of(-1);

        TreeNode actual = solution.buildTree(preorder, inorder);
        assertTrue(isSameTree(expected, actual));
    }

    @Test
    @DisplayName("Edge Case: Empty Tree")
    void testBuildTree_emptyTree() {
        int[] preorder = {};
        int[] inorder = {};

        TreeNode actual = solution.buildTree(preorder, inorder);
        assertEquals(null, actual);
    }

    @Test
    @DisplayName("Left-skewed Tree")
    void testBuildTree_leftSkewed() {
        int[] preorder = {5, 4, 3, 2, 1};
        int[] inorder = {1, 2, 3, 4, 5}; // inorder 反過來表示左子樹遞增

        TreeNode expected = TreeNode.of(5, 4, null, 3, null, 2, null, 1);

        TreeNode actual = solution.buildTree(preorder, inorder);
        assertTrue(isSameTree(expected, actual));
    }

    @Test
    @DisplayName("Right-skewed Tree")
    void testBuildTree_rightSkewed() {
        int[] preorder = {1, 2, 3, 4, 5};
        int[] inorder = {1, 2, 3, 4, 5}; // preorder = inorder 表示只有右子樹

        TreeNode expected = TreeNode.of(1, null, 2, null, 3, null, 4, null, 5);

        TreeNode actual = solution.buildTree(preorder, inorder);
        assertTrue(isSameTree(expected, actual));
    }

    /**
     * 遞迴比較兩棵二元樹的結構與數值是否一致
     */
    private boolean isSameTree(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        if (a.val != b.val) return false;
        return isSameTree(a.left, b.left) && isSameTree(a.right, b.right);
    }

}