package io.github.monty.leetcode.binarytree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode #117:
 * <a href="https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii">Populating Next Right Pointers in Each Node II</a>
 *
 * @author Monty.Tsai
 * @since 2025-05-09 10:51:48
 */
public class ID117PopulatingNextRightPointersInEachNodeII {

    public Node connect(Node root) {
        if (root == null) return null;

        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node prev = null; // 紀錄上一個節點

            int size = queue.size();
            while (size-- > 0) {
                Node curr = queue.poll();
                if (prev != null) prev.next = curr; // 將上一個節點的 next 指向當前節點
                prev = curr;

                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
        }

        return root;
    }

    /**
     * LeetCode 題目自帶類別
     * 巢狀類別，避免與其他題目的 Node 衝突
     */
    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

}