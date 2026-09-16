---
title: "Number of 1 Bits"
difficulty: Easy
topics: [Bit Manipulation]
category: 20-bit-manipulation
order: 3
source: [Grind75, Extra]
platform: LeetCode
url: https://leetcode.com/problems/number-of-1-bits/
status: ac-assisted
note: ""
date_created: 2026-08-04
date_updated: 2026-08-04
---

## 心得

延續上週 338，很快用遞迴加 Map 做 DP，但 AC 分數很低。後來問 AI 寫出兩種解法：迴圈遍歷所有位元（32），每次往右推一格，計算最低位的 1；第二種是跳級每次排除一個 1 的寫法（`n & (n - 1)`）。

## Java

### 自然解法：逐位檢查

```java
class Solution {

    public int hammingWeight(int n) {
        int count = 0;

        // Java int always has 32 bits.
        // We check every bit, including bits whose value is 0.
        for (int i = 0; i < 32; i++) {
            // AND with 1 keeps only the lowest bit.
            // If the result is 1, the current lowest bit is set.
            if ((n & 1) == 1) {
                count++;
            }

            // Use unsigned right shift so the left side is filled with 0.
            // This also works when the highest bit makes n negative in Java.
            n = n >>> 1;
        }

        return count;
    }
}
```

#### 自然解法的想法

例如：

```text
n     = 10110100
n & 1 = 00000000  → 最低位是 0
n >>> 1 = 01011010
```

每次先用 `n & 1` 讀取最低位，再用 `n >>> 1` 把已處理的最低位移出去。因為 Java `int` 固定有 32 bits，所以最多檢查 32 次。

`>>>` 和 `>>` 的差別是：`>>` 會複製符號位，負數右移後可能一直補 `1`；`>>>` 左邊永遠補 `0`，適合處理完整的 32-bit 位元。

### 最佳化解法：每次清除一個 `1`

```java
class Solution {

    public int hammingWeight(int n) {
        int count = 0;

        // Each iteration removes the lowest set bit.
        while (n != 0) {
            count++;
            n = n & (n - 1);
        }

        return count;
    }
}
```

#### 為什麼 `n & (n - 1)` 可以清除最低位的 `1`？

假設最低位的 `1` 右邊都是 `0`：

```text
n     = H 1 000
n - 1 = H 0 111
```

`n - 1` 會讓最右邊的 `1` 變成 `0`，並把它右邊的 `0` 變成 `1`。做 AND 後：

```text
  H 1 000
& H 0 111
----------
  H 0 000
```

例如：

```text
10110100 & 10110011 = 10110000
10110000 & 10101111 = 10100000
10100000 & 10011111 = 10000000
10000000 & 01111111 = 00000000
```

原本有幾個 `1`，就會執行幾次。

### Lookup table 解法：重複呼叫時複用答案

Follow-up 問「如果這個函式會被呼叫很多次，要怎麼最佳化？」時，可以把 32-bit 拆成四組 8-bit。8-bit 只有 `2^8 = 256` 種可能，所以先算好 `0 ~ 255` 每個數字有幾個 `1`，每次呼叫只要查四次表。

```java
/**
 * Lookup-table solution
 *
 * Precomputation:
 * Time: O(2^SIZE)
 * Space: O(2^SIZE)
 *
 * Per call:
 * Time: O(32 / SIZE)
 * Space: O(1)
 *
 * With SIZE = 8:
 * Precomputation: O(256) = O(1)
 * Per call: O(4) = O(1)
 */
class Solution {

    private static final int SIZE = 8;
    private static final int TABLE_SIZE = 1 << SIZE;
    private static final int MASK = TABLE_SIZE - 1;

    private static final int[] bits = new int[TABLE_SIZE];

    static {
        for (int i = 1; i < TABLE_SIZE; i++) {
            bits[i] = bits[i >> 1] + (i & 1);
        }
    }

    public int hammingWeight(int n) {
        int res = 0;

        while (n != 0) {
            // Keep the lowest 8 bits and look up their bit count.
            res += bits[n & MASK];

            // Move the next 8 bits to the lowest position.
            n = n >>> SIZE;
        }

        return res;
    }
}
```

#### 為什麼是 256 與四組？

- 一組 8-bit 有 `2^8 = 256` 種可能，範圍是 `00000000` 到 `11111111`，所以 lookup table 需要 256 格。
- Java `int` 有 32 bits，`32 / 8 = 4`，所以每次呼叫最多查四組。
- `1 << SIZE` 就是 `2^SIZE`。當 `SIZE = 8` 時，`1 << 8 = 256`。
- `MASK = TABLE_SIZE - 1`：`256 - 1 = 255 = 00000000 00000000 00000000 11111111`，用來只保留最低 8 bits。

例如：

```text
n = 10110100 01100011 10110000 00010101

n & MASK              → 00010101  // 最低一組 D
(n >>> 8) & MASK       → 10110000  // 下一組 C
(n >>> 16) & MASK      → 01100011  // 下一組 B
(n >>> 24) & MASK      → 10110100  // 最高一組 A
```

`n >>> 8` 不是只留下下一組，而是把所有 bit 右移 8 格；移位後仍有其他高位，所以還需要 `& MASK` 把目前要查的那組 8 bits 留下來。

## 複雜度

### 自然解法

- 時間：`O(32) = O(1)`，因為 Java `int` 固定是 32 bits。
- 空間：`O(1)`。

### 最佳化解法

如果二進位中有 `k` 個 `1`：

- 時間：`O(k)`。
- 空間：`O(1)`。
- 最壞情況 `k = 32`，所以在 Java `int` 的固定寬度下仍可寫成 `O(1)`，但實際上會跳過大量的 `0`。

## 原本的 DP 解法

原本使用遞迴與 `HashMap` 記錄右移後的結果：

```java
private static final Map<Integer, Integer> dp = new HashMap<>();
static {
    dp.put(0, 0);
}

public int hammingWeight(int n) {
    if (dp.containsKey(n)) {
        return dp.get(n);
    }

    int next = n >> 1;
    if (!dp.containsKey(next)) {
        int value = hammingWeight(next);
        dp.put(next, value);
    }

    return dp.get(next) + (n & 1);
}
```

這個方向可以利用重複呼叫，但對單次呼叫來說有遞迴、`HashMap`、`Integer` boxing 等額外成本。它也使用 `>>`，遇到 Java 負數的符號位時不如 `>>>` 安全。

## 問題紀錄：這次真的搞懂的地方

### 1. 為什麼自然解要用 `>>>`？

Java `int` 固定有 32 bits。自然解每次用 `n & 1` 檢查最低位，再把 `n` 右移一位。

- `>>` 是有號右移：負數左側會補符號位 `1`，可能一直補入新的 `1`。
- `>>>` 是無號右移：左側永遠補 `0`，原本的 bit 會逐一移到最低位，不會製造新的 `1`。

例如 8-bit 醜數字 `10010100`：

```text
10010100 >>> 1 = 01001010
01001010 >>> 1 = 00100101
00100101 >>> 1 = 00010010
```

最高位的 `1` 沒有消失，只是一路往右移，最後會被 `n & 1` 檢查到。

### 2. 為什麼自然解可以 `return ones`？

```java
ones = ones + (n & 1);
```

`n & 1` 只會得到 `0` 或 `1`，所以 `ones` 就是累積到目前為止看見的 `1` 的數量。當 `n == 0` 時，所有 bit 都已處理完，`ones` 就是答案。

### 3. 為什麼最佳解用 `while (n != 0)`？

如果寫 `while (n > 0)`，負數輸入會直接跳過。`n & (n - 1)` 不需要右移，即使最高位是 `1`，也能逐次清除 bit，直到 `n == 0`。

對 8-bit 的 `10010100`：

```text
10010100 & 10010011 = 10010000
10010000 & 10001111 = 10000000
10000000 & 01111111 = 00000000
```

原本有幾個 `1`，就執行幾次。

### 4. `n >> i` 為什麼不會跳過第 0 位？

這種寫法每次都從原本的 `n` 讀取第 `i` 位：

```java
((n >> i) & 1)
```

當 `i = 0` 時：

```text
n >> 0 = n
```

所以第 0 位完全沒有被跳過。它和「修改 `n` 後再右移」是兩種不同寫法。

## 相關

- [Bit Manipulation 主題](../../topics/T20-21-bit-manipulation.md)
- [時間與空間複雜度主題](../../topics/T00-01-complexity-analysis.md)
- [_moc](../../_moc.md)
