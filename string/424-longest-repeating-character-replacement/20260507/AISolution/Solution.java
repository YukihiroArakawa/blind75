// 問題: 424. Longest Repeating Character Replacement
// アプローチ: スライディングウィンドウで区間内の文字数を数え、最頻文字以外の個数が k 以下かを保つ
// 時間計算量: O(n)
// 空間計算量: O(1)  (大文字英字 26 種だけを数えるため)
class Solution {
    public int characterReplacement(String s, int k) {
        int[] charCounts = new int[26];
        int leftPointer = 0;
        int maxFrequencyInWindow = 0;
        int longestLength = 0;

        for (int rightPointer = 0; rightPointer < s.length(); rightPointer++) {
            int currentCharIndex = s.charAt(rightPointer) - 'A';
            charCounts[currentCharIndex]++;
            maxFrequencyInWindow = Math.max(maxFrequencyInWindow, charCounts[currentCharIndex]);

            // 「ウィンドウ長 - 最頻文字数」が置換必要回数なので、k を超えたら左を縮める。
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
