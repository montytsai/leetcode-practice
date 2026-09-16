---
title: "Check ASCII Palindromic"
difficulty: Easy
topics: [Two Pointers, String, Bit Manipulation]
category: 02-two-pointers
order: 14
source: [Contest]
platform: LeetCode
url: https://leetcode.com/problems/check-ascii-palindromic/description/
status: ac-unknown
note: ""
date_created: 2026-08-23
date_updated: 2026-08-23
---
# 4030. Check ASCII Palindromic

## 題目說明

- 給定小寫英文字母字串 `s`(`1 <= s.length <= 100`),把每個字元的 ASCII 值轉成固定 8 位元的二進位字串(補前導零),依原順序串接成一條長度 `8 * s.length()` 的二進位字串。
- 判斷這條串接後的二進位字串本身是不是回文。

## 心得

想法一次到位,但覺得寫得太複雜,不確定怎樣比較好。
看了AI給的，有兩件學到的事情：
(1) n <= 100 快取沒省到 —— [complexity-analysis](../../topics/T00-01-complexity-analysis.md)
(2) 複習到 i >> n & 1 —— [bit-manipulation](../../topics/T20-21-bit-manipulation.md)

---

## 解法一:雙指標比較「鏡射區塊」,用陣列快取每個字元的 8-bit 表示

### Intuition

不用真的把整條二進位字串串出來。設 `n = s.length()`,第 `k` 個字元(0-indexed)佔據串接後的第 `[8k, 8k+7]` 位元。對整體回文條件 `S[j] == S[L-1-j]`(`L = 8n`)代入 `j = 8k+b` 展開,可以推出:第 `k` 個字元的 8-bit 表示,必須等於「從尾端數來對應字元」(`n-1-k`)的 8-bit 表示**反轉**後的結果。也就是 `dic[s[k]] == reverse[s[n-1-k]]`。

這正是程式碼在做的事:`l`、`r` 兩個指標分別代表 `k` 與 `n-1-k`,`to8Bit(s, l, true)` 回傳 `l` 位置字元的正常 8-bit 字串,`to8Bit(s, r, false)` 回傳 `r` 位置字元 8-bit 字串反轉後的版本,兩者相等就代表這對鏡射區塊合法。長度為奇數時,正中間字元自己跟自己鏡射,退化成「這個字元的 8-bit 表示本身要是回文」,對應 `dic[num].equals(reverse[num])`。

`dic`/`reverse` 兩個大小 128 的陣列是拿來快取每個字元(ASCII code)算過一次的 8-bit 字串與其反轉版本,同一個字元重複出現時不用重算。

### Approach

1. 雙指標 `l=0`、`r=s.length()-1` 向中間夾逼。
2. 每一步取 `s[l]` 的正常 8-bit 表示、`s[r]` 的反轉 8-bit 表示,不相等就提早回傳 `false`。
3. 長度為奇數時,額外檢查正中間字元的 8-bit 表示是否自身回文。
4. 全部通過才回傳 `true`。

### Complexity

**Time complexity: `O(n)`**

`n = s.length()`。雙指標各跑 `n/2` 步,每步的 8-bit 轉換與比較是 `O(8) = O(1)`(未快取過的字元最多轉換一次,轉換後存進快取);`n <= 100` 使複雜度差異在此題規模下完全不影響效能。

**Space complexity: `O(1)`**(不含輸入輸出)

`dic`、`reverse` 是固定大小 128 的陣列,不隨輸入變大,是常數空間。

### Code

```java
/**
 * 4030. Check ASCII Palindromic
 * Time Complexity: O(n)
 * Space Complexity: O(1) extra (fixed-size 128-entry caches)
 */
class Solution {

    private String[] dic = new String[128];
    private String[] reverse = new String[128];

    public boolean isPalindromic(String s) {
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            String left = to8Bit(s, l, true);
            String right = to8Bit(s, r, false);

            if (!left.equals(right)) {
                return false;
            }
            l++;
            r--;
        }

        if (s.length() % 2 != 0) {
            int mid = (s.length() - 1) / 2;
            int num = (int) s.charAt(mid);
            to8Bit(s, mid, true);
            return dic[num].equals(reverse[num]);
        }

        return true;
    }

    private String to8Bit(String s, int i, boolean isLeft) {
        int num = (int) s.charAt(i);

        if (dic[num] == null || dic[num].length() == 0) {
            StringBuilder sb = to(num);

            reverse[num] = sb.toString();
            sb.reverse();
            dic[num] = sb.toString();
        }

        return isLeft ? dic[num] : reverse[num];
    }

    private StringBuilder to(int n) {
        StringBuilder sb = new StringBuilder();

        while (n > 0) {
            sb.append(n % 2);
            n /= 2;
        }

        int len = 8 - sb.length();
        while (len-- > 0) {
            sb.append("0");
        }

        return sb;
    }

}
```

LeetCode AC 數據:Runtime beats 93.0%、Memory beats 73.99%。

### Code Review

- **Learning provenance**:`self-solved`(一開始就想出這個解);獨立重現能力未確認(我的疑問是「太複雜」而非「能不能重寫」)。
- **Correctness / invariant**:正確。上面 Intuition 的推導證明了「鏡射區塊比較」等價於整體回文,奇數長度的中間字元特判也對應到 `k == n-1-k` 的退化情況,沒有遺漏。
- **Strength**:`dic`/`reverse` 是 `Solution` 的 instance field,若同一個實例被重複呼叫多次(例如評測時同一物件跑多筆測資),字元的 8-bit 快取可以跨呼叫沿用,這個設計是對的,不是 bug。
- **Style**:`to8Bit` 裡的 `dic[num].length() == 0` 判斷是多餘的——`dic[num]` 一旦被設定,必定是補滿 8 碼的字串,長度不會是 0,只檢查 `== null` 就足夠;這個多餘條件不影響正確性,純粹是可以刪掉的雜訊。
- **Edge cases**:`to(n)` 在 `n == 0` 時迴圈不執行、直接靠補零邏輯產生 `"00000000"`,邏輯上是對的;不過題目限定小寫字母(ASCII 97~122),`n == 0` 這個分支在本題實際上不會被觸發。

---

## 解法二:同樣的鏡射區塊比較,但用位元運算直接算,不快取

### Intuition

邏輯跟解法一完全一樣(第 `k` 個字元的 8-bit 表示要等於第 `n-1-k` 個字元 8-bit 表示反轉後的結果),差別只在於「怎麼取出某個字元第幾個 bit」這件事,解法二不建字串、不快取,直接用位元運算現算現比。

不快取的原因：`n ≤ 100`，單一字元轉 8-bit 只有 8 次位元運算，完全不快取、每次重算，總量也就幾百次運算，快取根本省不到什麼。

這個解法由 claude 提供，針對解法一「太複雜」的簡化建議。

### Approach

先講兩個位元運算基本工具,再說怎麼組合出比較邏輯。

**`x >> k`(右移)**:把 `x` 的二進位表示整體往右移 `k` 位,右邊移出去的位元直接丟棄,左邊補 `0`。等於「捨去最低的 `k` 位」。例如 `102 >> 3`:`102` 的二進位是 `01100110`,丟掉最右邊 3 位(`110`),剩下 `01100`,也就是 `12`。

**`x & 1`(取最低位)**:`&` 是位元的「且」,`& 1` 會把除了最後一位以外的所有位元都清成 `0`,只留下最後一位是 `0` 還是 `1`。`x` 是偶數就是 `0`、奇數就是 `1`。

**組合 `(x >> k) & 1`**:先右移 `k` 位、再取最後一位,效果就是「把 `x` 第 `k` 位(從右邊數,最右邊是第 `0` 位)單獨挑出來看是 `0` 還是 `1`」。這是位元運算裡「讀出第幾個 bit」的標準寫法。

**位置編號的方向要對齊**:8-bit 表示寫成 `"01100110"` 這種字串時,最左邊的字元是第 7 位(數值最大、`128` 那一位),最右邊的字元是第 0 位(數值最小、`1` 那一位)——跟字串的左右順序是反過來的。

**為什麼是 `(7-b)` 和 `b`**:迴圈裡 `b` 從 `0` 數到 `7`,想要的是「照字串正常的左到右順序讀 `left`」、「照字串反過來、右到左順序讀 `right`」:

- `left` 要正常順序:`b=0` 時要拿最左邊的位元,也就是第 `7` 位,所以是 `left >> (7-b)`;`b=7` 時要拿最右邊的位元,第 `0` 位,`7-b=0` 剛好對上。
- `right` 要反過來讀:`b=0` 時要拿最右邊的位元,也就是第 `0` 位,所以直接 `right >> b`,`b=0` 時剛好是第 `0` 位;`b=7` 時要拿最左邊的位元,第 `7` 位,`right >> 7` 也剛好對上。

兩邊各走一輪、方向相反,就等於在比較「`left` 的正常 8-bit 字串」跟「`right` 的反轉 8-bit 字串」,跟解法一用字串比較的結果完全一樣,只是沒有真的把字串building 出來。

**照題目自己的範例走一次**:題目說 `'f'`(ASCII 102)轉成 `"01100110"`。如果 `'f'` 剛好落在字串正中間(奇數長度的情況),要檢查它自己是不是回文,也就是呼叫 `mirrorBitsMatch('f', 'f')`:

| `b` | `left>>(7-b)` 取第幾位 | `leftBit` | `right>>b` 取第幾位 | `rightBit` | 相等? |
| --- | --- | --- | --- | --- | --- |
| 0 | 第 7 位 | 0 | 第 0 位 | 0 | ✅ |
| 1 | 第 6 位 | 1 | 第 1 位 | 1 | ✅ |
| 2 | 第 5 位 | 1 | 第 2 位 | 1 | ✅ |
| 3 | 第 4 位 | 0 | 第 3 位 | 0 | ✅ |
| 4 | 第 3 位 | 0 | 第 4 位 | 0 | ✅ |
| 5 | 第 2 位 | 1 | 第 5 位 | 1 | ✅ |
| 6 | 第 1 位 | 1 | 第 6 位 | 1 | ✅ |
| 7 | 第 0 位 | 0 | 第 7 位 | 0 | ✅ |

八輪都相等,回傳 `true`——`"01100110"` 本身就是回文,跟直接肉眼看字串的結論一致。

### Complexity

**Time complexity: `O(n)`**

雙指標各跑 `n/2` 步,每步固定跑 8 輪位元運算,`O(8) = O(1)` per 比較。

**Space complexity: `O(1)`**

沒有任何額外陣列或字串,只有迴圈變數。

### Code

```java
/**
 * 4030. Check ASCII Palindromic — Approach 2 (bitwise, no caching)
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
class Solution {
    public boolean isPalindromic(String s) {
        int n = s.length();
        int l = 0, r = n - 1;

        while (l < r) {
            if (!mirrorBitsMatch(s.charAt(l), s.charAt(r))) {
                return false;
            }
            l++;
            r--;
        }

        if (n % 2 != 0) {
            char mid = s.charAt(n / 2);
            if (!mirrorBitsMatch(mid, mid)) {
                return false;
            }
        }

        return true;
    }

    // Bit b (0..7, left to right) of `left`'s 8-bit block must equal
    // bit (7-b) of `right`'s block — i.e. left's normal order vs right's reversed order.
    private boolean mirrorBitsMatch(char left, char right) {
        for (int b = 0; b < 8; b++) {
            int leftBit = (left >> (7 - b)) & 1;
            int rightBit = (right >> b) & 1;
            if (leftBit != rightBit) {
                return false;
            }
        }
        return true;
    }
}
```

LeetCode AC 數據:Runtime beats 99.7%、Memory beats 98.7%。
### Code Review

- **Learning provenance**:`AI-assisted`——這是針對解法一「太複雜」的簡化建議,由 AI 提出並解釋位元運算的推導,未確認能否獨立重現。
- **Correctness / invariant**:跟解法一等價,推導見上方 Intuition,並用題目自帶的 `'f'` 範例逐輪驗證過。
- **Strength**:沒有任何 mutable field、沒有布林旗標分岔行為,`mirrorBitsMatch` 是一個純函式,兩個參數就決定輸出,好測試也好推理。
- **Trade-off**:比起解法一,少了跨呼叫的快取效果——但如前面分析,這題 `n <= 100` 下快取本來就沒有實質幫助,不是真的損失。
- **Edge cases**:與解法一相同(`n=0` 分支在小寫字母限制下不會觸發);位元運算不受 Java `char` 是 16-bit 的影響,因為小寫字母 ASCII 值都 `< 128`,右移量固定在 `0~7`,不會碰到第 8 位以上的無關位元。

---

## 解法比較

| 解法 | Time | Space | 優點 | Trade-off | 使用時機 |
| --- | --- | --- | --- | --- | --- |
| 解法一 | `O(n)` | `O(1)`(含 128 大小快取) | 有跨呼叫快取,`to8Bit` 回傳值可讀 | 兩個平行陣列 + 布林旗標,閱讀成本較高 | 需要頻繁重複查詢同一字元的 8-bit 表示時 |
| 解法二 | `O(n)` | `O(1)`(無額外陣列) | 無 mutable state,邏輯集中在一個純函式 | 位元運算對不熟悉的人有理解門檻 | 這題規模小、追求邏輯簡潔時 |

### Optimality

兩個解法在 Time 與 Space 指標上是同一個複雜度等級,已經是這題能拿到的最佳等級,差別純粹在 Readability:解法一多了快取機制但這題規模用不到;解法二拿掉快取換來更少的狀態,但要求看得懂位元運算。對這題(Easy、`n <= 100`)來說,解法二在「邏輯直接、無副作用」這個指標上更好;解法一則保留了原始的 self-solved 過程,兩者都值得留著對照。

## 相關

- [Two Pointers](../../topics/T02-21-two-pointers.md) — 左右指標比較鏡射區塊，不用真的串出完整二進位字串
- [String 字串](../../topics/T03-21-string.md) — 每個字元轉固定 8-bit 再串接
- [Bit Manipulation 位元運算](../../topics/T20-21-bit-manipulation.md) — 複習 `x >> k` 取第幾位、`x & 1` 取最低位怎麼組合著用
- [時間與空間複雜度分析](../../topics/T00-01-complexity-analysis.md) — `n <= 100` 時快取換不到實質效能，先看規模再決定要不要做這類優化
- [LeetCode 刷題總覽](../../_moc.md)
