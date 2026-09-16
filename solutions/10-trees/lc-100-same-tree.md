---
title: "Same Tree"
difficulty: Easy
topics: [Binary Tree, Tree, Depth-First Search, Breadth-First Search]
category: 10-trees
order: 44
source: [Grind75]
platform: LeetCode
url: https://leetcode.com/problems/same-tree/
status: ac-unknown
note: ""
date_created: 2026-08-04
date_updated: 2026-08-04
---

## 心得

遞迴 DFS 秒解，試著複習迭代寫法，結果太久沒寫而混淆前序與中序寫法。

這題只比較節點的 `val` 與樹的結構，不比較節點的 hash 或物件 identity。

## Java

### 解法一：遞迴 DFS

```java
class Solution {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        }

        if (p.val != q.val) {
            return false;
        }

        // Compare corresponding left subtrees and right subtrees.
        return isSameTree(p.left, q.left)
                && isSameTree(p.right, q.right);
    }
}
```

#### 遞迴的判斷順序

每一對對應節點都要依序確認：

1. 兩邊都是 `null`：這一對子樹相同。
2. 只有一邊是 `null`：結構不同。
3. `p.val != q.val`：值不同。
4. 遞迴比較左子樹與右子樹。

`&&` 有短路特性：左子樹已經不同時，右子樹不會再比較。

### 解法二：迭代 DFS 前序走訪

```java
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

class Solution {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        List<Integer> pList = toList(p);
        List<Integer> qList = toList(q);

        if (pList.size() != qList.size()) {
            return false;
        }

        return pList.equals(qList);
    }

    private List<Integer> toList(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        // LinkedList allows null elements; ArrayDeque does not.
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();

            // Keep null markers to preserve the tree structure.
            list.add(curr != null ? curr.val : null);
            if (curr == null) {
                continue;
            }

            // Preorder is root -> left -> right.
            // Push right first, so left is popped and processed first.
            stack.push(curr.right);
            stack.push(curr.left);
        }

        return list;
    }
}
```

#### 為什麼一定要保存 `null`？

只記錄值會遺失結構。例如：

```text
    1          1
   /            \
  2              2
```

兩棵樹的值都是 `[1, 2]`，但左子樹與右子樹不同。加入 `null` 標記後，前序結果會不同，才能分辨結構。

#### 前序和中序的 stack 差別

這份迭代解是前序：

```text
root -> left -> right
```

因為 stack 是後進先出，所以要：

```java
stack.push(curr.right);
stack.push(curr.left);
```

右邊先 push，左邊後 push，pop 時才會先處理左邊。

中序則是：

```text
left -> root -> right
```

不能只靠「右先 push、左後 push」完成，通常要一路把左節點 push 進 stack，pop 出節點後再處理右子樹。

## 複雜度

令 `p`、`q` 是兩棵樹的節點數，`hp`、`hq` 是兩棵樹的高度。

### 遞迴 DFS

- Time：嚴謹寫法是 `O(min(p, q))`；常見簡化寫法是 `O(p + q)` 的上界。
- Space：`O(min(hp, hq))`，來自遞迴 stack；若樹退化成 linked list，最壞是 `O(n)`。

### 迭代 DFS + list

- `toList(p)` 與 `toList(q)`：`O(p + q)`。
- `pList.equals(qList)`：最壞 `O(p + q)`，已被總時間涵蓋。
- Time：`O(p + q)`。
- Space：`O(p + q)`，因為兩個 list 都保存了節點值與 `null` 標記。

## 相關

- [我的 binary-tree-traversal-dfs 筆記](https://github.com/montytsai/leetcode-practice/blob/main/doc/topics/binary-tree-traversal-dfs.md)
- [_moc](../../_moc.md)
