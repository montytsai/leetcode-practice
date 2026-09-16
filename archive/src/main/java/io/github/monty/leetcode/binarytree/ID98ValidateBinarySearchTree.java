package io.github.monty.leetcode.binarytree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode #98:
 * <a href="https://leetcode.com/problems/validate-binary-search-tree">
 * Validate Binary Search Tree
 * </a>
 *
 * @author Monty.Tsai
 * @since 2025-05-14
 */
public class ID98ValidateBinarySearchTree {

    /**
     * 解法一：遞迴 + 上下界範圍限制
     * 思路：
     * - 對每個節點遞迴檢查：
     * - 值必須嚴格大於左子樹的最大值，且小於右子樹的最小值。
     * - 每層傳入一個 [min, max] 區間限制，節點值必須落在該區間內。
     * - 根據 BST 定義遞迴檢查每個子樹是否滿足條件。
     * 時間複雜度：O(n)，每個節點最多訪問一次。
     * 空間複雜度：O(h)，遞迴深度為樹高，最壞 O(n)，平均 O(log n)。
     */
    public boolean isValidBST(TreeNode root) {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValid(TreeNode node, long min, long max) {
        if (node == null) return true;

        if (node.val <= min) return false;
        if (node.val >= max) return false;

        return isValid(node.left, min, node.val) && isValid(node.right, node.val, max);
    }

    /**
     * 解法二：中序遍歷（遞迴版）
     * 思路：
     * - 利用 BST 中序遍歷「節點值遞增」的特性。
     * - 遍歷過程中保留上一個節點值 `preVal`，如果出現遞減（node.val <= preVal）就代表不是 BST。
     * 時間複雜度：O(n)
     * 空間複雜度：O(h)，最壞 O(n)，平均 O(log n)
     */
    public boolean isValidBSTInorder(TreeNode root) {
        return inorder(root);
    }

    private long preVal = Long.MIN_VALUE;

    private boolean inorder(TreeNode node) {
        if (node == null) return true;

        // 左
        if (!inorder(node.left)) return false;

        // 中
        if (node.val <= preVal) return false;
        preVal = node.val;

        // 右
        return inorder(node.right);
    }

    /**
     * 解法三：中序遍歷（迭代版）
     * 思路：
     * - 使用 stack 模擬中序遍歷。
     * - 每次從 stack 取出節點時，檢查是否遞增。
     * 時間複雜度：O(n)
     * 空間複雜度：O(h)，stack 深度為樹高
     */
    public boolean isValidBST2(TreeNode root) {
        if (root == null) return true;

        Deque<TreeNode> stack = new ArrayDeque<>();

        TreeNode cur = root;
        TreeNode pre = null;

        while (cur != null || !stack.isEmpty()) {
            // 一路向左
            while (cur != null) {
                stack.push(cur);
                cur = cur.left; // 左
            }

            cur = stack.pop(); // 中
            if (pre != null && pre.val >= cur.val) return false;
            pre = cur;

            cur = cur.right; // 右
        }

        return true;
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