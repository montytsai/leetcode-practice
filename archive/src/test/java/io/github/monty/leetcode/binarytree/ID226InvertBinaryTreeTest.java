package io.github.monty.leetcode.binarytree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 單元測試：反轉二元樹（Invert Binary Tree）
 * 題目編號：#226
 * 測試使用 JUnit 5，驗證樹結構是否正確反轉。
 */
class ID226InvertBinaryTreeTest {

    private final ID226InvertBinaryTree solution = new ID226InvertBinaryTree();

    @Test
    void testInvertTree_basicCase() {
        // 測試用例：一般二元樹
        // 建立輸入樹：
        //     4
        //    / \
        //   2   7
        //  / \ / \
        // 1  3 6  9
        TreeNode root = new TreeNode(4,
                new TreeNode(2,
                        new TreeNode(1),
                        new TreeNode(3)
                ),
                new TreeNode(7,
                        new TreeNode(6),
                        new TreeNode(9)
                )
        );

        // 預期結果（反轉後）：
        //     4
        //    / \
        //   7   2
        //  / \ / \
        // 9  6 3  1
        TreeNode expected = new TreeNode(4,
                new TreeNode(7,
                        new TreeNode(9),
                        new TreeNode(6)
                ),
                new TreeNode(2,
                        new TreeNode(3),
                        new TreeNode(1)
                )
        );

        TreeNode result = solution.invertTree(root);

        assertTrue(isSameTree(expected, result), "反轉後的二元樹不正確");
    }

    @Test
    void testInvertTree_emptyTree() {
        // 測試用例：空樹（null）
        assertNull(solution.invertTree(null), "空樹應返回 null");
    }

    @Test
    void testInvertTree_singleNode() {
        // 測試用例：只有一個節點
        TreeNode root = new TreeNode(1);
        TreeNode result = solution.invertTree(root);
        assertEquals(1, result.val);
        assertNull(result.left);
        assertNull(result.right);
    }

    /**
     * 工具方法：判斷兩棵二元樹是否相同
     */
    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

}