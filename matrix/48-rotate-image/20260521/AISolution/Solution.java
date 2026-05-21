// 問題: 48. Rotate Image
// アプローチ: 行列を転置してから各行を左右反転し、in-place で時計回り 90 度回転を実現する
// 時間計算量: O(n^2)
// 空間計算量: O(1)
class Solution {
  public void rotate(int[][] matrix) {
    int size = matrix.length;

    // 左下と右上の要素を入れ替えながら転置する。
    for (int row = 0; row < size; row++) {
      for (int column = row + 1; column < size; column++) {
        int temporaryValue = matrix[row][column];
        matrix[row][column] = matrix[column][row];
        matrix[column][row] = temporaryValue;
      }
    }

    // 各行を左右反転すると、転置後の行列が時計回り 90 度回転になる。
    for (int row = 0; row < size; row++) {
      int leftColumn = 0;
      int rightColumn = size - 1;

      while (leftColumn < rightColumn) {
        int temporaryValue = matrix[row][leftColumn];
        matrix[row][leftColumn] = matrix[row][rightColumn];
        matrix[row][rightColumn] = temporaryValue;
        leftColumn++;
        rightColumn--;
      }
    }
  }
}
