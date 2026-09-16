---
title: "All Paths From Source to Target"
difficulty: Medium
topics: [Graph, Backtracking, Depth-First Search, Breadth-First Search]
category: 14-graphs
order: 4
source: [Carl]
platform: LeetCode
url: https://leetcode.com/problems/all-paths-from-source-to-target/
status: ac-assisted
note: ""
date_created: 2026-09-01
date_updated: 2026-09-01
---

# 797. All Paths From Source to Target

## 題目說明

- 給一張 `n` 個節點的有向無環圖（DAG），`graph[i]` 是節點 `i` 能走到的所有節點，也就是現成的鄰接表。
- 回傳從節點 `0` 到節點 `n - 1` 的所有路徑，順序不拘。
- 約束：`2 <= n <= 15`、`graph[i]` 不含 `i`、元素唯一、保證是 DAG。
- 邊界：節點 `0` 可能沒有出邊，答案為空；路徑長度上限 `n`。

## 心得

偷看代碼隨想錄的答案寫出來的。還是不知道為什麼不用 visited，而且有兩個大錯 KEY。

---

## 解法一：Backtracking（DFS ＋ 路徑回溯）

### Intuition

本題手法是回溯：把所有選擇展開成一棵決策樹，深度優先走訪，離開分支時撤銷。難點不在模板，而在「為什麼這次不需要 visited」。

#### visited 的兩項職責在本題都不成立

`visited` 平常同時承擔兩項互相獨立的工作：

1. **防止環造成無限遞迴。** 本題保證是 DAG，沿邊前進不會回到起點，遞迴必然終止於無出邊的節點。
2. **保證每個節點只處理一次**，以換取 `O(V + E)`。數島嶼、判連通性依賴這一項。但本題要列舉所有路徑，同一節點本就應出現在多條不同路徑上；永久標記在此不是優化，是刪去正確答案。

判準因此是：**在數東西就永久標記；在列舉東西，標記必須還原，或根本不需要。**

若圖含環且仍需列舉路徑，要用的是還原式標記 `boolean[] onPath`——進節點設 `true`、離開設 `false`，與 `path` 的 add／remove 完全同步，與數島嶼的永久 `visited` 是兩回事。本題連 `onPath` 都可省略，因為 DAG 保證同一條路徑上不會出現重複節點，`path` 本身即是該紀錄。

**不用 visited 是 DAG 的前提所賦予，不是列舉路徑類題目的通則。** 同樣的程式碼放到含環的有向圖上會無限遞迴，搬用前先確認題目是否保證無環。

#### 兩個 KEY! 是同一件事的兩面

兩者都源自模板風格，不是本題特有的規則。本解採「在迴圈中處理下一個節點」的寫法，每個節點都由它的前驅加入 `path`；起點沒有前驅，因此必須手動放入。

另一種風格在函式開頭加入自己、結束前撤銷自己，就不需要預先放入起點，代價是 add／remove 跨越整個函式，配對關係不如前者明顯。兩種都對，重點是知道自己在用哪一種。

```java
// 風格 B：進函式先處理自己，離開前撤銷自己
private void dfs(int[][] graph, int curr, ...) {
    path.add(curr);
    if (curr == graph.length - 1) res.add(new ArrayList<>(path));
    else for (int next : graph[curr]) dfs(graph, next, ...);
    path.remove(path.size() - 1);
}
```

### Approach

1. `path` 先放入起點 `0`，然後從 `0` 開始遞迴。
2. 終止條件：`curr == graph.length - 1`，把 `path` 的**複本**收進 `res` 並 `return`。
3. 對 `graph[curr]` 的每個鄰居 `num`：
   - 處理：`path.add(num)`
   - 遞迴：以 `num` 為新的 `curr` 往下走
   - 回溯：`path.remove(path.size() - 1)`
4. 迴圈跑完自然返回；沒有出邊的節點直接結束這條分支。

invariant：進入 `backtracking(curr)` 的當下，`path` 恰好是「從 0 走到 curr 的那條路徑」，離開時會回到進來時的樣子。第 3 步的 add／remove 成對出現就是在守這個 invariant。

終止時直接 `return` 是安全的：DAG 保證離開 `n - 1` 之後回不來，所以不存在「中途經過終點、後面還要繼續」的合法路徑。

### Complexity

令 `n` 為節點數。

**Time complexity: `O(2^n · n)`**

最壞情況是完全 DAG（所有 `i < j` 都有邊），此時 `0` 到 `n-1` 的路徑數是 `2^(n-2)`，每找到一條要花 `O(n)` 複製。這是輸出敏感的複雜度：時間主要花在把答案寫出來，不是花在搜尋。

**Space complexity: `O(n)`（不計輸出）**

`path` 最長 `n`、遞迴堆疊最深 `n`。回傳的 `res` 是輸出，本身可達 `O(2^n · n)`。

### Code

```java
/**
 * 797. All Paths From Source to Target
 * Time Complexity: O(2^n * n)  -- output sensitive
 * Space Complexity: O(n) excluding the output
 */
class Solution {

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();

        // KEY! Different from a binary tree: no parent adds node 0 for us,
        // because the loop below always handles the NEXT node.
        List<Integer> path = new ArrayList<>();
        path.add(0);

        backtracking(graph, 0, res, path);

        return res;
    }

    private void backtracking(int[][] graph, int curr,
                     List<List<Integer>> res, List<Integer> path) {
        // 1. base case: we reached the target
        if (curr == graph.length - 1) {
            res.add(new ArrayList<>(path)); // copy it, or later removes destroy it
            return;
        }

        // 2. try every outgoing edge
        for (int num : graph[curr]) {
            // 2-1. KEY! We handle the NEXT node here, not the current one.
            path.add(num);

            // 2-2. recur
            backtracking(graph, num, res, path);

            // 2-3. backtrack: undo the step we just took
            path.remove(path.size() - 1);
        }
    }

}
```

### Code Review

- **Learning provenance**：`reference-assisted`，看代碼隨想錄的答案寫出來。能否獨立重現：未確認；自述仍不理解為什麼不用 visited，本篇 Intuition 已補。
- **Correctness**：正確。add／remove 成對出現，`path` 進出函式狀態一致；終點直接 `return` 在 DAG 前提下安全。
- **Strength 1**：`res.add(new ArrayList<>(path))` 有做深拷貝。這是回溯題的頭號地雷——直接 `res.add(path)` 會讓所有答案指向同一個物件，最後全變空 list。
- **Strength 2**：三段式結構乾淨，跟 backtracking 那章練的模板對得起來，等於把樹的肌肉記憶正確搬到圖上。
- **Strength 3**：`path.remove(path.size() - 1)` 用尾端刪除，`O(1)`，避開了 `remove(Object)` 陷阱——若寫成 `path.remove(num)`，Java 會解析成「刪值等於 num 的第一個元素」，路徑上一有重複值就刪錯人。
- **Trade-off**：`res`、`path` 走參數而非 instance field，沒有跨測資殘留狀態，代價是每層多傳兩個參考。深度只有 15，維持現狀。
- **Style**：`num` 其實是節點編號，命名成 `next` 會更貼近圖論講法。純可讀性。
- **Edge cases**：節點 `0` 沒有出邊時迴圈不跑、`res` 為空，正確。`n == 2` 且 `graph[0] = [1]` 得到一條路徑 `[0,1]`。
- **搬走時的風險**：這份程式碼依賴 DAG 保證，丟進可能有環的有向圖會無窮遞迴。

## 解法比較

只有一個 AC 解法，無需比較表。

### Optimality

以時間為指標，本解已是輸出敏感的最佳解。

下限由輸出本身決定：最壞情況（完全 DAG）有 `2^(n-2)` 條路徑、總長 `O(2^n · n)`，任何正確解都必須把這些內容輸出出來。空間 `O(n)` 亦為下限，至少要容納當前這條路徑。

唯一值得一提的替代法是 DAG 上的記憶化：`memo[v]` 存「從 `v` 到終點的所有路徑」，算過的節點直接取用。它在本題無效——`n <= 15`，且路徑數本身為指數級，記憶體會先爆。

它的價值在於揭示一個切換點：**列出所有路徑無法避免指數爆炸，計算路徑條數可以。** 同一張 DAG，把 `memo[v]` 從路徑清單換成路徑條數，複雜度降為 `O(V + E)`。題目從 list 改為 count 的那一刻，正解就從回溯換成 DAG 上的 DP。

## 相關

- [Backtracking](../../topics/T12-21-backtracking.md) — 樹上的回溯模板原封不動搬到圖上；差別只在誰把起點放進 path
- [圖論 基礎篇](../../topics/T14-01-graph-basics.md) — visited 什麼時候要還原、什麼時候可以完全不用
- [Depth-First Search](../../topics/T10-12-depth-first-search.md) — 決策樹型的 DFS，終止條件是「走到終點」不是「走到 null」
- [Breadth-First Search](../../topics/T10-13-breadth-first-search.md) — 官方 tag；本次未用
- [LeetCode 刷題總覽](../../_moc.md)
