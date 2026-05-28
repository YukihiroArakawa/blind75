class Solution {
  // 元の値の右ビットを取り出す
  // 答えの値の末尾に追加
  // 答えの値を左シフトして末尾を開ける
  public int reverseBits(int n) {
    int result = 0;

    for (int i = 1; i <= 32; i++) {
      // 答えの値を左シフトして末尾を開ける
      result = result << 1;

      // 元の値の右ビットを取り出す
      int nextBit = n & 1;

      // 元の値を右にシフト
      n = n >>> 1;

      // 答えの値の末尾に追加
      result = result | nextBit;
    }

    return result;
  }
}
