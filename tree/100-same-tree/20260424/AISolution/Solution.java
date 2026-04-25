// 問題: 100. Same Tree
// アプローチ: 再帰（深さ優先探索、DFS）による同時走査
//   2 つの木を同じ経路で同時にたどりながら、「ノードの存在状態」と「値」が一致するかを確認する。
//   判定ロジックは次の 3 段階:
//     (1) 両方 null → 構造の末端が一致しているので true
//     (2) 片方だけ null → 構造が非対称 → false
//     (3) 両方ノードあり → 値が等しく、かつ 左部分木同士・右部分木同士がともに同じ → true
//   このアプローチは木の再帰的定義に自然に沿うため、コードが短く見通しが良い。
// 時間計算量: O(n)  … n は小さい方の木のノード数（不一致が見つかれば即 return で打ち切り、
//                     一致する場合は全ノードを 1 回ずつ訪問）
// 空間計算量: O(h)  … 再帰呼び出しスタックの深さ = 木の高さ h（最悪 O(n)、バランス木で O(log n)）

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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // (1) 両方 null: 同じ位置で同時に末端に到達 → 一致
        if (p == null && q == null) {
            return true;
        }

        // (2) 片方だけ null: 構造が異なる
        //     ここに来た時点で「両方 null」は (1) で弾かれているので、
        //     「どちらか一方でも null」= 「片方だけ null」と判定できる
        if (p == null || q == null) {
            return false;
        }

        // (3) 両方ノードあり: 値の一致 AND 左右の部分木が同じ、の全てを満たす必要がある
        //     && は短絡評価なので、値が違った時点で再帰呼び出しは行われず即 false
        return p.val == q.val
                && isSameTree(p.left, q.left)
                && isSameTree(p.right, q.right);
    }
}
