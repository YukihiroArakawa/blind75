// 問題: 5. Longest Palindromic Substring
// アプローチ: 各位置を中心として左右に広げ、奇数長と偶数長の回文を調べる
// 時間計算量: O(n^2)
// 空間計算量: O(1)
class Solution {
  public String longestPalindrome(String s) {
    // 現時点で見つかっている最長回文の開始位置と長さを保持する。
    int bestStartIndex = 0;
    int bestLength = 1;

    for (int centerIndex = 0; centerIndex < s.length(); centerIndex++) {
      // 奇数長回文を調べる。
      // 例: "aba" なら真ん中の 'b' が中心になる。
      int oddLength = expandAroundCenter(s, centerIndex, centerIndex);
      if (oddLength > bestLength) {
        bestLength = oddLength;
        bestStartIndex = centerIndex - oddLength / 2;
      }

      // 偶数長回文を調べる。
      // 例: "abba" なら真ん中の "bb" の間が中心になる。
      int evenLength = expandAroundCenter(s, centerIndex, centerIndex + 1);
      if (evenLength > bestLength) {
        bestLength = evenLength;
        bestStartIndex = centerIndex - evenLength / 2 + 1;
      }
    }

    // 最長回文の開始位置と長さが分かっているので、その区間を切り出して返す。
    return s.substring(bestStartIndex, bestStartIndex + bestLength);
  }

  private int expandAroundCenter(String s, int leftPointer, int rightPointer) {
    // 左右の文字が一致する限り、回文として外側へ広げていく。
    while (leftPointer >= 0
        && rightPointer < s.length()
        && s.charAt(leftPointer) == s.charAt(rightPointer)) {
      leftPointer--;
      rightPointer++;
    }

    // ループを抜けた時点では、左右どちらかが 1 つ外へはみ出している。
    // そのため、最後に有効だった回文の長さは right - left - 1 で求める。
    return rightPointer - leftPointer - 1;
  }
}
