# blind75 Dynamic Programming 領域 サマリー

各問題について「問題文サマリ」「着目すべき法則性 (Key Insight)」「解法サマリ」を整理する。
**計算量** は `時間 / 空間` の表記。

DP 共通の考え方: **「状態 (state)」と「遷移 (transition)」をどう定義するか** が全て。
- `dp[i]` = 「位置 `i` までを使った最適値」もしくは「位置 `i` を末尾とする最適値」
- 遷移は前の状態から定数個 (または前の全状態) を参照

## 索引

| # | 問題 | 難易度 | 中心パターン |
| --- | --- | --- | --- |
| 70 | Climbing Stairs | Easy | フィボナッチ (1D, 2 変数) |
| 322 | Coin Change | Medium | 完全ナップサック |
| 300 | Longest Increasing Subsequence | Medium | 1D DP O(N²) or patience O(N log N) |
| 1143 | Longest Common Subsequence | Medium | 2D DP |
| 139 | Word Break | Medium | 1D DP + 辞書 lookup |
| 39 | Combination Sum | Medium | Backtracking + start index |
| 198 | House Robber | Medium | 1D DP (rob/skip) |
| 213 | House Robber II | Medium | 198 を 2 回 (環の処理) |
| 91 | Decode Ways | Medium | 1D DP (1桁/2桁) |
| 62 | Unique Paths | Medium | 2D DP (or 組合せ数) |
| 55 | Jump Game | Medium | Greedy (到達可能最大 index) |

---

## 70. Climbing Stairs

### 70.1. 問題文のサマリ

`n` 段の階段を 1 段 or 2 段ずつ登る方法の総数を返す。

### 70.2. 着目すべき法則性

`n` 段目に到達する直前の状態は **「n-1 段目から 1 段」** か **「n-2 段目から 2 段」** の 2 通り:

```
dp[n] = dp[n-1] + dp[n-2]
```

= フィボナッチ数列。直前 2 状態だけで遷移するので 2 変数で十分。

### 70.3. 解法のサマリ

- 2 変数 `prev2 = 1, prev1 = 1` を更新: `cur = prev1 + prev2` → 進める
- **計算量**: O(N) / O(1)

---

## 322. Coin Change

### 322.1. 問題文のサマリ

異なる額面のコイン `coins` と金額 `amount` が与えられたとき、`amount` を作るのに必要な **最小コイン枚数** を返す (作れなければ -1)。各コインは何回でも使える。

### 322.2. 着目すべき法則性

「金額 `a` を作る最小枚数」を `dp[a]` とする (= 完全ナップサック):

```
dp[a] = min(dp[a - c] + 1)  for each coin c <= a
```

= 「最後に使ったコイン `c` で場合分け」して、残り金額の最適解 + 1。
未到達は無限大 (or `amount + 1`) で初期化。

### 322.3. 解法のサマリ

- `dp[0..amount]` を `amount + 1` で初期化、`dp[0] = 0`
- 二重ループ: `for a = 1..amount, for c in coins: if c <= a: dp[a] = min(dp[a], dp[a-c] + 1)`
- 最後 `dp[amount]` が `amount + 1` のままなら -1
- 代替: BFS で「金額 → 金額 - c」の最短ステップ
- **計算量**: O(amount × len(coins)) / O(amount)

---

## 300. Longest Increasing Subsequence

### 300.1. 問題文のサマリ

整数配列 `nums` の **最長 (狭義) 増加部分列** の長さを返す (連続でなくてよい)。

### 300.2. 着目すべき法則性

**素朴 DP**: `dp[i] = nums[i] を末尾とする LIS の長さ`

```
dp[i] = 1 + max(dp[j])   (j < i, nums[j] < nums[i])
```

→ O(N²)。

**高速化 (patience sort)**: `tails[k]` = 「長さ `k+1` の増加列の末尾の最小値」を維持。
`nums[i]` が来たら `tails` 内で `nums[i]` 以上の最小位置に二分探索で上書き → O(N log N)。

### 300.3. 解法のサマリ

- 素朴 DP: 二重ループで `dp[i]` を更新、`max(dp)` を返す
- 二分探索版: `tails` 配列に `lower_bound` で挿入位置を見つけて上書き、最終長さを返す
- **計算量**: O(N²) または O(N log N) / O(N)

---

## 1143. Longest Common Subsequence

### 1143.1. 問題文のサマリ

2 つの文字列 `text1`, `text2` の **最長共通部分列** (連続でなくてよい) の長さを返す。

### 1143.2. 着目すべき法則性

`dp[i][j]` = 「`text1[0..i)` と `text2[0..j)` の LCS の長さ」と定義:

```
text1[i-1] == text2[j-1]:  dp[i][j] = dp[i-1][j-1] + 1
それ以外:                  dp[i][j] = max(dp[i-1][j], dp[i][j-1])
```

= 「両方の末尾が一致するなら使う、しないなら片方落とす」の場合分け。

### 1143.3. 解法のサマリ

- 2D DP テーブル `(M+1) × (N+1)` を 0 初期化、上の遷移で埋める
- 空間最適化: 2 行だけ持てば O(min(M, N)) に削減可能
- **計算量**: O(M × N) / O(M × N) (最適化で O(min))

---

## 139. Word Break

### 139.1. 問題文のサマリ

文字列 `s` と単語辞書 `wordDict` が与えられたとき、`s` を辞書の単語の連結で表現できるか (true/false)。
単語は何回でも使える。

### 139.2. 着目すべき法則性

`dp[i]` = 「`s[0..i)` を辞書で分割可能か」と定義:

```
dp[i] = OR over j < i of:  dp[j] AND (s[j..i) が辞書にある)
```

→ 「最後に使った単語 `s[j..i)` で場合分け」。`dp[0] = true` (空文字列)。

### 139.3. 解法のサマリ

- `dp[0..N]` を false 初期化、`dp[0] = true`
- `for i = 1..N, for j = 0..i-1: if dp[j] && s[j..i) in dict: dp[i] = true; break`
- 高速化: 辞書を HashSet 化、最大単語長で `j` の範囲を制限
- **計算量**: O(N² × L) / O(N + 辞書サイズ) (L = 単語長判定コスト)

---

## 39. Combination Sum

### 39.1. 問題文のサマリ

異なる正整数 `candidates` と `target` が与えられたとき、足して `target` になる組合せをすべて返す (各候補は何度でも使用可、組合せ順は無視)。

### 39.2. 着目すべき法則性

これは **DP より Backtracking** が自然 (組合せ列挙のため)。
重複防止のため **start index** を引数に持ち、「自分以前の候補は使わない」というルールで進む:

- 各呼び出しで `i = start..len(candidates)` を試す
- 同じ `i` を再帰で許す (= 何度でも使える)
- `target` が 0 になった瞬間に解を記録

### 39.3. 解法のサマリ

- 再帰: `backtrack(remaining, start, path)`
  - `remaining == 0` → `result.add(path)`
  - `remaining < 0` → return
  - `for i = start..n: path.add(candidates[i]); backtrack(remaining - candidates[i], i, path); path.removeLast()`
- 枝刈り: candidates をソートしておき、`candidates[i] > remaining` で break
- **計算量**: O(N^(target/min)) の指数 / O(target/min) (再帰深さ)

---

## 198. House Robber

### 198.1. 問題文のサマリ

各家にお金 `nums[i]` がある。**隣接する家** からは盗めない (連続不可)。最大盗める金額を返す。

### 198.2. 着目すべき法則性

家 `i` で「盗む」か「盗まない」の二択:

```
dp[i] = max(dp[i-1], dp[i-2] + nums[i])
```

= 「`i` を盗まず `i-1` までの最適」 vs 「`i` を盗み `i-2` までの最適 + 自分」。
直前 2 状態だけで決まるので 2 変数 OK。

### 198.3. 解法のサマリ

- 2 変数 `prev2, prev1` を更新: `cur = max(prev1, prev2 + nums[i])`
- **計算量**: O(N) / O(1)

---

## 213. House Robber II

### 213.1. 問題文のサマリ

198 と同じだが、家が **円環状** に並ぶ (最初と最後も隣接)。

### 213.2. 着目すべき法則性

**「最初を含む」と「最後を含む」が同時に成立できない** (隣接になるため)。
→ 排他的な 2 ケースに分割:

- ケース A: 家 `[0..n-2]` で 198 を解く (最後を含めない)
- ケース B: 家 `[1..n-1]` で 198 を解く (最初を含めない)
- `max(A, B)` が答え

→ 円環問題 → 2 つの直線問題に帰着。

### 213.3. 解法のサマリ

- 198 の関数を 2 回呼び出し: `[0..n-2]` と `[1..n-1]`
- 端例: `n == 1` のとき `nums[0]` を返す
- **計算量**: O(N) / O(1)

---

## 91. Decode Ways

### 91.1. 問題文のサマリ

数字文字列 `s` ('A'=1, 'B'=2, ..., 'Z'=26 でデコード) のデコード方法の総数を返す。

### 91.2. 着目すべき法則性

`dp[i]` = 「`s[0..i)` のデコード方法の数」とする:

```
dp[i] = (s[i-1] が '1'-'9' なら dp[i-1])         // 1 桁デコード
      + (s[i-2..i) が "10"-"26" なら dp[i-2])    // 2 桁デコード
```

= 「最後にデコードした文字が 1 桁か 2 桁か」で場合分け。
'0' 単独はデコード不可 (= 0 通り) という落とし穴に注意。

### 91.3. 解法のサマリ

- `dp[0] = 1, dp[1] = (s[0] != '0' ? 1 : 0)` で初期化
- `for i = 2..N` で 1 桁・2 桁の加算
- 2 変数で空間最適化可能
- **計算量**: O(N) / O(1)

---

## 62. Unique Paths

### 62.1. 問題文のサマリ

`m × n` グリッドの左上から右下まで、右か下にしか動けない場合の経路数を返す。

### 62.2. 着目すべき法則性

セル `(i, j)` への到達方法 = `(i-1, j)` から or `(i, j-1)` から:

```
dp[i][j] = dp[i-1][j] + dp[i][j-1]
```

= パスカルの三角形と同じ漸化式。
**閉じ式**: `C(m+n-2, m-1)` (組合せ)。下移動 m-1 回・右移動 n-1 回の並べ替え。

### 62.3. 解法のサマリ

- 2D DP: 1 行目・1 列目を 1 で初期化、残りは上記漸化式
- 空間最適化: 1 行だけで in-place 更新可
- 数式: `C(m+n-2, m-1)` を直接計算
- **計算量**: O(M × N) / O(N) (最適化版) または O(min) (数式)

---

## 55. Jump Game

### 55.1. 問題文のサマリ

非負整数配列 `nums` (各値はその位置からジャンプできる最大歩数) で、index 0 から最後まで到達可能か (true/false)。

### 55.2. 着目すべき法則性

DP より **Greedy** が綺麗:

- 走査しながら **「これまでに到達可能な最右端 `maxReach`」** を更新
- `i > maxReach` になった瞬間、そこより先は到達不能 → false
- `maxReach >= n - 1` になれば true

「到達可能な範囲は連続区間」という性質が貪欲を成立させる。

### 55.3. 解法のサマリ

- 1-pass: `maxReach = 0`、`for i = 0..n-1: if i > maxReach: return false; maxReach = max(maxReach, i + nums[i])`
- ループ完走したら true
- **計算量**: O(N) / O(1)
