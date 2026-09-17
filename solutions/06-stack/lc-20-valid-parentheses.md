---
title: "Valid Parentheses"
difficulty: Easy
topics: [Stack & Queue, String, Stack]
category: 06-stack
order: 3
source: [Grind75, Carl]
platform: LeetCode
status: ac-unknown
note: ""
date_created: 2025-05-02
date_updated: 2026-07-13
---


## 2025 初刷版（Day16，2025-05-03）

*原文見 [archive/doc/daily/day16-2025-05-03.md](../../archive/doc/daily/day16-2025-05-03.md)，已停更，內容按當時所寫原樣搬入*

### 20. Valid Parentheses 重點整理

#### 題目說明

- 給定一個只包含 `'('`, `')'`, `'{'`, `'}'`, `'['`, `']'` 的字串，判斷其是否為合法的括號組合。
- 合法的定義為：
  - 所有左括號都需有正確類型的右括號閉合。
  - 括號需以正確順序配對。

#### 解法：使用 Stack 匹配括號

##### 思路
- 使用 Stack 來追蹤預期出現的右括號。
- 遇到左括號時，將其對應的右括號壓入 Stack。
- 遇到右括號時，與 Stack 頂端元素比對，不符則直接回傳 `false`。
- 最終 Stack 應為空，表示所有括號都有正確閉合。

##### 複雜度分析
- Time Complexity: O(n)，每個字元最多進出 Stack 一次。
- Space Complexity: O(n)，最壞情況下所有字元都是左括號。

#### Java 程式碼：
- [ID20ValidParentheses.java](../../archive/src/main/java/io/github/monty/leetcode/stackqueue/ID20ValidParentheses.java)
