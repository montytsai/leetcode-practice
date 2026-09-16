package io.github.monty.leetcode.binarytree;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ID235LowestCommonAncestorOfABinarySearchTreeTest {

    ID235LowestCommonAncestorOfABinarySearchTree solution = new ID235LowestCommonAncestorOfABinarySearchTree();

    // 建立 BST 範例：
    //         6
    //       /   \
    //      2     8
    //     / \   / \
    //    0   4 7   9
    //       / \
    //      3   5

    private TreeNode buildSampleBST() {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        return root;
    }

    @Test
    void testLowestCommonAncestor() {
        TreeNode root = buildSampleBST();
        TreeNode p = root.left; // 2
        TreeNode q = root.right; // 8
        TreeNode result = solution.lowestCommonAncestor(root, p, q);
        assertEquals(6, result.val);
    }

    @Test
    void testLowestCommonAncestorIterative() {
        TreeNode root = buildSampleBST();
        TreeNode p = root.left; // 2
        TreeNode q = root.left.right; // 4
        TreeNode result = solution.lowestCommonAncestorIterative(root, p, q);
        assertEquals(2, result.val);
    }

    @Test
    void testSameNode() {
        TreeNode root = buildSampleBST();
        TreeNode p = root.left.right.left; // 3
        TreeNode q = root.left.right.left; // 3
        TreeNode result = solution.lowestCommonAncestor(root, p, q);
        assertEquals(3, result.val);
    }

}