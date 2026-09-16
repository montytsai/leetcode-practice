package io.github.monty.leetcode.binarytree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode #654: 
 * <a href="https://leetcode.com/problems/maximum-binary-tree">
 *   Maximum Binary Tree
 * </a>
 *
 * @author Monty.Tsai
 * @since 2025-05-13
 */
public class ID654MaximumBinaryTree {

    /**
     * 最優解: 單調棧法（O(n)） *先存起來以後再看
     * 思路:
     * 1. 使用「遞減單調棧」維護棧內節點值單調遞減。
     * 2. 遍歷 `nums`，對於當前元素：
     *    - 若比棧頂元素大，則不斷彈棧，直到遇到比自己大或棧空。
     *    - 彈出的元素最後一個成為當前元素的左子節點。
     *    - 若棧未空，棧頂元素成為當前元素的右子節點。
     *    - 將當前元素入棧。
     * 3. 最後棧底元素即為根。
     * 複雜度
     * - 時間：O(n)
     * - 空間：O(n) 用於棧
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        Deque<TreeNode> stack = new ArrayDeque<>();

        for (int num : nums) {
            TreeNode current = new TreeNode(num);

            // 處理左子節點：彈出比當前小的節點，並設為 current.left
            while (!stack.isEmpty() && stack.peek().val < num) {
                current.left = stack.pop();
            }

            // 處理右子節點：如果棧頂比 current 大，current 就是棧頂的右子節點
            if (!stack.isEmpty()) {
                stack.peek().right = current;
            }

            stack.push(current);
        }

        // 棧底就是 root（最大值）
        while (stack.size() > 1) {
            stack.pop();
        }
        return stack.peek();
    }

    /**
     * 解法：遞迴分治（Divide and Conquer）
     * 思路：
     * 1. 在子陣列 nums[start..end] 中線性掃描找出最大值的 index rootIdx。
     * 2. 將該最大值作為根節點。
     * 3. 左子樹對應 nums[start..rootIdx-1]，右子樹對應 nums[rootIdx+1..end]，遞迴構建。
     * 複雜度：
     * - 時間複雜度：O(n^2)（最壞為單邊樹，每層掃描約 n 次）
     * - 空間複雜度：O(n)（遞迴深度最壞為 n）
     */
    public TreeNode constructMaximumBinaryTree2(int[] nums) {
        return construct(nums, 0, nums.length - 1);
    }

    /**
     * 遞迴構建子樹
     *
     * @param nums  原始陣列
     * @param start 子陣列起始索引
     * @param end   子陣列結束索引
     *
     * @return      對應子樹的根節點
     */
    private TreeNode construct(int[] nums, int start, int end) {
        // 0. 終止條件
        if (start > end) return null;

        // 1. 陣列中最大的元素為 root
        int rootIdx = start;
        for (int i = start + 1; i <= end; i++) {
            if (nums[i] > nums[rootIdx]) {
                rootIdx = i;
            }
        }
        TreeNode root =  new TreeNode(nums[rootIdx]);

        // 2. 根據 root 所在的位置，分成左右子樹，繼續遞迴 (找到對大的元素為根節點xN)
        root.left = construct(nums, start, rootIdx - 1);
        root.right = construct(nums, rootIdx + 1, end);

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