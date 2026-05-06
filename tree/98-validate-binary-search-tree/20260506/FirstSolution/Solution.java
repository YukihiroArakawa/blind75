
class Solution {

  // node in left subtree < tree node
  // node in right subtree > tree node
  // 各ノードが上記の制約に合致するかチェックする
  // あるノードに対して、左側のノードが制約を満たすか、右側が制約を満たすかを再帰的にチェックすればよさそう
  public boolean isValidBST(TreeNode root) {
    return isValidSubtree(root, Long.MIN_VALUE, Long.MAX_VALUE);
  }

  private boolean isValidSubtree(TreeNode root, Long lowerBound, Long upperBound) {
    // ベースケース:
    if (root == null) {
      return true;
    }

    // ノードが範囲内であるかチェック
    if (!(root.val > lowerBound && root.val < upperBound)) {
      return false;
    }

    // 左右に下ってチェック
    Long rootVal = Long.valueOf(root.val);
    return isValidSubtree(root.left, lowerBound, rootVal) && isValidSubtree(root.right, rootVal, upperBound);
  }

  class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }
}
