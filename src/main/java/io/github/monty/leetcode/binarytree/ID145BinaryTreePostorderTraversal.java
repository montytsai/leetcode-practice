package io.github.monty.leetcode.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode #145:
 * <a href="https://leetcode.com/problems/binary-tree-postorder-traversal">Binary Tree Preorder Traversal</a>
 *
 * @author Monty.Tsai
 * @since 2025-05-08
 */
public class ID145BinaryTreePostorderTraversal {

    /**
     * 使用遞歸實現後序遍歷
     */
    public List<Integer> postorderTraversal(TreeNode root) {
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
