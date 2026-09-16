---
title: "Longest Common Prefix"
difficulty: Easy
topics: [String, Array, Trie]
category: 01-arrays-hashing
order: 23
source: [Grind75]
platform: LeetCode
url: https://leetcode.com/problems/longest-common-prefix/
status: ac-unknown
note: ""
date_created: 2026-08-03
date_updated: 2026-08-03
---

## 心得

排序後比較第一個與最後一個字串。

## Java

```java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 1) return strs[0];
        Arrays.sort(strs);

        String first = strs[0];
        String last = strs[strs.length - 1];

        int i = 0;
        for (; i < Math.min(first.length(), last.length()); i++) {
            if (first.charAt(i) != last.charAt(i)) {
                break;
            }
        }

        return first.substring(0, i);
    }
}
```

### 為什麼只比較排序後的第一個與最後一個？

排序後，字典序最小的字串在最前面，字典序最大的字串在最後面。所有字串的共同前綴，一定也是這兩個極端字串的共同前綴；如果第一個和最後一個在某個位置不同，中間的字串不可能讓這個位置重新成為所有字串共有的字元。

因此只要逐字元比較 `first` 和 `last`，就能得到整組字串的最長共同前綴。

### `substring(0, i)` 的索引範圍

Java 的 `substring` 採用左閉右開區間：`[beginIndex, endIndex)`。

```text
first = "flower"
last  = "flow"

i = 4，代表索引 4 是「短字串已經結束」或「第一個不相同的位置」
substring(0, 4) 取索引 0、1、2、3
結果是 "flow"
```

### 複雜度

設 `n` 是字串數量，`L` 是最長字串長度。

#### 時間：`O(n log n · L)`

1. 排序 `n` 個元素需要 `O(n log n)` 次比較。
2. 一次字串比較最壞可能要逐字元比到長度 `L`，所以一次比較是 `O(L)`。
3. 兩者相乘：`O(n log n) × O(L) = O(n log n · L)`。
4. 排序後再比較第一個與最後一個只需要 `O(L)`，已被排序成本涵蓋。

如果採用面試中的簡化分析，把一次字串比較視為 `O(1)`，可以寫成 `O(n log n)`；但把字元比較成本算進去時，`O(n log n · L)` 更嚴謹。

#### 空間：要看是否計入排序實作

- 只算自己宣告的 `first`、`last`、`i`：`O(1)`。
- `Arrays.sort(strs)` 會修改輸入陣列，而且 Java 的物件陣列排序可能使用暫存陣列；若把排序內部空間算進去，較安全寫 `O(n)`。
- 排序只重新排列 `String` 的參考，不會複製每個字串的全部內容，所以不是 `O(nL)`。
- `substring(0, i)` 產生的答案最多長度 `L`；通常輸出空間不計入 auxiliary space，但若要把輸出也算進總空間，還要加上 `O(L)`。

因此本題可以完整寫成：

```text
Time: O(n log n · L)
Auxiliary space: O(n) including Java's object-array sorting buffer
                  O(1) if sorting's internal space is ignored
```

## 相關

- [String 主題](../../topics/T03-21-string.md)
- [時間與空間複雜度主題](../../topics/T00-01-complexity-analysis.md)
- [_moc](../../_moc.md)
