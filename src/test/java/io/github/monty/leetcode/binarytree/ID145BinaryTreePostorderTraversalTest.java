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
    void testEmptyTree() {
        assertEquals(Collections.emptyList(), solution.postorderTraversal(null));
    }

    @Test
    void testSingleNode() {
        TreeNode root = new TreeNode(7);
        assertEquals(Collections.singletonList(7), solution.postorderTraversal(root));
    }

}