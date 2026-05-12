import java.util.ArrayList;
import java.util.List;

// 問題: 417. Pacific Atlantic Water Flow
// アプローチ: 太平洋側と大西洋側からそれぞれ逆向き DFS をして、到達可能なマスを記録し、両方から到達できるマスを答えにする
// 時間計算量: O(m * n)
// 空間計算量: O(m * n)
class Solution {
  private static final int[][] DIRECTIONS = {
      { 1, 0 },
      { -1, 0 },
      { 0, 1 },
      { 0, -1 }
  };

  public List<List<Integer>> pacificAtlantic(int[][] heights) {
    int rowCount = heights.length;
    int columnCount = heights[0].length;

    // 各マスが太平洋 / 大西洋へ到達可能かを別々に記録する。
    boolean[][] canReachPacific = new boolean[rowCount][columnCount];
    boolean[][] canReachAtlantic = new boolean[rowCount][columnCount];

    // 左端と右端から逆向き探索を始める。
    for (int row = 0; row < rowCount; row++) {
      dfs(heights, row, 0, canReachPacific);
      dfs(heights, row, columnCount - 1, canReachAtlantic);
    }

    // 上端と下端から逆向き探索を始める。
    for (int column = 0; column < columnCount; column++) {
      dfs(heights, 0, column, canReachPacific);
      dfs(heights, rowCount - 1, column, canReachAtlantic);
    }

    // 両方の海に到達できるマスだけを答えに集める。
    List<List<Integer>> result = new ArrayList<>();

    for (int row = 0; row < rowCount; row++) {
      for (int column = 0; column < columnCount; column++) {
        if (canReachPacific[row][column] && canReachAtlantic[row][column]) {
          List<Integer> cell = new ArrayList<>();
          cell.add(row);
          cell.add(column);
          result.add(cell);
        }
      }
    }

    return result;
  }

  private void dfs(int[][] heights, int row, int column, boolean[][] visited) {
    // 同じ海から見てすでに到達可能と分かっているマスは再探索しない。
    if (visited[row][column]) {
      return;
    }

    // 現在のマスはこの海へ到達可能。
    visited[row][column] = true;

    // 上下左右の隣接マスへ逆向きに広がる。
    for (int[] direction : DIRECTIONS) {
      int nextRow = row + direction[0];
      int nextColumn = column + direction[1];

      // 盤面の外は探索しない。
      if (isOutOfBounds(heights, nextRow, nextColumn)) {
        continue;
      }

      // すでに訪問済みのマスは探索しない。
      if (visited[nextRow][nextColumn]) {
        continue;
      }

      // 逆向き探索なので、現在地以上の高さにだけ進める。
      if (heights[nextRow][nextColumn] >= heights[row][column]) {
        continue;
      }

      dfs(heights, nextRow, nextColumn, visited);
    }
  }

  private boolean isOutOfBounds(int[][] heights, int row, int column) {
    return row < 0 || row >= heights.length || column < 0 || column >= heights[0].length;
  }
}
