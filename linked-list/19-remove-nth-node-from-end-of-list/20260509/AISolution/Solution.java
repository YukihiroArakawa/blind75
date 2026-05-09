// 問題: 19. Remove Nth Node From End of List
// アプローチ: ダミーノードを置き、2本のポインタの間隔を n 個ぶん空けて同時に進める
// 時間計算量: O(sz)
// 空間計算量: O(1)
class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyNode = new ListNode(0, head);
        ListNode fastPointer = dummyNode;
        ListNode slowPointer = dummyNode;

        for (int step = 0; step <= n; step++) {
            fastPointer = fastPointer.next;
        }

        while (fastPointer != null) {
            fastPointer = fastPointer.next;
            slowPointer = slowPointer.next;
        }

        slowPointer.next = slowPointer.next.next;
        return dummyNode.next;
    }
}
