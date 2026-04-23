class Solution {
  public int maxArea(int[] height) {

    // twopointers
    int leftId = 0;
    int rightId = height.length - 1;
    int maxArea = 0;

    while (leftId < rightId) {
      int currentArea = Math.min(height[leftId], height[rightId]) * (rightId - leftId);
      maxArea = Math.max(maxArea, currentArea);

      // 右側の方が大きい場合
      if (height[leftId] < height[rightId]) {
        leftId++;
      } else {
        rightId--;
      }
    }

    return maxArea;
  }

  public static void main(String[] args) {
    System.out.println(new Solution().maxArea(new int[] { 1, 8, 6, 2, 5, 4, 8, 3, 7 }));
    System.out.println(new Solution().maxArea(new int[] { 1, 1 }));
    System.out.println(new Solution().maxArea(new int[] { 8, 7, 2, 1 }));
  }
}
