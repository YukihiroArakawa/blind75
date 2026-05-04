// 問題: 191. Number of 1 Bits
// アプローチ: Brian Kernighan's Algorithm
//   n & (n - 1) を使うと、n の右端にある 1 ビットを 1 つ消せる。
//   そのため、n が 0 になるまでこの操作を繰り返し、
//   操作回数を数えれば set bit の個数になる。
// 時間計算量: O(k)  … k は n に含まれる 1 ビット数
// 空間計算量: O(1)

public class Solution {
    public int hammingWeight(int n) {
        int bitCount = 0;

        while (n != 0) {
            bitCount++;
            n = n & (n - 1);
        }

        return bitCount;
    }
}
