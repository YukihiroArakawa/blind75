import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
  // 方針1: ArrayListで最小値を取得するメソッドを使ったら瞬殺できそうだが、おそらくそういうことではない気がする
  // 方針2: binarySearchはソート済みでないといけないので、今回はそのまま使えない
  // 方針3: 最小値を探すというよりlog nでソートする方法を考えたほうがよいか？
  public int findMin(int[] nums) {
    // return findMinAproach1(nums);
    return findMinAproach3(nums);
  }

  private int findMinAproach1(int[] nums) {
    List<Integer> numsList = new ArrayList<>(Arrays.stream(nums).boxed().toList());
    return Collections.min(numsList);
  }

  private int findMinAproach3(int[] nums) {

  }
}
