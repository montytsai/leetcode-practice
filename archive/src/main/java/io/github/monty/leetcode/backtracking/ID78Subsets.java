package io.github.monty.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode #78: 
 * <a href="https://leetcode.com/problems/subsets">
 *   Subsets
 * </a>
 * 給定一個不含重複元素的整數陣列 nums，返回該陣列的所有子集（power set）。
 * 解集中不可包含重複的子集，順序不限。
 *
 * @author Monty.Tsai
 * @since 2025-06-16 18:45:10
 */
public class ID78Subsets {

    /**
     * 解法：回溯法（Backtracking）
     * - 每一層遞迴代表是否選擇當前元素
     * - 每次呼叫都將目前 path 加入結果集
     * - 避免重複選擇：每次從 index 之後開始往後遞迴
     * 複雜度：
     * - 時間複雜度：O(2^n)，每個元素有選與不選兩種可能，共 2^n 個子集
     * - 空間複雜度：O(n)
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtracking(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtracking(int[] nums, int start,
                              List<Integer> path, List<List<Integer>> res) {
        res.add(new ArrayList<>(path)); // 把所有可能性(子集)都加入結果
        if (start >= nums.length) return; // 不會執行到這，因為 for 迴圈結束條件

        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            backtracking(nums, i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }

}
