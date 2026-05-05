import java.util.HashMap;
import java.util.Map;

class Trie {
  // ノードを作る
  private static class Node {
    // 次の文字を持つノードがあるかを表すマップ
    private Map<Character, Node> children = new HashMap<>();
    // 終端かどうかを表すフラグ
    private boolean isEnd = false;
  }

  // rootノードを保持する変数
  // search時にrootから辿る必要があるため
  private Node root;

  public Trie() {
    this.root = new Node();
  }

  // ルートからノードをたどりながら、存在しない文字があればノードを追加する
  public void insert(String word) {
    Node currentNode = root;

    for (int i = 0; i < word.length(); i++) {
      char currentChar = word.charAt(i);
      if (!currentNode.children.containsKey(currentChar)) {
        Node newNode = new Node();
        currentNode.children.put(currentChar, newNode);
      }

      currentNode = currentNode.children.get(currentChar);
    }

    currentNode.isEnd = true;
  }

  public boolean search(String word) {

    Node currentNode = this.root;

    for (int i = 0; i < word.length(); i++) {
      char currentChar = word.charAt(i);

      if (!currentNode.children.containsKey(currentChar)) {
        return false;
      }

      currentNode = currentNode.children.get(currentChar);
    }

    return currentNode.isEnd;
  }

  // rootからたどっていく
  public boolean startsWith(String prefix) {

    Node currentNode = this.root;

    for (int i = 0; i < prefix.length(); i++) {
      char currentChar = prefix.charAt(i);

      if (!currentNode.children.containsKey(currentChar)) {
        return false;
      }

      currentNode = currentNode.children.get(currentChar);
    }

    return true;
  }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
