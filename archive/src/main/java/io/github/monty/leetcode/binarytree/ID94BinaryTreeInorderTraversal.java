package io.github.monty.leetcode.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * LeetCode #94:
 * <a href="https://leetcode.com/problems/binary-tree-inorder-traversal">Binary Tree Inorder Traversal</a>
 * 中序遍歷：中 → 左 → 右
 *
 * @author Monty.Tsai
 * @since 2025-05-08
 */
public class ID94BinaryTreeInorderTraversal {

    /**
     * 使用「迭代 + 空指針標記法」實現中序遍歷
     * 注意: 必須使用 Stack Class or LinkedList，因為 ArrayDeque 明確禁止 null 元素，push(null) 會噴 NullPointerException
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        // 使用 stack 存放被訪問過的節點
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();

            if (node != null) {
                stack.pop(); // 把 node 先彈出，再照順序放回來

                // 加入右 node (空節點不入棧)
                if (node.right != null) stack.push(node.right);
                // 加入中 node
                stack.push(node);
                stack.push(null); // 使用空指針標記: 此 node 被訪問過，但是還沒有處理
                // 加入左 node (空節點不入棧)
                if (node.left != null) stack.push(node.left);
            } else { // 遇到空指針，此指針被訪問過，可以放進結果集
                stack.pop(); // 彈出 null
                node = stack.pop(); // 彈出被標記的指針

                result.add(node.val); // 加入結果集
            }
        }

        return result;
    }

    /**
     * 使用「迭代」實現中序遍歷
     */
    public List<Integer> inorderTraversalIterative(TreeNode root) {
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
