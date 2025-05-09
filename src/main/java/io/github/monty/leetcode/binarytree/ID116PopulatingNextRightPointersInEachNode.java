package io.github.monty.leetcode.binarytree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode #116: 填充每個節點的下一個右側節點指針
 * <a href="https://leetcode.com/problems/populating-next-right-pointers-in-each-node">Populating Next Right Pointers in Each Node</a>
 * 給定一個 完美二元樹 ，其所有葉子節點都在同一層，每個父節點都有兩個子節點。
 * 填充它的每個 next 指針，讓這個指針指向其下一個右側節點。如果找不到下一個右側節點，則將 next 指針設定為 NULL。
 * 初始狀態下，所有 next 指針都被設定為 NULL。
 *
 * @author Monty.Tsai
 * @since 2025-05-09 10:51:42
 */
public class ID116PopulatingNextRightPointersInEachNode {

    /**
     * 使用 BFS（層序遍歷）逐層設定節點的 next 指標。
     * 時間複雜度: O(n)，其中 n 為節點數
     * 空間複雜度: O(n)，最壞情況下 queue 需儲存一整層節點
     */
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

            // 該層最後一個節點的 next 預設為 null（不需額外處理）
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