package io.github.monty.leetcode.binarytree;

import io.github.monty.leetcode.binarytree.ID117PopulatingNextRightPointersInEachNodeII.Node;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ID117PopulatingNextRightPointersInEachNodeIITest {

    private final ID117PopulatingNextRightPointersInEachNodeII solution = new ID117PopulatingNextRightPointersInEachNodeII();

    @Test
    void testConnect_singleNode() {
        Node root = new Node(1);

        Node result = solution.connect(root);

        assertNotNull(result);
        assertNull(result.next, "單節點的 next 應為 null");
    }

    @Test
    void testConnect_completeBinaryTree() {
        /*
         * 測試完整二元樹：
         *         1
         *       /   \
         *      2     3
         *     / \     \
         *    4   5     7
         */
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node7 = new Node(7);
        Node node2 = new Node(2, node4, node5, null);
        Node node3 = new Node(3, null, node7, null);
        Node root = new Node(1, node2, node3, null);

        Node result = solution.connect(root);

        // 驗證每個節點的 next 指標是否正確
        assertNull(result.next);
        assertEquals(3, result.left.next.val);        // 2 → 3
        assertNull(result.right.next);                // 3 → null

        assertEquals(5, result.left.left.next.val);   // 4 → 5
        assertEquals(7, result.left.right.next.val);  // 5 → 7
        assertNull(result.right.right.next);          // 7 → null
    }

    @Test
    void testConnect_nullTree() {
        // 空樹測試
        Node result = solution.connect(null);
        assertNull(result);
    }

}