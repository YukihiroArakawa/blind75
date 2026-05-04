public class Solution {
  // 10進数を2進数に変換する
  // 1の個数を数える
  // - 数え方が色々ありそう
  // - 空文字に置換した上で差を取る
  // - 単純ループで数える
  public int hammingWeight(int n) {
    String binaryString = Integer.toBinaryString(n);

    int count = 0;
    for (int i = 0; i < binaryString.length(); i++) {
      if (binaryString.charAt(i) == '1') {
        count++;
      }
    }

    return count;
  }
}
