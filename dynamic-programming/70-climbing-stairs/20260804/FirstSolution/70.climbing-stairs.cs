public class Solution
{
    // 問題の整理
    // - n=1の場合 1通り
    // - n=2の場合 2通り(1段*2、2段)
    // - n=3の場合 3通り(1段*3、2段+1段、1段+2段)
    // => n=iの組み合わせ = n(i-2) + n(i)
    public int ClimbStairs(int n)
    {
        // n=2の場合は、n=組み合わせ数としてアーリーリターン
        if (n <= 2)
        {
            return n;
        }

        // n=3の場合から数え上げていく
        // i=3のとき、previous=2、current=3
        // i=4のとき、previous=3、current=5
        int previous = 1;
        int current = 2;
        for (int i = 3; i <= n; i++)
        {
            int next = previous + current;
            previous = current;
            current = next;
        }

        return current;
    }

    // public static void Main(string[] args)
    // {
    //     var solution = new Solution();
    //     // expect 8
    //     Console.WriteLine(solution.ClimbStairs(3));
    //     Console.WriteLine(solution.ClimbStairs(4));
    //     Console.WriteLine(solution.ClimbStairs(5));
    // }
}
