package io.github.monty.leetcode.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode #257:
 * <a href="https://leetcode.com/problems/binary-tree-paths">
 * Binary Tree Paths
 * </a>
 *
 * @author Monty.Tsai
 * @since 2025-05-12
 */
public class ID257BinaryTreePaths {

    /**
     * 解法：DFS 遞迴（前序遍歷 + StringBuilder 回溯）
     * <p>
     * 思路：
     * - 題目要求找出「從根到所有葉節點」的完整路徑
     * - 採用 DFS 前序遍歷方式：遇到葉節點就將當前路徑加入結果集
     * - 為了避免每層遞迴都產生新字串，改用 StringBuilder 共用記憶體 + 回溯處理
     * <p>
     * 時間複雜度：O(n)，每個節點訪問一次
     * 空間複雜度：O(h)，h 為樹高，為遞迴棧深度（最壞 O(n)）
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) return result;

        getPath(root, result, new StringBuilder());

        return result;
    }

    /**
     * 使用 DFS 前序遍歷找出所有 root-to-leaf 路徑
     *
     * @param node   當前節點
     * @param result 儲存所有路徑的結果列表
     * @param path 使用同一份 StringBuilder 累加路徑，遞迴後回溯清除新增內容
     */
    private void getPath(TreeNode node, List<String> result, StringBuilder path) {
        if (node == null) return; // 空節點，不處理

        int len = path.length(); // 記錄當前長度（回溯點，還原位置）

        // 構建當前節點的路徑
        if(len > 0) {
            path.append("->");
        }
        path.append(node.val);

        // 如果為葉節點，將完整路徑加入結果列表
        if (node.left == null && node.right == null) {
            result.add(path.toString());
        } else {
            // 遞迴左右子樹
            getPath(node.left, result, path);
            getPath(node.right, result, path);
        }

        // 回溯：還原 path 為進入當前遞迴前的狀態
        path.setLength(len);
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