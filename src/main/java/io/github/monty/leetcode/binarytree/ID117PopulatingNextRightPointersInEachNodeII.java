package io.github.monty.leetcode.binarytree;

import java.util.List;

/**
 * LeetCode #117:
 * <a href="https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii">Populating Next Right Pointers in Each Node II</a>
 *
 * @author Monty.Tsai
 * @since 2025-05-09 10:51:48
 */
public class ID117PopulatingNextRightPointersInEachNodeII {

    public Node connect(Node root) {
        return null;
    }

    /**
     * LeetCode 題目自帶類別
     * 巢狀類別，避免與其他題目的 Node 衝突
     */
    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

}