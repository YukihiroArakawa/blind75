import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 問題: 49. Group Anagrams
// アプローチ: 各文字列をソートして共通キーを作り、HashMap で同じキーの文字列をまとめる
// 時間計算量: O(n * k log k)  (n は文字列数、k は各文字列の最大長)
// 空間計算量: O(n * k)
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groupedStringsBySortedKey = new HashMap<>();

        for (String currentString : strs) {
            char[] sortedChars = currentString.toCharArray();
            Arrays.sort(sortedChars);
            String sortedKey = new String(sortedChars);

            groupedStringsBySortedKey
                .computeIfAbsent(sortedKey, ignoredKey -> new ArrayList<>())
                .add(currentString);
        }

        return new ArrayList<>(groupedStringsBySortedKey.values());
    }
}
