# blind75 Linked List 領域 サマリー

各問題について「問題文サマリ」「着目すべき法則性 (Key Insight)」「解法サマリ」を整理する。
**計算量** は `時間 / 空間` の表記。`N` はリスト長。

連結リストの 3 大武器:
- **Dummy head**: 先頭が変わるケースで if 分岐を消せる
- **Two pointers (slow/fast)**: 中央検出、循環検出、N 番目検出
- **In-place 反転**: prev/curr/next の 3 変数で進める

## LinkedList テクニックパターン

### まず覚える型

| パターン | 何をしたいとき使うか | 合図 |
| --- | --- | --- |
| Dummy head | 先頭ノードを消す、差し替える、マージする | 「head 自体が変わるかも」 |
| Slow / Fast pointer | 中央、サイクル、末尾から N 番目 | 「1 回走査で位置関係を作りたい」 |
| In-place reverse | 向きをひっくり返す、後半だけ逆順にする | 「next の向きを変える」 |
| 2 本のリストをマージ | 交互結合、ソート済みマージ | 「2 系統を 1 本に編み込む」 |
| ポインタ退避 | `next` を壊す前の保全 | 「つなぎ替えがある」 |

### 1. Dummy head

- 用途: 先頭削除、先頭挿入、2 本のリストのマージ
- 発想: 本物の `head` の手前に偽ノードを置いて、先頭だけ特別扱いしない
- 定型:

```java
ListNode dummy = new ListNode(0);
dummy.next = head;
ListNode current = dummy;
```

- 典型問題: `#19 Remove Nth Node From End`, `#21 Merge Two Sorted Lists`
- ありがちなバグ:
  - `return head` してしまい、先頭変更を反映できない
  - `dummy.next` を返すべきところで `dummy` を返す

### 2. Slow / Fast pointer

- 用途:
  - 中央を見つける
  - サイクル検出
  - 末尾から N 番目を 1-pass で見つける
- 発想: 進む速度差か、開始位置の差で相対位置を作る

#### 中央を見つける型

```java
ListNode slow = head;
ListNode fast = head;

while (fast.next != null && fast.next.next != null) {
  slow = slow.next;
  fast = fast.next.next;
}
```

- ループ後の `slow` は「前半の末尾」寄り
- `Reorder List` のように分割したい問題で使いやすい

#### サイクル検出の型

```java
ListNode slow = head;
ListNode fast = head;

while (fast != null && fast.next != null) {
  slow = slow.next;
  fast = fast.next.next;
  if (slow == fast) {
    return true;
  }
}
return false;
```

#### N 個ずらす型

```java
ListNode dummy = new ListNode(0, head);
ListNode slow = dummy;
ListNode fast = dummy;

for (int i = 0; i < n + 1; i++) {
  fast = fast.next;
}

while (fast != null) {
  slow = slow.next;
  fast = fast.next;
}
```

- ループ後の `slow` は削除対象の 1 個手前
- 典型問題: `#19 Remove Nth Node From End`

### 3. In-place reverse

- 用途: リスト全体の反転、後半だけ反転、`k` 個単位反転の土台
- 発想: `curr.next` を `prev` に向け直しながら前進する
- 定型:

```java
ListNode prev = null;
ListNode curr = head;

while (curr != null) {
  ListNode next = curr.next;
  curr.next = prev;
  prev = curr;
  curr = next;
}

return prev;
```

- 重要:
  - `next` を先に退避する
  - 返すのは `curr` ではなく `prev`
- 典型問題: `#206 Reverse Linked List`, `#143 Reorder List`
- ありがちなバグ:
  - `while (curr.next != null)` にして最後のノードを処理し損ねる
  - `return curr` にして `null` を返す

### 4. 2 本のリストをマージする

- 用途:
  - ソート済み 2 本を 1 本にする
  - 前半と後半を交互に編み込む
- 発想: 今見ているノードの `next` を直接差し替える

#### ソート済みマージ

```java
ListNode dummy = new ListNode(0);
ListNode tail = dummy;

while (l1 != null && l2 != null) {
  if (l1.val < l2.val) {
    tail.next = l1;
    l1 = l1.next;
  } else {
    tail.next = l2;
    l2 = l2.next;
  }
  tail = tail.next;
}

tail.next = (l1 != null) ? l1 : l2;
return dummy.next;
```

#### 交互マージ

```java
while (second != null) {
  ListNode firstNext = first.next;
  ListNode secondNext = second.next;

  first.next = second;
  second.next = firstNext;

  first = firstNext;
  second = secondNext;
}
```

- 典型問題: `#21 Merge Two Sorted Lists`, `#143 Reorder List`
- ありがちなバグ:
  - `next` を退避する前に `next` を上書きして残りを見失う
  - 前半と後半を切り離さずにマージして循環を作る

### 5. 分割してから処理する

- 用途: `Reorder List` のように「前半」「後半」で役割が違う問題
- 発想: 1 本のリストをそのまま扱うと複雑なので、いったん 2 本に分ける
- 典型手順:
  1. `slow/fast` で中央を見つける
  2. `ListNode second = slow.next; slow.next = null;` で切る
  3. 後半だけ反転または別処理
  4. 2 本をマージ

### 6. 問題文からの見分け方

- 「末尾から N 番目」なら `2 pointers + dummy`
- 「中央」「半分に分ける」なら `slow/fast`
- 「逆順」「後ろから交互」なら `reverse`
- 「head が変わる削除・挿入」なら `dummy`
- 「2 本を 1 本にする」なら `merge`
- 「複雑に見える並び替え」なら `split -> reverse -> merge` を疑う

### 7. 連結リストで壊れやすいポイント

- `next` を上書きする前に退避していない
- 返すべき先頭が `head` なのか `dummy.next` なのか `prev` なのか曖昧
- `null` 条件が 1 個ずれている
- 分割後に `slow.next = null` しておらず、意図しないつながりが残る
- ノードの値を見ているつもりで、ノード参照そのものを比較すべき場面を取り違える

### 8. Blind 75 Linked List の対応

| 問題 | 主パターン | 補助パターン |
| --- | --- | --- |
| 141 Linked List Cycle | slow/fast | なし |
| 206 Reverse Linked List | reverse | ポインタ退避 |
| 21 Merge Two Sorted Lists | dummy + merge | なし |
| 19 Remove Nth Node From End | dummy + 2 pointers | ギャップを作る |
| 143 Reorder List | split + reverse + merge | slow/fast |

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
