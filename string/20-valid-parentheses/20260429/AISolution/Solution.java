import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

// 問題: 20. Valid Parentheses
// アプローチ: スタックによる対応関係の検証
//   - 「最後に開いた括弧から先に閉じる」という LIFO 構造そのものなのでスタックが最適
//   - 開き括弧はスタックに積み、閉じ括弧が来たら直前に積まれた開き括弧と種類を照合
//   - 走査終了時にスタックが空であれば全て対応がとれている = 有効
// 時間計算量: O(n)
//   - n = s.length()。各文字に対して push/pop が定数時間で実行される
// 空間計算量: O(n)
//   - 最悪ケース (例: "(((((") では全文字をスタックに積むため n に比例
class Solution {
  // 閉じ括弧 → 対応する開き括弧 のマッピング
  // Map にしておくことで、文字種が増えてもこの定義だけ拡張すれば対応可能
  private static final Map<Character, Character> CLOSE_TO_OPEN = Map.of(
      ')', '(',
      ']', '[',
      '}', '{');

  public boolean isValid(String s) {
    // 長さが奇数なら必ず対応が取れないので即時 false
    // 早期リターンで無駄な走査を避ける
    if (s.length() % 2 != 0) {
      return false;
    }

    // ArrayDeque は Stack より高速で、現代の Java では推奨される LIFO 実装
    Deque<Character> openStack = new ArrayDeque<>();

    for (int i = 0; i < s.length(); i++) {
      char current = s.charAt(i);

      if (CLOSE_TO_OPEN.containsKey(current)) {
        // 閉じ括弧の場合: スタックの先頭が対応する開き括弧かを確認
        // スタックが空 = 対応する開き括弧が存在しない → 不正
        // 種類が違う → 例: "([)]" のような交差は不正
        if (openStack.isEmpty() || openStack.pop() != CLOSE_TO_OPEN.get(current)) {
          return false;
        }
      } else {
        // 開き括弧の場合: 後で対応する閉じ括弧と照合するため積んでおく
        openStack.push(current);
      }
    }

    // 走査終了時に残っている開き括弧があれば、対応する閉じ括弧がなかったので不正
    return openStack.isEmpty();
  }

  public static void main(String[] args) {

  }
}
