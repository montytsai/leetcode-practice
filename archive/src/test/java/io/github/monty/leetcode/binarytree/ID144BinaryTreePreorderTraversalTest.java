package io.github.monty.leetcode.binarytree;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class ID144BinaryTreePreorderTraversalTest {

    ID144BinaryTreePreorderTraversal solution = new ID144BinaryTreePreorderTraversal();

    @Test
    void testExampleCase() {
        TreeNode root = TreeNode.of(1, null, 2, 3);
        List<Integer> expected = Arrays.asList(1, 2, 3);
        assertEquals(expected, solution.preorderTraversal(root));
    }

    @Test
    void testExampleCase2() {
        TreeNode root = TreeNode.of(1, 2, 3, 4, 5, null, 8, null, null, 6, 7, 9);
        List<Integer> expected = Arrays.asList(1, 2, 4, 5, 6, 7, 3, 8, 9);
        assertEquals(expected, solution.preorderTraversal(root));
    }

    @Test
    void testEmptyTree() {
        assertEquals(Collections.emptyList(), solution.preorderTraversal(null));
    }

    @Test
    void testSingleNode() {
        TreeNode root = new TreeNode(99);
        assertEquals(Collections.singletonList(99), solution.preorderTraversal(root));
    }

}
