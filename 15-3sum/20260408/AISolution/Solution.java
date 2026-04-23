// 問題: 15. 3Sum
// アプローチ: ソート + 二点法（two pointers）
//   配列をソートしてから、各要素を「最初の値 a」として固定し、
//   その右側の区間を左右ポインタで挟み撃ちして b + c = -a を探す。
//   ソート済みなので、合計が小さければ left を右へ、大きければ right を左へ動かせる。
//   重複を避けるため、a・b・c それぞれで「直前と同じ値はスキップ」する。
// 時間計算量: O(n^2)  （ソート O(n log n) + 各 a に対し two pointer O(n)）
// 空間計算量: O(1)    （結果リストを除く。ソートはin-place）
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;

        for (int i = 0; i < n - 2; i++) {
            // 最小要素が正なら以降の合計は必ず正になるため打ち切り
            if (nums[i] > 0) break;
            // 同じ値の a を二度処理しないことで重複三つ組を回避
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;
            int right = n - 1;
            int targetSum = -nums[i];

            while (left < right) {
                int currentSum = nums[left] + nums[right];
                if (currentSum == targetSum) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // b・c の重複もスキップして同じ三つ組を二度追加しない
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                } else if (currentSum < targetSum) {
                    // 合計が足りない → 大きい値を取り込むため left を右へ
                    left++;
                } else {
                    // 合計が大きすぎる → 小さい値を取り込むため right を左へ
                    right--;
                }
            }
        }

        return result;
    }
}
