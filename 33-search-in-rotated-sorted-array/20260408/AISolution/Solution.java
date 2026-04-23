// 問題: 33. Search in Rotated Sorted Array
// アプローチ: 修正版二分探索。回転された昇順配列でも、任意の中点で
//   左半分か右半分のどちらかは必ずソート済みとなる性質を利用する。
//   ソート済み側の範囲に target が入っていればそちら側、そうでなければ
//   反対側を探索することで O(log n) を達成する。
// 時間計算量: O(log n)
// 空間計算量: O(1)
class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // nums[left..mid] がソート済みかを判定
            // 回転点が mid より右にある場合、左半分は単調増加となる
            if (nums[left] <= nums[mid]) {
                // target が左半分のソート済み範囲内にあるか確認
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // 右半分 nums[mid..right] がソート済み
                // target が右半分のソート済み範囲内にあるか確認
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }
}
