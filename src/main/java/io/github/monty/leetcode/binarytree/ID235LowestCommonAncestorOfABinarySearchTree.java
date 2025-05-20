package io.github.monty.leetcode.binarytree;

/**
 * LeetCode #235:
 * <a href="https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree">
 *   Lowest Common Ancestor of a Binary Search Tree
 * </a>
 *
 * @author Monty.Tsai
 * @since 2025-05-20
 */
public class ID235LowestCommonAncestorOfABinarySearchTree {

    /**
     * 解法一：迴圈寫法（迭代版）
     * 思路：
     * - BST會由小排到大，只要找到中介於p, q，或是等於p, q，就找到最小祖先
     *  - 若 root.val > 兩點：代表兩節點都在左子樹
     *  - 若 root.val < 兩點：代表兩節點都在右子樹
     *  - 否則代表 p <= root <= q，root 為 LCA
     * 複雜度：
     * - 時間複雜度：O(h)，h 為樹高，最差為 O(n)
     * - 空間複雜度：O(1)，無遞迴堆疊
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 保證 p < q, p 在左, q 在右
        if (q.val < p.val) {
            TreeNode temp = p;
            p = q;
            q = temp;
        }

        while (root != null) {
            if (root.val > q.val)
                root = root.left;
            else if (root.val < p.val)
                root = root.right;
            else
                return root;
        }

        return null;
    }

    /**
     * 解法二：遞迴寫法
     * 思路與上面相同，只是換成遞迴實作
     * 複雜度：
     * - 時間複雜度：O(h)，h 為樹高
     * - 空間複雜度：O(h)，遞迴 stack 最多 h 層
     */
    public TreeNode lowestCommonAncestorIterative(TreeNode root, TreeNode p, TreeNode q) {
        if (q.val < p.val) {
            return find(root, q, p);
        } else {
            return find(root, p, q);
        }
    }

    private TreeNode find(TreeNode node, TreeNode p, TreeNode q) {
        if (q.val < node.val) {
            return find(node.left, p, q);
        } else if (p.val > node.val){
            return find(node.right, p, q);
        } else { // node == p or q
            return node;
        }
    }

}

/*
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */