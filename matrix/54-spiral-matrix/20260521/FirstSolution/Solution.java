import java.util.ArrayList;
import java.util.List;

class Solution {
  // right -> down -> left -> up の順番で見ていく
  // 探索範囲を狭めながら巡回する
  public List<Integer> spiralOrder(int[][] matrix) {

    int up = 0;
    int down = matrix.length - 1;
    int left = 0;
    int right = matrix[0].length - 1;
    List<Integer> spiralList = new ArrayList<>();

    // 探索範囲が存在する限り巡回する
    while (up <= down && left <= right) {
      // up edge
      for (int col = left; col <= right; col++) {
        spiralList.add(matrix[up][col]);
      }
      up++;

      // right edge
      for (int row = up; row <= down; row++) {
        spiralList.add(matrix[row][right]);
      }
      right--;

      // bottom edge
      if (up <= down) {
        for (int col = right; col >= left; col--) {
          spiralList.add(matrix[down][col]);
        }
        down--;
      }

      // left edge
      if (left <= right) {
        for (int row = down; row >= up; row--) {
          spiralList.add(matrix[row][left]);
        }
        left++;
      }
    }

    return spiralList;
  }
}
