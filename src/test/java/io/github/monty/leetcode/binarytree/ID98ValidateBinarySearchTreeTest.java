package io.github.monty.leetcode.binarytree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ID98ValidateBinarySearchTreeTest {

    private ID98ValidateBinarySearchTree solution;

    @BeforeEach
    public void setUp() {
        solution = new ID98ValidateBinarySearchTree();
    }

    @Test
    void testValidBST_example1() {
        // 樹結構如下，為合法 BST：
        //       2
        //      / \
        //     1   3
        TreeNode root = TreeNode.of(2, 1, 3);
        assertTrue(solution.isValidBST(root));
    }

    @Test
    void testInvalidBST_duplicateValue() {
        // 樹結構如下，3 的左子節點為 3，違反 BST 定義（左子節點必須 < 父節點）：
        //       5
        //      / \
        //     1   4
        //        / \
        //       3   6
        TreeNode root = TreeNode.of(5, 1, 4, null, null, 3, 6);
        assertFalse(solution.isValidBST(root));
    }

    @Test
    void testSingleNode() {
        // 只有一個節點的樹：
        //   1
        TreeNode root = TreeNode.of(1);
        assertTrue(solution.isValidBST(root));
    }

    @Test
    void testEqualNode() {
        //       1
        //      /
        //     1
        TreeNode root = TreeNode.of(1, 1, null);
        assertFalse(solution.isValidBST(root));
    }

    @Test
    void testInvalidBST_subtreeViolation() {
        // 樹結構如下，6 的右子節點為 4，違反 BST：
        //        10
        //       /  \
        //      5    15
        //          / \
        //         6   20
        TreeNode root = TreeNode.of(10, 5, 15, null, null, 6, 20);
        assertFalse(solution.isValidBST(root));
    }

    @Test
    void testValidBST_largeRange() {
        // 樹結構如下，為合法 BST：
        //          10
        //         /  \
        //        5    15
        //            /  \
        //          11   20
        TreeNode root = TreeNode.of(10, 5, 15, null, null, 11, 20);
        assertTrue(solution.isValidBST(root));
    }

    @Test
    void testNullRoot() {
        // 空樹應為合法 BST
        TreeNode root = null;
        assertTrue(solution.isValidBST(root));
    }

}