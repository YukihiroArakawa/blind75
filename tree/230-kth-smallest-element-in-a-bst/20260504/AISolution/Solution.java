// 問題: 230. Kth Smallest Element in a BST
// アプローチ: BST の中順走査 (in-order traversal) を反復的に行い、k 番目に取り出した値を返す
// BST の中順走査は値を昇順に並べた順序で訪問するため、k 番目に訪れたノードの値が答えとなる
// 反復版を採用する理由: 早期終了がしやすく (k 個目に到達した時点で探索を打ち切れる)、
// 再帰よりもスタックオーバーフローのリスクが低い
// 時間計算量: O(H + k) — 最初に左端まで降りるのに O(H)、その後 k ステップ進む
// 空間計算量: O(H) — 明示的なスタックに高さ分のノードを保持する
import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
  public int kthSmallest(TreeNode root, int k) {
    // 中順走査の途中状態を保持するためのスタック
    // ArrayDeque は java.util.Stack より高速で推奨される実装
    Deque<TreeNode> stack = new ArrayDeque<>();
    TreeNode currentNode = root;
    int visitedCount = 0;

    // currentNode が null かつスタックが空になったらツリー全体を走査し終えたことを意味する
    while (currentNode != null || !stack.isEmpty()) {
      // 現在のノードから左の子をたどれるだけたどり、通り道のノードをスタックに積む
      // BST では左部分木の方が値が小さいため、まず最も小さい値に到達するための準備
      while (currentNode != null) {
        stack.push(currentNode);
        currentNode = currentNode.left;
      }

      // スタックの先頭が「現時点で未訪問のノードのうち最も小さい値」
      currentNode = stack.pop();
      visitedCount++;

      // k 番目に訪れたノードの値が答え (1-indexed)
      if (visitedCount == k) {
        return currentNode.val;
      }

      // 左部分木と現在のノードを処理し終えたので、右部分木を同じ手順で処理する
      currentNode = currentNode.right;
    }

    // 制約上、必ず 1 <= k <= n なのでここには到達しない
    // 到達した場合は入力が想定外であることを示す
    throw new IllegalArgumentException("k is out of range");
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
