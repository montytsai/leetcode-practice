package io.github.monty.leetcode.binarytree;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

class ID94BinaryTreeInorderTraversalTest {

    ID94BinaryTreeInorderTraversal solution = new ID94BinaryTreeInorderTraversal();

    @Test
    void testExampleCase() {
        TreeNode root = TreeNode.of(1, null, 2, 3);
        List<Integer> expected = Arrays.asList(1, 3, 2);
        assertEquals(expected, solution.inorderTraversal(root));
    }

    @Test
    void testExampleCase2() {
        TreeNode root = TreeNode.of(1, 2, 3, 4, 5, null, 8, null, null, 6, 7, 9);
        List<Integer> expected = Arrays.asList(4, 2, 6, 5, 7, 1, 3, 9, 8);
        assertEquals(expected, solution.inorderTraversal(root));
    }

    @Test
    void testEmptyTree() {
        assertEquals(Collections.emptyList(), solution.inorderTraversal(null));
    }

    @Test
    void testSingleNode() {
        TreeNode root = new TreeNode(42);
        assertEquals(Collections.singletonList(42), solution.inorderTraversal(root));
    }

}