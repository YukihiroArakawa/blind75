class Solution {
  public int missingNumber(int[] nums) {

    int size = nums.length;
    int expectedSum = size * (size + 1) / 2;
    int actualSum = 0;
    for (int num : nums) {
      actualSum += num;
    }

    return expectedSum - actualSum;
  }
}
