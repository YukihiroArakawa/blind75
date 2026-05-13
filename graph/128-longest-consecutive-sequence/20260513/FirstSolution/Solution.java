import java.util.HashSet;
import java.util.Set;

class Solution {
  public int longestConsecutive(int[] nums) {
    int maxCounter = 0;

    Set<Integer> numsSet = new HashSet<>();
    for (Integer num : nums) {
      numsSet.add(num);
    }

    for (Integer num : numsSet) {

      // 開始点でない場合はスキップ
      if (numsSet.contains(num - 1)) {
        continue;
      }

      int counter = 1;
      int nextNum = num;
      while (numsSet.contains(nextNum + 1)) {
        nextNum++;
        counter++;
      }

      maxCounter = Math.max(maxCounter, counter);
    }

    return maxCounter;
  }
}
