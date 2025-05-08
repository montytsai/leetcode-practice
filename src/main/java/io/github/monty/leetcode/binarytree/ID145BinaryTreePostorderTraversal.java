package io.github.monty.leetcode.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode #145:
 * <a href="https://leetcode.com/problems/binary-tree-postorder-traversal">Binary Tree Preorder Traversal</a>
 * 後序遍歷：左 → 右 → 中
 *
 * @author Monty.Tsai
 * @since 2025-05-08
 */
public class ID145BinaryTreePostorderTraversal {

    /**
     * 使用迭代實現後序遍歷
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            // 處理順序: 中 → 右 → 左
            result.add(node.val);
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
        }

        // 反轉結果: 左 → 右 → 中
        Collections.reverse(result);
        return result;
    }

    /**
     * 使用遞歸實現後序遍歷
     */
    public List<Integer> postorderTraversalRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorder(root, result);
        return result;
    }

    private void postorder(TreeNode cur, List<Integer> res) {
        if (cur == null) {
            return;
        }
        // 左 → 右 → [中]
        postorder(cur.left, res);
        postorder(cur.right, res);
        res.add(cur.val);
    }

}
