---
title: "Letter Combinations of a Phone Number"
difficulty: Medium
topics: [Backtracking, Hash Table, String]
category: 12-backtracking
order: 3
source: [Carl]
platform: LeetCode
status: ac-unknown
note: ""
date_created: 2026-04-09
date_updated: 2026-04-09
---


## 2025 初刷版（Day42，2025-05-29）

*原文見 [archive/doc/daily/day42-2025-05-29.md](../../archive/doc/daily/day42-2025-05-29.md)，已停更，內容按當時所寫原樣搬入*

### LC17. Letter Combinations of a Phone Number

#### 題目說明
- 給定一組數字字串（僅包含 `2~9`），回傳所有可能對應英文字母組合（電話按鍵字母）。
- 按鍵對應如下：
  - `2`: abc, `3`: def, `4`: ghi, `5`: jkl, `6`: mno, `7`: pqrs, `8`: tuv, `9`: wxyz

---

#### 解法一：回溯（Backtracking）

##### 思路：構建每一位數的所有選擇

- 將題目轉換為「每一位數字，選一個對應的英文字母」的組合問題。
- 遞迴每一層 digit（第幾位數），每層遍歷當下 digit 對應的所有字母。
- 使用 `StringBuilder` 儲存當前路徑 `path`，組合完成時加入 `result`。
- 回溯步驟：加入字母 ➜ 遞迴 ➜ 移除字母

##### 視覺化理解：決策樹結構與回溯流程

以輸入 `digits = "23"` 為例：

```
                           ""
            ┌──────────────┼──────────────┐
           "a"            "b"            "c"
       ┌────┼────┐    ┌────┼────┐    ┌────┼────┐ 
      "ad" "ae" "af" "bd" "be" "bf" "cd" "ce" "cf"
```

- 每一層代表 `digits` 中的某一位數字。
- 每個節點選擇對應數字的其中一個字母。
- 當 path 長度等於 digits 長度，為合法結果。
- 橫向是當層所有可能字母，縱向是遞迴深入下一個 digit。
- 每個葉節點即為一種可能的字母組合。

##### 複雜度分析

- 時間複雜度：O(3^n * 4^m)，n 為 3 字母數字數量，m 為 4 字母數字數量
- 空間複雜度：O(n)，最長遞迴深度與 path 長度

---

#### Java 程式碼連結
- 題目實作：[ID17LetterCombinationsOfAPhoneNumber.java](../../archive/src/main/java/io/github/monty/leetcode/backtracking/ID17LetterCombinationsOfAPhoneNumber.java)
- 單元測試：[ID17LetterCombinationsOfAPhoneNumberTest.java](../../archive/src/test/java/io/github/monty/leetcode/backtracking/ID17LetterCombinationsOfAPhoneNumberTest.java)
