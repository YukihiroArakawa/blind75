// 問題: 208. Implement Trie (Prefix Tree)
// アプローチ: 各ノードが子ノード配列 (英小文字 26 個分) と「ここで単語が終わるか」フラグを持つ
//           木構造で trie を表現する。挿入・検索・プレフィックス判定はすべて文字列を
//           1 文字ずつ辿るだけで実現できる。
//           AISolution の HashMap 方式に対し、こちらは「英小文字限定」という制約を活かして
//           長さ 26 の配列を使う「最速版」。配列添字アクセスは O(1) でハッシュ計算もないため、
//           HashMap 方式より定数倍速く、メモリ使用量も予測しやすい。
//           ただし英小文字以外を扱いたい場合は HashMap 方式の方が柔軟。
// 時間計算量: insert / search / startsWith いずれも O(L) (L は word/prefix の長さ)
// 空間計算量: O(N * 26) (N は trie 内の総ノード数。最悪ケースで全単語の文字数合計に比例)
class Trie {

    // trie ノードの内部表現。
    // children[c - 'a'] が次の文字に対応する子ノードを指す。null なら未挿入の枝。
    // Java の参照型配列はデフォルトで null 埋めされるため、初期化処理は不要。
    // isEnd は「このノードで終わる単語が trie に挿入済み」であることを示す。
    private static class Node {
        Node[] children = new Node[26];
        boolean isEnd = false;
    }

    private final Node root;

    public Trie() {
        // ルートノードはどの文字にも対応しない空のノードとして用意する。
        root = new Node();
    }

    public void insert(String word) {
        // ルートから順に文字を辿り、子ノードが無ければ作成しながら降りていく。
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            // 'a' = 0, 'b' = 1, ..., 'z' = 25 の添字に変換 (英小文字限定だから可能)
            int index = word.charAt(i) - 'a';
            if (current.children[index] == null) {
                current.children[index] = new Node();
            }
            current = current.children[index];
        }
        // 最後の文字に対応するノードに「単語の終端」マークを付ける。
        current.isEnd = true;
    }

    public boolean search(String word) {
        // word の各文字を辿り、最後まで到達した上で終端マークが付いているか確認する。
        Node node = traverse(word);
        return node != null && node.isEnd;
    }

    public boolean startsWith(String prefix) {
        // プレフィックスとして存在するかは、最後まで到達できれば十分 (終端マーク不要)。
        return traverse(prefix) != null;
    }

    // 文字列を trie 上で辿り、到達したノードを返す。途中で枝が切れていれば null。
    private Node traverse(String s) {
        Node current = root;
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            if (current.children[index] == null) {
                return null;
            }
            current = current.children[index];
        }
        return current;
    }
}
