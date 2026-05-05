# blind75 Heap 領域 サマリー

各問題について「問題文サマリ」「着目すべき法則性 (Key Insight)」「解法サマリ」を整理する。
**計算量** は `時間 / 空間` の表記。`N` は要素数。

Heap (Priority Queue) を使う場面の典型:
- **Top-K** (上位 k 個): 最小値を捨てる min-heap (size k)
- **動的中央値**: max-heap + min-heap の両側
- **マージ多重ストリーム**: 各ストリームの先頭を heap で管理

## 索引

| # | 問題 | 難易度 | 中心パターン |
| --- | --- | --- | --- |
| 347 | Top K Frequent Elements | Medium | Bucket Sort or Min Heap (size k) |
| 23 | Merge k Sorted Lists | Hard | Min Heap (各リストの先頭) |
| 295 | Find Median from Data Stream | Hard | max-heap + min-heap でバランス |

---

## 347. Top K Frequent Elements

### 347.1. 問題文のサマリ

整数配列 `nums` と整数 `k` が与えられたとき、出現頻度の **上位 k 個の値** を返す。

### 347.2. 着目すべき法則性

頻度カウント後の処理に 2 通り:

- **Min Heap (size k)**: 全頻度ペアを heap に push、サイズが `k` を超えたら pop。最後に heap に残った k 個が答え → O(N log k)
- **Bucket Sort**: 頻度は最大 `N` 以下 → サイズ `N+1` のバケツ配列に「頻度 = i」の値を入れる → 末尾から k 個拾う → **O(N)**

問題制約上 `k ≤ ユニーク要素数` が保証されているので、Bucket Sort が最速。

### 347.3. 解法のサマリ

- 頻度 HashMap を構築
- Bucket Sort 版: `bucket[freq]` リストに値を追加 → `bucket[N..1]` の順に拾い、k 個揃ったら終了
- Min Heap 版: PQ に `(freq, value)` を push、サイズ > k なら poll
- **計算量**: O(N) (Bucket) または O(N log k) (Heap) / O(N)

---

## 23. Merge k Sorted Lists (Hard)

### 23.1. 問題文のサマリ

ソート済み連結リスト `k` 本をマージして 1 本のソート済みリストを返す。
全ノード数を `N` とする。

### 23.2. 着目すべき法則性

**Min Heap で「各リストの最小候補 (= 先頭)」のうちの最小** を高速取得:

- 各リストの先頭を heap に入れる (k 要素)
- pop → 結果に繋ぐ → そのノードの次が存在すれば push
- heap サイズは常に **最大 k**

→ 各 push/pop が O(log k)、全要素を 1 回ずつ処理 → **O(N log k)**。
代替 (分割統治): 同じ計算量、`#21 Merge Two Sorted Lists` の流用で書きやすい。

### 23.3. 解法のサマリ

- `PriorityQueue<ListNode>` を `node.val` 比較で構築
- 各リスト head を push
- ループ: pop → tail に繋ぐ → next があれば push
- **計算量**: O(N log k) / O(k)

(Linked List CHEATSHEET と同内容)

---

## 295. Find Median from Data Stream (Hard)

### 295.1. 問題文のサマリ

ストリームに数を 1 つずつ追加 (`addNum`)、現在までの **中央値** を返す (`findMedian`) クラスを実装する。

### 295.2. 着目すべき法則性

**2 つの heap で「下半分」と「上半分」を保持**:

- `lower`: 下半分を **max-heap** で持つ (top = 下半分の最大)
- `upper`: 上半分を **min-heap** で持つ (top = 上半分の最小)
- 両 heap のサイズ差が常に **0 または 1** (`lower` の方が同等以上)

中央値:
- 偶数個 → `(lower.top + upper.top) / 2`
- 奇数個 → `lower.top`

addNum 後にバランス調整 (`lower → upper` or `upper → lower` に 1 つ移動) するだけ。

### 295.3. 解法のサマリ

- `addNum(num)`:
  1. `lower` が空 or `num <= lower.top` → `lower.push(num)`、それ以外 → `upper.push(num)`
  2. サイズ調整: `lower.size > upper.size + 1` → `upper.push(lower.poll())`、`upper.size > lower.size` → `lower.push(upper.poll())`
- `findMedian`:
  - サイズが等しい → `(lower.top + upper.top) / 2.0`
  - そうでなければ → `lower.top`
- **計算量**: addNum O(log N) / findMedian O(1) / 空間 O(N)
