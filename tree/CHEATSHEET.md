# blind75 Tree 領域 サマリー

各問題について「問題文サマリ」「着目すべき法則性 (Key Insight)」「解法サマリ」を整理する。
**計算量** は `時間 / 空間` の表記。`N` はノード数、`H` は木の高さ、`L` は文字列長など問題ごとに定義。

## Tree テクニックパターン

### まず覚える型

| パターン | 何をしたいとき使うか | 合図 |
| --- | --- | --- |
| DFS 再帰 | 部分木の答えを集約したい | 「左右部分木の結果から決まる」 |
| BFS / Queue | レベル順に見たい | 「各深さごとに処理したい」 |
| BST の性質利用 | 値比較で枝を絞れる | 「左 < 根 < 右」 |
| In-order traversal | BST の昇順性を使いたい | 「k 番目」「単調増加判定」 |
| Tree DP | 子の情報を親に返す | 「最大和」「片側だけ返す」 |
| Trie | prefix 共有、文字列集合 | 「prefix 検索」「辞書」 |

### 1. DFS 再帰

- 用途: 深さ計算、木の比較、反転、部分木判定
- 発想: 木は「ノード + 左部分木 + 右部分木」なので、部分木に同じ処理を委譲しやすい
- 定型:

```java
private int dfs(TreeNode node) {
  if (node == null) {
    return 0;
  }

  int left = dfs(node.left);
  int right = dfs(node.right);
  return Math.max(left, right) + 1;
}
```

- 典型問題: `#104`, `#100`, `#226`, `#572`
- ありがちなバグ:
  - `null` のベースケースが曖昧
  - 「戻り値で返す情報」と「副作用で更新する情報」が混ざる

### 2. BFS / Queue

- 用途: レベル順走査、最短手数、深さごとの集計
- 発想: Queue を使うと浅いノードから順に処理できる
- レベル単位の定型:

```java
Queue<TreeNode> queue = new LinkedList<>();
queue.offer(root);

while (!queue.isEmpty()) {
  int levelSize = queue.size();
  List<Integer> level = new ArrayList<>();

  for (int i = 0; i < levelSize; i++) {
    TreeNode node = queue.poll();
    level.add(node.val);

    if (node.left != null) queue.offer(node.left);
    if (node.right != null) queue.offer(node.right);
  }
}
```

- `queue.size()` を先に取ると「そのレベルだけ」をきれいに切り出せる
- 典型問題: `#102`

### 3. BST の性質を使う

- 性質: 任意のノードで `左部分木の全値 < node.val < 右部分木の全値`
- 用途:
  - 値比較だけで進行方向を決める
  - 範囲制約で BST 妥当性を判定する

#### 値比較で枝を絞る型

```java
while (root != null) {
  if (p.val < root.val && q.val < root.val) {
    root = root.left;
  } else if (p.val > root.val && q.val > root.val) {
    root = root.right;
  } else {
    return root;
  }
}
```

- 典型問題: `#235 Lowest Common Ancestor of a BST`

#### 範囲伝播で妥当性判定する型

```java
private boolean validate(TreeNode node, long lower, long upper) {
  if (node == null) {
    return true;
  }
  if (node.val <= lower || node.val >= upper) {
    return false;
  }
  return validate(node.left, lower, node.val)
      && validate(node.right, node.val, upper);
}
```

- 典型問題: `#98 Validate Binary Search Tree`
- ありがちなバグ:
  - 親子比較だけで済ませてしまう
  - `int` 境界で overflow するので `long` を使わない

### 4. In-order traversal

- 性質: BST を in-order で走査すると昇順になる
- 用途:
  - BST 判定
  - `k` 番目に小さい値
- 反復定型:

```java
Stack<TreeNode> stack = new Stack<>();
TreeNode current = root;

while (current != null || !stack.isEmpty()) {
  while (current != null) {
    stack.push(current);
    current = current.left;
  }

  current = stack.pop();
  current = current.right;
}
```

- 典型問題: `#230`, `#98`

### 5. Tree DP

- 用途: 最大パス和のように、各ノードで「子の情報をまとめて親に返す」問題
- 発想:
  - 親に返す値
  - そのノードを頂点にした局所最適
  を分けて考える
- 典型問題: `#124 Binary Tree Maximum Path Sum`
- よくある型:
  - 戻り値は「片側だけ伸ばせる最適値」
  - グローバル変数は「そのノードを通過する最適値」で更新

### 6. Trie

- 用途: prefix 検索、単語辞書、ワイルドカード検索
- 発想: 各文字を 1 辺として、共通 prefix を共有する木構造にする
- 定型構造:

```java
class TrieNode {
  Map<Character, TrieNode> children = new HashMap<>();
  boolean isEnd;
}
```

- 典型問題: `#208`, `#211`, `#212`
- ありがちなバグ:
  - `isEnd` を付け忘れて `app` と `apple` を区別できない
  - `'.'` を通常文字と同じ処理にしてしまう

### 7. 木の再構築

- 用途: 走査順配列から元の木を戻す
- 発想:
  - preorder の先頭は root
  - inorder では root を境に左部分木と右部分木に分かれる
- 典型問題: `#105 Construct Binary Tree from Preorder and Inorder Traversal`
- 実装の要点:
  - `inorder` の index を HashMap 化して O(1) 参照
  - `preorder` の進行位置を再帰間で共有

### 8. serialize / deserialize

- 用途: 木構造を文字列に落として復元したい
- 発想: 値だけでなく `null` も明示しないと一般二分木は復元できない
- 定型:
  - serialize: preorder + `null` マーカー
  - deserialize: token を前から消費しながら左右を再帰生成
- 典型問題: `#297`

### 9. 問題文からの見分け方

- 「深さ」「合計」「左右部分木から決まる」なら `DFS`
- 「レベル順」「各段ごと」なら `BFS`
- 「BST」と書いてあるなら値比較や in-order を疑う
- 「k 番目」「昇順」なら `in-order`
- 「最大パス」「子の情報を親に返す」なら `Tree DP`
- 「prefix」「辞書」「ワイルドカード」なら `Trie`
- 「走査順から元の木を作る」なら `再構築`

### 10. Tree で壊れやすいポイント

- `null` ベースケースの戻り値がずれている
- 「戻り値」と「グローバル更新」を混同している
- BST を親子比較だけで判定してしまう
- BFS で `queue.size()` を毎回動的に見て、同レベル境界が壊れる
- 再構築問題で配列範囲の定義がずれる

### 11. Blind 75 Tree の対応

| 問題 | 主パターン | 補助パターン |
| --- | --- | --- |
| 104 Maximum Depth of Binary Tree | DFS | なし |
| 100 Same Tree | DFS 同時走査 | なし |
| 226 Invert Binary Tree | DFS | なし |
| 572 Subtree of Another Tree | DFS | Same Tree を再利用 |
| 102 Binary Tree Level Order Traversal | BFS | レベル境界管理 |
| 105 Construct Binary Tree from Preorder and Inorder Traversal | 再構築 | HashMap |
| 98 Validate Binary Search Tree | BST 範囲伝播 | in-order |
| 230 Kth Smallest Element in a BST | in-order | BST |
| 235 Lowest Common Ancestor of a BST | BST 値比較 | 反復 DFS 的走査 |
| 208 Implement Trie | Trie | なし |
| 211 Add and Search Words | Trie | DFS |

## 索引

| # | 問題 | 難易度 | 中心パターン |
| --- | --- | --- | --- |
| 104 | Maximum Depth of Binary Tree | Easy | DFS 再帰 (基本) |
| 100 | Same Tree | Easy | DFS 同時走査 |
| 226 | Invert Binary Tree | Easy | DFS 再帰 (副作用) |
| 572 | Subtree of Another Tree | Easy | DFS + Same Tree 判定 |
| 102 | Binary Tree Level Order Traversal | Medium | BFS (レベル境界) |
| 105 | Construct Binary Tree from Preorder and Inorder | Medium | 分割再構築 (preorder + inorder) |
| 98 | Validate Binary Search Tree | Medium | 範囲伝播 DFS / In-order |
| 230 | Kth Smallest Element in a BST | Medium | In-order Traversal |
| 235 | Lowest Common Ancestor of a BST | Medium | BST 値比較で分岐判定 |
| 208 | Implement Trie | Medium | Trie データ構造 |
| 211 | Add and Search Words | Medium | Trie + DFS (ワイルドカード) |
| 124 | Binary Tree Maximum Path Sum | Hard | Tree DP (片側ゲイン vs 通過和) |
| 297 | Serialize and Deserialize Binary Tree | Hard | Preorder + null マーカー |
| 212 | Word Search II | Hard | Trie + Backtracking DFS |

---

## 104. Maximum Depth of Binary Tree

### 104.1. 問題文のサマリ

二分木の `root` が与えられたとき、ルートから最も遠い葉までの **最大深度** (ノード数) を返す。空の木は深度 0。

### 104.2. 着目すべき法則性

ツリーの再帰的定義そのものが答えになる。

```
depth(node) = 0                                      (node == null)
depth(node) = 1 + max(depth(left), depth(right))     (otherwise)
```

「自分の深度 = 子の深度の最大 + 1」という構造的同型関係に気付けば、即座に再帰式が立つ。

### 104.3. 解法のサマリ

- **DFS 再帰**: 上の式をそのままコードに落とす
- 代替: BFS でレベルを 1 ずつカウントしてもよい
- **計算量**: O(N) / O(H)

---

## 100. Same Tree

### 100.1. 問題文のサマリ

2 つの二分木 `p`, `q` が与えられたとき、構造的にも値的にも同一かを判定する。

### 100.2. 着目すべき法則性

「同じ」という関係も再帰的に分解できる:

```
same(p, q) = true                          (p, q ともに null)
same(p, q) = false                         (片方だけ null)
same(p, q) = false                         (値が違う)
same(p, q) = same(p.left, q.left) AND same(p.right, q.right)
```

ベースケース (null 処理) を 3 段階に切り分けるのがポイント。

### 100.3. 解法のサマリ

- **DFS 同時走査**: 両ツリーを並行に降りつつ、null 処理 → 値比較 → 左右の再帰
- **計算量**: O(N) / O(H)

---

## 226. Invert Binary Tree

### 226.1. 問題文のサマリ

二分木を **左右反転** (鏡像化) して返す。各ノードで左右の子を入れ替える操作を全ノードに対して行う。

### 226.2. 着目すべき法則性

「全ノードで子を swap」する順序は何でもよい (前順・後順・どちらでも結果が同じ) という不変性がある。
反転を「自分でやる」ではなく「子に reverse を要求してから自分で swap」と捉えても、「自分で swap してから子に reverse を要求」と捉えても等価。

### 226.3. 解法のサマリ

- **DFS 再帰**: 各ノードで `node.left, node.right = invert(right), invert(left)`
- BFS でも同じ (キューで巡回しながら swap)
- **計算量**: O(N) / O(H)

---

## 572. Subtree of Another Tree

### 572.1. 問題文のサマリ

`root` の中に `subRoot` と完全一致する部分木が存在するかを判定する。

### 572.2. 着目すべき法則性

「subRoot と一致する部分木がある」を再帰的に分解:

```
isSub(root, subRoot) = sameTree(root, subRoot)
                       OR isSub(root.left, subRoot)
                       OR isSub(root.right, subRoot)
```

`#100 Same Tree` をサブルーチンとして使い回す。**部分木とは「ある葉まで含めて完全に一致」** であって、途中で切れた一致は不可、という定義に注意。

### 572.3. 解法のサマリ

- **DFS で各ノードを起点に Same Tree 判定**
- 高速化: ツリーを文字列化 (preorder + null マーカー) して KMP で部分一致検索 → O(N+M)
- **計算量**: 素朴 O(N×M) / O(H)、KMP 版 O(N+M) / O(N+M)

---

## 102. Binary Tree Level Order Traversal

### 102.1. 問題文のサマリ

二分木をレベル順 (上から順、各レベル内は左→右) に走査し、`List<List<Integer>>` で返す。

### 102.2. 着目すべき法則性

BFS は自然にレベル順だが、出力では **「同じレベルのノードをグルーピング」** が必要。
ループ開始時の `queue.size()` がそのレベルのノード数に等しいことを使えば境界を明示できる。

### 102.3. 解法のサマリ

- **BFS + Queue**: 外ループで各レベル開始時に `int levelSize = queue.size();` を退避し、内ループで `levelSize` 回だけ poll しつつ次レベルを enqueue
- **計算量**: O(N) / O(W) (W = 木の最大幅)

---

## 105. Construct Binary Tree from Preorder and Inorder Traversal

### 105.1. 問題文のサマリ

preorder と inorder の走査順配列が与えられたとき、元の二分木を再構築する。値はすべて一意。

### 105.2. 着目すべき法則性

2 つの強力な事実が組み合わさる:

1. **`preorder[0]` は必ずルート**
2. **inorder でルートを見つけると、左側 = 左部分木の inorder、右側 = 右部分木の inorder**
3. 左部分木のサイズが分かれば、`preorder` 内でも左右の範囲が確定する

→ 部分問題 (左部分木 / 右部分木) も同じ構造になり、再帰で再構築できる。

### 105.3. 解法のサマリ

- **再帰**: `preorder` のインデックスを進めつつ、`inorder` でルート位置を探して左右に分割し、両方を再帰
- **最適化**: `inorder` の `値→index` を HashMap に事前構築すると探索が O(1) になり全体 O(N)
- **計算量**: O(N) / O(N)

---

## 98. Validate Binary Search Tree

### 98.1. 問題文のサマリ

二分木が **有効な BST** かを判定する。各ノードについて「左部分木の全値 < 自 < 右部分木の全値」が再帰的に成立する必要がある。

### 98.2. 着目すべき法則性

**「親子の比較だけでは不十分」** という落とし穴がポイント。
`5 - 4 - 6` のような形で、ルート 5 の右部分木に 4 を含めると不正だが、4 の親 6 だけ見ると問題ない。
→ 各ノードに **「許容される値の範囲 [lower, upper]」** を上から伝播させる必要がある。

代替視点: **BST を in-order 走査すると値が単調増加** という性質も使える。

### 98.3. 解法のサマリ

- **DFS + 範囲伝播**: 引数に `lowerBound, upperBound` を渡し、左に降りるとき `upper = node.val`、右に降りるとき `lower = node.val` に更新
- **In-order 法**: 反復 in-order で前ノードと比較、単調増加でなければ false
- **計算量**: O(N) / O(H)

---

## 230. Kth Smallest Element in a BST

### 230.1. 問題文のサマリ

BST の `root` と整数 `k` が与えられたとき、全ノードの値のうち **k 番目に小さい値** (1-indexed) を返す。

### 230.2. 着目すべき法則性

BST を **in-order traversal** すると値が **昇順** に並ぶ。
→ 訪問した順に数えて `k` 番目に到達したノードの値が答え。

### 230.3. 解法のサマリ

- **反復スタック版 in-order**: 早期終了 (`k` 個目で break) ができ O(H+k)
- 再帰版でも可 (フィールドではなく `int[]` で渡すと再入可能)
- **フォローアップ**: 頻繁に挿入・削除があるなら、各ノードに「左部分木のサイズ」を持たせると挿入・削除 O(H)、k 番目検索 O(H) で両立可能
- **計算量**: O(H+k) / O(H)

---

## 235. Lowest Common Ancestor of a BST

### 235.1. 問題文のサマリ

BST の `root` と 2 ノード `p`, `q` が与えられたとき、両者の **最小共通祖先** (LCA) を返す。ノードは自分自身の子孫ともみなす。

### 235.2. 着目すべき法則性

BST の値の大小だけで「LCA がどこにあるか」が一意に決まる:

| `p`, `q` の位置 | 結論 |
| --- | --- |
| 両方 `current` より小さい | LCA は左部分木 |
| 両方 `current` より大きい | LCA は右部分木 |
| `p < current < q` (or 逆) | **`current` が LCA** |
| `current` が `p` か `q` | **`current` が LCA** (もう一方は子孫) |

→ 「左に降りるか、右に降りるか、ここで終わるか」が単純な分岐で決まる。

### 235.3. 解法のサマリ

- **反復**: ルートから値比較しながら降りるだけ。O(1) 空間
- **再帰**: 末尾再帰相当。`return lca(node.left/right, p, q)` の形
- **発展**: `#236` (普通の二分木の LCA) は値比較が使えないので、左右両方を再帰して結果を集約する別解法に切り替える
- **計算量**: O(H) / O(1) (反復) または O(H) (再帰)

---

## 208. Implement Trie (Prefix Tree)

### 208.1. 問題文のサマリ

`insert(word)`, `search(word)` (完全一致), `startsWith(prefix)` (プレフィックス一致) をサポートする **Trie** データ構造を実装する。

### 208.2. 着目すべき法則性

各文字を木のエッジに対応させると、**同じプレフィックスを持つ単語が同じパスを共有** する。
→ 単語数 W に依存せず、検索コストが **単語長 L のみ** に依存する (HashSet の O(L) と同等だが、プレフィックス検索が即時できる)。

`isEnd` フラグで「ここまでで 1 つの単語が終わる」ことを記録するのが必須 (`apple` と `app` を区別するため)。

### 208.3. 解法のサマリ

- **TrieNode**: `children` (HashMap or サイズ 26 の配列) + `isEnd` フラグ
- **insert**: 1 文字ずつ降り、無ければノード生成、最後に `isEnd = true`
- **search**: 1 文字ずつ辿り、途中で欠けたら false、最後に `isEnd` 確認
- **startsWith**: search と同じだが `isEnd` チェック不要
- **計算量**: 各操作 O(L) / 全文字数に比例

---

## 211. Design Add and Search Words Data Structure

### 211.1. 問題文のサマリ

`addWord(word)` と `search(word)` を提供。`search` の `word` には任意 1 文字を表す `'.'` を含めることができる。

### 211.2. 着目すべき法則性

通常の Trie 検索を拡張し、**`'.'` に出会ったときだけ「現在ノードの全ての子に対して残りの suffix を再帰探索」** する。
それ以外の文字は通常の Trie 検索と同じ。

→ `'.'` が多い検索ではブランチが指数的に増えるが、通常の単語ならほぼ O(L)。

### 211.3. 解法のサマリ

- **データ構造**: 通常の Trie
- **search**: 文字位置 `i` と現在ノードを引数に取る再帰 DFS
  - `c == '.'` → 全ての子で再帰、1 つでも true なら true
  - それ以外 → 該当する子があるかチェックして再帰
- **計算量**: 通常 O(L) / 最悪 O(26^L) (`'.'` 多数時)

---

## 124. Binary Tree Maximum Path Sum (Hard)

### 124.1. 問題文のサマリ

二分木の **任意の 2 ノード間** (1 ノードでも可、ルートを通る必要なし) を結ぶパスの最大和を返す。値は負を含み得る。

### 124.2. 着目すべき法則性

各ノードについて 2 つの異なる量を区別する必要がある:

1. **「自分から下に片側だけ伸びる」最大ゲイン** (= 親に渡す値、`gain(node) = node.val + max(0, max(gainL, gainR))`)
2. **「自分を頂点として通過する」最大パス和** (= グローバル候補、`gainL + node.val + gainR`)

子からの貢献が負の場合は 0 として扱う (パスを伸ばさない選択をする) のがコツ。

### 124.3. 解法のサマリ

- **Tree DP**: post-order DFS で各ノードの「片側ゲイン」を計算しつつ、グローバル `maxSum` を「通過パス和」で更新
- 再帰の戻り値は **片側ゲイン**、グローバル更新は副作用
- **計算量**: O(N) / O(H)

---

## 297. Serialize and Deserialize Binary Tree (Hard)

### 297.1. 問題文のサマリ

二分木を **文字列化 (serialize)** して、その文字列から **同じツリーに復元 (deserialize)** できるエンコード方式を実装する。

### 297.2. 着目すべき法則性

通常の preorder / inorder / postorder 単独では構造が一意に定まらない (BST なら一意だが、一般の二分木は不可)。
ただし **null も含めて preorder を記録すれば、構造が完全に一意に復元できる**。
- 例: `1, 2, null, null, 3, null, null` で構造が決まる

→ null を `"#"` 等のマーカーで明示するのがキー。

### 297.3. 解法のサマリ

- **serialize**: preorder DFS、null は `"#"` で記録、カンマ区切りで連結
- **deserialize**: トークンを Queue (Deque) に詰めて、preorder 順に「先頭を取り出す → ノード生成 → 左を再帰 → 右を再帰」
- 代替: BFS (level-order) + null マーカーでも可
- **計算量**: O(N) / O(N)

---

## 212. Word Search II (Hard)

### 212.1. 問題文のサマリ

`m × n` のグリッドと単語リストが与えられたとき、グリッド上 (上下左右 4 方向に隣接、同セル再使用不可) に存在する単語をすべて返す。

### 212.2. 着目すべき法則性

素朴に「単語ごとにグリッド DFS」だと O(W × M × N × 4^L) になり、単語が多いと TLE。
→ **単語リストを Trie に格納** し、グリッド DFS と Trie の降下を **同時に行う** ことで、1 回の DFS で全単語を同時に判定できる。

ポイント: Trie ノードに `word` フィールドを持たせ、`isEnd` で完全一致を検出 → 結果に追加 → 重複追加防止のため Trie 側でフィールドを null 化、というテクが定番。

### 212.3. 解法のサマリ

- **前処理**: 単語リストを Trie 構築
- **探索**: 各セル `(r, c)` から DFS、Trie ノードを引数で持ち回る
  - 現在の文字に対応する子が Trie になければ即 return (枝刈り)
  - 子に降りた先が `isEnd` なら結果に追加
  - 訪問マーキング (元の文字を一時的に書き換え) → 4 方向再帰 → 復元 (Backtracking)
- **計算量**: O(M × N × 4^L) / O(全単語の総文字数)
