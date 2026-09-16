package io.github.monty.leetcode.binarytree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ID538ConvertBstToGreaterTreeTest {

    private ID538ConvertBstToGreaterTree solution;

    @BeforeEach
    public void setUp() {
        solution = new ID538ConvertBstToGreaterTree();
    }

    @Test
    public void testConvertBST_iterative() {
        TreeNode input = TreeNode.of(4, 1, 6, 0, 2, 5, 7, null, null, null, 3, null, null, null, 8);
        TreeNode expected = TreeNode.of(30, 36, 21, 36, 35, 26, 15, null, null, null, 33, null, null, null, 8);

        TreeNode result = solution.convertBST(input);
        assertTrue(TreeNode.isSameTree(expected, result));
    }

    @Test
    public void testConvertBST_recursive() {
        TreeNode input = TreeNode.of(4, 1, 6, 0, 2, 5, 7, null, null, null, 3, null, null, null, 8);
        TreeNode expected = TreeNode.of(30, 36, 21, 36, 35, 26, 15, null, null, null, 33, null, null, null, 8);

        TreeNode result = solution.convertBST(input);
        assertTrue(TreeNode.isSameTree(expected, result));
    }

    @Test
    public void testEmptyTree() {
        assertNull(solution.convertBST(null));
    }

    @Test
    public void testSingleNode() {
        TreeNode input = TreeNode.of(5);
        TreeNode expected = TreeNode.of(5);
        TreeNode result = solution.convertBST(input);
        assertTrue(TreeNode.isSameTree(expected, result));
    }

}