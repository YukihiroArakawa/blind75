import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  public List<List<Integer>> threeSum(int[] nums) {
    // 配列をソート
    Arrays.sort(nums);

    List<List<Integer>> results = new ArrayList<>();

    // 配列をループする
    for (int i = 0; i < nums.length; i++) {
      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }
      // １つ固定(a)してループする
      // 固定した値 -aになるペアを探す
      int complement = nums[i] * -1;

      // TwoPointersを使うとわかりやすい.
      int left = i + 1;
      int right = nums.length - 1;
      while (left < right) {
        int sum = nums[left] + nums[right];

        if (sum == complement) {
          results.add(List.of(nums[i], nums[left], nums[right]));
          while (left < right && nums[left] == nums[left + 1]) {
            left++;
          }

          while (left < right && nums[right] == nums[right - 1]) {
            right--;
          }

          left++;
          right--;
        } else if (sum < complement) {
          left++;
        } else {
          right--;
        }
      }
    }

    return results;
  }
}
