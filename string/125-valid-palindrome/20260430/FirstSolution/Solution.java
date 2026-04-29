class Solution {
  public boolean isPalindrome(String s) {
    if (s.isBlank()) {
      return true;
    }

    String sanitizedS = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

    // 先頭と末尾を比較していく
    int maxLen = sanitizedS.length();
    for (int i = 0; i <= maxLen / 2 - 1; i++) {
      if (sanitizedS.charAt(i) != sanitizedS.charAt(maxLen - i - 1)) {
        return false;
      }
    }

    return true;
  }

  public static void main(String[] args) {
    System.out.println(new Solution().isPalindrome(" "));
    System.out.println(new Solution().isPalindrome("s"));
    System.out.println(new Solution().isPalindrome("ss"));
    System.out.println(new Solution().isPalindrome("ses"));
    System.out.println(new Solution().isPalindrome("sets"));
    System.out.println(new Solution().isPalindrome("A man, a plan, a canal: Panama"));
    System.out.println(new Solution().isPalindrome("0P"));

  }
}
