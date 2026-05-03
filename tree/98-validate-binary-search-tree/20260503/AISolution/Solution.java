// 問題: 98. Validate Binary Search Tree
// アプローチ: DFS + 許容範囲（lower bound / upper bound）を再帰で引き継ぐ
//   各ノードについて「この値はどの範囲に入っていなければならないか」を持ちながら探索する。
//   左部分木に進むと上限は現在ノードの値になり、
//   右部分木に進むと下限は現在ノードの値になる。
//   どこか 1 箇所でも範囲を破れば BST ではない。
// 時間計算量: O(n)  … n はノード数。各ノードを 1 回ずつ訪問する
// 空間計算量: O(h)  … 再帰呼び出しスタックの深さ h。最悪で O(n)

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
    public boolean isValidBST(TreeNode root) {
        return isValidSubtree(root, null, null);
    }

    private boolean isValidSubtree(TreeNode node, Long lowerBound, Long upperBound) {
        // 空ノードは BST 条件を壊さないので true
        if (node == null) {
            return true;
        }

        long currentValue = node.val;

        // 下限以上でなければならないのではなく、「下限より厳密に大きい」必要がある
        if (lowerBound != null && currentValue <= lowerBound) {
            return false;
        }

        // 同様に、上限より厳密に小さい必要がある
        if (upperBound != null && currentValue >= upperBound) {
            return false;
        }

        // 左部分木は upperBound が現在値に更新される
        if (!isValidSubtree(node.left, lowerBound, currentValue)) {
            return false;
        }

        // 右部分木は lowerBound が現在値に更新される
        return isValidSubtree(node.right, currentValue, upperBound);
    }
}
