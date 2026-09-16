package io.github.monty.leetcode.binarytree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ID654MaximumBinaryTreeTest {

    private ID654MaximumBinaryTree solution;

    @BeforeEach
    public void setUp() {
        solution = new ID654MaximumBinaryTree();
    }

    @Test
    public void testExample1() {
        int[] nums = {3, 2, 1, 6, 0, 5};

        // 手動建構預期樹：
        //         6
        //       /   \
        //      3     5
        //       \   /
        //        2 0
        //         \
        //          1
        TreeNode expected = new TreeNode(6);
        expected.left = new TreeNode(3);
        expected.right = new TreeNode(5);
        expected.left.right = new TreeNode(2);
        expected.left.right.right = new TreeNode(1);
        expected.right.left = new TreeNode(0);

        TreeNode actual = solution.constructMaximumBinaryTree(nums);
        assertTrue(TreeNode.isSameTree(expected, actual));
    }

    @Test
    public void testSingleNode() {
        int[] nums = {1};

        // 預期：單一節點樹
        TreeNode expected = new TreeNode(1);

        TreeNode actual = solution.constructMaximumBinaryTree(nums);
        assertTrue(TreeNode.isSameTree(expected, actual));
    }

    @Test
    public void testTwoNodesIncreasing() {
        int[] nums = {1, 2};

        // 預期：
        //   2
        //  /
        // 1
        TreeNode expected = new TreeNode(2);
        expected.left = new TreeNode(1);

        TreeNode actual = solution.constructMaximumBinaryTree(nums);
        assertTrue(TreeNode.isSameTree(expected, actual));
    }

    @Test
    public void testTwoNodesDecreasing() {
        int[] nums = {2, 1};

        // 預期：
        // 2
        //  \
        //   1
        TreeNode expected = new TreeNode(2);
        expected.right = new TreeNode(1);

        TreeNode actual = solution.constructMaximumBinaryTree(nums);
        assertTrue(TreeNode.isSameTree(expected, actual));
    }

    @Test
    public void testEmptyArray() {
        int[] nums = {};

        // 預期：null
        TreeNode expected = null;

        TreeNode actual = solution.constructMaximumBinaryTree(nums);
        assertTrue(TreeNode.isSameTree(expected, actual));
    }

}