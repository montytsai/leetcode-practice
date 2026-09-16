package io.github.monty.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode #216: 
 * <a href="https://leetcode.com/problems/combination-sum-iii">
 *   Combination Sum III
 * </a>
 * 給定兩個整數 k 和 n，找出所有由 1~9 中互不相同的 k 個數字組成，且總和為 n 的組合。
 * 每種組合中的數字必須是遞增的，不能重複。
 *
 * @author Monty.Tsai
 * @since 2025-05-27
 */
public class ID216CombinationSumIII {

    /*
     * 範例： k = 3, n = 7
     * 決策樹概念圖（部分展開）：
     *                                  []
     *              ┌────────────┬────────────┬────────────┐
     *             [1]         [2]         [3]        ... [7]
     *              |           |           |
     *         ┌────┴───┐     ┌─┴───┐     ┌─┴─┐
     *       [1,2]   [1,3]   [2,3]  ...   ...
     *         |        |
     *       [1,2,3]  [1,2,4]  ...
     * - 橫向代表：同一層不同的選擇（目前這一位數可以選哪些數），寬度為 start ~ 9，剪枝 start ~ k剩餘個數
     * - 縱向代表：遞迴深入一層，選取下一位數字（組合的第幾位），深度為 k
     * - 每個節點記錄目前的 path（組合內容）與 sum（總和）
     * - 葉節點（path.size() == k）時檢查 sum 是否等於目標 n
     */

    /**
     * 解法：回溯
     * 思路
     * - 從 1 開始遞迴選取數字，每次選下一個遞增的數字。
     * - 組合長度達 k 時檢查總和是否為 n，是則加入結果。
     * - 使用 sum > n 提前剪枝，加速效率。
     * - `i <= 9 - (k - path.size()) + 1` 剪枝，避免無解的情況。
     * 複雜度
     * - 時間：O(C(9,k))
     * - 空間：O(k)
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        backtracking(k, n, 1, 0, new ArrayList<>(), res);
        return res;
    }

    /**
     * 回溯函數
     *
     * @param k      組合長度上限
     * @param n      目標總和
     * @param start  當前遞迴起始數字
     * @param sum    當前組合的總和
     * @param path   當前組合內容
     * @param res    最終結果清單
     */
    private void backtracking(int k, int n, int start, int sum,
                              List<Integer> path, List<List<Integer>> res) {
        // 剪枝: 總和已超過 n
        if (sum > n) return;

        // 終止條件: 已達 k 個數
        if (path.size() == k) {
            if (sum == n) res.add(new ArrayList<>(path));
            return;
        }

        // 從當前數字開始遞增選取，避免重複
        // 剪枝: 計算剩餘元素個數
        for (int i = start; i <= 9 - (k - path.size()) + 1; i++) {
            // 處理當前節點
            sum += i;
            path.add(i);

            // 遞迴
            backtracking(k, n, i + 1, sum, path, res);

            // 回溯
            sum -= i;
            path.remove(path.size() - 1);
        }
    }

}