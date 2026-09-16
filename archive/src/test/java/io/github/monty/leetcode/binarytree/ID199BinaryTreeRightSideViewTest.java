package io.github.monty.leetcode.binarytree;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ID199BinaryTreeRightSideViewTest {

    ID199BinaryTreeRightSideView solution = new ID199BinaryTreeRightSideView();

    @Test
    void testEmptyTree() {
        List<Integer> expected = Collections.emptyList();
        assertEquals(expected, solution.rightSideView(null));
    }

    @Test
    void testSingleNode() {
        TreeNode root = TreeNode.of(1);
        List<Integer> expected = Collections.singletonList(1);
        assertEquals(expected, solution.rightSideView(root));
    }

    @Test
    void testCompleteBinaryTree() {
        TreeNode root = TreeNode.of(1, 2, 3, 4, 5, null, 6);
        // Tree:
        //        1
        //      /   \
        //     2     3
        //    / \     \
        //   4   5     6
        List<Integer> expected = Arrays.asList(1, 3, 6);
        assertEquals(expected, solution.rightSideView(root));
    }

    @Test
    void testLeftSkewedTree() {
        TreeNode root = TreeNode.of(1, 2, null, 3, null, 4);
        // Tree:
        //      1
        //     /
        //    2
        //   /
        //  3
        // /
        //4
        List<Integer> expected = Arrays.asList(1, 2, 3, 4);
        assertEquals(expected, solution.rightSideView(root));
    }

    @Test
    void testRightSkewedTree() {
        TreeNode root = TreeNode.of(1, null, 2, null, 3, null, 4);
        // Tree:
        // 1
        //  \
        //   2
        //    \
        //     3
        //      \
        //       4
        List<Integer> expected = Arrays.asList(1, 2, 3, 4);
        assertEquals(expected, solution.rightSideView(root));
    }

}