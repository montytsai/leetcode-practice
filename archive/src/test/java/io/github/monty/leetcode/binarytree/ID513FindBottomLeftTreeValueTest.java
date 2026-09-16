package io.github.monty.leetcode.binarytree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ID513FindBottomLeftTreeValueTest {

    private ID513FindBottomLeftTreeValue solution;

    @BeforeEach
    public void setUp() {
        solution = new ID513FindBottomLeftTreeValue();
    }

    @Test
    public void testExample1() {
        TreeNode root = TreeNode.of(2, 1, 3);
        // Tree:
        //     2
        //    / \
        //   1   3
        // 最底層為 [1, 3]，最左為 1
        assertEquals(1, solution.findBottomLeftValue(root));
    }

    @Test
    public void testExample2() {
        TreeNode root = TreeNode.of(1, 2, 3, 4, null, 5, 6, null, null, 7);
        // Tree:
        //         1
        //        / \
        //       2   3
        //      /   / \
        //     4   5   6
        //        /
        //       7
        // 最底層為 [7]，最左為 7
        assertEquals(7, solution.findBottomLeftValue(root));
    }

    @Test
    public void testSingleNode() {
        TreeNode root = TreeNode.of(42);
        assertEquals(42, solution.findBottomLeftValue(root));
    }

    @Test
    public void testLeftSkewedTree() {
        TreeNode root = TreeNode.of(1, 2, null, 3, null, 4);
        // 只有左邊節點：1 -> 2 -> 3 -> 4
        // 最底層為 4，最左也為 4
        assertEquals(4, solution.findBottomLeftValue(root));
    }

    @Test
    public void testRightSkewedTree() {
        TreeNode root = TreeNode.of(1, null, 2, null, 3);
        // 只有右邊節點：1 -> 2 -> 3
        // 最底層為 3，最左也為 3
        assertEquals(3, solution.findBottomLeftValue(root));
    }

}