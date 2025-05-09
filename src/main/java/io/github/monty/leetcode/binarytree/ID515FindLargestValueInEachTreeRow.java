package io.github.monty.leetcode.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode #515:
 * <a href="https://leetcode.com/problems/find-largest-value-in-each-tree-row">Find Largest Value in Each Tree Row</a>
 *
 * @author Monty.Tsai
 * @since 2025-05-09 10:51:34
 */
public class ID515FindLargestValueInEachTreeRow {

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int max = Integer.MIN_VALUE;

            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                max = Math.max(max, node.val);

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            res.add(max);
        }

        return res;
    }

}