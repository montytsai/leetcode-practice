---
title: "Merge Intervals"
difficulty: Medium
topics: [Greedy, Array, Sorting]
category: 19-intervals
order: 2
source: [Carl, Extra]
platform: LeetCode
url: https://leetcode.com/problems/merge-intervals/
status: ac-unknown
note: ""
date_created: 2026-04-03
date_updated: 2026-09-05
---

# 56. Merge Intervals

## 題目說明

- 給一組區間 `intervals`(每個區間 `[start, end]`)，合併所有重疊的區間，回傳合併後的區間陣列。
- 輸出不要求維持輸入順序，但重疊的判定包含端點相接的情況(例如 `[1,4]` 與 `[4,5]` 視為重疊，要合併成 `[1,5]`)。

## 心得

這題寫第三次秒解，但是排序 `Arrays.sort(arr, compare)`、最後轉換 `list.toArray(new int[0][])` 這兩個語法沒背起來還是要查。

---

## 解法一：排序 + 線性掃描貪心合併

### Intuition

排序後，重疊的區間一定相鄰，不會有兩個不相鄰的區間重疊卻中間夾著一個不重疊的區間。只要依 start 遞增排序，合併就能用一次線性掃描完成：維護目前正在合併中的區間上界 `right`，下一個區間的 start 只要不大於 `right` 就代表重疊，直接把 `right` 更新成兩者 end 的較大值；一旦下一個區間的 start 大於 `right`，後面所有區間(因為已排序)都不可能再跟目前這組合併，可以把目前這組收進結果、重新開一組。

這題第三次寫都能秒解，代表「排序打斷不相鄰重疊」這個性質已經內化，卡住的只剩 Java 語法層面(comparator 怎麼寫、`List<int[]>` 怎麼轉 `int[][]`)，不是演算法邏輯本身。

### Approach

1. 依區間起點由小到大排序 `intervals`。
2. 用 `left`、`right` 追蹤目前正在合併中的區間，初始化成排序後第一個區間的起訖點。
3. 從第二個區間開始掃描：若目前區間的 start ≤ `right`(重疊或相接)，把 `right` 更新為兩者 end 的較大值；否則把 `[left, right]` 收進結果，並用新區間重設 `left`、`right`。
4. 迴圈結束後，最後一組 `[left, right]` 還沒被收進結果，補加一次。
5. 把 `List<int[]>` 轉成 `int[][]` 回傳。

### Complexity

**Time complexity: `O(n log n)`**

`n` 是區間數，排序主導整體複雜度；排序後的線性掃描只需 `O(n)`。

**Space complexity: `O(n)`**

`result` 最壞情況下要存 `n` 組互不重疊的區間；`Arrays.sort` 對物件陣列(`int[][]`)使用 TimSort，額外空間也是 `O(n)`。

### Code

```java
/**
 * 56. Merge Intervals
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 */
class Solution {
    public int[][] merge(int[][] intervals) {
        List<int[]> result = new ArrayList<>();
        // Sort by start so overlapping intervals become adjacent.
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        int left = intervals[0][0];
        int right = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= right) {
                // Overlaps (or touches) the current merged interval; extend it.
                right = Math.max(right, intervals[i][1]);
            } else {
                // No overlap; close the current interval and start a new one.
                result.add(new int[] { left, right });
                left = intervals[i][0];
                right = intervals[i][1];
            }
        }
        // The last merged interval is never closed inside the loop.
        result.add(new int[] { left, right });

        return result.toArray(new int[0][]);
    }
}
```

### Code Review

- **Learning provenance**：`self-solved`；第三次寫此題，秒解，可獨立重現。
- **Correctness / invariant**：排序後用 `right` 追蹤目前合併區間上界的寫法正確；`intervals[i][0] <= right` 用 `<=` 而非 `<`，正確把端點相接(如 `[1,4]`、`[4,5]`)視為重疊。迴圈結束後手動補加最後一組，沒有漏掉。
- **Strength**：只用 `left`、`right` 兩個變數就把合併狀態表達完整，沒有多餘的資料結構；容易忘記的「補加最後一組」細節有做到。
- **Bug**：無。
- **Trade-off**：`Arrays.sort` 會直接排序、改動傳入的 `intervals` 陣列本身(in-place)。這題不影響結果，但如果呼叫端之後還要用原始順序的 `intervals`，會被此處的排序打亂。
- **Style**：comparator 用 `Integer.compare(a[0], b[0])` 比直接 `a[0] - b[0]` 更穩妥，能避免極端值相減溢位(本題 constraints 雖不會觸發，習慣值得保留)。
- **Edge cases**：單一區間、端點相接(`[1,4]`、`[4,5]`)、完全包含關係(`[1,10]`、`[2,3]`)都被 `right = Math.max(right, intervals[i][1])` 正確處理──即使新區間被完全包住，取 `max` 也不會把 `right` 縮小。

---

## 解法比較

只有一個解法，略。

### Optimality

以時間衡量，`O(n log n)` 是這題的下界：不先排序就無法在線性時間內判定哪些區間相鄰重疊，必須先建立順序關係。以空間衡量，`O(n)` 也是必要的，因為最壞情況下所有區間互不重疊，結果本身就要存 `n` 組。這個解法在時間、空間兩個指標上都已經是最佳。

這次卡點不在演算法而在 Java 語法，值得記的是語法面的替代寫法：comparator 可以寫成 `Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]))`，比 `(a, b) -> Integer.compare(a[0], b[0])` 短，效果相同，而且 `comparingInt` 這個方法名稱本身有語意，比死記 lambda 寫法好背。

## 相關

- [Greedy](../../topics/T18-21-greedy.md) ── 排序打斷不相鄰重疊的貪心合併範例
- [Array](../../topics/T01-21-array.md)
- [Sorting](../../topics/T00-21-sorting.md)
- [LeetCode 刷題總覽](../../_moc.md)
