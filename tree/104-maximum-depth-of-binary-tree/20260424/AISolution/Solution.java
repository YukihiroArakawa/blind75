// 問題: 104. Maximum Depth of Binary Tree
// アプローチ: 再帰（深さ優先探索、DFS）による分割統治
//   左右の部分木それぞれの最大深さを再帰的に求め、大きい方に自身の分の 1 を加算する。
//   ベースケースは「ノードが null のとき深さ 0」。
//   このアプローチはコードが簡潔で、木構造の再帰的定義とそのまま対応するため理解しやすい。
// 時間計算量: O(n)  … 全ノードを 1 回ずつ訪問するため
// 空間計算量: O(h)  … 再帰呼び出しスタックの深さ = 木の高さ h（最悪 O(n)、バランスが取れていれば O(log n)）

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
  public int maxDepth(TreeNode root) {
    // ベースケース: 空のノードは深さ 0
    // 制約より根自体が null（ノード数 0）の場合もここで 0 が返る
    if (root == null) {
      return 0;
    }

    // 左右の部分木の最大深さをそれぞれ再帰的に算出
    int leftDepth = maxDepth(root.left);
    int rightDepth = maxDepth(root.right);

    // 自ノード分の 1 を加えて、深い側の値を返す
    return Math.max(leftDepth, rightDepth) + 1;
  }
}
