package io.github.monty.leetcode.binarytree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ID404SumOfLeftLeavesTest {

    private ID404SumOfLeftLeaves solution;

    @BeforeEach
    void setUp() {
        solution = new ID404SumOfLeftLeaves();
    }

    /**
     * 測試空樹，應返回 0
     */
    @Test
    void testEmptyTree() {
        TreeNode root = null;
        assertEquals(0, solution.sumOfLeftLeaves(root));
    }

    /**
     * 測試僅有根節點，沒有左葉節點，應返回 0
     */
    @Test
    void testSingleNodeTree() {
        TreeNode root = TreeNode.of(1);
        assertEquals(0, solution.sumOfLeftLeaves(root));
    }

    /**
     * 樹結構：
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 左葉節點：9、15
     * 總和：24
     */
    @Test
    void testTreeWithLeftLeaves() {
        TreeNode root = TreeNode.of(3, 9, 20, null, null, 15, 7);
        assertEquals(24, solution.sumOfLeftLeaves(root));
    }

    /**
     * 測試沒有任何左葉節點，應返回 0
     * 結構：
     *    1
     *     \
     *      2
     *       \
     *        3
     */
    @Test
    void testNoLeftLeaves() {
        TreeNode root = TreeNode.of(1, null, 2, null, 3);
        assertEquals(0, solution.sumOfLeftLeaves(root));
    }

    /**
     * 測試所有左節點都是中繼點，不是葉節點
     *    1
     *   /
     *  2
     * /
     *3
     */
    @Test
    void testLeftBranchButNoLeftLeaves() {
        TreeNode root = TreeNode.of(1, 2, null, 3);
        assertEquals(3, solution.sumOfLeftLeaves(root)); // only node 3 is a left leaf
    }

    /**
     *     1
     *    / \
     *   2   3
     *    \
     *     4
     */
    @Test
    void test() {
        TreeNode root = TreeNode.of(1, 2, 3, null, 4, null, null);
        assertEquals(0, solution.sumOfLeftLeaves(root));
    }

}
