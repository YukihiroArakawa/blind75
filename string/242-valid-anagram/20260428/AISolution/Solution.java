// 問題: 242. Valid Anagram
// アプローチ: 文字頻度カウント (固定長配列)
//   - アナグラムであれば「両文字列に含まれる各文字の出現回数が完全一致する」
//   - 制約より小文字英字のみなので、長さ 26 の int 配列で頻度を管理できる
//   - s では +1, t では -1 し、最後に全て 0 ならアナグラム
// 時間計算量: O(n)
//   - n = s.length() (= t.length())。各文字を 1 回ずつ走査するだけ
// 空間計算量: O(1)
//   - 26 文字固定の配列のみ使用するので、入力サイズに依らず定数
class Solution {
  public boolean isAnagram(String s, String t) {
    // 長さが違えばアナグラムになり得ないので即時 false
    // 早期リターンにより、以降のループでの不要な走査を避ける
    if (s.length() != t.length()) {
      return false;
    }

    // 'a' を 0 番目とする頻度カウント配列
    // s は加算、t は減算することで「両者の差分」を表現する
    int[] charCounts = new int[26];
    for (int i = 0; i < s.length(); i++) {
      charCounts[s.charAt(i) - 'a']++;
      charCounts[t.charAt(i) - 'a']--;
    }

    // 全ての文字について差分が 0 であればアナグラム成立
    for (int count : charCounts) {
      if (count != 0) {
        return false;
      }
    }
    return true;
  }

  // フォローアップ (Unicode 対応) について:
  //   - 文字の種類が固定でなくなるので、配列ではなく HashMap<Character, Integer> を使う
  //   - 計算量は時間 O(n)、空間 O(k) (k = ユニーク文字数) になる
  //   - 実装の骨格は同じで、+1 / -1 で差分を取る方針も流用可能

  public static void main(String[] args) {

  }
}
