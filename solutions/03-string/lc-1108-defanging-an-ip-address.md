---
title: "Defanging an IP Address"
difficulty: Easy
topics: [String]
category: 03-string
order: 1
source: [Carl]
platform: LeetCode
status: ac-unknown
note: ""
date_created: 2026-03-05
date_updated: 2026-03-05
---


## 2025 初刷版（Day11，2025-04-28）

*原文見 [archive/doc/daily/day11-2025-04-28.md](../../archive/doc/daily/day11-2025-04-28.md)，已停更，內容按當時所寫原樣搬入*

### 1108. Defanging an IP Address 重點整理

#### 題目說明
- 給定一個有效的 IPv4 地址，請將所有的 '.' 替換為 "[.]"
- 輸入：一個字串 address，表示 IPv4 地址。

#### 解法
- 遍歷字串中的每一個字元，遇到 . 就將其替換為 "[.]"，其餘字元保持不變。
- 步驟： 
  1. 使用 StringBuilder 或 StringBuffer 將結果拼接出來。
  2. 遍歷字串，檢查每個字元：
     - 若為 .，則拼接 "[.]"；
     - 否則，拼接原字元。 
  3. 最後返回拼接好的結果。
- 複雜度： 
  - 時間複雜度：O(n)，其中 n 是字串的長度。每個字元都會被處理一次。 
  - 空間複雜度：O(n)，因為 StringBuilder 需要額外的空間來保存結果字串。

#### 小結
- 利用 StringBuilder 拼接字串，這樣避免了使用 + 進行拼接時產生大量的中間物件，特別是在處理長字串時能顯著提升效能。
- Java 函式庫可用
  ```java
  public class Solution {
      public String defangIPaddr(String address) {
          return address.replace(".", "[.]");
     }
  }
  ```

#### Java 程式碼
- [ID1108DefangingAnIpAddress.java](../../archive/src/main/java/io/github/monty/leetcode/string/ID1108DefangingAnIpAddress.java)
