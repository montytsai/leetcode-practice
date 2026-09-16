package io.github.monty.leetcode.binarytree;

/**
 * LeetCode #617: 
 * <a href="https://leetcode.com/problems/merge-two-binary-trees">
 *   Merge Two Binary Trees
 * </a>
 * 合併兩棵二元樹，若兩節點皆存在則相加，否則取存在者。
 *
 * @author Monty.Tsai
 * @since 2025-05-14
 */
public class ID617MergeTwoBinaryTrees {

    /**
     * 解法: 使用 DFS 遞迴實作，自頂向下處理
     * Time: O(n)
     * Space: O(h)，最差為 O(n)，最好的平衡樹為 O(log n)，h 為樹高
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        // 如果某邊為 null，直接回傳另一邊
        if (root1 == null) return root2;
        if (root2 == null) return root1;

        root1.val += root2.val; // 中: 若兩節點皆不為 null，合併數值
        root1.left = mergeTrees(root1.left, root2.left); // 左
        root1.right = mergeTrees(root1.right, root2.right); // 右
        return root1;
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