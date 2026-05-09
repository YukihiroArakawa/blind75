// 問題: 647. Palindromic Substrings
// アプローチ: 各位置を中心として左右に広げ、見つかった回文の数を数える
// 時間計算量: O(n^2)
// 空間計算量: O(1)
class Solution {
    public int countSubstrings(String s) {
        int palindromeCount = 0;

        for (int centerIndex = 0; centerIndex < s.length(); centerIndex++) {
            palindromeCount += countPalindromesFromCenter(s, centerIndex, centerIndex);
            palindromeCount += countPalindromesFromCenter(s, centerIndex, centerIndex + 1);
        }

        return palindromeCount;
    }

    private int countPalindromesFromCenter(String s, int leftPointer, int rightPointer) {
        int count = 0;

        while (leftPointer >= 0
            && rightPointer < s.length()
            && s.charAt(leftPointer) == s.charAt(rightPointer)) {
            count++;
            leftPointer--;
            rightPointer++;
        }

        return count;
    }
}
