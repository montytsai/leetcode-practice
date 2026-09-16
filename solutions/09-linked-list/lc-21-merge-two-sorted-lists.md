---
title: "Merge Two Sorted Lists"
difficulty: Easy
topics: [Linked List, Recursion]
category: 09-linked-list
order: 11
source: [Grind75]
platform: LeetCode
url: https://leetcode.com/problems/merge-two-sorted-lists/
status: ac-unknown
note: ""
date_created: 2026-07-14
date_updated: 2026-07-14
---

# 21. Merge Two Sorted Lists

## 題目說明

- 給定兩個已依非遞減順序排列的 linked lists，將它們合併成一條同樣排序的 linked list。
- 合併時重新連接既有節點；其中一條 list 用完後，可以直接接上另一條的剩餘部分。

## 心得

dummyHead 接著走，while 迴圈要記得讓 node 繼續往下走——這個細節容易忘。

---

## 解法一：迭代 Two Pointers + Dummy Node

### Intuition

利用兩個 pointers 同時遍歷兩條已排序的 linked lists。每次比較目前節點值，把較小的節點接到新 list 尾端，便能維持排序。

Dummy node 可以統一處理第一個節點，不必另外判斷新 list 是否仍為空。這份程式的 `KEY!` 是：接上節點後，`cur` 也必須往前移，否則後續會一直改寫同一個 `next`。

### Approach

1. 建立 dummy node，並讓 `cur` 指向它。
2. 當兩條 lists 都還有節點時，比較 `list1.val` 與 `list2.val`。
3. 把較小的節點接到 `cur.next`，並移動該 list 的 pointer。
4. 將 `cur` 移到剛接上的節點。
5. 其中一條 list 用完後，把另一條的剩餘節點一次接上。

迴圈 invariant 是：`dummy.next` 到 `cur` 始終是目前已處理節點組成的排序結果，而 `list1`、`list2` 分別指向尚未處理的第一個節點。

### Complexity

**Time complexity: `O(n + m)`**

令 `n` 與 `m` 分別為兩條 lists 的長度。每個節點最多被走訪並接上一次。

**Space complexity: `O(1)`**

程式只使用 dummy node 與固定數量的 pointers，並重新連接既有節點；不計回傳結果時，額外空間為常數。

### Code

```java
/**
 * 21. Merge Two Sorted Lists
 * Time Complexity: O(n + m)
 * Space Complexity: O(1)
 */
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Use a dummy node to handle edge cases easily.
        ListNode dummy = new ListNode(-101);
        ListNode cur = dummy;

        while (cur != null && list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            // KEY! Move the current pointer to the new last node.
            cur = cur.next;
        }

        // Link the remaining nodes from the unfinished list.
        cur.next = list1 == null ? list2 : list1;
        return dummy.next;
    }
}
```

### Code Review

- **Learning provenance**：未確認；原稿沒有記錄解法來源或能否獨立重現。
- **KEY!**：`cur = cur.next` 是必要步驟。它讓 tail pointer 跟上剛接入的節點，才能在下一輪繼續從正確尾端串接。
- **Correctness / invariant**：每輪選擇兩個目前節點中較小者，因此已合併部分持續有序；最後直接接上剩餘 list 仍能維持排序，沒有正確性 bug。
- **Strength**：dummy node 消除了 head 的特殊處理；結束後一次接上剩餘節點，不需要逐一走完。
- **Style**：`cur != null` 是 redundant condition，因為 `cur` 從 dummy 開始且只會移到實際節點，不會在迴圈內變成 `null`；移除後可讀性更集中，但保留不影響正確性。
- **Trade-off**：這個解法會修改並重用輸入 lists 的連結；若呼叫端需要保留原結構，就必須改為建立新節點，並付出 `O(n + m)` 額外空間。

### Optimality

以時間複雜度為指標，`O(n + m)` 已是漸進最佳，因為最壞情況必須處理兩條 lists 的所有節點。以額外空間為指標，重用原節點的 `O(1)` 也已最佳。
