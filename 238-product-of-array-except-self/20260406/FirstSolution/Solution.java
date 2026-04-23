class Solution {
  // [1,2,3,4]
  public int[] productExceptSelf(int[] nums) {
    int[] answer = new int[nums.length];

    // 1, 1, 2, 6
    // 左側からの累積和はanswerに格納
    answer[0] = 1;
    for (int i = 1; i < nums.length; i++) {
      answer[i] = answer[i - 1] * nums[i - 1];
    }

    // 右側からの累積和は追加変数に格納
    // 24, 12, 4, 1
    int sum = 1;
    for (int i = nums.length - 1; i >= 0; i--) {
      answer[i] = answer[i] * sum;
      // 右側からの累積にかける
      sum *= nums[i];
    }

    return answer;
  }
}
