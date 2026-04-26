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
  // これも再帰ベースで同一性判定したらよさそう
  // rootが少しずつずれていくみたいな感じになるのかな
  // 2階層目で部分木でなくても、3階層目から部分木とかありうるし
  public boolean isSubtree(TreeNode root, TreeNode subRoot) {

    if (root == null) {
      return false;
    }

    if (isSameTree(root, subRoot)) {
      return true;
    }

    return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
  }

  private static boolean isSameTree(TreeNode nodeA, TreeNode nodeB) {
    if (nodeA == null && nodeB == null) {
      return true;
    }

    if (nodeA == null || nodeB == null) {
      return false;
    }

    if (nodeA.val != nodeB.val) {
      return false;
    }

    boolean left = isSameTree(nodeA.left, nodeB.left);
    boolean right = isSameTree(nodeA.right, nodeB.right);

    return left && right;
  }

  public static void main(String[] args) {

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
