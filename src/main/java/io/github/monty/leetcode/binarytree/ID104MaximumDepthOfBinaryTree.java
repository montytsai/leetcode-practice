package io.github.monty.leetcode.binarytree;

/**
 * LeetCode #104: 
 * <a href="https://leetcode.com/problems/maximum-depth-of-binary-tree">Maximum Depth of Binary Tree</a>
 *
 * @author Monty.Tsai
 * @since 2025-05-09 10:51:56
 */
public class ID104MaximumDepthOfBinaryTree {

    /**
     * 解法一：經典遞迴
     * 採用後序遞迴（post-order）遍歷，先求左右子樹深度後回傳最大值 + 1。
     * Time: O(n), Space: O(h)
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return Math.max(left, right) + 1;
    }

    /**
     * 解法二：雙參數遞迴
     * <p>
     * 傳入目前深度參數，於遞迴中計算。
     * 適合擴展需追蹤深度的情境
     * <p>
     * 這是將深度值「當參數」傳遞的寫法，效果與方法一類似，但風格偏向前序處理（pre-order）邏輯中融合後序特性。
     * 寫法上較不直觀，但可以在需要追蹤層數時更有彈性。
     * <p>
     * Time: O(n), Space: O(h)
     */
    public int maxDepth2(TreeNode root) {
        if (root == null) return 0;
        return dfs(root, 0);
    }

    private int dfs(TreeNode node, int depth) {
        if (node == null) return depth;

        int left = dfs(node.left, depth + 1);
        int right = dfs(node.right, depth + 1);

        return Math.max(left, right);
    }

}
