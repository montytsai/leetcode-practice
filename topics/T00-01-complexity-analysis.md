# 時間與空間複雜度分析

複雜度是在估算：輸入規模變大時，程式需要增加多少運算時間與額外記憶體。分析時要先定義變數，再找出主要操作的次數，最後把「次數」乘上「一次操作的成本」。

## 基本方法

### 第一步：定義輸入變數

不要直接看到 `n` 就寫答案，要先說清楚 `n` 代表什麼。例如 Longest Common Prefix：

- `n`：字串的數量
- `L`：最長字串的長度

如果輸入有兩個不同維度，就要保留兩個變數。只用 `n` 可能會漏掉字串本身長度造成的成本。

### 第二步：找主要操作的次數

常見次數：

| 程式結構 | 常見時間 |
|---|---:|
| 一次掃描 `n` 個元素 | `O(n)` |
| 巢狀兩層各跑 `n` 次 | `O(n²)` |
| 每次把問題縮小一半 | `O(log n)` |
| 排序 `n` 個元素 | `O(n log n)` |
| `n` 個元素各做一次 `O(L)` 工作 | `O(nL)` |

### 第三步：一次操作不一定是 `O(1)`

陣列中的整數比較通常可以視為 `O(1)`，但字串比較可能需要逐字元檢查。

```text
比較兩個字串：O(L)
排序字串數量：O(n log n) 次比較
總時間：O(n log n) × O(L)
       = O(n log n · L)
```

例如：

```text
"aaaaaaaaax"
"aaaaaaaaay"
```

兩個字串直到最後一個字元才不同，因此一次比較可能檢查接近 `L` 個字元。

### 乘法與加法

如果一個流程是「先做 A，再做 B」，使用加法：

```text
O(n) + O(n²) = O(n²)
```

如果是「每一次 A 都要做一次 B」，使用乘法：

```text
n 次 × 每次 O(L) = O(nL)
```

Longest Common Prefix 的排序分析就是乘法：

```text
O(n log n) 次字串比較 × O(L) 每次比較
= O(n log n · L)
```

## 空間複雜度怎麼算

### Auxiliary space 與輸入空間

通常不把原本傳進來的輸入算入額外空間，只計算演算法另外建立的資料：

```java
int i = 0;
String first = strs[0];
String last = strs[strs.length - 1];
```

這些都是固定數量的變數，所以是 `O(1)`。

如果建立長度為 `n` 的陣列：

```java
int[] result = new int[n];
```

額外空間就是 `O(n)`。

### 函式呼叫的內部空間也要看

自己沒有建立陣列，不代表整個方法一定是 `O(1)`。被呼叫的函式可能自己使用空間：

```java
Arrays.sort(strs);
```

`String[]` 是物件陣列。Java 的物件陣列排序實作可能使用暫存陣列，因此把函式內部也算入時，排序額外空間可寫成 `O(n)`。

所以同一段程式可以有兩種合理說法：

```text
只算自己寫的變數：O(1)
把 Arrays.sort 的暫存空間算進去：O(n)
```

回答時要說明採用哪一種口徑，不要只丟一個沒有上下文的數字。

### 輸出空間通常另外標記

如果方法回傳長度為 `L` 的新字串，輸出本身需要 `O(L)` 空間；但很多題目的 auxiliary space 會排除輸出空間。

```text
不計輸出：只分析額外工作空間
計入輸出：額外空間 + output space
```

## 常見排序題的寫法

### 整數陣列排序

先確認排序實作與語言：有些排序可以原地完成，有些會使用暫存陣列。若題目只要求一般 Big-O，通常寫排序時間 `O(n log n)`；空間則依題目語言或實作註明 `O(1)`、`O(log n)` 或 `O(n)`。

### Java `Arrays.sort(String[])`

對物件陣列排序時，不應無條件宣稱整個方法空間是 `O(1)`。比較精確的說法是：

```text
時間：O(n log n · L)
空間：O(n)，若計入物件陣列排序的暫存空間
      O(1)，若只計自己宣告的變數
```

## 先看規模，再決定值不值得做常數優化

Big-O 是「`n` 趨近無窮」時的比較工具；題目給的 `constraints` 才是「這次真正會發生的事」。判斷一個 cache／memo／查表值不值得寫，不要只看漸近複雜度等級，要先把實際操作次數算出來再比較。

### 判斷步驟

1. **代入 constraints 裡的最大值，算出實際操作次數**：不要停在 `O(n)` 這種抽象符號，把 `n` 換成題目給的上限，算出一個具體數字。例如 `n <= 100`、每個元素要做 8 次固定運算，總共最多 `800` 次基本運算。
2. **跟現代硬體一次能做的量級比較**：粗略的經驗門檻——`10^8` 次基本運算內，單次執行幾乎感覺不到時間；`10^6` 次以下完全不用考慮任何常數優化。
3. **算出來的數字遠低於門檻，優化就是在修一個不痛的地方**：任何用來降常數的機制（cache、memo、查表）都不會帶來肉眼可見的效能提升，只會多付「維護這個 cache」的閱讀與正確性成本。這時候選狀態最少、最直接的寫法才是好答案。

### 什麼時候快取才真的有意義

- **同一個昂貴計算被重複呼叫非常多次**，重複次數本身跟 `n` 同量級甚至更高——例如遞迴子問題被重複算指數多次，才需要 memo 把它壓回多項式（這是 memo 真正在賺的地方，跟本題單純快取「查表結果」不同）。
- **單次計算本身不便宜**：不是 `O(1)` 或 `O(8)` 這種小常數，而是 `O(k)` 且 `k` 不小。
- **輸入規模夠大**：通常要 `n` 到 `10^4`～`10^5` 以上，常數因子的差異才會在總時間裡看得出來；`n <= 100` 這種題目給的訊號，就是在告訴你不用往這個方向想。

### 範例：Check ASCII Palindromic（[4030](../solutions/02-two-pointers/lc-4030-check-ascii-palindromic.md)）

字元轉 8-bit 表示只是 8 次位元運算，`n <= 100` 代入後最多也就 `800` 次，遠低於 `10^6` 門檻。原本用 `dic`/`reverse` 兩個陣列快取每個字元的轉換結果，看似省了重複運算，但省下來的量本身就小到可以忽略——快取沒有換到實質效能，只換到兩個要多推理的 mutable state。拿掉快取、每次直接用位移運算 `(x >> k) & 1` 現算，時間複雜度不變，程式碼反而更直接。

## 開陣列時，怎麼判斷大小安不安全

上一節判斷的是「值不值得做常數優化」——加了會不會有感。這一節判斷的是另一件事：**開一個大小等於 value range 的陣列，會不會直接爆掉**。這兩種判斷不能用同一套標準：常數優化只是省時間、省不到也就浪費一點程式碼複雜度；陣列開太大是直接 OOM 或 TLE，判斷錯了會整題掛掉。

### 判斷步驟

1. **確認陣列大小綁定的是題目 constraints 裡有明確上限的值，不是憑感覺猜的**。用「輸入陣列長度 `n`」跟「陣列要開的大小 `range`」是兩件事——`range` 必須在題目的 constraints 裡有自己的保證上限，才能放心用它決定陣列大小。如果題目只保證 `n` 小、卻沒限制 value 的範圍（例如允許到 `1e9`），用 value range 直接開陣列就是地雷。
2. **算出這個上限的實際大小，對照平台記憶體量級**。經驗門檻：primitive 陣列（`boolean[]`／`int[]`）開到 `10^7`～`10^8` 通常還在安全範圍（`int[1e7]` 約 40MB，多數線上評測 Java 記憶體上限是幾百 MB）；超過這個量級要開始考慮換成雜湊表、區間壓縮或其他不用開滿整個 value range 的做法。
3. **上限被鎖住、數字又遠低於門檻，就是安全、正確的選擇，不是需要謹慎小心的地雷區**——這種情況下開陣列不是「多餘的優化」，而是真正換到漸近時間改善（`O(n + range)` 打敗 `O(n log n)`），該用就用。

### 範例：Find All Numbers Disappeared in an Array II（[4031](../solutions/01-arrays-hashing/lc-4031-find-all-numbers-disappeared-in-an-array-ii.md)）

`range = upper - lower + 1`，題目保證 `upper <= 1e5`，所以 `range` 上限就是 `1e5`，跟 `nums.length` 是不是也 `<= 1e5` 無關——重點是 `upper` 本身有沒有被鎖住。`boolean[1e5]` 大約 100KB，遠低於 `10^7` 的安全門檻，直接開陣列既安全又能拿到比排序解法更好的時間複雜度。

## 複雜度分析模板

```text
Let n be ... and L be ... .

Time complexity is O(...), because ... .
If one operation compares strings, each comparison may cost O(L),
so the total becomes O(... · L).

Auxiliary space is O(...), excluding the input and output.
If a library sort uses an internal buffer, include that buffer separately.
```

## 已刷題目

- [14. Longest Common Prefix](../solutions/01-arrays-hashing/lc-14-longest-common-prefix.md) Easy — 從排序與字串比較理解 `O(n log n · L)`，並區分自己變數的 `O(1)` 與排序內部暫存的 `O(n)`。
- [4030. Check ASCII Palindromic](../solutions/02-two-pointers/lc-4030-check-ascii-palindromic.md) Easy — 輸入規模小(`n <= 100`)時，快取省下的常數成本可以忽略，先看規模再決定值不值得做。
- [4031. Find All Numbers Disappeared in an Array II](../solutions/01-arrays-hashing/lc-4031-find-all-numbers-disappeared-in-an-array-ii.md) Medium — 陣列大小綁定 `upper <= 1e5` 這個有保證的上限，換算實際大小遠低於安全門檻，直接開陣列既安全又能拿到更好的時間複雜度。

## 相關

- [_moc](../_moc.md)
- [String 主題](T03-21-string.md)
