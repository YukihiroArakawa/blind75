class Solution {
  // 正方形
  // 元のマトリクスを変える
  // 転置ー＞左右入れ替えを段階的に実施することでインプレースで入れ替え可能
  public void rotate(int[][] matrix) {
    // 転置
    for (int r = 0; r < matrix.length; r++) {
      for (int c = 0; c < matrix[0].length; c++) {
        int tmp = matrix[r][c];
        matrix[r][c] = matrix[c][r];
        matrix[c][r] = tmp;
      }
    }

    // 左右入れ替え
    int colSize = matrix[0].length;
    for (int r = 0; r < matrix.length; r++) {
      for (int c = 0; c < matrix[0].length / 2; c++) {
        int tmp = matrix[r][c];
        matrix[r][c] = tmp;
        matrix[r][colSize - c - 1] = tmp;
      }
    }
  }
}
