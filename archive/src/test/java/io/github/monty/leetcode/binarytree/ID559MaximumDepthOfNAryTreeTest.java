package io.github.monty.leetcode.binarytree;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.monty.leetcode.binarytree.ID559MaximumDepthOfNAryTree.Node;

/**
 * 單元測試：#559 Maximum Depth of N-ary Tree
 * <p>
 * 測試最大深度邏輯是否正確：
 * - 空樹應為深度 0
 * - 僅有 root 的樹應為深度 1
 * - 多層巢狀結構應正確計算最大深度
 */
class ID559MaximumDepthOfNAryTreeTest {

    private final ID559MaximumDepthOfNAryTree solution = new ID559MaximumDepthOfNAryTree();

    @Test
    void testEmptyTree() {
        // 空樹，預期深度為 0
        Node root = null;
        assertEquals(0, solution.maxDepth(root));
    }

    @Test
    void testSingleNode() {
        // 僅有 root 節點，深度應為 1
        Node root = new Node(1);
        assertEquals(1, solution.maxDepth(root));
    }

    @Test
    void testTwoLevelTree() {
        //       1
        //     / | \
        //    2  3  4
        Node child1 = new Node(2);
        Node child2 = new Node(3);
        Node child3 = new Node(4);
        Node root = new Node(1, Arrays.asList(child1, child2, child3));

        assertEquals(2, solution.maxDepth(root));
    }

    @Test
    void testThreeLevelTree() {
        //       1
        //     / | \
        //    2  3  4
        //       |
        //       5
        //       |
        //       6
        Node child5 = new Node(6);
        Node child4 = new Node(5, Collections.singletonList(child5));
        Node child2 = new Node(3, Collections.singletonList(child4));
        Node child1 = new Node(2);
        Node child3 = new Node(4);
        Node root = new Node(1, Arrays.asList(child1, child2, child3));

        // 預期深度：1(root) → 3 → 5 → 6 = 4 層
        assertEquals(4, solution.maxDepth(root));
    }

}