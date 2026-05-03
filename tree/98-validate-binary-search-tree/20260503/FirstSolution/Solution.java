
class Solution {
  public boolean isValidBST(TreeNode root) {
    return isValidSubtree(root, null, null);
  }

  private boolean isValidSubtree(TreeNode node, Long lowerBound, Long upperBound) {
    if (node == null) {
      return true;
    }

    long currentValue = node.val;

    // 現在のノードの値が制約を満たすかチェック
    if (lowerBound != null && currentValue <= lowerBound) {
      return false;
    }
    if (upperBound != null && currentValue >= upperBound) {
      return false;
    }

    // 左右のノードについても条件を満たすかチェック
    if (!isValidSubtree(node.left, lowerBound, currentValue)) {
      return false;
    }

    return isValidSubtree(node.right, currentValue, upperBound);
  }

  public class TreeNode {
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
