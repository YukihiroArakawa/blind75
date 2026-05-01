// 問題: 21. Merge Two Sorted Lists
// アプローチ: ダミーヘッド + 2 ポインタによる反復マージ
//   - ダミーノード（番兵）を先頭に置くことで「最初のノードが決まっていないときの分岐」を消す
//   - tail ポインタが「現在のマージ済みリストの末尾」を指し、毎回小さい方のノードを連結する
//   - どちらかが尽きたら、残った側をそのまま末尾に繋げる（既にソート済みなので走査不要）
// 時間計算量: O(n + m)
//   - n, m はそれぞれのリスト長。各ノードを高々 1 回だけ進めるため線形
// 空間計算量: O(1)
//   - ダミーノードと数本のポインタ以外を確保しないため定数
//   - 既存ノードを使い回す（splice）方式なので新しい ListNode は dummy のみ

// Definition for singly-linked list.
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
  public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    // ダミーヘッド: 結果の先頭を「dummy.next」で取り出せるようにする工夫
    // これにより「結果がまだ空のとき」と「すでに繋いだとき」を同じコードで扱える
    ListNode dummy = new ListNode();
    ListNode tail = dummy;

    // 両リストにノードがある間は、小さい方を tail に連結していく
    while (list1 != null && list2 != null) {
      if (list1.val <= list2.val) {
        tail.next = list1;
        list1 = list1.next;
      } else {
        tail.next = list2;
        list2 = list2.next;
      }
      tail = tail.next;
    }

    // 片方が尽きた時点で、残ったリストはすでにソート済みなのでまとめて繋げる
    // どちらか一方は必ず null なので、null チェックは不要
    tail.next = (list1 != null) ? list1 : list2;

    return dummy.next;
  }
}
