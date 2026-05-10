import java.util.HashMap;
import java.util.Map;

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

// 問題: 133. Clone Graph
// アプローチ: DFS と HashMap を使って、元ノード -> クローンノードの対応を保持しながらグラフを再帰的に複製する
// 時間計算量: O(V + E)
// 空間計算量: O(V)
class Solution {
  public Node cloneGraph(Node node) {
    if (node == null) {
      return null;
    }

    Map<Node, Node> originalToClone = new HashMap<>();
    return cloneNode(node, originalToClone);
  }

  private Node cloneNode(Node currentNode, Map<Node, Node> originalToClone) {
    // すでにコピー済みのノードなら、そのクローンを再利用する。
    // サイクルがあっても無限再帰にならない。
    if (originalToClone.containsKey(currentNode)) {
      return originalToClone.get(currentNode);
    }

    Node clonedNode = new Node(currentNode.val);
    originalToClone.put(currentNode, clonedNode);

    // 隣接ノードも順にクローンし、クローン側の neighbors に接続する。
    for (Node neighborNode : currentNode.neighbors) {
      clonedNode.neighbors.add(cloneNode(neighborNode, originalToClone));
    }

    return clonedNode;
  }
}
