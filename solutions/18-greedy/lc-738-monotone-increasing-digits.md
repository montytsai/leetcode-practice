---
title: "Monotone Increasing Digits"
difficulty: Medium
topics: [Greedy, Math]
category: 18-greedy
order: 13
source: [Carl]
platform: LeetCode
status: ac-unknown
note: ""
date_created: 2026-04-03
date_updated: 2026-04-03
---

使用flag標記開始轉換９的地方，由右往左“動態”轉換最高位數。轉換完再處理補９的位置（每個for迴圈只做一件事！！！一開始就想到由右往左及補９，但是太貪心要在同一個for迴圈直接轉換位元。）

[LeetCode 題目連結](https://leetcode.com/problems/monotone-increasing-digits/solutions/109794/simple-python-solution-w-explanation-by-zadha/)
