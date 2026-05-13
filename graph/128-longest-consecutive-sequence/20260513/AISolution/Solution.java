import java.util.HashSet;
import java.util.Set;

// 問題: 128. Longest Consecutive Sequence
// アプローチ: 全要素を HashSet に入れ、各数が連続列の開始点かどうかを判定し、開始点だけ右へ伸ばして長さを数える
// 時間計算量: O(n)
// 空間計算量: O(n)
class Solution {
  public int longestConsecutive(int[] nums) {
    Set<Integer> numberSet = new HashSet<>();
    for (int number : nums) {
      numberSet.add(number);
    }

    int longestLength = 0;

    for (int number : numberSet) {
      // 1つ前の数が存在するなら、この数は連続列の途中なので開始点ではない。
      if (numberSet.contains(number - 1)) {
        continue;
      }

      int currentNumber = number;
      int currentLength = 1;

      while (numberSet.contains(currentNumber + 1)) {
        currentNumber++;
        currentLength++;
      }

      longestLength = Math.max(longestLength, currentLength);
    }

    return longestLength;
  }
}
