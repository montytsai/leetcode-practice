---
title: "Repeated Substring Pattern"
difficulty: Easy
topics: [String, String Matching]
category: 03-string
order: 3
source: [Carl]
platform: LeetCode
status: ac-unknown
note: ""
date_created: 2026-03-07
date_updated: 2026-03-07
---


## 2025 初刷版（Day14，2025-05-01）

*原文見 [archive/doc/daily/day14-2025-05-01.md](../../archive/doc/daily/day14-2025-05-01.md)，已停更，內容按當時所寫原樣搬入*

### 459. Repeated Substring Pattern（重複的子字串模式）

#### 題目說明

- 判斷字串 `s` 是否可以由某個子字串重複多次組成。  
- 例如：
  - `s = "abab"` ➝ `true`（由 "ab" 重複兩次）
  - `s = "aba"` ➝ `false`

#### 解法一：KMP 前綴表判斷循環節

##### 思路
- 建立字串的 LPS（Longest Prefix Suffix）表，若存在一個循環節長度 `k` 能整除整體長度，則為重複子字串。
- 核心邏輯：
  ```
    int len = s.length();
    int lpsLast = lps[len - 1]; // 代表整體最長的「前綴 = 後綴」長度。
    return lpsLast > 0 && len % (len - lpsLast) == 0;
  ```
  - `lps[n-1]` 代表整體最長的「前綴 = 後綴」長度。
  - 如果 `n - lpsLast` 為循環節能整除，為重複子字串。

##### 複雜度
- 時間複雜度: O(n)
- 空間複雜度: O(n)

####  解法二：雙倍字串法（(s + s).substring(1, 2n - 1) 包含 s）

##### 思路
- 把字串 s 拼接自己，刪掉頭尾，看是否包含原始 s。若包含，表示存在某個子字串重複構成。

##### 複雜度
- 時間複雜度: O(n)
- 空間複雜度: O(n)

#### Java 程式碼連結
- [ID459RepeatedSubstringPattern.java](../../archive/src/main/java/io/github/monty/leetcode/string/ID459RepeatedSubstringPattern.java)
