# 211. Add と Search Words データ構造の設計

難易度: Medium

## 問題

新しい単語の追加と、これまでに追加された単語のいずれかに文字列がマッチするかの判定をサポートするデータ構造を設計してください。

`WordDictionary` クラスを実装してください:

- `WordDictionary()` オブジェクトを初期化します。
- `void addWord(word)` データ構造に `word` を追加します。後で検索可能になります。
- `bool search(word)` データ構造内に `word` にマッチする文字列が存在すれば `true`、そうでなければ `false` を返します。`word` にはドット `'.'` を含めることができ、ドットは任意の文字 1 つにマッチします。

## 例

**例:**

```
入力
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
出力
[null,null,null,null,false,true,true,true]

説明
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // false を返す
wordDictionary.search("bad"); // true を返す
wordDictionary.search(".ad"); // true を返す
wordDictionary.search("b.."); // true を返す
```

## 制約

- `1 <= word.length <= 25`
- `addWord` の `word` は英小文字のみで構成されます。
- `search` の `word` は `'.'` または英小文字で構成されます。
- `search` クエリの `word` には最大 `2` 個のドットが含まれます。
- `addWord` と `search` の合計呼び出し回数は最大 `10^4` 回です。
