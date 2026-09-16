package io.github.monty.leetcode.binarytree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ID501FindModeInBinarySearchTreeTest {

    private ID501FindModeInBinarySearchTree solution;

    @BeforeEach
    public void setUp() {
        solution = new ID501FindModeInBinarySearchTree();
    }

    @Test
    public void testSingleNode() {
        // 樹結構：
        //   1
        TreeNode root = TreeNode.of(1);
        int[] expected = {1};
        assertArrayEquals(expected, solution.findMode(root));
    }

    @Test
    public void testTwoSameValues() {
        // 樹結構：
        //     1
        //      \
        //       1
        TreeNode root = TreeNode.of(1, null, 1);
        int[] expected = {1};
        assertArrayEquals(expected, solution.findMode(root));
    }

    @Test
    public void testMultipleModes() {
        // 樹結構：
        //     1
        //    / \
        //   1   2
        TreeNode root = TreeNode.of(1, 1, 2);
        int[] expected = {1};
        assertArrayEquals(expected, solution.findMode(root));
    }

    @Test
    public void testNoDuplicateValues() {
        // 樹結構：
        //     2
        //    / \
        //   1   3
        TreeNode root = TreeNode.of(2, 1, 3);
        // 所有值都只出現一次，所有都是 mode
        int[] expected = {1, 2, 3};
        assertArrayEquals(expected, solution.findMode(root));
    }

    @Test
    public void testComplexTreeWithMultipleModes() {
        // 樹結構：
        //        6
        //       / \
        //      2   8
        //     / \   \
        //    2   4   8
        //             \
        //              8
//        TreeNode root = TreeNode.of(6, 2, 8, 2, 4, null, 8, null, null, null, null, null, null, null, 8);

        TreeNode root = new TreeNode(6);
        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(8);
        TreeNode n3 = new TreeNode(2);
        TreeNode n4 = new TreeNode(4);
        // index 5 = null
        TreeNode n6 = new TreeNode(8);
        // index 7–13 = null
        TreeNode n14 = new TreeNode(8);

        // 建立連結：
        root.left = n1;   // 6 -> 2
        root.right = n2;  // 6 -> 8
        n1.left = n3;   // 2 -> 2
        n1.right = n4;  // 2 -> 4
        // n2.left = null
        n2.right = n6;  // 8 -> 8
        // n6.left = null
        // n6.right = n14
        n6.right = n14; // 8 -> 8

        // 出現次數最多的是 8（3 次），其次是 2（2 次）
        int[] expected = {8};
        assertArrayEquals(expected, solution.findMode(root));
    }

    @Test
    public void testAllSameValues() {
        // 樹結構：
        //     1
        //    / \
        //   1   1
        TreeNode root = TreeNode.of(1, 1, 1);
        int[] expected = {1};
        assertArrayEquals(expected, solution.findMode(root));
    }

    @Test
    public void testTwoModes() {
        // 樹結構：
        //     3
        //    / \
        //   2   4
        //  /     \
        // 2       4
        TreeNode root = TreeNode.of(3, 2, 4, 2, null, null, 4);
        // 2 和 4 各出現 2 次
        int[] expected = {2, 4};
        assertArrayEquals(expected, solution.findMode(root));
    }

}