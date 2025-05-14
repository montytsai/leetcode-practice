package io.github.monty.leetcode.binarytree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ID617MergeTwoBinaryTreesTest {

    private ID617MergeTwoBinaryTrees solution;

    @BeforeEach
    public void setUp() {
        solution = new ID617MergeTwoBinaryTrees();
    }

    @Test
    public void testExample1() {
        TreeNode t1 = TreeNode.of(1, 3, 2, 5);
        TreeNode t2 = TreeNode.of(2, 1, 3, null, 4, null, 7);

        /*
         * Input Tree t1:           Input Tree t2:
         *     1                          2
         *    / \                        / \
         *   3   2                      1   3
         *  /                          \     \
         * 5                            4     7
         *
         * Expected merged tree:
         *     3
         *    / \
         *   4   5
         *  / \   \
         * 5   4   7
         */
        TreeNode expected = TreeNode.of(3, 4, 5, 5, 4, null, 7);

        TreeNode result = solution.mergeTrees(t1, t2);
        assertTrue(TreeNode.isSameTree(expected, result));
    }

    @Test
    public void testOneTreeIsNull() {
        TreeNode t1 = TreeNode.of(1, 2, 3);
        TreeNode t2 = null;

        /*
         * Input Tree t1:
         *   1
         *  / \
         * 2   3
         *
         * Expected: same as t1
         */
        TreeNode expected = TreeNode.of(1, 2, 3);

        TreeNode result = solution.mergeTrees(t1, t2);
        assertTrue(TreeNode.isSameTree(expected, result));
    }

    @Test
    public void testBothTreesNull() {
        TreeNode result = solution.mergeTrees(null, null);
        assertNull(result);
    }

    @Test
    public void testDifferentStructure() {
        TreeNode t1 = TreeNode.of(1, 3, null, 5);
        TreeNode t2 = TreeNode.of(2, 1, 3, null, 4, null, 7);

        /*
         * Input Tree t1:           Input Tree t2:
         *     1                          2
         *    /                            / \
         *   3                            1   3
         *  /                              \    \
         * 5                                4    7
         *
         * Expected merged tree:
         *     3
         *    / \
         *   4   3
         *  / \    \
         * 5   4    7
         */
        TreeNode expected = TreeNode.of(3, 4, 3, 5, 4, null, 7);

        TreeNode result = solution.mergeTrees(t1, t2);
        assertTrue(TreeNode.isSameTree(expected, result));
    }
}