---
title: "Reverse String II"
difficulty: Easy
topics: [String, Two Pointers]
category: 02-two-pointers
order: 4
source: [Carl]
platform: LeetCode
status: ac-unknown
note: ""
date_created: 2026-03-05
date_updated: 2026-03-05
---


## 2025 初刷版（Day11，2025-04-28）

*原文見 [archive/doc/daily/day11-2025-04-28.md](../../archive/doc/daily/day11-2025-04-28.md)，已停更，內容按當時所寫原樣搬入*

### 541. Reverse String II 重點整理

#### 題目說明
- 給定字串 s 和一個整數 k，從字串開頭算起，每計數至 2k 個字元，就反轉這 2k 字元中的前 k 個字元。
- 條件： 
  - 如果剩餘字元少於 k 個，則將剩餘字元全部反轉。 
  - 如果剩餘字元小於 2k 但大於或等於 k 個，則反轉前 k 個字元，其餘字元保持原樣。

#### 解法：for-loop + 雙指針
- 每 2k 字元為一個區塊，處理方法為： 
  - 反轉前 k 個字元，剩下的保留原樣。 
  - 如果剩下的字元少於 k，就反轉剩餘的所有字元。 
  - 根據 k 和字串長度，分別處理每個區塊。 
- 步驟： 
  1. 將字串轉換為字元陣列（char[]）。 
  2. 設定 left 指針，初始化為 0，每次加 2k 移動到下一個區塊。 
  3. 計算每個區塊的 right 邊界，確定需反轉的區間。 
  4. 反轉每個區間的前 k 個字元。 
  5. 反轉的過程使用雙指針法，分別從區間的兩端向中間移動。
- 複雜度：
  - 時間複雜度：O(n)，其中 n 是字串的長度。每個字元最多被反轉一次。
  - 空間複雜度：O(1)，因為只用了常數額外空間（字元陣列和指針）。

#### 庫函數使用原則
- 基本邏輯不依賴於庫函數，但若有需要快速處理字串變換，可以使用 StringBuilder（例如處理更複雜的字串操作）。
- 這題中使用了 Math.min() 來確保字串範圍合法，這是非常簡單且有效的工具。

#### 踩雷筆記
- 題目列了剩餘長度不同的2種處理方式，但冷靜思考之後，2種其實都是同一個規律，剩餘長度不用額外判斷。
- 小結: 不要被「題目敘述」給騙了！邏輯才是重點。

#### Java 程式碼
- [ID541ReverseStringII.java](../../archive/src/main/java/io/github/monty/leetcode/string/ID541ReverseStringII.java)
