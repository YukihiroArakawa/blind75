class Solution {
  // 小値と大値の差額が利ざやになる
  // 方針1：ナイーブに得ならすべて繰り返し処理する
  public int maxProfit(int[] prices) {

    int minPrice = Integer.MAX_VALUE;
    int maxProfit = 0;

    for (int price : prices) {
      if (minPrice > price) {
        minPrice = price;
      } else if (price - minPrice > maxProfit) {
        maxProfit = price - minPrice;
      }
    }

    return maxProfit;
  }

  public static void main(String[] args) {

    Solution foo = new Solution();
    System.out.println(foo.maxProfit(new int[] { 7, 1, 5, 3, 6, 4 }));

  }
}
