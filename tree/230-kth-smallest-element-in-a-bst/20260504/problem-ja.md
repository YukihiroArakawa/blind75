# 230. 二分探索木における k 番目に小さい要素

難易度: Medium

## 問題

二分探索木の `root` と整数 `k` が与えられたとき、ツリーの全ノードの値のうち、`k` 番目に小さい値（**1-indexed**）を返してください。

## 例

**例 1:**

![kthtree1](https://assets.leetcode.com/uploads/2021/01/28/kthtree1.jpg)

```
入力: root = [3,1,4,null,2], k = 1
出力: 1
```

**例 2:**

![kthtree2](https://assets.leetcode.com/uploads/2021/01/28/kthtree2.jpg)

```
入力: root = [5,3,6,2,4,null,null,1], k = 3
出力: 3
```

## 制約

- ツリーのノード数は `n` です。
- `1 <= k <= n <= 10^4`
- `0 <= Node.val <= 10^4`

**フォローアップ:** もし BST が頻繁に変更され（つまり、挿入や削除操作が行われる）、k 番目に小さい要素を頻繁に検索する必要がある場合、どのように最適化しますか？
