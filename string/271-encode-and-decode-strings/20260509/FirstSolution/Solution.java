import java.util.ArrayList;
import java.util.List;

class Solution {

  public String encode(List<String> strs) {

    StringBuilder builder = new StringBuilder();

    for (String string : strs) {
      builder.append(string.length());
      builder.append("#");
      builder.append(string);
    }

    return builder.toString();
  }

  public List<String> decode(String str) {
    List<String> wordList = new ArrayList<>();
    int currentId = 0;

    while (currentId < str.length()) {

      int delimiterId = currentId;

      while (true) {
        if (str.charAt(delimiterId) == '#') {
          break;
        }
        delimiterId++;
      }

      int wordLength = Character.getNumericValue(str.charAt(delimiterId - 1));
      int wordFirstId = delimiterId + 1;
      int wordEndId = wordFirstId + wordLength;

      String decodedWord = str.substring(wordFirstId, wordEndId);

      wordList.add(decodedWord);

      currentId = wordEndId;
    }

    return wordList;
  }

  public static void main(String[] args) {
    Solution sol = new Solution();
    String encodedMsg = sol.encode(List.of("Word", "Word", "####", "4#4#", "#4#4##"));
    System.out.println("encodedMsg: " + encodedMsg);
    List<String> decodedMsgList = sol.decode(encodedMsg);
    for (String string : decodedMsgList) {
      System.out.println(string);
    }

  }
}
