# blind75 Linked List 領域 サマリー

各問題について「問題文サマリ」「着目すべき法則性 (Key Insight)」「解法サマリ」を整理する。
**計算量** は `時間 / 空間` の表記。`N` はリスト長。

連結リストの 3 大武器:
- **Dummy head**: 先頭が変わるケースで if 分岐を消せる
- **Two pointers (slow/fast)**: 中央検出、循環検出、N 番目検出
- **In-place 反転**: prev/curr/next の 3 変数で進める

## 索引

| # | 問題 | 難易度 | 中心パターン |
| --- | --- | --- | --- |
| 206 | Reverse Linked List | Easy | prev/curr で in-place 反転 |
| 141 | Linked List Cycle | Easy | Floyd (slow/fast) |
| 21 | Merge Two Sorted Lists | Easy | Dummy head + 2 ポインタ |
| 19 | Remove Nth Node From End | Medium | 2 ポインタ (ギャップ n) |
| 143 | Reorder List | Medium | 中央 + 後半反転 + マージ |
| 23 | Merge k Sorted Lists | Hard | Min Heap or 分割統治 |

---

## 206. Reverse Linked List

### 206.1. 問題文のサマリ

単方向連結リストの先頭 `head` を受け取り、**反転したリスト** の先頭を返す。

### 206.2. 着目すべき法則性

各ノードで「次への矢印を逆向きに張り直す」操作を、**前のノードを覚えながら 1 つずつ進める** だけで反転できる。
ポイントは **`next` を破壊する前に退避** すること (さもないと辿れなくなる)。

### 206.3. 解法のサマリ

- 反復: `prev = null, curr = head` → ループで:
  1. `next = curr.next` (退避)
  2. `curr.next = prev` (反転)
  3. `prev = curr; curr = next` (進む)
- 終了後 `prev` が新 head
- 再帰版もあるが O(N) スタックを食うので反復推奨
- **計算量**: O(N) / O(1)

---

## 141. Linked List Cycle

### 141.1. 問題文のサマリ

連結リストに **循環** があるか (true/false) を返す。

### 141.2. 着目すべき法則性

**Floyd の循環検出 (slow/fast)**:
- slow を 1 歩、fast を 2 歩進める
- 循環があれば、いつか slow == fast になる (fast が slow に追いつく)
- 循環がなければ fast が null に到達して終了

直感: 周回コース上で 1 倍速と 2 倍速で走ると必ず追いつく。
HashSet で訪問判定でも O(N) で解けるが空間 O(N) かかる → Floyd は O(1)。

### 141.3. 解法のサマリ

- `slow = head, fast = head` → ループで:
  - `fast == null || fast.next == null` なら return false
  - `slow = slow.next; fast = fast.next.next`
  - `slow == fast` なら return true
- **計算量**: O(N) / O(1)

---

## 21. Merge Two Sorted Lists

### 21.1. 問題文のサマリ

ソート済み連結リスト 2 本をマージして 1 本のソート済みリストを返す。

### 21.2. 着目すべき法則性

**Dummy head** を 1 つ用意して、tail ポインタを進めながら「2 本のうち小さい方を繋ぐ」を繰り返す。
Dummy のおかげで「最初のノードをどちらにするか」の if 分岐が消える。

### 21.3. 解法のサマリ

- `dummy = new Node(0); tail = dummy`
- `while l1 && l2: 小さい方を tail.next にして tail を進める`
- 残った片方を tail.next に丸ごと繋ぐ
- `return dummy.next`
- **計算量**: O(N + M) / O(1)

---

## 19. Remove Nth Node From End of List

### 19.1. 問題文のサマリ

連結リストの末尾から数えて N 番目のノードを削除し、新しい head を返す。**1-pass** で。

### 19.2. 着目すべき法則性

**2 ポインタを n+1 だけずらして同時に進める** と、fast が null に到達した瞬間 slow は「削除対象の 1 つ手前」を指す。
→ 1-pass で削除位置を特定できる (2-pass なら長さを測ってから削除位置に進む)。

`dummy` head を使うと「先頭削除」のケースも統一して扱える。

### 19.3. 解法のサマリ

- `dummy.next = head; slow = dummy; fast = dummy`
- fast を `n + 1` 回進める
- `while fast != null: slow = slow.next; fast = fast.next`
- `slow.next = slow.next.next` で削除
- `return dummy.next`
- **計算量**: O(N) / O(1)

---

## 143. Reorder List

### 143.1. 問題文のサマリ

リスト `L0 → L1 → ... → Ln` を `L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → ...` に並び替える (in-place)。

### 143.2. 着目すべき法則性

3 段階に分解する:

1. **中央検出** (slow/fast): リストを前半と後半に分ける
2. **後半を反転** (#206 の手法)
3. **2 本をマージ** (前半と反転後半を交互に繋ぐ)

それぞれ O(N) で、合計 O(N)。

### 143.3. 解法のサマリ

- 中央: slow/fast で slow を中央に
- 後半 (`slow.next` から) を反転
- 前半と反転後半を交互に繋ぐループ
- **計算量**: O(N) / O(1)

---

## 23. Merge k Sorted Lists (Hard)

### 23.1. 問題文のサマリ

ソート済み連結リスト `k` 本をマージして 1 本のソート済みリストを返す。
全ノード数を `N` とする。

### 23.2. 着目すべき法則性

2 通りの最適解:

- **Min Heap**: 各リストの先頭ノードを heap に入れ、最小を pop して結果に繋ぐ → そのノードの次を heap に push。heap サイズが常に k → O(N log k)
- **分割統治**: ペアでマージ (#21 を使う) を再帰的に → log k 段、各段で全ノード処理 → O(N log k)

性能は同じ。Min Heap の方が直感的、分割統治は #21 の流用で書きやすい。

### 23.3. 解法のサマリ

- Min Heap 版:
  1. 各リストの head を heap に入れる (比較は `node.val`)
  2. heap から pop → 結果リストに繋ぐ → pop したノードに次があれば push
  3. heap が空になったら終了
- 分割統治版: `merge(lists[0..k]) = merge(merge(lists[0..k/2]), merge(lists[k/2..k]))`
- **計算量**: O(N log k) / O(k) (Heap) または O(log k) (再帰)
