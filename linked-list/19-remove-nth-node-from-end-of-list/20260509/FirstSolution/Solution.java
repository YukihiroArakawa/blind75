class Solution {
  // 末尾からN番目のノード
  // 一回の捜索で解こうと思うとtailから作って行けると良さそうに思う
  // 違うかノードの付け替えをするのか
  // ぱっと思いつくのは全体を捜索してノードの順番の配列を作成して、ノード数を数えた上で、そこまで戻って付け替える
  // 1, 2, 3, 4, 5
  public ListNode removeNthFromEnd(ListNode head, int n) {
    // fast/slow pointerを使いfast pointerが末尾に達した時点でslow pointerが削除ノードの1つ手前に来るようにする
    // 削除ノードの1つ手前の次のノードを、次の次のノードとすることで完了する
    // N+1件先にFastPointerを配置すればOK
    // ノードが1つだけの可能性を考慮して、ダミーノードを配置しておく

    ListNode dummyNode = new ListNode(0, head);
    ListNode fastPointer = dummyNode;
    ListNode slowPointer = dummyNode;

    // n=2, remove
    // 1, 2, 3, 4
    // 0, 1, 2, 3, 4

    // N+1件先までPointerを配置
    for (int i = 0; i <= n; i++) {
      fastPointer = fastPointer.next;
    }

    while (fastPointer != null) {
      fastPointer = fastPointer.next;
      slowPointer = slowPointer.next;
    }

    slowPointer.next = slowPointer.next.next;
    // ノード数が1つ、ファーストノードが削除されたケースの場合、headは存在していないため
    // dummyNode.nextを返すことで成立する
    return dummyNode.next;
  }
}

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
