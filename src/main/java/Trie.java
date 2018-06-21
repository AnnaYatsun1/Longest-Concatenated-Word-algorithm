import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class Trie {
    private class TrieNode {
        @SuppressWarnings("unused")
        private char val;			// символ, хранящийся в узле
        private HashMap<Character, TrieNode> children;
        private boolean isWord;

        public TrieNode(char val) {
            this.val = val;
            children = new HashMap<Character, TrieNode>();
            isWord = false;
        }


        public void addChild(char child) {
            children.put(child, new TrieNode(child));
        }

        // Получаем дочерний элемент триена, который имеет тот же символ
        public TrieNode getChild(char child) {
            if (!children.keySet().contains(child)) {
                return null;
            }

            return children.get(child);
        }

        // возвращаем true, если существует дочерний объект
        public boolean containsChild(char child) {
            return children.keySet().contains(child);
        }
    }

    private TrieNode root;
    private TrieNode curr;

    public Trie() {
        root = new TrieNode(' ');	// root
        curr = root;
    }

    // вставляем слова в trie
    public void insert(String s) {
        char letter;
        curr = root;

        // обход каждой буквы в слове, обновление структуры Trie при отсуцтвии

        for (int i = 0; i < s.length(); i++) {
            letter = s.charAt(i);

            if (!curr.containsChild(letter)) {
                curr.addChild(letter);
            }

            curr = curr.getChild(letter);
        }


        curr.isWord = true;
    }

    // возвращаем начальние индексы наших суфиксов слова
    public List<Integer> getSuffixesStartIndices(String s) {
        List<Integer> indices = new LinkedList<Integer>();	// store indices
        char letter;
        curr = root;	// start from root

        for (int i = 0; i < s.length(); i++) {
            letter = s.charAt(i);

            // если текущая буква не имеет одной буквы в качестве дочернего , что означает, что true в настоящее время не имеет отношения  возвращает индексы суффиксов
            if (!curr.containsChild(letter))
                return indices;


            curr = curr.getChild(letter);

            // если буква - это конец слова, это означает, что после буквы является суффиксом то нужно обновить индексы
            if (curr.isWord)
                indices.add(i + 1);
        }

        return indices;
    }

}