package io.github.monty.leetcode.binarytree;

import java.util.LinkedList;
import java.util.List;

/**
 * LeetCode #113:
 * <a href="https://leetcode.com/problems/path-sum-ii">
 * Path Sum II
 * </a>
 *
 * @author Monty.Tsai
 * @since 2025-05-12 16:54:09
 */
public class ID113PathSumII {

    /**
     * 解法：DFS + 回溯
     * 思路：
     * - 從根節點開始，記錄每條從 root 到葉節點的路徑。
     * - 若到達葉節點時，累加值等於 targetSum，加入結果清單。
     * - 遞迴過程中持續減去當前節點的值以更新剩餘 target。
     * 複雜度:
     * - 時間：O(n^2) 最壞情況是每條路徑都要複製一次，n 為節點數
     * - 空間：O(h)，h 為樹高（遞迴棧與 path list）
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) return result;

        path(root, targetSum, result, new LinkedList<>());

        return result;
    }

    private void path(TreeNode node, int targetSum, List<List<Integer>> res, List<Integer> path) {
        // 中：處理當前節點
        path.add(node.val);

        // 到達葉節點時，檢查是否符合 targetSum，加入結果集
        if (node.left == null && node.right == null && node.val == targetSum) {
            res.add(new LinkedList<>(path)); // 加入複製版本，避免參考同一物件
        }

        // 繼續遞迴子節點
        if (node.left != null) path(node.left, targetSum - node.val, res, path);
        if (node.right != null) path(node.right, targetSum - node.val, res, path);

        // 回溯: path 是共用的 List，需要 restore state
        //      每層遞迴只加入了一個節點，只需要 remove 最後一個節點
        //      移除剛剛加入的當前節點: path.add(node.val)
        path.remove(path.size() - 1);
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
