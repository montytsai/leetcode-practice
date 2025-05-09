package io.github.monty.leetcode.binarytree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * LeetCode #101: 
 * <a href="https://leetcode.com/problems/symmetric-tree">
 *   Symmetric Tree
 * </a>
 * 檢查一棵樹是否為對稱的
 *
 * @author Monty.Tsai
 * @since 2025-05-09 11:40:37
 */
public class ID101SymmetricTree {

    /**
     * 使用兩個 Queue 進行廣度優先遍歷，比對對稱節點是否相等。
     * 判斷條件為：值是否相同、結構是否對稱（左對右，右對左）。
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public boolean isSymmetric(TreeNode root) {
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