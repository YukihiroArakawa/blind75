class Solution {
  // 小値と大値の差額が利ざやになる
  // ただし最小値のほうが先に来ている必要がある
  // 方針1：ナイーブに得ならすべて繰り返し処理する
  public int maxProfit(int[] prices) {

    if (prices.length <= 1)
      return 0;

    int minPrice = prices[0];
    int minPriceIndex = 0;
    int maxPrice = prices[0];
    int maxPriceIndex = 9999;

    for (int i = 1; i < prices.length; i++) {

      // 価格が小さい && 最大価格より前の日
      if (minPrice >= prices[i] && maxPriceIndex >= i) {
        minPriceIndex = i;
        minPrice = prices[i];
      } else if (maxPrice <= prices[i] && minPriceIndex <= i) {
        maxPriceIndex = i;
        maxPrice = prices[i];
      }

    }

    return maxPrice - minPrice > 0 ? maxPrice - minPrice : 0;
  }

  public static void main(String[] args) {

    Solution foo = new Solution();
    System.out.println(foo.maxProfit(new int[] { 7, 1, 5, 3, 6, 4 }));

  }
}
