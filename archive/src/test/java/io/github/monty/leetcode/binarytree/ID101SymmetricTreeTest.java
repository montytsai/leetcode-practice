package io.github.monty.leetcode.binarytree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ID101SymmetricTreeTest {

    private final ID101SymmetricTree solution = new ID101SymmetricTree();

    /**
     * 測試空樹（視為對稱）
     */
    @Test
    void testEmptyTree() {
        TreeNode root = null;
        assertTrue(solution.isSymmetric(root));
    }

    /**
     * 測試單一節點（根節點，無左右子樹）
     */
    @Test
    void testSingleNodeTree() {
        TreeNode root = new TreeNode(1);
        assertTrue(solution.isSymmetric(root));
    }

    /**
     * 測試對稱二元樹
     * <p>
     * 1
     * / \
     * 2   2
     * / \ / \
     * 3  4 4  3
     */
    @Test
    void testSymmetricTree() {
        TreeNode root = new TreeNode(1,
                new TreeNode(2, new TreeNode(3), new TreeNode(4)),
                new TreeNode(2, new TreeNode(4), new TreeNode(3))
        );
        assertTrue(solution.isSymmetric(root));
    }

    /**
     * 測試不對稱二元樹：值不同
     * <p>
     * 1
     * / \
     * 2   2
     * \   \
     * 3    3
     */
    @Test
    void testAsymmetricTree_ValueMismatch() {
        TreeNode root = new TreeNode(1,
                new TreeNode(2, null, new TreeNode(3)),
                new TreeNode(2, null, new TreeNode(3))
        );
        assertFalse(solution.isSymmetric(root));
    }

    /**
     * 測試對稱有null二元樹
     * <p>
     * 1
     * / \
     * 2   2
     * /     \
     * 3       3
     */
    @Test
    void testAsymmetricTree_StructureMismatch() {
        TreeNode root = new TreeNode(1,
                new TreeNode(2, new TreeNode(3), null),
                new TreeNode(2, null, new TreeNode(3))
        );
        assertTrue(solution.isSymmetric(root));
    }

}