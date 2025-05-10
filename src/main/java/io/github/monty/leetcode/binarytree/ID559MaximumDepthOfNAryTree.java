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
     * 思路：
     * - 若為空節點，深度為 0
     * - 若為葉節點（無子節點），深度為 1
     * - 否則遞迴計算所有子節點的最大深度，取其中最大者 + 1
     * Time Complexity: O(n)，每個節點只遍歷一次
     * Space Complexity: O(h)，h 為樹高（遞迴堆疊深度）
     */
    public int maxDepth(Node root) {
        if (root == null) return 0;
        if (root.children == null || root.children.isEmpty()) return 1;

        int max = 0;
        for (Node child: root.children) {
            max = Math.max(max, maxDepth(child));
        }
        return max + 1; // 最大深度加上這層高度
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