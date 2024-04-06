package dictionary;

/** PrefixTree class, implements Dictionary interface.
 *  Can be used as a spell checker. */
public class PrefixTree implements Dictionary {

    // --------- Private class Node ------------
    /* Represents a node in a  prefix tree ("trie", "26-ary tree") */
    private class Node {
        Node children[]; // array of children (26 children, one corresponding to each English letter)
        boolean isWord; // true if by concatenating "edges" on the path from the root to this node, we get a valid word

        Node() {
            isWord = false;
            children = new Node[26]; // initialize the array of children
        }
    } // end of inner class Node

    private Node root; // the root of the tree

    public PrefixTree() {
        root = new Node();
    }

    /**
     * Creates a prefix tree using words from the given file.
     * The file contains one word per line.
     * @param filename the name of the file with words
     */
    public PrefixTree(String filename) {
        // FILL IN CODE:
        // Read each word from the file, add it to the tree

    }

    /** Adds a given word to the dictionary.
     * @param word the word to add to the dictionary
     */
    public void add(String word) {
        add(word.toLowerCase(), root);
    }

    /**
     * Checks if a given word is in the dictionary
     * @param word the word to check
     * @return true if the word is in the dictionary, false otherwise
     */
    public boolean check(String word) {
        return check(word.toLowerCase(), root);
    }

    /**
     * Checks if a word with the given prefix is in the dictionary
     * @param prefix The prefix of a word
     * @return true if this prefix is a prefix of any word in the dictionary,
     * and false otherwise
     * Example: "ca" is a valid prefix, if there is a word in the tree that
     * starts with "ca" (like the word "cat").
     */
    public boolean checkPrefix(String prefix) {
        return checkPrefix(prefix.toLowerCase(), root);
    }

    /**
     * Deletes a given word from the tree, possibly deletes some branches that are no longer needed.
     * @param word the word to be deleted
     */
    @Override
    public void delete(String word) {
        // FILL IN CODE: call the private helper delete method. Which parameters would it take?

    }

    /**
     * If the word is a valid word in the tree, return the word.
     * If the word is NOT a valid word in this tree, return a valid
     * word from the tree that has the longest common prefix with the given word.
     * Refer to the description of this method in the pdf to understand which
     * word to return if multiple such words exist in the tree ( get the word from the "leftmost" branch).

     * @param word the target word
     * @return a valid word from the tree that has the longest common prefix with the given word
     */
    @Override
    public String suggest(String word) {
        // Find a node with the longest common prefix (write a helper method)
       //  Find word(s) below the "longest common prefix" node (write another helper method)

        return ""; // change
    }

    /** Return a string representation of the prefix tree.
     * See expectedDictionary1.txt and expectedTree_Small.txt to understand the format.
     * @return string, representing the tree.
     */
    public String toString() {
        return toString(root, 0);
    }

    // ---------- Private helper methods ---------------

    /**
     * Adds (recursively) a given string to the subtree with the given root
     * @param word a word to add
     * @param node root of a subtree

     */
    private void add(String word, Node node) {

        // Base case
        if (word.length() == 0) {
            // This is the node where this word "ends"
            node.isWord = true;
            return;
        }

        int index = (int) word.charAt(0) - (int) 'a'; // index of the child that corresponds to the first letter in s

        // If this child is null, point it to a new Node
        if (node.children[index] == null) {
            node.children[index] = new Node();
        }

        // Recursive call to add on remaining part of word
        add(word.substring(1), node.children[index]);

    }


    /** A private recursive method to check whether a given word is stored in the given subtree.
     *  Checks if there is a node with isWord = true, such that if we concatenate all the letters
     *  in the edges on the path from the root of the subtree to this node, we will get the given word
     * Example: the tree below stores the word "ale", so check ("ale", root) would  return true.
      a
        l
          e*
     * @param word the string to check
     * @param node the root of a subtree
     * @return true if the word is in the dictionary, false otherwise
     */
    private boolean check(String word, Node node) {
        // Base case - we have come to the end of the word
        if (word.isEmpty()) {
            return node.isWord; // return the boolean value of 'valid bit'
        }

        // Otherwise - get the index of the next character in the word
        int index = (int) word.charAt(0) - (int) 'a';
        // If the child node at the index is null, the word is not in the dictionary
        if (node.children[index] == null) {
            return false;
        }

        // Recursively call - check the remaining part of the word
        return check(word.substring(1), node.children[index]);
    }

    /**
     * A private recursive method to check whether there is a word in the prefix tree
     * that starts with the given prefix
     * Example: consider the tree below.
     * checkPrefix("a") should return true, because "ale" starts with "a".
     * checkPrefix("al") should also return true because "ale" starts with "al".
     * checkPrefix("ale") would also be true.
     * On the other hand, checkPrefix ("le") will return false on the tree shown below.
     * Because the tree stores a single word "ale" and it does not start with "le".
     *
       a
         l
           e*

     * @param prefix the prefix
     * @param node root of the subtree
     * @return true if there is at least one word in the dictionary that starts with this prefix, false otherwise
     */
    private boolean checkPrefix(String prefix, Node node) {
        // FILL IN CODE:
        // Must be recursive

        return false;
    }

    // FILL IN CODE: Add a private delete method.
    // Think of what parameters (if any) it needs to take in addition to word and node.
    // Note that deleting a node involves more than just setting isWord to false, you'd possibly need to remove some branches.


    /**
     * Returns a string representing a tree: uses indentations to show nodes at different levels
     * Shows the letter corresponding to the incoming edge of the node.
     * Adds * to the prefix of the node, if the valid bit is set to true
     * @param node the root of the tree
     * @param numIndentations the number of indentations to print at the current level
     */
    private String toString(Node node, int numIndentations) {
        StringBuilder sb = new StringBuilder();
        if (node == null) {
            return sb.toString();
        }
        // FILL IN CODE: Iterate over the children
        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                char ch = (char) ('a' + i); // character on the incoming edge of node.children[i]
                // Print indentations
                // Print the character
                // Print * if isWord is true for the child at index i
                // Append a new line character (you can use System.lineSeparator())
                // Make a recursive call on the same method and append the result to sb
            }
        }
        return sb.toString();
    }
}
