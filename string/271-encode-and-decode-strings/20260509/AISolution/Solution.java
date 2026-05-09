import java.util.ArrayList;
import java.util.List;

// 問題: 271. Encode and Decode Strings
// アプローチ: 各文字列を「長さ#文字列本体」の形式で連結し、デコード時は長さを読んで切り出す
// 時間計算量: O(totalLength)
// 空間計算量: O(totalLength)
public class Solution {
  public String encode(List<String> strs) {
    StringBuilder encodedBuilder = new StringBuilder();

    for (String currentString : strs) {
      encodedBuilder.append(currentString.length());
      encodedBuilder.append('#');
      encodedBuilder.append(currentString);
    }

    return encodedBuilder.toString();
  }

  public List<String> decode(String s) {
    List<String> decodedStrings = new ArrayList<>();
    int currentIndex = 0;

    while (currentIndex < s.length()) {
      int delimiterIndex = currentIndex;

      while (s.charAt(delimiterIndex) != '#') {
        delimiterIndex++;
      }

      int currentStringLength = Integer.parseInt(s.substring(currentIndex, delimiterIndex));
      int stringStartIndex = delimiterIndex + 1;
      int stringEndIndex = stringStartIndex + currentStringLength;

      decodedStrings.add(s.substring(stringStartIndex, stringEndIndex));
      currentIndex = stringEndIndex;
    }

    return decodedStrings;
  }
}
