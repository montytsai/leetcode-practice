---
title: "Validate Binary Search Tree"
difficulty: Medium
topics: [Binary Tree, Tree, Depth-First Search, Binary Search Tree]
category: 10-trees
order: 28
source: [Carl]
platform: LeetCode
url: https://leetcode.com/problems/validate-binary-search-tree/
status: ac-unknown
note: ""
date_created: 2026-03-19
date_updated: 2026-09-02
---

# 98. Validate Binary Search Tree

## 題目說明

- 給一棵二元樹，判斷它是不是合法的 BST。
- 合法定義：每個節點的左子樹所有值都小於它、右子樹所有值都大於它，且這個條件對每一層子樹都成立（不能只比對直接子節點）。

## 心得

驗證 BST，不能只考慮當前節點，要考慮阿公阿媽層，要全都符合。使用前序：紀錄最小最大邊界值；中序：紀錄前一個值，由小到大。

---

## 解法一：DFS 邊界傳遞（前序）

### Intuition

單看父子關係不夠，因為右子樹裡任何一個節點都要大於所有祖先的下界，左子樹任何一個節點都要小於所有祖先的上界。解法是把這兩個邊界當參數往下傳：進到左子樹時上界收窄成當前節點值，進到右子樹時下界收窄成當前節點值。每個節點只要落在自己收到的 `(min, max)` 開區間內就合法。

邊界初始值要能容納任何合法節點值，題目節點值範圍到 `Integer.MIN_VALUE`／`MAX_VALUE`，直接用 `int` 當邊界會在節點值等於邊界時判斷錯誤，所以邊界用 `long`。

### Approach

1. 從根節點開始，帶入 `(Long.MIN_VALUE, Long.MAX_VALUE)` 當初始邊界。
2. 節點為 `null` 視為合法，回傳 `true`。
3. 節點值不在 `(min, max)` 開區間內，回傳 `false`。
4. 遞迴左子樹時把上界收窄為當前節點值，遞迴右子樹時把下界收窄為當前節點值；兩邊都合法才回傳 `true`。

### Complexity

**Time complexity: `O(n)`**

每個節點恰好被拜訪一次。

**Space complexity: `O(h)`**

`h` 是樹高，來自遞迴呼叫堆疊；平衡樹是 `O(log n)`，退化成鏈狀是 `O(n)`。

### Code

```java
/**
 * 98. Validate Binary Search Tree
 * Time Complexity: O(n)
 * Space Complexity: O(h), h = tree height (recursion stack)
 */
class Solution {
    public boolean isValidBST(TreeNode root) {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValid(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;

        return isValid(node.left, min, node.val) && isValid(node.right, node.val, max);
    }
}
```

### Code Review

- **Learning provenance**：`self-solved`；能否獨立重現：未確認。
- **Correctness / invariant**：邊界用 `long` 正確避開節點值卡在 `Integer.MIN_VALUE`／`MAX_VALUE` 時的誤判；每次遞迴只收窄不放寬，維持「當前節點必須落在所有祖先給定的開區間內」這個不變量。
- **Strength**：不用額外的實例欄位存狀態，邊界完全靠參數傳遞，函式本身無副作用。
- **Bug / Trade-off / Style**：沒有問題。
- **Edge cases**：單一節點、節點值剛好等於 `Integer.MIN_VALUE`／`MAX_VALUE` 都能正確處理。

---

## 解法二：中序走訪（Inorder）

### Intuition

BST 的中序走訪結果必為嚴格遞增序列，這是比邊界傳遞更直接的等價定義：不用管每個節點各自的合法範圍，只要走訪順序上前一個值一律小於後一個值就合法。

用 `Integer`（而非 `int`）存前一個值，靠 `null` 代表「還沒走訪過任何節點」，這樣才能跟合法的 `Integer.MIN_VALUE` 節點值分開，不會誤判。

**KEY!** 比較動作要放在「遞迴完左子樹之後、更新 `preVal` 之前」——這正是中序走訪的順序：左子樹全部走完才輪到自己，所以這裡才是整條路徑上「上一個被走訪的值」真正確定的時間點。

### Approach

1. 用一個實例欄位 `preVal` 記錄目前為止中序走訪到的前一個值，初始為 `null`。
2. 遞迴左子樹，若回傳 `false` 直接短路回傳。
3. 若 `preVal` 不為 `null` 且 `preVal >= node.val`，代表遞增序列被打破，回傳 `false`。
4. 更新 `preVal = node.val`，再遞迴右子樹並回傳其結果。

### Complexity

**Time complexity: `O(n)`**

每個節點恰好被拜訪一次。

**Space complexity: `O(h)`**

`h` 是樹高，來自遞迴呼叫堆疊；與解法一相同。

### Code

```java
/**
 * 98. Validate Binary Search Tree
 * Time Complexity: O(n)
 * Space Complexity: O(h), h = tree height (recursion stack)
 */
class Solution {
    public boolean isValidBST(TreeNode root) {
        return inorder(root);
    }

    private Integer preVal;

    private boolean inorder(TreeNode node) {
        if (node == null) return true;

        if (!inorder(node.left))
            return false;

        // KEY! The comparison happens after returning from the left subtree,
        // because that is the moment "the previous visited value" is settled.
        if (preVal != null && preVal >= node.val)
            return false;
        preVal = node.val;

        return inorder(node.right);
    }
}
```

### Code Review

- **Learning provenance**：`reference-assisted`（看之前自己的提交才想起這個解法）；能否獨立重現：未確認。
- **Correctness / invariant**：`Integer` 包裝型別 + `null` 哨兵正確避開與合法節點值 `Integer.MIN_VALUE` 的碰撞；`preVal >= node.val` 用非嚴格比較正確擋掉重複值。
- **Strength**：KEY! 註解點出的時機抓得準確——先遞迴左子樹、才比較、才更新 `preVal`，順序完全對應中序走訪定義，短路寫法也乾淨。
- **Bug / Trade-off / Style**：沒有問題；`preVal` 是實例欄位，若同一個 `Solution` 物件被重複呼叫 `isValidBST` 需注意狀態未重置，但本題呼叫模式不會觸發這個風險。
- **Edge cases**：全樹只有一個節點、節點值含 `Integer.MIN_VALUE`、值重複的節點都能正確處理。

---

## 解法比較

| 解法 | Time | Space | 優點 | Trade-off | 使用時機 |
| --- | --- | --- | --- | --- | --- |
| 解法一（邊界傳遞） | `O(n)` | `O(h)` | 無副作用、邊界語意直接對應題目定義 | 要記得把邊界型別升到 `long` | 想清楚表達「每層繼承的合法範圍」時 |
| 解法二（中序走訪） | `O(n)` | `O(h)` | 直接利用 BST 中序遞增這個性質，思路可延伸到找第 k 小、還原 BST 等題 | 需要一個額外欄位存前一個值，物件重用要小心狀態殘留 | 想凸顯或需要用到「中序遞增」這個性質時 |

### Optimality

兩個解法時間都是 `O(n)`，已是漸近最佳——驗證 BST 至少要看過每個節點一次。空間都是 `O(h)`，來自遞迴呼叫堆疊；本題規模下沒有壓縮空間的必要。若要進一步把額外空間壓到 `O(1)`，可以用 Morris 中序走訪（用暫時修改葉節點的右指標取代遞迴堆疊），但會犧牲程式碼可讀性，一般面試場景不必主動祭出。

---

## 2025 初刷版（Day27，2025-05-14）

*原文見 [archive/doc/daily/day27-2025-05-14.md](../../archive/doc/daily/day27-2025-05-14.md)，已停更，內容按當時所寫原樣搬入*

### LC98. Validate Binary Search Tree

#### 題目說明
- 給定一個二元樹，請判斷是否為一棵合法的「二元搜尋樹（BST）」。
- 二元搜尋樹需滿足：
    - 對每個節點 `node`，其左子樹所有節點值都 < `node.val`。
    - 右子樹所有節點值都 > `node.val`。
    - 且左右子樹也分別是 BST。

---

#### 思路筆記（陷阱與反省）

##### 陷阱1. 不能只比對左小右大

- 一開始的直覺是：只要當前節點的左節點比它小、右節點比它大就好。
- 但這樣是錯誤的，因為這樣只檢查了「一層」的左右節點，沒有檢查「整個子樹」是否符合 BST 的規則。
- 舉例：如果某個左子樹底下有個節點值比父節點還大，這樣也會違反 BST 規則，但你的檢查不會發現。

###### 反省：
- 遇到樹的題目時，不應只用局部值比較，要思考整體結構。
- 特別是遇到「排序、遞增」這類問題，應該優先考慮中序走訪。
- 也就是說，應該先問自己：「有沒有一種遍歷順序，能一次看到所有節點是否滿足條件？」這題答案是「中序」。
  - 中序走訪順序為`左 → 中 → 右`，這樣可以使 BST 由小排到大，只要比對前一個元素是否小於即可。

##### 陷阱2. 節點值可能是 `Integer.MIN_VALUE`，不能用 int 當上下限
- 在某些邊界情況出錯，例如節點剛好等於 `Integer.MIN_VALUE`。

###### 反省：
- Java 的 int 有範圍限制，有溢位問題。
- 應該改用 Long，這樣即使節點值是 int 的極限值，也能正確比較。

##### 陷阱3. 解法二中序遞歸時，`preVal` 沒有正確更新

- 前要：想用一個變數 preVal 來紀錄「前一個節點值」，讓當前節點能與上一個節點值做比較。

- 錯誤說明：
  - 把　preVal　用基本型別 int/long 當成參數傳入遞歸函式 
  - 中序會先走左子元素，左子元素更新的 `preVal` 沒有被改回上一層中節點，導致錯誤
  - => 內層(左子元素)遞迴更新了 `preVal`，外層(中節點)其實不知道它變了。

- 解法：改成使用「全域變數」，讓整個遞迴共用同一份 `preVal`。

---

#### 解法一：遞迴 + 範圍區間

##### 思路：
- 為每個節點設定一個合法區間 `[min, max]`。
- 遞迴時縮小上下限範圍（左子樹最大值為父節點，右子樹最小值為父節點）。
- 若節點值不落在區間內，回傳 false。

##### 複雜度分析：
- 時間：O(n)
- 空間：O(h)，遞迴棧深度（最壞為 n）

---

#### 解法二：中序遞迴

##### 思路：
- 利用 BST 中序遍歷是遞增序列。
- 設一個 `preVal` 追蹤上一個節點值，只要有一次 `node.val <= preVal` 則非合法 BST。

##### 重點：
- `preVal` 必須是全域變數或傳參照類型（否則無法跨遞迴更新）。
- 我的誤區：
  - 一開始中序版本的 `preVal` 用參數傳遞，但 Java 傳的是 primitive value，導致無法跨層遞迴同步更新，造成錯誤。

##### 複雜度分析：
- 時間：O(n)
- 空間：O(h)

---

#### 解法三：中序迭代（Stack）

##### 思路：
- 用 stack 模擬中序遍歷順序。
- 每次處理節點時與前一個節點比較大小。

##### 複雜度分析：
- 時間：O(n)
- 空間：O(h)

---

#### 解法比較

| 解法        | 時間複雜度 | 空間複雜度 | 特性                  |
|-----------|-------|-------|---------------------|
| 解法一：遞迴上下限 | O(n)  | O(h)  | 標準做法，處理範圍清楚明確       |
| 解法二：中序遞迴  | O(n)  | O(h)  | 容易出錯（如 preVal 傳值問題） |
| 解法三：中序迭代  | O(n)  | O(h)  | 適合面試現場手動推演          |

---

#### Java 程式碼連結
- 題目實作：[ID98ValidateBinarySearchTree.java](../../archive/src/main/java/io/github/monty/leetcode/binarytree/ID98ValidateBinarySearchTree.java)
- 單元測試：[ID98ValidateBinarySearchTreeTest.java](../../archive/src/test/java/io/github/monty/leetcode/binarytree/ID98ValidateBinarySearchTreeTest.java)

---

## 相關

- [Binary Tree](../../topics/T10-21-binary-tree.md) — 遞迴帶邊界／狀態往下傳的兩種模式各練一次
- [Depth-First Search](../../topics/T10-12-depth-first-search.md) — 前序帶邊界、中序帶前值兩種 DFS 走訪順序的實戰對照
- [Binary Search Tree](../../topics/T10-11-binary-search-tree.md) — 中序遞增這個 BST 核心性質的直接應用
- [LeetCode 刷題總覽](../../_moc.md)
