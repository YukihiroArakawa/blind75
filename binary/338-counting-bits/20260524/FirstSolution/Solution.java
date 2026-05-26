class Solution {
  public int[] countBits(int n) {
    int[] ans = new int[n + 1];

    // ex) i = 5の場合
    // if i = 1(10), then 001(2) = 00(2) + 1(2)
    // if i = 2(10), then 010(2) = 01(2) + 0(2)
    // if i = 5(10), then 101(2) = 10(2) + 1(2)
    // if i = 6(10), then 110(2) = 11(2) + 0(2)
    for (int i = 1; i <= n; i++) {
      int cachedCount = ans[i >> 1];
      int lastBitCount = i & 1;
      ans[i] = cachedCount + lastBitCount;
    }

    return ans;
  }
}
