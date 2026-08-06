// 問題: 322. Coin Change
// アプローチ: 金額ごとの最小硬貨枚数を記録するボトムアップ動的計画法
// 時間計算量: O(amount * coins.Length)
// 空間計算量: O(amount)
public class Solution
{
    public int CoinChange(int[] coins, int amount)
    {
        // amount + 1 は、作れる金額で必要になる最大枚数より大きい値である。
        // 到達不可能な枚数
        int unreachable = amount + 1;
        // minimumCoins[x]はx円を作るための最小枚数
        int[] minimumCoins = new int[amount + 1];
        Array.Fill(minimumCoins, unreachable);
        // 金額0は硬貨0枚で作れる
        minimumCoins[0] = 0;

        // 各金額について、最後に使う硬貨をすべて試す。
        for (int currentAmount = 1; currentAmount <= amount; currentAmount++)
        {
            foreach (int coin in coins)
            {
                if (coin <= currentAmount)
                {
                    minimumCoins[currentAmount] = Math.Min(
                        minimumCoins[currentAmount],
                        minimumCoins[currentAmount - coin] + 1
                    );
                }
            }
        }

        // unreachableのままの場合は到達不能だったとして-1を返す
        return minimumCoins[amount] == unreachable ? -1 : minimumCoins[amount];
    }
}
