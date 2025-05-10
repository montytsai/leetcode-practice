package io.github.monty.leetcode.binarytree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ID222CountCompleteTreeNodesTest {

    private ID222CountCompleteTreeNodes solution;

    /**
     * 測試前初始化解法實例，避免在每個測試中重複 new。
     */
    @BeforeEach
    public void setUp() {
        solution = new ID222CountCompleteTreeNodes();
    }

    @Test
    @DisplayName("範例一：完整二元樹 [1,2,3,4,5,6]，節點數應為 6")
    public void testExample1() {
        TreeNode root = TreeNode.of(1, 2, 3, 4, 5, 6);
        int result = solution.countNodes(root);
        assertEquals(6, result);
    }

    @Test
    @DisplayName("空樹：傳入 null，應回傳 0")
    public void testEmptyTree() {
        TreeNode root = null;
        int result = solution.countNodes(root);
        assertEquals(0, result);
    }

    @Test
    @DisplayName("只有根節點：樹為 [1]，節點數應為 1")
    public void testSingleNode() {
        TreeNode root = TreeNode.of(1);
        int result = solution.countNodes(root);
        assertEquals(1, result);
    }

    @Test
    @DisplayName("完整樹但缺右下葉節點：樹為 [1,2,3,4,5]，節點數應為 5")
    public void testMissingRightLeaf() {
        TreeNode root = TreeNode.of(1, 2, 3, 4, 5);
        int result = solution.countNodes(root);
        assertEquals(5, result);
    }

    @Test
    @DisplayName("左子樹完整但右子樹缺葉節點：樹為 [1,2,3,4,5,6]，節點數應為 6")
    public void testIncompleteRightSubtree() {
        TreeNode root = TreeNode.of(1, 2, 3, 4, 5, 6);
        int result = solution.countNodes(root);
        assertEquals(6, result);
    }

    @Test
    @DisplayName("完全二元樹：三層滿節點樹 [1,2,3,4,5,6,7]，節點數應為 7")
    public void testFullTree() {
        TreeNode root = TreeNode.of(1, 2, 3, 4, 5, 6, 7);
        int result = solution.countNodes(root);
        assertEquals(7, result);
    }

}