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
  // 再帰で綺麗に処理したい
  // 1, 2, 3, 4, 5
  // 2, 1
  // 3, 2, 1
  // 4, 3, 2, 1
  // 5, 4, 3, 2, 1
  // ヘッドの次のノードのネクストポインタをヘッドに変えるを繰り返したらいけそう？
  public ListNode reverseList(ListNode head) {
    if (head == null || head.next == null)
      return head;

    ListNode newHead = reverseList(head.next);
    head.next.next = head;
    head.next = null;
    return newHead;
  }

  public static void main(String[] args) {

  }
}
