import java.util.Arrays;

// 問題: 322. Coin Change
// アプローチ: 金額ごとの最小硬貨枚数を記録するボトムアップ動的計画法
// 時間計算量: O(amount * coins.length)
// 空間計算量: O(amount)
class Solution {
    public int coinChange(int[] coins, int amount) {
        // amount + 1 は、作れる金額で必要になる最大枚数より大きい値である。
        int unreachable = amount + 1;
        int[] minimumCoins = new int[amount + 1];
        Arrays.fill(minimumCoins, unreachable);
        minimumCoins[0] = 0;

        // 各金額について、最後に使う硬貨をすべて試す。
        for (int currentAmount = 1; currentAmount <= amount; currentAmount++) {
            for (int coin : coins) {
                if (coin <= currentAmount) {
                    minimumCoins[currentAmount] = Math.min(
                        minimumCoins[currentAmount],
                        minimumCoins[currentAmount - coin] + 1
                    );
                }
            }
        }

        return minimumCoins[amount] == unreachable ? -1 : minimumCoins[amount];
    }
}
