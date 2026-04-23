// 問題: 11. Container With Most Water
// アプローチ: 二点法（two pointers）。両端から開始し、面積を計算しつつ
//   「短い側のポインタ」を内側に動かしていく。
//   理由: 面積 = min(h[left], h[right]) * (right - left)。
//   幅は必ず減るので、面積を増やす唯一の希望は「短い側を動かしてより高い線に出会うこと」。
//   高い側を動かしても min は変わらず（むしろ下がる可能性があり）、幅は減るので必ず損。
// 時間計算量: O(n)
// 空間計算量: O(1)
class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxWater = 0;

        while (left < right) {
            int width = right - left;
            int shorterHeight = Math.min(height[left], height[right]);
            int area = width * shorterHeight;
            maxWater = Math.max(maxWater, area);

            // 短い方を内側に動かす（高い方を動かしても改善の見込みがない）
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxWater;
    }
}
