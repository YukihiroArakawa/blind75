// 問題: 238. Prjduct of Array Except Self
// アプローチ: 左右からの累積積（Prefix / Suffix Product）
//   - 各要素について「左側すべての積」と「右側すべての積」を掛け合わせることで、
//     自分自身を除いた全要素の積を除算なしで求める
//   - 出力配列を左からの累積積の保存に再利用し、右からの累積積は変数1つで管理することで
//     追加空間 O(1) を実現する
// 時間計算量: O(n) — 配列を2回走査するのみ
// 空間計算量: O(1) — 出力配列を除く追加空間は定数
class Solution {
  public int[] productExceptSelf(int[] nums) {
    int length = nums.length;
    int[] answer = new int[length];

    // 第1パス: answer[i] に nums[0..i-1] の累積積（左側の積）を格納する
    // answer[0] = 1 は、左側に要素がないため積の単位元を設定
    answer[0] = 1;
    for (int i = 1; i < length; i++) {
      answer[i] = answer[i - 1] * nums[i - 1];
    }

    // 第2パス: 右側からの累積積を変数 suffixProduct で管理しながら、
    // answer[i] に右側の積を掛け合わせて最終結果を完成させる
    int suffixProduct = 1;
    for (int i = length - 2; i >= 0; i--) {
      // 現在位置の右隣の要素を suffixProduct に反映
      suffixProduct *= nums[i + 1];
      // 左側の積（すでに格納済み）× 右側の積 = 自分自身を除いた全要素の積
      answer[i] *= suffixProduct;
    }

    return answer;
  }
}
