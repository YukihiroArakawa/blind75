import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution {
  public Node cloneGraph(Node node) {
    if (node == null) {
      return null;
    }

    // ノードをDFSで再コピーする
    HashMap<Node, Node> originalToClone = new HashMap<>();
    return cloneNode(node, originalToClone);
  }

  private Node cloneNode(Node originalNode, HashMap<Node, Node> originalToClone) {
    if (originalToClone.containsKey(originalNode)) {
      return originalToClone.get(originalNode);
    }

    Node cloneNode = new Node(originalNode.val);
    originalToClone.put(originalNode, cloneNode);

    for (Node originalNeighborNode : originalNode.neighbors) {
      cloneNode.neighbors.add(cloneNode(originalNeighborNode, originalToClone));
    }

    return cloneNode;
  }
}

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
