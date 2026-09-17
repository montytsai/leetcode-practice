---
title: "Reverse Words in a String"
difficulty: Medium
topics: [String, Two Pointers]
category: 02-two-pointers
order: 5
source: [Carl, LeetCode75]
platform: LeetCode
status: ac-unknown
note: ""
date_created: 2026-02-24
date_updated: 2026-02-24
---


## 2025 初刷版（Day11，2025-04-28）

*原文見 [archive/doc/daily/day11-2025-04-28.md](../../archive/doc/daily/day11-2025-04-28.md)，已停更，內容按當時所寫原樣搬入*

### 151. Reverse Words in a String 重點整理

#### 題目說明
- 給定一個字串 `s`，請反轉其中「單詞」的順序。
- 「單詞」指的是非空格字元的連續序列。
- **注意：**
  - 輸入可能包含前導空格、尾隨空格或單詞間多個空格。
  - 輸出需每個單詞間僅留一個空格，且不含額外空格。
- 題目要求：**空間複雜度 O(1)**。

#### 解法一：原地反轉（空間 O(1)）

##### 思路
1. **移除多餘空格並壓縮單字**
   - 跳過前置空格。 
   - 將單字逐字拷貝至新位置，單字之間補一個空格。
2. **反轉整個有效字串**
3. **反轉每個單字**

- 時間複雜度: O(n)，需遍歷三次：壓縮、整體反轉、單字反轉。
- 空間複雜度: O(1)，在原陣列內操作。

#### 解法二：使用 split()（空間 O(n)）

##### 思路
- 使用 trim() 去除前後空格，搭配 split("\\s+") 以正規表達式分割單字。
- 從後向前組合，單字間補一個空格。
- 複雜度
  - 時間複雜度: O(n)
  - 空間複雜度: O(n)，需要一個新的字串陣列。

##### 注意
- split(" ") 會將連續空格切成多個空字串，效率較差。 
- 使用 split("\s+") 可以避免此問題。

#### Java 程式碼
- [ID151ReverseWordsInAString.java](../../archive/src/main/java/io/github/monty/leetcode/string/ID151ReverseWordsInAString.java)
