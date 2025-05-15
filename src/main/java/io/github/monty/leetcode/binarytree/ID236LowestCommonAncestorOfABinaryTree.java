package io.github.monty.leetcode.binarytree;

/**
 * LeetCode #236: 
 * <a href="https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree">
 *   Lowest Common Ancestor of a Binary Tree
 * </a>
 * 給一棵二元樹的根節點 root，以及樹中的兩個節點 p 和 q。找出 p 和 q 的「最近公共祖先」（Lowest Common Ancestor），並回傳該節點。
 * 最近公共祖先（LCA）：
 * - 對於 p 和 q 這兩個節點，在整棵樹中，距離 p 和 q 最近、同時是他們祖先的節點。
 * - 注意：「祖先」包含節點本身。
 *
 * @author Monty.Tsai
 * @since 2025-05-15
 */
public class ID236LowestCommonAncestorOfABinaryTree {

    /**
     * 解法: DFS 後序遞迴方式，自底向上尋找
     * 涵蓋兩種情況：
     * - 情況一：p 和 q 分別位於左右子樹
     * - 情況二：p 或 q 是另一個節點的祖先
     * 複雜度：
     * - 時間：O(n) — 每個節點只會被訪問一次
     * - 空間：O(h) — 遞迴呼叫堆疊的最大深度為樹高 h，最差為 O(n)
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        // 情況二：若當前節點為 p/q 本身，直接回傳（可能是 LCA 或需繼續向上層確認）
        if (root == p || root == q) return root;

        // 從左右子樹遞迴尋找 p 與 q
        TreeNode left = lowestCommonAncestor(root.left, p, q); // 左
        TreeNode right = lowestCommonAncestor(root.right, p, q); // 右

        // 情況一：如果左右子樹都找到節點，表示 p 和 q 分別在兩側，當前節點即為 LCA
        if (left != null && right != null) return root;

        // 若只有一側找到，則繼續往上回傳該節點（此節點可能為 p or q or 某個祖先）
        return left != null ? left : right;
    }

}

/*
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */