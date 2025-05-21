package io.github.monty.leetcode.binarytree;

/**
 * LeetCode #450:
 * <a href="https://leetcode.com/problems/delete-node-in-a-bst">
 *   Delete Node in a BST
 * </a>
 *
 * @author Monty.Tsai
 * @since 2025-05-21
 */
public class ID450DeleteNodeInABST {

    /**
     * 解法：搬移子樹（左子樹接到右子樹最小值）
     * 思路：
     * 1. 遞迴遍歷樹找目標值。
     * 2. 若未找到，繼續遞迴往左右子樹找。
     * 3. 找到後依據以下狀況刪除：
     *    - 左右子節點皆為空：return null。
     *    - 左空右不空：return 右子節點。
     *    - 右空左不空：return 左子節點。
     *    - 左右皆不空：找到右子樹最小值，將左子樹接到該節點左邊，並回傳 root.right 作為新根節點。
     *      - 回傳 `root.right` 時，外層會自動接回回傳結果，不需額外指標維護。
     * 複雜度
     * - 時間複雜度：O(h)，h 為樹高，最壞 O(n)，最好 O(log n)
     * - 空間複雜度：O(h)，為遞迴棧的深度
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        // 狀況1: 沒找到 key 就到了葉節點
        if (root == null) return null;

        // 找到要刪除的節點了
        if (root.val == key) {
            // 狀況2: 左右子節點都為空
            if (root.left == null && root.right == null) return null;

            // 狀況3: 左子節點為空，右子節點不為空 => 右子節點補位 root
            if (root.left == null) return root.right;

            // 狀況4: 左子節點不為空、右子節點為空 => 左子節點補位 root
            if (root.right == null) return root.left;

            // 狀況5: 左右子節點皆不為空 => 找到右子樹最小的值，把左子樹掛在右子樹最小值上
            // - 找右子樹中最小的節點（最左邊）
            TreeNode minRight = root.right;
            while (minRight.left != null) {
                minRight = minRight.left;
            }
            // - 將原本 root 的左子樹掛到 minRight.left
            minRight.left = root.left;
            // - 回傳 root 的右子樹，頂替原 root 節點
            return root.right;
        } else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else { // root.val < key
            root.right = deleteNode(root.right, key);
        }

        return root;
    }

    /**
     * 解法: 標準換值法（右子樹最小值複製）
     * chatGPT 原本堅持的寫法
     */
    public TreeNode deleteNode2(TreeNode root, int key) {
        // Case 1: 沒找到節點
        if (root == null) return null;

        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else {
            // Case 2: 找到目標節點，開始刪除邏輯
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            // Case 3: 左右子樹都存在 → 找右子樹最小節點替代
            TreeNode minNode = findMin(root.right); // 右子樹最小節點
            root.val = minNode.val; // 用值換值
            root.right = deleteNode(root.right, minNode.val);
        }
        return root;
    }

    private TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
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
