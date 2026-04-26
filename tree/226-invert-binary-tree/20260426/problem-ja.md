# 226. 二分木の反転

難易度: Easy

## 問題

二分木の `root` が与えられたとき、その木を反転し、反転後の root を返してください。

## 例

**例 1:**

![invert1-tree](https://assets.leetcode.com/uploads/2021/03/14/invert1-tree.jpg)

```
入力: root = [4,2,7,1,3,6,9]
出力: [4,7,2,9,6,3,1]
```

**例 2:**

![invert2-tree](https://assets.leetcode.com/uploads/2021/03/14/invert2-tree.jpg)

```
入力: root = [2,1,3]
出力: [2,3,1]
```

**例 3:**

```
入力: root = []
出力: []
```

## 制約

- 木のノード数は `[0, 100]` の範囲内である。
- `-100 <= Node.val <= 100`
