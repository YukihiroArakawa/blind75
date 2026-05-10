// 問題: 143. Reorder List
// アプローチ: 中央で分割し、後半を反転してから前半と後半を交互にマージする
// 時間計算量: O(n)
// 空間計算量: O(1)
class ListNode {
  int val;
  ListNode next;

  ListNode() {
  }

  ListNode(int val) {
    this.val = val;
  }

  ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }
}

class Solution {
  public void reorderList(ListNode head) {
    if (head == null || head.next == null) {
      return;
    }

    ListNode slowPointer = head;
    ListNode fastPointer = head;

    // 中央を探す
    // 1, (2), [3], 4, 5
    // 1, 2 , (3), 4, [5]
    while (fastPointer.next != null && fastPointer.next.next != null) {
      slowPointer = slowPointer.next;
      fastPointer = fastPointer.next.next;
    }

    // 中央から後ろの後半のノードリストを反転させる
    ListNode secondHalfHead = reverseList(slowPointer.next);
    // 中央のノードが末尾になるため
    slowPointer.next = null;

    ListNode firstHalfPointer = head;
    ListNode secondHalfPointer = secondHalfHead;

    // ノードの付け替えをする
    // [1, 2, 3], [4, 5]
    // 1 -> 4, 4 -> 2
    while (secondHalfPointer != null) {
      ListNode firstHalfNext = firstHalfPointer.next;
      ListNode secondHalfNext = secondHalfPointer.next;

      firstHalfPointer.next = secondHalfPointer;
      secondHalfPointer.next = firstHalfNext;

      firstHalfPointer = firstHalfNext;
      secondHalfPointer = secondHalfNext;
    }
  }

  private ListNode reverseList(ListNode head) {
    ListNode previousNode = null;
    ListNode currentNode = head;

    while (currentNode != null) {
      ListNode nextNode = currentNode.next;
      currentNode.next = previousNode;
      previousNode = currentNode;
      currentNode = nextNode;
    }

    return previousNode;
  }
}
