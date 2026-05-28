// 問題: 190. Reverse Bits
// アプローチ: 下位ビットを 1 つずつ取り出し、結果を左にずらしながら逆順に積み上げる
// 時間計算量: O(1) ただし 32 回の固定ループ
// 空間計算量: O(1)
class Solution {
    public int reverseBits(int n) {
        int reversedValue = 0;

        for (int bitIndex = 0; bitIndex < 32; bitIndex++) {
            // これまで作った結果を 1 ビット左へずらし、新しいビットを入れる場所を空ける。
            reversedValue <<= 1;

            // 元の値の一番右のビットだけを取り出して結果の末尾に加える。
            reversedValue |= (n & 1);

            // 次のビットを取り出せるように、元の値を符号なし右シフトする。
            n >>>= 1;
        }

        return reversedValue;
    }
}
