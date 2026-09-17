# Matrix

Matrix 題把資料放在二維座標中，常見操作包含逐格掃描、四方向移動、邊界檢查，以及保存每個位置的狀態。

## 解題技巧

- 先確認 `row`、`col` 的有效範圍，再讀取相鄰格，避免超出邊界。
- 四方向問題可使用方向陣列、DFS、BFS，或依狀態依賴設計 DP 掃描順序。
- 若目前狀態需要相反方向的結果，可以用兩次相反方向掃描分開處理。
- 多個起點都能向外擴散時，可把所有起點一起放入 queue 做 Multi-source BFS；若直接把距離寫回輸入 matrix，要先確認 input mutation 可接受。

## 已刷題目

- [54. Spiral Matrix](../solutions/21-math-geometry/lc-54-spiral-matrix.md) Medium — 用四邊界收縮讀出螺旋順序，收縮的邊界 guard 是重點。
- [59. Spiral Matrix II](../solutions/21-math-geometry/lc-59-spiral-matrix-ii.md) Medium — 依邊界逐圈填入二維矩陣。
- [542. 01 Matrix](../solutions/14-graphs/lc-542-01-matrix.md) Medium — 用 Two-pass DP 或 Multi-source BFS 求每格的最近距離。
- [733. Flood Fill](../solutions/14-graphs/lc-733-flood-fill.md) Easy — 從起點向四個方向擴散填色。
- [2352. Equal Row and Column Pairs](../solutions/01-arrays-hashing/lc-2352-equal-row-and-column-pairs.md) Medium — 比較矩陣中的 row 與 column。

## 相關

- [_moc](../_moc.md)
- [Array](T01-21-array.md)
- [Dynamic Programming](T16-21-dynamic-programming.md)
- [Breadth-First Search](T10-13-breadth-first-search.md)
