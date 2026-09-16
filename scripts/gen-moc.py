# -*- coding: utf-8 -*-
"""gen-moc.py — 由題解 frontmatter 重生 _moc.md。

單一事實來源是各題解的 frontmatter；_moc.md 只是檢視，永遠可以砍掉重生。
用法：在 repo 根目錄執行 `python scripts/gen-moc.py`

設計約束：
- `order` 是排定好的順序，本腳本**只讀不寫**，絕不重新編號。
- 段落順序＝資料夾序號（01-21），段內順序＝`order` 升冪。
- 表格欄名與 frontmatter 欄位同名，方便對照。
"""
import io, os, re, sys, glob

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
SOL = os.path.join(ROOT, 'solutions')
OUT = os.path.join(ROOT, '_moc.md')

SOLVED = ('ac-unknown', 'ac-assisted', 'ac-solo', 'mastered', 'review')
PLATFORM_BASE = {
    'LeetCode': 'https://leetcode.com/problems/%s/',
    'NeetCode': 'https://neetcode.io/problems/%s',
    'KamaCoder': 'https://kamacoder.com/',
}
TITLES = {
    '01-arrays-hashing': 'Arrays & Hashing', '02-two-pointers': 'Two Pointers',
    '03-string': 'String', '04-sliding-window': 'Sliding Window',
    '05-prefix-sum': 'Prefix Sum', '06-stack': 'Stack',
    '07-monotonic-stack': 'Monotonic Stack', '08-binary-search': 'Binary Search',
    '09-linked-list': 'Linked List', '10-trees': 'Trees',
    '11-heap-priority-queue': 'Heap / Priority Queue', '12-backtracking': 'Backtracking',
    '13-tries': 'Tries', '14-graphs': 'Graphs', '15-advanced-graphs': 'Advanced Graphs',
    '16-one-d-dp': '1-D Dynamic Programming', '17-two-d-dp': '2-D Dynamic Programming',
    '18-greedy': 'Greedy', '19-intervals': 'Intervals',
    '20-bit-manipulation': 'Bit Manipulation', '21-math-geometry': 'Math & Geometry',
}


def frontmatter(path):
    fm = {}
    with io.open(path, encoding='utf-8') as fh:
        if fh.readline().strip() != '---':
            return fm
        for line in fh:
            if line.strip() == '---':
                break
            m = re.match(r'^([A-Za-z_][\w-]*):\s*(.*?)\s*$', line)
            if not m:
                continue
            k, v = m.groups()
            v = v.strip()
            if v.startswith('[') and v.endswith(']'):
                fm[k] = [x.strip().strip('"\'') for x in v[1:-1].split(',') if x.strip()]
            else:
                fm[k] = v.strip('"\'')
    return fm


rows, anomalies = {}, []
for path in glob.glob(os.path.join(SOL, '*', '*.md')):
    fm = frontmatter(path)
    cat = fm.get('category') or os.path.basename(os.path.dirname(path))
    if not fm.get('order'):
        anomalies.append('%s 缺 order' % os.path.basename(path))
    rows.setdefault(cat, []).append((fm, path))

lines = ['# LeetCode 刷題總覽', '',
         '> **本檔由 `scripts/gen-moc.py` 機器重生，請勿手改。**',
         '> 改狀態 → 改題解 frontmatter 的 `status`；改順序 → 改 `order`；改完重跑腳本。',
         '> 一題一列，不另開表格；每題住哪個資料夾由 `category` 決定。', '']

tot = sum(len(v) for v in rows.values())
done = sum(1 for v in rows.values() for fm, _ in v if fm.get('status') in SOLVED)
diff = {'Easy': 0, 'Medium': 0, 'Hard': 0}
stat = {}
for v in rows.values():
    for fm, _ in v:
        diff[fm.get('difficulty', '')] = diff.get(fm.get('difficulty', ''), 0) + 1
        s = fm.get('status', '?')
        stat[s] = stat.get(s, 0) + 1

lines += ['共 %d 題 ｜ done: %d ｜ todo: %d ｜ 難度 Easy %d / Medium %d / Hard %d'
          % (tot, done, tot - done, diff.get('Easy', 0), diff.get('Medium', 0), diff.get('Hard', 0)), '',
          '狀態分布：' + ' ｜ '.join('%s %d' % (k, stat[k]) for k in sorted(stat)), '',
          '狀態階梯：`todo` → `ac-unknown`（解過但沒記怎麼解的）／`ac-assisted`（靠提示或解答）／'
          '`ac-solo`（自己解出）→ `mastered`（重刷仍能獨立解出）；`review` = 已解但要再刷。', '', '---', '']

for cat in sorted(rows):
    items = sorted(rows[cat], key=lambda x: (int(x[0].get('order') or 9999), x[1]))
    d = sum(1 for fm, _ in items if fm.get('status') in SOLVED)
    lines += ['## %s（%d 題 · done %d · todo %d）' % (TITLES.get(cat, cat), len(items), d, len(items) - d), '',
              '| id | title | difficulty | source | status | solution |',
              '|---|---|---|---|---|---|']
    for fm, path in items:
        base = os.path.basename(path)
        num = re.match(r'lc-(\d+)-', base)
        num = num.group(1) if num else base
        url = fm.get('url', '')
        if not url:
            plat = fm.get('platform', 'LeetCode')
            slug = re.sub(r'^lc-\d+-', '', os.path.splitext(base)[0])
            tpl = PLATFORM_BASE.get(plat, PLATFORM_BASE['LeetCode'])
            url = tpl % slug if '%s' in tpl else tpl
        idcell = '[%s](%s)' % (num, url)
        src = fm.get('source', [])
        src = [src] if isinstance(src, str) else src
        rel = 'solutions/%s/%s' % (cat, base)
        sol = '—' if fm.get('status') == 'todo' else '[🔗](%s)' % rel
        title = str(fm.get('title', '')).replace('|', '\\|')
        lines.append('| %s | %s | %s | %s | %s | %s |'
                     % (idcell, title, fm.get('difficulty', '—'), ', '.join(src),
                        fm.get('status', '?'), sol))
    lines.append('')

lines += ['---', '',
          '## 相關', '',
          '- [題解格式正本](_solution-template.md) ｜ [官方提交歷史](_leetcode-submission-history.md)',
          '- 主題觀念筆記：[topics 索引](topics/_index.md)',
          '']

io.open(OUT, 'w', encoding='utf-8', newline='\n').write('\n'.join(lines))
print('已重生 %s：%d 題 / %d 段（done %d, todo %d）' % (os.path.relpath(OUT, ROOT), tot, len(rows), done, tot - done))
if anomalies:
    print('異常 %d 筆：' % len(anomalies))
    for a in anomalies[:10]:
        print('  ! ' + a)
