#!/usr/bin/env bash
# lc-stats.sh — 顯示 LeetCode official、非 LeetCode 平台已解題與 system total
# 用法: ./scripts/lc-stats.sh [username]
#
# 官方 API 只認得 LeetCode 上的 AC。在別的平台（NeetCode 的付費題鏡像、KamaCoder）
# 解掉的題不進官方紀錄，由 frontmatter 的 `platform` 欄位認定。
# 只讀題解 frontmatter，不讀也不改題解正文。
# 測試可設 `LC_STATS_JSON` 注入官方 GraphQL fixture，並以 `LC_SOLUTIONS_DIR` 指定 fixture 目錄。

set -euo pipefail

USERNAME="${1:-montytsai}"
ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
export LC_ROOT="$ROOT"

if [[ -n "${LC_STATS_JSON:-}" ]]; then
  printf '%s' "$LC_STATS_JSON"
else
  curl -s -X POST 'https://leetcode.com/graphql' \
  -H 'Content-Type: application/json' \
  -H 'Referer: https://leetcode.com' \
  --data-raw "{\"query\": \"{ matchedUser(username: \\\"${USERNAME}\\\") { submitStats: submitStatsGlobal { acSubmissionNum { difficulty count } } } }\"}"
fi \
| python3 -c "
import json, os, re, sys
from pathlib import Path

data = json.load(sys.stdin)
stats = data['data']['matchedUser']['submitStats']['acSubmissionNum']
total = next(s['count'] for s in stats if s['difficulty'] == 'All')
easy  = next(s['count'] for s in stats if s['difficulty'] == 'Easy')
med   = next(s['count'] for s in stats if s['difficulty'] == 'Medium')
hard  = next(s['count'] for s in stats if s['difficulty'] == 'Hard')

solutions = Path(os.environ.get('LC_SOLUTIONS_DIR', Path(os.environ['LC_ROOT']) / 'solutions'))

def frontmatter(path):
    values, active_list = {}, None
    with path.open(encoding='utf-8') as handle:
        if handle.readline().strip() != '---':
            return values
        for line in handle:
            stripped = line.strip()
            if stripped == '---':
                break
            if active_list and stripped.startswith('- '):
                values[active_list].append(stripped[2:].strip().strip(chr(34) + chr(39)))
                continue
            active_list = None
            match = re.match(r'^([A-Za-z_][A-Za-z0-9_-]*):\s*(.*?)\s*\$', line)
            if not match:
                continue
            key, value = match.groups()
            if value == '':
                values[key], active_list = [], key
            elif value.startswith('[') and value.endswith(']'):
                values[key] = [item.strip().strip(chr(34) + chr(39)) for item in value[1:-1].split(',') if item.strip()]
            else:
                values[key] = value.strip().strip(chr(34) + chr(39))
    return values

# 題解分散在 solutions/<NN-category>/ 底下，要往下走一層
paths = sorted(solutions.glob('*/lc-*.md')) + sorted(solutions.glob('lc-*.md'))
solved_elsewhere, by_platform = [], {}
counted = 0
for path in paths:
    meta = frontmatter(path)
    status = meta.get('status', '')
    if status in ('', 'todo'):
        continue
    counted += 1
    platform = meta.get('platform', 'LeetCode')
    if platform != 'LeetCode':
        solved_elsewhere.append(path.stem)
        by_platform[platform] = by_platform.get(platform, 0) + 1

print(f'official {total} E{easy}/M{med}/H{hard}')
if solved_elsewhere:
    detail = ', '.join('%s %d' % (k, v) for k, v in sorted(by_platform.items()))
    print('non-LeetCode solved {} ({}): {}'.format(len(solved_elsewhere), detail, ', '.join(solved_elsewhere)))
else:
    print('non-LeetCode solved 0')
print(f'system total {total + len(solved_elsewhere)}')
print(f'records solved {counted}')
"
