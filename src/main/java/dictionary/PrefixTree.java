package dictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
        root = new Node();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                add(line);
            }
        } catch (IOException e) {
            System.out.println("IO error.");
        }
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
        delete(word.toLowerCase(), root, 0);
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
        return suggest(word, root);
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
        if (word.length() == 0) { // This is the node where this word "ends"
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
        if (word.length() == 0) {
            return node.isWord; // return the boolean value of 'valid bit'
        }

        // Otherwise - get the index of the next character in the word
        int index = (int) word.charAt(0) - (int) 'a';
        // If the child node at the index is null, the word is not in the dictionary
        if (node.children[index] == null) {
            return false;
        }

        // Recursive call - check the remaining part of the word
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

        // Base case - end of prefix reached
        if (prefix.length() == 0) {
            return true;
        }

        int index = (int) prefix.charAt(0) - (int) 'a'; // Get index of next char

        // Check bounds
        if (index < 0 || index > 26) {
            return false;
        }

        // Base case - no matching child for the current char
        if (node.children[index] == null) {
            return false;
        }

        // Recursive call - proceed with next char in the prefix
        return checkPrefix(prefix.substring(1), node.children[index]);

    }

    /**
     * A private recursive method to delete a word from the prefix tree.
     *
     * @param word  word to delete
     * @param node  root of the subtree
     * @param depth how far we have recursed into tree
     * @return true if the current node becomes a leaf after deleting its child
     */
    private boolean delete(String word, Node node, int depth) {

        // Base case - null node
        if (node == null) {
            return false;
        }

        // Base case - empty word
        if (depth == word.length()) {
            // Check boolean flag and update it to false if necessary
            if (node.isWord) {
                node.isWord = false;

                // Check if the node is a leaf node
                boolean isLeaf = true;
                for (int i = 0; i < 26; i++) {
                    if (node.children[i] != null) {
                        isLeaf = false; // Node has children, can't delete
                        break;
                    }
                }
                return isLeaf; // Node is a leaf, can delete
            }

            return false; // Word not in tree
        }

        // Recursive case
        int index = word.charAt(depth) - 'a'; // Get index for next char
        boolean isChildLeaf = delete(word, node.children[index], depth + 1); // Check if child becomes leaf

        // We reach this point after base case in recursion is hit and we start returning back up
        // Now dealing with what happens as we go back up the tree
        if (isChildLeaf) {
            node.children[index] = null; // Remove child if it's a leaf

            // Check if current node became a leaf
            boolean isLeaf = true;
            for (int i = 0; i < 26; i++) {
                if (node.children[i] != null) {
                    isLeaf = false; // Node still has children
                    break;
                }
            }

            return isLeaf && !node.isWord; // Delete current node if it's a leaf and not a word
        }

        return false; // Node not deleted, still has children or is a word
    }


    /**
     * Returns a string representing a tree: uses indentations to show nodes at different levels
     * Shows the letter corresponding to the incoming edge of the node.
     * Adds * to the prefix of the node, if the valid bit is set to true
     * @param node the root of the tree
     * @param numIndentations the number of indentations to print at the current level
     */
    /**
     * Returns a string representing a tree: uses indentations to show nodes at different levels
     * Shows the letter corresponding to the incoming edge of the node.
     * Adds * to the prefix of the node, if the valid bit is set to true
     *
     * @param node            the root of the tree
     * @param numIndentations the number of indentations to print at the current level
     */
    private String toString(Node node, int numIndentations) {

        StringBuilder sb = new StringBuilder();

        // Base case
        if (node == null) {
            return sb.toString();
        }

        // Recursive case - iterate through child nodes
        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                char ch = (char) ('a' + i); // character on the incoming edge of node.children[i]

                // Print indentations
                for (int j = 0; j < numIndentations; j++) {
                    sb.append(" ");
                }

                sb.append(ch); // Print the char

                // Add '*' if this node marks the end of a valid word
                if (node.children[i].isWord) {
                    sb.append("*");
                }

                sb.append(System.lineSeparator()); // Append new line char

                // Recursive call to process child node
                sb.append(toString(node.children[i], numIndentations + 1));
            }
        }
        return sb.toString();
    }


    /**
     * Suggests a word from the tree with the longest common prefix.
     *
     * @param word The word to suggest.
     * @param root The root node of the tree.
     * @return The suggested word, or null if none found.
     */
    private String suggest(String word, Node root) {
        StringBuilder prefixBuilder = new StringBuilder();
        Node prefixNode = longestCommonPrefix(word, root, prefixBuilder, 0);

        // If no common prefix, return null
        if (prefixNode == null) {
            return null;
        }
        // Find valid words starting from the prefix node
        ArrayList<String> validWords = new ArrayList<>();
        findValidWords(prefixNode, prefixBuilder, validWords);

        if (validWords.size() == 0) {
            return null;
        } else {
            return validWords.get(0);
        }
    }

    /**
     * Finds the node with the longest common prefix with the given word in the tree
     *
     * @param word Target word
     * @param node Current node (start with root).
     * @param prefixBuilder Builds the prefix as traversing.
     * @param depth Current depth in tree.
     * @return Node with longest common prefix, null if not found.
     */
    private Node longestCommonPrefix(String word, Node node, StringBuilder prefixBuilder, int depth) {
        // Base case: null node or full depth reached
        if (node == null || depth == word.length()) {
            return node;
        }

        int index = word.charAt(depth) - 'a'; // Index for char at current depth

        // Traverse deeper if child exists for character
        if (node.children[index] != null) {
            prefixBuilder.append(word.charAt(depth));
            return longestCommonPrefix(word, node.children[index], prefixBuilder, depth + 1);
        }

        // Return node where path ends
        return node;
    }

    /**
     * Collects valid words starting from a node.
     *
     * @param node        The node to start from.
     * @param currentWord Builds words during traversal.
     * @param validWords  Stores found valid words.
     */
    private void findValidWords(Node node, StringBuilder currentWord, ArrayList<String> validWords) {
        if (node == null) {
            return;
        }
        //If the node is a word ensure it is stored first in valid words
        if (node.isWord) {
            validWords.add(currentWord.toString());
        }

        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                char charToAdd = (char) (i + 'a');
                currentWord.append(charToAdd);
                findValidWords(node.children[i], currentWord, validWords);
                currentWord.deleteCharAt(currentWord.length() - 1); // Backtrack
            }
        }
    }
}


