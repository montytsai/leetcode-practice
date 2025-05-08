package io.github.monty.leetcode.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
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
     * 使用「迭代」實現中序遍歷
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        // stack 用來存放「訪問『過』的元素」
        Deque<TreeNode> stack = new ArrayDeque<>();
        // curr 是指針，用來訪問目前的元素
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            // 若 curr 不為空，一路向左
            if (curr != null) {
                stack.push(curr); // 將訪問過的節點 暫存在 stack 中
                curr = curr.left; // 左: 繼續深度向左
            } else { // curr 為空，沒左邊了，處理自己和右邊
                curr = stack.pop();
                result.add(curr.val); // 中
                curr = curr.right; // 右
            }
        }
        return result;
    }


    /**
     * 使用遞歸實現中序遍歷
     */
    public List<Integer> inorderTraversalRecursive(TreeNode root) {
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
