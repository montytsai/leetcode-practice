package io.github.monty.leetcode.binarytree;

/**
 * LeetCode #108: 
 * <a href="https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree">
 *   Convert Sorted Array to Binary Search Tree
 * </a>
 *
 * @author Monty.Tsai
 * @since 2025-05-26
 */
public class ID108ConvertSortedArrayToBinarySearchTree {

    /**
     * 解法：Divide & Conquer（遞迴建樹）
     * 思路:
     * - 每次取中間值作為根節點，左半段遞迴建左子樹，右半段遞迴建右子樹
     * - 確保左右子樹高度差最多為 1，因此為高度平衡
     * 複雜度:
     * - 時間複雜度：O(n) - 每個元素造訪一次
     * - 空間複雜度：O(log n) - 遞迴深度（平均情況下）
     *   -> 最壞情況為 O(n)，取決於呼叫堆疊深度（如全部向一側偏斜時）
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return bst(nums, 0, nums.length - 1);
    }

    private TreeNode bst(int[] nums, int start, int end) {
        if (start > end) return null;

        int rootIdx = (start + end) / 2;
        TreeNode root = new TreeNode(nums[rootIdx]);

        root.left = bst(nums, start, rootIdx - 1);
        root.right = bst(nums, rootIdx + 1, end);
        return root;
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
