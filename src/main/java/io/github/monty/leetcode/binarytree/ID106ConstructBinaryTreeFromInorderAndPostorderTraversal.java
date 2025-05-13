package io.github.monty.leetcode.binarytree;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode #106:
 * <a href="https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal">
 * Construct Binary Tree from Inorder and Postorder Traversal
 * </a>
 * 根據一棵樹的中序遍歷與後序遍歷構造二元樹。
 * 注意: 你可以假設樹中沒有重複的元素。
 *
 * @author Monty.Tsai
 * @since 2025-05-12 16:54:16
 */
public class ID106ConstructBinaryTreeFromInorderAndPostorderTraversal {

    /**
     * Map 儲存中序遍歷每個值的位置，加快尋找 root index 的速度
     * key: 中序遍歷陣列的 val
     * value: 中序遍歷陣列的 index
     */
    private Map<Integer, Integer> inorderIdxByVal;

    /**
     * 解法：遞迴 + Map 優化尋找
     * 思路:
     *   1. 後序遍歷最後一個元素為 root
     *   2. 根據 root 在中序遍歷的位置，劃分左右子樹長度
     *   3. 依據左右子樹長度，從後序遍歷中切出對應的左右子樹區間，再遞迴構建左右子樹。
     * 複雜度
     * - 時間複雜度：`O(n)`（每個節點處理一次 + map 尋找為 O(1)）
     * - 空間複雜度：`O(n)`（map 儲存 n 個元素 + 遞迴堆疊最深為 n）
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0) return null;

        // 建立 Map 儲存中序值對應的索引，Time: O(n)
        inorderIdxByVal = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIdxByVal.put(inorder[i], i);
        }

        // 遞迴建構二元樹
        return build(inorder, 0, inorder.length - 1,
                     postorder, 0, postorder.length - 1);
    }

    /**
     * 遞迴建構二元樹
     *
     * @param inorder   中序遍歷陣列
     * @param inStart   當前子樹 在 中序遍歷 的 起點
     * @param inEnd     當前子樹 在 中序遍歷 的 終點
     *
     * @param postorder 後序遍歷陣列
     * @param postStart 當前子樹 在 後序遍歷 的 起點
     * @param postEnd   當前子樹 在 後序遍歷 的 終點
     *
     * @return 根節點 root node
     */
    private TreeNode build(int[] inorder, int inStart, int inEnd,
                           int[] postorder, int postStart, int postEnd) {
        // 0. 終止條件: 區間無效時
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }

        // 1. 取 postorder 最後一個元素為 root 節點
        int rootVal = postorder[postEnd];
        TreeNode root = new TreeNode(rootVal);

        // 2. 找到 root 在 inorder 的 index => 取得左子樹長度
        int rootInIdx = inorderIdxByVal.get(rootVal);
        int leftLen = rootInIdx - inStart;

        // 3. 遞迴建立左右子樹，並對 root 賦值 （依據子樹長度切割中序與後序區間）
        root.left = build(inorder, inStart, rootInIdx - 1,
                          postorder, postStart, postStart + leftLen - 1);
        root.right = build(inorder, rootInIdx + 1, inEnd,
                           postorder, postStart + leftLen, postEnd - 1);

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