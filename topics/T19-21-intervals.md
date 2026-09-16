# Intervals

Interval 題的本質是處理時間範圍之間的重疊、合併或資源分配。看到題目需要比較 `start`、`end`，或問最少房間、最多不重疊區間時，可以先考慮依端點排序，再用 Greedy 或 Heap 維護目前狀態。

## 解題技巧

1. 先確認相等端點是否算重疊，例如一場會議在另一場結束時開始，通常可以共用房間。
2. 依 `start` 排序：適合依時間順序加入區間，並維護已存在的狀態。
3. 依 `end` 排序：適合 Greedy 選出最多不重疊區間，或移除最少區間。
4. 需要同時追蹤多個結束時間時，使用 Min-Heap 快速取得最早的 `end`。
5. 只需判斷是否有重疊時，依 `start` 排序後比較相鄰區間即可，不需要 Heap。
6. 插入區間可分成三階段：先收集不重疊前綴，再維護 merged range 合併重疊區間，最後追加不重疊後綴；index 全程只往前走。

常見陷阱：排序後不會再遇到更早的 `start`，因此不需要為未來保留已經錯過的空檔；另外避免用相減撰寫 comparator，以免整數溢位。

## 已刷題目

- [Merge Intervals](../solutions/19-intervals/lc-56-merge-intervals.md) Medium — 依 start 排序後線性掃描,重疊就更新右邊界合併。
- [Insert Interval](../solutions/19-intervals/lc-57-insert-interval.md) Medium — 以「前綴、合併區、後綴」三階段完成插入與合併。
- [Non-overlapping Intervals](../solutions/19-intervals/lc-435-non-overlapping-intervals.md) Medium — 依結束時間做 Greedy，保留更早結束的區間。
- [Minimum Number of Arrows to Burst Balloons](../solutions/18-greedy/lc-452-minimum-number-of-arrows-to-burst-balloons.md) Medium — 用共同右邊界覆蓋重疊的氣球。
- [Meeting Rooms](../solutions/19-intervals/lc-252-meeting-rooms.md) Easy — 排序後只比較相鄰會議，快速判斷是否重疊。
- [Meeting Rooms II](../solutions/19-intervals/lc-253-meeting-rooms-ii.md) Medium — 排序 start，再用 Min-Heap 復用最早結束的房間。

## 相關

- [_moc](../_moc.md)
