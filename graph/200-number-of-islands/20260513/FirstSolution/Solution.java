class Solution {
  public int numIslands(char[][] grid) {
    int rowSize = grid.length;
    int columnSize = grid[0].length;
    int islandCount = 0;

    // gridのマスを全てループ
    for (int row = 0; row < rowSize; row++) {
      for (int column = 0; column < columnSize; column++) {
        // 1があれば探索開始
        if (grid[row][column] == '1') {
          islandCount++;
          checkIsland(grid, row, column);
        }
      }
    }

    return islandCount;
  }

  private void checkIsland(char[][] grid, int row, int column) {
    int[][] directions = {
        { 0, 1 }, // 右
        { 0, -1 }, // 左
        { 1, 0 }, // 下
        { -1, 0 }, // 上
    };

    if (row < 0 || row >= grid.length || column < 0 || column >= grid[0].length) {
      return;
    }

    if (grid[row][column] == '0') {
      return;
    }

    grid[row][column] = '0';

    for (int[] direction : directions) {
      int nextRow = row + direction[0];
      int nextCol = column + direction[1];
      checkIsland(grid, nextRow, nextCol);
    }
  }
}
