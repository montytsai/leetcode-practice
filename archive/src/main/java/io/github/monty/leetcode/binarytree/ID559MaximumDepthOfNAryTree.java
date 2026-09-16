package io.github.monty.leetcode.binarytree;

import java.util.List;

/**
 * LeetCode #559:
 * <a href="https://leetcode.com/problems/maximum-depth-of-n-ary-tree">
 * Maximum Depth of N-ary Tree
 * </a>
 * 求 N 元樹的最大深度
 *
 * @author Monty.Tsai
 * @since 2025-05-10 10:23:04
 */
public class ID559MaximumDepthOfNAryTree {

    /**
     * 解法：DFS 遞迴（後序遍歷）
     * <p>
     * 思路：
     * - 本題為 N-ary Tree 的最大「深度」（Maximum Depth）
     * - 而「根節點的高度」即為「整棵樹的最大深度」
     * - 可採用後序遍歷（自下而上）計算子節點高度，再加上自身節點高度（+1）
     * <p>
     * 遞迴規則：
     * - 若為 null，高度為 0（空樹）
     * - 若為葉節點，高度為 1（只有自己這一層）
     * - 否則遞迴所有子節點，取最大高度 + 1
     * <p>
     * Time Complexity: O(n) - 每個節點只被訪問一次
     * Space Complexity: O(h) - 遞迴深度最多為樹高（最壞情況 O(n)）
     */
    public int maxDepth(Node root) {
        if (root == null) return 0;
        if (root.children == null || root.children.isEmpty()) return 1;

        int max = 0;
        for (Node child: root.children) {
            max = Math.max(max, maxDepth(child));
        }
        return max + 1; // 子節點的最大高度加上這層高度
    }

    /**
     * LeetCode 題目自帶類別
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