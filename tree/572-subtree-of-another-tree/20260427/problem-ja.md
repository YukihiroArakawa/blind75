# 572. 別の木の部分木

難易度: Easy

## 問題

2 つの二分木のルート `root` と `subRoot` が与えられる。`root` の部分木の中に、`subRoot` と同じ構造かつ同じノードの値を持つものが存在する場合は `true` を返し、存在しない場合は `false` を返せ。

二分木 `tree` の部分木とは、`tree` 中のあるノードと、そのノードの全ての子孫から構成される木のことである。木 `tree` は自分自身の部分木とみなすこともできる。

## 例

**例 1:**

![例 1](https://assets.leetcode.com/uploads/2021/04/28/subtree1-tree.jpg)

```
入力: root = [3,4,5,1,2], subRoot = [4,1,2]
出力: true
```

**例 2:**

![例 2](https://assets.leetcode.com/uploads/2021/04/28/subtree2-tree.jpg)

```
入力: root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
出力: false
```

## 制約

- `root` の木のノード数は `[1, 2000]` の範囲。
- `subRoot` の木のノード数は `[1, 1000]` の範囲。
- `-10^4 <= root.val <= 10^4`
- `-10^4 <= subRoot.val <= 10^4`
