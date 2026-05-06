// 問題: 98. Validate Binary Search Tree
// アプローチ: 各ノードが「取りうる値の下限と上限」の範囲内にあるかを深さ優先探索で確認する
// 時間計算量: O(n)
// 空間計算量: O(h)  (h は木の高さ。再帰呼び出しスタック分)
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public boolean isValidBST(TreeNode root) {
        return isValidSubtree(root, null, null);
    }

    private boolean isValidSubtree(TreeNode currentNode, Long lowerBound, Long upperBound) {
        if (currentNode == null) {
            return true;
        }

        long currentValue = currentNode.val;

        // 祖先ノードから受け継いだ範囲を破っていないかを確認する。
        if (lowerBound != null && currentValue <= lowerBound) {
            return false;
        }
        if (upperBound != null && currentValue >= upperBound) {
            return false;
        }

        // 左部分木では「現在値より小さい」、右部分木では「現在値より大きい」を追加条件にする。
        return isValidSubtree(currentNode.left, lowerBound, currentValue)
            && isValidSubtree(currentNode.right, currentValue, upperBound);
    }
}
