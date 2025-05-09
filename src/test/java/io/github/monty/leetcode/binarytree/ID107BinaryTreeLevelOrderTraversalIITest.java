package io.github.monty.leetcode.binarytree;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ID107BinaryTreeLevelOrderTraversalIITest {

    private final ID107BinaryTreeLevelOrderTraversalII solution = new ID107BinaryTreeLevelOrderTraversalII();

    @Test
    void testExample1() {
        TreeNode root = TreeNode.of(3, 9, 20, null, null, 15, 7);
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(15, 7));
        expected.add(Arrays.asList(9, 20));
        expected.add(Arrays.asList(3));
        assertEquals(expected, solution.levelOrderBottom(root));
    }

    @Test
    void testSingleNode() {
        TreeNode root = TreeNode.of(1);
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(1));
        assertEquals(expected, solution.levelOrderBottom(root));
    }

    @Test
    void testEmptyTree() {
        TreeNode root = TreeNode.of();
        List<List<Integer>> expected = new ArrayList<>();
        assertEquals(expected, solution.levelOrderBottom(root));
    }

    @Test
    void testLeftSkewedTree() {
        TreeNode root = TreeNode.of(1, 2, null, 3);
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(3));
        expected.add(Arrays.asList(2));
        expected.add(Arrays.asList(1));
        assertEquals(expected, solution.levelOrderBottom(root));
    }

    @Test
    void testRightSkewedTree() {
        TreeNode root = TreeNode.of(1, null, 2, null, 3);
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(3));
        expected.add(Arrays.asList(2));
        expected.add(Arrays.asList(1));
        assertEquals(expected, solution.levelOrderBottom(root));
    }

}
