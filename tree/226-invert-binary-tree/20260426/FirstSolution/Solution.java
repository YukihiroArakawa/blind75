
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
  // １つずつたどっていく
  // 同じ階層の逆側に配置する <- 位置関係を把握するのがむずい、、？
  // 再帰でうまいこと処理できないか？ 再帰で左右を逆にしたツリーを返すようにしていけば最終的にTreeNodeを返せない？
  public TreeNode invertTree(TreeNode root) {
    if (root == null)
      return null;

    TreeNode left = invertTree(root.left);
    TreeNode right = invertTree(root.right);

    TreeNode InvertedNode = new TreeNode(
        root.val,
        right,
        left);

    return InvertedNode;
  }
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
