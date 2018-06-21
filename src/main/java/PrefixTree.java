import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
/**
 * Prefix Tree.
 *
 * @author AnnaYatsun(annashtepa512@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class PrefixTree {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("words.txt");


        Trie trie = new Trie();
        LinkedList<Pair<String>> queue = new LinkedList<Pair<String>>();

        // используется для вычисления общего количества составных слов
        HashSet<String> compoundWords = new HashSet<String>();


        @SuppressWarnings("resource")
        Scanner s = new Scanner(file);

        String word;
        List<Integer> sufIndices;	// суфиксы шндексов слов

// чтение слов из файла заполняем очередь словами с суффиксами, которые кандидаты должны быть составными словами вставляем каждое слово в trie
        while (s.hasNext()) {
            word = s.next();
            sufIndices = trie.getSuffixesStartIndices(word);

            for (int i : sufIndices) {
                if (i >= word.length())
                    break;

                queue.add(new Pair<String>(word, word.substring(i)));
             }

            trie.insert(word);
        }

/// пара слов  суффикс
        Pair<String> p;
        int maxLength = 0;

        String longest = "";
        String sec_longest = "";

        while (!queue.isEmpty()) {
            p = queue.removeFirst();
            word = p.second();

            sufIndices = trie.getSuffixesStartIndices(word);

            // если суффиксы не найдены, это означает, что не найдены префиксы отбросить пару и проверить следующую пару
            if (sufIndices.isEmpty()) {
                continue;
            }


            for (int i : sufIndices) {
                if (i > word.length()) {
                    break;
                }

                if (i == word.length()) {


// нет суффикса, означает, что это конкантинируемое  слово тогда  проверяем, является ли сложное слово самым длинным

                    if (p.first().length() > maxLength) {
                        //sec_maxLength = maxLength;
                        sec_longest = longest;
                        maxLength = p.first().length();
                        longest = p.first();
                    }

                    compoundWords.add(p.first());	// the word is compound word

                } else {
                    queue.add(new Pair<String>(p.first(), word.substring(i)));
                }
            }
        }

        System.out.println("Longest Compound Word: " + longest);
        System.out.println("Second Longest Compound Word: " + sec_longest);
        System.out.println("Total Number of Compound Words: " + compoundWords.size());
    }
}

