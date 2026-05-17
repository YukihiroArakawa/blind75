class Solution {
  // 回答方針
  // 1. 追加の空間計算量をO(1)で解くために、入力値の空間を利用する
  // 2. １行目・１列目に0の有無を記録し、それを用いて置換する
  // 3. ただし１行１列のマスについてはあとで置換するようにしないと、記録を上書きしてしまう
  public void setZeroes(int[][] matrix) {

    // 1行目のマスが0かどうか記録
    boolean isFirstRowZero = false;
    for (int c = 0; c < matrix[0].length; c++) {
      if (matrix[0][c] == 0) {
        isFirstRowZero = true;
        break;
      }
    }

    // １列目のマスが0かどうか記録
    boolean isFirstColZero = false;
    for (int r = 0; r < matrix.length; r++) {
      if (matrix[r][0] == 0) {
        isFirstColZero = true;
        break;
      }
    }

    // 1行目・1列目以外のマスに0があるかチェック
    for (int r = 1; r < matrix.length; r++) {
      for (int c = 1; c < matrix[0].length; c++) {
        if (matrix[r][c] == 0) {
          matrix[r][0] = 0;
          matrix[0][c] = 0;
        }
      }
    }

    // row replacement
    for (int r = 1; r < matrix.length; r++) {
      if (matrix[r][0] != 0) {
        continue;
      }

      for (int c = 1; c < matrix[0].length; c++) {
        matrix[r][c] = 0;
      }
    }

    // column replacement
    for (int c = 1; c < matrix[0].length; c++) {
      if (matrix[0][c] != 0) {
        continue;
      }

      for (int r = 1; r < matrix.length; r++) {
        matrix[r][c] = 0;
      }
    }

    // first row replacement
    if (isFirstRowZero) {
      for (int c = 0; c < matrix[0].length; c++) {
        matrix[0][c] = 0;
      }
    }

    // first col replacement
    if (isFirstColZero) {
      for (int r = 0; r < matrix.length; r++) {
        matrix[r][0] = 0;
      }
    }
  }
}
