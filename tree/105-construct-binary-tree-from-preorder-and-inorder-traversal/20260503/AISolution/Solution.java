import java.util.HashMap;
import java.util.Map;

// 問題: 105. Construct Binary Tree from Preorder and Inorder Traversal
// アプローチ: 再帰 + inorder の添字マップ
//   preorder では「現在の部分木の根」が先頭に現れる。
//   その根の値が inorder のどこにあるか分かれば、
//   左側が左部分木、右側が右部分木の範囲だと一意に分割できる。
//   inorder の位置探索を毎回線形に行うと O(n^2) になるため、
//   先に値 -> 添字のマップを作って O(1) で参照する。
// 時間計算量: O(n)  … n はノード数。各値をマップ化し、各ノードを 1 回ずつ構築する
// 空間計算量: O(n)  … 添字マップと再帰呼び出しスタックを含む

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
  private final Map<Integer, Integer> inorderIndexByValue = new HashMap<>();
  private int preorderIndex = 0;

  public TreeNode buildTree(int[] preorder, int[] inorder) {
    for (int index = 0; index < inorder.length; index++) {
      inorderIndexByValue.put(inorder[index], index);
    }

    return buildSubtree(preorder, 0, inorder.length - 1);
  }

  private TreeNode buildSubtree(int[] preorder, int inorderLeft, int inorderRight) {
    // inorder 上の範囲が空なら、この部分木にノードは存在しない
    if (inorderLeft > inorderRight) {
      return null;
    }

    // preorder では現在位置が部分木の根を指す
    int rootValue = preorder[preorderIndex];
    preorderIndex++;

    TreeNode root = new TreeNode(rootValue);
    int inorderRootIndex = inorderIndexByValue.get(rootValue);

    // inorder の左範囲が左部分木、右範囲が右部分木になる
    root.left = buildSubtree(preorder, inorderLeft, inorderRootIndex - 1);
    root.right = buildSubtree(preorder, inorderRootIndex + 1, inorderRight);

    return root;
  }
}
