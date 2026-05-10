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
  // 0) ヘッドがNULL、ヘッドの次がNULLの場合は処理せず終了
  // 1) 前半後半でノードのグループを分ける
  // 2) 後半ノードを反転
  // 3) 前半後半を交互につなぎ替える
  public void reorderList(ListNode head) {

    // 0) ヘッドがNULL、ヘッドの次がNULLの場合は処理せず終了
    if (head == null || head.next == null)
      return;

    // 1) 前半後半でノードのグループを分ける
    // 1.1. 前半後半で分けるために中央のノードを見つける
    // 1, (2), [3], 4, 5
    // 1, 2 , (3), 4, [5]
    ListNode slowPointer = head;
    ListNode fastPointer = head;
    while (fastPointer.next != null && fastPointer.next.next != null) {
      slowPointer = slowPointer.next;
      fastPointer = fastPointer.next.next;
    }

    // 2) 後半ノードを反転
    ListNode secondHalfNode = reverseNode(slowPointer.next);
    ListNode firstHalfNode = head;
    slowPointer.next = null; // 中央ノードは末尾になるため次のノードはNULL

    // 3) 前半後半を交互につなぎ替える
    switchNode(firstHalfNode, secondHalfNode);
  }

  private void switchNode(ListNode firstHalfNode, ListNode secondHalfNode) {

    // 1, 4, 2, 5, 3

    // f) 1, 2, 3
    // s) 4, 5
    // 1) 1 -> 4 , 4 -> 2
    // 2) 2 -> 5, 5 -> 3
    // result 1 , 4 , 2, 5, 3

    while (secondHalfNode != null) {
      ListNode firstHalfNext = firstHalfNode.next;
      ListNode secondHalfNext = secondHalfNode.next;

      firstHalfNode.next = secondHalfNode;
      secondHalfNode.next = firstHalfNext;

      firstHalfNode = firstHalfNext;
      secondHalfNode = secondHalfNext;
    }
  }

  private ListNode reverseNode(ListNode node) {
    // 4, 5, 6
    // 4 -> null(1つ前のノード)
    // 5(次のノード) -> 4(1つ前のノード) -> null
    // 6 -> 5 -> 4 -> null

    ListNode currentNode = node;
    ListNode previousNode = null;

    while (currentNode != null) {
      ListNode nextNode = currentNode.next;
      currentNode.next = previousNode;
      previousNode = currentNode;
      currentNode = nextNode;
    }

    return previousNode;
  }
}
