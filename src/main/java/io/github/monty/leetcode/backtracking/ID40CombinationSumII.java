package io.github.monty.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode #40:
 * <a href="https://leetcode.com/problems/combination-sum-ii">
 * Combination Sum II
 * </a>
 *
 * @author Monty.Tsai
 * @since 2025-05-30
 */
public class ID40CombinationSumII {

    /**
     * 解法：回溯法
     * - 與 LC39 不同，每個數字只能使用一次。
     * - 為避免結果重複，需「同層去重」。
     * - 排序後可輕易判斷同層重複元素，且利於剪枝。
     * 複雜度：
     * - 時間：O(2^n)，每個元素存在與否
     * - 空間：O(n)，遞迴最大深度與 path 長度
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates); // 排序以利剪枝與重複處理
        backtracking(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    /**
     * 回溯函數
     *
     * @param candidates 候選數字（已排序）
     * @param target     目標剩餘差值
     * @param start      當前遞迴起始 index
     * @param path       當前組合（遞迴路徑）
     * @param res        儲存所有合法組合
     */
    private void backtracking(int[] candidates, int target, int start,
                              List<Integer> path, List<List<Integer>> res) {
        // 當累計值剛好為目標，視為合法解
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // 剪枝：若目前值已超出目標，後面數字更大，不需再遞迴
            if (target - candidates[i] < 0) return;

            // 同層去重: 結果集不可重複，跳過「同層」使用過的元素（同層 >start 而非 >0），不同層可用使用過的元素
            if (i > start && candidates[i] == candidates[i - 1]) continue;

            path.add(candidates[i]);
            target -= candidates[i];

            backtracking(candidates, target, i + 1, path, res);

            target += candidates[i];
            path.remove(path.size() - 1);
        }
    }

}