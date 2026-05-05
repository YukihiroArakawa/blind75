import java.util.HashMap;
import java.util.Map;

class WordDictionary {
  // Trieをベースにすれば、おｋ。
  private TrieNode trieNode;

  public WordDictionary() {
    this.trieNode = new TrieNode();
  }

  public void addWord(String word) {
    this.trieNode.insert(word);
  }

  public boolean search(String word) {
    return this.trieNode.search(this.trieNode.root, word, 0);
  }

  private static class TrieNode {

    private Node root;

    public TrieNode() {
      this.root = new Node();
    }

    // insert
    private void insert(String word) {
      Node currentNode = root;

      for (int i = 0; i < word.length(); i++) {
        char currentChar = word.charAt(i);
        if (!currentNode.children.containsKey(currentChar)) {
          currentNode.children.put(currentChar, new Node());
        }

        currentNode = currentNode.children.get(currentChar);
      }

      currentNode.isEnd = true;
    }

    // 子ノードに該当する文字があるか探索
    private boolean search(Node node, String word, int charIndex) {
      // ベースケース, 最後の文字まで読み切ったら返す
      if (word.length() == charIndex) {
        return node.isEnd;
      }

      char currentChar = word.charAt(charIndex);

      // ワイルドカードに該当したら子ノードを全て探索
      if (currentChar == '.') {
        for (Node nextNode : node.children.values()) {
          if (search(nextNode, word, charIndex + 1)) {
            return true;
          }
        }
        return false;
      }

      Node nextNode = node.children.get(currentChar);
      if (nextNode == null) {
        return false;
      }

      return search(nextNode, word, charIndex + 1);
    }
  }

  private static class Node {
    private Map<Character, Node> children = new HashMap<>();
    private boolean isEnd = false;
  }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
