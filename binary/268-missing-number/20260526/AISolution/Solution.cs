// 問題: 268. Missing Number
// アプローチ: 0 から n までの合計値と、配列内の合計値の差を使って欠けた数を求める
// 時間計算量: O(n)
// 空間計算量: O(1)
public class Solution {
    public int MissingNumber(int[] nums) {
        int numberCount = nums.Length;

        // 0 + 1 + ... + n の合計を公式で求める。
        int expectedSum = numberCount * (numberCount + 1) / 2;

        // 配列に実際に入っている値の合計を求める。
        int actualSum = 0;
        foreach (int number in nums) {
            actualSum += number;
        }

        // 足りない分が、そのまま欠けている数になる。
        return expectedSum - actualSum;
    }
}
