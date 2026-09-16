package io.github.monty.leetcode.binarytree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * LeetCode #199:
 * <a href="https://leetcode.com/problems/binary-tree-right-side-view">Binary Tree Right Side View</a>
 *
 * @author Monty.Tsai
 * @since 2025-05-09 10:51:12
 */
public class ID199BinaryTreeRightSideView {

    /**
     * 解法為 BFS：
     *   - 每層僅記錄最右側節點的值（即每層最後一個彈出的節點）
     *   - 使用 queue 進行層序遍歷
     *   - Time Complexity: O(n)
     *   - Space Complexity: O(n)
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        if (root == null) return result;

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            while (levelSize-- > 0) {
                TreeNode node = queue.poll();
                if (levelSize == 0) {
                    result.add(node.val);
                }

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }

        return result;
    }

}