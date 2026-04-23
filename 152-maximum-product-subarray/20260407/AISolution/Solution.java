// 問題: 152. Maximum Product Subarray
// アプローチ: 動的計画法 (DP) - 各位置で「最大積」と「最小積」を同時に保持する
//   負の数が掛かると最大と最小が入れ替わるため、最小積も追跡する必要がある
// 時間計算量: O(n) - 配列を1度走査するだけ
// 空間計算量: O(1) - 定数個の変数のみ使用
class Solution {
  public int maxProduct(int[] nums) {
    // 現在位置までの「最大積」「最小積」と、全体の答えを初期化
    // 最初の要素を起点とする
    int currentMax = nums[0];
    int currentMin = nums[0];
    int result = nums[0];

    // 2要素目から走査
    // 各位置で「その位置を末尾とする部分配列の最大積/最小積」を更新する
    for (int i = 1; i < nums.length; i++) {
      int num = nums[i];

      // num が負の場合、現在の max と min を掛けると大小が反転する
      // そのため候補は「num 単独」「前回max * num」「前回min * num」の3つ
      // 更新前の currentMax を退避（currentMax を上書きすると min 計算で使えなくなるため）
      int prevMax = currentMax;
      currentMax = Math.max(num, Math.max(prevMax * num, currentMin * num));
      currentMin = Math.min(num, Math.min(prevMax * num, currentMin * num));

      // グローバルな最大値を更新
      result = Math.max(result, currentMax);
    }

    return result;
  }
}
