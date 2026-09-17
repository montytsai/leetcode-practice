---
title: "Remove All Adjacent Duplicates In String"
difficulty: Easy
topics: [Stack & Queue, String, Stack]
category: 06-stack
order: 4
source: [Carl]
platform: LeetCode
status: ac-unknown
note: ""
date_created: 2025-05-03
date_updated: 2025-05-03
---


## 2025 初刷版（Day16，2025-05-03）

*原文見 [archive/doc/daily/day16-2025-05-03.md](../../archive/doc/daily/day16-2025-05-03.md)，已停更，內容按當時所寫原樣搬入*

### 1047. Remove All Adjacent Duplicates In String 重點整理

#### 題目說明

給定一個只包含小寫字母的字串 `s`，不斷移除相鄰且相同的字元對，直到無法再移除為止，回傳處理後的結果字串。

#### 解法：使用 Stack 模擬消除相鄰重複字元

##### 思路

- 使用 Stack 來追蹤尚未配對的字元。
- 每次讀取新字元時，如果與 Stack 頂端相同，表示可抵銷 → pop。
- 否則 push 到 Stack。
- 最後從 Stack 底到頂組合成結果字串（需反轉順序）。

##### 注意

- StringBuilder insert(0, c) 複雜度為O(n)；由 Deque removeLast() 為 O(1)

##### 複雜度分析

- Time Complexity: O(n)，每個字元最多被 push 與 pop 各一次。
- Space Complexity: O(n)，最壞情況 Stack 大小為 n。


#### Java 程式碼連結

- [ID1047RemoveAllAdjacentDuplicatesInString.java](../../archive/src/main/java/io/github/monty/leetcode/stackqueue/ID1047RemoveAllAdjacentDuplicatesInString.java)
