package io.github.monty.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode #90: 
 * <a href="https://leetcode.com/problems/subsets-ii">
 *   Subsets II
 * </a>
 *
 * @author Monty.Tsai
 * @since 2025-06-17 14:15:08
 */
public class ID90SubsetsII {

    /*
     * ASCII 樹圖說明（輸入 [1,2,2]）
     *
     *                        [ ]
     *          ┌──────────────┼──────────────┐
     *         [1]2,2         [2]2           [2](重複X)
     *       ┌──┴──┐           │
     *    [1,2]2  (X)       [2,2]
     *       │
     *  [1,2,2]
     */

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // 先排序以利去除重複元素
        backtracking(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtracking(int[] nums, int start,
                              List<Integer> path, List<List<Integer>> res) {
        res.add(new ArrayList<>(path)); // 全部都是合法子集
        if (start >= nums.length) return; // 終止條件，但是不會執行，for 迴圈已控管結束

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue; // 去重：如果與前面元素相同，跳過

            path.add(nums[i]);
            backtracking(nums, i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }

}