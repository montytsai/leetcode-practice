package io.github.monty.leetcode.binarytree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * LeetCode #101:
 * <a href="https://leetcode.com/problems/symmetric-tree">
 * Symmetric Tree
 * </a>
 * 檢查一棵樹是否為對稱的
 *
 * @author Monty.Tsai
 * @since 2025-05-09 11:40:37
 */
public class ID101SymmetricTree {

    /**
     * 解法一：DFS 遞迴
     * - 使用遞迴函數同時比對左子樹與右子樹是否為鏡像。
     * - 結構需對稱：左.left vs 右.right、左.right vs 右.left
     * - Time: O(n)
     * - Space: O(h), h 為樹高, 最壞為 O(n)
     */
    public boolean isSymmetricRecursive(TreeNode root) {
        if (root == null) return true;
        return dfs(root.left, root.right);
    }

    private boolean dfs(TreeNode left, TreeNode right) {
        // 兩個都是 null => 對稱
        if (left == null && right == null) return true;
        // 其中之一是 null or val 不相同 => 不對稱
        if (left == null || right == null || left.val != right.val) return false;

        // 鏡像比較值
        boolean outside = dfs(left.left, right.right); // 比較二元樹外側是否對稱
        boolean inside = dfs(left.right, right.left);  // 比較二元樹內側是否對稱

        return outside && inside;
    }

    /**
     * 解法二：BFS 單佇列
     * - 使用單個佇列，每次加入成對節點（left, right）。（注意入佇列順序！）
     * - 每次出列比對值是否相同，並按鏡像順序加入下一層子節點。
     * - Time: O(n)
     * - Space: O(n)
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;

        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);

        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();

            if (left == null && right == null) continue;
            if (left == null || right == null || left.val != right.val) return false;

            // 注意加入順序: 最左側 → 最右側 → 第二左左側 → 第二右右側 → ...
            // => 所以兩個要比較的元素會緊緊相連，連續 poll() 出就可得到
            queue.offer(left.left);
            queue.offer(right.right);
            queue.offer(left.right);
            queue.offer(right.left);
        }

        return true;
    }

    /**
     * 解法三：BFS 雙佇列（兩邊節點分別入列進行比對）
     * - 使用兩個佇列，分別追蹤左子樹與右子樹。
     * - 每次從兩個佇列出列對應節點，值與結構需一致。
     * - 判斷條件為：值是否相同、結構是否對稱（左對右，右對左）。
     * - Time: O(n)
     * - Space: O(n)
     */
    public boolean isSymmetricDoubleQueue(TreeNode root) {
        if (root == null) return true;

        // 使用兩個佇列模擬左右子樹的鏡像比對
        Deque<TreeNode> first = new LinkedList<>();
        Deque<TreeNode> last = new LinkedList<>();
        first.offer(root.left);
        last.offer(root.right);

        while (!first.isEmpty() && !last.isEmpty()) {
            TreeNode f = first.poll();
            TreeNode l = last.poll();

            // 同時為 null 表示對稱，繼續
            if (f == null && l == null) continue;

            // 只有一邊為 null 或值不同，表示不對稱
            if (f == null || l == null || f.val != l.val) return false;

            // 加入下一層要比較的節點（注意鏡像順序）
            first.offer(f.left);
            first.offer(f.right);
            last.offer(l.right);
            last.offer(l.left);
        }

        return first.isEmpty() && last.isEmpty();
    }

}