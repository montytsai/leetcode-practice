package io.github.monty.leetcode.binarytree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ID112PathSumTest {

    private ID112PathSum solution;

    @BeforeEach
    public void setUp() {
        solution = new ID112PathSum();
    }

    @Test
    public void testExample1() {
        TreeNode root = TreeNode.of(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1);
        // Tree 結構：
        //         5
        //        / \
        //       4   8
        //      /   / \
        //     11  13  4
        //    /  \      \
        //   7    2      1
        // targetSum = 22, 有一條路徑：5 → 4 → 11 → 2
        assertTrue(solution.hasPathSum(root, 22));
    }

    @Test
    public void testExample2() {
        TreeNode root = TreeNode.of(1, 2, 3);
        // Tree 結構：
        //     1
        //    / \
        //   2   3
        // targetSum = 5, 無符合路徑
        assertFalse(solution.hasPathSum(root, 5));
    }

    @Test
    public void testNullTree() {
        TreeNode root = null;
        // 空樹不可能有任何路徑
        assertFalse(solution.hasPathSum(root, 0));
    }

    @Test
    public void testSingleNodeMatch() {
        TreeNode root = TreeNode.of(7);
        // 單節點值剛好等於 targetSum
        assertTrue(solution.hasPathSum(root, 7));
    }

    @Test
    public void testSingleNodeMismatch() {
        TreeNode root = TreeNode.of(7);
        // 單節點值不等於 targetSum
        assertFalse(solution.hasPathSum(root, 8));
    }

    @Test
    public void testNegativeValues() {
        TreeNode root = TreeNode.of(-2, null, -3);
        // Tree 結構：
        //   -2
        //     \
        //     -3
        // targetSum = -5 → 存在 -2 → -3 的路徑
        assertTrue(solution.hasPathSum(root, -5));
    }

    @Test
    public void testMultiplePathsButNoMatch() {
        TreeNode root = TreeNode.of(1, 2, 3, 4, 5, 6, 7);
        // 無任何 root-to-leaf 總和為 100 的路徑
        assertFalse(solution.hasPathSum(root, 100));
    }

}