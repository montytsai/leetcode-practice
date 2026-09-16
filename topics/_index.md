# 主題筆記索引

`topics/` 的入口與導航，編號與排序跟 [`solutions/`](../solutions) 的資料夾一致。

檔名 `T<主題號2碼>-<篇序2碼>-<slug>.md`：主題號對應 `solutions/` 的資料夾序號，`T00` 是不綁題型的橫切筆記。篇序第一碼是寫的時機——`0X` 基礎篇、`1X` 子主題、`2X` 總結篇，第二碼是該段的流水號。

題目的完成狀態與刷題順序看 [_moc](../_moc.md)。建檔規格在本頁末：[主題名合併表](#主題名合併表) ｜ [兩篇制](#兩篇制) ｜ [筆記格式](#筆記格式)

---

## T00 · 橫切

不綁題型，刷哪一類都用得到。

- [T00-01 complexity-analysis](T00-01-complexity-analysis.md) — 時間與空間複雜度怎麼算、常數優化值不值得、開陣列前怎麼判大小
- [T00-11 quickselect](T00-11-quickselect.md) — 平均 O(n) 找第 K 大，與 Heap 的取捨
- [T00-21 sorting](T00-21-sorting.md) — 各排序法的穩定性與適用場景
- [T00-22 divide-and-conquer](T00-22-divide-and-conquer.md) — 拆半後合併的通用骨架

## T01 · Arrays & Hashing

- [T01-11 matrix](T01-11-matrix.md) — 二維座標走訪、方向陣列、原地旋轉
- [T01-21 array](T01-21-array.md) — 覆寫式移除、原地重排、前綴／差分的線性掃描手法
- [T01-22 hash-table](T01-22-hash-table.md) — 用空間換查找時間；計數、配對、去重的選型

## T02 · Two Pointers

- [T02-21 two-pointers](T02-21-two-pointers.md) — 對撞、快慢、同向三種指針的適用條件

## T03 · String

- [T03-11 string-matching](T03-11-string-matching.md) — KMP 與子字串比對
- [T03-21 string](T03-21-string.md) — 反轉、切分、就地改寫與 `StringBuilder` 取捨

## T04 · Sliding Window

- [T04-21 sliding-window](T04-21-sliding-window.md) — 同向雙指針的定長／變長視窗

## T05 · Prefix Sum

- [T05-21 prefix-sum](T05-21-prefix-sum.md) — 前綴／後綴的差值換取 O(1) 區間查詢，含差分與前綴積

## T06 · Stack

- [T06-21 stack-queue](T06-21-stack-queue.md) — LIFO／FIFO 選型、互相模擬、Monotonic Deque

## T07 · Monotonic Stack

**待建**——題還沒刷。

## T08 · Binary Search

- [T08-21 binary-search](T08-21-binary-search.md) — 答案空間二分的邊界寫法

## T09 · Linked List

**待建**——題刷完了，筆記還沒寫。

## T10 · Trees

- [T10-11 binary-search-tree](T10-11-binary-search-tree.md) — 中序遞增、BST 的搜尋／插入／刪除
- [T10-12 depth-first-search](T10-12-depth-first-search.md) — 樹與圖共用的遞迴走訪｜基礎篇在 [T14-02](T14-02-depth-first-search-basics.md)
- [T10-13 breadth-first-search](T10-13-breadth-first-search.md) — 逐層／最短步數｜基礎篇在 [T14-03](T14-03-breadth-first-search-basics.md)
- [T10-21 binary-tree](T10-21-binary-tree.md) — 走訪順序、層邊界、遞迴帶 `depth` 的兩種模式

## T11 · Heap / Priority Queue

- [T11-21 heap-priority-queue](T11-21-heap-priority-queue.md) — 固定大小 Heap 解 Top K

## T12 · Backtracking

- [T12-21 backtracking](T12-21-backtracking.md) — 三段式模板、組合／排列的分水嶺、同層去重與剪枝

## T13 · Tries

- [T13-01 trie-basics](T13-01-trie-basics.md) — **基礎篇**：字元住在邊上的心智模型（理論已備，題還沒刷）
- 總結篇**待建**——觸發點是刷完 lc-208、lc-1268

## T14 · Graphs

- [T14-01 graph-basics](T14-01-graph-basics.md) — **基礎篇**：三種存法、visited 標記時機、選型表
- [T14-02 depth-first-search-basics](T14-02-depth-first-search-basics.md) — **基礎篇**：回溯在撤銷什麼、深搜三部曲
- [T14-03 breadth-first-search-basics](T14-03-breadth-first-search-basics.md) — **基礎篇**：一圈一圈、容器選擇、分層與多源
- 總結篇**待建**——觸發點是刷完島嶼系列（lc-200／695／1020／130）
- 並查集基礎篇**待建**——觸發點是 lc-721 Accounts Merge，同輪暖身題 lc-547、lc-1971

## T15 · Advanced Graphs

- [T15-01 topological-sort-basics](T15-01-topological-sort-basics.md) — **基礎篇**：入度＝還欠幾門先修課、Kahn 剝洋蔥與環偵測、DFS 後序逆序與三色標記
- 最短路徑基礎篇**待建**（Dijkstra／Bellman-Ford／Floyd）——觸發點是 lc-787 Cheapest Flights，暖身題 lc-743

## T16 · 1-D Dynamic Programming

- [T16-21 dynamic-programming](T16-21-dynamic-programming.md) — 狀態定義、轉移、初始值、遍歷順序、空間壓縮
- 背包、打家劫舍、股票、子序列四大題型都還沒開始

## T17 · 2-D Dynamic Programming

與 T16 共用 [T16-01](T16-21-dynamic-programming.md)，等二維題型累積夠再拆。

## T18 · Greedy

- [T18-21 greedy](T18-21-greedy.md) — 局部最佳何時等於全域最佳、反證怎麼找

## T19 · Intervals

- [T19-21 intervals](T19-21-intervals.md) — 排序後掃描的區間合併／移除

## T20 · Bit Manipulation

- [T20-21 bit-manipulation](T20-21-bit-manipulation.md) — XOR、lowbit、無號右移

## T21 · Math & Geometry

- [T21-21 math](T21-21-math.md) — 進位、取餘、溢位邊界

---

## 主題名合併表

題解 frontmatter 的 `topics` 照抄 LeetCode 官方 tag，同一個主題因此會有多個名字。左欄的任何一個 tag 都寫進右欄那一組筆記，不另開新檔；同一主題可以有基礎篇與總結篇各一份，同職責只有一份。

| 題解 frontmatter 出現過的 tag | 唯一主題筆記 |
| --- | --- |
| `Array`、`Simulation`、`Enumeration` | [T01-21 array](T01-21-array.md) |
| `Sorting`、`Bucket Sort`、`Counting` | [T00-21 sorting](T00-21-sorting.md) |
| `Stack`、`Queue`、`Stack & Queue`、`Monotonic Queue`、`Design` | [T06-21 stack-queue](T06-21-stack-queue.md) |
| `Binary Tree`、`Tree` | [T10-21 binary-tree](T10-21-binary-tree.md) |
| `Heap (Priority Queue)`、`Heap`、`Data Stream` | [T11-21 heap-priority-queue](T11-21-heap-priority-queue.md) |
| `Graph`、`Graph Theory`、`Eulerian Circuit` | [T14-01 graph-basics](T14-01-graph-basics.md)（基礎篇）＋總結篇（待建） |
| `Dynamic Programming`、`DP`、`Memoization` | [T16-21 dynamic-programming](T16-21-dynamic-programming.md) |

## 三個號段

篇序的第一碼記錄**寫的時機**，排出來的順序就是實際的學習動線：

| 號段 | 篇型 | 什麼時候寫 | 寫什麼 |
| --- | --- | --- | --- |
| `0X` | 基礎篇 | **開刷前**，尤其是不熟的主題 | 心智模型、名詞、資料結構、模板、常見陷阱、開刷前自我檢查 |
| `1X` | 子主題 | 刷題途中冒出新主題時 | 該子主題自成一篇；它自己的基礎篇與小結篇也待在這一段，取相鄰號 |
| `2X` | 總結篇 | **刷完一個 pattern 之後** | 實戰手法收斂、題型分類、已刷題目清單、踩過的坑 |

基礎篇的檔名帶 `-basics` 後綴。尚未寫的在本頁標「待建」，不留指向不存在檔案的連結；同主題有多篇時要互相連結。

新增一篇只取該號段末尾的下一號，既有檔名不動。

## 筆記格式

**總結篇**：`# 標題＋概念總結` → `## 解題技巧` → `## 已刷題目`（每行 `- <id>. Title 難度 — 一句話`）→ `## 相關`。

**基礎篇**：`# 標題` → 開頭一段講清楚這是哪一篇、講給誰聽 → 分節講解（可含 mermaid 圖與 Java 模板）→ `## 開刷前自我檢查` → `## 已刷題目`（還沒刷就寫「尚未開始」）→ `## 相關`。

題目連結用相對路徑指向題解檔，例：`[1. Two Sum](../solutions/01-arrays-hashing/lc-1-two-sum.md)`。

## 相關

- [_moc](../_moc.md) — 全題清單、刷題順序與完成狀態
- [repo README](../README.md) — 資料角色、計數口徑與同步邊界
