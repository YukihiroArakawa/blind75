import java.util.ArrayList;
import java.util.List;

// 問題: 261. Graph Valid Tree
// アプローチ: まず辺数が n - 1 かを確認し、その後に隣接リストを作って DFS で全ノードが連結か調べる
// 時間計算量: O(n + edges.length)
// 空間計算量: O(n + edges.length)
class Solution {
  public boolean validTree(int n, int[][] edges) {
    // 木なら辺数は必ず n - 1 本なので、ここを満たさない時点で不成立。
    if (edges.length != n - 1) {
      return false;
    }

    List<List<Integer>> adjacencyList = buildAdjacencyList(n, edges);
    boolean[] visited = new boolean[n];
    dfs(0, adjacencyList, visited);

    // 閉路なしは辺数チェックで保証できているので、
    // あとは全ノードに到達できたかどうかを見れば木か判定できる。
    for (boolean nodeVisited : visited) {
      if (!nodeVisited) {
        return false;
      }
    }

    return true;
  }

  private List<List<Integer>> buildAdjacencyList(int n, int[][] edges) {
    List<List<Integer>> adjacencyList = new ArrayList<>();
    for (int node = 0; node < n; node++) {
      adjacencyList.add(new ArrayList<>());
    }

    for (int[] edge : edges) {
      int fromNode = edge[0];
      int toNode = edge[1];
      adjacencyList.get(fromNode).add(toNode);
      adjacencyList.get(toNode).add(fromNode);
    }

    return adjacencyList;
  }

  private void dfs(int currentNode, List<List<Integer>> adjacencyList, boolean[] visited) {
    if (visited[currentNode]) {
      return;
    }

    visited[currentNode] = true;

    for (int neighborNode : adjacencyList.get(currentNode)) {
      dfs(neighborNode, adjacencyList, visited);
    }
  }
}
