package io.github.monty.leetcode.binarytree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode #404:
 * <a href="https://leetcode.com/problems/sum-of-left-leaves">
 * Sum of Left Leaves
 * </a>
 *
 * @author Monty.Tsai
 * @since 2025-05-12
 */
public class ID404SumOfLeftLeaves {

    /**
     * 使用 DFS（Stack）迭代實作
     *   - 僅在 left 子節點為葉節點時加總
     *   - Time: O(n), Space: O(h)，h 為樹高
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 0;

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        int sum = 0;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            if (node.left != null) {
                // 左邊的葉節點 進行加總
                if (node.left.left == null && node.left.right == null) {
                    sum += node.left.val;
                } else {
                    stack.push(node.left);
                }
            }
            if (node.right != null) stack.push(node.right);

        }
        return sum;
    }

}

/*
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
