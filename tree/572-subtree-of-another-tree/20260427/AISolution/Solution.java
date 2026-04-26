// 問題: 572. Subtree of Another Tree
// アプローチ: DFS による全探索 + 木同士の同型判定
//   - root の各ノードを「subRoot と一致するか」の起点として再帰的に試す
//   - 一致判定（isSameTree）は両方の木を同時に辿って構造と値が完全一致するかを確認
// 時間計算量: O(m * n)
//   - m = root のノード数, n = subRoot のノード数
//   - 最悪ケースでは root の各ノードに対して subRoot 全体を比較する
// 空間計算量: O(m + n)
//   - 再帰呼び出しスタックの深さ。バランスしていれば O(log m + log n)、
//     偏った木の場合は O(m + n) になる
class Solution {
  public boolean isSubtree(TreeNode root, TreeNode subRoot) {
    // root が空なら、subRoot も空でない限り部分木として一致しえない
    // 制約上 subRoot は最低 1 ノードあるが、再帰中に root 側が null まで到達するケースに備える
    if (root == null) {
      return false;
    }

    // 現在のノードを起点として subRoot と完全一致するかを判定
    // 一致しなければ、左右の子を起点に再帰的に探索する
    if (isSameTree(root, subRoot)) {
      return true;
    }
    return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
  }

  // 2 つの木が構造・値ともに完全一致するかを判定するヘルパー
  // 100. Same Tree と同じロジック
  private boolean isSameTree(TreeNode left, TreeNode right) {
    // 両方 null なら同一（葉の先まで一致）
    if (left == null && right == null) {
      return true;
    }
    // 片方だけ null なら構造が異なるので不一致
    if (left == null || right == null) {
      return false;
    }
    // 値が違えば不一致
    if (left.val != right.val) {
      return false;
    }
    // 左右の部分木を再帰的に比較
    return isSameTree(left.left, right.left) && isSameTree(left.right, right.right);
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
