---
title: "Reverse Linked List"
difficulty: Easy
topics: [Linked List, Recursion]
category: 09-linked-list
order: 3
source: [Grind75, Carl, LeetCode75]
platform: LeetCode
status: ac-unknown
note: ""
date_created: 2026-03-10
date_updated: 2026-07-21
---

## 心得

這題我寫很多次還是會錯，主要不是不知道要反轉，而是指標移動順序很容易混亂。這次把 `temp`、`curr.next`、`prev`、`curr` 的責任拆開記，並確認最後要回傳的是反轉後的新 head：`prev`。

## Java

### 迭代解法

```java
class Solution {

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            // Save the original next node before changing curr.next.
            ListNode temp = curr.next;

            // KEY! Reverse the current pointer.
            // The current node should point to the previous node.
            curr.next = prev;

            // KEY! Move prev forward.
            // curr becomes the new head of the reversed part.
            prev = curr;

            // Move curr to the original next node.
            curr = temp;
        }

        // KEY! curr is null now.
        // prev is the new head of the reversed linked list.
        return prev;
    }
}
```

### 遞迴解法

```java
class Solution {

    public ListNode reverseList(ListNode head) {
        return reverse(head, null);
    }

    private ListNode reverse(ListNode node, ListNode prev) {
        if (node == null) {
            // KEY! prev is the new head after all nodes are reversed.
            return prev;
        }

        ListNode next = node.next;

        // Reverse the current pointer.
        node.next = prev;

        // Pass the next unprocessed node and the current node as prev.
        return reverse(next, node);
    }
}
```

遞迴版本和迭代版本做的是同一件事；差別只是「下一輪」由 recursive call 保存，而不是由 `while` 迴圈保存。

## 我反覆卡住的指標問題

### 1. 為什麼一定要先存 `temp`？

原本：

```text
prev -> curr -> next
```

如果先執行：

```java
curr.next = prev;
```

原本的 `curr.next` 就被覆蓋了。如果沒有先存：

```java
ListNode temp = curr.next;
```

就找不回下一個尚未處理的節點。

### 2. 為什麼是 `curr.next = prev`？

反轉前：

```text
prev -> curr -> next
```

反轉後要變成：

```text
prev <- curr    next
```

所以要改的是目前節點的 next：

```java
curr.next = prev;
```

不是：

```java
prev.next = curr;
```

`prev.next = curr` 不會完成反轉，還可能讓指標重新連回已處理的鏈結。

### 3. 為什麼最後回傳 `prev`？

用：

```text
1 -> 2 -> 3
```

最後一輪結束時：

```text
prev = 3 -> 2 -> 1 -> null
curr = null
```

`curr` 代表尚未處理的部分，已經沒有節點；`prev` 才是反轉後鏈表的新 head。

### 4. 每一輪的固定順序

請固定記成：

```text
1. Save next
2. Reverse current pointer
3. Move prev
4. Move curr
```

對應程式：

```java
ListNode temp = curr.next;
curr.next = prev;
prev = curr;
curr = temp;
```

### 5. 迭代與遞迴的空間差異

- 迭代版本：只使用 `prev`、`curr`、`temp`，所以是 `O(1)`。
- 遞迴版本：每個節點都會建立一層 call stack，所以是 `O(n)`。

遞迴版本的 `return prev` 和迭代版本最後的 `return prev` 意義相同：都回傳反轉後的 new head。

## 複雜度

- Time：`O(n)`，每個節點處理一次。
- Iterative space：`O(1)`，只使用固定數量的指標。
- Recursive space：`O(n)`，來自遞迴 call stack。

## 相關

- [234. Palindrome Linked List](lc-234-palindrome-linked-list.md) — 在回文判斷中反轉後半段。
- [_moc](../../_moc.md)
