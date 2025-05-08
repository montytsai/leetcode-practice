package io.github.monty.leetcode.binarytree;

import java.util.ArrayList;
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
     * 使用遞歸實現前序遍歷
     */
    public List<Integer> preorderTraversal(TreeNode root) {
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