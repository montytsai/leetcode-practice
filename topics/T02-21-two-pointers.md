# Two Pointers

Two Pointers 題用兩個索引共同描述掃描狀態，藉由移動其中一邊或兩邊，避免重複搜尋。常見方向包括左右夾逼、同向快慢指標，以及從尾端反向掃描。

## 解題技巧

1. 先定義 pointer invariant：每個 pointer 現在指向什麼，以及移動後必須維持什麼狀態。
2. 左右夾逼適合 palindrome；兩端字元符合條件時一起往中間移動。
3. 若輸入包含會讓前面內容失效的操作，例如 backspace，可從尾端掃描並用 counter 跳過失效字元。
4. helper 正規化 pointer 後，先處理「兩邊耗盡／只耗盡一邊／兩邊都有效」三種狀態，再比較值。
5. 同向讀寫指針可做原地改寫：`read` 掃過每個元素，`write` 指向下一個可寫入的位置，只在該保留時才寫入並前進。判準是「被覆蓋掉的內容不需要保留」——`write` 永遠不超過 `read`，被覆蓋的位置一定已讀過，成立就無腦覆寫，不成立才需要 swap。
6. 已排序陣列的兩端若都可能產生最大值，可左右夾逼比較候選值，並從結果尾端反向填入；每次取走較大者後移動對應指針。
7. k-Sum 系列(3Sum/4Sum)剪枝時，不能只拿單一數字跟 `target` 比較大小；`target` 固定為 `0` 時 `nums[i] > 0` 才安全 break，`target` 可正可負時必須比較「以這個索引開頭、陣列排序後能湊出的最小 k 數和」(緊鄰後面 k-1 個數字的和)，超過 `target` 才能 break。反方向的剪枝同理：要用「陣列裡能拿到的最大值」湊，湊不到才代表目前索引太小該往右跳。
8. 同一個讀寫指標手法可以連續套用多輪，做多路分類(如三色排序：先分 0、再分 1，剩下自動是 2)。判準第 5 點的「無腦覆寫」只在被踢出去的值不需要保留時成立；如果每個值都要保留(不是廢值)，要換成 **swap**——被換出 write 位置的值不會消失，而是移到 read 位置，等這輪或下一輪繼續處理。

```java
int write = 0;
for (int read = 0; read < nums.length; read++) {
    if (shouldKeep(nums[read])) {
        nums[write] = nums[read];
        write++;
    }
}
// write 之後的位置留給收尾（補預設值或直接回傳 write 當長度）
```

卡在「指標怎麼移動」時的解法：先問「如果允許額外空間我會怎麼做」，寫出那個版本，再檢查能不能壓回原陣列。

常見陷阱：index `0` 仍是合法位置，只有 `< 0` 才代表耗盡；兩個 pointer 的數字相等，也不代表它們指向的內容相等。

## 已刷題目

- [Container With Most Water](../solutions/02-two-pointers/lc-11-container-with-most-water.md) Medium
- [3Sum](../solutions/02-two-pointers/lc-15-3sum.md) Medium
- [4Sum](../solutions/02-two-pointers/lc-18-4sum.md) Medium — 剪枝要比「以 i 開頭能湊出的最小四數和」跟 target，不能只比 `nums[i]` 單一值，因為 target 可正可負。
- [Remove Nth Node From End of List](../solutions/09-linked-list/lc-19-remove-nth-node-from-end-of-list.md) Medium
- [Remove Element](../solutions/01-arrays-hashing/lc-27-remove-element.md) Easy
- [Find the Index of the First Occurrence in a String](../solutions/01-arrays-hashing/lc-28-find-the-index-of-the-first-occurrence-in-a-string.md) Easy
- [Sort Colors](../solutions/01-arrays-hashing/lc-75-sort-colors.md) Medium — 同一招連續套用兩輪做三色分類；換成 swap 而非覆寫，因為被換出去的值仍要留在陣列裡等下一輪處理。
- [Valid Palindrome](../solutions/02-two-pointers/lc-125-valid-palindrome.md) Easy
- [Linked List Cycle](../solutions/09-linked-list/lc-141-linked-list-cycle.md) Easy
- [Linked List Cycle II](../solutions/09-linked-list/lc-142-linked-list-cycle-ii.md) Medium
- [Reverse Words in a String](../solutions/02-two-pointers/lc-151-reverse-words-in-a-string.md) Medium
- [Intersection of Two Linked Lists](../solutions/09-linked-list/lc-160-intersection-of-two-linked-lists.md) Easy
- [Happy Number](../solutions/21-math-geometry/lc-202-happy-number.md) Easy
- [Palindrome Linked List](../solutions/09-linked-list/lc-234-palindrome-linked-list.md) Easy
- [Meeting Rooms II](../solutions/19-intervals/lc-253-meeting-rooms-ii.md) Medium
- [Move Zeroes](../solutions/02-two-pointers/lc-283-move-zeroes.md) Easy — 同向讀寫指針原地改寫；被丟掉的只有 0，可以安全覆寫。
- [Reverse String](../solutions/02-two-pointers/lc-344-reverse-string.md) Easy
- [Reverse Vowels of a String](../solutions/02-two-pointers/lc-345-reverse-vowels-of-a-string.md) Easy
- [Intersection of Two Arrays](../solutions/01-arrays-hashing/lc-349-intersection-of-two-arrays.md) Easy
- [Is Subsequence](../solutions/01-arrays-hashing/lc-392-is-subsequence.md) Easy
- [String Compression](../solutions/02-two-pointers/lc-443-string-compression.md) Medium
- [Assign Cookies](../solutions/18-greedy/lc-455-assign-cookies.md) Easy
- [Reverse String II](../solutions/02-two-pointers/lc-541-reverse-string-ii.md) Easy
- [Partition Labels](../solutions/18-greedy/lc-763-partition-labels.md) Medium
- [Backspace String Compare](../solutions/02-two-pointers/lc-844-backspace-string-compare.md) Easy
- [Middle of the Linked List](../solutions/09-linked-list/lc-876-middle-of-the-linked-list.md) Easy
- [Squares of a Sorted Array](../solutions/08-binary-search/lc-977-squares-of-a-sorted-array.md) Easy — 比較兩端平方值，從結果尾端放入較大者。
- [Max Number of K-Sum Pairs](../solutions/02-two-pointers/lc-1679-max-number-of-k-sum-pairs.md) Medium
- [Merge Strings Alternately](../solutions/02-two-pointers/lc-1768-merge-strings-alternately.md) Easy
- [Delete the Middle Node of a Linked List](../solutions/09-linked-list/lc-2095-delete-the-middle-node-of-a-linked-list.md) Medium
- [Maximum Twin Sum of a Linked List](../solutions/09-linked-list/lc-2130-maximum-twin-sum-of-a-linked-list.md) Medium
- [Successful Pairs of Spells and Potions](../solutions/08-binary-search/lc-2300-successful-pairs-of-spells-and-potions.md) Medium
- [Check ASCII Palindromic](../solutions/02-two-pointers/lc-4030-check-ascii-palindromic.md) Easy — 左右指標比較「鏡射區塊」是否互為反轉,不用真的串出完整二進位字串。

## 相關

- [_moc](../_moc.md)
- [Stack & Queue](T06-21-stack-queue.md)
