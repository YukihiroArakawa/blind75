// 問題: 1. Two Sum
// アプローチ: HashMapを使い、各要素の補数(target - num)を探索する
// 配列を1回走査しながら、値→インデックスのマッピングを構築する
// 時間計算量: O(n) - 配列を1回走査、HashMapの操作は平均O(1)
// 空間計算量: O(n) - 最悪の場合、全要素をHashMapに格納する
class Solution {
  public int[] twoSum(int[] nums, int target) {
    // 値をキー、インデックスを値としてマッピングするHashMap
    java.util.HashMap<Integer, Integer> numToIndex = new java.util.HashMap<>();

    for (int i = 0; i < nums.length; i++) {
      // 現在の要素と足してtargetになる値（補数）を計算
      int complement = target - nums[i];

      // 補数が既にHashMapに存在すれば、ペアが見つかった
      if (numToIndex.containsKey(complement)) {
        return new int[] { numToIndex.get(complement), i };
      }

      // 現在の要素をHashMapに登録し、後続の要素から参照できるようにする
      numToIndex.put(nums[i], i);
    }

    // 問題の制約上、必ず解が存在するためここには到達しない
    return new int[] {};
  }
}
