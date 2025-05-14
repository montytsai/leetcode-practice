package io.github.monty.leetcode.binarytree;

/**
 * LeetCode #700: 
 * <a href="https://leetcode.com/problems/search-in-a-binary-search-tree">
 *   Search in a Binary Search Tree
 * </a>
 * 給定一棵二元搜尋樹 (BST) 的根節點 root 和一個整數 val，
 * 回傳值等於 val 的節點所代表的子樹根節點；若找不到，則回傳 null。
 *
 * @author Monty.Tsai
 * @since 2025-05-14
 */
public class ID700SearchInABinarySearchTree {

    /**
     * 解法：迴圈搜尋（二元搜尋樹特性）
     * - 若 val 小於當前節點值，往左子樹搜尋
     * - 若 val 大於當前節點值，往右子樹搜尋
     * - 若相等，代表已找到該節點，回傳即可
     * 時間複雜度：O(h)，h 為樹高，最壞為 O(n)（非平衡樹）
     * 空間複雜度：O(1)，使用迴圈不需額外堆疊空間
     */
    public TreeNode searchBST(TreeNode root, int val) {
        while (root != null) {
            if (val == root.val) return root;

            if (val < root.val) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return null;
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