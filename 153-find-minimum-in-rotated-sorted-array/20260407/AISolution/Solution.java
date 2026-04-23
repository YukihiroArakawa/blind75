// 問題: 153. Find Minimum in Rotated Sorted Array
// アプローチ: 二分探索 - 中央値と右端を比較し、最小値が含まれる側に範囲を絞る
//   nums[mid] > nums[right] なら最小値は mid より右側にある（回転点が右半分）
//   そうでなければ最小値は mid 以下の位置にある
// 時間計算量: O(log n)
// 空間計算量: O(1)
class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        // left < right の間ループし、最終的に left == right が答えの位置
        while (left < right) {
            int mid = left + (right - left) / 2;

            // 中央値が右端より大きい => 配列の「段差」(回転点) は mid の右側にある
            // よって最小値は mid+1 以降に存在する
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                // 中央値が右端以下 => mid 自身が最小値の候補。mid を含めて左側を探す
                right = mid;
            }
        }

        return nums[left];
    }
}
