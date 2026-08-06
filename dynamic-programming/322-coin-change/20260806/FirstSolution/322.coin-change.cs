public class Solution
{
    // 最小枚数を返すとあるので、大きい額から使えないか探すと良さそう？
    // 目標額amountが0になるケースはそのまま0で返せば良さそう
    // 額面を作れない場合は -1 を返す必要がある
    // 無限に額面を使える
    // 制約
    // - 1 <= coins.length <= 12
    // - 1 <= coins[i] <= 2^31 - 1
    // - 0 <= amount <= 10^4
    public int CoinChange(int[] coins, int amount)
    {
        int unreachable = amount + 1;

        // 各金額ごとにその金額を構成する最小枚数を調べる
        int[] minCoins = new int[amount + 1]; // 0~amount yenまでの金額でそれぞれ最小で必要な枚数を記録
        Array.Fill(minCoins, unreachable);
        minCoins[0] = 0;

        for (int currentAmount = 1; currentAmount <= amount; currentAmount++)
        {
            // コインの種別ごとに、目標額に達する最後がそのコインだった場合、何枚必要かを計算していく
            foreach (var coin in coins)
            {
                if (coin <= currentAmount)
                {
                    minCoins[currentAmount] = Math.Min(minCoins[currentAmount], minCoins[currentAmount - coin] + 1);
                }
            }
        }

        return minCoins[amount] == unreachable ? -1 : minCoins[amount];
    }
}
