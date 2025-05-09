package io.github.monty.leetcode.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode #102:
 * <a href="https://leetcode.com/problems/binary-tree-level-order-traversal">Binary Tree Level Order Traversal</a>
 * 層序遍歷
 *
 * @author Monty.Tsai
 * @since 2025-05-09 10:50:53
 */
public class ID102BinaryTreeLevelOrderTraversal {

    /**
     * 解法: DFS + 遞迴（以 depth 控制層級）
     * - 使用「前序遍歷」的思路，每遇到一個節點，就根據其 `depth` 決定加入哪一層。
     * - 當目前深度 `depth` 與結果 list 的大小相等時，表示這是該層第一個出現的節點 → 應新建 list。
     * - Time: O(n)
     * - Space: O(h), 遞迴深度，h 為樹高
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, result, 0);
        return result;
    }

    private void dfs(TreeNode cur, List<List<Integer>> result, int depth) {
        if (cur == null) return;

        // 每層第一次到達時建立新的 List（層數從 0 開始）
        if (depth >= result.size()) {
            result.add(new ArrayList<>());
        }

        // 加入當層結果
        List<Integer> level = result.get(depth);
        level.add(cur.val);
        // 遞迴左右子樹
        dfs(cur.left, result, depth + 1);
        dfs(cur.right, result, depth + 1);
    }

    /**
     * 解法: BFS + Queue（使用 `ArrayDeque`）
     * 使用 queue 儲存每一層節點，在每一層遍歷過程中記錄 queue 的長度，避免節點動態加入導致層級混淆。
     * - Time Complexity： O(n) — 每個節點恰好被訪問一次。
     * - Space Complexity： O(n) — 最壞情況下（完全二元樹），queue 會同時儲存最多 n/2 個節點。
     */
    public List<List<Integer>> levelOrderQueue(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();

            int levelSize = queue.size(); // queue 的 size 不斷變化，要先儲存當前層數的大小
            while (levelSize-- > 0) {
                TreeNode node = queue.poll();
                level.add(node.val); // 將當前節點加入結果集

                // 把下一層的左右子節點加入 queue
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            result.add(level);
        }

        return result;
    }

}

