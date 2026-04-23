class Solution {
  // 方針
  // - numsをループして1つずつ掛け合わせて言って最大になる場合は掛け合わせる、そうでない場合はnums[i]を入れる
  // - ただし-をかけると逆転するため、最小値も保持する
  // - 時間計算量: O(n)
  // - 空間計算量: O(1)
  public int maxProduct(int[] nums) {
    int currentMax = nums[0];
    int currentMin = nums[0];
    int result = nums[0];

    for (int i = 1; i < nums.length; i++) {
      int prevMax = currentMax;
      currentMax = Math.max(nums[i], Math.max(prevMax * nums[i], currentMin * nums[i]));
      currentMin = Math.min(nums[i], Math.min(currentMin * nums[i], prevMax * nums[i]));

      result = Math.max(result, currentMax);
    }

    return result;
  }

  // nums = [2,3,-2,4]
  // max, min,
  // 2, 2
  // 6, 2
  // 6, -12
  //
  public static void main(String[] args) {
    System.out.println(new Solution().maxProduct(new int[] { 2, 3, -2, 4 }));
    System.out.println(new Solution().maxProduct(new int[] { -2, 0, -1 }));
  }
}
