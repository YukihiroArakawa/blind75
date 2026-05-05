// 問題: 208. Implement Trie (Prefix Tree)
// アプローチ: 各ノードが「文字 → 子ノード」の HashMap と「ここで単語が終わるか」フラグを持つ
//           木構造で trie を表現する。挿入・検索・プレフィックス判定はすべて文字列を
//           1 文字ずつ辿るだけで実現できる。
//           HashMap 方式は配列方式 (children[26]) より少しオーバーヘッドがあるが、
//           「文字 → ノード」の対応関係がコード上で直接見えるため読みやすい。本問題の
//           制約 (操作数最大 3*10^4、各単語最大 2000 文字) では十分高速。
// 時間計算量: insert / search / startsWith いずれも O(L) (L は word/prefix の長さ、HashMap は平均 O(1))
// 空間計算量: O(全挿入文字数) — trie 内の総ノード数に比例
import java.util.HashMap;
import java.util.Map;

class Trie {

    // trie ノードの内部表現。
    // children は「次の文字 → 子ノード」の対応表。HashMap なので、キーに無い文字は単に未挿入を意味する。
    // isEnd は「このノードで終わる単語が trie に挿入済み」であることを示す。
    private static class Node {
        Map<Character, Node> children = new HashMap<>();
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
            char c = word.charAt(i);
            // children に c が無ければ新しいノードを作って登録する。
            if (!current.children.containsKey(c)) {
                current.children.put(c, new Node());
            }
            // c に対応する子ノードに降りる。
            current = current.children.get(c);
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
            current = current.children.get(s.charAt(i));
            if (current == null) {
                return null;
            }
        }
        return current;
    }
}
