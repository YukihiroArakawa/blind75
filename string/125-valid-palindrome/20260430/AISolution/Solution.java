// 問題: 125. Valid Palindrome
// アプローチ: 双方向ポインタ (Two Pointers)
//   - 両端から中央に向かってポインタを進め、英数字のみを比較する
//   - 新たな文字列を組み立てない方針にすることで、追加メモリを抑えられる
//   - 大文字小文字の差異は Character.toLowerCase で吸収する
// 時間計算量: O(n)
//   - 左右のポインタが合計で n 文字を一度ずつ走査する
// 空間計算量: O(1)
//   - ポインタ用の変数のみで、入力サイズに依存しない
class Solution {
  public boolean isPalindrome(String s) {
    // 左端と右端を指す双方向ポインタ
    int left = 0;
    int right = s.length() - 1;

    while (left < right) {
      // 左ポインタを「次の英数字」まで進める
      // 英数字以外 (空白や記号) は palindrome 判定の対象外なのでスキップ
      while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
        left++;
      }
      // 右ポインタも同様に「次の英数字」まで戻す
      while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
        right--;
      }

      // 大文字小文字を無視して比較するため、両方を小文字化してから判定
      // 一致しなければ palindrome ではないので即時 false
      if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
        return false;
      }

      // 一致したら次のペアへ進める
      left++;
      right--;
    }

    // 中央まで矛盾なく走査できれば palindrome
    return true;
  }

  public static void main(String[] args) {

  }
}
