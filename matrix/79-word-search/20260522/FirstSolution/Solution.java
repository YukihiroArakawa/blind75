class Solution {
  public boolean exist(char[][] board, String word) {
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[0].length; c++) {
        if (dfs(board, word, r, c, 0)) {
          return true;
        }
      }
    }

    return false;

  }

  private boolean dfs(char[][] board, String word, int row, int col, int index) {
    // 終了条件
    if (index == word.length()) {
      return true;
    }

    int rowCount = board.length;
    int colCount = board[0].length;

    // 範囲外だったらfalse
    if (row >= rowCount || row < 0 || col >= colCount || col < 0) {
      return false;
    }

    // 対象マスと文字が一致しない場合はfalse
    if (board[row][col] != word.charAt(index)) {
      return false;
    }

    char originalChar = board[row][col];
    board[row][col] = '#';

    // 上下左右に探索
    boolean found = dfs(board, word, row + 1, col, index + 1)
        || dfs(board, word, row - 1, col, index + 1)
        || dfs(board, word, row, col + 1, index + 1)
        || dfs(board, word, row, col - 1, index + 1);

    // 元に戻す
    board[row][col] = originalChar;

    return found;
  }
}
