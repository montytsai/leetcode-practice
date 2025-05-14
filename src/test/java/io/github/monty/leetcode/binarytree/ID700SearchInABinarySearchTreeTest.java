package io.github.monty.leetcode.binarytree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ID700SearchInABinarySearchTreeTest {

    private ID700SearchInABinarySearchTree solution;

    @BeforeEach
    void setUp() {
        solution = new ID700SearchInABinarySearchTree();
    }

    @Test
    @DisplayName("BST 範例一：查找值為 2 的子樹")
    void testExample1() {
        /*
         * 原始 BST 結構：
         *
         *         4
         *        / \
         *       2   7
         *      / \
         *     1   3
         *
         * 查找值為 2，預期回傳子樹：
         *
         *       2
         *      / \
         *     1   3
         */
        TreeNode root = TreeNode.of(4, 2, 7, 1, 3);
        TreeNode expected = TreeNode.of(2, 1, 3);

        TreeNode result = solution.searchBST(root, 2);

        assertTrue(isSameTree(result, expected));
    }

    @Test
    @DisplayName("BST 範例二：查找值為 5，樹中不存在")
    void testValueNotFound() {
        /*
         * 原始 BST 結構：
         *
         *         4
         *        / \
         *       2   7
         *      / \
         *     1   3
         *
         * 查找值為 5，預期找不到，回傳 null
         */
        TreeNode root = TreeNode.of(4, 2, 7, 1, 3);

        TreeNode result = solution.searchBST(root, 5);

        assertNull(result);
    }

    @Test
    @DisplayName("BST 為空：應回傳 null")
    void testNullTree() {
        TreeNode result = solution.searchBST(null, 3);
        assertNull(result);
    }

    @Test
    @DisplayName("BST 只有一個節點，且為目標值")
    void testSingleNodeTreeFound() {
        TreeNode root = TreeNode.of(1);
        TreeNode expected = TreeNode.of(1);

        TreeNode result = solution.searchBST(root, 1);

        assertTrue(isSameTree(result, expected));
    }

    @Test
    @DisplayName("BST 只有一個節點，但非目標值")
    void testSingleNodeTreeNotFound() {
        TreeNode root = TreeNode.of(1);

        TreeNode result = solution.searchBST(root, 2);

        assertNull(result);
    }

    /**
     * 判斷兩棵二元樹是否相同（遞迴比對）
     */
    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        return p.val == q.val
                && isSameTree(p.left, q.left)
                && isSameTree(p.right, q.right);
    }

}