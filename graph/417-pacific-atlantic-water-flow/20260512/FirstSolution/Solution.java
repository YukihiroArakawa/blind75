import java.util.ArrayList;
import java.util.List;

class Solution {

  // 海側からこの陸が海に到達可能か調べていくことで重複して探索することを防ぐ？
  public List<List<Integer>> pacificAtlantic(int[][] heights) {

    int rowCount = heights.length;
    int columnCount = heights[0].length;

    // 各マスが太平洋/大西洋に到達可能か記録する
    // 後々両方を照らして両方到達するマスを特定するのに利用する
    boolean[][] canReachPacific = new boolean[rowCount][columnCount];
    boolean[][] canReachAtlantic = new boolean[rowCount][columnCount];

    // 左端と右端から探索を始める
    for (int row = 0; row < rowCount; row++) {
      // 左端は太平洋側
      dfs(heights, row, 0, canReachPacific);
      // 右端は大西洋側
      dfs(heights, row, columnCount - 1, canReachAtlantic);
    }

    // 上端と下端から探索
    for (int column = 0; column < columnCount; column++) {
      // 上端は太平洋側
      dfs(heights, 0, column, canReachPacific);
      // 下端は大西洋側
      dfs(heights, rowCount - 1, column, canReachAtlantic);

    }

    // 両方の海に到達できるマスを格納
    List<List<Integer>> result = new ArrayList<>();

    for (int row = 0; row < rowCount; row++) {
      for (int column = 0; column < columnCount; column++) {
        if (canReachPacific[row][column] && canReachAtlantic[row][column]) {
          List<Integer> cell = List.of(row, column);
          result.add(cell);
        }
      }
    }

    return result;
  }

  private void dfs(int[][] heights, int row, int column, boolean[][] visited) {
    int[][] directions = {
        { 1, 0 },
        { -1, 0 },
        { 0, 1 },
        { 0, -1 }
    };

    if (visited[row][column]) {
      return;
    }

    visited[row][column] = true;

    // そのマスを起点に上下左右のマスが到達可能か探索する
    for (int[] direction : directions) {
      int nextRow = row + direction[0];
      int nextColumn = column + direction[1];

      // 盤面の外は探索しないのでスキップ
      if (isOutOfBounds(heights, nextRow, nextColumn)) {
        continue;
      }

      // すでに訪問済みのマスは探索しない
      if (visited[nextRow][nextColumn]) {
        continue;
      }

      // 海側が次のマスより高さが同じ以上の場合のみ探索対象とする
      if (heights[row][column] > heights[nextRow][nextColumn]) {
        continue;
      }

      // 探索
      dfs(heights, nextRow, nextColumn, visited);
    }

  }

  private boolean isOutOfBounds(int[][] heights, int nextRow, int nextColumn) {

    if (nextRow < 0)
      return true;

    if (nextColumn < 0)
      return true;

    if (nextRow >= heights.length)
      return true;

    if (nextColumn >= heights[0].length)
      return true;

    return false;
  }
}
