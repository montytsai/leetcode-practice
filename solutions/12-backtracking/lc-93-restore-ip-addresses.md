---
title: "Restore IP Addresses"
difficulty: Medium
topics: [Backtracking, String]
category: 12-backtracking
order: 7
source: [Carl]
platform: LeetCode
status: ac-unknown
note: ""
date_created: 2026-04-12
date_updated: 2026-04-12
---

[LeetCode 題目連結](https://leetcode.com/problems/restore-ip-addresses/description/)

---

## 2025 初刷版（Day53，2025-06-09）

*原文見 [archive/doc/daily/day53-2025-06-09.md](../../archive/doc/daily/day53-2025-06-09.md)，已停更，內容按當時所寫原樣搬入*

### LeetCode 93 - Restore IP Addresses

#### 題目說明
- 給定一個只包含數字的字串 s，請將其切割為所有可能的合法 IPv4 地址組合。
- IPv4 地址由四段 0~255 組成，以點分隔，且不可有前導 0（如 "01"、"001" 不合法）。

---

#### 解法：回溯法

##### 思路

- 採用回溯方式，將字串切成四段。
- 每段需滿足：
  1. 數值介於 0~255。
  2. 若非單個 '0'，不得有前導 0。 
- 每切出一段合法 IP，就遞迴處理剩下部分，直到切出四段為止。 
- 使用 `StringBuilder` 避免過多字串拷貝，並以 `setLength()` 回溯現場。

##### 視覺化理解（例："25525511135"）
```
restore("25525511135")
├── "2."
│   └── "5."
│       └── "5."
│           └── "255."
│               └── "255."
│                   └── "11."
│                       └── "135" → 255.255.11.135 ✅
│                   └── "111." → "255.255.111.35" ✅
```

##### 重點
- 回溯層數限制為 4。 
- 加入剪枝：
  - 若剩餘字數無法補足剩餘段落（< min 或 > max）可提早結束。
  - 若段落值不合法（> 255）或有前導零，直接 break。

##### 複雜度
- 時間複雜度：O(1)，最多 3^4 = 81 種切法。
- 空間複雜度：O(n)，遞迴深度最多 4 層。

---

#### Java 程式碼連結
- 題目實作：[ID93RestoreIpAddresses.java](../../archive/src/main/java/io/github/monty/leetcode/backtracking/ID93RestoreIpAddresses.java)
- 單元測試：[ID93RestoreIpAddressesTest.java](../../archive/src/test/java/io/github/monty/leetcode/backtracking/ID93RestoreIpAddressesTest.java)
