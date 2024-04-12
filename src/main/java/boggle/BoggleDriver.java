package boggle;
import dictionary.Dictionary;
import dictionary.PrefixTree;

/**
 * Driver class for playing Boggle
 */
public class BoggleDriver {
    public static void main(String[] args) {
        Dictionary dict = new PrefixTree("input/small.txt");
        BoggleBoard board = new BoggleBoard("input/board1.txt", dict);
        board.play();
    }
}
