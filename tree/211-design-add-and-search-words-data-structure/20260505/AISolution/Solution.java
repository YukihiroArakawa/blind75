// 問題: 211. Design Add and Search Words Data Structure
// アプローチ: 208 (Implement Trie) で作った trie を素直に拡張する。
//           addWord は通常の trie 挿入そのまま (HashMap 方式)。
//           search では word の各文字を順に辿るが、'.' に出会ったときだけは
//           「現在ノードの全ての子」に対して残りの suffix を再帰的に試す DFS 分岐を行う。
//           '.' 以外は通常の 1 子のみ辿る。
// なぜ trie か: 単純に List<String> に積んで全件比較すると 1 回の search が O(N * L)。
//             trie なら長さ L の検索が共通プレフィックスを共有して O(L) (ドット無し)、
//             ドットがあっても探索は trie の枝に絞られるため、辞書全件比較より速い。
// 時間計算量:
//   - addWord: O(L) (L = word.length)
//   - search: ドット無しなら O(L)。ドットがある場合、最悪 O(26^d * L)
//             (d = ドット数)。本問題では d <= 2 なので最悪でも数百回程度の分岐。
// 空間計算量: O(N * 26) 相当 (N = trie 内ノード数。HashMap なので実際に登場した文字分だけ)
import java.util.HashMap;
import java.util.Map;

class WordDictionary {

    // trie ノードの内部表現。208 と全く同じ構造。
    // children: 「次の文字 → 子ノード」の対応表
    // isEnd: このノードで終わる単語が登録済みかどうか
    private static class Node {
        Map<Character, Node> children = new HashMap<>();
        boolean isEnd = false;
    }

    private final Node root;

    public WordDictionary() {
        // ルートノードはどの文字にも対応しない空のノードとして用意する。
        root = new Node();
    }

    // 通常の trie 挿入 (208 と同一ロジック)。
    public void addWord(String word) {
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!current.children.containsKey(c)) {
                current.children.put(c, new Node());
            }
            current = current.children.get(c);
        }
        current.isEnd = true;
    }

    // ワイルドカード '.' を含む可能性があるため、再帰 DFS で実装する。
    // 公開 API は単純な signature を保ち、内部ヘルパに「現在ノード」と「読んでいる位置」を渡す。
    public boolean search(String word) {
        return searchFrom(root, word, 0);
    }

    // 現在いる trie ノード node から、word[index..] にマッチする経路があるか判定する。
    private boolean searchFrom(Node node, String word, int index) {
        // word を最後まで読み終えた → このノードで単語が終わっていれば true
        if (index == word.length()) {
            return node.isEnd;
        }

        char currentChar = word.charAt(index);

        if (currentChar == '.') {
            // ワイルドカード: どの文字にもマッチするので、現在ノードの全ての子で残りを試す。
            // 1 つでも true が返ってくれば探索成功。
            for (Node child : node.children.values()) {
                if (searchFrom(child, word, index + 1)) {
                    return true;
                }
            }
            // 全ての子で失敗 → このパスでは見つからなかった
            return false;
        }

        // 通常文字: 該当する子が無ければ即座に false、あればその子に降りて続行。
        Node next = node.children.get(currentChar);
        if (next == null) {
            return false;
        }
        return searchFrom(next, word, index + 1);
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
