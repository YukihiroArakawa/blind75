// 問題: 200. Number of Islands
// アプローチ: グリッドを走査し、未訪問の陸を見つけたら DFS でその島全体を塗りつぶして島数を 1 増やす
// 時間計算量: O(m * n)
// 空間計算量: O(m * n)
class Solution {
  private static final int[][] DIRECTIONS = {
      { 1, 0 },
      { -1, 0 },
      { 0, 1 },
      { 0, -1 }
  };

  public int numIslands(char[][] grid) {
    int rowCount = grid.length;
    int columnCount = grid[0].length;
    int islandCount = 0;

    for (int row = 0; row < rowCount; row++) {
      for (int column = 0; column < columnCount; column++) {
        if (grid[row][column] == '1') {
          islandCount++;
          sinkIsland(grid, row, column);
        }
      }
    }

    return islandCount;
  }

  private void sinkIsland(char[][] grid, int row, int column) {
    if (isOutOfBounds(grid, row, column)) {
      return;
    }

    if (grid[row][column] == '0') {
      return;
    }

    // 訪問済みの陸を水に変えて、同じ島を二重に数えないようにする。
    grid[row][column] = '0';

    for (int[] direction : DIRECTIONS) {
      int nextRow = row + direction[0];
      int nextColumn = column + direction[1];
      sinkIsland(grid, nextRow, nextColumn);
    }
  }

  private boolean isOutOfBounds(char[][] grid, int row, int column) {
    return row < 0 || row >= grid.length || column < 0 || column >= grid[0].length;
  }
}
