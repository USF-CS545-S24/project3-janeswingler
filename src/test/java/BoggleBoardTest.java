import boggle.BoggleBoard;
import dictionary.Dictionary;
import dictionary.PrefixTree;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

public class BoggleBoardTest {
    private Dictionary dictionary;

    public BoggleBoardTest() {
        dictionary = new PrefixTree("input/small.txt");
    }

    @Test
    public void testBoard1() {
        BoggleBoard board = new BoggleBoard("input/board1.txt", dictionary);
        Set<String> results = board.findValidWords();
        Assert.assertTrue("The number of generated words should be 2", results.size() == 2);
        Assert.assertTrue("The word MAN is not found ", results.contains("MAN"));
        Assert.assertTrue("The word NAME is not found ", results.contains("NAME"));
    }

    @Test
    public void testBoard2() {
        BoggleBoard board = new BoggleBoard("input/board2.txt", dictionary);
        Set<String> results = board.findValidWords();
        Assert.assertTrue("The number of generated words should be 3", results.size() == 3);
        Assert.assertTrue("The word CAR is not found ", results.contains("CAR"));
        Assert.assertTrue("The word ART is not found ", results.contains("ART"));
        Assert.assertTrue("The word END is not found ", results.contains("END"));
    }

    @Test
    public void testBoard3() {
        BoggleBoard board = new BoggleBoard("input/board3.txt", dictionary);
        Set<String> results = board.findValidWords();
        Assert.assertTrue("The number of generated words should be 2", results.size() == 2);
        Assert.assertTrue("The word DAY is not found ", results.contains("DAY"));
        Assert.assertTrue("The word DOOR is not found ", results.contains("DOOR"));
    }

    @Test
    public void testBoard4() {
        BoggleBoard board = new BoggleBoard("input/board4.txt", dictionary);
        Set<String> results = board.findValidWords();
        Assert.assertTrue("The number of generated words should be 2", results.size() == 2);
        Assert.assertTrue("The word EYE is not found ", results.contains("EYE"));
        Assert.assertTrue("The word LINE is not found ", results.contains("LINE"));
    }

    @Test
    public void testBoard5() {
        BoggleBoard board = new BoggleBoard("input/board5.txt", dictionary);
        Set<String> results = board.findValidWords();
        System.out.println(results);
        Assert.assertTrue("The number of generated words should be 5", results.size() == 5);
        Assert.assertTrue("The word SIDE is not found ", results.contains("SIDE"));
        Assert.assertTrue("The word IDEA is not found ", results.contains("IDEA"));
        Assert.assertTrue("The word LINE is not found ", results.contains("LINE"));
        Assert.assertTrue("The word PRESIDENT is not found ", results.contains("PRESIDENT"));
        Assert.assertTrue("The word END is not found ", results.contains("END"));
    }

}
