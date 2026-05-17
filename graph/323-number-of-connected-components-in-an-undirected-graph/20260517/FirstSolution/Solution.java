import java.util.ArrayList;
import java.util.List;

class Solution {
  // 隣接リストを作って、探索を繰り返して、いい感じにできないか？？
  public int countComponents(int n, int[][] edges) {

    // 隣接リスト作成
    List<List<Integer>> adjList = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      adjList.add(new ArrayList<>());
    }
    for (int i = 0; i < edges.length; i++) {
      int fromNode = edges[i][0];
      int toNode = edges[i][1];
      adjList.get(fromNode).add(toNode);
      adjList.get(toNode).add(fromNode);
    }

    // 探索済みノードの記録リストを作成
    boolean[] visited = new boolean[n];
    for (int i = 0; i < n; i++) {
      visited[i] = false;
    }

    // 繰り返し処理で未探索ノードからDFSしてノードを記録する
    int result = 0;
    for (int i = 0; i < n; i++) {
      if (visited[i]) {
        continue;
      }

      result++;
      // 新規ノードから探索
      dfs(visited, adjList, i);
    }

    return result;
  }

  private void dfs(boolean[] visited, List<List<Integer>> adjList, int currentNode) {

    if (visited[currentNode]) {
      return;
    }

    visited[currentNode] = true;

    List<Integer> adjNodes = adjList.get(currentNode);

    for (Integer adjNode : adjNodes) {
      dfs(visited, adjList, adjNode);
    }
  }

  public static void main(String[] args) {
    Solution solution = new Solution();

    printTestResult(solution.countComponents(5, new int[][] {
        { 0, 1 },
        { 1, 2 },
        { 3, 4 }
    }), 2);

    printTestResult(solution.countComponents(5, new int[][] {
        { 0, 1 },
        { 1, 2 },
        { 2, 3 },
        { 3, 4 }
    }), 1);

    printTestResult(solution.countComponents(5, new int[][] {}), 5);

    printTestResult(solution.countComponents(4, new int[][] {
        { 0, 1 },
        { 2, 3 }
    }), 2);

    printTestResult(solution.countComponents(1, new int[][] {}), 1);

    printTestResult(solution.countComponents(2, new int[][] {}), 2);

    printTestResult(solution.countComponents(2, new int[][] {
        { 0, 1 }
    }), 1);

    printTestResult(solution.countComponents(6, new int[][] {
        { 0, 1 },
        { 1, 2 },
        { 3, 4 }
    }), 3);
  }

  private static void printTestResult(int actual, int expected) {
    System.out.println("expected = " + expected + ", actual = " + actual);
  }
}
