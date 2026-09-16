# Greedy

Greedy 的核心是在每一步做當下安全的最佳選擇，並證明這個選擇不會破壞全域最佳解。看到「保留有利資訊、立即丟棄無利資訊」或排序後只需做局部決定時，可以考慮 Greedy。

## 解題技巧

- 先說清楚局部選擇是什麼，以及為什麼丟棄的狀態不可能讓後續答案更好。
- 區間題常依起點或終點排序，再保留最能為後續留下空間的區間。
- Maximum Subarray 中，負的前綴和只會降低後續子陣列總和，因此可以直接丟棄並從目前元素重算。
- 小心題目的合法答案：subarray 是 non-empty 時，全負數輸入仍必須選一個元素。

## 已刷題目

- [Container With Most Water](../solutions/02-two-pointers/lc-11-container-with-most-water.md) Medium — 移動限制面積的較短邊
- [Jump Game II](../solutions/18-greedy/lc-45-jump-game-ii.md) Medium — 每一層選擇可到達的最遠範圍
- [Maximum Subarray](../solutions/18-greedy/lc-53-maximum-subarray.md) Medium — 丟棄只會拖累後續的負前綴和
- [Jump Game](../solutions/18-greedy/lc-55-jump-game.md) Medium — 持續更新目前可到達的最遠位置
- [Merge Intervals](../solutions/19-intervals/lc-56-merge-intervals.md) Medium — 排序後合併重疊區間
- [Best Time to Buy and Sell Stock II](../solutions/01-arrays-hashing/lc-122-best-time-to-buy-and-sell-stock-ii.md) Medium — 收集所有正向價差
- [Gas Station](../solutions/18-greedy/lc-134-gas-station.md) Medium — 總量足夠時跳過失敗起點
- [Candy](../solutions/18-greedy/lc-135-candy.md) Hard — 從兩個方向滿足局部大小關係
- [Meeting Rooms II](../solutions/19-intervals/lc-253-meeting-rooms-ii.md) Medium — 優先重用最早結束的會議室
- [Increasing Triplet Subsequence](../solutions/01-arrays-hashing/lc-334-increasing-triplet-subsequence.md) Medium — 保留更小的候選值增加後續機會
- [Wiggle Subsequence](../solutions/18-greedy/lc-376-wiggle-subsequence.md) Medium — 只保留對下一次轉折最有利的端點
- [Queue Reconstruction by Height](../solutions/18-greedy/lc-406-queue-reconstruction-by-height.md) Medium — 排序後在局部正確位置插入
- [Longest Palindrome](../solutions/18-greedy/lc-409-longest-palindrome.md) Easy — 盡量使用所有成對字元
- [Non-overlapping Intervals](../solutions/19-intervals/lc-435-non-overlapping-intervals.md) Medium — 保留較早結束的區間
- [Minimum Number of Arrows to Burst Balloons](../solutions/18-greedy/lc-452-minimum-number-of-arrows-to-burst-balloons.md) Medium — 用同一支箭覆蓋最多重疊區間
- [Assign Cookies](../solutions/18-greedy/lc-455-assign-cookies.md) Easy — 用最小可滿足的餅乾配對
- [Can Place Flowers](../solutions/01-arrays-hashing/lc-605-can-place-flowers.md) Easy — 能安全種花時立即使用位置
- [Dota2 Senate](../solutions/18-greedy/lc-649-dota2-senate.md) Medium — 優先封鎖下一個對手
- [Monotone Increasing Digits](../solutions/18-greedy/lc-738-monotone-increasing-digits.md) Medium — 違反單調時降低前位並填滿後綴
- [Partition Labels](../solutions/18-greedy/lc-763-partition-labels.md) Medium — 到達目前最遠邊界就切割
- [Lemonade Change](../solutions/18-greedy/lc-860-lemonade-change.md) Easy — 優先保留更有彈性的零錢
- [Binary Tree Cameras](../solutions/18-greedy/lc-968-binary-tree-cameras.md) Hard — 優先在未覆蓋節點的父節點放相機
- [Maximize Sum Of Array After K Negations](../solutions/18-greedy/lc-1005-maximize-sum-of-array-after-k-negations.md) Easy — 優先翻轉最小的負數
- [Minimum Increase to Maximize Special Indices](../solutions/18-greedy/lc-3891-minimum-increase-to-maximize-special-indices.md) Medium — 以局部增量換取最多特殊位置

## 相關

- [_moc](../_moc.md)
- [dynamic-programming](T16-21-dynamic-programming.md) — 若局部選擇無法安全證明，改保留多個狀態
- [intervals](T19-21-intervals.md) — 排序後貪婪選擇的常見題型
