// 問題: 53. Maximum Subarray
// アプローチ: Kadane のアルゴリズム（動的計画法）
//   - 各要素を走査しながら「現在位置で終わる部分配列の最大和」を更新する
//   - 累積和が負になったら、そこまでの部分配列を捨てて新たに開始する方が有利
//   - 走査中に見つかった最大和を記録し続ける
// 時間計算量: O(n) — 配列を1回走査するのみ
// 空間計算量: O(1) — 追加変数2つのみ
class Solution {
    public int maxSubArray(int[] nums) {
        // maxSum: これまでに見つかった部分配列の最大和
        // 最初の要素を初期値にすることで、全要素が負の場合にも対応
        int maxSum = nums[0];

        // currentSum: 現在位置で終わる部分配列の和
        int currentSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // 「これまでの累積和 + 現在の要素」と「現在の要素のみで新規開始」の大きい方を選ぶ
            // currentSum が負なら、累積を捨てて現在の要素から再スタートする方が有利
            currentSum = Math.max(currentSum + nums[i], nums[i]);

            // 最大和を更新
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }
}
