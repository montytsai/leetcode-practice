---
title: "Implement Trie (Prefix Tree)"
difficulty: Medium
topics: [Trie]
category: 13-tries
order: 1
source: [Grind75]
platform: LeetCode
url: https://leetcode.com/problems/implement-trie-prefix-tree/
status: ac-assisted
note: "看詳解寫的"
date_created: 2026-11-09
date_updated: 2026-11-09
---

# 208. Implement Trie (Prefix Tree)

## 題目說明

- 實作插入單字、查詢完整單字與查詢前綴三種操作。
- 輸入由小寫英文字母組成；完整單字存在與前綴存在需要分開判斷。

## 心得

看詳解寫的

## 解法一：HashMap 儲存子節點

### Intuition

Trie 用共同路徑共用單字的前綴。每個節點代表從根走到此處得到的前綴；這個前綴由路徑決定，不需要存成節點的字串欄位。

每個節點有自己的 `children`。Map 的 key 是下一條邊的字元，value 是走過這條邊會到達的節點。`Trie` 同時作為整棵樹的入口與各節點的型別，每次 `new Trie()` 都建立一份獨立的 Map 與結尾旗標。

路徑存在只能證明前綴存在。`isLast` 記錄這個前綴是否曾被當成完整單字插入，讓 `search` 能區分 `app` 與只有 `apple` 的情況。

### Approach

1. 三種操作都令 `curr` 指向根，依字元往下走。處理前 k 個字元後，`curr` 恰好代表長度 k 的前綴。
2. 插入遇到缺少的邊就建立節點，走完後將終點標為 `isLast = true`。
3. 查詢遇到缺少的邊立即回傳 false；走完完整單字後回傳 `isLast`。
4. 前綴查詢只要求走完整條路徑，走完即回傳 true。

### Complexity

- **Time complexity: O(L)**：L 是本次輸入長度。每個字元做固定次數的 Map 操作；HashMap 採預期 O(1) 存取，本題每個節點的孩子數上限為固定的 26。`toCharArray()` 同樣花 O(L)。
- **Space complexity: O(P)**：P 是所有單字的不同前綴數，包含根；每條邊只存一次。所有單字總長為 S 時，最壞 O(S)。
- **單次額外空間 O(L)**：三個方法都建立字元陣列；插入最壞另建 O(L) 個節點。改用 `charAt` 可讓查詢額外空間降至 O(1)。

### Code

```java
/**
 * 208. Implement Trie (Prefix Tree)
 * Time Complexity: O(L) per operation
 * Space Complexity: O(P) for the trie, O(L) extra per operation
 */
class Trie {
    private Map<Character, Trie> children;
    private Boolean isLast;

    public Trie() {
        children = new HashMap<>();
        isLast = false;
    }

    public void insert(String word) {
        Trie curr = this;
        for (char c : word.toCharArray()) {
            if (!curr.children.containsKey(c)) {
                curr.children.put(c, new Trie());
            }
            curr = curr.children.get(c);
        }
        curr.isLast = true;
    }

    public boolean search(String word) {
        Trie curr = this;
        for (char c : word.toCharArray()) {
            if (!curr.children.containsKey(c)) {
                return false;
            }
            curr = curr.children.get(c);
        }
        return curr.isLast;
    }

    public boolean startsWith(String prefix) {
        Trie curr = this;
        for (char c : prefix.toCharArray()) {
            if (!curr.children.containsKey(c)) {
                return false;
            }
            curr = curr.children.get(c);
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
```

### Code Review

- **Learning provenance**：reference-assisted，參考網路攻略完成 AC；獨立重現未確認，標為 `ac-assisted`。
- **Correctness / invariant**：`curr` 始終對應已讀前綴，結尾旗標區分完整單字與前綴。題目條件下沒有發現 bug。
- **Strength**：查詢只讀不建節點；插入共用既有前綴，重複插入只把同一旗標設為 true。
- **Trade-off**：`containsKey` 加 `get` 對同一字元查兩次，仍是相同漸近時間；`toCharArray()` 每次配置 O(L) 陣列。
- **Style**：`isLast` 只需 true／false，可改 primitive `boolean`。目前初始化使它不會是 null，現有寫法沒有拆箱錯誤。
- **Edge cases**：只插入 `apple` 時，`search("app")` 為 false、`startsWith("app")` 為 true；之後插入 `app` 只改終點旗標；重複插入同一單字仍正確。

### Optimality

以最壞查詢時間衡量，O(L) 已達一般字元存取模型的漸近最佳：完全匹配或最後一字元才不同時，需要確認 L 個字元。插入最壞也需要建立 L 個不同前綴。查詢暫存空間則可用 `charAt` 從 O(L) 改善成 O(1)。

唯一替代是 `Trie[26]`：時間仍是 O(L)，省去 HashMap 存取的常數成本；每個節點固定配置 26 格，稀疏時留下許多 null。字元集固定且重視存取常數時可選陣列，Map 則直接表達實際存在的分支。

## 相關

- [Trie 基礎](../../topics/T13-01-trie-basics.md) — 節點、邊與各自獨立的 children Map。
- [LeetCode 刷題總覽](../../_moc.md)
