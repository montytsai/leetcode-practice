package io.github.monty.leetcode.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * LeetCode #107: 
 * <a href="https://leetcode.com/problems/binary-tree-level-order-traversal-ii">Binary Tree Level Order Traversal II</a>
 *
 * @author Monty.Tsai
 * @since 2025-05-09 10:51:05
 */
public class ID107BinaryTreeLevelOrderTraversalII {

    /**
     * 採用 DFS 解法：
     *   - 透過遞迴遍歷節點，並根據深度倒序插入結果清單中（避免使用 Collections.reverse）。
     *   - 每層第一次遇到時插入一個空 list 到開頭，再依據 res.size - depth - 1 放入節點值。
     *   - Time Complexity: O(n)
     *   - Space Complexity: O(h) — h 為樹高（遞迴棧深）
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        dfs(root, result, 0);
//        Collections.reverse(result);
        return result;
    }

    private void dfs(TreeNode node, List<List<Integer>> res, int depth) {
        if (node == null) return;

        if (res.size() == depth) {
            res.add(0, new ArrayList<>());
        }

        res.get(res.size() - depth - 1).add(node.val);
        dfs(node.left, res, depth + 1);
        dfs(node.right, res, depth + 1);
    }

}