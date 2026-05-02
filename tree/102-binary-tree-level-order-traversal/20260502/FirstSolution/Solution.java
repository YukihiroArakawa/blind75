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
  // binary treeのルールはなんだっけ？
  // 二分木というだけでルート、左・右という順序だけ守れば良いはず
  // 再帰でうまいことできそうな気がする
  // ひとつ下の階層の左右のValを返すメソッドを再帰的に呼び出すのは？
  // キューで考え直す
  //
  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();

    if (root == null) {
      return result;
    }

    // キューに探索する階層のノードを入れて、それを繰り返し処理する
    // 1) root => left, right
    // 2) left, right => left.left, left.rgiht , right.left, right.right

    Queue<TreeNode> currentQueue = new LinkedList<>();
    currentQueue.offer(root);
    while (!currentQueue.isEmpty()) {
      int currentQueueSize = currentQueue.size();

      List<Integer> currentList = new ArrayList<>();
      for (int i = 0; i < currentQueueSize; i++) {
        TreeNode node = currentQueue.poll();
        currentList.add(node.val);

        if (node.left != null) {
          currentQueue.offer(node.left);
        }

        if (node.right != null) {
          currentQueue.offer(node.right);
        }
      }

      result.add(currentList);
    }

    return result;
  }

}
