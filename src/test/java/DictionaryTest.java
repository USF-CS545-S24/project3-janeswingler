import dictionary.Dictionary;
import dictionary.PrefixTree;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DictionaryTest {

    @Test
    public void testAddCheck() {
        Dictionary d = new PrefixTree();
        String[] words = {"apple", "ale", "app", "dog", "day",
                "camel", "dove", "cat", "cats", "camera", "cater"};
        try {
            for (int i = 0; i < words.length; i++) {
                d.add(words[i]);
            }
        }
        catch (Exception e) {
            Assert.fail("Exception occurred while adding one of the words");
        }

        String[] invalidWords = {"ca", "c", "al", "apples", "dogs", "days", "anecdote",
        "xyz", "catss", "cameo", "train"};
        for (int i = 0; i < words.length; i++) {
            Assert.assertTrue("check(\"" + words[i] + "\") should return true, not false.", d.check(words[i]));
            Assert.assertFalse("check(\"" + invalidWords[i] + "\") should return false, not true.", d.check(invalidWords[i]));
        }
    }

    @Test
    public void testAddAndToString() {
        Dictionary d = new PrefixTree();
        String[] words = {"apple", "ale", "app", "dog", "day",
                "camel", "dove", "cat", "cats", "camera", "cater"};
        try (PrintWriter pw = new PrintWriter("studentDictionary1.txt")){
            for (int i = 0; i < words.length; i++) {
                d.add(words[i]);
            }
            String stringRes = d.toString();
            pw.println(stringRes);
            pw.flush();

            int count;
            try {
                count = TestUtils.checkFiles(Paths.get("expectedDictionary1.txt"), Paths.get("studentDictionary1.txt"));
                if (count <= 0)
                    Assert.fail("testAddAndToString failed. Expected result is not equal to the actual result.");
            } catch (IOException e) {
                Assert.fail(" File check failed: " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("IOException occured.");
        }
    }

    @Test
    public void testSmallDictionaryAddAndToString() {
        Dictionary d = new PrefixTree("input/small.txt");
        try (PrintWriter pw = new PrintWriter("studentSmallTree.txt")){
            String stringRes = d.toString();
            pw.println(stringRes);
            pw.flush();

            int count;
            try {
                count = TestUtils.checkFiles(Paths.get("expectedTree_Small.txt"), Paths.get("studentSmallTree.txt"));
                if (count <= 0)
                    Assert.fail("testSmallDictionary() failed. Expected result is not equal to the actual result.");
            } catch (IOException e) {
                Assert.fail(" File check failed: " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("IOException occured.");

        }
    }


    @Test
    public void testSmallDictionaryCheck() {
        Dictionary d = new PrefixTree("input/small.txt");
        String[] words = {
                "time", "year", "people", "way", "day", "man", "thing", "woman", "life", "child",
                "world", "school", "state", "family", "student", "group", "country", "problem", "hand",
                "part", "place", "case", "week", "company", "system", "program", "question", "work",
                "government", "number", "night", "point", "home", "water", "room", "mother", "area",
                "money", "story", "fact", "month", "lot", "right", "study", "book", "eye", "job",
                "word", "business", "issue", "side", "kind", "head", "house", "service", "friend",
                "father", "power", "hour", "game", "line", "end", "member", "law", "car", "city",
                "community", "name", "president", "team", "minute", "idea", "kid", "body", "information",
                "back", "parent", "face", "others", "level", "office", "door", "health", "person", "art",
                "war", "history", "party", "result", "change", "morning", "reason", "research", "girl",
                "guy", "moment", "air", "teacher", "force", "education"
        };
        for (int i = 0; i < words.length; i++) {
            Assert.assertTrue("check(\"" + words[i] + "\") should have returned true", d.check(words[i]));
        }

        String[] invalidWords = {"ar", "arts", "blink", "hiss", "res", "zone", "girls" };
        for (int i = 0; i < invalidWords.length; i++) {
            Assert.assertFalse("check(\"" + invalidWords[i] + "\") should return false, not true.", d.check(invalidWords[i]));
        }
    }

    @Test
    public void testSmallDictionaryCheckPrefix() {
        Dictionary d = new PrefixTree("input/small.txt");
        String[] prefixes = {
                "t", "ti", "tim", "time", "y", "ye", "yea", "year",
                "p", "pe", "peo", "peop", "peopl", "w", "wa", "way",
                 "d", "da", "day", "m", "ma", "man", "t", "th", "thi", "thin", "thing",
                 "wo", "wom", "woma", "woman", "li", "ch", "child",
                 "worl", "f", "fa", "fat", "fath", "fathe", "father", "pow", "power", "hou",
                  "g", "ga", "gam", "game", "l", "li", "lin", "line",
                  "res", "resear", "gi", "mom", "edu", ""
        };
        for (int i = 0; i < prefixes.length; i++) {
            Assert.assertTrue("checkPrefix(\"" + prefixes[i] + "\") should have returned true", d.checkPrefix(prefixes[i]));
        }

        String[] invalidPrefixes = {" ", "dr", "to", "mk", "ime", "esult", "ult", "rls", "ath", "ine", "ow", "ke", "ath", "far" };
        for (int i = 0; i < invalidPrefixes.length; i++) {
            Assert.assertFalse("checkPrefix(\"" + invalidPrefixes[i] + "\") should return false, not true.", d.checkPrefix(invalidPrefixes[i]));
        }
    }

    @Test
    public void testSmallDictionaryDelete() {
        Dictionary d = new PrefixTree("input/small.txt");
        d.delete("thing");
        d.delete("company");
        d.delete("kind");
        d.delete("case");
        String[] words = {
                "time", "year", "people", "way", "day", "man", "woman", "life", "child",
                "world", "school", "state", "family", "student", "group", "country", "problem", "hand",
                "part", "place", "week", "system", "program", "question", "work",
                "government", "number", "night", "point", "home", "water", "room", "mother", "area",
                "money", "story", "fact", "month", "lot", "right", "study", "book", "eye", "job",
                "word", "business", "issue", "side", "head", "house", "service", "friend",
                "father", "power", "hour", "game", "line", "end", "member", "law", "car", "city",
                "community", "name", "president", "team", "minute", "idea", "kid", "body", "information",
                "back", "parent", "face", "others", "level", "office", "door", "health", "person", "art",
                "war", "history", "party", "result", "change", "morning", "reason", "research", "girl",
                "guy", "moment", "air", "teacher", "force", "education"
        };
        for (int i = 0; i < words.length; i++) {
            Assert.assertTrue("The word \"" + words[i] + "\") should still be in the dictionary", d.check(words[i]));
        }
        String[] deletedWords = {"thing","case", "company","kind" };

        for (int i = 0; i < deletedWords.length; i++) {
            Assert.assertFalse("We deleted this word, so check(\"" + deletedWords[i] + "\") should return false, not true.", d.check(deletedWords[i]));
        }
    }

    @Test
    public void testDelete() {
        Dictionary d = new PrefixTree();
        String[] words = {"apple", "ale", "app", "dog", "day",
                "camel", "dove", "cat", "cats", "camera", "cater"};
        try {
            for (int i = 0; i < words.length; i++) {
                d.add(words[i]);
            }
        }
        catch (Exception e) {
            Assert.fail("Exception occured while adding one of the words");
        }

        d.delete("cat");
        d.delete("apple");
        d.delete("camera");

        String[] wordsAfterDeletion = {"ale", "app", "dog", "day",
                "camel", "dove", "cats", "cater"};

        Assert.assertFalse("The word cat was supposed to be deleted, but check returns true", d.check("cat"));
        Assert.assertFalse("The word apple was supposed to be deleted, but check returns true", d.check("apple"));
        Assert.assertFalse("The word camera was supposed to be deleted, but check returns true", d.check("camera"));

        for (int i = 0; i < wordsAfterDeletion.length; i++) {
            Assert.assertTrue("The word \"" + wordsAfterDeletion[i] + " was supposed to be in the dictionary; it was not deleted", d.check(wordsAfterDeletion[i]));
        }
    }

    @Test
    public void testSuggestSimple() {
        Dictionary d = new PrefixTree();
        String[] words = {"apple", "ale", "app", "dog", "day",
                "camel", "dove", "cat", "cats", "camera", "cater"};
        for (int i = 0; i < words.length; i++) {
            d.add(words[i]);
        }
        Assert.assertTrue("The suggestion for can should be camel", d.suggest("can").equals("camel"));
        Assert.assertTrue("The suggestion for cat should be cat", d.suggest("cat").equals("cat"));
        Assert.assertTrue("The suggestion for caty should be cat", d.suggest("caty").equals("cat"));
        Assert.assertTrue("The suggestion for dy should be day", d.suggest("dy").equals("day"));
    }

    @Test
    public void testSmallDictionarySuggest() {
        Dictionary d = new PrefixTree("input/small.txt");
        String[] misspelledWords = {"fa", "chek", "eudarion", "lawer", "cad", "reeson", "chaneg", "citey","aree", "prolbem"};
        String[] suggestions = {"face", "change", "education", "law", "car", "reason", "change", "city", "area", "problem"};
        for (int i = 0; i < misspelledWords.length; i++) {
            Assert.assertTrue("suggest(\"" + misspelledWords[i] + "\") should return " + suggestions[i], d.suggest(misspelledWords[i]).equals(suggestions[i]));
        }
    }

    @Test
    public void testLargeDictionaryCheck() {
        Dictionary d = new PrefixTree("input/large.txt");
        try (BufferedReader br = Files.newBufferedReader(Paths.get("input/large.txt"))) {
            String word;
            while ((word = br.readLine()) != null) {
                Assert.assertTrue("check(\"" + word + "\") should have returned true", d.check(word));
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        String[] invalidWords = {"araa", "arti", "blinx", "hisyva", "rebsbs", "zonbabe", "gigrls"};
        for (int i = 0; i < invalidWords.length; i++) {
            Assert.assertFalse("check(\"" + invalidWords[i] + "\") should return false, not true.", d.check(invalidWords[i]));
        }
    }

    @Test
    public void testLargeDictionarySuggest() {
        Dictionary d = new PrefixTree("input/large.txt");
        String[] misspelledWords = {"aqau", "cheeck", "trayn", "lawer", "queu", "rison", "chanec", "citey", "aree", "probemma"};
        String[] suggestions = {"aqua", "cheek", "tray", "lawed", "queue", "risotto", "chance", "cite", "are", "probe"};
        for (int i = 0; i < misspelledWords.length; i++) {
            Assert.assertTrue("suggest(\"" + misspelledWords[i] + "\") should return " + suggestions[i], d.suggest(misspelledWords[i]).equals(suggestions[i]));
        }
    }

}
