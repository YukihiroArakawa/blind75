import java.util.ArrayList;
import java.util.List;

// 問題: 323. Number of Connected Components in an Undirected Graph
// アプローチ: 隣接リストを作り、未訪問ノードを起点に DFS を始めるたびに連結成分数を 1 増やす
// 時間計算量: O(n + edges.length)
// 空間計算量: O(n + edges.length)
class Solution {
  public int countComponents(int n, int[][] edges) {
    List<List<Integer>> adjacencyList = buildAdjacencyList(n, edges);
    boolean[] visited = new boolean[n];
    int componentCount = 0;

    for (int node = 0; node < n; node++) {
      if (visited[node]) {
        continue;
      }

      componentCount++;
      dfs(node, adjacencyList, visited);
    }

    return componentCount;
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
