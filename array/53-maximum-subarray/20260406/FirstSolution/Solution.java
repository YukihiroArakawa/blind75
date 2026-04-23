class Solution {
  public int maxSubArray(int[] nums) {

    // インデックスの要素より、インデックスの数字を累積した値のほうがでかければ累積を続ける
    // そうでない場合は、リスタートする
    // ただし、既存の累積が最大である場合は最大値を更新しない

    int max = -99999;
    int sum = -99999;

    for (int num : nums) {
      if (num > num + sum) {
        sum = num;
      } else {
        sum += num;
      }

      if (max < sum) {
        max = sum;
      }
    }

    return max;
  }

  public static void main(String[] args) {
    System.out.println(new Solution().maxSubArray(new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 }));
    System.out.println(new Solution().maxSubArray(new int[] { 1 }));
    System.out.println(new Solution().maxSubArray(new int[] { 5, 4, -1, 7, 8 }));

  }
}
