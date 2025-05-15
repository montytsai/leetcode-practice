package io.github.monty.leetcode.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode #501:
 * <a href="https://leetcode.com/problems/find-mode-in-binary-search-tree">
 * Find Mode in Binary Search Tree
 * </a>
 * 找出 BST 中出現頻率最高的節點值（可能不只一個）。
 *
 * @author Monty.Tsai
 * @since 2025-05-15
 */
public class ID501FindModeInBinarySearchTree {

    /**
     * 解法：中序遍歷 + 模擬頻率統計（O(1) 空間）
     * 思路：
     * - BST 中序遍歷會產生遞增序列，可用於計算連續值的出現次數。
     * - 若遇不同值，curFreq 歸 1，更新最大頻率 maxFreq 並維護眾數 result。
     * - 不使用 Map，可達 O(1) 空間（輸出結果除外）。
     * 複雜度:
     * - Time: O(n)
     * - Space: O(h) → 遞迴棧 or 額外 stack（最壞為 O(n)）
     */
    public int[] findMode(TreeNode root) {
        if (root == null) return new int[0];

        List<Integer> result = new ArrayList<>();
        int maxFreq = 0;
        int curFreq = 0;

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        TreeNode prev = null;

        while (!stack.isEmpty() || curr != null) {
            // 左
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            // 中
            curr = stack.pop();

            // 如果當前值與前一個相同，累加頻率
            if (prev != null && prev.val == curr.val) {
                curFreq++;
            } else {
                curFreq = 1;
            }

            // 更新結果
            if (curFreq == maxFreq) {
                result.add(curr.val);
            } else if (curFreq > maxFreq) {
                maxFreq = curFreq;
                result.clear();
                result.add(curr.val);
            }
            prev = curr;

            // 右
            curr = curr.right;
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * 解法：遞迴 + HashMap 統計
     * 思路：
     * - 使用遞迴，遍歷 BST 中每個節點。
     * - 利用 HashMap 統計每個值出現的次數。
     * - 最後從 Map 中找出最大頻率，收集所有符合該頻率的值。
     * 複雜度
     * - 時間：O(n)
     * - 空間：O(n)
     */
    public int[] findMode2(TreeNode root) {
        if (root == null) return new int[0];

        // 使用 Map 統計每個值出現的次數
        Map<Integer, Integer> freqByVal = new HashMap<>();
        countFreq(root, freqByVal);

        // 找出最大出現頻率
        int maxFreq = Collections.max(freqByVal.values());

        // 收集所有出現頻率等於最大值的 key（即 mode）
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : freqByVal.entrySet()) {
            if (entry.getValue() == maxFreq) {
                result.add(entry.getKey());
            }
        }

        // 轉換成 int[]
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private void countFreq(TreeNode node, Map<Integer, Integer> freqByVal) {
        if (node == null) return;

        int frequency = freqByVal.getOrDefault(node.val, 0) + 1;
        freqByVal.put(node.val, frequency);

        countFreq(node.left, freqByVal);
        countFreq(node.right, freqByVal);
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

