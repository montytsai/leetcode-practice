---
title: "Combination Sum"
difficulty: Medium
topics: [Backtracking, Array]
category: 12-backtracking
order: 4
source: [Carl]
platform: LeetCode
url: https://leetcode.com/problems/combination-sum/
status: ac-unknown
note: ""
date_created: 2026-04-09
date_updated: 2026-09-03
---

# 39. Combination Sum

## 題目說明

- 給一個不重複的整數陣列 `candidates` 和一個 `target`,找出所有加總等於 `target` 的組合,結果不可重複。
- 同一個數字可以無限次使用。

## 心得

回溯寫到爛了,這題秒解;剪枝那段寫的當下有點心虛,對完答案才確定沒漏東西。

---

## 解法一:排序 + startIndex 回溯

### Intuition

Combination Sum 允許同一個數字重複使用,這跟 Combination Sum II、Subsets II 的差異只在遞迴時傳進去的起始索引:可以重複取就傳 `i`(自己有機會再被選一次),只能取一次就傳 `i + 1`。排序之後,搜尋樹的每一層都只往後看,靠 `startIndex` 固定順序,避免同一組合因為排列方式不同被算兩次。

剪枝的關鍵是陣列已排序:一旦目前最小的候選值已經超過剩餘的 target,後面的候選值只會更大,這條分支底下不可能再湊出答案,可以整段砍掉。這份提交把這個判斷放在函式一進來的地方(`candidates[start] > target`),而不是寫在 for 迴圈裡提前 `break`。兩種寫法砍掉的分支範圍完全一樣,差別只在於前者每次都會多進一次函式呼叫才返回。

### Approach

1. 對 `candidates` 排序,讓後續的剪枝與順序都能依賴「遞增」這個 invariant。
2. 呼叫 `backtracking(candidates, 0, target, result, path)` 開始搜尋。
3. 進入函式先檢查 `target == 0`,是則把 `path` 深拷貝進 `result` 並返回——找到一組答案。
4. 檢查 `candidates[start] > target`,是則直接返回——剩下的候選值都比 target 大,剪掉整條分支。
5. for 迴圈從 `start` 掃到最後一個候選值:把值選入 `path`,遞迴呼叫時起始索引仍傳 `i`(允許重複取同一個數),遞迴返回後把剛才選的值移出 `path`(撤銷)。

### Complexity

**Time complexity: `O(N^(T/M+1))`**

`N` 是 `candidates.length`,`T` 是 `target`,`M` 是 `candidates` 的最小值。每一層最多分支出 `N` 條路,搜尋樹的深度上限是 `T/M`(全部用最小值往上疊,能疊出的最長路徑)。

**Space complexity: `O(T/M)`**

遞迴呼叫堆疊的深度上限跟時間複雜度算出來的樹深同一個量,不含輸出結果本身佔用的空間。

### Code

```java
/**
 * 39. Combination Sum
 * Time Complexity: O(N^(T/M+1))
 * Space Complexity: O(T/M)
 */
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);

        backtracking(candidates, 0, target, result, new ArrayList<>());

        return result;
    }

    private void backtracking(int[] candidates, int start, int target,
                              List<List<Integer>> result, List<Integer> path) {
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        // Feasibility pruning: sorted array, smallest remaining candidate already too big.
        if (candidates[start] > target) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            int candidate = candidates[i];
            path.add(candidate);

            // Pass i, not i + 1, so the same number can be reused.
            backtracking(candidates, i, target - candidate, result, path);

            path.remove(path.size() - 1);
        }
    }
}
```

程式碼規則已套用:B1 English 註解、保留我 AC 的實際邏輯。

### Code Review

- **Learning provenance**:self-solved。能否獨立重現:未確認。
- **Correctness / invariant**:排序 + `startIndex` 遞迴保證組合按遞增順序生成、不會重複。剪枝條件在 `target` 變成負值時一樣成立——此時 `candidates[start]` 是正數,必然大於負的 `target`,提前返回;每次傳入的 `start` 都是上一層 for 迴圈裡合法的索引 `i`,不會有陣列越界的風險。
- **Strength**:剪枝寫在函式入口,同時涵蓋了第一次呼叫與每一層遞迴呼叫,不用對初始呼叫額外處理。
- **Trade-off**:剪枝判斷放在函式入口,而不是 for 迴圈裡提前 `break`,砍掉的分支範圍相同,但每次都會多進出一次函式呼叫——這正是寫的當下懷疑「剪枝是不是少想到什麼」的地方,對完答案可以確定兩種寫法漸進複雜度一樣,差別只在常數因子。
- **Edge cases**:`candidates` 最小值若已經大於 `target`,第一次呼叫就會被步驟 4 剪掉,直接回傳空結果,不需要額外的邊界判斷。

---

## 解法比較

只有一個解法,不需要比較表。

### Optimality

這題要列出所有解,答案數量本身可能是指數量級,不存在能繞過枚舉的多項式演算法,所以回溯已經是「列出所有解」這個目標下的漸進最佳解(metric:time)。

唯一值得一提的替代寫法是把剪枝判斷從函式入口搬到 for 迴圈裡提前 `break`:掃到第一個超過剩餘 target 的候選值就直接跳出迴圈,不再進入遞迴。漸進複雜度不變,差別是常數因子更好——省下確定要剪掉的分支所多花的一次函式呼叫。這是實作細節層級的取捨(metric:constant factor / 可讀性),不是演算法等級的差異,面試時兩種寫法都會被接受。

## 相關

- [Backtracking](../../topics/T12-21-backtracking.md) — 元素可重複取的第一題:遞迴時 `startIndex` 不加一
- [Array](../../topics/T01-21-array.md) — 排序後靠遞增順序做剪枝與去重
- [LeetCode 刷題總覽](../../_moc.md)
