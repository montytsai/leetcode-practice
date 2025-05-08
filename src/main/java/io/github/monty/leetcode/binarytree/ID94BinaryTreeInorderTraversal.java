package io.github.monty.leetcode.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode #94:
 * <a href="https://leetcode.com/problems/binary-tree-inorder-traversal">Binary Tree Preorder Traversal</a>
 *
 * @author Monty.Tsai
 * @since 2025-05-08
 */
public class ID94BinaryTreeInorderTraversal {

    /**
     * 使用遞歸實現中序遍歷
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(TreeNode cur, List<Integer> result) {
        if (cur == null) {
            return;
        }

        // 左 → [中] → 右
        inorder(cur.left, result);
        result.add(cur.val);
        inorder(cur.right, result);
    }

}
