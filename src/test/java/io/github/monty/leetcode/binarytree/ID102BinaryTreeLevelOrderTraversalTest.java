package io.github.monty.leetcode.binarytree;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ID102BinaryTreeLevelOrderTraversalTest {

    ID102BinaryTreeLevelOrderTraversal solution = new ID102BinaryTreeLevelOrderTraversal();

    @Test
    void testExampleCase() {
        Integer[] input = {3, 9, 20, null, null, 15, 7};
        TreeNode root = TreeNode.of(input);

        List<List<Integer>> expected = Arrays.asList(
                Collections.singletonList(3),
                Arrays.asList(9, 20),
                Arrays.asList(15, 7)
        );

        assertEquals(expected, solution.levelOrder(root));
    }

    @Test
    void testEmptyTree() {
        TreeNode root = null;
        List<List<Integer>> expected = new ArrayList<>();
        assertEquals(expected, solution.levelOrder(root));
    }

    @Test
    void testSingleNode() {
        TreeNode root = new TreeNode(1);
        List<List<Integer>> expected = Collections.singletonList(Collections.singletonList(1));
        assertEquals(expected, solution.levelOrder(root));
    }

    @Test
    void testLeftSkewedTree() {
        Integer[] input = {1, 2, null, 3, null, 4, null};
        TreeNode root = TreeNode.of(input);

        List<List<Integer>> expected = Arrays.asList(
                Collections.singletonList(1),
                Collections.singletonList(2),
                Collections.singletonList(3),
                Collections.singletonList(4)
        );

        assertEquals(expected, solution.levelOrder(root));
    }

    @Test
    void testRightSkewedTree() {
        Integer[] input = {1, null, 2, null, 3, null, 4};
        TreeNode root = TreeNode.of(input);

        List<List<Integer>> expected = Arrays.asList(
                Collections.singletonList(1),
                Collections.singletonList(2),
                Collections.singletonList(3),
                Collections.singletonList(4)
        );

        assertEquals(expected, solution.levelOrder(root));
    }

}
