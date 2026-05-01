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
  // 循環の判定
  // 循環していなければどこかでNULLにたどり着く
  // 進行速度が異なる2つのポインタを使うことで循環を検出する
  // 同じ値にポインタが合致するタイミングがあれば循環
  public boolean hasCycle(ListNode head) {
    if (head == null || head.next == null) {
      return false;
    }

    ListNode slow = head;
    ListNode fast = head.next;

    while (true) {
      if (fast == null || fast.next == null || fast.next.next == null) {
        return false;
      }

      if (slow == fast) {
        return true;
      }

      slow = slow.next;
      fast = fast.next.next;
    }
  }
}
