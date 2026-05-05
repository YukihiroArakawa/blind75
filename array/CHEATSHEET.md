# blind75 Array 領域 サマリー

各問題について「問題文サマリ」「着目すべき法則性 (Key Insight)」「解法サマリ」を整理する。
**計算量** は `時間 / 空間` の表記。`N` は配列長。

## 索引

| # | 問題 | 難易度 | 中心パターン |
| --- | --- | --- | --- |
| 1 | Two Sum | Easy | HashMap (補数検索) |
| 121 | Best Time to Buy and Sell Stock | Easy | 最小値追跡 (1-pass) |
| 217 | Contains Duplicate | Easy | HashSet |
| 238 | Product of Array Except Self | Medium | 左右累積積 (除算禁止) |
| 53 | Maximum Subarray | Medium | Kadane DP |
| 152 | Maximum Product Subarray | Medium | max/min 同時 track |
| 153 | Find Minimum in Rotated Sorted Array | Medium | 二分探索 (中央 vs 右端) |
| 33 | Search in Rotated Sorted Array | Medium | 二分探索 (どちら半分がソート済) |
| 15 | 3Sum | Medium | ソート + Two Pointers |
| 11 | Container With Most Water | Medium | Two Pointers (短い側を進める) |

---

## 1. Two Sum

### 1.1. 問題文のサマリ

整数配列 `nums` と整数 `target` が与えられたとき、足して `target` になる **2 つの index** を返す。同じ要素は使えない。答えはちょうど 1 組存在する。

### 1.2. 着目すべき法則性

「ペアを探す」を「**自分を見たときに、補数 (target - 自分) が過去にあったか**」に言い換えられる。
過去に見た値とそのインデックスを HashMap に持てば、各要素 1 回の走査で済む。

### 1.3. 解法のサマリ

- 1-pass: `nums[i]` を見るたびに `target - nums[i]` が map にあるかチェック → なければ `nums[i]` を map に追加
- **計算量**: O(N) / O(N)

---

## 121. Best Time to Buy and Sell Stock

### 121.1. 問題文のサマリ

`prices[i]` は `i` 日目の株価。1 回だけ買って 1 回売る (買 → 売の順) 場合の最大利益を返す。利益が出ない場合は 0。

### 121.2. 着目すべき法則性

「ある日 `i` に売る」と決めたなら、最大利益は `prices[i] - min(prices[0..i-1])`。
→ 左から走査しながら **「これまでの最小値」** を更新するだけで全候補を試せる。

### 121.3. 解法のサマリ

- 1-pass: `minPrice` と `maxProfit` を更新しながら走査
  - `minPrice = min(minPrice, prices[i])`
  - `maxProfit = max(maxProfit, prices[i] - minPrice)`
- **計算量**: O(N) / O(1)

---

## 217. Contains Duplicate

### 217.1. 問題文のサマリ

整数配列に重複要素があれば true、すべて一意なら false を返す。

### 217.2. 着目すべき法則性

「過去に見たか」を高速判定する典型 → HashSet。
ソートしてから隣接比較でも O(N log N) で解けるが、HashSet なら O(N)。

### 217.3. 解法のサマリ

- HashSet に追加しながら、追加前に既に含むかチェック
- **計算量**: O(N) / O(N)

---

## 238. Product of Array Except Self

### 238.1. 問題文のサマリ

整数配列 `nums` に対し、`answer[i] = nums の i 番目以外の全要素の積` となる配列を返す。
**除算は禁止**。O(N) 時間で。

### 238.2. 着目すべき法則性

`answer[i] = (i より左の積) × (i より右の積)`。
左右からそれぞれ累積積を作れば 2 パスで完成。空間 O(1) に削るには、出力配列を「左の累積積」で先に埋めて、右からのスキャンで in-place に右側の累積積を掛け込む。

### 238.3. 解法のサマリ

- 第 1 パス (左→右): `answer[i] = 左の累積積`
- 第 2 パス (右→左): 変数 `right` に右の累積積を貯めながら `answer[i] *= right; right *= nums[i]`
- **計算量**: O(N) / O(1) (出力配列を除く)

---

## 53. Maximum Subarray

### 53.1. 問題文のサマリ

整数配列 `nums` の中で、**連続部分配列** の和の最大値を返す (空配列不可)。

### 53.2. 着目すべき法則性

「位置 `i` で終わる部分配列の最大和」を `cur` とすると:

```
cur(i) = max(nums[i], cur(i-1) + nums[i])
```

= 「これまでの累積を捨てて自分から再スタート」 vs 「累積を引き継ぐ」の二択。
これが Kadane アルゴリズム。

### 53.3. 解法のサマリ

- 1-pass: `cur = max(nums[i], cur + nums[i])`、`best = max(best, cur)`
- **計算量**: O(N) / O(1)

---

## 152. Maximum Product Subarray

### 152.1. 問題文のサマリ

整数配列 `nums` の中で、連続部分配列の **積** の最大値を返す。

### 152.2. 着目すべき法則性

和と違い積は **負 × 負 = 正** で大きくなる → 「現在の最大」だけ追うと負数で台無しになる。
→ **最大と最小を同時に追う** 必要がある。負数に出会ったら max/min を swap してから更新。

### 152.3. 解法のサマリ

- 1-pass: `curMax = max(nums[i], nums[i]*curMax, nums[i]*curMin)`、`curMin` も同様
- 各ステップで `best = max(best, curMax)` を更新
- **計算量**: O(N) / O(1)

---

## 153. Find Minimum in Rotated Sorted Array

### 153.1. 問題文のサマリ

ソート済み配列をある位置で回転させた結果が `nums` (重複なし)。最小値を O(log N) で返す。

### 153.2. 着目すべき法則性

二分探索の判定軸を「中央 vs **右端**」にする:

- `nums[mid] > nums[right]` → 最小は **mid より右** にある (`left = mid + 1`)
- `nums[mid] < nums[right]` → 最小は **mid 以下** にある (`right = mid`)

中央 vs 左端だと回転していないケースで詰まる。右端比較が定石。

### 153.3. 解法のサマリ

- 二分探索: `nums[mid] > nums[right]` で探索範囲を狭める
- **計算量**: O(log N) / O(1)

---

## 33. Search in Rotated Sorted Array

### 33.1. 問題文のサマリ

回転されたソート済み配列 `nums` (重複なし) と `target` を受け取り、`target` の index を返す。なければ -1。O(log N)。

### 33.2. 着目すべき法則性

任意の `mid` に対し、**左半分 `[left, mid]` と右半分 `[mid, right]` のうち少なくとも片方は必ずソート済み** という不変性がある。
→ ソート済み側内に `target` があるか判定して、その方向を採用。

### 33.3. 解法のサマリ

- 二分探索 + 場合分け:
  - `nums[left] <= nums[mid]` → 左がソート済み → `target` が `[nums[left], nums[mid])` 内なら左へ
  - そうでなければ右がソート済み → 右へ
- **計算量**: O(log N) / O(1)

---

## 15. 3Sum

### 15.1. 問題文のサマリ

整数配列 `nums` から、**3 つの異なる index の組** で和が 0 になるものを (重複なく) 全て返す。

### 15.2. 着目すべき法則性

「3 数の和 = 0」を「1 つ固定 + 残り 2 つで補数」に分解。
ソートすれば、固定した `nums[i]` に対し残りの 2 数を **Two Pointers** で O(N) 探索可能。
ソートのおかげで重複スキップが `nums[i] == nums[i-1]` で簡単に書ける。

### 15.3. 解法のサマリ

- ソート → 各 `i` で `target = -nums[i]` の Two Sum (Two Pointers) を解く
- 重複スキップ: `i` を進めるとき + Two Pointers が一致を見つけた後、両ポインタも skip
- **計算量**: O(N²) / O(1) (ソート除く)

---

## 11. Container With Most Water

### 11.1. 問題文のサマリ

`height[i]` を縦線の高さとしたとき、2 本選んで作れる長方形 (幅 = index 差、高さ = 短い方) の最大面積を返す。

### 11.2. 着目すべき法則性

両端から Two Pointers で挟む。**短い側を内側に動かす** のが正解:

- 短い側を動かす → 高さが上がる可能性あり (面積増の余地)
- 長い側を動かす → 高さは絶対上がらず幅は縮むだけ (損確定)

→ 短い側を捨てる「貪欲」が最適性を担保する。

### 11.3. 解法のサマリ

- 両端 `left, right` から Two Pointers
- 各ステップで面積更新 → `height[left] < height[right]` なら `left++`、それ以外は `right--`
- **計算量**: O(N) / O(1)
