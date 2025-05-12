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
     * 解法一：後序遞迴（左右中），求「最大深度」
     * <p>
     * - 本題要求「一棵二元樹的最大深度」
     * - 定義差異說明
     *   - 深度（depth）： 某節點到根節點的距離，由上往下計算（常用前序遍歷）
     *   - 高度（height）：某節點到最遠葉子節點的距離（邊數），由下往上計算（常用後序遍歷）
     *   - LeetCode 中定義是以「節點數」為單位：root 深度為 1（非學術上定義邊長為 0）
     * - 而整棵樹的「最大深度」等同於「根節點的高度」
     *   - 所以可以從葉子節點開始往上遞迴，計算每個節點的左右子樹高度，取 max
     * <p>
     * Time Complexity: O(n) - 每個節點會被訪問一次
     * Space Complexity: O(h) - 遞迴深度最多為樹的高度（最壞情況為 O(n)）
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0; // leaf 節點為最 1 的高度，走到 null => 沒有節點，高度為 0

        int leftHeight = maxDepth(root.left); // 左
        int rightHeight = maxDepth(root.right); // 右
        return 1 + Math.max(leftHeight, rightHeight); // 中（根節點高度 = max(左右子樹高度) + 1）
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
