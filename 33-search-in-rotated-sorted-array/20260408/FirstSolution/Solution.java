class Solution {

  // 入力: nums = [4,5,6,7,0,1,2], target = 0
  // 出力: 4

  // 方針1；BinarySearchを左右で切り替えつつ実施すれば見つかりそう
  // 方針2: ArrayListのライブラリでなんかできちゃいそうな気もする(一旦これはなしで考える)
  public int search(int[] nums, int target) {
    int mid = nums.length / 2 - 1;
    int leftId = nums[0];
    int rightId = nums[nums.length - 1];

    if (leftId <= nums[mid]) {
      rightId = mid;
    } else {
      leftId = mid;
    }

    while (true) {

      if (nums[mid] == target) {
        return mid;
      }

      if (nums[mid] > target) {
        rightId = mid;
      } else {
        leftId = mid;
      }

      mid = (leftId + rightId) / 2 - 1;
    }
  }

  public static void main(String[] args) {
    System.out.println(new Solution().search(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 0));
  }
}
