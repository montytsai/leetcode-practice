package io.github.monty.leetcode.binarytree;

/**
 * LeetCode #669: 
 * <a href="https://leetcode.com/problems/trim-a-binary-search-tree">
 *   Trim a Binary Search Tree
 * </a>
 * 鑑於二進制搜索樹的根以及最低和最高邊界為低和高的邊界，將樹修剪成，以使其所有元素都位於[低，高]中。
 * 修剪樹不應改變將保留在樹中的元素的相對結構（即，任何節點的後代都應保留為後代）。
 * 可以證明有一個獨特的答案。
 * 返回修剪的二進制搜索樹的根。
 * 請注意，根可能會根據給定界限發生變化。
 *
 * @author Monty.Tsai
 * @since 2025-05-26
 */
public class ID669TrimABinarySearchTree {

    /**
     * 解法: BFS 迭代修剪根與左右子樹
     * 思路:
     * - 先讓 root 移到範圍內（可能會調整根節點）。
     * - 然後沿著每個節點，若其子節點不合法則往下接替。
     * 複雜度:
     * - 時間複雜度：O(n)，每個節點最多遍歷一次
     * - 空間複雜度：O(h)，遞迴棧深度為樹高，最壞 O(n)，平均 O(log n)
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        // 1. 處理頭結點，讓 root 移動到[L, R] 範圍內，注意是左閉右閉
        while (root != null && (root.val < low || root.val > high)) {
            if (root.val < low) root = root.right;
            else root = root.left;
        }

        // 2. 此時root已經在[L, R] 範圍內，處理左孩子元素小於L的情況
        TreeNode curr = root;
        while (curr != null) {
            while (curr.left != null && curr.left.val < low) {
                curr.left = curr.left.right;
            }
            curr = curr.left;
        }

        // 3. 此時root已經在[L, R] 範圍內，處理右孩子大於R的情況
        curr = root;
        while (curr != null) {
            while (curr.right != null && curr.right.val > high) {
                curr.right = curr.right.left;
            }
            curr = curr.right;
        }

        return root;
    }

    /**
     * 解法：利用 BST 的性質進行修剪 *推薦
     * 思路：
     * - 使用 BST 特性：若節點值小於 low，其左子樹都不合法；反之亦然。
     * - 採用後序處理方式：
     *   - 先處理左右子樹，再處理當前節點是否應保留。
     * - 每次遞迴都返回合法子樹的根節點。
     * 複雜度:
     * - 時間複雜度：O(n)，每個節點最多遍歷一次
     * - 空間複雜度：O(h)，遞迴棧深度為樹高，最壞 O(n)，平均 O(log n)
     */
    public TreeNode trimBSTRecursive(TreeNode root, int low, int high) {
        if (root == null) return null;

        // 若當前節點小於 low，小於當前節點的左子樹必定整個都不合法
        // 遞迴處理右子樹，將處理後的右子樹頂替原本的節點
        if (root.val < low) {
            return trimBST(root.right, low, high);
        }

        // 同上邏輯，將處理好的左子樹頂替原本的節點
        if (root.val > high) {
            return trimBST(root.left, low, high);
        }

        // 節點在範圍內，遞迴處理左右子樹
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
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
