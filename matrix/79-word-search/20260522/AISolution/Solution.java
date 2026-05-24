// 問題: 79. Word Search
// アプローチ: 各マスを開始点として DFS を行い、訪問中のマスを一時的に印付けしてバックトラッキングする
// 時間計算量: O(m * n * 4^L)
// 空間計算量: O(L)
class Solution {
  public boolean exist(char[][] board, String word) {
    int rowCount = board.length;
    int columnCount = board[0].length;

    for (int row = 0; row < rowCount; row++) {
      for (int column = 0; column < columnCount; column++) {
        if (search(board, word, row, column, 0)) {
          return true;
        }
      }
    }

    return false;
  }

  private boolean search(char[][] board, String word, int row, int column, int index) {
    if (index == word.length()) {
      return true;
    }

    int rowCount = board.length;
    int columnCount = board[0].length;

    if (row < 0 || row >= rowCount || column < 0 || column >= columnCount) {
      return false;
    }

    if (board[row][column] != word.charAt(index)) {
      return false;
    }

    char originalCharacter = board[row][column];
    board[row][column] = '#';

    // 今いるマスを使ったうえで、上下左右のどこかに次の文字が続けば成功。
    boolean found = search(board, word, row + 1, column, index + 1)
        || search(board, word, row - 1, column, index + 1)
        || search(board, word, row, column + 1, index + 1)
        || search(board, word, row, column - 1, index + 1);

    // 他の探索経路でも使えるように、帰りがけに元の文字へ戻す。
    board[row][column] = originalCharacter;
    return found;
  }
}
