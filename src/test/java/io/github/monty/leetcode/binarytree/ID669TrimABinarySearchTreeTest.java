package io.github.monty.leetcode.binarytree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ID669TrimABinarySearchTreeTest {

    private ID669TrimABinarySearchTree solution;

    @BeforeEach
    public void setUp() {
        solution = new ID669TrimABinarySearchTree();
    }

    @Test
    public void testTrimBST_example1() {
        // 測資輸入：
        //       1
        //      / \
        //     0   2
        //
        // 範圍：low = 1, high = 2
        //
        // 預期輸出：
        //       1
        //        \
        //         2
        //
        // ASCII 樹圖：
        //     1
        //      \
        //       2
        TreeNode root = TreeNode.of(1, 0, 2);
        TreeNode expected = TreeNode.of(1, null, 2);
        TreeNode actual = solution.trimBST(root, 1, 2);
        assertTrue(TreeNode.isSameTree(expected, actual));
    }

    @Test
    public void testTrimBST_example2() {
        // 測資輸入：
        //           3
        //          / \
        //         0   4
        //          \
        //           2
        //          /
        //         1
        //
        // 範圍：low = 1, high = 3
        //
        // 預期輸出：
        //           3
        //          /
        //         2
        //        /
        //       1
        //
        // ASCII 樹圖：
        //     3
        //    /
        //   2
        //  /
        // 1
        TreeNode root = TreeNode.of(3, 0, 4, null, 2, null, null, 1);
        TreeNode expected = TreeNode.of(3, 2, null, 1);
        TreeNode actual = solution.trimBST(root, 1, 3);
        assertTrue(TreeNode.isSameTree(expected, actual));
    }

    @Test
    public void testTrimBST_equals() {
        /*
         * 測資輸入： [3,2,4,1]
         *      3
         *     / \
         *    2   4
         *   /
         *  1
         *  範圍：low = 1, high = 1
         * 	期望结果:[1]
         */
        TreeNode root = TreeNode.of(3, 2, 4, 1);
        TreeNode expected = TreeNode.of(1);
        TreeNode actual = solution.trimBST(root, 1, 1);
        assertTrue(TreeNode.isSameTree(expected, actual));
    }

    @Test
    public void testTrimBST_2() {
        /*
         * 測資輸入： [4, 2, 5, 1, 3]
         *      4
         *     / \
         *    2   5
         *   / \
         *  1   3
         *  範圍：low = 3, high = 5
         * 	期望结果:[4, 3, 5]
         */
        TreeNode root = TreeNode.of(4, 2, 5, 1, 3);
        TreeNode expected = TreeNode.of(2, null, 3);
        TreeNode actual = solution.trimBST(root, 2, 3);
        assertTrue(TreeNode.isSameTree(expected, actual));
    }

    @Test
    public void testTrimBST_3() {
        /*
         * 測資輸入： [3,1,4,null,2]
         *      3
         *     / \
         *    1   4
         *     \
         *      2
         *  範圍：low = 3, high = 4
         * 	期望结果:[3, null, 4]
         */
        TreeNode root = TreeNode.of(3, 1, 4, null, 2);
        TreeNode expected = TreeNode.of(3, null, 4);
        TreeNode actual = solution.trimBST(root, 3, 4);
        assertTrue(TreeNode.isSameTree(expected, actual));
    }

    @Test
    public void testTrimBST_allNodesOutOfRange() {
        // 測資輸入：
        //     3
        //    / \
        //   1   4
        //
        // 範圍：low = 5, high = 6
        //
        // 預期輸出：null
        TreeNode root = TreeNode.of(3, 1, 4);
        TreeNode actual = solution.trimBST(root, 5, 6);
        assertTrue(TreeNode.isSameTree(null, actual));
    }

    @Test
    public void testTrimBST_fullTreeWithinRange() {
        // 測資輸入：
        //     2
        //    / \
        //   1   3
        //
        // 範圍：low = 1, high = 3
        //
        // 預期輸出與輸入相同
        TreeNode root = TreeNode.of(2, 1, 3);
        TreeNode expected = TreeNode.of(2, 1, 3);
        TreeNode actual = solution.trimBST(root, 1, 3);
        assertTrue(TreeNode.isSameTree(expected, actual));
    }

    @Test
    public void testTrimBST_singleNodeWithinRange() {
        // 測資輸入：
        //     1
        //
        // 範圍：low = 1, high = 1
        //
        // 預期輸出：
        //     1
        TreeNode root = TreeNode.of(1);
        TreeNode expected = TreeNode.of(1);
        TreeNode actual = solution.trimBST(root, 1, 1);
        assertTrue(TreeNode.isSameTree(expected, actual));
    }

    @Test
    public void testTrimBST_singleNodeOutOfRange() {
        // 測資輸入：
        //     1
        //
        // 範圍：low = 2, high = 3
        //
        // 預期輸出：null
        TreeNode root = TreeNode.of(1);
        TreeNode actual = solution.trimBST(root, 2, 3);
        assertTrue(TreeNode.isSameTree(null, actual));
    }

}