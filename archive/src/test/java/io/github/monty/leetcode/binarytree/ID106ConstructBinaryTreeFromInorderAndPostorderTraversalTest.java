package io.github.monty.leetcode.binarytree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ID106ConstructBinaryTreeFromInorderAndPostorderTraversalTest {

    private ID106ConstructBinaryTreeFromInorderAndPostorderTraversal solution;

    @BeforeEach
    public void setUp() {
        solution = new ID106ConstructBinaryTreeFromInorderAndPostorderTraversal();
    }

    @Test
    public void testExample1() {
        // 測資來源：LeetCode 題目範例
        int[] inorder =    {9, 3, 15, 20, 7};
        int[] postorder =  {9, 15, 7, 20, 3};

        // 預期建構結果：
        //        3
        //       / \
        //      9  20
        //         / \
        //        15  7
        TreeNode expected = TreeNode.of(3, 9, 20, null, null, 15, 7);
        TreeNode actual = solution.buildTree(inorder, postorder);
        assertTrue(isSameTree(expected, actual));
    }

    @Test
    public void testSingleElement() {
        // 單一節點情境
        int[] inorder = {1};
        int[] postorder = {1};

        // 預期建構結果：
        //    1
        TreeNode expected = TreeNode.of(1);
        TreeNode actual = solution.buildTree(inorder, postorder);
        assertTrue(isSameTree(expected, actual));
    }

    @Test
    public void testEmptyTree() {
        // 空陣列 → 應返回 null
        int[] inorder = {};
        int[] postorder = {};

        TreeNode expected = null;
        TreeNode actual = solution.buildTree(inorder, postorder);
        assertEquals(expected, actual);
    }

    @Test
    public void testLeftSkewedTree() {
        // 只有左子樹（完全左傾）
        int[] inorder =   {4, 3, 2, 1};
        int[] postorder = {4, 3, 2, 1};

        // 預期建構結果：
        //      1
        //     /
        //    2
        //   /
        //  3
        // /
        //4
        TreeNode expected = TreeNode.of(1, 2, null, 3, null, 4, null);
        TreeNode actual = solution.buildTree(inorder, postorder);
        assertTrue(isSameTree(expected, actual));
    }

    @Test
    public void testRightSkewedTree() {
        // 只有右子樹（完全右傾）
        int[] inorder =   {1, 2, 3, 4};
        int[] postorder = {4, 3, 2, 1};

        // 預期建構結果：
        // 1
        //  \
        //   2
        //    \
        //     3
        //      \
        //       4
        TreeNode expected = TreeNode.of(1, null, 2, null, 3, null, 4);
        TreeNode actual = solution.buildTree(inorder, postorder);
        assertTrue(isSameTree(expected, actual));
    }

    /**
     * 遞迴比較兩棵二元樹的結構與數值是否一致
     */
    private boolean isSameTree(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        if (a.val != b.val) return false;
        return isSameTree(a.left, b.left) && isSameTree(a.right, b.right);
    }

}