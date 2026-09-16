---
title: "String to Integer (atoi)"
difficulty: Medium
topics: [String]
category: 03-string
order: 4
source: [Grind75]
platform: LeetCode
url: https://leetcode.com/problems/string-to-integer-atoi/
status: ac-unknown
note: ""
date_created: 2026-09-13
date_updated: 2026-09-13
---

# 8. String to Integer (atoi)

## 題目說明

- 把字串轉成 32-bit 有號整數：跳過開頭空白 → 讀一個可有可無的 `+`／`-` → 讀連續數字，遇到非數字就停。
- 結果超出 `[-2^31, 2^31 - 1]` 時要夾到邊界值（clamp）；沒有讀到任何數字就回傳 `0`。
- 字串只含英文字母、數字、`' '`、`'+'`、`'-'`、`'.'`，長度 0 到 200。

## 心得

我覺得這題根本沒有演算法 是考怎處理業務邏輯

卡最久的是溢位處理；另一個要補的觀念是「取十進位最後一位」要用 `% 10`，`% 1` 永遠是 0。

---

## 解法一：逐字元掃描＋乘 10 之前預判溢位

### Intuition

這題的流程是固定的三段：空白、正負號、數字。每一段各用一個 `while` 或 `if` 往前推同一個索引 `i`，不需要任何資料結構。難點全部集中在第三段的溢位判斷。

`num * 10 + digit` 在 `int` 裡一旦溢位，結果會繞成負數，事後已經無法分辨。所以檢查必須放在乘 10 **之前**，用 `Integer.MAX_VALUE / 10`（214748364）和 `Integer.MAX_VALUE % 10`（7）這兩個數字預判下一步會不會超過 2147483647。

預判分成兩種情況，兩者任一成立就溢位，所以用 `||` 連起來。第一種是 `num > 214748364`：不論下一位是多少，乘 10 就已經超過上限。第二種是 `num == 214748364`：這時才需要看下一位，`digit > 7` 才溢位。

`2147483640` 這筆測資（KEY!）說明為什麼不能把兩種情況併成一個 `num >= MAX / 10`。讀到最後一位時 `num == 214748364`、`digit == 0`，答案 2147483640 完全合法；如果用 `>=`，這裡會被誤判成溢位而回傳 `MAX_VALUE`。只有「等於」的時候才要把下一位拿來比，這就是第二個條件存在的原因。

負數不需要另外寫一套邊界。`Integer.MIN_VALUE` 是 -2147483648，比正數邊界多 1。當 `num == 214748364` 且 `digit == 8` 時，程式因為 `8 > 7` 提早回傳 `MIN_VALUE`，而 -2147483648 恰好就是正確答案，所以這條共用的判斷對負數也成立。

`% 10` 取的是十進位的最後一位，`/ 10` 是去掉最後一位；這一組和 bit 運算無關。對應到二進位的是 `& 1`（等同 `% 2`）取最低位、`>> 1` 去掉最低位。

### Approach

1. 用 `i` 跳過所有開頭的 `' '`。
2. 如果 `s.charAt(i)` 是 `'+'` 或 `'-'`，記下 `sign` 並讓 `i` 前進一格。
3. 只要當前字元是數字，就取出 `digit`：
   - 先檢查 `num > MAX / 10`，或 `num == MAX / 10 && digit > MAX % 10`。成立就依 `sign` 回傳 `MAX_VALUE` 或 `MIN_VALUE`。
   - 否則 `num = num * 10 + digit`。
4. 迴圈結束時回傳 `sign * num`。

Invariant：每次進入迴圈時 `num` 都在 `[0, 2147483647]` 內，而且檢查通過後 `num * 10 + digit` 保證不會溢位，所以 `int` 全程夠用。

### Complexity

**Time complexity: `O(n)`**

`n` 是字串長度。索引 `i` 只往前走，每個字元最多看一次。

**Space complexity: `O(1)`**

只用了 `i`、`sign`、`num`、`digit` 幾個變數。

### Code

```java
/**
 * 8. String to Integer (atoi)
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * Test case: 2147483640 <--- KEY! Why do we need ||
 */
class Solution {
    public int myAtoi(String s) {
        int i = 0;
        int n = s.length();

        // 1. Skip leading whitespace
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // 2. Read the sign
        int sign = 1;
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            if (s.charAt(i) == '-') {
                sign = -1;
            }
            i++;
        }

        // 3. Read digits and clamp on overflow
        int num = 0;
        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';

            // Check before num * 10, because an int overflow cannot be detected afterwards
            if (num > Integer.MAX_VALUE / 10
                    || (num == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            num = num * 10 + digit;
            i++;
        }

        return sign * num;
    }
}
```

### Code Review

- **Learning provenance**：在溢位處理卡很久，第一版用 `long` 加上 `(int)` 轉型時跳出 LeetCode 警告；提交後有看過最佳解。這份 `int` 版本是看解前或看解後寫成：未確認。能否獨立重現：未確認。
- **Correctness / invariant**：正確。溢位檢查在乘 10 之前，`num` 永遠不會真的溢位；負數邊界 -2147483648 被 `digit > 7` 提早夾成 `MIN_VALUE`，結果剛好正確。
- **Strength**：全程只用 `int`，不依賴 64-bit 型別，符合面試裡「環境只能存 32-bit 整數」的追問（LC 7 Reverse Integer 就明文這樣規定）。三段流程用註解分開，讀起來和題目描述一一對應。
- **Style**：`Character.isDigit` 也會認得非 ASCII 的數字字元（例如阿拉伯文數字），那時 `- '0'` 會算出錯的值。本題限制只有 ASCII，所以不是 bug；想寫得更精準可以改成 `c >= '0' && c <= '9'`。
- **Edge cases**：`""` 與 `"   "`（`i` 直接到尾，回傳 0）、`"+-12"`（只吃一個正負號，回傳 0）、`"2147483640"`（等於邊界但不溢位）、`"-2147483648"`（剛好等於 `MIN_VALUE`）、`"00000000000012345678"`（前導零很多，`num` 一直是 0，不會誤判溢位）。

---

## 解法比較

### Optimality

以時間與空間指標來看，這份解法已經最佳：每個字元都可能影響答案，至少要讀一次，所以 `O(n)` 是下限；額外空間 `O(1)`。

替代法是用 `long` 累加，每讀一位就檢查 `sign * num` 有沒有超出 `int` 範圍，超出就立刻回傳邊界值。時間與空間相同，好處是判斷式比較直覺。代價有兩個：一是檢查必須放在迴圈**裡面**、每一步都做，因為 `"99999999999999999999"` 這種超過 19 位的輸入連 `long` 也會溢位；二是最後回傳時要寫成 `(int) (sign * num)`。`(int) sign * num` 會因為轉型的優先順序高於乘法，只轉了 `sign`，整個運算結果仍是 `long`，Java 就會報 `possible lossy conversion from long to int`。面試時若被要求不能用 64-bit 型別，只能用本解法的 `int` 預判寫法。

## 相關

- [String](../../topics/T03-21-string.md) — 固定流程的逐字元解析，以及乘 10 之前預判 `int` 溢位
- [LeetCode 刷題總覽](../../_moc.md)
