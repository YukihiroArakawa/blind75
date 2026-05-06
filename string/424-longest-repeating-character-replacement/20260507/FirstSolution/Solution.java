class Solution {
  public int characterReplacement(String s, int k) {
    int[] charCounts = new int[26];
    int leftPointer = 0;
    int maxFrequencyInWindow = 0;
    int longestLength = 0;

    // 外側ループで右のポインタをスライドさせながら、置換できる回数がウィンドウ内の文字数より多い場合は左ポインタを右にスライドしていく
    for (int rightPointer = 0; rightPointer < s.length(); rightPointer++) {
      int currentCharIndex = s.charAt(rightPointer) - 'A';
      charCounts[currentCharIndex]++;
      maxFrequencyInWindow = Math.max(maxFrequencyInWindow, charCounts[currentCharIndex]);

      while (rightPointer - leftPointer + 1 - maxFrequencyInWindow > k) {
        int leftCharIndex = s.charAt(leftPointer) - 'A';
        charCounts[leftCharIndex]--;
        leftPointer++;
      }

      int currentWindowLength = rightPointer - leftPointer + 1;
      longestLength = Math.max(longestLength, currentWindowLength);
    }

    return longestLength;
  }
}
