---
title: "Implement Queue using Stacks"
difficulty: Easy
topics: [Stack & Queue, Stack, Design, Queue]
category: 06-stack
order: 1
source: [Grind75, Carl]
platform: LeetCode
status: ac-unknown
note: ""
date_created: 2025-04-28
date_updated: 2026-07-19
---


## 2025 初刷版（Day15，2025-05-02）

*原文見 [archive/doc/daily/day15-2025-05-02.md](../../archive/doc/daily/day15-2025-05-02.md)，已停更，內容按當時所寫原樣搬入*

### 232. Implement Queue using Stacks 重點整理

#### 題目說明

- 使用兩個 Stack（後進先出）來模擬 Queue（先進先出）行為。  
- 限制條件如下：
  - 只能使用 Stack 標準操作：`push(x)`、`pop()`、`peek()`、`size()`、`isEmpty()`。
  - 所有操作應具備良好效能（均攤時間複雜度 O(1)）。

#### 解法：雙 Stack 模擬

##### 思路
- 使用兩個 Stack：
    - `sIn`: 負責 push（進隊）
    - `sOut`: 負責 pop / peek（出隊）
- 每次 pop 或 peek 時，若 `sOut` 為空，將 `sIn` 所有元素倒入 `sOut`，保證 FIFO。
- 利用 lazy-load 策略，避免每次操作都轉移元素。

##### 複雜度分析
- **時間複雜度：**
    - `push()`: O(1)
    - `pop()`, `peek()`: 均攤 O(1)，因為每個元素最多只會被搬移一次（從 `sIn` 到 `sOut`）
    - `empty()`: O(1)
- **空間複雜度：** O(n)
    - 使用兩個 stack 儲存 n 個元素

#### Java 程式碼：
- [ID232ImplementQueueUsingStacks.java](../../archive/src/main/java/io/github/monty/leetcode/stackqueue/ID232ImplementQueueUsingStacks.java)
