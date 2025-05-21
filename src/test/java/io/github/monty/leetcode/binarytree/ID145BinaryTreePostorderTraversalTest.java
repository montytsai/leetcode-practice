package io.github.monty.leetcode.binarytree;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

class ID145BinaryTreePostorderTraversalTest {

    ID145BinaryTreePostorderTraversal solution = new ID145BinaryTreePostorderTraversal();

    @Test
    void testExampleCase() {
        TreeNode root = TreeNode.of(1, null, 2, 3);
        List<Integer> expected = Arrays.asList(3, 2, 1);
        assertEquals(expected, solution.postorderTraversal(root));
    }

    @Test
    void testAllTraversals() {
        /*
         *                  1
         *                /   \
         *              4       2
         *            /       /   \
         *          7       3       5
         *         / \         \     \
         *       8    9         6     10
         *      /
         *    11
         */

        // 建立樹
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(4);
        root.right = new TreeNode(2);

        root.left.left = new TreeNode(7);
        root.left.left.left = new TreeNode(8);
        root.left.left.right = new TreeNode(9);
        root.left.left.left.left = new TreeNode(11);

        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(5);
        root.right.left.right = new TreeNode(6);
        root.right.right.right = new TreeNode(10);

        List<Integer> expected = Arrays.asList(11, 8, 9, 7, 4, 6, 3, 10, 5, 2, 1);
        assertEquals(expected, solution.postorderTraversal(root));
    }

    @Test
    void testEmptyTree() {
        assertEquals(Collections.emptyList(), solution.postorderTraversal(null));
    }

    @Test
    void testSingleNode() {
        TreeNode root = new TreeNode(7);
        assertEquals(Collections.singletonList(7), solution.postorderTraversal(root));
    }

}