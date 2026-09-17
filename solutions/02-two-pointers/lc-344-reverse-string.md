---
title: "Reverse String"
difficulty: Easy
topics: [String, Two Pointers]
category: 02-two-pointers
order: 3
source: [Carl]
platform: LeetCode
status: ac-unknown
note: ""
date_created: 2025-04-26
date_updated: 2025-04-26
---


## 2025 初刷版（Day11，2025-04-28）

*原文見 [archive/doc/daily/day11-2025-04-28.md](../../archive/doc/daily/day11-2025-04-28.md)，已停更，內容按當時所寫原樣搬入*

### 344. Reverse String 重點整理

#### 題目說明
- 給定字元陣列 char[]，原地反轉字串。
- **限制：**
  - 不可使用額外陣列（空間複雜度 O(1)）。 
  - 字元皆為 ASCII 可列印字元。

#### 解法：雙指針法
- 設定兩個指針（index）： 
  - `left` 從頭開始 
  - `right` 從尾開始 
- 同時向中間靠攏，並交換元素。 
- 交換可以用： 
  - 臨時變數 temp 
  - 或是位運算（但不建議，影響可讀性）
- 複雜度：
  - 時間複雜度：O(n)。
  - 空間複雜度：O(1)。
  
#### 庫函數使用原則
- 如果**核心邏輯**可直接由庫函數完成，**不要用**，自己手寫。
- 如果庫函數只是小部分、且理解實現原理，可酌情使用。

#### Java 程式碼
- [ID344ReverseString.java](../../archive/src/main/java/io/github/monty/leetcode/string/ID344ReverseString.java)
