---
title: "Meeting Rooms"
difficulty: Easy
topics: [Intervals, Array, Sorting]
category: 19-intervals
order: 4
source: [Grind75]
platform: NeetCode
url: https://neetcode.io/problems/meeting-schedule/question
status: ac-unknown
note: ""
date_created: 2026-08-02
date_updated: 2026-08-02
---

## 心得

先依 `start` 排序後，所有可能衝突的會議都會相鄰；只要目前會議的 `start < prevEnd` 就代表重疊，可以直接回傳 `false`。若 `start == prevEnd`，前一場剛好結束，不算衝突。

## Java

```java
/**
 * Definition of Interval:
 * public class Interval {
 *     public int start, end;
 *     public Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */
class Solution {
    public boolean canAttendMeetings(List<Interval> intervals) {
        if (intervals == null || intervals.isEmpty()) {
            return true;
        }

        // KEY! After sorting, we only need to compare adjacent meetings.
        intervals.sort((a, b) -> Integer.compare(a.start, b.start));

        int prevEnd = intervals.get(0).end;

        for (int i = 1; i < intervals.size(); i++) {
            Interval curr = intervals.get(i);
            if (curr.start < prevEnd) {
                return false;
            }
            prevEnd = curr.end;
        }

        return true;
    }
}
```

Time is O(n log n). Space is O(n) in the worst case.
