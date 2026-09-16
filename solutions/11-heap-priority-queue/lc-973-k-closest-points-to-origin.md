---
title: "K Closest Points to Origin"
difficulty: Medium
topics: [Heap, Array, Math, Divide and Conquer, Geometry, Sorting, Heap (Priority Queue), Quickselect]
category: 11-heap-priority-queue
order: 3
source: [Grind75]
platform: LeetCode
url: https://leetcode.com/problems/k-closest-points-to-origin/
status: ac-unknown
note: ""
date_created: 2026-08-17
date_updated: 2026-08-17
---

## 心得

取距離最近的前 K 個點時，可以直覺想到兩條路：把全部點依距離排序後取前 K 個，或維護大小為 K 的 Max-Heap，只保留目前最近的 K 個點。

排序解最直接，時間是 O(n log n)，空間是 O(n)：Java 對物件陣列排序需要輔助空間，回傳結果也會複製前 K 個元素。Max-Heap 解把時間降為 O(n log k)，空間為 O(k)，更適合 K 遠小於 n 的情況。

查完後知道這題還能用 Quickselect 優化，但本次只完成排序與 Max-Heap 兩種 AC 解法；Quickselect 留待之後研究。

## Java

### Sorting

```java
class Solution {
    public int[][] kClosest(int[][] points, int k) {
        // Sort the points by their squared distance from the origin.
        Arrays.sort(
            points,
            Comparator.comparingInt(point -> point[0] * point[0] + point[1] * point[1])
        );

        // Return a copy of the first k points.
        return Arrays.copyOf(points, k);
    }
}
```

- 時間：O(n log n)，因為需要排序全部 n 個點。
- 空間：O(n)，因為 Java 的物件陣列排序需要輔助空間，且結果副本使用 O(k) 空間；k 不超過 n。

### Max-Heap

```java
class Solution {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            k + 1,
            Comparator.comparingInt(point -> -(point[0] * point[0] + point[1] * point[1]))
        );

        for (int[] point : points) {
            maxHeap.offer(point);

            // Remove the farthest point when the heap has more than k points.
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        return maxHeap.toArray(new int[k][]);
    }
}
```

- 時間：O(n log k)，因為每個點都會進入大小最多為 k + 1 的 Heap。
- 空間：O(k)，因為 Heap 最多保存 k + 1 個點，回傳陣列保存 k 個參考。

## 相關

- [Array](../../topics/T01-21-array.md) — 輸入與輸出都是點座標陣列
- [Sorting](../../topics/T00-21-sorting.md) — 排序後直接取前 K 個點
- [Heap (Priority Queue)](../../topics/T11-21-heap-priority-queue.md) — 用 PriorityQueue 保留最近的 K 個點
- [Quickselect](../../topics/T00-11-quickselect.md) — 官方標籤中的最佳化方向，本次尚未實作
- [LeetCode 刷題總覽](../../_moc.md)
