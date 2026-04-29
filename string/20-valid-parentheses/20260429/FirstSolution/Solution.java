import java.util.ArrayList;
import java.util.List;

class Solution {
  // 閉じカッコがあっているかどうか
  // (),([{}]) ,(){}, empty, (のようなパターンをさばければ他のパターンは対処できそう
  public boolean isValid(String s) {
    // 一文字以下の場合はカッコが作れない
    if (s.length() <= 1) {
      return false;
    }

    // 文字列を1文字ずつ繰り返し処理する
    // コレクションに詰めて判定したい
    // => どうする？
    // 閉じカッコが現れたら判定すると良さそう
    // ({} の場合
    // ({[]
    // スタックに詰めては取り出すようにすれば良さそう

    List<Character> braceList = new ArrayList<>();
    for (char brace : s.toCharArray()) {
      if (brace == '(' || brace == '{' || brace == '[') {
        braceList.addLast(brace);
      }

      // 閉じカッコが現れたら判定
      if (brace == ')' || brace == '}' || brace == ']') {
        if (braceList.isEmpty())
          return false;

        char leftBrace = braceList.removeLast();
        switch (brace) {
          case ')':
            if (leftBrace != '(')
              return false;
            else
              break;
          case '}':
            if (leftBrace != '{')
              return false;
            else
              break;
          case ']':
            if (leftBrace != '[')
              return false;
            else
              break;
          default:
            break;
        }
      }
    }

    return braceList.isEmpty();
  }

  public static void main(String[] args) {
    System.out.println(new Solution().isValid("()"));

  }
}
