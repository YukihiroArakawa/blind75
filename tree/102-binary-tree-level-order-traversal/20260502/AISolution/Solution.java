// 問題: 102. Binary Tree Level Order Traversal
// アプローチ: BFS（幅優先探索）+ キュー
//   - レベル順走査は BFS の典型用途。キューに「次に訪れるノード」を入れて FIFO で取り出す
//   - 「同じレベルのノードをまとめる」ために、各イテレーションの先頭でキューサイズを記録し、
//     その個数だけ取り出して 1 階層分の List を作る
//   - 取り出すたびに左右の子をキューに追加することで、次のレベルのノードが自然に貯まっていく
// 時間計算量: O(n)
//   - 各ノードはキューに 1 回ずつ enqueue / dequeue されるため線形
// 空間計算量: O(n)
//   - キューに保持するのは最大で 1 レベル分のノード（最悪ケースで n/2 ≈ O(n)）
//   - 結果リストも全ノードを格納するため O(n)

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Definition for a binary tree node.
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

class Solution {
  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();

    // 空の木は空リストを返す（制約上ノード 0 個のケースがある）
    if (root == null) {
      return result;
    }

    // BFS 用キュー: ArrayDeque でも可だが、null を扱わないなら LinkedList でも問題ない
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      // 現在のレベルにあるノード数を先に固定する
      // ループ中に queue.size() を参照すると、子ノード追加で値が変動してしまうため
      int levelSize = queue.size();
      List<Integer> currentLevel = new ArrayList<>(levelSize);

      for (int i = 0; i < levelSize; i++) {
        TreeNode node = queue.poll();
        currentLevel.add(node.val);

        // 左→右の順で子を enqueue することで、次レベルも左→右の順序が保たれる
        if (node.left != null) {
          queue.offer(node.left);
        }
        if (node.right != null) {
          queue.offer(node.right);
        }
      }

      result.add(currentLevel);
    }

    return result;
  }
}
