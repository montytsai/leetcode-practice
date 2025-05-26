package io.github.monty.leetcode.binarytree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode #538:
 * <a href="https://leetcode.com/problems/convert-bst-to-greater-tree">
 * Convert BST to Greater Tree
 * </a>
 *
 * @author Monty.Tsai
 * @since 2025-05-23
 */
public class ID538ConvertBstToGreaterTree {

    /**
     * 解法一：Iterative
     * - 採用中序反向遍歷（右 → 中 → 左），使用 Stack 模擬。
     * - 每走訪一個節點，就加上前一節點的累加值。
     * 複雜度:
     * - Time Complexity: O(n)，每個節點走訪一次。
     * - Space Complexity: O(h)，h 為樹高，最壞為 O(n)。
     */
    public TreeNode convertBSTIterative(TreeNode root) {
        if (root == null) return null;

        int pre = 0; // 儲存目前累加的總和
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;

        // 中序反向遍歷：右 → 中 → 左
        while (curr != null || !stack.isEmpty()) {
            // 一路向右
            while (curr != null) {
                stack.push(curr);
                curr = curr.right;
            }

            // 中: 更換成累加數值
            curr = stack.pop();
            curr.val += pre;
            pre = curr.val;

            // 下一個處理左樹
            curr = curr.left;
        }

        return root;
    }

    /**
     * 解法二：Recursive
     * - 也採用右 → 中 → 左的 DFS 方式。
     * - 使用 class 層級變數 prev 儲存上一個處理的節點。
     * 複雜度:
     * - Time Complexity: O(n)
     * - Space Complexity: O(h)，為遞迴 stack 深度。
     */
    public TreeNode convertBST(TreeNode root) {
        bst(root, new int[1]);
        return root;
    }

    private void bst(TreeNode node, int[] sum) {
        if (node == null) return;

        bst(node.right, sum);

        node.val += sum[0];
        sum[0] = node.val;

        bst(node.left, sum);
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

