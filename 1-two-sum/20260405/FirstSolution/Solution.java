class Solution {
  // ナイーブな解答、すべての組み合わせで試すn^2
  public int[] twoSum(int[] nums, int target) {

    for (int i = 0; i < nums.length - 1; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        if (nums[i] + nums[j] == target) {
          return new int[] { i, j };
        }
      }
    }

    return null;
  }

  public static void main(String[] args) {
    Solution sol = new Solution();
    System.out.println(sol.twoSum(new int[] { 2, 7, 11, 15 }, 9));
  }
}
