import java.util.ArrayList;
import java.util.List;

// 問題: 54. Spiral Matrix
// アプローチ: 上下左右の境界を持ちながら、右 -> 下 -> 左 -> 上の順で外枠を削るように走査する
// 時間計算量: O(m * n)
// 空間計算量: O(1) （返り値の配列を除く）
class Solution {
  public List<Integer> spiralOrder(int[][] matrix) {
    int rowCount = matrix.length;
    int columnCount = matrix[0].length;

    // まだ読んでいない長方形の境界を 4 つで管理する。
    int topRow = 0;
    int bottomRow = rowCount - 1;
    int leftColumn = 0;
    int rightColumn = columnCount - 1;
    List<Integer> spiralOrder = new ArrayList<>();

    while (topRow <= bottomRow && leftColumn <= rightColumn) {
      // 上辺を左から右へ読む。
      for (int column = leftColumn; column <= rightColumn; column++) {
        spiralOrder.add(matrix[topRow][column]);
      }
      topRow++;

      // 右辺を上から下へ読む。
      for (int row = topRow; row <= bottomRow; row++) {
        spiralOrder.add(matrix[row][rightColumn]);
      }
      rightColumn--;

      // 下辺はまだ行が残っているときだけ右から左へ読む。
      if (topRow <= bottomRow) {
        for (int column = rightColumn; column >= leftColumn; column--) {
          spiralOrder.add(matrix[bottomRow][column]);
        }
        bottomRow--;
      }

      // 左辺はまだ列が残っているときだけ下から上へ読む。
      if (leftColumn <= rightColumn) {
        for (int row = bottomRow; row >= topRow; row--) {
          spiralOrder.add(matrix[row][leftColumn]);
        }
        leftColumn++;
      }
    }

    return spiralOrder;
  }
}
