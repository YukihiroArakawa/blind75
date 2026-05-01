// 問題: 206. Reverse Linked List
// アプローチ: 3 ポインタ法による反復的な反転
//   - prev / current / next の 3 つのポインタを使い、各ノードの next 参照を 1 つずつ反転する
//   - 連結リストは「次のノードへの参照」だけを持つため、参照を書き換える前に
//     必ず元の next を退避してから付け替える必要がある
//   - 再帰版もあるが、スタック消費が O(n) になりノード数 5000 でも許容範囲ではあるものの、
//     反復版の方が空間効率が良いため本実装では反復を採用
// 時間計算量: O(n)
//   - 各ノードを 1 回ずつ訪問し、定数時間でポインタを書き換える
// 空間計算量: O(1)
//   - 追加で使うのは 3 つのポインタ変数のみで、入力サイズに依存しない

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
  public ListNode reverseList(ListNode head) {
    // prev: 反転後リストの先頭 (最初は null = 末尾の next になる)
    // current: 現在処理中のノード
    ListNode prev = null;
    ListNode current = head;

    while (current != null) {
      // current.next を反転すると元の次のノードを失うので、先に退避しておく
      ListNode nextNode = current.next;

      // current の next を 1 つ前のノード (prev) に向けることで参照を反転
      current.next = prev;

      // prev と current を 1 つずつ進める
      // - prev は「これまでに反転済みのリストの先頭」を指し続ける
      // - current は退避していた次のノードへ進む
      prev = current;
      current = nextNode;
    }

    // ループ終了時、current は null (末尾の次) を指しているので、
    // 反転済みリストの新しい先頭は prev に格納されている
    return prev;
  }

  public static void main(String[] args) {

  }
}
