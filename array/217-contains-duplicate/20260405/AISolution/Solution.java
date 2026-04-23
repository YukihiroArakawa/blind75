// 問題: 217. Contains Duplicate
// アプローチ: HashSetを使い、要素を順に追加しながら重複を検出する
// HashSet.addは要素が既に存在する場合にfalseを返すため、重複判定に利用できる
// 時間計算量: O(n) - 配列を1回走査、HashSetの操作は平均O(1)
// 空間計算量: O(n) - 最悪の場合、全要素をHashSetに格納する
class Solution {
    public boolean containsDuplicate(int[] nums) {
        // 出現済みの要素を記録するためのHashSet
        java.util.HashSet<Integer> seen = new java.util.HashSet<>();

        for (int num : nums) {
            // addがfalseを返す = 既にSetに存在する = 重複あり
            if (!seen.add(num)) {
                return true;
            }
        }

        // 全要素を走査しても重複が見つからなかった
        return false;
    }
}
