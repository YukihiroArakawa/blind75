// 問題: 338. Counting Bits
// アプローチ: 動的計画法
//   偶数 i の 1 の個数は i / 2 と同じ。
//   奇数 i の 1 の個数は i - 1 の個数に 1 を足したものになる。
//   これをまとめると ans[i] = ans[i >> 1] + (i & 1) で求められる。
// 時間計算量: O(n)
// 空間計算量: O(n)
class Solution {
  public int[] countBits(int n) {
    int[] bitCounts = new int[n + 1];

    for (int value = 1; value <= n; value++) {
      bitCounts[value] = bitCounts[value >> 1] + (value & 1);
    }

    return bitCounts;
  }
}
