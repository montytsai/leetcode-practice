package io.github.monty.leetcode.binarytree;

/**
 * LeetCode #110:
 * <a href="https://leetcode.com/problems/balanced-binary-tree">
 * Balanced Binary Tree
 * </a>
 * 給定二元樹，確定它是否具有高度平衡。（每個節點的左右兩個子樹的高度差的絕對值不超過 1。）
 *
 * @author Monty.Tsai
 * @since 2025-05-10 10:24:18
 */
public class ID110BalancedBinaryTree {

    /**
     * 解法一：DFS 後序遞迴 + 剪枝
     * 思路：
     * - 採後序遍歷（先算子節點高度），可自底向上逐層計算。
     * - 若任一節點的左右子樹高度差大於 1，則該樹不平衡。
     * - 剪枝：只要發現一個節點不平衡，就回傳 -1 終止遞迴，避免不必要計算。
     * 複雜度：
     * - Time: O(n)，每個節點僅被訪問一次。
     * - Space: O(h)，h 為樹高（遞迴堆疊深度），最壞為 O(n)。
     */
    public boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1;
    }

    /**
     * 後序遞迴計算每個節點的高度。
     * 若某節點的左右子樹高度差 > 1，則視為不平衡，回傳 -1 作為錯誤標記。
     *
     * @param node 當前節點
     * @return 若平衡則回傳節點高度，否則回傳 -1
     */
    private int getHeight(TreeNode node) {
        if (node == null) return 0; // 空節點，高度為 0

        int leftHeight = getHeight(node.left); // 左子樹高度
        if (leftHeight == -1) return -1;

        int rightHeight = getHeight(node.right); // 右子樹高度
        if (rightHeight == -1) return -1;

        // 若當前節點左右子樹高度差超過 1，則樹不平衡，回傳 -1 標記
        if (Math.abs(leftHeight - rightHeight) > 1) return -1;

        // 當前節點的高度 = 左右子樹高度 + 自己這層高度(1)
        return 1 + Math.max(leftHeight, rightHeight);
    }

    /**
     * 解法 Z：原始版本（Top-down）
     * 思路：
     * - 先遞迴判斷左右子樹是否平衡，再比較左右子樹高度差是否超過 1。
     * 缺點：
     * - 每次都重新計算子樹高度，導致重複計算。最壞情況下時間複雜度為 O(n^2)
     * - 僅作為錯誤示範與效率對比用途
     * Time: O(n^2), Space: O(h)
     */
    public boolean isBalancedZ(TreeNode root) {
        if (root == null) return true;

        // 分別計算左右子樹高度
        int leftHeight = getHeightZ(root.left);
        int rightHeight = getHeightZ(root.right);

        // 判斷當前節點是否平衡，並遞迴檢查左右子樹是否平衡
        return (Math.abs(leftHeight - rightHeight) <= 1)
                && isBalancedZ(root.left)
                && isBalancedZ(root.right);
    }

    /**
     * Top-down 高度計算函數。
     * （對每個節點都會完整遞迴左右子樹，造成重複計算。）
     */
    private int getHeightZ(TreeNode node) {
        if (node == null) return 0;

        int leftHeight = node.left != null ? getHeightZ(node.left) : 0;
        int rightHeight = node.right != null ? getHeightZ(node.right) : 0;

        return Math.max(leftHeight, rightHeight) + 1;
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