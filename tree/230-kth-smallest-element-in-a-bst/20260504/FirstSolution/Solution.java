
class Solution {

  private int count = 0;
  private int result = 0;

  public int kthSmallest(TreeNode root, int k) {
    this.count = k;
    recur(root);
    return this.result;
  }

  // ひたすら再帰で左側まで掘っていってそこからカウントダウンしていく
  private void recur(TreeNode node) {
    if (node == null || this.count == 0) {
      return;
    }

    recur(node.left);

    count--;

    if (count == 0) {
      this.result = node.val;
    }

    recur(node.right);

    return;
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
