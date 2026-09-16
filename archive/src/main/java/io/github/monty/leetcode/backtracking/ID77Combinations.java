package io.github.monty.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode #77: 
 * <a href="https://leetcode.com/problems/combinations">
 *   Combinations
 * </a>
 * 給定 n 和 k，找出從 1 到 n 中選擇 k 個數的所有組合。
 *
 * @author Monty.Tsai
 * @since 2025-05-28
 */
public class ID77Combinations {

    /*
     * Input: n = 4, k = 2
     * Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
     * Tree ASCII:
     *
     *                                     for 循環：橫向遍歷
     *     —————————————————————————————————————————————————————————————————→
     * 　  |                                       [0]                          ← 第 0 層，起點
     * 　  |                                        |
     * 　  |                               (在1,2,3,4 中取兩個數)
     *  　 |            ┌───────────────────┬───────┴───────┬────────────┐
     *  遞 |            ↓                   ↓               ↓            ↓      ← 第 1 層，for i from 1 to 4（橫向選擇）
     *  歸 |           [1]                 [2]             [3]          [4]     ← 組合長度 = 1
     *  ： |            |                   |               |            |
     *  縱 |   (在2,3,4中取一個數)     (在3,4中取一個數)   (在4中取一個數)     (空)     ← 若 k = 2，則 [4] 無子節點，剪枝
     *  向 |     ┌──────┼──────┐        ┌───┴───┐           |
     *  遍 |    [2]    [3]    [4]      [3]     [4]         [4]
     *  歷 |     ↓      ↓      ↓        ↓       ↓           ↓                   ← 第 2 層，for i from start to 4（橫向選擇）
     *  　 |   [1,2]  [1,3]  [1,4]    [2,3]   [2,4]       [3,4]                 ← 組合長度 = k（2），即為解（葉節點）
     *  　 |
     *  　 ↓
     */

    /**
     * 解法：回溯（Backtracking）＝ 構造一棵選擇樹，走到底（葉節點）就是一個解。
     * 思路：用 DFS 遞迴生成組合樹
     * - 每層從 `start` 起選數，避免重複與順序問題
     * - 若組合長度達 k，記錄為解
     * - 每次選擇後回溯
     * 複雜度：
     * - 時間複雜度：O(C(n, k))，最多組合數為 n choose k
     * - 空間複雜度：O(k)，遞迴深度
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtracking(n, k, 1, new ArrayList<>(), result);
        return result;
    }

    /**
     * 回溯函數
     *
     * @param n      數字上限
     * @param k      組合目標長度
     * @param start  本層起始位置（防止重複與逆序）
     * @param path   當前遞迴路徑（中間組合）
     * @param result 結果集合
     */
    private void backtracking(int n, int k, int start,
                              List<Integer> path, List<List<Integer>> result) {
        // 1. 終止條件: 當前組合已經有 k 個數字
        if (path.size() == k) {
            result.add(new ArrayList<>(path)); // 存放結果
            return;
        }

        // 2. 選擇當層選項
        // for 選擇本層集合大小 (樹的子節點數量): [start, n]
        for (int i = start; i <= n - (k - path.size()) + 1; i++) {
            // (1). 做選擇 (處理節點)
            path.add(i);
            // (2). 遞迴: 進入下一層
            backtracking(n, k, i + 1, path, result);
            // (3). 回溯: 移除剛剛加入的 i
            path.remove(path.size() - 1);
        }
    }

}