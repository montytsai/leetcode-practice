package io.github.monty.leetcode.binarytree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ID116PopulatingNextRightPointersInEachNodeTest {

    ID116PopulatingNextRightPointersInEachNode solution = new ID116PopulatingNextRightPointersInEachNode();

    @Test
    void testConnect_case1() {
        // 建立一棵完美二元樹：
        //
        //        1
        //      /   \
        //     2     3
        //    / \   / \
        //   4  5  6   7

        ID116PopulatingNextRightPointersInEachNode.Node n4 = new ID116PopulatingNextRightPointersInEachNode.Node(4);
        ID116PopulatingNextRightPointersInEachNode.Node n5 = new ID116PopulatingNextRightPointersInEachNode.Node(5);
        ID116PopulatingNextRightPointersInEachNode.Node n6 = new ID116PopulatingNextRightPointersInEachNode.Node(6);
        ID116PopulatingNextRightPointersInEachNode.Node n7 = new ID116PopulatingNextRightPointersInEachNode.Node(7);

        ID116PopulatingNextRightPointersInEachNode.Node n2 = new ID116PopulatingNextRightPointersInEachNode.Node(2);
        ID116PopulatingNextRightPointersInEachNode.Node n3 = new ID116PopulatingNextRightPointersInEachNode.Node(3);

        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;

        ID116PopulatingNextRightPointersInEachNode.Node root = new ID116PopulatingNextRightPointersInEachNode.Node(1);
        root.left = n2;
        root.right = n3;

        ID116PopulatingNextRightPointersInEachNode.Node result = solution.connect(root);

        // 驗證每層節點的 next 指標
        assertEquals(3, result.left.next.val);                      // 2 -> 3
        assertEquals(5, result.left.left.next.val);                 // 4 -> 5
        assertEquals(6, result.left.left.next.next.val);           // 5 -> 6
        assertEquals(7, result.left.left.next.next.next.val);      // 6 -> 7
        assertNull(result.left.left.next.next.next.next);          // 7 -> null
    }

    @Test
    void testConnect_singleNode() {
        ID116PopulatingNextRightPointersInEachNode.Node root =
                new ID116PopulatingNextRightPointersInEachNode.Node(1);

        ID116PopulatingNextRightPointersInEachNode.Node result = solution.connect(root);
        assertNull(result.next);
    }

    @Test
    void testConnect_nullRoot() {
        ID116PopulatingNextRightPointersInEachNode.Node result = solution.connect(null);
        assertNull(result);
    }

}