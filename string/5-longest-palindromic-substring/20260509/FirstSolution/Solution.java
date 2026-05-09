class Solution {
  // 最長の回分
  // 回分の判定は関数として切り出して使いまわす
  // ナイーブな方法だと全て探索して回分判定＆最大文字数の更新をする方法がありそう？
  // たかだか1000文字
  // ex) abcdの場合
  // - a, ab, abc, abcd
  // - b, bc, bcd
  // - c, cd
  // - d,
  // 時間計算量: O(n^2) .. n^2 / 2
  // タイムアウトした
  public String longestPalindrome(String s) {
    String longestWord = "";

    for (int i = 0; i < s.length(); i++) {
      for (int j = i + 1; j <= s.length(); j++) {
        String subStr = s.substring(i, j);
        longestWord = isPalindrome(subStr) && subStr.length() > longestWord.length() ? subStr : longestWord;
      }
    }

    return longestWord;
  }

  private boolean isPalindrome(String s) {
    int length = s.length();
    for (int i = 0; i < length / 2; i++) {
      if (s.charAt(i) != s.charAt(length - 1 - i)) {
        return false;
      }
    }

    return true;
  }

  public static void main(String[] args) {
    System.out.println(new Solution().longestPalindrome("abbad"));
    System.out.println(new Solution().longestPalindrome("a"));
    System.out.println(new Solution().longestPalindrome("cbbd"));
    System.out.println(new Solution().longestPalindrome("bb"));
    System.out.println(new Solution().longestPalindrome("121"));
    System.out.println(new Solution().longestPalindrome("1221"));
  }
}
