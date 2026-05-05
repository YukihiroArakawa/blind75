# blind75 String 領域 サマリー

各問題について「問題文サマリ」「着目すべき法則性 (Key Insight)」「解法サマリ」を整理する。
**計算量** は `時間 / 空間` の表記。`N` は文字列長、`M` はパターン長など。

文字列問題の主要パターン:
- **Sliding Window**: 部分文字列の最大/最小長
- **Two Pointers**: 両端から or 同方向 2 指針
- **HashMap / 配列 26**: 文字頻度比較
- **中心展開**: 回文系
- **Stack**: 括弧マッチ

## 索引

| # | 問題 | 難易度 | 中心パターン |
| --- | --- | --- | --- |
| 242 | Valid Anagram | Easy | 文字頻度比較 (配列 26) |
| 20 | Valid Parentheses | Easy | Stack + ペア表 |
| 125 | Valid Palindrome | Easy | Two Pointers + 英数字スキップ |
| 3 | Longest Substring Without Repeating Characters | Medium | Sliding Window + last index map |
| 424 | Longest Repeating Character Replacement | Medium | Sliding Window + max freq |
| 49 | Group Anagrams | Medium | HashMap (key = ソート済 or 頻度) |
| 5 | Longest Palindromic Substring | Medium | 中心展開 (奇 + 偶) |
| 647 | Palindromic Substrings | Medium | 中心展開でカウント |
| 271 | Encode and Decode Strings | Medium | 長さプレフィックス + デリミタ |
| 76 | Minimum Window Substring | Hard | Sliding Window + need vs have |

---

## 242. Valid Anagram

### 242.1. 問題文のサマリ

2 つの文字列 `s`, `t` が **アナグラム** (= 同じ文字を同じ回数含む) か判定。

### 242.2. 着目すべき法則性

「同じ文字頻度 (multiset として等しい)」を判定 → 各文字の出現回数を数えて比較するだけ。
英小文字限定なら **サイズ 26 の配列** が最速 (HashMap より O(1) 定数倍速い)。

代替: 両方をソートして文字列比較 → O(N log N) だが追加メモリ少なめ。

### 242.3. 解法のサマリ

- 配列 `count[26]` を `s` で +1、`t` で -1 → 全要素 0 なら true
- 長さが違えば即 false
- **計算量**: O(N) / O(1) (アルファベット定数)

---

## 20. Valid Parentheses

### 20.1. 問題文のサマリ

`(`, `)`, `[`, `]`, `{`, `}` のみからなる文字列が **正しく対応** しているか判定。

### 20.2. 着目すべき法則性

開き括弧を **Stack に push**、閉じ括弧を見たら **Stack の top と種類が一致するか** チェック (一致なら pop、しないなら false)。
LIFO 構造そのものが「最も新しい未閉じ括弧」を即取り出せるため、括弧マッチングと相性抜群。

### 20.3. 解法のサマリ

- ペア表: `{')': '(', ']': '[', '}': '{'}`
- 開き括弧 → push、閉じ括弧 → top をチェック (空 or 不一致なら false、一致なら pop)
- ループ後 Stack が空なら true
- **計算量**: O(N) / O(N)

---

## 125. Valid Palindrome

### 125.1. 問題文のサマリ

文字列を「英数字以外を無視 + 大文字小文字無視」した上で、回文か判定。

### 125.2. 着目すべき法則性

**Two Pointers** で両端から内側へ進めつつ、英数字以外を skip。
追加メモリ無しで in-place 判定可能。

### 125.3. 解法のサマリ

- `left, right` を両端から
- それぞれ英数字以外を skip
- `Character.toLowerCase` 比較、不一致なら false
- 一致なら両方内側に動かす
- `left >= right` で true 終了
- **計算量**: O(N) / O(1)

---

## 3. Longest Substring Without Repeating Characters

### 3.1. 問題文のサマリ

文字列 `s` の中で、**重複文字を含まない部分文字列** の最大長を返す。

### 3.2. 着目すべき法則性

**Sliding Window**: `[left, right]` を保持し、新しい文字が window 内に既存なら left を「重複文字の次の位置」までジャンプさせる。
HashMap (文字 → 最後に見た index) を使えば、left のジャンプが O(1)。

### 3.3. 解法のサマリ

- `left = 0`, `lastIndex` HashMap
- `for right = 0..n-1`:
  - `c = s[right]`
  - `if c in lastIndex && lastIndex[c] >= left: left = lastIndex[c] + 1`
  - `lastIndex[c] = right`
  - `best = max(best, right - left + 1)`
- **計算量**: O(N) / O(min(N, アルファベットサイズ))

---

## 424. Longest Repeating Character Replacement

### 424.1. 問題文のサマリ

文字列 `s` と整数 `k` が与えられたとき、`k` 回まで文字置換して **同一文字の最長部分文字列** を作る。その長さを返す。

### 424.2. 着目すべき法則性

window 内で「**最頻文字以外の文字数**」が `k` 以下なら、その window は実現可能 (長さ = `windowLen - maxFreq <= k`)。
→ Sliding Window + window 内の **最大頻度** を維持する。

工夫: `maxFreq` は厳密な最大でなくても良い (一度大きくなったら縮めなくて OK)。理由: 答えはそれ以上の window が見つかった時にのみ更新されるので、過去の `maxFreq` が大きすぎても答えに影響しない。

### 424.3. 解法のサマリ

- `count[26]`、`left = 0`、`maxFreq = 0`
- `for right = 0..n-1`:
  - `count[s[right]]++`、`maxFreq = max(maxFreq, count[s[right]])`
  - `if (right - left + 1) - maxFreq > k`: `count[s[left]]--; left++`
  - `best = max(best, right - left + 1)`
- **計算量**: O(N) / O(1)

---

## 49. Group Anagrams

### 49.1. 問題文のサマリ

文字列配列をアナグラム同士でグループ化して返す。

### 49.2. 着目すべき法則性

**「アナグラム同士に共通の不変な key」** を作って HashMap でグループ化:

- key = ソート済み文字列 → O(L log L) per word
- key = 文字頻度のタプル (例: `"a2b1c3..."`) → O(L) per word

→ HashMap で `key → List<word>` を構築。

### 49.3. 解法のサマリ

- `Map<String, List<String>> groups`
- 各単語 `s`: `key = sorted(s)` (or 頻度文字列) を作って `groups[key].add(s)`
- `groups.values()` を返す
- **計算量**: O(N × L log L) (sort 版) または O(N × L) (頻度版) / O(N × L)

---

## 5. Longest Palindromic Substring

### 5.1. 問題文のサマリ

文字列 `s` の中で **最長の回文部分文字列** を返す。

### 5.2. 着目すべき法則性

**「中心」を固定して両側に伸ばす** (中心展開) と、各中心で O(N)、合計 O(N²)。
中心は **「1 文字 (奇数長) と 2 文字 (偶数長)」の 2 種類** ある (例: `"aba"` の中心は `b`、`"abba"` の中心は `b-b` 間)。
→ `2N - 1` 個の中心を試す。

代替: Manacher で O(N) も可能だが面接では中心展開で十分。

### 5.3. 解法のサマリ

- ヘルパー `expand(l, r)`: `l >= 0 && r < n && s[l] == s[r]` の間 `l--, r++`、最終 `[l+1, r-1]` が回文
- `for i = 0..n-1`: `expand(i, i)` (奇数) と `expand(i, i+1)` (偶数) を試して最長を保持
- **計算量**: O(N²) / O(1)

---

## 647. Palindromic Substrings

### 647.1. 問題文のサマリ

文字列 `s` の中に含まれる **回文部分文字列の総数** を返す (位置が違えば別カウント)。

### 647.2. 着目すべき法則性

#5 と全く同じ「中心展開」を使う。**伸ばせる回数 = その中心から作れる回文の数** (例: `"aba"` を中心に伸ばすと `"b", "aba"` の 2 つ → 2 回伸びた)。

→ 中心展開を `2N - 1` 個やって、伸びた回数を全合計するだけ。

### 647.3. 解法のサマリ

- ヘルパー `countExpand(l, r)`: 回文の間 `l--, r++; count++` を繰り返してカウント返却
- 全中心で呼び出して合計
- **計算量**: O(N²) / O(1)

---

## 271. Encode and Decode Strings

### 271.1. 問題文のサマリ

文字列リスト `strs` を 1 本の文字列に **エンコード** し、その文字列から元のリストに **デコード** できる仕組みを実装する。
任意の文字 (区切り候補も含む) が含まれ得る。

### 271.2. 着目すべき法則性

任意のデリミタ (`,`, `#` など) は文字列内にも出現し得るので **デリミタ単独では曖昧**。
→ **「長さ + デリミタ + 本体」** 形式 (例: `"5#hello3#abc"`) にすれば、長さで本体を切り出せて完全に明瞭。

### 271.3. 解法のサマリ

- encode: 各 `s` について `s.length() + "#" + s` を連結
- decode: ループで `#` を見つけて長さをパース → 次の `len` 文字を抽出 → 進める
- **計算量**: O(N) / O(N) (出力)

---

## 76. Minimum Window Substring (Hard)

### 76.1. 問題文のサマリ

文字列 `s`, `t` が与えられたとき、**`s` の部分文字列で `t` の全文字 (頻度込み) を含む最小** のものを返す (なければ `""`)。

### 76.2. 着目すべき法則性

**Sliding Window + 「必要文字数 vs 現在持っている文字数」のカウント**:

- `need[c]` = `t` における各文字の必要数
- `have` = 現在 window 内で「必要数を満たしている文字種」の数
- `required` = `t` の文字種数
- `have == required` のとき window が条件を満たす → 縮められるだけ縮める

文字単位ではなく **文字種単位の counter (have/required)** で「条件達成」を O(1) 判定するのがコツ。

### 76.3. 解法のサマリ

- `need[c]` と `required` を `t` から構築、`window[c]` と `have = 0`
- `for right = 0..n-1`:
  - `window[s[right]]++`
  - `if window[s[right]] == need[s[right]]: have++`
  - `while have == required:` (window 縮小)
    - 答え候補を更新
    - `window[s[left]]--`、`if window[s[left]] < need[s[left]]: have--`
    - `left++`
- **計算量**: O(N + M) / O(アルファベットサイズ)
