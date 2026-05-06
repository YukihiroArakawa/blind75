import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
    Map<Character, Integer> leftPointerMap = new HashMap<>();
    int max = 0;

    int currentChars = 0;
    Set<Character> charSet = new HashSet<>();

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      Integer leftPointerIndex = leftPointerMap.get(c);
      if (leftPointerIndex == null) {
        leftPointerMap.put(c, i);
      } else {
        leftPointerMap.replace(c, i);
      }

      // 重複文字を見つけたらスライド
      if (charSet.contains(c)) {
        currentChars = 0;
        charSet = new HashSet<>();
        i = leftPointerIndex;
      } else {
        charSet.add(c);
        currentChars++;
      }

      max = Math.max(max, currentChars);
    }

    return max;
  }

  public static void main(String[] args) {
    System.out.println(new Solution().lengthOfLongestSubstring("abcabcbb"));
    System.out.println(new Solution().lengthOfLongestSubstring("bbbb"));
    System.out.println(new Solution().lengthOfLongestSubstring("pwwkew"));
    System.out.println(new Solution().lengthOfLongestSubstring("dvdf"));
  }
}
