package io.github.monty.leetcode.binarytree;

/**
 * LeetCode #701:
 * <a href="https://leetcode.com/problems/insert-into-a-binary-search-tree">
 *   Insert into a Binary Search Tree
 * </a>
 *
 * @author Monty.Tsai
 * @since 2025-05-21
 */
public class ID701InsertIntoABinarySearchTree {

    /**
     * 解法一：遞迴插入
     * 思路:
     * - 從根節點出發遞迴遍歷，如果 `val` 小於節點值，遞迴左子樹，否則右子樹。
     * - 若遇到空節點，表示可以插入。
     * 時間複雜度: O(h)，其中 h 為樹的高度（最壞 O(n)，平均 O(log n)）
     * 空間複雜度: O(h)，遞迴呼叫堆疊使用
     */
    public TreeNode insertIntoBSTRecursive(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);

        if (root.val > val)
            root.left = insertIntoBST(root.left, val);
        else
            root.right = insertIntoBST(root.right, val);
        return root;
    }

    /**
     * 解法二：迭代插入
     * 時間複雜度: O(h)，其中 h 為樹的高度（最壞 O(n)，平均 O(log n)）
     * 空間複雜度: O(1)
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);

        TreeNode curr = root;
        TreeNode parent = null;

        // 找到要插入的位置
        while (curr != null) {
            parent = curr;
            if (curr.val > val) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }

        // 將新節點插入父節點的正確子節點位置
        TreeNode insertNode = new TreeNode(val);
        if (parent.val > val) parent.left = insertNode;
        else parent.right = insertNode;

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
