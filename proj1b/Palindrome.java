/**  Project 1B: Applying and Testing Data Structures version 1.0
 *
 *   @author Yimiao Cao 11/21/2019
 *
 * */
public class Palindrome {
    /** Task 2: wordToDeque
     *  Transform string into Deque
     * @param word
     * @return
     */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> d = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

    /** Task 3: isPalindrome
     *  check whether given string is a Palindrome
     * @param word
     * @return true if word is Palindrome, false otherwise
     */
    public boolean isPalindrome(String word) {
        Deque<Character> d = wordToDeque(word);
        return isPalindromeHelper(d);

    }

    /**
     *  private helper function for 'isPalindrome'
     * @param d
     * @return
     */
    private boolean isPalindromeHelper(Deque<Character> d) {
        if (d.size() <= 1) {
            return true;
        }
        if (d.removeFirst() != d.removeLast()) {
            return false;
        }
        return isPalindromeHelper(d);
    }

    /**  Task 4: Generalized Palindrome and OffByOne
     *
     *   Generalized Palindrome.
     * */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> d = wordToDeque(word);
        return isPalindromeHelper(d, cc);
    }

    private boolean isPalindromeHelper(Deque<Character> d, CharacterComparator cc) {
        if (d.size() <= 1) {
            return true;
        }
        if (!cc.equalChars(d.removeFirst(), d.removeLast())) {
            return false;
        }
        return isPalindromeHelper(d, cc);
    }
}


