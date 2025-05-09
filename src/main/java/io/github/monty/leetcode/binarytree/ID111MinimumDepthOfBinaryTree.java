package io.github.monty.leetcode.binarytree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode #111:
 * <a href="https://leetcode.com/problems/minimum-depth-of-binary-tree">Minimum Depth of Binary Tree</a>
 *
 * @author Monty.Tsai
 * @since 2025-05-09 10:52:02
 */
public class ID111MinimumDepthOfBinaryTree {

    /**
     * 解法一：BFS（層序遍歷）
     * 一旦遇到第一個葉節點，立即回傳該深度，為最小深度。
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int depth = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                // 若為葉節點，直接回傳當前深度
                if (node.left == null && node.right == null) {
                    return depth;
                }

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            depth++;  // 每跑完一層，深度+1
        }

        return depth;
    }

    /**
     * 解法二：DFS 遞迴
     * 若某一子樹為 null，不可取其深度為 0，需特殊處理。
     */
    public int minDepthDfs(TreeNode root) {
        if (root == null) return 0;

        // 左右子樹皆為 null，表示是葉節點
        if (root.left == null && root.right == null) return 1;

        // 若某子樹為 null，則只能走另一邊（不能取 min(0, x)）
        if (root.left == null) return minDepth(root.right) + 1;
        if (root.right == null) return minDepth(root.left) + 1;

        // 左右子樹皆不為 null，取最小深度
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

}