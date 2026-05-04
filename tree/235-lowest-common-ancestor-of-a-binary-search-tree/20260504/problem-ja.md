# 235. 二分探索木の最小共通祖先

難易度: Medium

## 問題

二分探索木 (BST) と、その BST 内の 2 つのノードが与えられたとき、その 2 つのノードの最小共通祖先 (Lowest Common Ancestor, LCA) ノードを見つけてください。

[Wikipedia における LCA の定義](https://en.wikipedia.org/wiki/Lowest_common_ancestor)によると: 「2 つのノード `p` と `q` の最小共通祖先とは、`T` の中で `p` と `q` の両方を子孫として持つ最も深いノードである（**ノードは自分自身の子孫であるとみなす**）。」

## 例

**例 1:**

![binarysearchtree_improved](https://assets.leetcode.com/uploads/2018/12/14/binarysearchtree_improved.png)

```
入力: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
出力: 6
説明: ノード 2 と 8 の LCA は 6 です。
```

**例 2:**

![binarysearchtree_improved](https://assets.leetcode.com/uploads/2018/12/14/binarysearchtree_improved.png)

```
入力: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
出力: 2
説明: ノード 2 と 4 の LCA は 2 です。LCA の定義により、ノードは自分自身の子孫とみなせるためです。
```

**例 3:**

```
入力: root = [2,1], p = 2, q = 1
出力: 2
```

## 制約

- ツリーのノード数は `[2, 10^5]` の範囲です。
- `-10^9 <= Node.val <= 10^9`
- すべての `Node.val` は **一意** です。
- `p != q`
- `p` と `q` は必ず BST に存在します。
