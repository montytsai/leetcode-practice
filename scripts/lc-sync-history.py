#!/usr/bin/env python3
"""lc-sync-history.py — 從 LeetCode 官方 GraphQL 增量同步 AC 紀錄到 _leetcode-submission-history.md

用法: python3 scripts/lc-sync-history.py [username] [--dry-run]

只做「新增」:已在表中的列一律不動。

公開 API 的三個限制:
  1. 只回傳最近 20 筆 AC——刷超過 20 題沒同步就會漏,至少每 20 題跑一次
  2. 只有 AC,拿不到 WA/TLE 紀錄
  3. 拿不到「提交次數」,新增的列該欄填 `-`
需要完整歷史或提交次數時,登入官網 Practice History 手動補。
"""
import json, os, re, sys, urllib.request
from datetime import datetime, timezone

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
HIST = os.path.join(ROOT, "_leetcode-submission-history.md")
USER = next((a for a in sys.argv[1:] if not a.startswith("-")), "montytsai")
DRY = "--dry-run" in sys.argv


def gql(query, variables):
    req = urllib.request.Request(
        "https://leetcode.com/graphql",
        data=json.dumps({"query": query, "variables": variables}).encode(),
        headers={"Content-Type": "application/json", "Referer": "https://leetcode.com",
                 "User-Agent": "Mozilla/5.0"})
    return json.load(urllib.request.urlopen(req, timeout=30))["data"]


# 1. 最近 20 筆 AC
recent = gql("query r($u:String!,$l:Int){recentAcSubmissionList(username:$u,limit:$l){title titleSlug timestamp}}",
             {"u": USER, "l": 20}) or {}
recent = recent.get("recentAcSubmissionList") or []
if not recent:
    print("查不到提交紀錄(帳號名稱錯誤或官網改版?),沒有變更")
    sys.exit(1)

# 2. 官方題庫:slug → (題號, 標題, 難度)
lv = {1: "Easy", 2: "Med.", 3: "Hard"}
allp = json.load(urllib.request.urlopen(urllib.request.Request(
    "https://leetcode.com/api/problems/all/", headers={"User-Agent": "Mozilla/5.0"}), timeout=30))
meta = {q["stat"]["question__title_slug"]:
        (str(q["stat"]["frontend_question_id"]), q["stat"]["question__title"], lv[q["difficulty"]["level"]])
        for q in allp["stat_status_pairs"]}

# 3. 現有表格
text = open(HIST, encoding="utf-8").read()
rows, have = [], set()
for line in text.split("\n"):
    m = re.match(r"^\|\s*(\d{4}-\d{2}-\d{2})\s*\|\s*(\d+)\.\s*([^|]+?)\s*\|\s*([^|]+?)\s*\|\s*([^|]+?)\s*\|\s*([^|]+?)\s*\|", line)
    if m:
        rows.append(list(m.groups()))
        have.add((m.group(1), m.group(2)))

# 4. 併入新的
added = []
for s in recent:
    d = datetime.fromtimestamp(int(s["timestamp"]), timezone.utc).astimezone().strftime("%Y-%m-%d")
    if s["titleSlug"] not in meta:
        continue
    num, title, diff = meta[s["titleSlug"]]
    if (d, num) in have:
        continue
    rows.append([d, num, title, diff, "Accepted", "-"])
    added.append(f"{d} {num}. {title}")

if not added:
    print("已是最新,無新增(最近一筆 AC:%s)" % datetime.fromtimestamp(
        int(recent[0]["timestamp"]), timezone.utc).astimezone().strftime("%Y-%m-%d"))
    sys.exit(0)

rows.sort(key=lambda r: (r[0], int(r[1])), reverse=True)
table = ["| 日期 | 題目 | 難度 | 結果 | 提交次數 |", "|---|---|---|---|---|"]
table += [f"| {r[0]} | {r[1]}. {r[2]} | {r[3]} | {r[4]} | {r[5]} |" for r in rows]

head, tail = text.split("| 日期 | 題目", 1)
tail = tail.split("\n\n", 1)[1] if "\n\n" in tail else ""
head = re.sub(r"涵蓋 [\d-]+ ~ [\d-]+,共 \d+ 筆", f"涵蓋 {rows[-1][0]} ~ {rows[0][0]},共 {len(rows)} 筆", head)
out = head + "\n".join(table) + "\n\n" + tail

print("新增 %d 筆:" % len(added))
for a in added:
    print("  +", a)
if DRY:
    print("(--dry-run,未寫檔)")
else:
    open(HIST, "w", encoding="utf-8", newline="\n").write(out)
    print("已寫入 %s" % os.path.relpath(HIST, ROOT))
print("提醒:新增列的「提交次數」為 `-`(公開 API 不提供);需要精確次數請到官網 Practice History 補。")
