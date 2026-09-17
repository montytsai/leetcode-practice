---
title: "Implement Stack Using Queues"
difficulty: Easy
topics: [Stack & Queue, Stack, Design, Queue]
category: 06-stack
order: 2
source: [Carl]
platform: LeetCode
status: ac-unknown
note: ""
date_created: 2025-05-02
date_updated: 2025-05-02
---


## 2025 初刷版（Day15，2025-05-02）

*原文見 [archive/doc/daily/day15-2025-05-02.md](../../archive/doc/daily/day15-2025-05-02.md)，已停更，內容按當時所寫原樣搬入*

#### 225. Implement Stack using Queues 重點整理

##### 題目說明
實作一個 LIFO Stack，僅能使用 Queue 的標準操作（`offer(x)`, `poll()`, `peek()`, `isEmpty()`），不允許使用額外資料結構（如陣列或 LinkedList 的 Stack 操作）。

##### 解法：單 Queue 模擬 Stack
使用單一 Queue，每次 `pop()` 或 `top()` 操作前，將 queue 中前 n-1 個元素移至尾端，保留最後一個元素當作 Stack 頂端。

##### 方法
- `push(x)`: 直接用 `offer(x)` 放到 queue 尾端。
- `pop()`: 將前 n-1 個元素移到尾端，poll() 剩下的最後一個（即 stack top）。
- `top()`: 同上，但最後的元素需重新放回 queue。
- `empty()`: queue 為空即表示 stack 為空。

##### 複雜度分析
| 操作    | 時間複雜度 | 空間複雜度 |
|-------|-------|-------|
| push  | O(1)  | O(n)  |
| pop   | O(n)  | O(n)  |
| top   | O(n)  | O(n)  |
| empty | O(1)  | O(1)  |

##### 小結
這種設計是以 **push O(1)**、**pop/top O(n)** 為取捨，也可改為 push O(n)、pop/top O(1) 的版本，但本實作較符合一般使用情境（頻繁 push）。

#### Java 程式碼：
- [ID225ImplementStackUsingQueues.java](../../archive/src/main/java/io/github/monty/leetcode/stackqueue/ID225ImplementStackUsingQueues.java)
