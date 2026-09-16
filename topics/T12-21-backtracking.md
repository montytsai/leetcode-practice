# Backtracking

回溯就是 **DFS ＋ 撤銷**。它不是一種新演算法，而是「窮舉」長出來的樣子：把所有可能的選擇畫成一棵決策樹，深度優先走完，走錯就退回上一個岔路重選。

什麼時候會想到它？看到題目要**列出所有**符合條件的東西——所有組合、所有排列、所有切法、所有路徑——而且沒有數學公式可以直接算出來，那就是回溯。反過來說，題目只問「有幾個」「最大是多少」的時候，先想想 DP，回溯往往只是保底解。

**回溯本質上就是暴力搜尋**，複雜度天生是指數或階乘級。所以優化的空間不在「換更快的演算法」，而在**剪枝**：越早發現這條路不可能有答案，砍掉的子樹越大。

## 解題技巧

### 三段式模板

```java
void backtracking(參數) {
    if (終止條件) {
        res.add(new ArrayList<>(path));   // 收答案一定要「深拷貝」
        return;
    }
    for (每個可選的分支) {
        path.add(選擇);        // 1. 處理
        backtracking(...);     // 2. 遞迴
        path.remove(size - 1); // 3. 回溯（撤銷 1）
    }
}
```

第 1 步和第 3 步必須**成對**出現，這是整個模板唯一的鐵律。少一次撤銷，後面所有答案都會多帶一截垃圾。

### 頭號地雷：收答案要深拷貝

`res.add(path)` 加進去的是**參考**，`path` 之後被改，已經收下的答案會跟著變，最後全部變成空 list。一定要 `res.add(new ArrayList<>(path))`。這個 bug 在小測資有時候看不出來，特別陰險。

### 組合 vs 排列：差在下一層從哪裡開始

| | 樹的每一層從哪開始 | 怎麼避免重複 | 代表題 |
| --- | --- | --- | --- |
| **組合／子集**（順序不重要） | 從 `startIndex` 開始，只往後看 | `startIndex` 本身就防住了 | [lc-77-combinations](../solutions/12-backtracking/lc-77-combinations.md)、[lc-78-subsets](../solutions/12-backtracking/lc-78-subsets.md) |
| **排列**（順序重要） | 每一層都從 `0` 開始 | 用 `used[]` 排除已經在路徑上的元素 | [lc-46-permutations](../solutions/12-backtracking/lc-46-permutations.md) |

「元素可不可以重複取」則是另一個獨立開關：可以重複就遞迴 `startIndex`（不加一，[lc-39-combination-sum](../solutions/12-backtracking/lc-39-combination-sum.md)），只能取一次就遞迴 `i + 1`（[lc-40-combination-sum-ii](../solutions/12-backtracking/lc-40-combination-sum-ii.md)）。

### 去重：先分清楚「同層」還是「同枝」

輸入有重複元素的時候，會冒出重複答案。要去掉的永遠是**同一層**的重複（同一個位置換成一樣的值），不是同一條路徑上的重複。

- **排序後同層去重**：`if (i > startIndex && nums[i] == nums[i - 1]) continue;`（[lc-40-combination-sum-ii](../solutions/12-backtracking/lc-40-combination-sum-ii.md)、[lc-90-subsets-ii](../solutions/12-backtracking/lc-90-subsets-ii.md)）
- **不能排序的時候**：像 [lc-491-non-decreasing-subsequences](../solutions/12-backtracking/lc-491-non-decreasing-subsequences.md) 一排序就破壞了「遞增子序列」的定義，只能在**這一層**開一個 `Set` 記錄用過的值。

### 答案收在哪裡

- 收在**葉子**（走到終止條件才收）：組合、排列、路徑類
- 收在**每個節點**（一進函式就收）：子集類（[lc-78-subsets](../solutions/12-backtracking/lc-78-subsets.md)）

### 剪枝

剪枝就是把 `for` 的範圍縮小，或提早 `return`。兩種常見寫法：

- **上界剪枝**：還需要 `k - path.size()` 個元素，那 `i` 最多只能到 `n - (k - path.size()) + 1`（[lc-77-combinations](../solutions/12-backtracking/lc-77-combinations.md)）
- **可行性剪枝**：目前的和已經超過 target、剩下的段數不夠切完，直接不進這條分支（[lc-216-combination-sum-iii](../solutions/12-backtracking/lc-216-combination-sum-iii.md)、[lc-93-restore-ip-addresses](../solutions/12-backtracking/lc-93-restore-ip-addresses.md)）

### 搬到圖上要多問一句

回溯的模板在樹和圖上是一模一樣的，但圖多了一個問題：**會不會繞回來**。

- 圖保證是 **DAG**（無環）→ 什麼都不用加，`path` 自己就是「當前路徑上有誰」的紀錄（[lc-797-all-paths-from-source-to-target](../solutions/14-graphs/lc-797-all-paths-from-source-to-target.md)）
- 圖**可能有環** → 要加 `boolean[] onPath`，設 `true` 和設 `false` 就寫在 `path.add` 與 `path.remove` 的同兩行

注意這裡加的是**還原式**的標記，不是數島嶼那種永久 `visited`。列舉路徑時同一個節點本來就該出現在很多條路徑上，永久標記會把正確答案砍掉。判準見 [graph-basics](T14-01-graph-basics.md) 第 7 節。

另外，要管的是「點」還是「邊」也要分清楚：[lc-332-reconstruct-itinerary](../solutions/15-advanced-graphs/lc-332-reconstruct-itinerary.md) 限制的是同一張機票只能用一次，那是**邊**，`boolean[] visited` 這種以節點為索引的結構根本裝不下。

## 已刷題目

- [113. Path Sum II](../solutions/10-trees/lc-113-path-sum-ii.md) Medium — 樹上第一次要「收整條路徑」而不只是回傳布林，add／remove 配對從這題開始
- [131. Palindrome Partitioning](../solutions/12-backtracking/lc-131-palindrome-partitioning.md) Medium — 切割型回溯：切在哪裡就是一個分支，切完再驗回文
- [17. Letter Combinations of a Phone Number](../solutions/12-backtracking/lc-17-letter-combinations-of-a-phone-number.md) Medium — 多個集合各取一個；樹的深度＝輸入長度、寬度＝該數字對應的字母數
- [216. Combination Sum III](../solutions/12-backtracking/lc-216-combination-sum-iii.md) Medium — 長度與總和同時被限制，剪枝要同時看「還差幾個」與「還差多少」
- [257. Binary Tree Paths](../solutions/10-trees/lc-257-binary-tree-paths.md) Easy — 回溯的最小案例：路徑在遞迴回來後要還原
- [332. Reconstruct Itinerary](../solutions/15-advanced-graphs/lc-332-reconstruct-itinerary.md) Hard — 標記的對象是「邊」不是「節點」，節點型的 `visited` 在這題不夠用
- [39. Combination Sum](../solutions/12-backtracking/lc-39-combination-sum.md) Medium — 同一元素可重複取：遞迴時 `startIndex` 不加一
- [40. Combination Sum II](../solutions/12-backtracking/lc-40-combination-sum-ii.md) Medium — 有重複元素但每個只能取一次；排序後「同層去重」的第一題
- [46. Permutations](../solutions/12-backtracking/lc-46-permutations.md) Medium — 排列與組合的分水嶺：每層都從 0 開始，靠 `used[]` 排除自己
- [47. Permutations II](../solutions/12-backtracking/lc-47-permutations-ii.md) Medium — 排列的同層去重，判斷式跟組合的不一樣
- [491. Non-decreasing Subsequences](../solutions/12-backtracking/lc-491-non-decreasing-subsequences.md) Medium — 不能排序（排了就破壞題意），去重只能靠本層的 `Set`
- [51. N-Queens](../solutions/12-backtracking/lc-51-n-queens.md) Hard — 棋盤型回溯：每列一個決策，合法性檢查與撤銷都在同一層
- [77. Combinations](../solutions/12-backtracking/lc-77-combinations.md) Medium — 組合問題的原型，也是上界剪枝最好的示範
- [78. Subsets](../solutions/12-backtracking/lc-78-subsets.md) Medium — 答案收在「每個節點」而不是葉子
- [797. All Paths From Source to Target](../solutions/14-graphs/lc-797-all-paths-from-source-to-target.md) Medium — 回溯搬到圖上；DAG 讓 `visited` 完全可以不要，但起點要自己塞進 `path`
- [90. Subsets II](../solutions/12-backtracking/lc-90-subsets-ii.md) Medium — 子集 ＋ 同層去重的合體
- [93. Restore IP Addresses](../solutions/12-backtracking/lc-93-restore-ip-addresses.md) Medium — 切割型回溯 ＋ 合法性剪枝（前導零、大於 255、剩餘段數）

## 相關

- [_index](_index.md) — 主題筆記索引
- [_moc](../_moc.md) — 全題清單與完成狀態
- [Depth-First Search](T10-12-depth-first-search.md) — 回溯就是會撤銷的 DFS
- [圖論 基礎篇](T14-01-graph-basics.md) — 圖上回溯要不要 `visited` 的判準
- [Binary Tree](T10-21-binary-tree.md) — 收路徑類的樹題（lc-113、lc-257）
- [Dynamic Programming](T16-21-dynamic-programming.md) — 題目從「列出所有」改成「有幾個／最大值」時，正確答案通常從這裡換過來
