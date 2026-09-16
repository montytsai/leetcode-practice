# String Matching

String Matching 的核心是判斷一段模式是否出現在另一段序列中。輸入不一定原本就是字串；只要能用無歧義的方式編碼成序列，就能使用序列比對的觀點。

## 解題技巧

- 先確認是要找單一字元、固定片段，還是重複模式。
- 編碼必須無歧義：若原始資料含結構，必須保留邊界或空位置，不能只保留值。
- 不只要看比對本身的成本，也要計算產生待比對序列的時間與空間。

## 已刷題目

- [28. Find the Index of the First Occurrence in a String](../solutions/01-arrays-hashing/lc-28-find-the-index-of-the-first-occurrence-in-a-string.md) Easy — 在字串中尋找目標片段的起點。
- [459. Repeated Substring Pattern](../solutions/03-string/lc-459-repeated-substring-pattern.md) Easy — 判斷字串是否由同一片段重複組成。
- [572. Subtree of Another Tree](../solutions/10-trees/lc-572-subtree-of-another-tree.md) Easy — 目前解法不物化序列，而是配對比較候選子樹的值與結構。
- [796. Rotate String](../solutions/03-string/lc-796-rotate-string.md) Easy — 判斷目標字串是否出現在兩倍原字串中。

## 相關

- [_moc](../_moc.md)
- [String](T03-21-string.md)
