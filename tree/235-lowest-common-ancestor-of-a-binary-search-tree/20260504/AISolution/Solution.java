// 問題: 235. Lowest Common Ancestor of a Binary Search Tree
// アプローチ: BST の性質 (左部分木 < ノード < 右部分木) を使い、
// 現在のノードと p, q の値を比較しながら下に降りていく
//   - p, q ともに現在のノードより小さい → 左部分木に LCA がある
//   - p, q ともに現在のノードより大きい → 右部分木に LCA がある
//   - それ以外 (p, q が現在のノードを挟む or 現在のノード自身が p か q) → 現在のノードが LCA
// 反復版を採用する理由: 末尾再帰相当の処理なので、ループに展開した方がシンプルでスタックも消費しない
// 時間計算量: O(H) — 高さ分しか降りない (バランスが取れていれば O(log N))
// 空間計算量: O(1) — 追加のデータ構造を使わない
class Solution {
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    // p と q の値を一度だけ取り出して比較に使う (毎回参照するより読みやすい)
    int pValue = p.val;
    int qValue = q.val;

    TreeNode currentNode = root;

    // 制約により p, q は必ず BST に存在するため、currentNode が null になることはない
    while (currentNode != null) {
      int currentValue = currentNode.val;

      if (pValue < currentValue && qValue < currentValue) {
        // p, q ともに左側にある → LCA は左部分木のどこか
        currentNode = currentNode.left;
      } else if (pValue > currentValue && qValue > currentValue) {
        // p, q ともに右側にある → LCA は右部分木のどこか
        currentNode = currentNode.right;
      } else {
        // 分岐点に到達した
        // パターン1: p < current < q (または逆) → ここで p と q が左右に分かれる
        // パターン2: current が p か q そのもの → 自分自身が LCA (もう一方は子孫)
        // どちらの場合も current が答え
        return currentNode;
      }
    }

    // 制約上ここには到達しない
    return null;
  }

  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }
}
