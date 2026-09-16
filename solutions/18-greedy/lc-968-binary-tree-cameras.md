---
title: "Binary Tree Cameras"
difficulty: Hard
topics: [Greedy, Dynamic Programming, Tree, Depth-First Search, Binary Tree]
category: 18-greedy
order: 14
source: [Carl]
platform: LeetCode
status: ac-unknown
note: ""
date_created: 2026-04-04
date_updated: 2026-04-04
---

幾個難點
 1. 貪心思維：葉子節點的上一層來放相機，可以保證最底最多的葉子不要放以減少相機數量 
2. 定義“狀態”：(一開始只想到一層放一層不要）釐清每個節點的狀態，可以發現每三層為一組；另外在節點為Null時，應該根據定義來思考應回傳２
3. 額外處理：在三層為一組時，根節點可能沒被處理，考慮case４要補一個相機

[LeetCode 題目連結](https://leetcode.com/problems/binary-tree-cameras/)
