package boggle;

import dictionary.Dictionary;
;
import java.util.HashSet;
import java.util.Set;

/** A class that stores the Boggle game board, and
 * contains a method to generate valid words and play a simple game. */
public class BoggleBoard {
    public static final int BOARD_WIDTH = 4;
    public static final int BOARD_HEIGHT = 4;
    private String board[][]; // boggle board
    private Dictionary dict; // dictionary used to check if a word is valid
    private boolean[][] visited; // a 2D array that tells us which board cells we already visited during the search

    /**
     * Creates a new board from the board file; Takes a dictionary.
     * @param filename file containing a description of the board
     * @param dict The dictionary to use for checking word validity
     */
    public BoggleBoard(String filename, Dictionary dict) {
        this.dict = dict;
        board = new String[BOARD_WIDTH][BOARD_HEIGHT];
        visited = new boolean[BOARD_WIDTH][BOARD_HEIGHT];
        // FILL IN CODE: each line of the input file corresponds to one row in the boggle board

    }

    /**
     * Prints each row of the boggle board to the console.
     * The format must match the format of board1.txt etc.
     */
    public void printBoard() {
        // FILL IN CODE:

    }


    /**
     * Finds valid words on the boggle board following the rules of the Boggle game:
     * where we can move horizontally, vertically, and diagonally,
     * but cannot skip letters.
     * Example: in the board below, we can find the word "MAN" by first going left
     * in the third row to find "M" and "A", and then going diagonally to the bottom right
     * to find "N". We can also find "NAME" by starting with the third letter ("N")
     * in the bottom row, then going diagonally to the top left to find "A",
     * then left to find "M" and finally, going up to find "E".
     P	 O	L	V
     E	 S	A	I
     M	 A	X	E
     Y	 Z	N	O
     * We decide whether the word is valid based on the dictionary.
     * @return a set of valid words generated from the Boggle board.
     *
     */
    public Set<String> findValidWords() {
        Set<String> words = new HashSet<>();
        // You are allowed to use a HashSet for this method.
        // FILL IN CODE:
        // Iterate over the board and call a recursive helper method


        return words;
    }

    // FILL IN CODE: add a recursive helper method that searches for valid words
    // Think of what parameters you need to pass apart from i, j of the board cell.

    /**
     * A method that can be called to let the user play a simplified
     * version of Boggle against the computer (once).
     * The program should ask the user to "Please type valid words you can generate from this board, separated by space."
     * After the user types the words they find, the program should print words found by the computer by calling findValidWords
     * and provide feedback to the user.
     * If the user's list is not of the right size, the program should print
     * "Your list of words is not as expected", and then print computer's words.
     * If the user found some words that are invalid, the program should
     * say which words are invalid.
     * If the user's list matches the computer's list, the program should print
     * "Great job!".
     * Note that only words present in the given board AND the given dictionary are considered "valid",
     *
     */
    public void play() {
        // FILL IN CODE

    }
}