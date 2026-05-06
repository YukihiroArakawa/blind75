import java.util.HashMap;
import java.util.Map;

// 問題: 3. Longest Substring Without Repeating Characters
// アプローチ: スライディングウィンドウで重複のない区間を保ちながら、各文字の直近出現位置を HashMap で管理する
// 時間計算量: O(n)
// 空間計算量: O(min(n, 文字種数))
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> latestIndexByChar = new HashMap<>();
        int leftPointer = 0;
        int longestLength = 0;

        for (int rightPointer = 0; rightPointer < s.length(); rightPointer++) {
            char currentChar = s.charAt(rightPointer);

            // 同じ文字が現在のウィンドウ内にあるなら、左端をその次まで進める。
            if (latestIndexByChar.containsKey(currentChar)) {
                leftPointer = Math.max(leftPointer, latestIndexByChar.get(currentChar) + 1);
            }

            latestIndexByChar.put(currentChar, rightPointer);
            int currentWindowLength = rightPointer - leftPointer + 1;
            longestLength = Math.max(longestLength, currentWindowLength);
        }

        return longestLength;
    }
}
