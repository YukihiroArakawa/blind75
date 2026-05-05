# blind75 Interval 領域 サマリー

各問題について「問題文サマリ」「着目すべき法則性 (Key Insight)」「解法サマリ」を整理する。
**計算量** は `時間 / 空間` の表記。`N` は区間の個数。

区間問題の鉄則:
- **まず start (or end) でソート** すれば、隣接区間の関係だけで全体を判定できる
- 「重なり判定」は `a.end >= b.start` が定型 (端点を含む/含まないは問題ごとに微調整)

## 索引

| # | 問題 | 難易度 | 中心パターン |
| --- | --- | --- | --- |
| 252 | Meeting Rooms | Easy | ソート + 隣接重なり判定 |
| 57 | Insert Interval | Medium | 3 フェーズ (前/重なり/後) |
| 56 | Merge Intervals | Medium | ソート + 直前と重なれば end を max |
| 435 | Non-overlapping Intervals | Medium | ソート (end 昇順) + Greedy |
| 253 | Meeting Rooms II | Medium | ソート + Min Heap (or Sweep Line) |

---

## 252. Meeting Rooms

### 252.1. 問題文のサマリ

会議の時間区間 `intervals[i] = [start, end]` が与えられたとき、**全ての会議に出席可能か** (= 重なりがないか) を判定。

### 252.2. 着目すべき法則性

**start 昇順にソート** すれば、重なりは「直前の end > 次の start」の局所判定で全部見つかる (互いに離れた 2 区間が重なるためには、間にある区間も重ならないといけないため)。

### 252.3. 解法のサマリ

- start でソート → `for i = 1..n-1: if intervals[i].start < intervals[i-1].end: return false`
- **計算量**: O(N log N) / O(1)

---

## 57. Insert Interval

### 57.1. 問題文のサマリ

start 昇順かつ非重複の区間配列 `intervals` と、新区間 `newInterval` が与えられたとき、追加 + 必要なら重なりをマージした結果を返す。

### 57.2. 着目すべき法則性

ソート済みなので、線形に走査して **3 フェーズ** に分けると綺麗:

1. **`newInterval` より完全に前にある区間** → そのまま結果に追加
2. **`newInterval` と重なる区間** → 全部マージ (start = min, end = max) して `newInterval` を更新
3. **`newInterval` より完全に後にある区間** → `newInterval` を結果に追加 → 残りをそのまま結果に追加

ソート済みであるおかげで二分探索を使わずとも 1-pass で済む。

### 57.3. 解法のサマリ

- 3 つの while ループ (or 1 つの if-else 連鎖) でフェーズ分け
- **計算量**: O(N) / O(N) (出力)

---

## 56. Merge Intervals

### 56.1. 問題文のサマリ

区間配列 `intervals` を、重なるものを全てマージした結果に変換して返す。

### 56.2. 着目すべき法則性

start 昇順にソートすると、**「直前の区間と重なるか」だけで全マージが完了** する (3 つ以上の連鎖マージも自動で処理される)。

### 56.3. 解法のサマリ

- start でソート
- 結果配列に最初の区間を入れる
- `for i = 1..n-1`:
  - `intervals[i].start <= last.end` (重なり) → `last.end = max(last.end, intervals[i].end)`
  - そうでなければ → 新しい区間として追加
- **計算量**: O(N log N) / O(N)

---

## 435. Non-overlapping Intervals

### 435.1. 問題文のサマリ

区間配列から、**最小限の削除** で残りを互いに非重複にしたい。削除すべき区間の最小数を返す。

### 435.2. 着目すべき法則性

「残せる最大数」を求めれば `削除数 = N - 残せる数`。
**end 昇順にソート + Greedy**: 「最も早く終わる区間を残す」と次の区間に多くの余地が残る → 最適。

→ Activity Selection Problem の典型。

### 435.3. 解法のサマリ

- end でソート
- `prevEnd = -∞`、`count = 0` (残せる数)
- `for i in intervals: if i.start >= prevEnd: count++; prevEnd = i.end`
- 答え = `N - count`
- **計算量**: O(N log N) / O(1)

---

## 253. Meeting Rooms II

### 253.1. 問題文のサマリ

会議区間 `intervals` が与えられたとき、**同時に必要な会議室の最小数** を返す。

### 253.2. 着目すべき法則性

**「現在使われている部屋」 = 「end が今の start より大きい区間の数」**。
これを効率的に追うには 2 通り:

- **Min Heap**: 部屋ごとに「終了時刻」を heap で管理。新しい会議の start が heap の top (最も早く終わる部屋の end) より遅ければその部屋を再利用 (pop)、そうでなければ部屋を追加 (push)。最終的な heap サイズが答え
- **Sweep Line**: start を +1, end を -1 のイベント化 → 時刻順にソート → 累計の最大値

### 253.3. 解法のサマリ

- Min Heap 版:
  1. start でソート
  2. Min Heap (end を持つ) を空で開始
  3. 各 interval について、`heap.top <= interval.start` なら pop (部屋再利用)、`heap.push(interval.end)` (使用中部屋追加)
  4. 最後の `heap.size()` が答え
- Sweep Line 版: イベント `(start, +1), (end, -1)` を時刻昇順 (同時刻なら -1 を先) にソートして累計 max
- **計算量**: O(N log N) / O(N)
