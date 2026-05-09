
class Solution {
  // これも前の問題と同じで中心の文字ずらしていって、
  // 左右に広げることで回分判定することで O(n^2)でとけそう
  // = n文字数ずらす、n/2
  // = n * n * 1/2
  // = n^2
  public int countSubstrings(String s) {
    int result = 0;

    for (int centerId = 0; centerId < s.length(); centerId++) {
      // 奇数文字回分
      result += checkPalindrome(s, centerId, centerId);
      // 偶数文字回分
      result += checkPalindrome(s, centerId, centerId + 1);
    }

    return result;
  }

  private int checkPalindrome(String s, int leftId, int rightId) {
    int count = 0;
    while (leftId >= 0 && rightId < s.length() && s.charAt(leftId) == s.charAt(rightId)) {
      count++;
      leftId--;
      rightId++;
    }
    return count;
  }

  public static void main(String[] args) {
    // System.out.println(new Solution().countSubstrings(
    // "abc"));

    // System.out.println(new Solution().countSubstrings(
    // "aaa"));

    System.out.println(new Solution().countSubstrings(
        "fdsklf"));

  }
}
