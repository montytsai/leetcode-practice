# Dynamic Programming

Dynamic Programming 適合有重疊子問題與可重用狀態的題目。先定義狀態代表什麼，再寫出狀態轉移、初始值與遍歷順序；如果目前狀態只依賴前一個狀態，可以壓縮空間。

## 解題技巧

- 先用一句話定義 `dp[i]`，避免只背公式卻不知道每個值的意義。
- 初始值要符合題目的合法答案範圍；題目要求 non-empty 時，不能隨意用 0 當答案。
- 若 `dp[i]` 只依賴 `dp[i - 1]`，可用一個變數保存前一個狀態，把空間從 O(n) 降到 O(1)。
- Maximum Subarray 的壓縮狀態是「以目前位置結尾的最大子陣列總和」：保留有幫助的前綴，否則從目前元素重算。
- 二維 DP 若四個方向會互相依賴，可拆成兩次相反方向掃描；每一輪只參考已完成的方向。

## 已刷題目

- [Jump Game II](../solutions/18-greedy/lc-45-jump-game-ii.md) Medium — 比較各步能到達的範圍
- [Maximum Subarray](../solutions/18-greedy/lc-53-maximum-subarray.md) Medium — 壓縮「以目前位置結尾」的最佳狀態
- [Jump Game](../solutions/18-greedy/lc-55-jump-game.md) Medium — 維護目前可以到達的最遠位置
- [Climbing Stairs](../solutions/16-one-d-dp/lc-70-climbing-stairs.md) Easy — 從前兩個狀態推得目前方法數
- [Best Time to Buy and Sell Stock](../solutions/04-sliding-window/lc-121-best-time-to-buy-and-sell-stock.md) Easy — 維護最低買價與最大收益
- [Best Time to Buy and Sell Stock II](../solutions/01-arrays-hashing/lc-122-best-time-to-buy-and-sell-stock-ii.md) Medium — 累積每段可取得的正收益
- [Palindrome Partitioning](../solutions/12-backtracking/lc-131-palindrome-partitioning.md) Medium — 預先判斷回文區間後回溯切割
- [Counting Bits](../solutions/20-bit-manipulation/lc-338-counting-bits.md) Easy — 從較小數字重用位元計數結果
- [Wiggle Subsequence](../solutions/18-greedy/lc-376-wiggle-subsequence.md) Medium — 維護上升與下降結尾的最佳長度
- [Is Subsequence](../solutions/01-arrays-hashing/lc-392-is-subsequence.md) Easy — 依序匹配兩個字串的狀態
- [Non-overlapping Intervals](../solutions/19-intervals/lc-435-non-overlapping-intervals.md) Medium — 比較保留相容區間的最佳結果
- [01 Matrix](../solutions/14-graphs/lc-542-01-matrix.md) Medium — 以兩次相反方向掃描消除四方向的循環依賴
- [Binary Tree Cameras](../solutions/18-greedy/lc-968-binary-tree-cameras.md) Hard — 依節點狀態決定相機配置
- [Longest ZigZag Path in a Binary Tree](../solutions/10-trees/lc-1372-longest-zigzag-path-in-a-binary-tree.md) Medium — 在樹上傳遞左右交替的路徑狀態
- [Longest Subarray of 1's After Deleting One Element](../solutions/04-sliding-window/lc-1493-longest-subarray-of-1-s-after-deleting-one-element.md) Medium — 維護刪除前後的連續長度
- [Minimum Increase to Maximize Special Indices](../solutions/18-greedy/lc-3891-minimum-increase-to-maximize-special-indices.md) Medium — 比較局部選擇形成的最佳狀態

## 相關

- [_moc](../_moc.md)
- [greedy](T18-21-greedy.md) — 有些一維 DP 可化成局部最佳選擇
- [array](T01-21-array.md) — 常見的線性狀態來源
- [matrix](T01-11-matrix.md) — 二維 DP 的常見來源
