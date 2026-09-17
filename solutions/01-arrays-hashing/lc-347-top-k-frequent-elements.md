---
title: "Top K Frequent Elements"
difficulty: Medium
topics: [Stack & Queue, Array, Hash Table, Divide and Conquer, Sorting, Heap (Priority Queue), Bucket Sort, Counting, Quickselect]
category: 01-arrays-hashing
order: 9
source: [Carl]
platform: LeetCode
status: ac-unknown
note: ""
date_created: 2025-05-04
date_updated: 2025-05-04
---


## 2025 初刷版（Day18，2025-05-05）

*原文見 [archive/doc/daily/day18-2025-05-05.md](../../archive/doc/daily/day18-2025-05-05.md)，已停更，內容按當時所寫原樣搬入*

### 347. Top K Frequent Elements 重點整理

#### 題目說明

- 給定一個非空整數陣列 `nums`，返回其中出現頻率前 `k` 高的元素。
- 要求時間複雜度優於 O(n log n)。

#### 解法一：List sort 排序 / stream API 排序

##### 思路

- 使用 `HashMap` 統計每個元素出現的頻率。
- 將 `map.entrySet()` 轉為 `List`，再以 value 進行降序排序。
- 取排序後前 k 個元素的 key 作為結果。

##### 重點

- 使用 `List.sort()` 搭配 comparator。
- 排序操作會處理所有元素，故時間複雜度為 O(n log n)。

##### 複雜度分析

- 時間複雜度：O(n log n)
- 空間複雜度：O(n)

#### 解法二：Heap（優先佇列）

##### 思路

- 使用 `HashMap` 統計每個元素的出現頻率。
- 使用 `PriorityQueue` 建立小根堆（Min Heap），依據頻率排序。
- 當堆大小超過 k，就移除最小頻率的元素，最終堆中保留頻率最高的前 k 個元素。

##### 重點

- 若使用 Max Heap，需將所有元素都放入 heap，時間為 O(n log n)。
- 本題透過維護大小為 k 的 Min Heap，可將時間複雜度降為 O(n log k)。

##### 複雜度分析

- 時間複雜度：O(n log k)
- 空間複雜度：O(n)

##### 補充：Heap

#### 補充：Heap 教學簡述

- **Heap（堆）** 是一種特殊的**完全二元樹（Complete Binary Tree）**，常用於處理「Top K」、「排序」、「排程」等問題。
- 本質是「優先佇列」的實作方式，父節點與子節點的值會維持特定大小關係。

##### 結構示意（Min Heap）
```
        1
       / \
      3   5
     / \    
    4   6
```
- 根節點是最小值 1
- 每個節點的值都不小於它的父節點

##### Min Heap vs Max Heap

| 類型      | Min Heap                    | Max Heap                               |
|---------|-----------------------------|----------------------------------------|
| 定義      | 根節點為最小值，父節點小於子節點            | 根節點為最大值，父節點大於子節點                       |
| Java 實作 | `new PriorityQueue<>()`（預設） | `new PriorityQueue<>((a, b) -> b - a)` |
| 使用情境    | 維護 Top K 大的元素（最小在頂部）        | 一次性取得前 K 大元素                           |

#### 方法比較總結

| 方法          | 是否按 value 排序    | 時間複雜度      | 適用情況            |
|-------------|-----------------|------------|-----------------|
| TreeMap     | ❌ 無法直接按 value 排 | O(n log n) | 不推薦用於本題         |
| List sort   | ✅               | O(n log n) | 簡單直觀，能滿足需求      |
| Heap        | ✅               | O(n log k) | 最推薦，高效且記憶體使用低   |
| Bucket sort | ✅               | O(n)       | 頻率範圍小時效率極高（未實作） |

#### Java 程式碼連結

- [ID347TopKFrequentElements.java](../../archive/src/main/java/io/github/monty/leetcode/stackqueue/ID347TopKFrequentElements.java)
