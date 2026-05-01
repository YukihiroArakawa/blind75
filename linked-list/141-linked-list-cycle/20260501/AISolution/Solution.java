// 問題: 141. Linked List Cycle
// アプローチ: Floyd の循環検出アルゴリズム（うさぎとかめ / Tortoise and Hare）
//   - slow ポインタは 1 ステップずつ、fast ポインタは 2 ステップずつ進める
//   - 循環がある場合、fast は循環内を高速に周回し必ず slow に追いつく
//   - 循環がない場合、fast が null に到達してループを抜ける
// 時間計算量: O(n)
//   - 循環がない場合: fast が末尾に到達するまで O(n)
//   - 循環がある場合: slow が循環に入った時点から、fast との相対速度は 1 ステップ/回なので、
//     循環の長さを k とすると最大 k ステップで追いつく → 全体で O(n)
// 空間計算量: O(1)
//   - HashSet で訪問済みノードを保持する解法は O(n) 空間が必要だが、
//     こちらは 2 つのポインタのみで判定できるためフォローアップ条件を満たす

// Definition for singly-linked list.
class ListNode {
  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
    next = null;
  }
}

public class Solution {
  public boolean hasCycle(ListNode head) {
    // 空リストや単一ノードでも安全に動くよう、fast の前進時に null チェックを行う
    ListNode slow = head;
    ListNode fast = head;

    while (fast != null && fast.next != null) {
      // slow は 1 ステップ、fast は 2 ステップ進める
      slow = slow.next;
      fast = fast.next.next;

      // 同一ノードを参照していれば循環あり
      // （値ではなく参照を比較する点に注意。LeetCode 制約上、ノードの一意性は参照で判定する）
      if (slow == fast) {
        return true;
      }
    }

    // fast が末尾（null）に到達した場合は循環なし
    return false;
  }
}
