// 問題: 73. Set Matrix Zeroes
// アプローチ: 1 行目と 1 列目をマーカー領域として再利用し、どの行と列を 0 にするかを in-place で記録する
// 時間計算量: O(m * n)
// 空間計算量: O(1)
class Solution {
  public void setZeroes(int[][] matrix) {
    int rowCount = matrix.length;
    int columnCount = matrix[0].length;
    boolean firstRowHasZero = false;
    boolean firstColumnHasZero = false;

    // 1 列目は後で行マーカーとして使うので、
    // 元から 0 が含まれていたかを先に退避しておく。
    for (int row = 0; row < rowCount; row++) {
      if (matrix[row][0] == 0) {
        firstColumnHasZero = true;
        break;
      }
    }

    // 1 行目は後で列マーカーとして使うので、
    // 元から 0 が含まれていたかを先に退避しておく。
    for (int column = 0; column < columnCount; column++) {
      if (matrix[0][column] == 0) {
        firstRowHasZero = true;
        break;
      }
    }

    // 1 行目と 1 列目を「この行/列はあとで 0 にする」という印として使う。
    for (int row = 1; row < rowCount; row++) {
      for (int column = 1; column < columnCount; column++) {
        if (matrix[row][column] == 0) {
          matrix[row][0] = 0;
          matrix[0][column] = 0;
        }
      }
    }

    // 行マーカーを見て、0 にすべき行の内側をまとめて 0 にする。
    for (int row = 1; row < rowCount; row++) {
      if (matrix[row][0] != 0) {
        continue;
      }

      for (int column = 1; column < columnCount; column++) {
        matrix[row][column] = 0;
      }
    }

    // 列マーカーを見て、0 にすべき列の内側をまとめて 0 にする。
    for (int column = 1; column < columnCount; column++) {
      if (matrix[0][column] != 0) {
        continue;
      }

      for (int row = 1; row < rowCount; row++) {
        matrix[row][column] = 0;
      }
    }

    // 1 行目自身が元から 0 を含んでいたなら、最後に 1 行目全体を 0 にする。
    if (firstRowHasZero) {
      for (int column = 0; column < columnCount; column++) {
        matrix[0][column] = 0;
      }
    }

    // 1 列目自身が元から 0 を含んでいたなら、最後に 1 列目全体を 0 にする。
    if (firstColumnHasZero) {
      for (int row = 0; row < rowCount; row++) {
        matrix[row][0] = 0;
      }
    }
  }
}
