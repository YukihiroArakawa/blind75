// 問題: 53. Maximum Subarray
// アプローチ: 分割統治法（Divide and Conquer）
//   - 配列を半分に分割し、最大部分配列が以下の3ケースのどれかに存在すると考える
//     1. 左半分のみに存在
//     2. 右半分のみに存在
//     3. 中央をまたいで存在
//   - ケース1,2は再帰で解き、ケース3は中央から左右に伸ばして最大和を求める
//   - 3つのケースの最大値が答えとなる
// 時間計算量: O(n log n) — 毎回半分に分割（log n 段）× 各段で O(n) の走査
// 空間計算量: O(log n) — 再帰呼び出しのスタック深さ
class Solution {
    public int maxSubArray(int[] nums) {
        return findMaxSubArray(nums, 0, nums.length - 1);
    }

    // 配列の [left, right] 区間における最大部分配列和を再帰的に求める
    private int findMaxSubArray(int[] nums, int left, int right) {
        // ベースケース: 要素が1つだけならその値を返す
        if (left == right) {
            return nums[left];
        }

        int mid = left + (right - left) / 2;

        // ケース1: 左半分のみに最大部分配列が存在する場合
        int leftMax = findMaxSubArray(nums, left, mid);
        // ケース2: 右半分のみに最大部分配列が存在する場合
        int rightMax = findMaxSubArray(nums, mid + 1, right);
        // ケース3: 中央をまたいで最大部分配列が存在する場合
        int crossMax = findMaxCrossingSubArray(nums, left, mid, right);

        // 3つのケースのうち最大の値を返す
        return Math.max(Math.max(leftMax, rightMax), crossMax);
    }

    // 中央をまたぐ部分配列の最大和を求める
    // mid を必ず含む形で、左右それぞれに伸ばしたときの最大和を合算する
    private int findMaxCrossingSubArray(int[] nums, int left, int mid, int right) {
        // mid から左方向に伸ばして最大和を求める
        int leftSum = Integer.MIN_VALUE;
        int currentSum = 0;
        for (int i = mid; i >= left; i--) {
            currentSum += nums[i];
            leftSum = Math.max(leftSum, currentSum);
        }

        // mid+1 から右方向に伸ばして最大和を求める
        int rightSum = Integer.MIN_VALUE;
        currentSum = 0;
        for (int i = mid + 1; i <= right; i++) {
            currentSum += nums[i];
            rightSum = Math.max(rightSum, currentSum);
        }

        // 左側最大 + 右側最大 = 中央をまたぐ最大部分配列和
        return leftSum + rightSum;
    }
}
