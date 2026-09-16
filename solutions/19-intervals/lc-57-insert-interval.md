---
title: "Insert Interval"
difficulty: Medium
topics: [Intervals, Array]
category: 19-intervals
order: 3
source: [Grind75, Extra]
platform: LeetCode
status: ac-unknown
note: ""
date_created: 2026-08-01
date_updated: 2026-08-17
---

## 心得

2026-08-01 已經寫過一次；當時如何得到解法未確認。2026-08-17 複習時能獨立、輕鬆重現三階段 one-pass 解法：先收集完全位於 `newInterval` 前方的區間，再合併所有重疊區間，最後追加後方區間。

核心心得：已排序且互不重疊的 intervals 可以切成「前綴、合併區、後綴」三段；index 只往前走一次，就能完成插入與合併。

## Java

```java
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> ans = new ArrayList<>();
        int i = 0;
        // Add the non-overlapping intervals before newInterval.
        for (; i < intervals.length; i++) {
            if (intervals[i][1] >= newInterval[0]) break;
            ans.add(intervals[i]);
        }
        // Merge all overlapping intervals.
        int left = i < intervals.length ? Math.min(intervals[i][0], newInterval[0]) : newInterval[0];
        int right = newInterval[1];
        for (; i < intervals.length; i++) {
            if (intervals[i][0] > newInterval[1]) break;
            right = Math.max(intervals[i][1], right);
        }
        ans.add(new int[]{left, right});
        // Add the non-overlapping intervals after the merged interval.
        for (; i < intervals.length; i++) ans.add(intervals[i]);
        return ans.toArray(new int[ans.size()][]);
    }
}
```

## Code Review

### Learning provenance

- 2026-08-01 初次 AC 的解題來源未確認，不推斷為自行完成或受提示完成。
- 2026-08-17 複習為 `self-solved`：我明確表示這次能獨立、輕鬆重現。

### Correctness and invariant

- 第一階段只收集 `end < newInterval.start` 的區間，所以 `ans` 中的前綴一定與新區間不重疊。
- 第二階段合併所有可能重疊的區間，並維護合併後的 `left`、`right`。迴圈結束後加入的 `[left, right]` 是完整合併區。
- 第三階段追加剩餘區間；它們都位於合併區之後，而且原輸入已排序，因此結果仍保持排序且互不重疊。
- 空陣列、插在最前或最後、`newInterval` 完全被舊區間包含，以及一次跨過多個區間，都能正確處理。`ans.toArray(new int[ans.size()][])` 也能正確產生 `int[][]`。

### Complexity, strengths, and improvements

- 時間是 O(n)，因為 index `i` 只會從左到右走過每個 interval 一次。
- 回傳輸出最多有 O(n) 個 intervals；此外，`ArrayList` builder 會持有 O(n) 個 references，所以這個版本的 auxiliary space 也是 O(n)，不能籠統寫成 O(1)。
- 合併迴圈用 `intervals[i][0] > newInterval[1]` 當 break，在題目保證舊 intervals 已排序且互不重疊時是正確的，不是 bug。若某個重疊 interval 把 `right` 延伸到原始 new end 之外，下一個舊 interval 的 start 必定大於該 interval 的 end，因此不會再與 merged range 重疊。
- 可以把 break 改成 `intervals[i][0] > right`，更直接表達「目前 merged range」的不變量，也能適應較寬鬆的輸入。這是 readability／robustness 改善，不是本題修錯。
- 程式不會改寫 interval 的值；前綴與後綴會沿用原本的 `int[]` references，對 LeetCode 合約沒有問題，但呼叫端若之後修改回傳列，會與原輸入共享那些列。

### Optimality and alternatives

以 time complexity 為指標，O(n) 已達漸近最佳：最壞情況必須讀取並輸出所有 intervals。這就是 canonical one-pass 解法，沒有更好的 Big-O；binary search 也無法省去後續輸出與搬移。常見的 while-loop variant 會直接擴張 `newInterval`，但本質仍是相同三階段演算法，時間與空間複雜度相同，因此不需要再貼一份完整 code。

## 相關

- [Intervals](../../topics/T19-21-intervals.md) — 前綴、合併區、後綴的三階段 invariant
- [Array](../../topics/T01-21-array.md) — 線性掃描已排序 intervals
- [LeetCode 刷題總覽](../../_moc.md)
