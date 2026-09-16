# 🧠 LeetCode Practice with Java

A record of working through LeetCode, mostly in Java, with per-problem write-ups and topic notes.
The goal is fluency in algorithms and data structures. Every problem records the approach, the complexity, and how well I actually know it.

English | [繁體中文](README.zh-TW.md)

**[📋 Problem index](_moc.md)** ｜ [Topic notes](topics/_index.md) ｜ [Solution format](_solution-template.md) ｜ [Official AC log](_leetcode-submission-history.md)

---

## 📁 Repo layout

The tooling went through three rounds: **a Maven project** → **Notion** → **Obsidian**. The Notion batch has been exported and merged into `solutions/`; the Maven batch is archived whole under `archive/`; what is public now is the Obsidian setup.

The root holds only what is current.

```
leetcode-practice/
 ├── _moc.md                             # Problem index (generated — do not edit by hand)
 ├── _leetcode-submission-history.md     # Official LeetCode AC log
 ├── _solution-template.md               # Solution format, frontmatter spec, writing guidelines
 ├── solutions/<NN-topic>/               # One file per problem; _template.md is the skeleton
 ├── topics/                             # Topic notes; _index.md is the map
 ├── scripts/                            # Sync and statistics scripts
 └── archive/                            # 2025 material, no longer extended
     ├── pom.xml + src/                  # Maven project: Java solutions and JUnit tests
     └── doc/                            # Daily logs and early topic notes
```

---

## 🗂️ Categories and notes

**Taxonomy**: [NeetCode](https://neetcode.io/)'s topic system, plus a few categories it does not have but I needed.

**Order**: two layers — the folder number, then each problem's `order` in frontmatter. See [how the data is organised](#-how-the-data-is-organised); [`_moc.md`](_moc.md) is sorted accordingly.

**Two kinds of notes**: **topic notes** follow a problem type; **cross-cutting notes** span all of them. Full map in [`topics/_index.md`](topics/_index.md).

### Cross-cutting notes

Not tied to any problem type — useful whichever category I am on. Complexity analysis is the first thing I read before starting.

[Complexity](topics/T00-01-complexity-analysis.md) · [Sorting](topics/T00-21-sorting.md) · [Divide and conquer](topics/T00-22-divide-and-conquer.md) · [Quickselect](topics/T00-11-quickselect.md)

### Topic notes

Split by **when they are written**: a **primer** before starting a category to build the mental model, and a **summary** after finishing a pattern to consolidate the common techniques. Topic names link to that category's solutions folder.

| # | Topic | Read before | Written after |
|---|---|---|---|
| 01 | [Arrays & Hashing](solutions/01-arrays-hashing/) | — | [array](topics/T01-21-array.md) · [hash-table](topics/T01-22-hash-table.md) · [matrix](topics/T01-11-matrix.md) |
| 02 | [Two Pointers](solutions/02-two-pointers/) | — | [two-pointers](topics/T02-21-two-pointers.md) |
| 03 | [String](solutions/03-string/) | — | [string](topics/T03-21-string.md) · [string-matching](topics/T03-11-string-matching.md) |
| 04 | [Sliding Window](solutions/04-sliding-window/) | — | [sliding-window](topics/T04-21-sliding-window.md) |
| 05 | [Prefix Sum](solutions/05-prefix-sum/) | — | [prefix-sum](topics/T05-21-prefix-sum.md) |
| 06 | [Stack](solutions/06-stack/) | — | [stack-queue](topics/T06-21-stack-queue.md) |
| 07 | [Monotonic Stack](solutions/07-monotonic-stack/) | — | — |
| 08 | [Binary Search](solutions/08-binary-search/) | — | [binary-search](topics/T08-21-binary-search.md) |
| 09 | [Linked List](solutions/09-linked-list/) | — | — |
| 10 | [Trees](solutions/10-trees/) | — | [binary-tree](topics/T10-21-binary-tree.md) · [binary-search-tree](topics/T10-11-binary-search-tree.md) · [dfs](topics/T10-12-depth-first-search.md) · [bfs](topics/T10-13-breadth-first-search.md) |
| 11 | [Heap / Priority Queue](solutions/11-heap-priority-queue/) | — | [heap-priority-queue](topics/T11-21-heap-priority-queue.md) |
| 12 | [Backtracking](solutions/12-backtracking/) | — | [backtracking](topics/T12-21-backtracking.md) |
| 13 | [Tries](solutions/13-tries/) | [trie](topics/T13-01-trie-basics.md) | — |
| 14 | [Graphs](solutions/14-graphs/) | [graph theory](topics/T14-01-graph-basics.md) · [DFS](topics/T14-02-depth-first-search-basics.md) · [BFS](topics/T14-03-breadth-first-search-basics.md) | — |
| 15 | [Advanced Graphs](solutions/15-advanced-graphs/) | [topological sort](topics/T15-01-topological-sort-basics.md) | — |
| 16 | [1-D Dynamic Programming](solutions/16-one-d-dp/) | — | [dynamic-programming](topics/T16-21-dynamic-programming.md) |
| 17 | [2-D Dynamic Programming](solutions/17-two-d-dp/) | — | [dynamic-programming](topics/T16-21-dynamic-programming.md) |
| 18 | [Greedy](solutions/18-greedy/) | — | [greedy](topics/T18-21-greedy.md) |
| 19 | [Intervals](solutions/19-intervals/) | — | [intervals](topics/T19-21-intervals.md) |
| 20 | [Bit Manipulation](solutions/20-bit-manipulation/) | — | [bit-manipulation](topics/T20-21-bit-manipulation.md) |
| 21 | [Math & Geometry](solutions/21-math-geometry/) | — | [math](topics/T21-21-math.md) |

---

## 🏷️ Frontmatter

The YAML block at the top of every solution file. Status, order and category all live there; [`_moc.md`](_moc.md) is a view regenerated from them.

The field list, allowed values and filling rules are in the [solution format spec](_solution-template.md). The one field worth explaining here is `status`:

### Status ladder

It records more than "solved or not" — it records whether I could produce the solution on my own:

| Status | Meaning |
|---|---|
| `todo` | Not attempted yet |
| `ac-unknown` | Solved, but no record of how |
| `ac-assisted` | Got there with a hint, or by reading a solution |
| `ac-solo` | Solved independently |
| `mastered` | Re-solved independently on a later pass |
| `review` | Solved, but flagged for another pass |

*Note: early problems are mostly `ac-unknown`. I did not record it at the time, and I am not going to invent it after the fact.*

---

## 📐 How the data is organised

| Dimension | Lives in | How it changes |
| --- | --- | --- |
| Coarse order | Folder number `01`–`21` | By hand |
| Fine order | frontmatter `order` | By hand; scripts read it and never renumber |
| Category, status, source, platform | frontmatter | Updated when the problem is solved |
| View | `_moc.md` | Regenerated by `gen-moc.py` |

**Topic note encoding**: files in `topics/` are named `T<category 2 digits>-<sequence 2 digits>-<slug>.md`. The category number matches the `solutions/` folder, with `00` reserved for cross-cutting notes. The first digit of the sequence records when the note was written — `0X` primer (before starting), `1X` sub-topic (surfaced while solving), `2X` summary (after finishing). Sorting the folder alphabetically therefore gives the learning path.

**Source of truth, in order**:

1. Solution frontmatter is the single source of truth for status, order and category.
2. [`_leetcode-submission-history.md`](_leetcode-submission-history.md) is authoritative for official AC records; it is only ever appended to, never rewritten.
3. [`_moc.md`](_moc.md) is regenerated from (1) and is **not** a source of truth — delete it and it comes back; edit it by hand and the next run overwrites you.

**How the count works**: `system total = official LeetCode AC + problems solved elsewhere`. The latter means entries whose `platform` is not `LeetCode` — those never show up in the official record, and the field alone decides it. The `done:` figure in the `_moc.md` header is the system total.

---

## 🚀 Running things

`solutions/` and `topics/` are plain Markdown. Obsidian gives you backlinks and Base views, but any editor — or GitHub itself — reads them fine. No tooling required.

The scripts need Python 3 (`lc-stats.sh` also needs `curl`):

```bash
python scripts/gen-moc.py          # Regenerate _moc.md from solution frontmatter
python scripts/lc-sync-history.py  # Incrementally sync AC records from the official API
bash   scripts/lc-stats.sh         # Reconcile the official count against local records
```

For building and testing the 2025 Java code, see [`archive/README.md`](archive/README.md).

---

## 📚 Sources

**Problem lists**: the main line is [Grind75](https://www.techinterviewhandbook.org/grind75/), with [*Code Random Thoughts*](https://programmercarl.com/) as the follow-up, plus [LeetCode 75](https://leetcode.com/studyplan/leetcode-75/) and the occasional [weekly contest](https://leetcode.com/contest/). Which list a problem came from is recorded in its `source` field.

**Taxonomy**: [NeetCode](https://neetcode.io/)'s topic categories, with a few additions.

**Notes**: the primers (graph theory, DFS, BFS, topological sort) follow the structure of the corresponding *Code Random Thoughts* chapters; the prose is rewritten and the code ported from C++ to Java.

---

## 📝 Note

Hang in there and keep holding on!
