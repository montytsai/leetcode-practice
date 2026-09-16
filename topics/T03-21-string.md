# String 字串

字串題常見的核心不是複雜資料結構，而是先找出「字元的比較範圍」與「不變的條件」。常見工具包括逐字元掃描、雙指標、排序後比較極端值、字串切片，以及用額外結構記錄字元狀態。

## 解題技巧

### 排序後比較極端值：Longest Common Prefix

當題目要找「所有字串共有的前綴」時，可以先排序字串，再只比較字典序最小的第一個字串與最大的最後一個字串。所有字串共有的前綴，一定也是這兩個極端值的共同前綴。

```java
Arrays.sort(strs);
String first = strs[0];
String last = strs[strs.length - 1];

int i = 0;
while (i < Math.min(first.length(), last.length())
        && first.charAt(i) == last.charAt(i)) {
    i++;
}

return first.substring(0, i);
```

### Java `substring` 的左閉右開

`substring(beginIndex, endIndex)` 會取 `[beginIndex, endIndex)`：包含開始索引，不包含結束索引。

如果 `i` 是第一個不相同的位置，索引 `i` 不屬於共同前綴，所以要回傳 `substring(0, i)`，不能寫成 `substring(0, i + 1)`。

### 逐字元解析數字：String to Integer (atoi)

把字串轉成整數時，用一個索引依序走過「空白 → 正負號 → 數字」三段，每段只推進同一個 `i`。累加 `num = num * 10 + digit` 會在 `int` 溢位後繞成負數，所以檢查必須放在乘 10 之前：`num > MAX / 10`，或 `num == MAX / 10 && digit > MAX % 10`，兩者任一成立就溢位。只有「等於邊界」時才看下一位，否則 `2147483640` 會被誤判。

```java
if (num > Integer.MAX_VALUE / 10
        || (num == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
    return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
}
num = num * 10 + digit;
```

十進位取最後一位用 `% 10`、去掉最後一位用 `/ 10`；二進位才是 `& 1` 與 `>> 1`。

### 常見字串解題檢查

- 空字串是否可能是答案？共同前綴可能長度為 `0`。
- 某個字串是否是其他字串的前綴？要限制比較長度，避免索引越界。
- `substring` 的結束索引是否誤以為包含？Java 是不包含。
- 是否修改了輸入？`Arrays.sort(strs)` 會改變原本的陣列順序。
- 複雜度是否只看演算法步驟，卻漏算字串比較本身的字元成本？嚴謹分析時要加上最大字串長度 `L`。

## 已刷題目

- [3. Longest Substring Without Repeating Characters](../solutions/04-sliding-window/lc-3-longest-substring-without-repeating-characters.md) Medium — 用滑動窗口與字元最後出現位置維持無重複子字串。
- [8. String to Integer (atoi)](../solutions/03-string/lc-8-string-to-integer-atoi.md) Medium — 固定流程的逐字元解析，重點在乘 10 之前預判 `int` 溢位並夾到邊界值。
- [14. Longest Common Prefix](../solutions/01-arrays-hashing/lc-14-longest-common-prefix.md) Easy — 排序後只比較字典序最小與最大的字串，並確認 `substring(0, i)` 的右邊界不包含。
- [28. Find the Index of the First Occurrence in a String](../solutions/01-arrays-hashing/lc-28-find-the-index-of-the-first-occurrence-in-a-string.md) Easy — 在字串中尋找目標片段的起始位置。
- [151. Reverse Words in a String](../solutions/02-two-pointers/lc-151-reverse-words-in-a-string.md) Medium — 處理空白與單字順序。
- [344. Reverse String](../solutions/02-two-pointers/lc-344-reverse-string.md) Easy — 用雙指標交換字元。
- [345. Reverse Vowels of a String](../solutions/02-two-pointers/lc-345-reverse-vowels-of-a-string.md) Easy — 雙指標只交換符合條件的字元。
- [443. String Compression](../solutions/02-two-pointers/lc-443-string-compression.md) Medium — 讀寫指標壓縮連續字元。
- [459. Repeated Substring Pattern](../solutions/03-string/lc-459-repeated-substring-pattern.md) Easy — 判斷字串是否由重複片段組成。
- [541. Reverse String II](../solutions/02-two-pointers/lc-541-reverse-string-ii.md) Easy — 依固定區段反轉字元。
- [796. Rotate String](../solutions/03-string/lc-796-rotate-string.md) Easy — 用字串拼接判斷旋轉關係。
- [1071. Greatest Common Divisor of Strings](../solutions/21-math-geometry/lc-1071-greatest-common-divisor-of-strings.md) Easy — 用拼接關係與最大公因數找共同重複片段。
- [1108. Defanging an IP Address](../solutions/03-string/lc-1108-defanging-an-ip-address.md) Easy — 逐字元替換字串內容。
- [1768. Merge Strings Alternately](../solutions/02-two-pointers/lc-1768-merge-strings-alternately.md) Easy — 交錯合併兩個字串。
- [4030. Check ASCII Palindromic](../solutions/02-two-pointers/lc-4030-check-ascii-palindromic.md) Easy — 每個字元轉固定 8-bit 二進位再串接,判斷整體回文而不必真的建出完整字串。
- [981. Time Based Key-Value Store](../solutions/08-binary-search/lc-981-time-based-key-value-store.md) Medium — value 只是字串，練習重點在 hash map 搭配 binary search，不在字串操作本身。

## 相關

- [_moc](../_moc.md)
- [刷題規劃：String](../_moc.md)
- [Two Pointers 主題](T02-21-two-pointers.md)
