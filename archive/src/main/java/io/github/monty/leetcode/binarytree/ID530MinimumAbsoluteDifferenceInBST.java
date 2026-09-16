package io.github.monty.leetcode.binarytree;

/**
 * LeetCode #530: 
 * <a href="https://leetcode.com/problems/minimum-absolute-difference-in-bst">
 *   Minimum Absolute Difference in BST
 * </a>
 * 給一棵所有節點為非負值的二叉搜尋樹，請計算樹中「任意」兩節點的差的絕對值的最小值。
 * 注意是任意，不用相連接。
 *
 * @author Monty.Tsai
 * @since 2025-05-15
 */
public class ID530MinimumAbsoluteDifferenceInBST {

    /**
     * 解法：中序遍歷 + 記錄前一個節點
     * 思路
     * - 中序遍歷 BST，節點值會以遞增順序排列，相鄰節點差值最小
     * - 記錄前一個節點值 `prev`，每次與當前節點值比較，更新 `minDiff`。
     * - 提早剪枝（BST不會有相同值的節點，當差值為 1 時即為最小）。
     * 複雜度
     * - 時間複雜度：O(n)
     * - 空間複雜度：O(h)，h 為樹高
     */
    public int getMinimumDifference(TreeNode root) {
        if (root == null) return 0;
        getMinDiff(root);
        return minDiff;
    }

    // 紀錄前一個走訪的節點值（中序遍歷順序）
    private TreeNode prev;
    // 紀錄目前最小的差值
    private int minDiff = Integer.MAX_VALUE;

    private void getMinDiff(TreeNode curr) {
        if (curr == null) return;
        if (minDiff == 1) return;

        // 左
        getMinDiff(curr.left);

        // 中
        if (prev != null) {
            minDiff = Math.min(minDiff, curr.val - prev.val);
        }
        if (minDiff == 1) return;
        prev = curr;

        // 右
        getMinDiff(curr.right);
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