package io.github.monty.leetcode.binarytree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ID108ConvertSortedArrayToBinarySearchTreeTest {

    private ID108ConvertSortedArrayToBinarySearchTree solution;

    @BeforeEach
    void setUp() {
        solution = new ID108ConvertSortedArrayToBinarySearchTree();
    }

    @Test
    void testExample1() {
        int[] nums = {-10, -3, 0, 5, 9};
        TreeNode expected = TreeNode.of(0, -10, 5, null, -3, null, 9);

        /*
         * 預期樹結構：
         *
         *        0
         *      /   \
         *    -10     5
         *      \      \
         *      -3      9
         */
        TreeNode actual = solution.sortedArrayToBST(nums);
        assertTrue(TreeNode.isSameTree(expected, actual));
    }

    @Test
    void testSingleElement() {
        int[] nums = {1};
        TreeNode expected = TreeNode.of(1);

        /*
         * 預期樹結構：
         *    1
         */
        TreeNode actual = solution.sortedArrayToBST(nums);
        assertTrue(TreeNode.isSameTree(expected, actual));
    }

    @Test
    void testTwoElements() {
        int[] nums = {1, 3};
        TreeNode expected = TreeNode.of(1, null, 3);

        /*
         * 預期樹結構：
         *    1
         *     \
         *      3
         */
        TreeNode actual = solution.sortedArrayToBST(nums);
        assertTrue(TreeNode.isSameTree(expected, actual));
    }

    @Test
    void testEmptyArray() {
        int[] nums = {};
        TreeNode actual = solution.sortedArrayToBST(nums);
        assertNull(actual);
    }

}