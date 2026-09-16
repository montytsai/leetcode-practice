package io.github.monty.leetcode.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode #637:
 * <a href="https://leetcode.com/problems/average-of-levels-in-binary-tree">Average of Levels in Binary Tree</a>
 *
 * @author Monty.Tsai
 * @since 2025-05-09 10:51:20
 */
public class ID637AverageOfLevelsInBinaryTree {

    /**
     * 解法為 BFS：
     *   - 每層統計節點數與總和後計算平均值
     *   - 使用 queue 進行層序遍歷
     *   - Time: O(n), Space: O(w)，其中 w 為樹的最大寬度
     * - 提供對應的 JUnit 5 測試
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        if (root == null) return res;

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            double sum = 0;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            res.add(sum / size);
        }

        return res;
    }

}