---
title: "Meeting Rooms II"
difficulty: Medium
topics: [Intervals, Array, Two Pointers, Greedy, Sorting, Heap (Priority Queue), Prefix Sum]
category: 19-intervals
order: 5
source: [Grind75]
platform: NeetCode
url: https://neetcode.io/problems/meeting-schedule-ii/question
status: ac-assisted
note: ""
date_created: 2026-08-02
date_updated: 2026-08-02
---

## 心得

我一開始以為需要找「離 `curr.start` 最近且不衝突」的結束時間，甚至想把房間的 `end` 當成 Map key，導致資料格式越想越複雜；在 AI 提示下才改用 Min-Heap，直接取得最早結束時間。

我原本擔心：若把 `(8,9)` 排進最早結束的 A 房（end=5），之後可能出現 `(6,7)`，造成原本可用的空檔被浪費。真正的突破是會議已按 `start` 排序，處理完 `start=8` 後，不可能再遇到 `start=6`，所以不需要回頭補空檔，也不需要尋找最接近的結束時間。

Min-Heap 的 `peek()` 不是在找結束時間最接近新會議的房間，而是在判斷是否至少有一間房可以復用。若最早結束的房間仍與目前會議重疊，其他房間也一定重疊；否則直接復用最早結束的房間即可。

## Java

```java
class Solution {
    public int minMeetingRooms(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) {
            return 0;
        }

        intervals.sort((a, b) -> Integer.compare(a.start, b.start));

        PriorityQueue<Integer> roomEnds = new PriorityQueue<>();

        for (Interval curr : intervals) {
            if (roomEnds.isEmpty()) {
                roomEnds.offer(curr.end);
                continue;
            }

            // KEY! Meetings are sorted by start time, so we never need to fill an earlier gap.
            if (curr.start >= roomEnds.peek()) {
                roomEnds.poll();
            }

            roomEnds.offer(curr.end);
        }

        return roomEnds.size();
    }
}
```

Time is O(n log n). Space is O(n) in the worst case.
