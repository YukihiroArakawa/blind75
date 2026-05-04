// 問題: 235. Lowest Common Ancestor of a Binary Search Tree
// アプローチ: BST の性質 (左部分木 < ノード < 右部分木) を使い、再帰で下に降りる
// AISolution との違い: while ループではなく再帰呼び出しで「次のノードに移る」を表現する
//   - p, q ともに現在のノードより小さい → 左部分木に再帰
//   - p, q ともに現在のノードより大きい → 右部分木に再帰
//   - それ以外 (分岐点 or 自分自身が p/q) → 現在のノードが LCA
// なぜ再帰版も知っておくか: BST 系の問題では再帰の方が「BST の構造をそのまま辿る」ことを
// 表現しやすく、書きやすい場合が多い。末尾再帰相当なので性能は反復版とほぼ同等
// 時間計算量: O(H) — 高さ分しか降りない (バランスが取れていれば O(log N))
// 空間計算量: O(H) — 再帰呼び出しのコールスタック分
class Solution {
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    // root は問題の制約上 null にならないが、防御的にチェック
    // (再帰呼び出しの中では、本来到達しないが null が来うるケースをカバーする意味もある)
    if (root == null) {
      return null;
    }

    int rootValue = root.val;
    int pValue = p.val;
    int qValue = q.val;

    if (pValue < rootValue && qValue < rootValue) {
      // p, q ともに左側にある → LCA は左部分木のどこか
      return lowestCommonAncestor(root.left, p, q);
    }

    if (pValue > rootValue && qValue > rootValue) {
      // p, q ともに右側にある → LCA は右部分木のどこか
      return lowestCommonAncestor(root.right, p, q);
    }

    // 分岐点に到達した
    // パターン1: p < root < q (または逆) → ここで p と q が左右に分かれる
    // パターン2: root が p か q そのもの → 自分自身が LCA (もう一方は子孫)
    // どちらの場合も root が答え
    return root;
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
