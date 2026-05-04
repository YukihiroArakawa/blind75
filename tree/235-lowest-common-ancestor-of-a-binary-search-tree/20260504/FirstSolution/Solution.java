
class Solution {
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

    TreeNode currentNode = root;

    while (currentNode != null) {

      // 左側に固まっている
      if (currentNode.val > p.val && currentNode.val > q.val) {
        currentNode = currentNode.left;
      } else if (currentNode.val < p.val && currentNode.val < q.val) {
        currentNode = currentNode.right;
      } else {
        return currentNode;
      }
    }

    return null;
  }

  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }
}
