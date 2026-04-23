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
  public int maxDepth(TreeNode root) {
    // nullの場合は0を返す
    if (root == null) {
      return 0;
    }

    // 左右ノードで探索
    int left = maxDepth(root.left);
    int right = maxDepth(root.right);

    // 深い方の深さ+1ノード(自分の階層)を返す
    return Math.max(left, right) + 1;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();

    // --- Example 1: [3,9,20,null,null,15,7] -> 期待値 3 ---
    // 3
    // / \
    // 9 20
    // / \
    // 15 7
    TreeNode example1 = new TreeNode(3,
        new TreeNode(9),
        new TreeNode(20, new TreeNode(15), new TreeNode(7)));
    check("Example 1", solution.maxDepth(example1), 3);

    // --- Example 2: [1,null,2] -> 期待値 2 ---
    // 1
    // \
    // 2
    TreeNode example2 = new TreeNode(1, null, new TreeNode(2));
    check("Example 2", solution.maxDepth(example2), 2);

    // --- Edge 1: 空の木 (null) -> 期待値 0 ---
    // 制約で「ノード数 0」が許容されているので境界値として確認
    check("Edge: empty tree", solution.maxDepth(null), 0);

    // --- Edge 2: 単一ノード [5] -> 期待値 1 ---
    // ルートだけのとき「自分自身の分」の +1 が効いているかを確認
    check("Edge: single node", solution.maxDepth(new TreeNode(5)), 1);

    // --- Edge 3: 左に一直線 [1,2,3,null,null] -> 期待値 3 ---
    // 1
    // /
    // 2
    // /
    // 3
    // バランスが崩れたケース。再帰の深さが最悪になる形
    TreeNode leftSkewed = new TreeNode(1,
        new TreeNode(2, new TreeNode(3), null),
        null);
    check("Edge: left-skewed", solution.maxDepth(leftSkewed), 3);

    // --- Edge 4: 完全二分木 [1,2,3,4,5,6,7] -> 期待値 3 ---
    // 1
    // / \
    // 2 3
    // / \ / \
    // 4 5 6 7
    TreeNode balanced = new TreeNode(1,
        new TreeNode(2, new TreeNode(4), new TreeNode(5)),
        new TreeNode(3, new TreeNode(6), new TreeNode(7)));
    check("Edge: balanced", solution.maxDepth(balanced), 3);
  }

  // テスト結果を PASS/FAIL 形式で出力するヘルパー
  private static void check(String label, int actual, int expected) {
    String status = actual == expected ? "PASS" : "FAIL";
    System.out.println(status + " | " + label + " | expected=" + expected + ", actual=" + actual);
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
