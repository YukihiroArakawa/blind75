# 208. Trie (プレフィックスツリー) の実装

難易度: Medium

## 問題

**trie**（「トライ」と発音）または **プレフィックスツリー** は、文字列のデータセットからキーを効率的に保存・検索するために使用されるツリー型のデータ構造である。このデータ構造はオートコンプリートやスペルチェッカーなど、さまざまな用途に応用される。

Trie クラスを実装せよ:

- `Trie()` Trie オブジェクトを初期化する。
- `void insert(String word)` 文字列 `word` を trie に挿入する。
- `boolean search(String word)` 文字列 `word` が trie に存在する（つまり以前に挿入された）場合は `true` を、そうでなければ `false` を返す。
- `boolean startsWith(String prefix)` 以前に挿入された文字列 `word` の中に `prefix` をプレフィックスとして持つものが存在すれば `true` を、そうでなければ `false` を返す。

## 例

**例 1:**

```
入力
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
出力
[null, null, true, false, true, null, true]

説明
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // True を返す
trie.search("app");     // False を返す ("app" はまだ挿入されていない)
trie.startsWith("app"); // True を返す ("apple" のプレフィックス)
trie.insert("app");
trie.search("app");     // True を返す
```

## 制約

- `1 <= word.length, prefix.length <= 2000`
- `word` および `prefix` は英小文字のみで構成される。
- `insert`, `search`, `startsWith` の呼び出しは合計で最大 `3 * 10^4` 回行われる。
