package io.github.monty.leetcode.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode #144:
 * <a href="https://leetcode.com/problems/binary-tree-preorder-traversal">Binary Tree Preorder Traversal</a>
 * 前序遍歷： 中 → 左 → 右，根排在前面。
 *
 * @author Monty.Tsai
 * @since 2025-05-08
 */
public class ID144BinaryTreePreorderTraversal {

    /**
     * 使用「迭代」實現前序遍歷
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        // 利用棧來彈出要處理的元素
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val); // 中
            // 右子樹先放，因為 stack 先進後出，左要先處理要先彈出
            if (node.right != null) stack.push(node.right); // 右
            if (node.left != null) stack.push(node.left); // 子
        }
        return result;
    }

    /**
     * 使用遞歸實現前序遍歷
     */
    public List<Integer> preorderTraversalRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    /**
     * 遞迴三大要素：
     * 1. 確定遞迴函數的參數和返回值
     * 2. 確定終止條件
     * 3. 確定單層遞迴的邏輯
     */
    // 1. 確定遞迴函數的參數和返回值:
    //    要印出前序遍歷 node 的 val，所以參數傳入 List<Integer> 來放印出結果
    //    除了這一點就不需要再處理什麼資料了也不需要有返回值，所以遞迴函數返回類型就是 void
    private void preorder(TreeNode cur, List<Integer> result) {
        // 2. 確定終止條件: 當前節點為空，直接 return 結束遞迴
        if (cur == null) {
            return;
        }
        // 3. 確定單層遞迴的邏輯: 前序遍歷是「[中] → 左 → 右」的順序，所以在單層遞迴的邏輯，是要先取中節點的數值
        result.add(cur.val);         // 中
        preorder(cur.left, result);  // 左
        preorder(cur.right, result); // 右
    }

}