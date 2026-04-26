// 問題: 226. Invert Binary Tree
// アプローチ: 再帰（深さ優先探索、DFS）による左右部分木のスワップ
//   各ノードについて、左右の子を入れ替える操作を再帰的に適用するだけで木全体が反転する。
//   処理の流れ:
//     (1) ノードが null なら何もせず null を返す（再帰の基底ケース）
//     (2) 左部分木・右部分木をそれぞれ再帰的に反転する
//     (3) 反転済みの左右を、現在のノードの右・左に入れ替えて差し込む
//   ポイントは「子の参照を入れ替える前に再帰呼び出しの結果を一時変数に退避する」こと。
//   先に root.left を書き換えてしまうと、続く右部分木の再帰で本来とは別の木を見てしまう。
//   この実装ではポストオーダー（子を処理してから自分を処理）で安全にスワップしている。
// 時間計算量: O(n)  … n はノード数。各ノードを 1 回ずつ訪問する
// 空間計算量: O(h)  … 再帰呼び出しスタックの深さ = 木の高さ h
//                     最悪（一直線の木）で O(n)、バランス木で O(log n)

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode invertTree(TreeNode root) {
        // (1) 基底ケース: 空のノードはそのまま返す
        //     これにより呼び出し側で null チェックを書かずに済む
        if (root == null) {
            return null;
        }

        // (2) 左右の部分木を先に再帰で反転する
        //     一時変数に退避するのは、次の (3) で root.left/right を
        //     書き換える前に、元の参照に対する再帰結果を確定させるため
        TreeNode invertedLeft = invertTree(root.left);
        TreeNode invertedRight = invertTree(root.right);

        // (3) 左右を入れ替えて自ノードに差し込む
        //     ここが「反転」の本体。子は既に反転済みなので、
        //     自ノードでは参照のスワップだけで木全体の反転が完了する
        root.left = invertedRight;
        root.right = invertedLeft;

        return root;
    }
}
