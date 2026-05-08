import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// ブルートフォース回答だと、毎回ソートしてハッシュマップに追加するとか？
// 時間計算量 O(n * k log k): n個の要素を全て確認する、サイズkのアイテムのソートにk log kかかる. 比較系のソートはk log k
class Solution {

  public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> wordMap = new HashMap<>();

    for (String word : strs) {
      char[] charArr = word.toCharArray();
      Arrays.sort(charArr);
      String sortedWord = new String(charArr);

      wordMap.computeIfAbsent(sortedWord, ignoredKey -> new ArrayList<>())
          .add(word);
    }

    return new ArrayList<>(wordMap.values());
  }

  public static void main(String[] args) {
    System.out.println(new Solution().groupAnagrams(new String[] { "eat", "tea", "tan", "ate", "nat", "bat" }));
  }
}
