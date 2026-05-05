# 解説: 208. Implement Trie (Prefix Tree) — 配列方式 (高速版)

`AISolution` (HashMap 方式) との違いは **子ノードの保持方法だけ**。
データ構造の選び方で性能とコードの雰囲気がどう変わるかを比較するための姉妹解。

問題自体の整理 (操作の意味、`search` と `startsWith` の違いなど) や、trie 全体の動きの可視化は `AISolution/explanation.md` を参照。
ここでは **「配列方式に変えると何が変わるか」** に焦点を当てる。

## 1. なぜ配列方式が選べるか

問題の制約に **「`word` と `prefix` は英小文字のみで構成される」** が明記されている。
→ 子ノードのキーは必ず `'a'`〜`'z'` の 26 種類のいずれか。
→ **長さ 26 の固定配列** で各文字を表現できる (キーのインデックス化が可能)。

```
'a' → children[0]
'b' → children[1]
...
'z' → children[25]
```

文字 `c` から添字への変換は `c - 'a'` で 1 引き算するだけ。

## 2. HashMap 方式 (AISolution) との比較

| 観点 | HashMap 方式 | 配列方式 (本実装) |
| --- | --- | --- |
| 子の保持 | `Map<Character, Node>` | `Node[26]` |
| 1 文字あたりの操作 | ハッシュ計算 + バケット参照 | **添字計算 + 配列アクセス (定数時間, 単純命令数も少ない)** |
| 計算量 (理論) | 平均 O(L) | **常に O(L) (最悪ケースなし)** |
| 体感速度 | 速い (LeetCode で 50〜100ms) | **さらに速い (LeetCode で 30〜50ms)** |
| メモリ使用量 | 実際に登場した文字分だけ | **常に 26 スロット確保 (空でも参照 1 つ分)** |
| 柔軟性 | 任意の文字 / Unicode 対応可 | **英小文字限定** |
| 「children に c があるか?」の表現 | `children.containsKey(c)` | `children[c - 'a'] != null` |

要するに、**「制約を最大限利用して、ハッシュ関数を引き算 1 回に置き換える」** のが配列方式の本質。

## 3. コードの読み解き

`AISolution` とほぼ同じ構造 (`traverse` ヘルパで共通処理を抽出) だが、HashMap 操作が配列添字操作に置き換わっている:

```java
// HashMap 方式 (AISolution):
char c = word.charAt(i);
if (!current.children.containsKey(c)) {
    current.children.put(c, new Node());
}
current = current.children.get(c);

// 配列方式 (AISolution2):
int index = word.charAt(i) - 'a';
if (current.children[index] == null) {
    current.children[index] = new Node();
}
current = current.children[index];
```

ロジックの形は完全に同じ。「containsKey + put + get」の 3 つが「配列の null 判定 + 代入 + 添字アクセス」に対応。

## 4. 配列方式で気をつける点

- **添字計算をミスらない**: `charAt(i) - 'a'` を `charAt(i) + 'a'` などと書くと違う場所を読み書きしてしまう。出やすいケアレスミス。
- **大文字や記号が混ざると即破綻**: `'A' - 'a' = -32` のような負の添字で `ArrayIndexOutOfBoundsException`。問題制約で英小文字保証があるからこそ使える。
- **メモリ消費の見積もり**: 各ノードが **必ず 26 個の参照** (Java の `Object[]` なら 1 参照 = 4 or 8 バイト) を持つ。1 単語あたりノード数 × 26 ポインタの分は確実に消費される。HashMap 方式と比べて、登場する文字種が少ない場合 (例: 全単語が `a`, `b` だけ) は配列方式の方が無駄が大きい。逆に全文字種を均等に使うなら配列方式の方が定数倍小さい (HashMap のエントリ構造のオーバーヘッドがないため)。
- **「初期化忘れ」の落とし穴がない**: Java の参照型配列は宣言時に自動で全要素 null になる。`new Node[26]` した直後の `children[0]..children[25]` は全て null。明示初期化ループは不要。

## 5. 計算量

- `insert(word)`: O(L)。L は `word.length()`。配列添字アクセスは厳密に O(1) で、ハッシュ衝突による劣化なし。
- `search(word)` / `startsWith(prefix)`: O(L)。
- 空間計算量: O(N * 26) (N = trie 内ノード数)。実際のメモリ使用量は HashMap 方式より定数倍小さくなる傾向 (ハッシュ表のオーバーヘッドがないため)。
- 支配的な処理: 文字列の長さに比例した木の辿り処理。

## 6. どちらを選ぶか

| シーン | おすすめ |
| --- | --- |
| 初めて trie を理解したい | HashMap 方式 (`AISolution`) |
| 任意の文字を扱いたい (Unicode、記号、混在) | HashMap 方式 |
| 競技プログラミングでミリ秒を絞りたい | 配列方式 (本実装) |
| 大量の単語を入れる + 全文字種が均等に登場する | 配列方式 |
| 大量の単語を入れる + 文字種が偏る | HashMap 方式 (空エントリのメモリ無駄を避けるため) |

LeetCode 208 の制約 (操作数 3*10^4、各単語最大 2000 文字) では **どちらも余裕で通る**。配列方式は「英小文字 → 添字」の変換イディオムを覚えるためのトレーニングと考えるとよい。
