import java.util.HashMap;
import java.util.Map;

class Solution {
  // O(N)で解きたい
  // abc
  // abc
  // b

  // pw
  // wke
  // w

  // 文字列を追加していく、重複を発見したらリスタート、最大文字を超えたら最大文字数を更新
  public int lengthOfLongestSubstring(String s) {
    int max = 0;
    Map<Character, Integer> latestIndex = new HashMap<>();
    int leftIndex = 0;

    for (int rightIndex = 0; rightIndex < s.length(); rightIndex++) {
      char c = s.charAt(rightIndex);

      Integer oldIndex = latestIndex.get(c);
      if (oldIndex != null) {
        leftIndex = Math.max(leftIndex, oldIndex + 1);
      }

      latestIndex.put(c, rightIndex);

      // leftIndex <-> rightIndex の差を取って部分文字列の長さを調べる
      int subStringSize = rightIndex - leftIndex + 1;
      max = Math.max(max, subStringSize);
    }

    return max;
  }

  public static void main(String[] args) {
    System.out.println(new Solution().lengthOfLongestSubstring("abcabcbb"));
    System.out.println(new Solution().lengthOfLongestSubstring("bbbb"));
    System.out.println(new Solution().lengthOfLongestSubstring("pwwkew"));
    System.out.println(new Solution().lengthOfLongestSubstring("dvdf"));
    System.out.println(new Solution().lengthOfLongestSubstring("abba"));
  }
}
