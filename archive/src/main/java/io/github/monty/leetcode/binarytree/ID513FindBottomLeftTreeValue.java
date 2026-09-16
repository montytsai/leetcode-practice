package io.github.monty.leetcode.binarytree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode #513: 
 * <a href="https://leetcode.com/problems/find-bottom-left-tree-value">
 *   Find Bottom Left Tree Value
 * </a>
 *
 * @author Monty.Tsai
 * @since 2025-05-12 16:53:42
 */
public class ID513FindBottomLeftTreeValue {

    /**
     * 給定一個二元樹，在樹的最後一行找到最左邊的值。
     */
    public int findBottomLeftValue(TreeNode root) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        int leftValue = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            leftValue = queue.peek().val; // 這層最左邊的元素

            while (size-- > 0) { // 把這層的元素吐光
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }

        return leftValue;
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