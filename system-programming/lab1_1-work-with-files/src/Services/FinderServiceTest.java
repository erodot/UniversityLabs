package Services;

import com.sun.scenario.animation.shared.FiniteClipEnvelope;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class FinderServiceTest {
    @Test
    public void oneToFour() throws Exception {
        String[] _words = {"One","Two","Three","Four"};
        String[] _expectedOutput = {"Three"};

        ArrayList<String> words = new ArrayList<String>(Arrays.asList(_words));
        ArrayList<String> expectedOutput = new ArrayList<String>(Arrays.asList(_expectedOutput));

        assertEquals(expectedOutput,FinderService.findLongestConsonantChain(words));
    }

    @Test
    public void fiveToNine() throws Exception {
        String[] _words = {"Five","Six","Seven","Eight", "Nine"};
        String[] _expectedOutput = {"Eight"};

        ArrayList<String> words = new ArrayList<String>(Arrays.asList(_words));
        ArrayList<String> expectedOutput = new ArrayList<String>(Arrays.asList(_expectedOutput));

        assertEquals(expectedOutput,FinderService.findLongestConsonantChain(words));
    }

    @Test
    public void avoidDuplication() throws Exception {
        String[] _words = {"I","I","ought","ought", "you", "you", "empty", "empty"};
        String[] _expectedOutput = {"ought","empty"};

        ArrayList<String> words = new ArrayList<String>(Arrays.asList(_words));
        ArrayList<String> expectedOutput = new ArrayList<String>(Arrays.asList(_expectedOutput));

        assertEquals(expectedOutput,FinderService.findLongestConsonantChain(words));
    }

    @Test
    public void noCons() throws Exception {
        String[] _words = {"I","a","you","ae"};
        String[] _expectedOutput = {"I","a","you","ae"}; // All words has no cons -> 0 is max

        ArrayList<String> words = new ArrayList<String>(Arrays.asList(_words));
        ArrayList<String> expectedOutput = new ArrayList<String>(Arrays.asList(_expectedOutput));

        assertEquals(expectedOutput,FinderService.findLongestConsonantChain(words));
    }

    @Test
    public void allCons() throws Exception {
        String[] _words = {"bcd","cdr","q","plk", "sx"};
        String[] _expectedOutput = {"bcd","cdr", "plk"};

        ArrayList<String> words = new ArrayList<String>(Arrays.asList(_words));
        ArrayList<String> expectedOutput = new ArrayList<String>(Arrays.asList(_expectedOutput));

        assertEquals(expectedOutput,FinderService.findLongestConsonantChain(words));
    }

    @Test
    public void emptyArray() throws Exception {
        String[] _words = {};
        String[] _expectedOutput = {};

        ArrayList<String> words = new ArrayList<String>(Arrays.asList(_words));
        ArrayList<String> expectedOutput = new ArrayList<String>(Arrays.asList(_expectedOutput));

        assertEquals(expectedOutput,FinderService.findLongestConsonantChain(words));
    }

    @Test(expected=IllegalArgumentException.class)
    public void notOnlyLettersInTheEnd() {
        String[] _notCorrectArray = {"Ias1,", "sdf9*^"};

        ArrayList<String> notCorrectArray = new ArrayList<String>(Arrays.asList(_notCorrectArray));

        ArrayList<String> catchExc = new ArrayList<String>(FinderService.findLongestConsonantChain(notCorrectArray));

    }

    @Test(expected=IllegalArgumentException.class)
    public void notOnlyLettersInTheBegginning() {
        String[] _notCorrectArray = {"-me", "#hash&"};

        ArrayList<String> notCorrectArray = new ArrayList<String>(Arrays.asList(_notCorrectArray));

        ArrayList<String> catchExc = new ArrayList<String>(FinderService.findLongestConsonantChain(notCorrectArray));

    }

    @Test(expected=IllegalArgumentException.class)
    public void notOnlyLettersInTheMiddle() {
        String[] _notCorrectArray = {"mf@e", "has!23h"};

        ArrayList<String> notCorrectArray = new ArrayList<String>(Arrays.asList(_notCorrectArray));

        ArrayList<String> catchExc = new ArrayList<String>(FinderService.findLongestConsonantChain(notCorrectArray));

    }

    @Test(expected=IllegalArgumentException.class)
    public void notLetters() {
        String[] _notCorrectArray = {"(*&%$@&*", "#)(I#!&"};

        ArrayList<String> notCorrectArray = new ArrayList<String>(Arrays.asList(_notCorrectArray));

        ArrayList<String> catchExc = new ArrayList<String>(FinderService.findLongestConsonantChain(notCorrectArray));

    }

    @Test
    public void veryLongSentence() throws Exception {
        String[] _words = { "Knowing", "that", "millions", "of", "peoples", "around", "the", "world", "would", "be", "watching", "in", "person", "and", "on", "television", "and", "expecting", "great", "things", "from", "him", "at", "least", "one", "more", "gold", "medal", "for", "America", "if", "not", "another", "world", "record", "during", "this", "his", "fourth", "and", "surely", "his", "last", "appearance", "in", "the", "World", "Olympics", "and", "realizing", "thathis", "legs", "could", "no", "longer", "carry", "him", "down", "the", "runway", "with", "the", "same", "blazing", "speed", "and", "confidence", "in", "making", "a", "huge", "eye", "popping", "leap", "that", "they", "were", "capable", "of", "a", "few", "years", "ago", "when", "he", "set", "world", "records", "in", "the", "meter", "dash", "and", "in", "the", "meter", "relay", "and", "won", "a", "silver", "medal", "in", "the", "long", "jump", "the", "renowned", "sprinter", "and", "track", "and", "field", "personality", "Carl", "Lewis", "who", "had", "known", "pressure", "from", "fans", "and", "media", "before", "but", "never", "even", "as", "a", "professional", "runner", "this", "kind", "of", "pressure", "made", "only", "a", "few", "appearances", "in", "races", "during", "the", "few", "months", "before", "the", "Summer", "Olympics", "in", "Atlanta", "Georgia", "partly", "because", "he", "was", "afraid", "of", "raising", "expectations", "even", "higher", "and", "he", "did", "not", "want", "to", "be", "distracted", "by", "interviews", "and", "adoring", "fans", "who", "would", "follow", "him", "into", "stores", "and", "restaurants", "demanding", "autographs", "and", "photo", "opportunities", "but", "mostly", "because", "he", "wanted", "to", "conserve", "his", "energies", "and", "concentrate", "like", "a", "martial", "arts", "expert", "on", "the", "job", "at", "hand", "winning", "his", "favorite", "competition", "the", "long", "jump", "and", "bringing", "home", "another", "Gold", "Medal", "for", "the", "United", "States", "the", "most", "fitting", "conclusion", "to", "his", "brilliant", "career", "in", "track", "and", "field"};
        String[]_expectedOutput = {"months"};

        ArrayList<String> words = new ArrayList<String>(Arrays.asList(_words));
        ArrayList<String> expectedOutput = new ArrayList<String>(Arrays.asList(_expectedOutput));

        assertEquals(expectedOutput,FinderService.findLongestConsonantChain(words));
    }
}