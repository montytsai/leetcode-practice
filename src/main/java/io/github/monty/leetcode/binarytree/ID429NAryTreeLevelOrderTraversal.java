package io.github.monty.leetcode.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode #429:
 * <a href="https://leetcode.com/problems/n-ary-tree-level-order-traversal">
 * N-ary Tree Level Order Traversal
 * </a>
 * 給定一個 N 叉樹，返回其節點值的層序遍歷。（即從左到右，逐層遍歷）。
 * 樹的序列化輸入是用層序遍歷，每組子節點都由 null 值分隔（參見示例）。
 *
 * @author Monty.Tsai
 * @since 2025-05-09 10:51:26
 */
public class ID429NAryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        // 使用 queue 進行 BFS 層序遍歷
        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();

            // 處理當前層的所有節點
            int size = queue.size();
            while (size-- > 0) {
                Node node = queue.poll();
                level.add(node.val);

                if (node.children != null) {
                    for (Node child : node.children) {
                        if (child != null) {
                            queue.offer(child);
                        }
                    }
                }
            }

            // 將當前層結果加入總結果中
            res.add(level);
        }

        return res;
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

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }
    }

}