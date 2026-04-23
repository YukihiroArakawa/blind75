// 問題: 121. Best Time to Buy and Sell Stock
// アプローチ: 最小値を保持しながら、各時点での最大利益を計算するワンパスのアルゴリズム
// 時間計算量: O(n) - 配列を一度だけ走査するため
// 空間計算量: O(1) - 最小値と最大利益を保持する定数個の変数のみを使用するため
class Solution {
  public int maxProfit(int[] prices) {
    // これまでに見つかった最小価格を追跡する（初期値は最大値）
    int minPrice = Integer.MAX_VALUE;
    // これまでに見つかった最大利益を追跡する
    int maxProfit = 0;

    for (int currentPrice : prices) {
      // 現在の価格が最小価格より低ければ、最小価格を更新する
      if (currentPrice < minPrice) {
        minPrice = currentPrice;
      }
      // 現在の価格で売却した場合の利益を計算し、最大利益を更新する
      else if (currentPrice - minPrice > maxProfit) {
        maxProfit = currentPrice - minPrice;
      }
    }

    return maxProfit;
  }

  public static void main(String[] args) {

    Solution foo = new Solution();
    System.out.println(foo.maxProfit(new int[] { 7, 1, 5, 3, 6, 4 }));
    System.out.println(foo.maxProfit(new int[] { 7, 6, 4, 3, 1 }));

  }
}
