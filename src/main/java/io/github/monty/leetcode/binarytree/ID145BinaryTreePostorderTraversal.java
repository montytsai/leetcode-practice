package io.github.monty.leetcode.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * LeetCode #145:
 * <a href="https://leetcode.com/problems/binary-tree-postorder-traversal">Binary Tree Postorder Traversal</a>
 * 後序遍歷：左 → 右 → 中
 *
 * @author Monty.Tsai
 * @since 2025-05-08
 */
public class ID145BinaryTreePostorderTraversal {

    /**
     * 解法: 迴圈模擬（不使用 null）
     * 思路:
     * - 使用 prev 指針記錄「上次訪問的節點」，判斷右子樹是否已訪問。
     * 時間複雜度: O(n)
     * 空間複雜度: O(n)
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        TreeNode prev = null; // 用來判斷右子樹是否已走過

        while (curr != null || !stack.isEmpty()) {
            // 先一路往左走
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            // 看最上層節點（不馬上彈出）
            TreeNode node = stack.peek();

            // 若沒有右子樹(null) or 右子樹已處理完(上個節點prev) => 才會處理目前節點。
            if (node.right == null || node.right == prev) {
                result.add(node.val); // 左右皆訪問完，處理中節點
                stack.pop();
                prev = node;  // 標記這個節點處理過
                // 不再往下走
            } else {
                // 尚未訪問右子樹，進入右子樹
                curr = node.right;
            }
        }

        return result;
    }

    /**
     * 解法: 迭代 + 空指針標記法 （推薦：易於理解）
     * 思路:
     * - 使用「空指針 null 作為已訪問中節點的標記」來模擬遞歸的控制流。
     * - 步驟為：中 → 右 → 左（放入棧中） + Null 標記 → 回到中節點處理。
     * 複雜度:
     * - 時間複雜度: O(n)
     * - 空間複雜度: O(n)（棧空間最壞情況為樹深度）
     */
    public List<Integer> postorderTraversalNull(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        // 利用棧來彈出要處理的元素
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if (node != null) {
                stack.pop();

                stack.push(node); // 中
                stack.push(null);
                if (node.right != null) stack.push(node.right); // 右
                if (node.left != null) stack.push(node.left); // 左
            } else {
                stack.pop(); // pop null
                node = stack.pop();
                result.add(node.val);
            }

        }
        return result;
    }

    /**
     * 解法: 先序反轉法（簡單快速）
     * 思路: 將「中 → 右 → 左」的結果反轉，即為「左 → 右 → 中」。
     * 時間複雜度: O(n)
     * 空間複雜度: O(n)
     */
    public List<Integer> postorderTraversalReversePreorder(TreeNode root) {
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
     * 解法: 遞歸 (樹不大時最簡潔好用)
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
