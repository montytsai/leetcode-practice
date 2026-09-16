# Heap (Priority Queue)

Priority Queue 用 Heap 維護優先順序，適合需要快速取得目前最大值、最小值，或只保留前 K 個元素的題目。

## 解題技巧

- Java `PriorityQueue` 預設先取出最小值；Max-Heap 需要傳入反向 Comparator。
- 固定大小的 Heap 可以把 Top K 題的空間限制在 O(k)。
- 要保留最小的 K 個元素時，用大小為 K 的 Max-Heap；新元素加入後若超過 K 個就移除目前最大值。
- 要持續取得第 K 大元素時，用大小為 K 的 Min-Heap，Heap 頂端就是目前第 K 大值。
- Comparator 若用減法或負值反轉順序，要先確認數值範圍不會 overflow。

## 已刷題目

- [215. Kth Largest Element in an Array](../solutions/11-heap-priority-queue/lc-215-kth-largest-element-in-an-array.md) Medium — 官方標籤包含 Heap (Priority Queue)。
- [239. Sliding Window Maximum](../solutions/04-sliding-window/lc-239-sliding-window-maximum.md) Hard — 官方標籤包含 Heap (Priority Queue)。
- [253. Meeting Rooms II](../solutions/19-intervals/lc-253-meeting-rooms-ii.md) Medium — 用 Min-Heap 取得最早結束的會議。
- [332. Reconstruct Itinerary](../solutions/15-advanced-graphs/lc-332-reconstruct-itinerary.md) Hard — 官方標籤包含 Heap (Priority Queue)。
- [347. Top K Frequent Elements](../solutions/01-arrays-hashing/lc-347-top-k-frequent-elements.md) Medium — 官方標籤包含 Heap (Priority Queue)。
- [703. Kth Largest Element in a Stream](../solutions/11-heap-priority-queue/lc-703-kth-largest-element-in-a-stream.md) Easy — 用 PriorityQueue 維護第 K 大元素。
- [973. K Closest Points to Origin](../solutions/11-heap-priority-queue/lc-973-k-closest-points-to-origin.md) Medium — 用 Max-Heap 移除目前距離最遠的點。

## 相關

- [_moc](../_moc.md)
- [Quickselect](T00-11-quickselect.md)
