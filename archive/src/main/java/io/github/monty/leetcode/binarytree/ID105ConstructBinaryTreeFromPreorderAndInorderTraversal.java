package io.github.monty.leetcode.binarytree;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode #105:
 * <a href="https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal">
 * Construct Binary Tree from Preorder and Inorder Traversal
 * </a>
 *
 * @author Monty.Tsai
 * @since 2025-05-12 16:54:23
 */
public class ID105ConstructBinaryTreeFromPreorderAndInorderTraversal {

    /**
     * 解法：Divide and Conquer，透過 preorder 決定 root，inorder 劃分左右子樹
     * Time: O(n), Space: O(n) — 每個節點訪問一次，並用 HashMap 儲存 index
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inorderIdxByVal = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIdxByVal.put(inorder[i], i);
        }

        return build(preorder, 0, preorder.length -1,
                inorder, 0, inorder.length - 1,
                inorderIdxByVal);
    }

    private TreeNode build(int[] preorder, int preStart, int preEnd,
                           int[] inorder, int inStart, int inEnd,
                           Map<Integer, Integer> inorderIdxByVal) {
        // 0. 終止條件
        if (preStart > preEnd || inStart > inEnd) return null;

        // 1. 前序的第一個元素是 root
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);

        // 2. 根據 root 尋找中序遍歷的 index
        int rootIdx = inorderIdxByVal.get(rootVal);

        // 3. 根據 rootIdx 取得左右子樹的大小
        int leftSize = rootIdx - inStart;

        // 4. 遍歷左右子樹
        root.left = build(preorder, preStart + 1, preStart + leftSize,
                inorder, inStart, inStart + leftSize - 1,
                inorderIdxByVal);
        root.right = build(preorder, preStart + leftSize + 1, preEnd,
                inorder, inStart + leftSize + 1, inEnd,
                inorderIdxByVal);

        return root;
    }

}

/*
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */