package io.github.monty.leetcode.binarytree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ID450DeleteNodeInABSTTest {

    private ID450DeleteNodeInABST solution;

    @BeforeEach
    public void setUp() {
        solution = new ID450DeleteNodeInABST();
    }

    @Test
    public void testDeleteLeafNode() {
        // 刪除葉節點 2
        TreeNode root = TreeNode.of(5, 3, 6, 2, 4, null, 7);
        TreeNode expected = TreeNode.of(5, 3, 6, null, 4, null, 7);

        /*
         * 原始樹：
         *        5
         *       / \
         *      3   6
         *     / \   \
         *    2   4   7
         *
         * 刪除節點 2 後：
         *        5
         *       / \
         *      3   6
         *       \   \
         *        4   7
         */

        TreeNode result = solution.deleteNode(root, 2);
        assertTrue(TreeNode.isSameTree(expected, result));
    }

    @Test
    public void testDeleteNodeWithOneChild() {
        // 刪除只有右子節點的節點 3
        TreeNode root = TreeNode.of(5, 3, 6, null, 4, null, 7);
        TreeNode expected = TreeNode.of(5, 4, 6, null, null, null, 7);

        /*
         * 原始樹：
         *        5
         *       / \
         *      3   6
         *       \   \
         *        4   7
         *
         * 刪除節點 3 後（只有右子節點）：
         *        5
         *       / \
         *      4   6
         *           \
         *            7
         */

        TreeNode result = solution.deleteNode(root, 3);
        assertTrue(TreeNode.isSameTree(expected, result));
    }

    @Test
    public void testDeleteNodeWithTwoChildren() {
        // 刪除有兩個子節點的節點 3
        TreeNode root = TreeNode.of(5, 3, 6, 2, 4, null, 7);
        TreeNode expected = TreeNode.of(5, 4, 6, 2, null, null, 7);

        /*
         * 原始樹：
         *        5
         *       / \
         *      3   6
         *     / \   \
         *    2   4   7
         *
         * 刪除節點 3 後（有兩個子節點，使用右子樹最小節點替代）：
         *        5
         *       / \
         *      4   6
         *     /     \
         *    2       7
         */

        TreeNode result = solution.deleteNode(root, 3);
        assertTrue(TreeNode.isSameTree(expected, result));
    }

    @Test
    public void testDeleteRootNode() {
        // 刪除根節點 5（有左右子節點）
        TreeNode root = TreeNode.of(5, 3, 6, 2, 4, null, 7);
        TreeNode expected = TreeNode.of(6, 3, 7, 2, 4);

        /*
         * 原始樹：
         *        5
         *       / \
         *      3   6
         *     / \   \
         *    2   4   7
         *
         * 刪除根節點 5 後：
         *        6
         *       / \
         *      3   7
         *     / \
         *    2   4
         */

        TreeNode result = solution.deleteNode(root, 5);
        assertTrue(TreeNode.isSameTree(expected, result));
    }

    @Test
    public void testDeleteOnlyNode() {
        // 刪除唯一節點（根節點）
        TreeNode root = TreeNode.of(1);
        TreeNode expected = null;

        /*
         * 原始樹：
         *    1
         *
         * 刪除節點 1 後：
         *    null
         */

        TreeNode result = solution.deleteNode(root, 1);
        assertTrue(TreeNode.isSameTree(expected, result));
    }

    @Test
    public void testDeleteNonExistentNode() {
        // 嘗試刪除不存在的節點，原樹應維持不變
        TreeNode root = TreeNode.of(2, 1, 3);
        TreeNode expected = TreeNode.of(2, 1, 3);

        /*
         * 原始樹：
         *      2
         *     / \
         *    1   3
         *
         * 嘗試刪除節點 4（不存在），樹維持原樣。
         */

        TreeNode result = solution.deleteNode(root, 4);
        assertTrue(TreeNode.isSameTree(expected, result));
    }

}