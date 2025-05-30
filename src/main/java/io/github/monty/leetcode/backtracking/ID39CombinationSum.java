package io.github.monty.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode #39: 
 * <a href="https://leetcode.com/problems/combination-sum">
 *   Combination Sum
 * </a>
 * 給一個沒有重複元素的整數陣列 candidates 和一個目標數 target
 * 回傳 candidates 中所有能夠加總為 target 的 結果列表，且結果不可重複，可以以任何順序排列。
 * candidates 中的數字可以重複選取。
 *
 * @author Monty.Tsai
 * @since 2025-05-30
 */
public class ID39CombinationSum {

    /**
     * 解法: 回歸法 (Backtracking)
     * - 構建 DFS 親子結點的算法樹
     * - 每個等級在 for-loop 中從 start 開始循環，以防止重複計算
     * - 當 sum 達到 target 時，即為合法解
     * 複雜度
     * - 時間：最差為 O(2^t)，取決於組合數與遞迴樹分支（會受剪枝影響）
     * - 空間：O(t)，為 recursion stack 的深度
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates); // 為了剪枝需要排序
        backtracking(candidates, target, 0, 0, new ArrayList<>(), result);
        return result;
    }

    /**
     * 回溯函數
     * 樹分析
     *  1. 橫向: for 整個陣列，每層都是[自己到整個陣列]
     *  2. 縱向: 樹深無限，直到 >= target 為止
     *
     * @param candidates 候選數字（已排序）
     * @param target     目標總和
     * @param start      當前遞迴起始 index
     * @param sum        當前累加和
     * @param path       當前組合（遞迴路徑）
     * @param res        儲存所有合法組合
     */
    private void backtracking(int[] candidates, int target, int start, int sum,
                              List<Integer> path, List<List<Integer>> res) {
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // 剪枝: 陣列已遞增排列，若確認 當前節點 已經大於 target，可以不用繼續遍歷
            if (sum + candidates[i] > target) return;

            path.add(candidates[i]);
            backtracking(candidates, target, i, sum + candidates[i], path, res);
            path.remove(path.size() - 1);
        }
    }

}