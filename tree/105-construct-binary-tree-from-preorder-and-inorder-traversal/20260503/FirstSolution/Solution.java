import java.util.HashMap;
import java.util.Map;

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
  private final Map<Integer, Integer> inorderIndexMap = new HashMap<>();
  private int preorderRootIndex = 0;

  // preorderはルート、左、右の順番になる
  // inorderはルートを境に左側がLeftTree、右側がRightTreeになる
  // 二分木を構築するのを再帰的に繰り返せばOK
  //
  public TreeNode buildTree(int[] preorder, int[] inorder) {

    // inorderでルートの位置をO(1)で探せるようにマップを作っておく
    for (int i = 0; i < inorder.length; i++) {
      inorderIndexMap.put(inorder[i], i);
    }

    return buildSubTree(preorder, 0, inorder.length - 1);
  }

  // サブツリーを構築するメソッド
  private TreeNode buildSubTree(int[] preorder, int inorderLeft, int inorderRight) {

    if (inorderLeft > inorderRight) {
      return null;
    }

    int rootValue = preorder[preorderRootIndex];
    preorderRootIndex++;

    TreeNode rootNode = new TreeNode(rootValue);
    int rootIndexOfInorder = inorderIndexMap.get(rootValue);

    rootNode.left = buildSubTree(preorder, inorderLeft, rootIndexOfInorder - 1);
    rootNode.right = buildSubTree(preorder, rootIndexOfInorder + 1, inorderRight);

    return rootNode;
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
