---
title: "Product of Array Except Self"
difficulty: Medium
topics: [Array, Prefix Sum]
category: 01-arrays-hashing
order: 12
source: [LeetCode75]
platform: LeetCode
url: https://leetcode.com/problems/product-of-array-except-self/
status: ac-unknown
note: ""
date_created: 2026-02-25
date_updated: 2026-09-01
---

# 238. Product of Array Except Self

## 題目說明

- 給一個整數陣列 `nums`,回傳同長度的 `answer`,其中 `answer[i]` 等於「除了 `nums[i]` 以外所有元素的乘積」。
- 題目硬條件:**不准用除法**,時間必須 `O(n)`。
- 官方保證任何前綴或後綴乘積都塞得進 32-bit int,所以中間值不會溢位。
- `nums.length >= 2`;陣列可能含 0(甚至兩個以上的 0),所以「全部乘起來再除掉自己」這條路本來就走不通。

## 心得

重刷秒解:一個元素的答案 = 它左邊全部的乘積 × 右邊全部的乘積,左邊用 prefix 陣列存、右邊用一個滾動變數帶著走,兩趟掃完。還沒做的是把 prefix 直接寫進答案陣列,把額外空間壓到 `O(1)`。

---

## 解法一:前綴乘積陣列 + 後綴滾動變數

### Intuition

把「除了自己以外的乘積」拆成兩半來看:`answer[i] = (nums[0] × … × nums[i-1]) × (nums[i+1] × … × nums[n-1])`,也就是**左半邊的乘積 × 右半邊的乘積**。這樣一拆,除法就完全用不到了——0 也不再是特例,因為 0 只會讓「包含它的那一半」變成 0,不會出現除以 0 的問題。

左半邊得從左往右累積、右半邊得從右往左累積,方向相反,所以自然是兩趟掃描。左半邊先算好存進 `prefix` 陣列;右半邊在第二趟由右往左走的時候現算,用一個變數 `suffix` 一路乘過去就好,不必再開一個陣列。

提交裡那段註解表格就是這個拆法的手算版:第二列是每個位置的左乘積,第三列是右乘積,兩列相乘就是答案。

### Approach

1. 第一趟由左往右填 `prefix`:`prefix[0] = 1`(0 號左邊沒有東西,乘法單位元素是 1),之後 `prefix[i] = nums[i-1] * prefix[i-1]`。
   - Invariant:迴圈結束時 `prefix[i]` = `nums[0..i-1]` 的乘積。
2. 第二趟由右往左,維持一個 `suffix`,初值 1。
   - Invariant:**在處理位置 `i` 之前**,`suffix` = `nums[i+1..n-1]` 的乘積。
   - 先寫 `answer[i] = prefix[i] * suffix`(此時 `suffix` 還沒吃進 `nums[i]`,正是我們要的「不含自己」),再 `suffix *= nums[i]`,讓 invariant 對下一個位置 `i-1` 繼續成立。
3. 正確性就靠這兩個 invariant:左邊乘積由 `prefix[i]` 保證,右邊乘積由 `suffix` 保證,兩者都不含 `nums[i]`。
4. **順序是關鍵**:第二趟一定要「先讀後更新」。反過來寫 `suffix *= nums[i]` 再取值,答案就會把自己乘進去。

### Complexity

**Time complexity: `O(n)`**

`n` = `nums.length`。兩趟獨立的線性掃描,每趟每個元素做常數次乘法,合計 `2n` 次 → `O(n)`。這已經觸底:答案的每一格都依賴全部元素,任何解法至少得把陣列讀完一次。

**Space complexity: `O(n)`**

額外空間來自 `prefix` 這個長度 `n` 的陣列。`answer` 是題目要求的回傳值,依慣例不計入額外空間;`suffix` 只是單一變數,`O(1)`。

### Code

```java
/**
 * 238. Product of Array Except Self
 * Time Complexity: O(n)
 * Space Complexity: O(n) extra for the prefix array
 *
 * Worked example for [1, 2, 3, 4]:
 *   nums    [ 1,  2,  3,  4]
 *   prefix    1   1   2   6   (product of everything on the left)
 *   suffix   24  12   4   1   (product of everything on the right)
 *   answer   24  12   8   6   (prefix * suffix)
 */
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;

        // Pass 1: prefix[i] holds the product of nums[0..i-1].
        int[] prefix = new int[len];
        prefix[0] = 1; // nothing on the left of index 0
        for (int i = 1; i < len; i++) {
            prefix[i] = nums[i - 1] * prefix[i - 1];
        }

        // Pass 2: suffix holds the product of nums[i+1..len-1].
        int[] answer = new int[len];
        int suffix = 1;
        for (int i = len - 1; i >= 0; i--) {
            // Read before update, so suffix never includes nums[i] itself.
            answer[i] = prefix[i] * suffix;
            suffix *= nums[i];
        }

        return answer;
    }
}
```

### Code Review

- **Learning provenance**:`self-solved`。這是**重刷,秒解**(2026-02-25 首次 AC,2026-09-01 再次 AC)。能否獨立重現:依「秒解」的自述判定為可以;是否能同樣快寫出 `O(1)` 額外空間版,未確認。
- **Correctness / invariant**:正確。兩個 invariant(見 Approach)各自守住左右兩半,且 `answer[i]` 在 `suffix` 吃進 `nums[i]` 之前就寫好,所以不含自己。`prefix[0] = 1` 與 `suffix = 1` 用乘法單位元素當邊界,不需要對頭尾特判。含 0 的輸入自動正確:恰好一個 0 時只有該位置非 0,兩個以上 0 時全為 0。
- **Strength**:
  - 用一個滾動變數取代第二個陣列,已經把「兩個陣列」的教科書寫法砍掉一半,只剩 `prefix` 可再壓。
  - 第二趟合併了「算後綴」與「寫答案」,只走一趟迴圈,而不是先建 suffix 陣列再合成。
  - 提交裡附的手算表格是很好的自我驗證習慣,直接把 invariant 畫出來,重刷時能秒回想起拆法。
- **Bug**:無。
- **Trade-off**:`prefix` 是唯一可省的額外空間(見下方 Optimality),但保留它換來的是「左右兩半各自命名、讀起來一眼看懂」;面試時先寫這版再講優化,是安全的順序。
- **Style**:無問題。命名 `prefix` / `suffix` / `answer` 直接對應概念,不需要改。
- **Edge cases**:
  - `len == 2`:第一趟只跑 `i = 1`,第二趟兩格,正確。題目保證 `len >= 2`,所以 `prefix[0]` 不會越界。
  - 含 0 與含負數:皆自然正確,不需特判。
  - 溢位:靠題目「任何前綴/後綴乘積都在 int 範圍內」的保證。**這是題目給的,不是程式碼本身的性質**——同樣邏輯搬到沒有這條保證的場合,`int` 要換成 `long`。

---

## Optimality

- **時間**:`O(n)` 已是最佳。答案的每一格都依賴全部輸入,下界就是讀完整個陣列一次。
- **額外空間**:**不是最佳**,還有一階可壓。

唯一值得做的改進:**把 prefix 直接寫進 `answer`,省掉獨立的 `prefix` 陣列**。第一趟改成往 `answer[i]` 填左乘積(語意完全一樣,只是換了容器);第二趟由右往左時,`answer[i] *= suffix` 就地把右乘積乘上去,再 `suffix *= nums[i]`。時間仍是 `O(n)` 兩趟,額外空間降到 `O(1)`(只剩 `suffix` 一個變數)。

什麼時候選它:面試講到 follow-up「Can you solve it in O(1) extra space?」時——這題的 follow-up 幾乎必問,而這個改法不需要新想法,只是**認出「左乘積」和「最終答案」可以共用同一塊記憶體**。代價是可讀性略降(同一個陣列在兩趟裡代表兩種東西),所以正式寫的時候記得補一行註解說明 `answer` 的階段語意。

## 相關

- [Array](../../topics/T01-21-array.md) — 前綴/後綴的線性掃描:方向相反的兩趟,把「除了自己」拆成左右兩半
- [Prefix Sum](../../topics/T05-21-prefix-sum.md) — 前綴乘積版的前綴和;學到「後綴那一半可以用滾動變數取代陣列」
- [LeetCode 刷題總覽](../../_moc.md)
