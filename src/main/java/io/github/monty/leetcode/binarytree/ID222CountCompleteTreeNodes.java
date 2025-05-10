package io.github.monty.leetcode.binarytree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode #222:
 * <a href="https://leetcode.com/problems/count-complete-tree-nodes">
 * Count Complete Tree Nodes
 * </a>
 * 給定一棵完全二元樹，請計算節點總數。
 * 完全二元樹定義：除了最底層之外，每層節點都是滿的，且最底層節點從左至右排列。
 *
 * @author Monty.Tsai
 * @since 2025-05-10 10:23:37
 */
public class ID222CountCompleteTreeNodes {

    /**
     * 解法四(最優):
     * 利用完全二元樹特點: 如果是滿樹，節點數量 = 2^h - 1
     * - 判斷是否為滿樹 (左右深度相同)
     *   - 是: 返回 2^h - 1
     *   - 否: 遞迴左右子樹 + 1（根節點）
     * - 時間複雜度：O(log² n)（每層 getDepth 是 O(log n)，最多遞迴 log n 層）
     * - 空間複雜度：O(log n)（遞迴棧深度）
     */
    public int countNodes(TreeNode root) {
        if (root == null) return 0;

        TreeNode left = root.left;
        TreeNode right = root.right;

        int depth = isFullGetDepth(left, right);
        if (depth != -1) {
            return (1 << depth) - 1; // 2^depth - 1
        }

        return 1 + countNodes(left) + countNodes(right);
    }

    /**
     * 從該子樹的根開始往左與往右各走到底，確認是否為滿樹
     * 如果左右深度相同，表示為滿樹，回傳含根深度；否則回傳 -1
     */
    private int isFullGetDepth(TreeNode left, TreeNode right) {
        int leftDepth = 0;
        while (left != null) {
            left = left.left;
            leftDepth++;
        }

        int rightDepth = 0;
        while (right != null) {
            right = right.right;
            rightDepth++;
        }

        // leftDepth 只有左/右子樹的深度，少了根節點深度，要加 1 才是「當前整棵樹的層數 h」
        return leftDepth == rightDepth ? (leftDepth + 1) : -1;
    }


    /**
     * 解法一：遞迴（後序遍歷）
     * 每個節點都會遞迴左右子樹後再加上自己（+1）
     * Time Complexity: O(n)
     * Space Complexity: O(h) - 遞迴棧深度，最壞為樹高
     */
    public int countNodes1(TreeNode root) {
        if (root == null) return 0;
        int countLeft = countNodes(root.left);
        int countRight = countNodes(root.right);
        return countLeft + countRight + 1; // 左 > 右 > 中 (自己+1)
    }

    /**
     * 解法二：遞迴（解法一的改寫）
     * 同樣遞迴左、右子樹後加上當前節點，但封裝在另一個方法中。
     * Time Complexity: O(n)
     * Space Complexity: O(h)
     */
    public int countNodes2(TreeNode root) {
        return count(root);
    }

    private int count(TreeNode node) {
        if (node == null) return 0;
        int countLeft = countNodes(node.left);
        int countRight = countNodes(node.right);
        return 1 + countLeft + countRight;
    }

    /**
     * 解法三：BFS（層序遍歷）
     * 使用 queue 逐層遍歷所有節點，遇到節點就加總。
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public int countNodes3(TreeNode root) {
        if (root == null) return 0;

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        int nodes = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            nodes++;

            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }

        return nodes;
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
