// 問題: 70. Climbing Stairs
// アプローチ: 直前の 2 段についての到達方法数だけを保持する動的計画法
// 時間計算量: O(n)
// 空間計算量: O(1)
public class Solution
{
    public int ClimbStairs(int n)
    {
        // 1 段へは 1 通り、2 段へは 2 通りで到達できる。
        int waysToPreviousStep = 1;
        int waysToCurrentStep = 2;

        // n が 1 または 2 のときは、上で求めた初期値をそのまま返す。
        if (n <= 2)
        {
            return n;
        }

        // i 段へは、(i - 1) 段から 1 段上る方法と、
        // (i - 2) 段から 2 段上る方法を合わせた数だけ到達できる。
        for (int step = 3; step <= n; step++)
        {
            int waysToNextStep = waysToPreviousStep + waysToCurrentStep;
            waysToPreviousStep = waysToCurrentStep;
            waysToCurrentStep = waysToNextStep;
        }

        return waysToCurrentStep;
    }
}
