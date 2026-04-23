class Solution {
  // 入力: nums = [4,5,6,7,0,1,2], target = 0
  // 出力: 4

  // 規則
  // - 半分に分割したら片側はソート済みになる
  // [4, 5, 6, 7], [7, 0, 1, 2]
  // - 左端 < 真ん中となる場合はソート済みの側
  // - 成り立たない場合は、ソートされていない側
  // - ソート済み側にターゲットが入っている場合はそちらをそうでない場合は逆側を探索する

  // [4, 5, 6, 0][0, 1, 2, 3]
  public int search(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;

    while (left <= right) {
      int mid = left + (right - left) / 2;

      if (nums[mid] == target) {
        return mid;
      }

      // 左側がソート済みである
      if (nums[left] <= nums[mid]) {
        // targetが含まれている
        if (nums[left] <= target && target < nums[mid]) {
          right = mid - 1;
        } else {
          left = mid + 1;
        }

        // 右側がソート済み
      } else {

        if (nums[mid] < target && target <= nums[right]) {
          left = mid + 1;
        } else {
          right = mid - 1;
        }
      }

    }

    return -1;
  }

  public static void main(String[] args) {
    System.out.println(new Solution().search(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 0));
    // expected result is 1, but output is -1
    System.out.println(new Solution().search(new int[] { 3, 1 }, 1));
  }
}
