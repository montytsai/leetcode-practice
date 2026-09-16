# Quickselect

Quickselect 用 partition 把目標位置移到正確區間，不需要把全部元素完整排序。平均時間是 O(n)，適合只需要第 K 個元素或前 K 個元素的題目；最差時間仍可能是 O(n²)。

## 解題技巧

- 每次選一個 pivot，完成 partition 後比較 pivot 位置與目標位置。
- 只繼續處理包含目標位置的那一側，不需要像 Quicksort 一樣處理兩側。
- 本題目前只有閱讀到 Quickselect 這個方向，尚未完成實作；之後研究時要特別驗證 partition 邊界與重複值。

## 已刷題目

- [215. Kth Largest Element in an Array](../solutions/11-heap-priority-queue/lc-215-kth-largest-element-in-an-array.md) Medium — 官方標籤包含 Quickselect。
- [347. Top K Frequent Elements](../solutions/01-arrays-hashing/lc-347-top-k-frequent-elements.md) Medium — 官方標籤包含 Quickselect。
- [973. K Closest Points to Origin](../solutions/11-heap-priority-queue/lc-973-k-closest-points-to-origin.md) Medium — 已知可用 Quickselect 優化，但本次尚未實作。

## 相關

- [_moc](../_moc.md)
- [Heap (Priority Queue)](T11-21-heap-priority-queue.md)
- [Divide and Conquer](T00-22-divide-and-conquer.md)
