package io.github.monty.leetcode.binarytree;

/**
 * LeetCode #112:
 * <a href="https://leetcode.com/problems/path-sum">
 * Path Sum
 * </a>
 *
 * @author Monty.Tsai
 * @since 2025-05-12 16:54:01
 */
public class ID112PathSum {

    /**
     * 解法： DFS 遞迴（以目標值遞減方式）
     * Time Complexity: O(n) — 每個節點最多走訪一次
     * Space Complexity: O(h) — 遞迴深度為樹高，最壞為 O(n)
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;

        // 中：處理當前節點，減去節點值
        targetSum -= root.val; // 中

        // 若為 leaf，返回是否為合法路徑
        if (root.left == null && root.right == null) {
            return 0 == targetSum;
        }

        // 遞迴左右節點
        boolean left = hasPathSum(root.left, targetSum); // 左
        boolean right = hasPathSum(root.right, targetSum); // 右
        return left || right;
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