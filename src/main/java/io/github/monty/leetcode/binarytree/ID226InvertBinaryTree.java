package io.github.monty.leetcode.binarytree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode #226: 
 * <a href="https://leetcode.com/problems/invert-binary-tree">Invert Binary Tree</a>
 * 反轉一棵二元樹（左右子樹交換）。
 *
 * @author Monty.Tsai
 * @since 2025-05-09 11:39:16
 */
public class ID226InvertBinaryTree {

    /**
     * 解法一：BFS 迭代解法（使用 Stack 模擬 DFS 前序遍歷）
     * 每個節點在訪問時立即交換左右子樹，並將子節點推入堆疊繼續處理。
     * Time Complexity: O(n)，每個節點訪問一次
     * Space Complexity: O(n)，最壞情況下 stack 儲存所有節點
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            swap(node);

            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }

        return root;
    }

    /**
     * 解法二：DFS 遞迴解法（後序處理）
     * 先遞迴處理左右子樹，處理完後再交換左右節點。
     * Time Complexity: O(n)，每個節點遞迴訪問一次
     * Space Complexity: O(h)，h 為樹的高度（遞迴深度）
     */
    public TreeNode invertTreeIterative(TreeNode root) {
        if (root == null) return null;
        invertTree(root.left);
        invertTree(root.right);
        // swap left and right
        swap(root);
        return root;
    }

    private void swap(TreeNode node) {
        if (node == null) return;
        TreeNode tmp = node.left;
        node.left = node.right;
        node.right = tmp;
    }

}