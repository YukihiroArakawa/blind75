import java.util.ArrayList;
import java.util.List;

// 入力: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
// adjList
// - 0 => 1, 2, 3
// - 1 => 0, 4
// - 2 => 0
// - 3 => 0
// - 4 => 1

class Solution {
  public boolean validTree(int n, int[][] edges) {

    // 木構造である場合、辺の数はnode数-1になる
    if (edges.length != n - 1) {
      return false;
    }

    // 隣接リストを作成して、ノード0から全てのノードを辿ることができるか調べる

    // 隣接リストの作成
    // 0 -> 1, 2, 3
    List<List<Integer>> adjList = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      adjList.add(new ArrayList<>());
    }
    for (int[] edge : edges) {
      adjList.get(edge[0]).add(edge[1]);
      adjList.get(edge[1]).add(edge[0]);
    }

    // 訪問済みかどうかを記録する配列を作成
    boolean[] visited = new boolean[n];

    // dfsして各ノードに訪問済みかどうかマークしていく
    dfs(visited, 0, adjList);

    for (boolean b : visited) {
      if (b == false) {
        return false;
      }
    }

    return true;
  }

  private void dfs(boolean[] visited, int currentNode, List<List<Integer>> adjList) {

    if (visited[currentNode]) {
      return;
    } else {
      visited[currentNode] = true;
    }

    List<Integer> neighbors = adjList.get(currentNode);

    for (Integer neighborNode : neighbors) {
      dfs(visited, neighborNode, adjList);
    }
  }

  public static void main(String[] args) {
    // 実行時は `java -ea Solution` のように -ea を付けて assert を有効化する。
    assert new Solution().validTree(5, new int[][] {
        { 0, 1 },
        { 0, 2 },
        { 0, 3 },
        { 1, 4 },
    });

    assert !new Solution().validTree(4, new int[][] {
        { 0, 1 },
        { 1, 2 },
        { 2, 0 }
    });

    assert !new Solution().validTree(5, new int[][] {
        { 0, 1 },
        { 1, 2 },
        { 2, 3 },
        { 1, 3 },
        { 1, 4 }
    });

    assert new Solution().validTree(1, new int[][] {});

    assert !new Solution().validTree(4, new int[][] {
        { 0, 1 },
        { 1, 2 },
        { 2, 0 }
    });

  }
}
