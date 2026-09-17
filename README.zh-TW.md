# 🧠 LeetCode Practice with Java

紀錄刷 LeetCode 的過程，主要以 Java 實作，含解題筆記與主題講義。
目標是熟悉演算法與資料結構實作。每題記錄解法、複雜度與掌握程度。

[English](README.md) | 繁體中文

**[📋 刷題總表](_moc.md)** ｜ [主題筆記索引](topics/_index.md) ｜ [題解格式](_solution-template.md) ｜ [官方 AC 紀錄](_leetcode-submission-history.md)

---

## 📁 repo 結構

刷題的工具換過三輪：**Maven 專案** → **Notion** → **Obsidian**。Notion 那批題解已匯出併進 `solutions/`；Maven 那批整包封存在 `archive/`；現在公開的是 Obsidian 這套。

root 只放現行主體。

```
leetcode-practice/
 ├── _moc.md                             # 刷題總表（機器重生，勿手改）
 ├── _leetcode-submission-history.md     # LeetCode 官方 AC 紀錄
 ├── _solution-template.md               # 題解格式、frontmatter 規格與寫作口徑
 ├── solutions/<NN-主題>/                # 一題一檔，_template.md 是新增用的骨架
 ├── topics/                             # 主題筆記，_index.md 是導航
 ├── assets/                             # 主題筆記引用的自繪 SVG 圖解
 ├── scripts/                            # 同步與統計腳本
 └── archive/                            # 2025 年舊資產，已停止新增
     ├── pom.xml ＋ src/                 # Maven 專案：Java 解法與 JUnit 測試
     └── doc/                            # 每日刷題筆記與早期主題筆記
```

---

## 🗂️ 題目分類與筆記

**分類依據**：採 **NeetCode** 的主題體系，加上幾個它沒有、但我需要的分類。

**刷題順序**：由**資料夾序號**搭配**每題 frontmatter 的序號**兩層決定，見[資料怎麼組織](#-資料怎麼組織)，總表 [`_moc.md`](_moc.md) 按順序自動排好。

**筆記兩種**：**主題筆記**跟著題型走，**橫切筆記**跨所有題型。總索引在 [`topics/_index.md`](topics/_index.md)。

### 橫切筆記

不綁題型，刷哪一類都用得到。複雜度分析是開刷前讀的第一篇。

[複雜度分析](topics/T00-01-complexity-analysis.md) · [排序](topics/T00-21-sorting.md) · [分治](topics/T00-22-divide-and-conquer.md) · [Quickselect](topics/T00-11-quickselect.md)

### 主題筆記

依**寫的時機**分兩篇：**開刷前**寫基礎篇建立心智模型，**刷完**一個 pattern 後寫總結篇收斂常見手法。主題名連到該類的題解資料夾。

| # | 主題 | 開刷前讀 | 刷完後寫 |
|---|---|---|---|
| 01 | [Arrays & Hashing](solutions/01-arrays-hashing/) | — | [array](topics/T01-21-array.md) · [hash-table](topics/T01-22-hash-table.md) · [matrix](topics/T01-11-matrix.md) |
| 02 | [Two Pointers](solutions/02-two-pointers/) | — | [two-pointers](topics/T02-21-two-pointers.md) |
| 03 | [String](solutions/03-string/) | — | [string](topics/T03-21-string.md) · [string-matching](topics/T03-11-string-matching.md) |
| 04 | [Sliding Window](solutions/04-sliding-window/) | — | [sliding-window](topics/T04-21-sliding-window.md) |
| 05 | [Prefix Sum](solutions/05-prefix-sum/) | — | [prefix-sum](topics/T05-21-prefix-sum.md) |
| 06 | [Stack](solutions/06-stack/) | — | [stack-queue](topics/T06-21-stack-queue.md) |
| 07 | [Monotonic Stack](solutions/07-monotonic-stack/) | — | — |
| 08 | [Binary Search](solutions/08-binary-search/) | — | [binary-search](topics/T08-21-binary-search.md) |
| 09 | [Linked List](solutions/09-linked-list/) | — | — |
| 10 | [Trees](solutions/10-trees/) | — | [binary-tree](topics/T10-21-binary-tree.md) · [binary-search-tree](topics/T10-11-binary-search-tree.md) · [dfs](topics/T10-12-depth-first-search.md) · [bfs](topics/T10-13-breadth-first-search.md) |
| 11 | [Heap / Priority Queue](solutions/11-heap-priority-queue/) | — | [heap-priority-queue](topics/T11-21-heap-priority-queue.md) |
| 12 | [Backtracking](solutions/12-backtracking/) | — | [backtracking](topics/T12-21-backtracking.md) |
| 13 | [Tries](solutions/13-tries/) | [trie](topics/T13-01-trie-basics.md) | — |
| 14 | [Graphs](solutions/14-graphs/) | [圖論](topics/T14-01-graph-basics.md) · [DFS](topics/T14-02-depth-first-search-basics.md) · [BFS](topics/T14-03-breadth-first-search-basics.md) | — |
| 15 | [Advanced Graphs](solutions/15-advanced-graphs/) | [拓撲排序](topics/T15-01-topological-sort-basics.md) | — |
| 16 | [1-D Dynamic Programming](solutions/16-one-d-dp/) | — | [dynamic-programming](topics/T16-21-dynamic-programming.md) |
| 17 | [2-D Dynamic Programming](solutions/17-two-d-dp/) | — | [dynamic-programming](topics/T16-21-dynamic-programming.md) |
| 18 | [Greedy](solutions/18-greedy/) | — | [greedy](topics/T18-21-greedy.md) |
| 19 | [Intervals](solutions/19-intervals/) | — | [intervals](topics/T19-21-intervals.md) |
| 20 | [Bit Manipulation](solutions/20-bit-manipulation/) | — | [bit-manipulation](topics/T20-21-bit-manipulation.md) |
| 21 | [Math & Geometry](solutions/21-math-geometry/) | — | [math](topics/T21-21-math.md) |

---

## 🏷️ frontmatter

每份題解開頭的 YAML。狀態、順序、分類都住在這裡，`_moc.md` 是由它們重生出來的檢視。

欄位一覽、各欄值域與填寫規則在[題解格式正本](_solution-template.md)。特別設計欄位 `status`：

### 狀態階梯

它記錄的不只是「解了沒」，還有「能不能自己解出來」：

| 狀態 | 意思 |
|---|---|
| `todo` | 還沒刷 |
| `ac-unknown` | 解過，但沒記錄當時是怎麼解出來的 |
| `ac-assisted` | 靠提示、看解答才寫出來 |
| `ac-solo` | 自己獨立解出來 |
| `mastered` | 重刷仍能獨立解出 |
| `review` | 已解，但要再刷一次 |

*註：早期題目多為 `ac-unknown`，當時沒記，不回頭編造。*

---

## 📐 資料怎麼組織

| 維度          | 住哪                  | 怎麼變動            |
| ----------- | ------------------- | --------------- |
| 大順序         | 資料夾序號 `01`–`21`     | 手動              |
| 小順序         | frontmatter `order` | 手動；腳本只讀不寫，不重新編號 |
| 分類、狀態、來源、平台 | frontmatter         | 刷完該題時更新         |
| 檢視          | `_moc.md`           | `gen-moc.py` 重生 |

**主題筆記編碼**：`topics/` 檔名為 `T<主題號2碼>-<篇序2碼>-<slug>.md`。主題號對應 `solutions/` 的資料夾序號，`00` 是橫切筆記；篇序第一碼是寫的時機——`0X` 基礎篇（開刷前）、`1X` 子主題（刷的過程中冒出）、`2X` 總結篇（刷完收斂）。排出來的字母序就是學習動線。

**權威排序**：

1. 題解 frontmatter 是狀態、順序、分類的單一事實來源。
2. [`_leetcode-submission-history.md`](_leetcode-submission-history.md) 是官方 AC 的權威，只增量新增、不改寫既有列。
3. [`_moc.md`](_moc.md) 由 1 重生，**不是資料正本**——砍掉可以重生，手改會被下次重生蓋掉。

**計數口徑**：`system total = 官方 LeetCode AC + 非 LeetCode 平台已解題`。後者指 `platform` 不是 `LeetCode` 的題，那些在官方紀錄裡查不到，由欄位認定。`_moc.md` header 的 `done:` 就是 system total。

---

## 🚀 執行方式

`solutions/` 與 `topics/` 是純 Markdown，用 Obsidian 開有雙向連結與 Base 檢視，用一般編輯器或直接在 GitHub 上讀也行，不需要任何工具。

腳本要有 Python 3（`lc-stats.sh` 另外需要 `curl`）：

```bash
python scripts/gen-moc.py          # 由題解 frontmatter 重生 _moc.md
python scripts/lc-sync-history.py  # 從官方 API 增量同步 AC 紀錄
bash   scripts/lc-stats.sh         # 對帳官方題數與本地紀錄
```

2025 年那批 Java 實作的編譯與測試方式，見 [`archive/README.md`](archive/README.md)。

---

## 📚 資源與出處

**題單**：主線是 [Grind75](https://www.techinterviewhandbook.org/grind75/)，餘力續刷[《代碼隨想錄》](https://programmercarl.com/)，另有 [LeetCode 75](https://leetcode.com/studyplan/leetcode-75/) 與零星的[每週競賽題](https://leetcode.com/contest/)。每題出自哪一份，記在 frontmatter 的 `source`。

**分類體系**：[NeetCode](https://neetcode.io/) 的主題分類，輔以額外主題。

**筆記**：基礎篇（圖論、DFS、BFS、拓撲排序）理論骨架多參考《代碼隨想錄》對應章節，文字為自行重寫，程式碼由 C++ 改寫為 Java。

---

## 📝 備註

Hang in there and keep holding on!
